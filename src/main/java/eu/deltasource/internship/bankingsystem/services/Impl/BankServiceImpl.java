package eu.deltasource.internship.bankingsystem.services.Impl;

import eu.deltasource.internship.bankingsystem.Bank;
import eu.deltasource.internship.bankingsystem.BankAccount;
import eu.deltasource.internship.bankingsystem.Transaction;
import eu.deltasource.internship.bankingsystem.services.BankService;

import java.math.BigDecimal;

public class BankServiceImpl implements BankService {

    private BigDecimal priceWithTaxes(Bank bank, BigDecimal amountOfMoney, String typeOfTransaction) {
        BigDecimal feeOfTheBank = bank.getPriceList().get(typeOfTransaction);
        return amountOfMoney.add(amountOfMoney.multiply(feeOfTheBank));
    }

    @Override
    public void withDrawing(BigDecimal amountToWithDraw, BankAccount accountForWithDraw) {
        BigDecimal amountForWithDrawWithFee = priceWithTaxes(accountForWithDraw.getBank(),amountToWithDraw,"withdraw");

        if (amountForWithDrawWithFee.compareTo(accountForWithDraw.getAmountAvailable()) > 0) {
            throw new IllegalArgumentException("There is no needed amount to deposit");
        } else {
            accountForWithDraw.setAmountAvailable(accountForWithDraw.getAmountAvailable().subtract(amountForWithDrawWithFee));
            Transaction transaction = new Transaction(accountForWithDraw.getIban(), accountForWithDraw.getBank(), amountToWithDraw, accountForWithDraw.getCurrency());
            accountForWithDraw.getBank().getBankTransactions().add(transaction);
        }
    }

    @Override
    public void depositing(BigDecimal amountToDeposit, BankAccount accountToDeposit) {
        BigDecimal amountForWithDrawWithFee = priceWithTaxes(accountToDeposit.getBank(),amountToDeposit,"deposit");
    }
}
