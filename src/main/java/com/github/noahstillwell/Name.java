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

    //Methods
    public String fullName() {
        return this.lastName + ", " + this.firstName;
    }

    public boolean match(Name other) {
        return this.firstName.toLowerCase().equals(other.firstName.toLowerCase()) 
        && this.lastName.toLowerCase().equals(other.lastName.toLowerCase());
    }

    public boolean isLessThan(Name other) {
        if (this.lastName.toLowerCase().compareTo(other.lastName.toLowerCase()) != 0) {
            return this.lastName.toLowerCase().compareTo(other.lastName.toLowerCase()) < 0;
        } else {
            return this.firstName.toLowerCase().compareTo(other.firstName.toLowerCase()) < 0;
        }
    }
}
