package com.formation.epsi.gosecuri.util;

import com.formation.epsi.gosecuri.model.Guard;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.*;

public class CopyCardsId implements Runnable {

    Thread thread;
    private final String threadName;
    private final List<Guard> guards;
    private final ResourceBundle resource = ResourceBundle.getBundle("info");

    /**
     *
     * @param guards List of guards
     */
    public CopyCardsId(List<Guard> guards) {
        this.guards = guards;
        this.threadName = "copy cards id";
    }

    @Override
    public void run() {
        try {
            // Get data from properties file
            String targetDir = resource.getString("target.dir");
            String dataDir = resource.getString("data.dir");

            /* Initialize variable */
            String folderName = "images";

            /* Create image folder */
            createFolder(targetDir, folderName);

            /* Create list of files */
            List<File> files = new ArrayList<>();
            for (Guard guard : guards) {
                /* A filename is generated */
                String pathname = dataDir+guard.getId()+".jpg";
                /* Create new file */
                File file = new File(pathname);
                /* Add file in the list */
                files.add(file);
            }

            /* Copy files in image folder */
            for(File file : files) {
                String pathname = targetDir + folderName + "/" + file.getName();
                File target = new File(pathname);
                Files.copy(file.toPath(), target.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }
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

    private static boolean createFolder(String targetDir, String folderName){
        String directoryPathname = targetDir + folderName;
        File directory = new File(directoryPathname);
        if (! directory.exists()){
            return directory.mkdirs();
        }

        return false;
    }
}
