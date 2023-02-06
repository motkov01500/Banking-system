package eu.deltasource.internship.bankingsystem;

import eu.deltasource.internship.bankingsystem.enums.BankAccountType;
import eu.deltasource.internship.bankingsystem.enums.BankTaxType;
import eu.deltasource.internship.bankingsystem.enums.Currency;
import eu.deltasource.internship.bankingsystem.models.Bank;
import eu.deltasource.internship.bankingsystem.models.BankAccount;
import eu.deltasource.internship.bankingsystem.models.Customer;
import eu.deltasource.internship.bankingsystem.repositories.BankAccountRepository;
import eu.deltasource.internship.bankingsystem.repositories.BankRepository;
import eu.deltasource.internship.bankingsystem.services.BankAccountService;
import eu.deltasource.internship.bankingsystem.services.BankService;
import eu.deltasource.internship.bankingsystem.services.ExchangeRateService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Application {

    public static void main(String[] args) {
        BankRepository bankRepository = new BankRepository();
        BankAccountRepository bankAccountRepository = new BankAccountRepository();

        ExchangeRateService exchangeRateService = new ExchangeRateService();
        BankService bankService = new BankService(bankRepository);
        BankAccountService bankAccountService = new BankAccountService(bankAccountRepository, bankRepository, exchangeRateService);

        Map<BankTaxType, BigDecimal> priceList = new HashMap<BankTaxType, BigDecimal>() {
            {
                put(BankTaxType.DEPOSIT, new BigDecimal("0.01"));
                put(BankTaxType.TRANSFER_DIFFERENT_BANK, new BigDecimal("0.10"));
                put(BankTaxType.TRANSFER_SAME_BANK, new BigDecimal("0.05"));
                put(BankTaxType.WITHDRAW, new BigDecimal("0.01"));
            }
        };

        bankService.addBank("DSK", "Vasil Levski st.", priceList, "ST32");
        Customer Zhivodar = new Customer("Zhivodar", "Toskov", 69, "1234567898");
        Customer Hristo = new Customer("Hristo", "Gechev", 20, "1111111111");
        BankAccount bankAccountOfBatTiZhivodar = new BankAccount(Zhivodar, "12312312asda", Currency.BGN, "ST32", new BigDecimal("100"), BankAccountType.CURRENT_ACCOUNT);
        BankAccount bankAccountOfBatTiIco = new BankAccount(Hristo, "234123123", Currency.USD, "ST32", new BigDecimal("253.52"), BankAccountType.CURRENT_ACCOUNT);
        BankAccount bankAccountOfBatTiKuncho = new BankAccount(Hristo, "23412312312", Currency.USD, "ST32", new BigDecimal("253.52"), BankAccountType.CURRENT_ACCOUNT);
        BankAccount bankAccountOfBatTi = new BankAccount(Zhivodar, "234123123", Currency.USD, "ST32", new BigDecimal("253.52"), BankAccountType.CURRENT_ACCOUNT);
        BankAccount savingsBankAccountOfBatTiIco = new BankAccount(Hristo, "234123123", Currency.BGN, "ST32", new BigDecimal("253.52"), BankAccountType.SAVINGS_ACCOUNT);

        bankAccountService.addBankAccount(bankAccountOfBatTiZhivodar);
        bankAccountService.addBankAccount(bankAccountOfBatTiIco);
        bankAccountService.addBankAccount(bankAccountOfBatTiKuncho);
        bankAccountService.addBankAccount(bankAccountOfBatTi);
        bankAccountService.addBankAccount(savingsBankAccountOfBatTiIco);

        bankService.deposit(new BigDecimal("150"), bankAccountOfBatTiZhivodar, LocalDate.of(2000, 5, 6));
        bankService.withdraw(new BigDecimal("50"), bankAccountOfBatTiZhivodar, LocalDate.of(2001, 1, 2));
        bankAccountService.transfer(new BigDecimal("20"), bankAccountOfBatTiZhivodar, bankAccountOfBatTiIco, LocalDate.of(2002, 5, 1));

        LocalDate dateFrom = LocalDate.of(2000, 5, 2);
        LocalDate dateTo = LocalDate.of(2001, 1, 3);
        Bank bank = bankRepository.getBankByIdentifierCode("ST32");


        System.out.println(bank);
        System.out.println(String.format("The customers to %s bank are:\n%s",
                bank.getName(), bank.getBankCustomers()));
        System.out.println(String.format("The transactions of %s bank are:\n%s:",
                bank.getName(), bankAccountService.getBankTransaction("ST32")));
        System.out.println(String.format("Transactions between %s and %s are:\n %s",
                dateFrom, dateTo, bankAccountService.getTransactionsInPeriodOfTime(dateFrom, dateTo, "ST32")));
        System.out.println(String.format("The account of %s with %s iban transactions are:\n%s",
                bank.getName(), bankAccountOfBatTiZhivodar.getIban(), bankAccountOfBatTiZhivodar.getTransactionList()));
    }
}