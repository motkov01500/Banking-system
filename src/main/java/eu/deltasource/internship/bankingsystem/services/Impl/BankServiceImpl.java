package eu.deltasource.internship.bankingsystem.services.Impl;

import eu.deltasource.internship.bankingsystem.Bank;
import eu.deltasource.internship.bankingsystem.BankAccount;
import eu.deltasource.internship.bankingsystem.Transaction;
import eu.deltasource.internship.bankingsystem.services.BankService;

import java.math.BigDecimal;


/**
 * Class about business logic for Bank class.(It contains following functionalities: withdraw, deposit and transfer money.)
 */
public class BankServiceImpl implements BankService {


    /**
     * Method for withdrawing money from the Bank. Also, checks if current customer can afford to withdraw the sum.
     * Finally, if the withdrawing is successfully, the method creates the Transaction and add it to the list of transactions of customer's bank.
     * @param amountToWithDraw - money we want to withdraw
     * @param accountForWithDraw - account from we will withdraw
     */
    @Override
    public void withDrawing(BigDecimal amountToWithDraw, BankAccount accountForWithDraw) {
        BigDecimal amountForWithDrawWithFee = priceWithTaxes(accountForWithDraw.getBank(), amountToWithDraw, "withdraw");

        if (amountForWithDrawWithFee.compareTo(accountForWithDraw.getAmountAvailable()) > 0) {
            throw new IllegalArgumentException("There is no needed amount to withdraw.");
        } else {
            accountForWithDraw.setAmountAvailable(accountForWithDraw.getAmountAvailable().subtract(amountForWithDrawWithFee));
            Transaction transaction = new Transaction(accountForWithDraw.getIban(), accountForWithDraw.getBank(), amountToWithDraw, accountForWithDraw.getCurrency(), "withDraw");
            accountForWithDraw.getBank().getBankTransactions().add(transaction);
        }
    }

    /**
     * Method for depositing any amount of money to the customer account.
     * At the end of the method, we create a transaction which is added to the list of transactions of the customer's bank.
     * @param amountToDeposit - the amount of money we want to deposit to the customer's account
     * @param accountToDeposit - the bank account we want to receive the money
     */
    @Override
    public void depositing(BigDecimal amountToDeposit, BankAccount accountToDeposit) {
        BigDecimal calculateTheFee = amountToDeposit.multiply(accountToDeposit.getBank().getPriceList().get("deposit"));
        accountToDeposit.setAmountAvailable(accountToDeposit.getAmountAvailable().add(amountToDeposit.subtract(calculateTheFee)));
        Transaction transaction = new Transaction(accountToDeposit.getIban(), accountToDeposit.getBank(), amountToDeposit, accountToDeposit.getCurrency(), "deposit");
        accountToDeposit.getBank().getBankTransactions().add(transaction);
    }


    /**
     * Method to transfer money between two current accounts.
     * At the start we check if the availability with the fees of the customer's bank in the source account is enough to transfer to the another account.
     * Also, if the accounts are from current type, we make the transfer after that we make the transaction and add it list of transactions of the current bank.
     * @param amountToTransfer - The amount of money we want to transfer to the another bank account.
     * @param sourceAccount - The account from we want to transfer money.
     * @param targetAccount - The account we want to receive the transferred money.
     */
    @Override
    public void transferMoney(BigDecimal amountToTransfer, BankAccount sourceAccount, BankAccount targetAccount) {
        BigDecimal sumToTransfer = calculateSumWithExchangeRate(amountToTransfer, sourceAccount, targetAccount);
        BigDecimal sumWithTaxes = calculateSumToTransferWithTaxes(amountToTransfer, sourceAccount.getBank(), targetAccount.getBank());
        BigDecimal currentExchangeRate = exchangeRate(sourceAccount,targetAccount);

        if (sumWithTaxes.compareTo(sourceAccount.getAmountAvailable()) > 0)
            throw new IllegalArgumentException("There is no needed amount to transfer.");

        if (targetAccount.getTypeOfAccount() != "current account" || sourceAccount.getTypeOfAccount() != "current account") {
            throw new IllegalArgumentException("You can not transfer money if one of given accounts is different from current account.");
        } else {
            targetAccount.setAmountAvailable(targetAccount.getAmountAvailable().add(sumToTransfer));
            Transaction transaction = new Transaction(sourceAccount.getIban(), targetAccount.getIban(), sourceAccount.getBank(), targetAccount.getBank(), amountToTransfer, sourceAccount.getCurrency(), targetAccount.getCurrency(),currentExchangeRate, "transfering");
            sourceAccount.setAmountAvailable(sourceAccount.getAmountAvailable().subtract(sumWithTaxes));
        }
    }

    /**
     * Method for calculate the amountOfMoney with the taxes for any type of transaction we need.
     * The method is in use from the withdrawing functionality, because we need to subtract this amount from the account.
     */
    private BigDecimal priceWithTaxes(Bank bank, BigDecimal amountOfMoney, String typeOfTransaction) {
        BigDecimal feeOfTheBank = bank.getPriceList().get(typeOfTransaction);
        return amountOfMoney.add(amountOfMoney.multiply(feeOfTheBank));
    }

    /**
     * Method to exchange money from one currency to another currency.
     */
    private BigDecimal calculateSumWithExchangeRate(BigDecimal sumToExchange, BankAccount sourceAccount, BankAccount targetAccount) {
        if (sourceAccount.getCurrency() == targetAccount.getCurrency()) {
            return sumToExchange;
        } else {
            BigDecimal currentExchangeRate = exchangeRate(sourceAccount,targetAccount);
            return sumToExchange.multiply(currentExchangeRate);
        }
    }

    /**
     * Method to get the key for fee about transfer to the same or another bank.
     * After that we use the key to search in price list in the source customer bank.
     */
    private String taxForTransfer(Bank currentBank, Bank targetBank) {
        if (currentBank != targetBank) {
            return String.format("transferToAccountFromOtherBank");
        } else {
            return String.format("transferToAccountFromSameBank");
        }
    }

    /**
     * Method to get the taxes from the price list of the current bank and calculate the needed amount to transfer the money.
     * @param amountToTransfer - Amount of money we want to transfer.(Without taxes)
     */
    private BigDecimal calculateSumToTransferWithTaxes(BigDecimal amountToTransfer, Bank currentBank, Bank targetBank) {
        String taxForTransferInBank = taxForTransfer(currentBank, targetBank);
        BigDecimal tax = currentBank.getPriceList().get(taxForTransferInBank);

        return amountToTransfer.add(amountToTransfer.multiply(tax));
    }

    /**
     * Method to get the exchange rate between currencies in accounts.
     * Check the currencies in the source and target account and if currencies are same the value of  exchange rate is: 1.
     * But if the currencies are different, makes the key which we use to search in priceList in the Bank and return the current exchange rate.
     */
    private BigDecimal exchangeRate(BankAccount sourceAccount, BankAccount targetAccount) {
        if (sourceAccount.getCurrency().equals(targetAccount.getCurrency())) {
            return new BigDecimal("1");
        } else {
            String exchangeToSearchInPriceList = String.format("%sTO%s", sourceAccount.getCurrency(), targetAccount.getCurrency()).toUpperCase();

            return sourceAccount.getBank().getPriceList().get(exchangeToSearchInPriceList);
        }
    }
}