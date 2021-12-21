package com.formation.epsi.gosecuri;

import com.formation.epsi.gosecuri.model.Staff;

import java.io.*;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import freemarker.template.*;

public class Main {

	public static void main(String[] args) throws IOException, TemplateException {
		String STAFF_DATA = "E:/www/epsi/mspr_tpre500/go-securi-data/staff.txt";
		String TEMPLATES_PATH = "E:/www/epsi/mspr_tpre500/go-securi-appli/src/main/java/com/formation/epsi/gosecuri/templates";
		String TEMPLATES_NAME = "staff.ftl";
		String OUTPUT_PATH = "out/index.html";

		/* Create and adjust the configuration singleton */
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);
		cfg.setDirectoryForTemplateLoading(new File(TEMPLATES_PATH));
		cfg.setDefaultEncoding("UTF-8");
		cfg.setLocale(Locale.FRANCE);
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		cfg.setLogTemplateExceptions(false);
		cfg.setWrapUncheckedExceptions(true);
		cfg.setFallbackOnNullLoopVariable(false);

		/* Create a data-model */
		Map<String, Object> input = new HashMap<>();
		Staff staff = new Staff(STAFF_DATA);
		input.put("guards", staff.getGuards());

		/* Get the template (uses cache internally) */
		Template template = cfg.getTemplate(TEMPLATES_NAME);

		/* Merge data-model with template in a new file */
		try (Writer fileWriter = new FileWriter(OUTPUT_PATH)) {
			template.process(input, fileWriter);
		}
	}
}
