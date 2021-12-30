package com.formation.epsi.gosecuri;

import com.formation.epsi.gosecuri.factory.*;
import com.formation.epsi.gosecuri.model.Equipment;
import com.formation.epsi.gosecuri.model.Guard;
import com.formation.epsi.gosecuri.model.Material;
import com.formation.epsi.gosecuri.model.Staff;

import java.io.*;
import java.util.*;

import freemarker.template.*;

public class Main {

	public static void main(String[] args) throws IOException, TemplateException {
		/* Create the equipments */
		List<Equipment> equipments = EquipmentsFactory.create();

		/* Create the material */
		Material material = MaterialFactory.create(equipments);

		/* Create the guards */
		List<Guard> guards = GuardsFactory.create(material);

		/* Create the staff */
		Staff staff = StaffFactory.create(guards);

		/* Create freemarker configuration */
		Configuration cfg =  FreemarkerConfigurationFactory.create();

		/* Create the homepage */
		IndexPageFactory.create(cfg, staff);

		/* Create a page for each guard */
		GuardsPageFactory.create(cfg, staff);
	}
}
