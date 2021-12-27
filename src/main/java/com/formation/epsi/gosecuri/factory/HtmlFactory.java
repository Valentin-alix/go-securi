package com.formation.epsi.gosecuri.factory;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class HtmlFactory {

    /* Data */
    static String DATA_DIR = "E:/www/epsi/mspr_tpre500/go-securi-data/";

    public static boolean create(Configuration cfg, Object templateData, String templateName, String targetFileName) throws IOException, TemplateException {
        /* Get the template (uses cache internally) */
        Template template = cfg.getTemplate(templateName);

        /* Merge data-model with template in a new file */
        try (Writer fileWriter = new FileWriter(DATA_DIR + targetFileName)) {
            template.process(templateData, fileWriter);
            return true;
        }
    }
}
