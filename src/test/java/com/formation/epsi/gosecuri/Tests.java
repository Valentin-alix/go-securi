package com.formation.epsi.gosecuri;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.Scanner;

import org.apache.commons.lang3.ThreadUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.formation.epsi.gosecuri.factory.EquipmentsFactory;
import com.formation.epsi.gosecuri.factory.FreemarkerConfigurationFactory;
import com.formation.epsi.gosecuri.factory.GuardPageFactory;
import com.formation.epsi.gosecuri.factory.GuardsFactory;
import com.formation.epsi.gosecuri.factory.IndexPageFactory;
import com.formation.epsi.gosecuri.model.Equipment;
import com.formation.epsi.gosecuri.model.Guard;
import com.formation.epsi.gosecuri.util.CopyCardsId;

import freemarker.template.Configuration;

class Tests {

	// Récupération des données de info.properties
	private static final ResourceBundle resource = ResourceBundle.getBundle("info");
	static String templateIndex = resource.getString("template.index");
	static String targetIndex = resource.getString("target.index");
	static String targetData = resource.getString("target.dir");

	static HashMap<String, Equipment> equipments = null;
	static List<Guard> guards = null;
	static Configuration cfg = null;
	static IndexPageFactory indexPage = null;
	static CopyCardsId copyCardsId = null;

	@BeforeAll
	public static void setUp() throws IOException {
		equipments = EquipmentsFactory.create();
		guards = GuardsFactory.create(equipments);
		cfg = FreemarkerConfigurationFactory.create();
		indexPage = new IndexPageFactory(cfg, guards);
		copyCardsId = new CopyCardsId(guards);
	}

	@ParameterizedTest
	@CsvSource({ "lampe, Lampe Torche", "cyno, Bandeau agent cynophile" })
	public void createEquipments(String input, String expected) {
		/* test return name of equipments */
		assertEquals(equipments.get(input).getName(), expected);
	}

	@Disabled
	@ParameterizedTest
	@CsvSource({ "0, Corinne, Berthier, Surveillance entrepôt, pmNd1ldFE7WTk" })
	public void createGuards(int input, String expectedFirstname, String expectedLastname, String expectedJob,
			String expectedPassword) {
		/* test return values of GuardsFactory */
		assertEquals(guards.get(input).getFirstname(), expectedFirstname);
		assertEquals(guards.get(input).getLastname(), expectedLastname);
		assertEquals(guards.get(input).getJob(), expectedJob);
		assertEquals(guards.get(input).getPassword(), expectedPassword);

	}

	@Test
	public void createIndexPage() throws IOException, InterruptedException {
		// Tests relative to the IndexPage

		indexPage.start();

		while (ThreadUtils.findThreadsByName("index").size() != 0) {
		}
		int nbGuardsInIndexPage = 0;

		ArrayList<String> guardsName = new ArrayList<String>();

		for (int i = 0; i < guards.size(); i++) {
			guardsName.add(guards.get(i).getLastname());
		}

		File indexfile = new File(targetData + targetIndex);
		Scanner scanner = new Scanner(indexfile);
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			if (line.contains(".html")) {
				nbGuardsInIndexPage++;
			}

			for (int i = 0; i < guardsName.size(); i++) {
				if (line.contains(guardsName.get(i))) {
					guardsName.remove(i);
				}
			}
		}
		scanner.close();

		// Test if index.html contains the good number of html links
		assertEquals(nbGuardsInIndexPage - 1, guards.size());
		// Test if we find the name corresponding to guards name
		assertEquals(guardsName.size(), 0);

	}

	@Test
	public void createGuardsPage() throws InterruptedException, FileNotFoundException {
		// Tests guardsPage

		Map<String, Guard> expectedGuards = new HashMap<>();

		for (Guard guard : guards) {
			GuardPageFactory guardPage = new GuardPageFactory(cfg, guard);
			// put filename and guard in hashmap expectedGuards
			expectedGuards.put((guard.getFirstname().charAt(0) + guard.getLastname()).toLowerCase() + ".html", guard);

			guardPage.start();
			while (ThreadUtils.findThreadsByName(guard.getId()).size() != 0) {
			}
		}

		String lines = "";
		int numberAnalyzedFiles = 0;
		File publicFolder = new File(targetData);
		File[] filesOut = publicFolder.listFiles();
		for (File item : filesOut) {
			for (Entry<String, Guard> expectedGuard : expectedGuards.entrySet()) {
				if (expectedGuard.getKey().equals(item.getName())) {
					Scanner scanner = new Scanner(item);
					while (scanner.hasNextLine()) {
						lines += scanner.nextLine();
					}
					scanner.close();

					// Test that the properties of guard are in the guard html page

					assertTrue(lines.contains(expectedGuard.getValue().getFirstname()));
					assertTrue(lines.contains(expectedGuard.getValue().getLastname()));
					assertTrue(lines.contains(expectedGuard.getValue().getJob()));

					for (int i = 0; i < expectedGuard.getValue().getEquipments().size(); i++) {
						assertTrue(lines.contains(expectedGuard.getValue().getEquipments().get(i).getName()));
					}
					numberAnalyzedFiles++;
					lines = "";

				}
			}
		}

		// Test that all the guards html page were analyzed (relative to filename test)
		assertEquals(guards.size(), numberAnalyzedFiles);

	}

	@Test
	public void testCopyCardsId() {

		ArrayList<String> expectedCopyCardsFile = new ArrayList<>();
		for (int i = 0; i < guards.size(); i++) {
			expectedCopyCardsFile
					.add((guards.get(i).getFirstname().charAt(0) + guards.get(i).getLastname()).toLowerCase() + ".jpg");
		}

		int numberFilesCopyCards = 0;

		copyCardsId.start();
		File publicImage = new File(targetData + "images");
		File[] filesOut = publicImage.listFiles();
		for (File item : filesOut) {
			if (expectedCopyCardsFile.contains(item.getName())) {
				numberFilesCopyCards++;
			}
		}

		assertEquals(numberFilesCopyCards, guards.size());
	}
}
