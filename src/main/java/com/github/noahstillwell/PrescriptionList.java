package com.github.noahstillwell;

public class PrescriptionList {
    // Nested Classes
    private class ListRecord {
        public Prescription data;
        public ListRecord next;

        public ListRecord(Prescription prescription) {
            this.data = prescription;
            this.next = null;
        }
    }

    // Instance Variables
    private ListRecord head;
    private ListRecord currentIterationRecord;

    // Constructors
    public PrescriptionList() {
        this.head = null;
        this.currentIterationRecord = null;
    }

    // Methods
    public void add(Prescription prescription) {
        if (prescription == null) {
            return;
        }

        ListRecord newRecord = new ListRecord(prescription);

        if (this.head == null || comesBefore(prescription, this.head.data)) {
            newRecord.next = this.head;
            this.head = newRecord;
            return;
        }

        ListRecord previousRecord = this.head;

        while (previousRecord.next != null && comesBefore(previousRecord.next.data, prescription)) {
            previousRecord = previousRecord.next;
        }

        newRecord.next = previousRecord.next;
        previousRecord.next = newRecord;

    }

    public void initializeIteration() {
        this.currentIterationRecord = this.head;
    }

    public Prescription next() {
        if (this.currentIterationRecord == null) {
            return null;
        }
        
        Prescription prescription = this.currentIterationRecord.data;
        this.currentIterationRecord = this.currentIterationRecord.next;
        return prescription;
    }

    // Hellper Methods
    private static boolean comesBefore(Prescription prescription1, Prescription prescription2) {
        return prescription1.getDateOfIssue().compareTo(prescription2.getDateOfIssue()) > 0;
    }
}
