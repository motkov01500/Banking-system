package eu.deltasource.internship.bankingsystem.models;

import eu.deltasource.internship.bankingsystem.enums.BankTaxType;
import eu.deltasource.internship.bankingsystem.exceptions.InvalidBankIdentifierCodeException;

import java.math.BigDecimal;
import java.util.*;

/**
 * In this class we save all transactions of the bank and all customers of the bank.
 * Also, every bank has the own price list with different taxes for transactions.
 */
public class Bank {

    private String bankIdentifierCode;
    private String name;
    private String address;
    private Map<BankTaxType, BigDecimal> priceList;
    private List<Customer> bankCustomers;

    public Bank(String name, String address, Map<BankTaxType, BigDecimal> priceList, String bankIdentifierCode) {
        this.name = name;
        this.address = address;
        this.priceList = priceList;
        this.bankCustomers = new ArrayList<>();
        this.setBankIdentifierCode(bankIdentifierCode);
    }

    public String getName() {
        return name;
    }

    public List<Customer> getBankCustomers() {
        return Collections.unmodifiableList(bankCustomers);
    }

    public BigDecimal getBankTax(BankTaxType taxType) {
        return priceList.get(taxType);
    }

    public String getBankIdentifierCode() {
        return bankIdentifierCode;
    }

    public void setBankIdentifierCode(String bankIdentifierCode) {
        if (bankIdentifierCode.length() != 4)
            throw new InvalidBankIdentifierCodeException("Identifier code must be 4 symbols.");

        this.bankIdentifierCode = bankIdentifierCode;
    }

    public void addBankCustomer(Customer customer) {
        bankCustomers.add(customer);
    }

    @Override
    public String toString() {
        return String.format("The bank name is: %s.\nThe address is: %s.", name, address);
    }
}