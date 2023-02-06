package eu.deltasource.internship.bankingsystem.services;

import eu.deltasource.internship.bankingsystem.enums.BankTaxType;
import eu.deltasource.internship.bankingsystem.enums.TransactionType;
import eu.deltasource.internship.bankingsystem.exceptions.BankAlreadyExistsException;
import eu.deltasource.internship.bankingsystem.exceptions.InsufficientAmountToWithdrawException;
import eu.deltasource.internship.bankingsystem.models.Bank;
import eu.deltasource.internship.bankingsystem.models.BankAccount;
import eu.deltasource.internship.bankingsystem.models.Customer;
import eu.deltasource.internship.bankingsystem.models.Transaction;
import eu.deltasource.internship.bankingsystem.repositories.BankRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Class about business logic for Bank class.(It contains following functionalities: withdraw, deposit and transfer money.)
 */
public class BankService {

    private BankRepository bankRepository;

    public BankService(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    public void addBank(String name, String address, Map<BankTaxType, BigDecimal> priceList, String bankIdentifierCode) {
        Bank bank = new Bank(name, address, priceList, bankIdentifierCode);

        if (bankRepository.getBankByIdentifierCode(bankIdentifierCode) != null)
            throw new BankAlreadyExistsException("Bank already exists");

        bankRepository.add(bank);
    }

    public List<Customer> getBankCustomers(String bankCode) {
        return bankRepository.getBankByIdentifierCode(bankCode).getBankCustomers();
    }

    /**
     * Method for withdrawing money from the Bank. Also, checks if current customer can afford to withdraw the sum.
     * Finally, if the withdrawing is successfully, the method creates the Transaction and add it to the list of transactions of customer's bank.
     */
    public void withdraw(BigDecimal amount, BankAccount account, LocalDate timestamp) {
        BigDecimal amountForWithDrawWithFee = priceWithTaxes(account.getBankIdentifierCode(), amount, BankTaxType.WITHDRAW);

        if (amountForWithDrawWithFee.compareTo(account.getAmountAvailable()) > 0)
            throw new InsufficientAmountToWithdrawException("There is no needed amount to withdraw.");

        account.subtractAmount(amountForWithDrawWithFee);
        Transaction transaction = new Transaction(account.getIban(), account.getBankIdentifierCode(), amount, account.getCurrency().getValue(), TransactionType.WITHDRAW.getValue(), timestamp);
        account.addTransaction(transaction);
    }

    /**
     * Method for depositing any amount of money to the customer account.
     * At the end of the method, we create a transaction which is added to the list of transactions of the customer's bank.
     */
    public void deposit(BigDecimal amount, BankAccount account, LocalDate timestamp) {
        Bank accountBank = bankRepository.getBankByIdentifierCode(account.getBankIdentifierCode());
        BigDecimal calculateTheFee = amount.multiply(accountBank.getBankTax(BankTaxType.DEPOSIT));
        account.addAmount(amount.subtract(calculateTheFee));
        Transaction transaction = new Transaction(account.getIban(), account.getBankIdentifierCode(), amount, account.getCurrency().getValue(), TransactionType.DEPOSIT.getValue(), timestamp);
        account.addTransaction(transaction);
    }

    /**
     * Method for calculate the amountOfMoney with the taxes.(Used in withdraw method)
     */
    private BigDecimal priceWithTaxes(String bankIdentifierCode, BigDecimal amount, BankTaxType typeOfTransaction) {
        Bank bank = bankRepository.getBankByIdentifierCode(bankIdentifierCode);
        BigDecimal feeOfTheBank = bank.getBankTax(typeOfTransaction);
        return amount.add(amount.multiply(feeOfTheBank));
    }
}