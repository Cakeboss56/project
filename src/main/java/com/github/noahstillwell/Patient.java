package com.github.noahstillwell;

import java.util.Date;
import java.util.Scanner;
import java.util.NoSuchElementException;

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

    //Methods
    public String toCSV() {
        return patientIdentity.toString();
    }

    public static Patient makePatient(String line) {
        try (Scanner scanner = new Scanner(line)) {
            scanner.useDelimiter(",\\s*");

            String lastName = scanner.next();
            String firstName = scanner.next();
            String dateOfBirthString = scanner.next();

            Name name = new Name(firstName, lastName);
            Date dateOfBirth = PatientIdentity.parseDate(dateOfBirthString);
            PatientIdentity patientIdentity = new PatientIdentity(name, dateOfBirth);
            Patient patient = new Patient(patientIdentity);

            return patient;
        } catch (NoSuchElementException noSuchElementException) {
            return null;
        }
    }
}