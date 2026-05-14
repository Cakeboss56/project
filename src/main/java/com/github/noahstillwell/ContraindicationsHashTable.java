package com.github.noahstillwell;

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
