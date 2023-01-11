package eu.deltasource.internship;

import java.util.HashMap;
import java.util.Map;

public class Bank {

    private String name;
    private String address;
    private int countOfCustomers;
    private Map<String, Double> priceList = new HashMap<>();

    public Bank(String name, String address, int countOfCustomers, Map<String, Double> priceList) {
        this.name = name;
        this.address = address;
        this.countOfCustomers = countOfCustomers;
        this.priceList = priceList;
    }
}
