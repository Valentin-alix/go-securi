package com.formation.epsi.gosecuri;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import com.formation.epsi.gosecuri.factory.*;
import com.formation.epsi.gosecuri.model.Equipment;
import com.formation.epsi.gosecuri.model.Guard;
import com.formation.epsi.gosecuri.util.CopyCardsId;

import freemarker.template.Configuration;

public class Main {

	public static void main(String[] args) throws IOException {
		/* Create the equipments */
		HashMap<String, Equipment> equipments = EquipmentsFactory.create();
		/* Create the guards */
		List<Guard> guards = GuardsFactory.create(equipments);

		/* Create freemarker configuration */
		Configuration cfg = FreemarkerConfigurationFactory.create();

		/* Create the homepage */
		IndexPageFactory indexPage = new IndexPageFactory(cfg, guards);
		indexPage.start();

		/* Create a page for each guard */
		for (Guard guard : guards) {
			GuardPageFactory guardPage = new GuardPageFactory(cfg, guard);
			guardPage.start();
		}

		/* Copy cards id */
		CopyCardsId copyCardsId = new CopyCardsId(guards);
		copyCardsId.start();

		/* Create the htpassword file */
		HtpasswdFactory htpasswd = new HtpasswdFactory(guards);
		htpasswd.start();

		/* Create the service worker file */
		ServiceWorkerFactory serviceWorkerFactory = new ServiceWorkerFactory(cfg, guards);
		serviceWorkerFactory.start();
	}
}
