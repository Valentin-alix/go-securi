package com.formation.epsi.gosecuri;

import com.formation.epsi.gosecuri.factory.*;
import com.formation.epsi.gosecuri.model.Equipment;
import com.formation.epsi.gosecuri.model.Guard;

import java.io.*;
import java.util.*;

import freemarker.template.*;

public class Main {

	public static void main(String[] args) throws IOException {
		/* Create the equipments */
		HashMap<String, Equipment> equipments = EquipmentsFactory.create();

		/* Create the guards */
		List<Guard> guards = GuardsFactory.create(equipments);

		/* Create freemarker configuration */
		Configuration cfg =  FreemarkerConfigurationFactory.create();

		/* Create the homepage */
		IndexPageFactory indexPage = new IndexPageFactory(cfg, guards);
		indexPage.start();

		/* Create a page for each guard */
		for (Guard guard : guards) {
			GuardPageFactory guardPage = new GuardPageFactory(cfg, guard);
			guardPage.start();
		}

		/* Create the htpassword file */
		HtpasswdFactory htpasswd = new HtpasswdFactory(guards);
		htpasswd.start();
	}

}
