package eu.deltasource.internship.bankingsystem.repositories;

import eu.deltasource.internship.bankingsystem.models.Bank;

import java.util.ArrayList;
import java.util.List;

public class BankRepository {

    List<Bank> bankList;

    public BankRepository() {
        bankList = new ArrayList<>();
    }

    public Bank getBankByIdentifierCode(String identifyCode) {
        for (Bank bank : bankList) {
            if (bank.getBankIdentifierCode().equals(identifyCode)) {
                return bank;
            }
        }
        return null;
    }

    public void add(Bank bank) {
        bankList.add(bank);
    }
}
