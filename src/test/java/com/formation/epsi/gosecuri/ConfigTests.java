package com.formation.epsi.gosecuri;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.ResourceBundle;

import org.junit.jupiter.api.Test;

public class ConfigTests {

	private static final ResourceBundle resource = ResourceBundle.getBundle("info");
	public static String data = resource.getString("data.dir");
	public static String dataStaff = resource.getString("data.file.staff");
	public static String dataMaterial = resource.getString("data.file.material");

	@Test
	public void dataDirectoryTests() {
		assertTrue(new File(data).exists());
	}

	@Test
	public void staffFileTest() {
		assertTrue(new File(data + dataStaff).exists());
	}

	@Test
	public void materialFileTest() {
		assertTrue(new File(data + dataMaterial).exists());
	}
}
