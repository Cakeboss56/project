package com.github.noahstillwell;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

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
    public boolean addContraindications(Contraindications newContraindications) {
        if (newContraindications ==  null || newContraindications.getDrugName().isEmpty()) {
            return false;
        }

        int index = buildHashCode(newContraindications.getDrugName());
        int startIndex = index;

        do {
            if (this.table[index] == null) {
                this.table[index] = newContraindications;
                return true;
            }

            if (this.table[index].matchesDrug(newContraindications.getDrugName())) {
                this.table[index].addContraindicatedDrugs(newContraindications.getContraindicatedDrugs());
                return true;
            }

            index = nextIndex(index);

        } while (index != startIndex);

        return false;
    }

    public Contraindications findContraindications(String drugName) {
        if (drugName.isEmpty()) {
            return null;
        }

        int index = buildHashCode(drugName);
        int startIndex = index;

        do {
            if (this.table[index] == null) {
                return null;
            }

            if (this.table[index].matchesDrug(drugName)) {
                return this.table[index];
            }

            index = nextIndex(index);
            
        } while (index != startIndex);

        return null;
    }

    public boolean importContraindications(String filename) {
        File file = new File(filename);

        try (Scanner scanner = new Scanner(file)) {
            scanner.useDelimiter(",\\s*");

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Contraindications[] contraindications = Contraindications.makeContraindications(line);

                if (contraindications != null) {
                    addContraindications(contraindications[0]);
                    addContraindications(contraindications[1]);
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
            return false;
        }

        return true;
    }

    // Helper Methods
    private int buildHashCode(String drugName) {
        drugName = drugName.trim().toLowerCase();

        if (drugName.isEmpty()) {
            return 0;
        }

        int length = drugName.length();
        int[] indices = {0, length /3, (2 * length) / 3, length - 1};
        long hashCode = 0;

        for (int index = 0; index < indices.length; index++) {
            int characterIndex = indices[index];

            if (characterIndex < 0) {
                characterIndex = 0;
            }

            if (characterIndex >= length) {
                characterIndex = length - 1;
            }

            long characterValue = drugName.charAt(characterIndex) & 0xFFL;
            hashCode = hashCode | (characterValue << (8 * index));
        }

        return (int) (hashCode % TABLE_SIZE);
    }

    private int nextIndex(int index) {
        index++;

        if (index >= TABLE_SIZE) {
            index = 0;
        }

        return index;
    }
}
