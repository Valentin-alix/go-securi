package com.formation.epsi.gosecuri.factory;

import com.formation.epsi.gosecuri.model.Guard;
import com.formation.epsi.gosecuri.model.Staff;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GuardsPageFactory {

    /* Templates */
    static String TEMPLATE_GUARD = "guard.ftl";

    public static void create(Configuration cfg, Staff staff) throws TemplateException, IOException {
        for (Guard guard : staff.getGuards()) {
            Map<String, Object> guardData = new HashMap<>();
            guardData.put("title", guard.getId());
            guardData.put("guard", guard);

            HtmlFactory.create(cfg, guardData, TEMPLATE_GUARD, String.format("%s.html", guard.getId()));
        }
    }
}
