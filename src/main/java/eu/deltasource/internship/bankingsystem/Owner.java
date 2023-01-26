package eu.deltasource.internship.bankingsystem;

public class Owner {

    private String firstName;
    private String lastName;
    private int age;

    public Owner(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
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

    @Override
    public String toString() {
        return String.format("The full name of owner is: %s %s.The age of owner is:%s\n", getFirstName(), getLastName(), getAge());
    }
}