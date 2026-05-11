package com.github.noahstillwell;

public class DrugList {
    // Nested Classes
    private class Record {
        private String drugName;
        private Record nextRecord;

        private Record(String drugName) {
            this.drugName = drugName;
            this.nextRecord = null;
        }
    }

    // Instance Variables
    private Record headRecord;
    private Record currentRecord;
    private int drugCount;

    // Constructors
    public DrugList() {
        this.headRecord = null;
        this.currentRecord = null;
        this.drugCount = 0;
    }

    // Getters
    public Record getHeadRecord() {
        return headRecord;
    }

    public Record getCurrentRecord() {
        return currentRecord;
    }

    public int getDrugCount() {
        return drugCount;
    }

    // Methods
    public boolean addDrug(String drugName) {
        drugName = drugName.trim();

        if (drugName.isEmpty()) {
            return false;
        }

        if (findDrug(drugName) != null) {
            return false;
        }

        Record newRecord = new Record(drugName);
        newRecord.nextRecord = this.headRecord;
        this.headRecord = newRecord;
        this.drugCount++;

        return true;
    }

    public String findDrug(String drugName) {
        drugName = drugName.trim();

        if (drugName.isEmpty()) {
            return null;
        }

        Record record = this.headRecord;

        while (record != null) {
            if (record.drugName.trim().equalsIgnoreCase(drugName)) {
                return record.drugName;
            }

            record = record.nextRecord;
        }

        return null;
    }

    public void initializeIteration() {
        this.currentRecord = this.headRecord;
    }

    public String nextDrug() {
        if (this.currentRecord == null) {
            return null;
        }

        String drugName = this.currentRecord.drugName;
        this.currentRecord = this.currentRecord.nextRecord;

        return drugName;
    }
}
