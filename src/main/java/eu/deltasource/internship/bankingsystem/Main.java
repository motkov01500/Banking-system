package eu.deltasource.internship.bankingsystem;

import eu.deltasource.internship.bankingsystem.constants.ExchangeRateEnum;
import eu.deltasource.internship.bankingsystem.services.BankService;
import eu.deltasource.internship.bankingsystem.services.Impl.BankServiceImpl;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        BankService bankService = new BankServiceImpl();
        Map<ExchangeRateEnum, BigDecimal> priceList = new HashMap<ExchangeRateEnum, BigDecimal>() {
            {
                put(ExchangeRateEnum.DEPOSIT, new BigDecimal("0.01"));
                put(ExchangeRateEnum.TRANSFER_DIFFERENT_BANK, new BigDecimal("0.10"));
                put(ExchangeRateEnum.TRANSFER_SAME_BANK, new BigDecimal("0.05"));
                put(ExchangeRateEnum.BGNTOUSD, new BigDecimal("1.95"));
                put(ExchangeRateEnum.USDTOBGN,new BigDecimal("0.55322"));
                put(ExchangeRateEnum.WITHDRAWING, new BigDecimal("0.01"));
            }
        };

        Bank dsk = new Bank("DSK", "street Vasil Levski", priceList);

        Owner Zhivodar = new Owner("Zhivodar", "Toskov", 69);
        Owner Hristo = new Owner("Hristo", "Gechev", 20);

        BankAccount bankAccountOfBatTiZhivodar = new BankAccount(Zhivodar, "12312312asda", "USD", dsk, new BigDecimal("255.55"), "current account");
        BankAccount bankAccountOfBatTiIco = new BankAccount(Hristo , "234123123", "BGN", dsk, new BigDecimal("253.52"), "current account");

        //bankService.depositing(new BigDecimal("150.55"), bankAccountOfBatTiZhivodar);
        //bankService.withDrawing(new BigDecimal("250"), bankAccountOfBatTiZhivodar);
        //bankService.transferMoney(new BigDecimal("150"),bankAccountOfBatTiZhivodar,bankAccountOfBatTiIco);
        //System.out.println(bankAccountOfBatTiZhivodar.getAmountAvailable());
        System.out.println(bankService.exchangeRate(bankAccountOfBatTiZhivodar,bankAccountOfBatTiIco));
    }
}