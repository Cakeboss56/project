package com.github.noahstillwell;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Contraindications {
    // Instance Variables
    private String drugName;
    private DrugList contraindicatedDrugs;

    // Constructors
    public Contraindications(String drugName) {
        this.drugName = drugName;
        this.contraindicatedDrugs = new DrugList();
    }

    // Getters
    public String getDrugName() {
        return this.drugName;
    }

    public DrugList getContraindicatedDrugs() {
        return this.contraindicatedDrugs;
    }

    // Methods
    public boolean addContraindicatedDrug(String drugName) {
        return this.contraindicatedDrugs.addDrug(drugName);
    }

    public void addContraindicatedDrugs(DrugList drugList) {
        if (drugList == null) {
            return;
        }

        drugList.initializeIteration();

        String drugName = null;

        while ((drugName = drugList.nextDrug()) != null) {
            this.contraindicatedDrugs.addDrug(drugName);
        }
    }

    public boolean matchesDrug(String drugName) {
        if (drugName == null) {
            return false;
        }

        return this.drugName.trim().equalsIgnoreCase(drugName.trim());
    }

    public static Contraindications makeContraindication(String drugName, String contraindicatedDrugName) {
        drugName = drugName.trim();
        contraindicatedDrugName = contraindicatedDrugName.trim();

        if (drugName.isEmpty() || contraindicatedDrugName.isEmpty()) {
            return null;
        }

        Contraindications contraindications = new Contraindications(drugName);
        contraindications.addContraindicatedDrug(contraindicatedDrugName);
        return contraindications;
    }

    public static Contraindications[] makeContraindications(String line) {
        try (Scanner scanner = new Scanner(line)) {
            scanner.useDelimiter(",\\s*");

            String drugName1 = scanner.next();
            String drugName2 = scanner.next();

            Contraindications[] pair = new Contraindications[2];

            pair[0] = makeContraindication(drugName1, drugName2);
            pair[1] = makeContraindication(drugName2, drugName1);

            if (pair[0] == null || pair[1] == null) {
                return null;
            }

            return pair;
        } catch (NoSuchElementException exception) {
            exception.printStackTrace();
            return null;
        }
    }
}
