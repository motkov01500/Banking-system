package eu.deltasource.internship.bankingsystem;

import eu.deltasource.internship.bankingsystem.constants.BankTaxes;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;

/**
 * In this class we save all transactions of the bank and all customers of the bank.
 * Also, every bank has the own price list with different taxes for transactions.
 */
public class Bank {

    private String name;
    private String address;
    private Map<BankTaxes, BigDecimal> priceList;
    private ArrayList<Transaction> bankTransactions;
    private ArrayList<Owner> bankCustomers;

    public Bank(String name, String address, Map<BankTaxes, BigDecimal> priceList) {
        this.name = name;
        this.address = address;
        this.priceList = priceList;
        this.bankTransactions = new ArrayList<>();
        this.bankCustomers = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Transaction> getBankTransactions(){
        return bankTransactions;
    }

    public ArrayList<Owner> getBankCustomers(){
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