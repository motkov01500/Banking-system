package eu.deltasource.internship.bankingsystem.models;

import eu.deltasource.internship.bankingsystem.BankAccountType;

import java.math.BigDecimal;

/**
 * Class about created bank account for any customer. The accounts could be current or savings.
 */
public class BankAccount {

    private Owner owner;
    private String iban;
    private String currency;
    private Bank bank;
    private BigDecimal amountAvailable;
    private BankAccountType typeOfAccount;

    public BankAccount(Owner owner, String iban, String currency, Bank bank, BigDecimal amountAvailable, BankAccountType typeOfAccount) {
        this.owner = owner;
        this.iban = iban;
        this.currency = currency;
        this.amountAvailable = amountAvailable;
        this.typeOfAccount = typeOfAccount;
        this.bank = bank;

        if(!bank.getBankCustomers().contains(owner)){
            this.bank.addBankCustomer(owner);
        }
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

    public BankAccountType getTypeOfAccount(){
        return typeOfAccount;
    }

    public void setAmountAvailable(BigDecimal amountAvailable) {
        this.amountAvailable = amountAvailable;
    }

    @Override
    public String toString() {
        return String.format("Bank account of %s %s. Iban is %s. Currency is:%s. Amount of this account is: %s. Type of account is: %s.\n",owner.getFirstName(),owner.getLastName(),iban,currency,amountAvailable,typeOfAccount);
    }
}
