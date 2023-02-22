package eu.deltasource.internship.bankingsystem.services;

import eu.deltasource.internship.bankingsystem.enums.BankAccountType;
import eu.deltasource.internship.bankingsystem.enums.BankTaxType;
import eu.deltasource.internship.bankingsystem.enums.TransactionType;
import eu.deltasource.internship.bankingsystem.exceptions.isSavingsAccountException;
import eu.deltasource.internship.bankingsystem.exceptions.InsufficientAmountToTransferException;
import eu.deltasource.internship.bankingsystem.models.Bank;
import eu.deltasource.internship.bankingsystem.models.BankAccount;
import eu.deltasource.internship.bankingsystem.models.Customer;
import eu.deltasource.internship.bankingsystem.models.Transaction;
import eu.deltasource.internship.bankingsystem.repositories.BankAccountRepository;
import eu.deltasource.internship.bankingsystem.repositories.BankRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public class BankAccountService {

    private BankAccountRepository bankAccountRepository;
    private BankRepository bankRepository;
    private ExchangeRateService exchangeRateService;

    public BankAccountService(BankAccountRepository bankAccountRepository, BankRepository bankRepository, ExchangeRateService exchangeRateService) {
        this.bankAccountRepository = bankAccountRepository;
        this.bankRepository = bankRepository;
        this.exchangeRateService = exchangeRateService;
    }

    public void addBankAccount(BankAccount account) {
        bankAccountRepository.add(account, account.getBankIdentifierCode());
        Bank bank = bankRepository.getBankByIdentifierCode(account.getBankIdentifierCode());

        if (bank.getBankCustomers().size() > 0) {
            Optional<Customer> customerInList = bank.getBankCustomers().stream()
                    .filter(customer -> customer.getPersonalIdentificationNumber().equals(account.getOwnerPIN()))
                    .findFirst();

            if (!customerInList.isEmpty()
                    &&
                    customerInList.get().getPersonalIdentificationNumber().equals(account.getOwnerPIN())) {
                return;
            }
        }
        bank.addBankCustomer(account.getOwner());
    }

    public List<BankAccount> getBankAccounts(String bankCode) {
        return bankAccountRepository.get(bankCode);
    }

    public void transfer(BigDecimal amount, BankAccount sourceAccount, BankAccount targetAccount, LocalDate timestamp) {
        String currentBank = sourceAccount.getBankIdentifierCode();
        String otherBank = targetAccount.getBankIdentifierCode();
        BigDecimal amountToTransferWithExchangeRate = calculateSumWithExchangeRate(amount, sourceAccount, targetAccount);
        BigDecimal amountWithTax = calculateTransferSumWithTaxes(amount, currentBank, otherBank);
        BigDecimal exchangeRate = getExchangeRate(sourceAccount, targetAccount);

        if (amountWithTax.compareTo(sourceAccount.getAmountAvailable()) > 0)
            throw new InsufficientAmountToTransferException("There is no needed amount to transfer.");

        if (isAnyAccountIsCurrent(sourceAccount, targetAccount))
            throw new isSavingsAccountException("You can not transfer money if any of given accounts is different from current account.");

        targetAccount.addAmount(amountToTransferWithExchangeRate);
        Transaction transaction = new Transaction(sourceAccount.getIban(), targetAccount.getIban(), currentBank, otherBank, amount, sourceAccount.getCurrency().getValue(), targetAccount.getCurrency().getValue(), exchangeRate, TransactionType.TRANSFER.getValue(), timestamp);
        sourceAccount.subtractAmount(amountWithTax);
        sourceAccount.addTransaction(transaction);
        targetAccount.addTransaction(transaction);
    }


    /**
     * Method returns the bank transactions of given period of time.
     */
    public List<Transaction> getTransactionsInPeriodOfTime(LocalDate startDate, LocalDate endDate, String bankCode) {
        List<Transaction> transactionsBetweenPeriodOfTime = new ArrayList<>();

        for (Transaction transaction : getBankTransaction(bankCode)) {
            if (transaction.getTimestamp().isAfter(startDate) && transaction.getTimestamp().isBefore(endDate))
                transactionsBetweenPeriodOfTime.add(transaction);
        }

        return Collections.unmodifiableList(transactionsBetweenPeriodOfTime);
    }

    public List<Transaction> getBankTransaction(String bankCode) {
        return bankAccountRepository.getTransactions(bankCode);
    }

    private BigDecimal calculateTaxForTransfer(String currentBank, String targetBank) {
        Bank sourceBank = bankRepository.getBankByIdentifierCode(currentBank);

        if (!currentBank.equals(targetBank)) {
            return sourceBank.getBankTax(BankTaxType.TRANSFER_DIFFERENT_BANK);
        } else {
            return sourceBank.getBankTax(BankTaxType.TRANSFER_SAME_BANK);
        }
    }

    /**
     * Method to get the taxes from the price list of the current bank and calculate the needed amount to transfer the money.
     */
    private BigDecimal calculateTransferSumWithTaxes(BigDecimal amount, String currentBank, String otherBank) {
        BigDecimal tax = calculateTaxForTransfer(currentBank, otherBank);
        return amount.add(amount.multiply(tax));
    }

    /**
     * Method to exchange money from one currency to another currency.
     */
    private BigDecimal calculateSumWithExchangeRate(BigDecimal amount, BankAccount sourceAccount, BankAccount targetAccount) {
        if (sourceAccount.getCurrency().equals(targetAccount.getCurrency()))
            return amount;

        BigDecimal currentExchangeRate = getExchangeRate(sourceAccount, targetAccount);
        return amount.multiply(currentExchangeRate);
    }

    /**
     * Method to get the exchange rate between currencies in accounts.
     * Check the currencies in the source and target account and if currencies are same the value of  exchange rate is: 1.
     * But if the currencies are different, makes the key which we use to search in priceList in the Bank and return the current exchange rate.
     */
    private BigDecimal getExchangeRate(BankAccount sourceAccount, BankAccount targetAccount) {
        return exchangeRateService.getExchangeRate(sourceAccount, targetAccount);
    }

    private boolean isAnyAccountIsCurrent(BankAccount sourceAccount, BankAccount targetAccount) {
        return (!targetAccount.getTypeOfAccount().equals(BankAccountType.CURRENT_ACCOUNT)) || (!sourceAccount.getTypeOfAccount().equals(BankAccountType.CURRENT_ACCOUNT));
    }
}
