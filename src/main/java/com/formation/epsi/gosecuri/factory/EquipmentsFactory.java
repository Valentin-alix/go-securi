package com.formation.epsi.gosecuri.factory;

import com.formation.epsi.gosecuri.model.Equipment;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class EquipmentsFactory {

    private static final ResourceBundle resource = ResourceBundle.getBundle("info");

    public static List<Equipment> create() throws IOException {
        // Get data from properties file
        String dataDir = resource.getString("data.dir");
        String dataFileMaterial = resource.getString("data.file.material");

        /* Create a list of equipments */
        List<Equipment> equipments = new ArrayList<>();

        /* Scanning liste txt file */
        String materialData = dataDir+dataFileMaterial;

        try (Scanner materialDataScan = new Scanner(new File(materialData))) {
            while (materialDataScan.hasNext()) {
                /* Split the string data  */
                String originalData = materialDataScan.nextLine().trim();
                String[] splitData = originalData.split("\\s", 2);
                String id = splitData[0];
                String name = splitData[1];

                /* Create a new equipment */
                Equipment equipment = EquipmentFactory.create(id, name);

                /*  Adding the new equipment to the list */
                equipments.add(equipment);
            }
        }

        return  equipments;
    }
}
