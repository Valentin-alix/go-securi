package com.formation.epsi.gosecuri;

import com.formation.epsi.gosecuri.factory.*;
import com.formation.epsi.gosecuri.model.Equipment;
import com.formation.epsi.gosecuri.model.Guard;
import com.formation.epsi.gosecuri.model.Staff;

import java.io.*;
import java.util.*;

import freemarker.template.*;

public class Main {

	public static void main(String[] args) throws IOException, TemplateException {
		/* Create the equipments */
		HashMap<String, Equipment> equipments = EquipmentsFactory.create();

		/* Create the guards */
		List<Guard> guards = GuardsFactory.create(equipments);

		/* Create the staff */
		Staff staff = StaffFactory.create(guards);

		/* Create freemarker configuration */
		Configuration cfg =  FreemarkerConfigurationFactory.create();

		/* Create the homepage */
		IndexPageFactory indexPage = new IndexPageFactory(cfg, staff);
		indexPage.start();

		/* Create a page for each guard */
		GuardsPageFactory.create(cfg, staff);

		/* Create the htpassword file */
		HtpasswdFactory.create(staff);
	}

}
