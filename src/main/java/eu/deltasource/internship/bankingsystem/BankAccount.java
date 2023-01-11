package eu.deltasource.internship.bankingsystem;

public class BankAccount {

    private Owner owner;
    private String iban;
    private String currency;
    private double amountAvailable;
    private String typeOfAccount;

    public BankAccount(Owner owner, String iban, String currency, double amountAvailable, String typeOfAccount) {
        this.owner = owner;
        this.iban = iban;
        this.currency = currency;
        this.amountAvailable = amountAvailable;
        this.typeOfAccount = typeOfAccount;
    }
}
