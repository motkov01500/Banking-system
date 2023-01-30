package eu.deltasource.internship.bankingsystem;

import eu.deltasource.internship.bankingsystem.constants.BankTaxes;
import eu.deltasource.internship.bankingsystem.services.BankService;
import eu.deltasource.internship.bankingsystem.services.Impl.BankServiceImpl;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        BankService bankService = new BankServiceImpl();
        Map<BankTaxes, BigDecimal> priceList = new HashMap<BankTaxes, BigDecimal>() {
            {
                put(BankTaxes.DEPOSIT, new BigDecimal("0.01"));
                put(BankTaxes.TRANSFER_DIFFERENT_BANK, new BigDecimal("0.10"));
                put(BankTaxes.TRANSFER_SAME_BANK, new BigDecimal("0.05"));
                put(BankTaxes.BGNTOUSD, new BigDecimal("1.95"));
                put(BankTaxes.USDTOBGN,new BigDecimal("0.55322"));
                put(BankTaxes.WITHDRAW, new BigDecimal("0.01"));
            }
        };

        Bank dsk = new Bank("DSK", "street Vasil Levski", priceList);

        Owner Zhivodar = new Owner("Zhivodar", "Toskov", 69);
        Owner Hristo = new Owner("Hristo", "Gechev", 20);

        BankAccount bankAccountOfBatTiZhivodar = new BankAccount(Zhivodar, "12312312asda", "USD", dsk, new BigDecimal("100"), "current account");
        BankAccount bankAccountOfBatTiIco = new BankAccount(Hristo , "234123123", "BGN", dsk, new BigDecimal("253.52"), "current account");
        BankAccount savingsBankAccountOfBatTiIco = new BankAccount(Hristo , "234123123", "BGN", dsk, new BigDecimal("253.52"), "savings account");

        //bankService.depositing(new BigDecimal("150"), bankAccountOfBatTiZhivodar, LocalDate.of(2000,10,2));
        //bankService.withDrawing(new BigDecimal("250"), bankAccountOfBatTiZhivodar,LocalDate.of(2000,10,2));
        bankService.transferMoney(new BigDecimal("150"),bankAccountOfBatTiZhivodar,bankAccountOfBatTiIco,LocalDate.of(2002,5,1));

        //System.out.println(bankService.getTransactionsInPeriodOfTime(LocalDate.of(2000,9,5),LocalDate.of(2001,2,1),dsk));
        //System.out.println(dsk);
    }
}