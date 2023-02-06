package eu.deltasource.internship.bankingsystem.models;

import eu.deltasource.internship.bankingsystem.enums.BankAccountType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;

import eu.deltasource.internship.bankingsystem.enums.Currency;

import java.util.List;

/**
 * Class about created bank account for any customer. The accounts could be current or savings.
 */
public class BankAccount {

    private Customer customer;
    private String iban;
    private Currency currency;
    private String bankIdentifierCode;
    private BigDecimal amountAvailable;
    private BankAccountType typeOfAccount;
    private List<Transaction> transactionList;

    public BankAccount(Customer customer, String iban, Currency currency, String bankIdentifierCode, BigDecimal amountAvailable, BankAccountType typeOfAccount) {
        this.customer = customer;
        this.iban = iban;
        this.currency = currency;
        this.amountAvailable = amountAvailable;
        this.typeOfAccount = typeOfAccount;
        this.bankIdentifierCode = bankIdentifierCode;
        this.transactionList = new ArrayList<>();
    }

    public BigDecimal getAmountAvailable() {
        return amountAvailable;
    }

    public String getOwnerPIN() {
        return customer.getPersonalIdentificationNumber();
    }

    public Customer getOwner() {
        return customer;
    }

    public String getIban() {
        return iban;
    }

    public Currency getCurrency() {
        return currency;
    }

    public String getBankIdentifierCode() {
        return bankIdentifierCode;
    }

    public BankAccountType getTypeOfAccount() {
        return typeOfAccount;
    }

    public List<Transaction> getTransactionList() {
        return Collections.unmodifiableList(transactionList);
    }

    public void addAmount(BigDecimal amount) {
        this.amountAvailable = amountAvailable.add(amount);
    }

    public void subtractAmount(BigDecimal amount) {
        this.amountAvailable = amountAvailable.subtract(amount);
    }

    public void addTransaction(Transaction transaction) {
        transactionList.add(transaction);
    }

    @Override
    public String toString() {
        return String.format("Bank account of %s %s. Iban is %s. Currency is:%s. Amount of this account is: %s. Type of account is: %s.\n", customer.getFirstName(), customer.getLastName(), iban, currency, amountAvailable, typeOfAccount);
    }
}