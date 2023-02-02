package eu.deltasource.internship.bankingsystem.services.Impl;

import eu.deltasource.internship.bankingsystem.*;
import eu.deltasource.internship.bankingsystem.exceptions.AnyAccountIsNotCurrentFailsTransferException;
import eu.deltasource.internship.bankingsystem.exceptions.NoNeededAmountToTransferException;
import eu.deltasource.internship.bankingsystem.exceptions.NoNeededAmountToWithdrawException;
import eu.deltasource.internship.bankingsystem.models.Bank;
import eu.deltasource.internship.bankingsystem.models.BankAccount;
import eu.deltasource.internship.bankingsystem.models.Owner;
import eu.deltasource.internship.bankingsystem.services.BankService;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class BankServiceTest {

    BankService bankService;
    Owner firstOwner;
    Owner secondOwner;
    Bank dsk;
    BankAccount accountOfHristo;
    BankAccount accountOfZhivko;
    BankAccount savingsAccountOfZhivko;

    @Before
    public void setUp() {
        Map<BankTaxes, BigDecimal> priceList = new HashMap<BankTaxes, BigDecimal>() {
            {
                put(BankTaxes.DEPOSIT, new BigDecimal("0.01"));
                put(BankTaxes.TRANSFER_DIFFERENT_BANK, new BigDecimal("0.10"));
                put(BankTaxes.TRANSFER_SAME_BANK, new BigDecimal("0.05"));
                put(BankTaxes.BGN_TO_USD, new BigDecimal("1.95"));
                put(BankTaxes.USD_TO_BGN, new BigDecimal("0.55322"));
                put(BankTaxes.WITHDRAW, new BigDecimal("0.01"));
            }
        };
        firstOwner = new Owner("Hristo", "Gechev", 20);
        secondOwner = new Owner("Zhivko", "Gechev", 20);
        dsk = new Bank("DSK", "Vasil Levski street", priceList);
        bankService = new BankService();
        accountOfHristo = new BankAccount(firstOwner, "1234123a", "BGN", dsk, new BigDecimal("250"), BankAccountType.CURRENT_ACCOUNT);
        accountOfZhivko = new BankAccount(secondOwner, "1217112a", "USD", dsk, new BigDecimal("100"), BankAccountType.CURRENT_ACCOUNT);
        savingsAccountOfZhivko = new BankAccount(secondOwner, "abcdertq", "USD", dsk, new BigDecimal("1000"), BankAccountType.SAVINGS_ACCOUNT);
    }

    @Test
    public void successfulWithdrawing() {
        bankService.withDrawing(new BigDecimal("100"), accountOfHristo, LocalDate.of(2000, 5, 2));
        assertEquals(accountOfHristo.getAmountAvailable(), new BigDecimal("149.00"));
    }

    @Test(expected = NoNeededAmountToWithdrawException.class)
    public void withdrawingWithNoNeededAmount() {
        bankService.withDrawing(new BigDecimal("100"), accountOfZhivko, LocalDate.of(2000, 5, 2));
    }

    @Test
    public void successfulDepositToTargetAccount() {
        bankService.depositing(new BigDecimal("150"), accountOfZhivko, LocalDate.of(2000, 5, 2));
        assertEquals(accountOfZhivko.getAmountAvailable(), new BigDecimal("248.50"));
    }

    @Test
    public void successfulTransferMoneyToSameBank() {
        bankService.transferMoney(new BigDecimal("50"), accountOfZhivko, accountOfHristo, LocalDate.of(2000, 5, 2));
        assertEquals(accountOfZhivko.getAmountAvailable(), new BigDecimal("47.50"));
        assertEquals(accountOfHristo.getAmountAvailable(), new BigDecimal("277.66100"));
    }

    @Test(expected = NoNeededAmountToTransferException.class)
    public void isAmountWithTaxesIsHigherThanSourceAmount() {
        bankService.transferMoney(new BigDecimal("99"), accountOfZhivko, accountOfZhivko, LocalDate.of(2000, 5, 2));
    }

    @Test(expected = AnyAccountIsNotCurrentFailsTransferException.class)
    public void isAnyAccountIsNotCurrent() {
        bankService.transferMoney(new BigDecimal("99"), savingsAccountOfZhivko, accountOfZhivko, LocalDate.of(2000, 5, 2));
    }
}