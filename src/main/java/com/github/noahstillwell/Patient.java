package com.github.noahstillwell;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Scanner;

public class Patient {
    //Instance Variables
    private PatientIdentity patientIdentity;
    private static final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    static {
        formatter.setLenient(false);
    }

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
        String nameString = this.patientIdentity.getName().toString();
        String dateOfBirthString = formatter.format(this.patientIdentity.getDateOfBirth());
        String patientIdentityString = nameString + ", " + dateOfBirthString;

        return patientIdentityString;
    }

    public static Patient makePatient(String line) {
        try (Scanner scanner = new Scanner(line)) {
            scanner.useDelimiter(",\\s*");

            String lastName = scanner.next();
            String firstName = scanner.next();
            String dateOfBirthString = scanner.next();

            Name name = new Name(firstName, lastName);
            Date dateOfBirth = parseDate(dateOfBirthString);
            PatientIdentity patientIdentity = new PatientIdentity(name, dateOfBirth);
            Patient patient = new Patient(patientIdentity);

            return patient;
        } catch (Exception exception) {
            return null;
        }
    }

    //Helper Methods
    private static Date parseDate(String dateString) throws ParseException {
        return formatter.parse(dateString);
    }
}