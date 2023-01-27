package eu.deltasource.internship.bankingsystem.services;

import eu.deltasource.internship.bankingsystem.BankAccount;
import java.math.BigDecimal;

public interface BankService {
    void withDrawing(BigDecimal amountToWithDraw, BankAccount accountForWithDraw);

    void depositing(BigDecimal amountToDeposit, BankAccount accountToDeposit);

    void transferMoney(BigDecimal amountToTransfer, BankAccount sourceAccount, BankAccount targetAccount);

    BigDecimal exchangeRate(BankAccount sourceAccount, BankAccount targetAccount);
}
