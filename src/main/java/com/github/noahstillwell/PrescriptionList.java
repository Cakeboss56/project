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
        ListRecord new
    }

    public void init() {
        this.currentIterationRecord = this.head;
    }

    public Prescription next() {

    }
}
