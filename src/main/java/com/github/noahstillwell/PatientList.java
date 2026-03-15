package com.github.noahstillwell;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class PatientList {
    //Instance Variables
    private Patient[] patientArray;
    private int maximumPatients;
    private int numberOfPatients;
    private int indexOfIteration;
    
    //Constructors
    public PatientList(int maximumPatients) {
        this.maximumPatients = maximumPatients;
        this.patientArray = new Patient[maximumPatients];
        this.numberOfPatients = 0;
        this.indexOfIteration = -1;
    }

    //Getters
    public Patient getPatientArray(int index) {
        return this.patientArray[index];
    }

    public int getMaximumPatients() {
        return this.maximumPatients;
    }

    public int getNumberOfPatients() {
        return this.numberOfPatients;
    }

    public int getIndexOfIteration() {
        return this.indexOfIteration;
    }

    //Methods
    public boolean addPatient(Patient patient) {
        if (patient == null || this.numberOfPatients >= this.patientArray.length) {
            return false;
        }

        return insertPatient(patient);
    }

    public Patient findPatient(PatientIdentity patientIdentity) {
        Patient patient = binarySearch(patientIdentity);

        if (patient == null) {
            return null;
        }

        return patient;
    }

    public void initializeIteration() {
        if (this.numberOfPatients == 0) {
            this.indexOfIteration = -1;
        } else {
            this.indexOfIteration = 0;
        }
    }

    public Patient nextPatient() {
        if (this.indexOfIteration == -1) {
            return null;
        }

        Patient patient = this.patientArray[this.indexOfIteration];
        this.indexOfIteration++;

        if (patient == null) {
            this.indexOfIteration = -1;
        }

        return patient;
    }

    public boolean saveToFile(String filename) {
        File file = new File(filename);

        try (FileWriter fileWriter = new FileWriter(file)) {
            initializeIteration();
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

    public boolean loadFromFile(String filename) {
        File file = new File(filename);
        
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Patient patient = Patient.makePatient(line);
                
                if (!(patient == null)) {
                    this.patientArray[this.numberOfPatients] = patient;
                    this.numberOfPatients++;
                }

                if (this.numberOfPatients >= this.patientArray.length) {
                    return false;
                }
            }
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
            return false;
        }

        return true;
    }

    //Helper Methods
    private boolean insertPatient(Patient patient) {
        PatientIdentity patientIdentity = patient.getPatientIdentity();
        int index = this.numberOfPatients - 1;

        while (index >= 0) {
            PatientIdentity otherPatientIdentity = this.patientArray[index].getPatientIdentity();

            if (patientIdentity.isLessThan(otherPatientIdentity)) {
                this.patientArray[index + 1] = this.patientArray[index];
                index--;
            } else {
                break;
            }
        }

        this.patientArray[index + 1] = patient;
        this.numberOfPatients++;

        return true;
    }

    private Patient binarySearch(PatientIdentity patientIdentity) {
        int lower = 0;
        int upper = this.numberOfPatients - 1;

        while (lower <= upper) {
            int middle = lower + (upper - lower) / 2;
            Patient otherPatient = this.patientArray[middle];
            PatientIdentity otherPatientIdentity = otherPatient.getPatientIdentity();

            if (otherPatientIdentity.match(patientIdentity)) {
                return otherPatient;
            } else if (otherPatientIdentity.isLessThan(patientIdentity)) {
                lower = middle + 1;
            } else {
                upper = middle - 1;
            }
        }

        return null;
    }
}
