package com.github.noahstillwell;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class ContraindicationsHashTable {
    // Instance Variables
    private Contraindications[] table;

    // Constants
    private static final int TABLE_SIZE = 5009;

    // Constructors
    public ContraindicationsHashTable() {
        this.table = new Contraindications[TABLE_SIZE];
    }

    // Methods
    public boolean addInteraction(String drugName1, String drugName2) {
        drugName1 = drugName1.trim();
        drugName2 = drugName2.trim();

        if (drugName1.isEmpty() || drugName2.isEmpty()) {
            return false;
        }


    }

    public Contraindications findContraindications(String drugName) {
        drugName = drugName.trim();

        if (drugName.isEmpty()) {
            return null;
        }

        int index = hash
    }
}
