package com.formation.epsi.gosecuri;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.formation.epsi.gosecuri.factory.EquipmentsFactory;
import com.formation.epsi.gosecuri.factory.GuardsFactory;
import com.formation.epsi.gosecuri.model.Equipment;
import com.formation.epsi.gosecuri.model.Guard;

import freemarker.template.TemplateException;

class MainTests {

	@Test
	public void createEquipments() throws IOException {
		/* test return value of EquipmentsFactory */
		HashMap<String, Equipment> equipments = EquipmentsFactory.create();
		assertNotNull(equipments);
		assertNotNull(equipments.get("lampe"));
		assertNotNull(equipments.get("cyno"));
		assertNotNull(equipments.get("lampe").getId());
		assertNotNull(equipments.get("lampe").getName());
		assertEquals(equipments.get("lampe").getId(), "lampe");
		assertEquals(equipments.get("cyno").getId(), "cyno");
		assertEquals(equipments.get("lampe").getName(), "Lampe Torche");
		assertEquals(equipments.get("cyno").getName(), "Bandeau agent cynophile");

	}

	@Test
	public void createGuards() throws IOException {
		/* test return value of GuardsFactory */
		HashMap<String, Equipment> equipments = EquipmentsFactory.create();
		List<Guard> guards = GuardsFactory.create(equipments);
		assertNotNull(guards);
		assertNotNull(guards.get(0));
		assertNotNull(guards.get(0).getFirstname());
		assertNotNull(guards.get(0).getLastname());
		assertNotNull(guards.get(0).getJob());
		assertNotNull(guards.get(0).getPassword());
		assertEquals(guards.get(0).getFirstname(), "Corinne");
		assertEquals(guards.get(0).getLastname(), "Berthier");
		assertEquals(guards.get(0).getJob(), "Surveillance entrepôt");
		assertEquals(guards.get(0).getPassword(), "pmNd1ldFE7WTk");

	}

	@Test
	public void modifHTML() throws IOException, TemplateException {

		/* test les modifs sur l'html ? */

	}
}
