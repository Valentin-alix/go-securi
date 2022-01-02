package com.formation.epsi.gosecuri.factory;

import com.formation.epsi.gosecuri.model.Equipment;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class EquipmentsFactory {

    private static final ResourceBundle resource = ResourceBundle.getBundle("info");

    /**
     *
     * @return HashMap of equipments
     */
    public static HashMap<String, Equipment> create() throws IOException {
        // Get data from properties file
        String dataDir = resource.getString("data.dir");
        String dataFileMaterial = resource.getString("data.file.material");

        /* Create a hashmap of equipments */
        HashMap<String, Equipment> equipments = new HashMap<>();

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

                /*  Adding the new equipment to the hashmap */
                equipments.put(id, equipment);
            }
        }

        return  equipments;
    }
}
