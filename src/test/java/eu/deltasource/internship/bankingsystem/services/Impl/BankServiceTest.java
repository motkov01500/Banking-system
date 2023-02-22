package eu.deltasource.internship.bankingsystem.services.Impl;

import eu.deltasource.internship.bankingsystem.enums.BankAccountType;
import eu.deltasource.internship.bankingsystem.enums.BankTaxType;
import eu.deltasource.internship.bankingsystem.enums.Currency;
import eu.deltasource.internship.bankingsystem.exceptions.*;
import eu.deltasource.internship.bankingsystem.models.Bank;
import eu.deltasource.internship.bankingsystem.models.BankAccount;
import eu.deltasource.internship.bankingsystem.models.Customer;
import eu.deltasource.internship.bankingsystem.repositories.BankAccountRepository;
import eu.deltasource.internship.bankingsystem.repositories.BankRepository;
import eu.deltasource.internship.bankingsystem.services.BankAccountService;
import eu.deltasource.internship.bankingsystem.services.BankService;
import eu.deltasource.internship.bankingsystem.services.ExchangeRateService;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class BankServiceTest {

    BankService bankService;
    Customer firstCustomer;
    Customer secondCustomer;
    Customer thirdCustomer;
    Customer fourthCustomer;
    BankAccount accountOfHristo;
    BankAccount accountOfPetur;
    BankAccount accountOfZhivko;
    BankAccount savingsAccountOfZhivko;
    ExchangeRateService exchangeRateService;
    BankAccountRepository bankAccountRepository;
    BankRepository bankRepository;
    BankAccountService bankAccountService;
    Map<BankTaxType, BigDecimal> priceList;


    @Before
    public void setUp() {
        bankRepository = new BankRepository();
        bankAccountRepository = new BankAccountRepository();

        exchangeRateService = new ExchangeRateService();
        bankService = new BankService(bankRepository);
        bankAccountService = new BankAccountService(bankAccountRepository, bankRepository, exchangeRateService);
        priceList = new HashMap<BankTaxType, BigDecimal>() {
            {
                put(BankTaxType.DEPOSIT, new BigDecimal("0.01"));
                put(BankTaxType.TRANSFER_DIFFERENT_BANK, new BigDecimal("0.10"));
                put(BankTaxType.TRANSFER_SAME_BANK, new BigDecimal("0.05"));
                put(BankTaxType.WITHDRAW, new BigDecimal("0.01"));
            }
        };
        bankService.addBank("DSK", "Vasil Levski st.", priceList, "ST32");
        bankService.addBank("Revolut", "Stones bl.", priceList, "BR35");
        firstCustomer = new Customer("Hristo", "Gechev", 20,"1010101010");
        secondCustomer = new Customer("Zhivko", "Gechev", 20,"2020202020");
        fourthCustomer = new Customer("Petur","Petrov",35,"5432112345");

        accountOfHristo = new BankAccount(firstCustomer, "1234123a", Currency.BGN, "ST32", new BigDecimal("250"), BankAccountType.CURRENT_ACCOUNT);
        accountOfZhivko = new BankAccount(secondCustomer, "1217112a", Currency.USD, "ST32", new BigDecimal("100"), BankAccountType.CURRENT_ACCOUNT);
        accountOfPetur = new BankAccount(fourthCustomer,"456357abv",Currency.USD,"BR35",new BigDecimal("200"),BankAccountType.CURRENT_ACCOUNT);
        savingsAccountOfZhivko = new BankAccount(secondCustomer, "abcdertq", Currency.USD, "ST32", new BigDecimal("1000"), BankAccountType.SAVINGS_ACCOUNT);
    }

    @Test
    public void testSuccessfulWithdrawing() {
        bankService.withdraw(new BigDecimal("100"), accountOfHristo, LocalDate.of(2000, 5, 2));
        assertEquals(accountOfHristo.getAmountAvailable(), new BigDecimal("149.00"));
    }

    @Test(expected = InsufficientAmountToWithdrawException.class)
    public void testWithdrawingWithInsufficientAmount() {
        bankService.withdraw(new BigDecimal("100"), accountOfZhivko, LocalDate.of(2000, 5, 2));
    }

    @Test
    public void testSuccessfulDepositToTargetAccount() {
        bankService.deposit(new BigDecimal("150"), accountOfZhivko, LocalDate.of(2000, 5, 2));
        assertEquals(accountOfZhivko.getAmountAvailable(), new BigDecimal("248.50"));
    }

    @Test
    public void testSuccessfulTransferMoneyToSameBank() {
        bankAccountService.transfer(new BigDecimal("50"), accountOfZhivko, accountOfHristo, LocalDate.of(2000, 5, 2));
        assertEquals(accountOfZhivko.getAmountAvailable(), new BigDecimal("47.50"));
        assertEquals(accountOfHristo.getAmountAvailable(), new BigDecimal("340.50"));
    }

    @Test
    public void testSuccessfulTransferToOtherBank() {
        bankAccountService.transfer(new BigDecimal("50"), accountOfZhivko,accountOfPetur,LocalDate.of(2000,5,2));
        assertEquals(accountOfZhivko.getAmountAvailable(),new BigDecimal("45.00"));
        assertEquals(accountOfPetur.getAmountAvailable(),new BigDecimal("250"));
    }

    @Test(expected = InsufficientAmountToTransferException.class)
    public void testIsAmountWithTaxesHigherThanSourceAmount() {
        bankAccountService.transfer(new BigDecimal("99"), accountOfZhivko, accountOfZhivko, LocalDate.of(2000, 5, 2));
    }

    @Test(expected = isSavingsAccountException.class)
    public void testIsAnyAccountCurrent() {
        bankAccountService.transfer(new BigDecimal("99"), savingsAccountOfZhivko, accountOfZhivko, LocalDate.of(2000, 5, 2));
    }

    @Test(expected = InvalidPersonalIdentificationNumberException.class)
    public void testIsCustomerPINCorrect() {
        thirdCustomer = new Customer("Lyubo","Motkov",21,"01502312121");
    }

    @Test(expected = InvalidBankIdentifierCodeException.class)
    public void testIsBankIdentifierCodeValid(){
        bankService.addBank("RandomBank","Random st",priceList,"SS335");
    }
}