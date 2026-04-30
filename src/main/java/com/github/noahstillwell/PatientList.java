package com.github.noahstillwell;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PatientList {
    // Instance Variables
    private BinarySearchTree patientTree;
    private int patientCount;

    // Constructors
    public PatientList() {
        this.patientTree = new BinarySearchTree();
        this.patientCount = 0;
    }

    // Getters
    public int getPatientCount() {
        return this.patientCount;
    }

    // Methods
    public boolean addPatient(Patient patient) {
        boolean addedPatient = this.patientTree.add(patient);

        if (addedPatient) {
            this.patientCount++;
        }

        return addedPatient;
    }

    public Patient findPatient(PatientIdentity patientIdentity) {
        Patient patient = (Patient) this.patientTree.find(patientIdentity);
        return patient;
    }

    public void initializePatientIteration() {
        this.patientTree.initializeIteration();
    }

    public Patient nextPatient() {
        Patient patient = (Patient) this.patientTree.next();
        return patient;
    }

    public boolean savePatients(String filename) {
        File file = new File(filename);

        try (FileWriter fileWriter = new FileWriter(file)) {
            initializePatientIteration();
            Patient patient = null;

            while ((patient = nextPatient()) != null) {
                fileWriter.write(patient.toCSV() + "\n");
            }

            return true;
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return false;
        }
    }

}
