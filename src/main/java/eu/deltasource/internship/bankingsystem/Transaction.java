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
    private BigDecimal exchangeRate;
    private LocalDate timeStamp;
    private String typeOfTransaction;

    public Transaction(String sourceIBAN, String targetIBAN, Bank sourceBank, Bank targetBank, BigDecimal amountBeingTransferred, String sourceCurrency, String targetCurrency, BigDecimal exchangeRate,String typeOfTransaction) {
        this.sourceIBAN = sourceIBAN;
        this.targetIBAN = targetIBAN;
        this.sourceBank = sourceBank;
        this.targetBank = targetBank;
        this.amountBeingTransferred = amountBeingTransferred;
        this.sourceCurrency = sourceCurrency;
        this.targetCurrency = targetCurrency;
        this.exchangeRate = exchangeRate;
        this.typeOfTransaction = typeOfTransaction;
        this.timeStamp = LocalDate.now();
    }

    public Transaction(String targetIBAN, Bank targetBank, BigDecimal amountBeingTransferred, String targetCurrency,String typeOfTransaction){
        this.targetIBAN = targetIBAN;
        this.targetBank = targetBank;
        this.amountBeingTransferred = amountBeingTransferred;
        this.targetCurrency = targetCurrency;
        this.typeOfTransaction = typeOfTransaction;
        this.timeStamp = LocalDate.now();
    }

    @Override
    public String toString() {
        if((typeOfTransaction == "deposit") || (typeOfTransaction == "withDraw")) {
            return String.format("Target IBAN is:%s. Target bank is %s. Amount being transferred was %s. Target currency is:%s. Type of transaction is: %s\n", targetIBAN, targetBank.getName(),amountBeingTransferred, targetCurrency,typeOfTransaction);
        } else{
            return String.format("Source IBAN is: %s. Target IBAN is: %s. Source Bank is: %s. Target bank is: %s. Amount being transferred was: %s. Source currency is: %s. Target currency is: %s. Exchange rate is: %s. Type of transaction is: %s",sourceIBAN,targetIBAN,sourceBank,targetBank,amountBeingTransferred,sourceCurrency,targetCurrency,exchangeRate,typeOfTransaction);
        }
    }
}