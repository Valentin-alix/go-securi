package com.formation.epsi.gosecuri.factory;

import com.formation.epsi.gosecuri.model.Guard;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class GuardPageFactory implements Runnable {

    Thread thread;
    private final String threadName;
    private final Guard guard;
    private final Configuration cfg;
    private static final ResourceBundle resource = ResourceBundle.getBundle("info");

    /**
     *
     * @param guard Guard
     */
    public GuardPageFactory(Configuration cfg, Guard guard) {
        this.cfg = cfg;
        this.guard = guard;
        this.threadName = guard.getId();
    }

    @Override
    public void run() {
        try {
            /* Get data from properties file */
            String templateGuard = resource.getString("template.guard");

            /* Create the guardData hash */
            Map<String, Object> guardData = new HashMap<>();
            /* Put string "title" into the guardData */
            guardData.put("title", guard.getId());
            /* Put guard into the guardData */
            guardData.put("guard", guard);

            /* Create html page guard */
            HtmlFactory.create(cfg, guardData, templateGuard, String.format("%s.html", guard.getId()));

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
