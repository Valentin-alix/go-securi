package com.formation.epsi.gosecuri.factory;

import com.formation.epsi.gosecuri.model.Guard;

import java.io.FileWriter;
import java.io.IOException;

import java.util.List;
import java.util.ResourceBundle;

public class HtpasswdFactory implements Runnable {

    Thread thread;
    private final String threadName;
    private final List<Guard> guards;
    private static final ResourceBundle resource = ResourceBundle.getBundle("info");

    /**
     *
     * @param guards List of guards
     */
    public HtpasswdFactory(List<Guard> guards) {
        this.guards = guards;
        this.threadName = "htpasswd";
    }

    @Override
    public void run() {
        try {
            /* Get data from properties file */
            String targetDir = resource.getString("target.dir");
            
            /* Create a htpasswd file */
            FileWriter myWriter = new FileWriter(targetDir + ".htpasswd");

            /* Add guards in htpasswd file */
            for (Guard guard : guards) {
                String string = String.format("%s:%s\r\n", guard.getId(), guard.getPassword());
                myWriter.write(string);
            }

            myWriter.close();
        } catch (IOException e) {
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
