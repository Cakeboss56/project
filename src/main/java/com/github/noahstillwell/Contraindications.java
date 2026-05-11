package com.github.noahstillwell;

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
}
