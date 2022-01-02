package com.formation.epsi.gosecuri.factory;

import com.formation.epsi.gosecuri.model.Equipment;

public class EquipmentFactory {

    /**
     *
     * @param id Id  of the equipment
     * @param name Name of the equipment
     * @return Equipment
     */
    public static Equipment create(String id, String name){
        Equipment equipment = new Equipment();
        equipment.setId(id);
        equipment.setName(name);

        return equipment;
    }
}
