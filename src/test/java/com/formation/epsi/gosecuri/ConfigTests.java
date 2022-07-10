package com.formation.epsi.gosecuri;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.ResourceBundle;

import org.junit.jupiter.api.Test;

public class ConfigTests {

	private static final ResourceBundle resource = ResourceBundle.getBundle("info");
	public static String dataFolder = resource.getString("data.dir");
	public static String publicFolder = resource.getString("target.dir");
	public static String templateFolder = resource.getString("templates.path");
	public static String cssFolder = publicFolder + "css";

	@Test
	public void dataDirectoryTests() {
		assertTrue(new File(dataFolder).exists());
	}

	@Test
	public void publicDirectoryTests() {
		assertTrue(new File(publicFolder).exists());
	}

	@Test
	public void templateDirectoryTests() {
		assertTrue(new File(templateFolder).exists());
	}

	@Test
	public void cssDirectoryTests() {
		assertTrue(new File(cssFolder).exists());
	}

}
