package com.formation.epsi.gosecuri.factory;

import com.formation.epsi.gosecuri.model.Guard;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GuardsFactory {

    /* Data */
    static String DATA_DIR = "E:/www/epsi/mspr_tpre500/go-securi-data/";
    static String DATA_FILE = "staff.txt";

    public static List<Guard> create() throws IOException {
        /* Create a list of guards */
        List<Guard> guards = new ArrayList<>();

        /* Scanning staff txt file */
        String staffData = DATA_DIR+DATA_FILE;

        try (Scanner staffDataScan = new Scanner(new File(staffData))) {
            while (staffDataScan.hasNext()) {
                /* Initialize variable */
                String lastname = null;
                String firstname = null;
                String job = null;
                String password = null;

                /* The shortname of the guard is retrieved */
                String shortname = staffDataScan.nextLine().trim();

                /* A filename is generated */
                String guardData = DATA_DIR+shortname+".txt";
                File guardDataFile = new File(guardData);

                /* Scanning txt file of each guard */
                /* If the file exist */
                if(guardDataFile.exists()) {
                    /* Declaration of the number of lines counter */
                    int i = 0;

                    Scanner guardDataScan = new Scanner(guardDataFile);

                    while (guardDataScan.hasNext()) {
                        /* Counter increment */
                        i++;
                        /* the data is retrieved */
                        String data = guardDataScan.nextLine().trim();
                        if (i == 1) {
                            lastname = data;
                        } else if (i == 2) {
                            firstname = data;
                        } else if (i == 3) {
                            job = data;
                        } else if (i == 4) {
                            password = data;
                        }
                    }

                    /* Create a new guard */
                    Guard guard = GuardFactory.create(shortname, lastname, firstname, job, password);

                    guardDataScan.close();

                    /*  Adding the new guard to the list */
                    guards.add(guard);
                }
            }
        }
        /*  Return the list */
        return guards;
    }
}
