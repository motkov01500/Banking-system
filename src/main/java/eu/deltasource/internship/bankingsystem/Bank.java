package eu.deltasource.internship.bankingsystem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Bank {

    private String name;
    private String address;
    private int countOfCustomers;
    private Map<String, BigDecimal> priceList;
    private ArrayList<Transaction> bankTransactions;

    public Bank(String name, String address, int countOfCustomers, Map<String, BigDecimal> priceList) {
        this.name = name;
        this.address = address;
        this.countOfCustomers = countOfCustomers;
        this.priceList = priceList;
        this.bankTransactions = new ArrayList<>();
    }

    public ArrayList<Transaction> getBankTransactions(){
        return bankTransactions;
    }

    public Map<String, BigDecimal> getPriceList(){
        return priceList;
    }
}