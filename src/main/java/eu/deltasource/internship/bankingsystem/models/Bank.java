package eu.deltasource.internship.bankingsystem.models;

import eu.deltasource.internship.bankingsystem.BankTaxes;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * In this class we save all transactions of the bank and all customers of the bank.
 * Also, every bank has the own price list with different taxes for transactions.
 */
public class Bank {

    private String name;
    private String address;
    private Map<BankTaxes, BigDecimal> priceList;
    private List<Transaction> bankTransactions;
    private List<Owner> bankCustomers;
    private List<BankAccount> accountList;

    public Bank(String name, String address, Map<BankTaxes, BigDecimal> priceList) {
        this.name = name;
        this.address = address;
        this.priceList = priceList;
        this.bankTransactions = new ArrayList<>();
        this.bankCustomers = new ArrayList<>();
        this.accountList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<BankAccount> getAccountList() {
        return accountList;
    }

    public List<Transaction> getBankTransactions(){
        return bankTransactions;
    }

    public List<Owner> getBankCustomers(){
        return bankCustomers;
    }

    public Map<BankTaxes, BigDecimal> getPriceList(){
        return priceList;
    }

    @Override
    public String toString(){
        return String.format("The bank: %s, has %s owners.\nOwners:\n %s\n Transactions:\n %s \n=========================================\n",name,bankCustomers.size(),bankCustomers,bankTransactions);
    }
}