package com.github.noahstillwell;

public class PrescriptionList {
    // Nested Classes
    private class Record {
        private Prescription prescription;
        private Record nextRecord;

        private Record(Prescription prescription) {
            this.prescription = prescription;
            this.nextRecord = null;
        }
    }

    // Instance Variables
    private Record headRecord;
    private Record currentRecord;

    // Constructors
    public PrescriptionList() {
        this.headRecord = null;
        this.currentRecord = null;
    }

    // Methods
    public void addPrescription(Prescription prescription) {
        if (prescription == null) {
            return;
        }

        Record newRecord = new Record(prescription);

        if (this.headRecord == null || comesBefore(prescription, this.headRecord.prescription)) {
            newRecord.nextRecord = this.headRecord;
            this.headRecord = newRecord;
            return;
        }

        Record previousRecord = this.headRecord;

        while (previousRecord.nextRecord != null && comesBefore(previousRecord.nextRecord.prescription, prescription)) {
            previousRecord = previousRecord.nextRecord;
        }

        newRecord.nextRecord = previousRecord.nextRecord;
        previousRecord.nextRecord = newRecord;

    }

    public void initializeIteration() {
        this.currentRecord = this.headRecord;
    }

    public Prescription nextPrescription() {
        if (this.currentRecord == null) {
            return null;
        }
        
        Prescription prescription = this.currentRecord.prescription;
        this.currentRecord = this.currentRecord.nextRecord;
        return prescription;
    }

    // Helper Methods
    private static boolean comesBefore(Prescription prescription1, Prescription prescription2) {
        return prescription1.getDateOfIssue().compareTo(prescription2.getDateOfIssue()) > 0;
    }
}
