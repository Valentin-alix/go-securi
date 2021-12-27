package com.formation.epsi.gosecuri.factory;

import com.formation.epsi.gosecuri.model.Guard;
import com.formation.epsi.gosecuri.model.Staff;

import java.util.List;

public class StaffFactory {

    /**
     *
     * @param guards List of guards
     *
     * @return Staff
     */
    public static Staff create(List<Guard> guards){
        Staff staff = new Staff();
        staff.setGuards(guards);

        return staff;
    }
}
