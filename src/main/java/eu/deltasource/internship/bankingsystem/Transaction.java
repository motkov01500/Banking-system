package eu.deltasource.internship.bankingsystem;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *  We made Transaction in deposit, transfer or withdraw. These transactions are saved in Bank in which is created.
 */
public class Transaction {
    private String sourceIBAN;
    private String targetIBAN;
    private Bank sourceBank;
    private Bank targetBank;
    private BigDecimal amountBeingTransferred;
    private String sourceCurrency;
    private String targetCurrency;
    private BigDecimal exchangeRate;
    private LocalDate timestamp;
    private String typeOfTransaction;

    /**
     * Constructor of Transaction only for transfer money between two accounts.
     */
    public Transaction(String sourceIBAN, String targetIBAN, Bank sourceBank, Bank targetBank, BigDecimal amountBeingTransferred, String sourceCurrency, String targetCurrency, BigDecimal exchangeRate,String typeOfTransaction,LocalDate timestamp) {
        this.sourceIBAN = sourceIBAN;
        this.targetIBAN = targetIBAN;
        this.sourceBank = sourceBank;
        this.targetBank = targetBank;
        this.amountBeingTransferred = amountBeingTransferred;
        this.sourceCurrency = sourceCurrency;
        this.targetCurrency = targetCurrency;
        this.exchangeRate = exchangeRate;
        this.typeOfTransaction = typeOfTransaction;
        this.timestamp = timestamp;
    }

    /**
     * Constructor of Transaction for deposit and withdrawing.
     * */
    public Transaction(String targetIBAN, Bank targetBank, BigDecimal amountBeingTransferred, String targetCurrency,String typeOfTransaction,LocalDate timestamp){
        this.targetIBAN = targetIBAN;
        this.targetBank = targetBank;
        this.amountBeingTransferred = amountBeingTransferred;
        this.targetCurrency = targetCurrency;
        this.typeOfTransaction = typeOfTransaction;
        this.timestamp = timestamp;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        if((typeOfTransaction == "deposit") || (typeOfTransaction == "withdraw")) {
            return String.format("Target IBAN is:%s. Target bank is %s. Amount being transferred was %s. Target currency is:%s. Type of transaction is: %s. The transaction is created in:%s\n", targetIBAN, targetBank.getName(),amountBeingTransferred, targetCurrency,typeOfTransaction,timestamp.toString());
        } else{
            return String.format("Source IBAN is: %s. Target IBAN is: %s. Source Bank is: %s. Target bank is: %s. Amount being transferred was: %s. Source currency is: %s. Target currency is: %s. Exchange rate is: %s. Type of transaction is: %s. The transaction is created in:%s.",sourceIBAN,targetIBAN,sourceBank.getName(),targetBank.getName(),amountBeingTransferred,sourceCurrency,targetCurrency,exchangeRate,typeOfTransaction,timestamp);
        }
    }
}