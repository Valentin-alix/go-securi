package com.formation.epsi.gosecuri.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Guard {
    private String id;
    private String firstname;
    private String lastname;
    private String job;
    private String password;
    private List<Equipment> equipments;
}
