package com.formation.epsi.gosecuri.factory;

import com.formation.epsi.gosecuri.model.Guard;
import com.formation.epsi.gosecuri.model.Staff;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ResourceBundle;

public class HtpasswdFactory {

    private static final ResourceBundle resource = ResourceBundle.getBundle("info");

    public static boolean create(Staff staff) {
        /* Get data from properties file */
        String targetDir = resource.getString("target.dir");

        /* Create a File */
        try {
            FileWriter myWriter = new FileWriter(targetDir+".htpasswd");
            for (Guard guard : staff.getGuards()) {
                String string = String.format("%s:%s\r\n", guard.getId(), guard.getPassword());
                myWriter.write(string);
            }
            myWriter.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}
