package com.github.noahstillwell;

public class Patient {
    //Instance Variables
    private PatientIdentity patientIdentity;

    //Constructors
    public Patient(PatientIdentity patientIdentity) {
        this.patientIdentity = patientIdentity;
    }

    //Getters
    public PatientIdentity getPatientIdentity() {
        return this.patientIdentity;
    }
}
