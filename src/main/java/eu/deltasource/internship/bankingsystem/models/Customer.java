package eu.deltasource.internship.bankingsystem.models;

import eu.deltasource.internship.bankingsystem.exceptions.InvalidPersonalIdentificationNumberException;

/**
 * Class to make owners for bank accounts in banks.
 */
public class Customer {

    private String personalIdentificationNumber;
    private String firstName;
    private String lastName;
    private int age;

    public Customer(String firstName, String lastName, int age, String personalIdentificationNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.setPersonalIdentificationNumber(personalIdentificationNumber);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getPersonalIdentificationNumber() {
        return personalIdentificationNumber;
    }

    public void setPersonalIdentificationNumber(String personalIdentificationNumber) {
        if (personalIdentificationNumber.length() != 10)
            throw new InvalidPersonalIdentificationNumberException("Invalid PIN. The PIN must be exact 10 symbols");

        this.personalIdentificationNumber = personalIdentificationNumber;
    }

    @Override
    public String toString() {
        return String.format("The full name of owner is: %s %s.The age of owner is:%s\n", getFirstName(), getLastName(), getAge());
    }
}