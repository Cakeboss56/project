package com.github.noahstillwell;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class PatientListDeprecated {
    // Instance Variables
    private Patient[] patientArray;
    private static int maximumPatients = 10000;
    private int numberOfPatients;
    private int indexOfIteration;
    
    // Constructors
    public PatientListDeprecated() {
        this.patientArray = new Patient[maximumPatients];
        this.numberOfPatients = 0;
        this.indexOfIteration = -1;
    }

    // Getters
    public Patient getPatient(int index) {
        return this.patientArray[index];
    }

    public int getNumberOfPatients() {
        return this.numberOfPatients;
    }

    public int getIndexOfIteration() {
        return this.indexOfIteration;
    }

    // Methods
    public boolean addPatient(Patient patient) {
        if (this.numberOfPatients > this.patientArray.length) {
            return false;
        }

        boolean addedPatient = addSorted(patient);

        return addedPatient;
    }

    public Patient findPatient(PatientIdentity patientIdentity) {
        return binarySearch(patientIdentity);
    }

    public void initializeIndexOfIteration() {
        if (this.numberOfPatients == 0) {
            this.indexOfIteration = -1;
        } else {
            this.indexOfIteration = 0;
        }
    }

    public Patient nextPatient() {
        if (this.indexOfIteration < 0 || this.indexOfIteration >= this.numberOfPatients + 1) {
            return null;
        }

        Patient patient = this.patientArray[this.indexOfIteration];
        this.indexOfIteration++;
        
        if (this.indexOfIteration >= this.numberOfPatients + 1) {
            this.indexOfIteration = -1;
        }

        return patient;
    }

    public boolean savePatients(String filename) {
        File file = new File(filename);

        try (FileWriter fileWriter = new FileWriter(file)) {
            initializeIndexOfIteration();
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

    public boolean importPatients(String filename) {
        File file = new File(filename);
        boolean importedEverything = true;

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Patient patient = Patient.makePatient(line);

                if (patient == null) {
                    continue;
                }

                if (this.numberOfPatients >= this.patientArray.length) {
                    importedEverything = false;
                    break;
                }

                this.patientArray[this.numberOfPatients] = patient;
                this.numberOfPatients++;
            }
        } catch (FileNotFoundException exception) {
            return false;
        }

        Patient[] patientsToSort = new Patient[this.numberOfPatients];

        for (int index = 0; index < this.numberOfPatients; index++) {
            patientsToSort[index] = this.patientArray[index];
        }

        mergeSort(patientsToSort);

        for (int index = 0; index < this.numberOfPatients; index++) {
            this.patientArray[index] = patientsToSort[index];
        }

        return importedEverything;
    }

    // Helper Methods
    private boolean addSorted(Patient patient) {
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

    private void mergeSort(Patient[] patientArray) {
        int length = patientArray.length;

        if (length < 2) {
            return;
        }

        int middle = length / 2;
        Patient[] left = new Patient[middle];
        Patient[] right = new Patient[length - middle];

        for (int index = 0; index < middle; index++) {
            left[index] = patientArray[index];
        }

        for (int index = middle; index < length; index++) {
            right[index - middle] = patientArray[index];
        }

        mergeSort(left);
        mergeSort(right);
        merge(patientArray, left, right);
    }

    // Helper Helper Methods
    private static void merge(Patient[] patientArray, Patient[] leftPatientArray, Patient[] rightPatientArray) {
        int leftSize = leftPatientArray.length;
        int rightSize = rightPatientArray.length;
        int leftIndex = 0;
        int rightIndex = 0;
        int mergedIndex = 0;

        while (leftIndex < leftSize && rightIndex < rightSize) {
            if (leftPatientArray[leftIndex].getPatientIdentity().isLessThan(rightPatientArray[rightIndex].getPatientIdentity())) {
                patientArray[mergedIndex] = leftPatientArray[leftIndex];
                leftIndex++;
            } else {
                patientArray[mergedIndex] = rightPatientArray[rightIndex];
                rightIndex++;
            }

            mergedIndex++;
        }

        while (leftIndex < leftSize) {
            patientArray[mergedIndex] = leftPatientArray[leftIndex];
            leftIndex++;
            mergedIndex++;
        }

        while (rightIndex < rightSize) {
            patientArray[mergedIndex] = rightPatientArray[rightIndex];
            rightIndex++;
            mergedIndex++;
        }
    }
}
