package com.formation.epsi.gosecuri.factory;

import com.formation.epsi.gosecuri.model.Guard;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class ServiceWorkerFactory implements Runnable {

    Thread thread;
    private final String threadName;
    private final Configuration cfg;
    private final List<Guard> guards;
    private static final ResourceBundle resource = ResourceBundle.getBundle("info");

    /**
     * @param cfg    Configuration
     * @param guards List of guards
     */
    public ServiceWorkerFactory(Configuration cfg, List<Guard> guards) {
        this.cfg = cfg;
        this.guards = guards;
        this.threadName = "sw";
    }

    @Override
    public void run() {
        try {
            /* Get data from properties file */
            String templateSw = resource.getString("template.sw");
            String targetSw = resource.getString("target.sw");

            /* Create the indexData hash */
            Map<String, Object> indexData = new HashMap<>();
            /* Put guards into the indexData */
            indexData.put("guards", guards);

            /* Create service worker */
            FileFactory.create(cfg, indexData, templateSw, targetSw);
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
