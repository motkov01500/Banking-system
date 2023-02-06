package eu.deltasource.internship.bankingsystem.models;

import eu.deltasource.internship.bankingsystem.models.Bank;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * We made Transaction in deposit, transfer or withdraw. These transactions are saved in Bank in which is created.
 */
public class Transaction {
    private String sourceIBAN;
    private String targetIBAN;
    private String sourceBankName;
    private String targetBankName;
    private BigDecimal amountBeingTransferred;
    private String sourceCurrency;
    private String targetCurrency;
    private BigDecimal exchangeRate;
    private LocalDate timestamp;
    private String typeOfTransaction;

    /**
     * Constructor of Transaction only for transfer money between two accounts.
     */
    public Transaction(String sourceIBAN, String targetIBAN, String sourceBankName, String targetBankName, BigDecimal amountBeingTransferred, String sourceCurrency, String targetCurrency, BigDecimal exchangeRate, String typeOfTransaction, LocalDate timestamp) {
        this.sourceIBAN = sourceIBAN;
        this.targetIBAN = targetIBAN;
        this.sourceBankName = sourceBankName;
        this.targetBankName = targetBankName;
        this.amountBeingTransferred = amountBeingTransferred;
        this.sourceCurrency = sourceCurrency;
        this.targetCurrency = targetCurrency;
        this.exchangeRate = exchangeRate;
        this.typeOfTransaction = typeOfTransaction;
        this.timestamp = timestamp;
    }

    /**
     * Constructor of Transaction for deposit and withdrawing.
     */
    public Transaction(String targetIBAN, String targetBankName, BigDecimal amountBeingTransferred, String targetCurrency, String typeOfTransaction, LocalDate timestamp) {
        this.targetIBAN = targetIBAN;
        this.targetBankName = targetBankName;
        this.amountBeingTransferred = amountBeingTransferred;
        this.targetCurrency = targetCurrency;
        this.typeOfTransaction = typeOfTransaction;
        this.timestamp = timestamp;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public String getInformationAboutTransaction() {
        if ((typeOfTransaction == "deposit") || (typeOfTransaction == "withdraw")) {
            return String.format("Target IBAN is:%s. Target bank is %s. Amount being transferred was %s. Target currency is:%s. Type of transaction is: %s. The transaction is created in:%s\n", targetIBAN, targetBankName, amountBeingTransferred, targetCurrency, typeOfTransaction, timestamp.toString());
        } else {
            return String.format("Source IBAN is: %s. Target IBAN is: %s. Source Bank is: %s. Target bank is: %s. Amount being transferred was: %s. Source currency is: %s. Target currency is: %s. Exchange rate is: %s. Type of transaction is: %s. The transaction is created in:%s.", sourceIBAN, targetIBAN, sourceBankName, targetBankName, amountBeingTransferred, sourceCurrency, targetCurrency, exchangeRate, typeOfTransaction, timestamp);
        }
    }

    @Override
    public String toString() {
        return getInformationAboutTransaction();
    }
}