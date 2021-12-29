package com.formation.epsi.gosecuri.factory;

import com.formation.epsi.gosecuri.model.Guard;
import com.formation.epsi.gosecuri.model.Staff;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class GuardsPageFactory {

    private static final ResourceBundle resource = ResourceBundle.getBundle("info");

    public static void create(Configuration cfg, Staff staff) throws TemplateException, IOException {
        // Get data from properties file
        String templateGuard = resource.getString("template.guard");

        for (Guard guard : staff.getGuards()) {
            Map<String, Object> guardData = new HashMap<>();
            guardData.put("title", guard.getId());
            guardData.put("guard", guard);

            HtmlFactory.create(cfg, guardData, templateGuard, String.format("%s.html", guard.getId()));
        }
    }
}
