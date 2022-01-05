package com.formation.epsi.gosecuri.factory;

import com.formation.epsi.gosecuri.model.Staff;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class IndexPageFactory implements Runnable {

    Thread thread;
    private final String name;
    private final Configuration cfg;
    private final Staff staff;
    private final ResourceBundle resource = ResourceBundle.getBundle("info");

    /**
     *
     * @param cfg Configuration
     * @param staff Staff
     */
    public IndexPageFactory(Configuration cfg, Staff staff) {
        this.cfg = cfg;
        this.staff = staff;
        this.name = "index";
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
            indexData.put("title", "Liste des agents");
            /* Put guards into the indexData */
            indexData.put("guards", staff.getGuards());

            /* Create index page */
            HtmlFactory.create(cfg, indexData, templateIndex, targetIndex);
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        if (thread == null) {
            thread = new Thread(this, name);
            thread.start();
        }
    }
}
