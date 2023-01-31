package eu.deltasource.internship.bankingsystem.services;

import eu.deltasource.internship.bankingsystem.models.Bank;
import eu.deltasource.internship.bankingsystem.models.BankAccount;
import eu.deltasource.internship.bankingsystem.models.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

public interface BankService {
    void withDrawing(BigDecimal amountToWithDraw, BankAccount accountForWithDraw, LocalDate timestamp);

    void depositing(BigDecimal amountToDeposit, BankAccount accountToDeposit, LocalDate timestamp);

    void transferMoney(BigDecimal amountToTransfer, BankAccount sourceAccount, BankAccount targetAccount, LocalDate timestamp);

    ArrayList<Transaction> getTransactionsInPeriodOfTime(LocalDate startDate, LocalDate endDate, Bank bank);

    BigDecimal exchangeRate(BankAccount sourceAccount, BankAccount targetAccount);
}
