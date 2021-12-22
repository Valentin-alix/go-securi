package com.formation.epsi.gosecuri.model;

import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

@Getter
@Setter
public class Staff {
    List<Guard> guards = new ArrayList<>();

    public Staff(String dataDir, String dataFile) throws FileNotFoundException {
        try (Scanner scan = new Scanner(new File(dataDir+dataFile))) {
            scan.useLocale(Locale.FRANCE);
            while (scan.hasNext()) {
                Guard guard = new Guard();
                String line = scan.nextLine().trim();
                guard.setId(line);
                this.guards.add(guard);
            }
        }
    }
}
