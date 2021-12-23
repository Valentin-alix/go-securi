package com.formation.epsi.gosecuri.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Staff {
    List<Guard> guards = new ArrayList<>();
}
