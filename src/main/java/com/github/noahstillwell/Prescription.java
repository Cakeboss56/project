package com.github.noahstillwell;

import java.util.Date;
import java.util.Scanner;
import java.util.NoSuchElementException;

public class Prescription {
    // Instance Variables
    String name;
    Date dateOfIssue;
    int dosage;
    String prescriber;

    // Constructors
    public Prescription(String name, Date dateOfIssue, int dosage, String prescriber) {
        this.name = name;
        this.dateOfIssue = dateOfIssue;
        this.dosage = dosage;
        this.prescriber = prescriber;
    }

    // Getters
    public String getName() {
        return this.name;
    }

    public Date getDateOfIssue() {
        return this.dateOfIssue;
    }

    public int getDosage() {
        return this.dosage;
    }

    public String getPrescriber() {
        return this.prescriber;
    }

    // Methods
    public String toString() {
        return this.name
        + ", "
        + PatientIdentity.formatDate(this.dateOfIssue)
        + ", "
        + this.dosage
        + ", "
        + this.prescriber;
    }

    public static Prescription makePrescription(String line) {
        try (Scanner scanner = new Scanner(line)) {
            scanner.useDelimiter(",\\s*");

            scanner.next();
            scanner.next();
            scanner.next();

            String name = scanner.next();
            Date dateOfIssue = PatientIdentity.parseDate(scanner.next());
            int dosage = Integer.parseInt(scanner.next());
            String prescriber = scanner.next();

            if (name.isEmpty() || dateOfIssue == null || prescriber.isEmpty()) {
                return null;
            }

            return new Prescription(name, dateOfIssue, dosage, prescriber);
        } catch (NoSuchElementException | NumberFormatException exception) {
            return null;
        }
    }
}
