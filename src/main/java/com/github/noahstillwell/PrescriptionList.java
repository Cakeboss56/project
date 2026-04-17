package com.github.noahstillwell;

public class PrescriptionList {
    // Nested Classes
    private class PrescriptionRecord {
        private Prescription prescription;
        private PrescriptionRecord nextPrescriptionRecord;

        private PrescriptionRecord(Prescription prescription) {
            this.prescription = prescription;
            this.nextPrescriptionRecord = null;
        }
    }

    // Instance Variables
    private PrescriptionRecord headPrescriptionRecord;
    private PrescriptionRecord currentPrescriptionRecord;

    // Constructors
    public PrescriptionList() {
        this.headPrescriptionRecord = null;
        this.currentPrescriptionRecord = null;
    }

    // Methods
    public void addPrescription(Prescription prescription) {
        if (prescription == null) {
            return;
        }

        PrescriptionRecord newPrescriptionRecord = new PrescriptionRecord(prescription);

        if (this.headPrescriptionRecord == null || comesBefore(prescription, this.headPrescriptionRecord.prescription)) {
            newPrescriptionRecord.nextPrescriptionRecord = this.headPrescriptionRecord;
            this.headPrescriptionRecord = newPrescriptionRecord;
            return;
        }

        PrescriptionRecord previousPrescriptionRecord = this.headPrescriptionRecord;

        while (previousPrescriptionRecord.nextPrescriptionRecord != null && comesBefore(previousPrescriptionRecord.nextPrescriptionRecord.prescription, prescription)) {
            previousPrescriptionRecord = previousPrescriptionRecord.nextPrescriptionRecord;
        }

        newPrescriptionRecord.nextPrescriptionRecord = previousPrescriptionRecord.nextPrescriptionRecord;
        previousPrescriptionRecord.nextPrescriptionRecord = newPrescriptionRecord;

    }

    public void initializeIteration() {
        this.currentPrescriptionRecord = this.headPrescriptionRecord;
    }

    public Prescription next() {
        if (this.currentPrescriptionRecord == null) {
            return null;
        }
        
        Prescription prescription = this.currentPrescriptionRecord.prescription;
        this.currentPrescriptionRecord = this.currentPrescriptionRecord.nextPrescriptionRecord;
        return prescription;
    }

    // Helper Methods
    private static boolean comesBefore(Prescription prescription1, Prescription prescription2) {
        return prescription1.getDateOfIssue().compareTo(prescription2.getDateOfIssue()) > 0;
    }
}
