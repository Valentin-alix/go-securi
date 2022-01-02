package com.formation.epsi.gosecuri.factory;

import com.formation.epsi.gosecuri.model.Equipment;

public class EquipmentFactory {

    /**
     *
     * @param id Equipment’s id
     * @param name Equipment’s name
     * @return Equipment
     */
    public static Equipment create(String id, String name){
        Equipment equipment = new Equipment();
        equipment.setId(id);
        equipment.setName(name);

        return equipment;
    }
}
