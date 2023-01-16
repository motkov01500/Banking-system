package eu.deltasource.internship.bankingsystem;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Transaction {
    private String sourceIBAN;
    private String targetIBAN;
    private Bank sourceBank;
    private Bank targetBank;
    private BigDecimal amountBeingTransferred;
    private String sourceCurrency;
    private String targetCurrency;
    private Double exchangeRate;
    private LocalDate timeStamp;

    public Transaction(String sourceIBAN, String targetIBAN, Bank sourceBank, Bank targetBank, BigDecimal amountBeingTransferred, String sourceCurrency, String targetCurrency, Double exchangeRate) {
        this.sourceIBAN = sourceIBAN;
        this.targetIBAN = targetIBAN;
        this.sourceBank = sourceBank;
        this.targetBank = targetBank;
        this.amountBeingTransferred = amountBeingTransferred;
        this.sourceCurrency = sourceCurrency;
        this.targetCurrency = targetCurrency;
        this.exchangeRate = exchangeRate;
        this.timeStamp = LocalDate.now();
    }

    public Transaction(String targetIBAN, Bank targetBank, BigDecimal amountBeingTransferred, String targetCurrency){
        this.targetIBAN = targetIBAN;
        this.targetBank = targetBank;
        this.amountBeingTransferred = amountBeingTransferred;
        this.targetCurrency = targetCurrency;
        this.timeStamp = LocalDate.now();
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "sourceIBAN='" + sourceIBAN + '\'' +
                ", targetIBAN='" + targetIBAN + '\'' +
                ", sourceBank=" + sourceBank +
                ", targetBank=" + targetBank +
                ", amountBeingTransferred=" + amountBeingTransferred +
                ", sourceCurrency='" + sourceCurrency + '\'' +
                ", targetCurrency='" + targetCurrency + '\'' +
                ", exchangeRate=" + exchangeRate +
                ", timeStamp=" + timeStamp +
                '}';
    }
}