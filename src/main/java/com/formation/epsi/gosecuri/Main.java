package com.formation.epsi.gosecuri;

import com.formation.epsi.gosecuri.model.Guard;
import com.formation.epsi.gosecuri.model.Staff;

import java.io.*;
import java.util.*;

import freemarker.template.*;

public class Main {

	/* Templates */
	static String TEMPLATES_PATH = "E:/www/epsi/mspr_tpre500/go-securi-appli/src/main/java/com/formation/epsi/gosecuri/templates";
	static String TEMPLATE_INDEX = "index.ftl";
	static String TEMPLATE_GUARD = "guard.ftl";

	/* Data */
	static String DATA_DIR = "E:/www/epsi/mspr_tpre500/go-securi-data/";
	static String DATA_FILE = "staff.txt";

	/* Target */
	static String TARGET_DIR = "out/";
	static String TARGET_INDEX = "index.html";

	public static void main(String[] args) throws IOException, TemplateException {
		/* Create freemarker configuration */
		Configuration cfg = configuration(TEMPLATES_PATH);

		/* Create the guards */
		List<Guard> guards = guardsFactory();

		/* Create the staff */
		Staff staff = staffFactory(guards);

		/* Create the homepage */
		generateIndex(cfg, staff);

		/* Create a page for each guard */
		generateGuardsPage(cfg, staff);
	}

	private static List<Guard> guardsFactory() throws IOException {
		/* Create a list of guards */
		List<Guard> guards = new ArrayList<>();

		/* Scanning staff txt file */
		String staffData = DATA_DIR+DATA_FILE;
		try (Scanner staffDataScan = new Scanner(new File(staffData))) {
			while (staffDataScan.hasNext()) {

				/* The shortname of the guard is retrieved */
				String shortname = staffDataScan.nextLine().trim();

				/* A filename is generated */
				String guardData = DATA_DIR+shortname+".txt";
				File guardDataFile = new File(guardData);

				/* Scanning txt file of each guard */
				/* If the file exist */
				if(guardDataFile.exists()) {
					/* Declaration of the number of lines counter */
					int i = 0;
					Scanner guardDataScan = new Scanner(guardDataFile);

					/* Create a new guard */
					Guard guard = new Guard();
					guard.setId(shortname);
					while (guardDataScan.hasNext()) {
						/* Counter increment */
						i++;
						/* the data is retrieved */
						String data = guardDataScan.nextLine().trim();
						if (i == 1) {
							guard.setLastname(data);
						} else if (i == 2) {
							guard.setFirstname(data);
						} else if (i == 3) {
							guard.setJob(data);
						} else if (i == 4) {
							guard.setPassword(data);
						}
					}
					guardDataScan.close();

					/*  Adding the new guard to the list */
					guards.add(guard);
				}
			}
		}
		/*  Return the list */
		return guards;
	}

	/**
	 *
	 * @param guards List of guards
	 * @return Staff
	 */
	private static Staff staffFactory(List<Guard> guards) {
		Staff staff = new Staff();
		staff.setGuards(guards);

		return staff;
	}

	private static void generateIndex(Configuration cfg, Staff staff) throws TemplateException, IOException {
		Map<String, Object> indexData = new HashMap<>();
		indexData.put("title", "Liste des agents");
		indexData.put("guards", staff.getGuards());
		generateHTML(cfg, indexData, TEMPLATE_INDEX, TARGET_INDEX);
	}

	private static void generateGuardsPage(Configuration cfg, Staff staff) throws TemplateException, IOException {
		for (Guard guard : staff.getGuards()) {
			Map<String, Object> guardData = new HashMap<>();
			guardData.put("title", guard.getId());
			guardData.put("guard", guard);
			generateHTML(cfg, guardData, TEMPLATE_GUARD, String.format("%s.html", guard.getId()) );
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
