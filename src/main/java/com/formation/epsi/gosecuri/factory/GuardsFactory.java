package com.formation.epsi.gosecuri.factory;

import com.formation.epsi.gosecuri.model.Equipment;
import com.formation.epsi.gosecuri.model.Guard;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class GuardsFactory {

    private static final ResourceBundle resource = ResourceBundle.getBundle("info");

    public static List<Guard> create(HashMap<String, Equipment> equipments) throws IOException {
        // Get data from properties file
        String dataDir = resource.getString("data.dir");
        String dataFileStaff = resource.getString("data.file.staff");

        /* Create a list of guards */
        List<Guard> guards = new ArrayList<>();

        /* Scanning staff txt file */
        String staffData = dataDir+dataFileStaff;

        try (Scanner staffDataScan = new Scanner(new File(staffData))) {
            while (staffDataScan.hasNext()) {
                /* Initialize variable */
                String guardLastname = null;
                String guardFirstname = null;
                String guardJob = null;
                String guardPassword = null;
                List<Equipment> guardEquipments = new ArrayList<>();

                /* The shortname of the guard is retrieved */
                String guardShortname = staffDataScan.nextLine().trim();

                /* A filename is generated */
                String guardData = dataDir+guardShortname+".txt";
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
                            guardLastname = data;
                        } else if (i == 2) {
                            guardFirstname = data;
                        } else if (i == 3) {
                            guardJob = data;
                        } else if (i == 4) {
                            guardPassword = data;
                        } else if (i >= 6) {
                            try {
                                Equipment equipment = equipments.get(data);
                                guardEquipments.add(equipment);
                            } catch (Exception ignored) {
                            }
                        }
                    }

                    /* Create a new guard */
                    Guard guard = GuardFactory.create(guardShortname, guardLastname, guardFirstname, guardJob, guardPassword, guardEquipments);

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
