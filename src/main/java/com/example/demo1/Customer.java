package com.example.demo1;

public class Customer {
    public String firstName;
    public String lastName;
    public int burgersRequired;

    public Customer(String firstName, String lastName, int burgersRequired) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.burgersRequired = burgersRequired;
    }

    public String getFullName() {

        return firstName + " " + lastName;
    }

    public int getBurgersRequired() {

        return burgersRequired;
    }
}
