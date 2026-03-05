package com.github.noahstillwell;

import java.util.Date;

public class PatientIdentity {
    //Instance Variables
    private Name name;
    private Date dateOfBirth;

    //Constructors
    public PatientIdentity(Name name, Date dateOfBirth) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    //Getters
    public Name getName() {
        return this.name;
    }

    public Date getDateOfBirth() {
        return this.dateOfBirth;
    }

    //Methods
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
}
