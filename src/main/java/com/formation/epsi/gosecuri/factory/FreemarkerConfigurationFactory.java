package com.formation.epsi.gosecuri.factory;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

public class FreemarkerConfigurationFactory {

    /* Templates */
    static String TEMPLATES_PATH = "E:/www/epsi/mspr_tpre500/go-securi-appli/src/main/java/com/formation/epsi/gosecuri/templates";

    public static Configuration create() throws IOException {
        /* Create and adjust the configuration singleton */
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);
        cfg.setDirectoryForTemplateLoading(new File(TEMPLATES_PATH));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setLocale(Locale.FRANCE);
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);
        cfg.setFallbackOnNullLoopVariable(false);

        return cfg;
    }
}
