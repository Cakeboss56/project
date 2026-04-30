package com.github.noahstillwell;

import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.NoSuchElementException;

public class PatientIdentity implements ObjectIdentity {
    // Instance Variables
    private Name name;
    private Date dateOfBirth;
    
    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    static { 
        formatter.setLenient(false);
    }

    // Constructors
    public PatientIdentity(Name name, Date dateOfBirth) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    // Getters
    public Name getName() {
        return this.name;
    }

    public Date getDateOfBirth() {
        return this.dateOfBirth;
    }

    // Methods
    public String toString() {
        return this.name.toString() 
        + ", "
        + formatDate(dateOfBirth);
    }

    public boolean match(PatientIdentity other) {
        return this.name.match(other.name) 
        && this.dateOfBirth.equals(other.dateOfBirth);
    }

    @Override
    public boolean match(ObjectIdentity identity) {
        return match((PatientIdentity) identity);
    }

    public boolean isLessThan(PatientIdentity other) {
        if (!this.name.match(other.name)) {
            return this.name.isLessThan(other.name);
        } else {
            return this.dateOfBirth.compareTo(other.dateOfBirth) < 0;
        }
    }

    @Override
    public boolean isLessThan(ObjectIdentity identity) {
        return isLessThan((PatientIdentity) identity);
    }

    public static String formatDate(Date date) {
        return formatter.format(date);
    }

    public static Date parseDate(String dateString) {
        try {
            return formatter.parse(dateString);
        } catch (ParseException parseException) {
            return null;
        }
    }

    public static PatientIdentity makePatientIdentity(String line) {
        try (Scanner scanner = new Scanner(line)) {
            scanner.useDelimiter(",\\s*");

            String lastName = scanner.next();
            String firstName = scanner.next();
            Date dateOfBirth = PatientIdentity.parseDate(scanner.next());

            if (lastName.isEmpty() || firstName.isEmpty() || dateOfBirth == null) {
                return null;
            }

            Name name = new Name(firstName, lastName);
            
            return new PatientIdentity(name, dateOfBirth);
        } catch (NoSuchElementException exception) {
            return null;
        }
    }
}
