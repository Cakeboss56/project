package com.github.noahstillwell;

import java.util.Date;
import java.util.Scanner;
import java.util.NoSuchElementException;

public class Patient {
    // Instance Variables
    private PatientIdentity patientIdentity;
    private PrescriptionList prescriptionList;

    // Constructors
    public Patient(PatientIdentity patientIdentity) {
        this.patientIdentity = patientIdentity;
        this.prescriptionList = new PrescriptionList();
    }

    // Getters
    public PatientIdentity getPatientIdentity() {
        return this.patientIdentity;
    }

    public PrescriptionList getPrescriptionList() {
        return this.prescriptionList;
    }

    // Methods
    public String toCSV() {
        return patientIdentity.toString();
    }

    public static Patient makePatient(String line) {
        try (Scanner scanner = new Scanner(line)) {
            scanner.useDelimiter(",\\s*");

            String lastName = scanner.next();
            String firstName = scanner.next();
            Date dateOfBirth = PatientIdentity.parseDate(scanner.next());

            if (lastName.isEmpty() || firstName.isEmpty() || dateOfBirth == null) {
                return null;
            }

            Name name = new Name(firstName, lastName);
            PatientIdentity patientIdentity = new PatientIdentity(name, dateOfBirth);
            return new Patient(patientIdentity);
        } catch (NoSuchElementException exception) {
            return null;
        }
    }
}