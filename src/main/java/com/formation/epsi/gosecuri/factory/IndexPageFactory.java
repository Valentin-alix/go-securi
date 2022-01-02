package com.formation.epsi.gosecuri.factory;

import com.formation.epsi.gosecuri.model.Staff;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class IndexPageFactory {

    private static final ResourceBundle resource = ResourceBundle.getBundle("info");

    /**
     *
     * @param cfg Configuration
     * @param staff Staff
     * @return boolean
     */
    public static boolean create(Configuration cfg, Staff staff) throws TemplateException, IOException {
        // Get data from properties file
        String templateIndex = resource.getString("template.index");
        String targetIndex = resource.getString("target.index");

        Map<String, Object> indexData = new HashMap<>();
        indexData.put("title", "Liste des agents");
        indexData.put("guards", staff.getGuards());

        return HtmlFactory.create(cfg, indexData, templateIndex, targetIndex);
    }
}
