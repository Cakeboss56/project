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

    // Helper Methods
    private long hashCode(String drugName) {
        drugName = drugName.trim().toLowerCase();

        if (drugName.isEmpty()) {
            return 0;
        }

        int length = drugName.length();
        int[] indices = {0, length /3, (2 * length) / 3, length - 1};
        long hashCode = 0;

        for (int index = 0; index < indices.length; index++) {
            if (indices[index] < 0) {
                index = 0;
            } else if (indices[index] >= length) {
                index = length - 1;
            } else {
                index = indices[index];
            }

            long characterValue = drugName.charAt(index) & 0xFFL;
            hashCode = hashCode | (characterValue << (8 * index));
        }

        return hashCode;
    }
}
