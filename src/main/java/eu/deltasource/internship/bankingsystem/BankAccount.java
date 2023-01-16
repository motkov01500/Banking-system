package eu.deltasource.internship.bankingsystem;

import java.math.BigDecimal;

public class BankAccount {

    private Owner owner;
    private String iban;
    private String currency;
    private Bank bank;
    private BigDecimal amountAvailable;
    private String typeOfAccount;

    public BankAccount(Owner owner, String iban, String currency, Bank bank, BigDecimal amountAvailable, String typeOfAccount) {
        this.owner = owner;
        this.iban = iban;
        this.currency = currency;
        this.amountAvailable = amountAvailable;
        this.typeOfAccount = typeOfAccount;
        this.bank = bank;
    }

    public BigDecimal getAmountAvailable() {
        return amountAvailable;
    }

    public String getIban() {
        return iban;
    }

    public String getCurrency() {
        return currency;
    }

    public Bank getBank(){
        return bank;
    }

    public void setAmountAvailable(BigDecimal amountAvailable) {
        this.amountAvailable = amountAvailable;
    }
}
