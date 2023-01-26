package eu.deltasource.internship.bankingsystem.services.Impl;

import eu.deltasource.internship.bankingsystem.Bank;
import eu.deltasource.internship.bankingsystem.BankAccount;
import eu.deltasource.internship.bankingsystem.Transaction;
import eu.deltasource.internship.bankingsystem.services.BankService;

import java.math.BigDecimal;

public class BankServiceImpl implements BankService {

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

    @Override
    public void depositing(BigDecimal amountToDeposit, BankAccount accountToDeposit) {
        BigDecimal calculateTheFee = amountToDeposit.multiply(accountToDeposit.getBank().getPriceList().get("deposit"));
        accountToDeposit.setAmountAvailable(accountToDeposit.getAmountAvailable().add(amountToDeposit.subtract(calculateTheFee)));
        Transaction transaction = new Transaction(accountToDeposit.getIban(), accountToDeposit.getBank(), amountToDeposit, accountToDeposit.getCurrency(), "deposit");
        accountToDeposit.getBank().getBankTransactions().add(transaction);
    }

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

    private BigDecimal priceWithTaxes(Bank bank, BigDecimal amountOfMoney, String typeOfTransaction) {
        BigDecimal feeOfTheBank = bank.getPriceList().get(typeOfTransaction);
        return amountOfMoney.add(amountOfMoney.multiply(feeOfTheBank));
    }

    private BigDecimal calculateSumWithExchangeRate(BigDecimal sumToExchange, BankAccount sourceAccount, BankAccount targetAccount) {
        if (sourceAccount.getCurrency() == targetAccount.getCurrency()) {
            return sumToExchange;
        } else {
            BigDecimal currentExchangeRate = exchangeRate(sourceAccount,targetAccount);
            return sumToExchange.multiply(currentExchangeRate);
        }
    }

    private String taxForTransfer(Bank currentBank, Bank targetBank) {
        if (currentBank != targetBank) {
            return String.format("transferToAccountFromOtherBank");
        } else {
            return String.format("transferToAccountFromSameBank");
        }
    }

    private BigDecimal calculateSumToTransferWithTaxes(BigDecimal amountToTransfer, Bank currentBank, Bank targetBank) {
        String taxForTransferInBank = taxForTransfer(currentBank, targetBank);
        BigDecimal tax = currentBank.getPriceList().get(taxForTransferInBank);

        return amountToTransfer.add(amountToTransfer.multiply(tax));
    }

    private BigDecimal exchangeRate(BankAccount sourceAccount, BankAccount targetAccount) {
        if (sourceAccount.getCurrency() == targetAccount.getCurrency()) {
            return new BigDecimal("1");
        } else {
            String exchangeToSearchInPriceList = String.format("%sTO%s", sourceAccount.getCurrency(), targetAccount.getCurrency()).toUpperCase();

            return sourceAccount.getBank().getPriceList().get(exchangeToSearchInPriceList);
        }
    }
}