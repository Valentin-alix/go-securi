package com.formation.epsi.gosecuri.factory;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ResourceBundle;

public class HtmlFactory {

    private static final ResourceBundle resource = ResourceBundle.getBundle("info");

    public static boolean create(Configuration cfg, Object templateData, String templateName, String targetFileName) throws IOException, TemplateException {
        // Get data from properties file
        String targetDir = resource.getString("target.dir");

        /* Get the template (uses cache internally) */
        Template template = cfg.getTemplate(templateName);

        /* Merge data-model with template in a new file */
        try (Writer fileWriter = new FileWriter(targetDir + targetFileName)) {
            template.process(templateData, fileWriter);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
