package com.github.noahstillwell;

public class Name {
    //Instance Variables
    private String firstName;
    private String lastName;

    //Constructors
    public Name(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    //Getters
    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    //Methods
    public String toString() {
        return this.lastName 
        + ", " 
        + this.firstName;
    }

    public boolean match(Name other) {
        return this.firstName.equalsIgnoreCase(other.firstName)
        && this.lastName.equalsIgnoreCase(other.lastName);
    }

    public boolean isLessThan(Name other) {
        if (this.lastName.compareToIgnoreCase(other.lastName) != 0) {
            return this.lastName.compareToIgnoreCase(other.lastName) < 0;
        } else {
            return this.firstName.compareToIgnoreCase(other.firstName) < 0;
        }
    }
}