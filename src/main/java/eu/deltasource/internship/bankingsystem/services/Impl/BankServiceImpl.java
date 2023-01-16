package eu.deltasource.internship.bankingsystem.services.Impl;

import eu.deltasource.internship.bankingsystem.BankAccount;
import eu.deltasource.internship.bankingsystem.Transaction;
import eu.deltasource.internship.bankingsystem.services.BankService;

import java.math.BigDecimal;

public class BankServiceImpl implements BankService {

    @Override
    public void withDrawing(BigDecimal amountToWithDraw, BankAccount accountForWithDraw) {
        //TODO:Add taxes for the final price to transaction.P.S. There is a chance to do not have a necessary money to withdraw :)
        if (accountForWithDraw.getAmountAvailable().compareTo(amountToWithDraw) < 0) {
            throw new IllegalArgumentException("There is no needed amount to deposit");
        } else {
            accountForWithDraw.setAmountAvailable(accountForWithDraw.getAmountAvailable().subtract(amountToWithDraw));
            Transaction transaction = new Transaction(accountForWithDraw.getIban(), accountForWithDraw.getBank(), amountToWithDraw, accountForWithDraw.getCurrency());
            accountForWithDraw.getBank().getBankTransactions().add(transaction);
        }
    }

    @Override
    public void depositing(BigDecimal amountToDeposit, BankAccount sourceAccount, BankAccount targetAccount) {
        //sourceAccount.setAmountAvailable(sourceAccount.getAmountAvailable() - amountToDeposit);
        //targetAccount.setAmountAvailable(targetAccount.getAmountAvailable() + amountToDeposit);
    }
}
