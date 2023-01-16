package eu.deltasource.internship.bankingsystem;

import eu.deltasource.internship.bankingsystem.services.BankService;
import eu.deltasource.internship.bankingsystem.services.Impl.BankServiceImpl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        BankServiceImpl bankService = new BankServiceImpl();
        Map<String,BigDecimal> priceList = new HashMap<String,BigDecimal>(){
            {
                put("fee", new BigDecimal("25.5"));
                put("dollar",new BigDecimal("1.95"));
            }
        };

        Bank dsk = new Bank("DSK","street Vasil Levski",10,priceList);

        Owner Zhivodar = new Owner("Zhivodar","Toskov",69);

        BankAccount bankAccountOfBatTiZhivodar = new BankAccount(Zhivodar,"12312312asda","dollar",dsk,new BigDecimal("255.55"),"current account");

        bankService.withDrawing(new BigDecimal("250"),bankAccountOfBatTiZhivodar);

        System.out.println(dsk.getBankTransactions());
    }
}