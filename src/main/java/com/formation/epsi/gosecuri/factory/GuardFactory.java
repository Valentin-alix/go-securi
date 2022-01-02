package com.formation.epsi.gosecuri.factory;

import com.formation.epsi.gosecuri.model.Equipment;
import com.formation.epsi.gosecuri.model.Guard;

import java.util.ArrayList;
import java.util.List;

public class GuardFactory {

    /**
     *
     * @param shortname Guard’s shortname
     * @param lastname Guard’s lastname
     * @param firstname Guard’s firstname
     * @param job Guard’s job
     * @param password Guard’s password
     * @return Guard
     */
    public static Guard create(String shortname, String lastname, String firstname, String job, String password, List<Equipment> equipments) {
        Guard guard = new Guard();
        guard.setId(shortname);
        guard.setLastname(lastname);
        guard.setFirstname(firstname);
        guard.setJob(job);
        guard.setPassword(password);
        guard.setEquipments(equipments);

        return guard;
    }
}
