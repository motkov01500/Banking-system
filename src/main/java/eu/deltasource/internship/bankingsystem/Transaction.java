package eu.deltasource.internship.bankingsystem;

import java.time.LocalDate;

public class Transaction {
    private String sourceIBAN;
    private String targetIBAN;
    private Bank sourceBank;
    private Bank targetBank;
    private double amountBeingTransferred;
    private String sourceCurrency;
    private String targetCurrency;
    private Double exchangeRate;
    private LocalDate timeStamp;

    public Transaction(String sourceIBAN, String targetIBAN, Bank sourceBank, Bank targetBank, double amountBeingTransferred, String sourceCurrency, String targetCurrency, Double exchangeRate, LocalDate timeStamp) {
        this.sourceIBAN = sourceIBAN;
        this.targetIBAN = targetIBAN;
        this.sourceBank = sourceBank;
        this.targetBank = targetBank;
        this.amountBeingTransferred = amountBeingTransferred;
        this.sourceCurrency = sourceCurrency;
        this.targetCurrency = targetCurrency;
        this.exchangeRate = exchangeRate;
        this.timeStamp = timeStamp;
    }
}
