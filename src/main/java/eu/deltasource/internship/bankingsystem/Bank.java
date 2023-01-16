package eu.deltasource.internship.bankingsystem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Bank {

    private String name;
    private String address;
    private Map<String, BigDecimal> priceList;
    private ArrayList<Transaction> bankTransactions;
    private ArrayList<Owner> bankCustomers;

    public Bank(String name, String address, Map<String, BigDecimal> priceList) {
        this.name = name;
        this.address = address;
        this.priceList = priceList;
        this.bankTransactions = new ArrayList<>();
        this.bankCustomers = new ArrayList<>();
    }

    public ArrayList<Transaction> getBankTransactions(){
        return bankTransactions;
    }

    public ArrayList<Owner> getBankCustomers(){
        return bankCustomers;
    }

    public Map<String, BigDecimal> getPriceList(){
        return priceList;
    }
}