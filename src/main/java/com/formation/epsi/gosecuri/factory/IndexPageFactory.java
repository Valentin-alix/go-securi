package com.formation.epsi.gosecuri.factory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.formation.epsi.gosecuri.model.Guard;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;

public class IndexPageFactory implements Runnable {

	Thread thread;
	private final String threadName;
	private final Configuration cfg;
	private final List<Guard> guards;
	private final ResourceBundle resource = ResourceBundle.getBundle("info");

	/**
	 *
	 * @param cfg    Configuration
	 * @param guards List of guards
	 */
	public IndexPageFactory(Configuration cfg, List<Guard> guards) {
		this.cfg = cfg;
		this.guards = guards;
		this.threadName = "index";
	}

	@Override
	public void run() {
		try {
			/* Get data from properties file */
			String templateIndex = resource.getString("template.index");
			String targetIndex = resource.getString("target.index");

			/* Create the indexData hash */
			Map<String, Object> indexData = new HashMap<>();
			/* Put string "title" into the indexData */
			indexData.put("title", "Accueil");
			/* Put guards into the indexData */
			indexData.put("guards", guards);

			/* Create index page */
			HtmlFactory.create(cfg, indexData, templateIndex, targetIndex);
		} catch (IOException | TemplateException e) {
			e.printStackTrace();
		}
	}

	public void start() {
		if (thread == null) {
			thread = new Thread(this, threadName);
			thread.start();

		}
	}
}
