package com.github.noahstillwell;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class PatientList {
    //Instance Variables
    private Patient[] patientArray;
    private static int maximumPatients = 2000;
    private int numberOfPatients;
    private int indexOfIteration;
    
    //Constructors
    public PatientList() {
        this.patientArray = new Patient[maximumPatients];
        this.numberOfPatients = 0;
        this.indexOfIteration = -1;
    }

    //Getters
    public int getNumberOfPatients() {
        return this.numberOfPatients;
    }

    public int getIndexOfIteration() {
        return this.indexOfIteration;
    }

    public Patient getPatientArray(int index) {
        if (index < 0 || index >= this.numberOfPatients) {
            return null;
        }

        return this.patientArray[index];
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
        if (this.indexOfIteration == -1 || this.indexOfIteration >= this.numberOfPatients) {
            return null;
        }

        Patient patient = this.patientArray[this.indexOfIteration];
        this.indexOfIteration++;

        if (this.indexOfIteration >= this.numberOfPatients) {
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

    public boolean importFromFile(String filename) {
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

        mergeSortPatients(patientsToSort);

        for (int index = 0; index < this.numberOfPatients; index++) {
            this.patientArray[index] = patientsToSort[index];
        }

        return importedEverything;
    }

    private void mergeSortPatients(Patient[] patientArray) {
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

        mergeSortPatients(left);
        mergeSortPatients(right);
        merge(patientArray, left, right);
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
