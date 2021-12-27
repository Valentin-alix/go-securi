package com.formation.epsi.gosecuri.factory;

import com.formation.epsi.gosecuri.model.Staff;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class IndexPageFactory {

    /* Templates */
    static String TEMPLATE_INDEX = "index.ftl";

    /* Target */
    static String TARGET_INDEX = "index.html";

    /**
     *
     * @param cfg Configuration
     * @param staff Staff
     * @return boolean
     */
    public static boolean create(Configuration cfg, Staff staff) throws TemplateException, IOException {
        Map<String, Object> indexData = new HashMap<>();
        indexData.put("title", "Liste des agents");
        indexData.put("guards", staff.getGuards());

        HtmlFactory.create(cfg, indexData, TEMPLATE_INDEX, TARGET_INDEX);

        return true;
    }
}
