package com.github.noahstillwell;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class PatientIdentity {
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

    public boolean isLessThan(PatientIdentity other) {
        if (!this.name.match(other.name)) {
            return this.name.isLessThan(other.name);
        } else {
            return this.dateOfBirth.compareTo(other.dateOfBirth) < 0;
        }
    }

    public static String formatDate(Date date) {
        return formatter.format(date);
    }

    public static Date parseDate(String dateString) {
        try {
            return formatter.parse(dateString);
        } catch (ParseException parseException){
            return null;
        }
    }
}
