package com.formation.epsi.gosecuri;

import com.formation.epsi.gosecuri.model.Guard;
import com.formation.epsi.gosecuri.model.Staff;

import java.io.*;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import freemarker.template.*;

public class Main {

	static String TEMPLATES_PATH = "E:/www/epsi/mspr_tpre500/go-securi-appli/src/main/java/com/formation/epsi/gosecuri/templates";
	static String DATA_DIR = "E:/www/epsi/mspr_tpre500/go-securi-data/";
	static String DATA_FILE = "staff.txt";
	static String TARGET_DIR = "out/";

	public static void main(String[] args) throws IOException, TemplateException {
		/* Create freemarker configuration */
		Configuration cfg = configuration(TEMPLATES_PATH);

		/* Create a new staff */
		Staff staff = new Staff(DATA_DIR, DATA_FILE);

		/* Create the homepage */
		generateIndex(cfg, staff);

		/* Create a page for each guard */
		generateGuardsPage(cfg, staff);
	}

	private static void generateIndex(Configuration cfg, Staff staff) throws TemplateException, IOException {
		Map<String, Object> indexData = new HashMap<>();
		indexData.put("title", "Liste des agents");
		indexData.put("guards", staff.getGuards());
		generateHTML(cfg, indexData, "index.ftl", "index.html" );
	}

	private static void generateGuardsPage(Configuration cfg, Staff staff) throws TemplateException, IOException {
		for (Guard guard : staff.getGuards()) {
			Map<String, Object> guardData = new HashMap<>();
			guardData.put("title", guard.getId());
			guardData.put("guard", guard);
			generateHTML(cfg, guardData, "guard.ftl", String.format("%s.html", guard.getId()) );
		}
	}

	private static Configuration configuration(String templateFileDir) throws IOException {
		/* Create and adjust the configuration singleton */
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);
		cfg.setDirectoryForTemplateLoading(new File(templateFileDir));
		cfg.setDefaultEncoding("UTF-8");
		cfg.setLocale(Locale.FRANCE);
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		cfg.setLogTemplateExceptions(false);
		cfg.setWrapUncheckedExceptions(true);
		cfg.setFallbackOnNullLoopVariable(false);

		return cfg;
	}

	private static void generateHTML(Configuration cfg, Object templateData, String templateName, String targetFileName) throws IOException, TemplateException {
		/* Get the template (uses cache internally) */
		Template template = cfg.getTemplate(templateName);

		/* Merge data-model with template in a new file */
		try (Writer fileWriter = new FileWriter(TARGET_DIR + targetFileName)) {
			template.process(templateData, fileWriter);
		}
	}
}
