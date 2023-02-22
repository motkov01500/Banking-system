package eu.deltasource.internship.bankingsystem.repositories;

import eu.deltasource.internship.bankingsystem.models.BankAccount;
import eu.deltasource.internship.bankingsystem.models.Transaction;

import java.util.*;

public class BankAccountRepository {

    Map<String, List<BankAccount>> bankAccountMap;

    public BankAccountRepository() {
        this.bankAccountMap = new HashMap<>();
    }

    public void add(BankAccount bankAccount, String bankIdentifierCode) {
        if (!bankAccountMap.containsKey(bankIdentifierCode))
            bankAccountMap.put(bankIdentifierCode, new ArrayList<>());

        bankAccountMap.get(bankIdentifierCode).add(bankAccount);
    }

    public List<BankAccount> get(String bankCode) {
        return Collections.unmodifiableList(bankAccountMap.get(bankCode));
    }

    public List<Transaction> getTransactions(String bankCode) {
        List<Transaction> transactions = new ArrayList<>();

        for (BankAccount account : bankAccountMap.get(bankCode)) {
            transactions.addAll(account.getTransactionList());
        }

        return transactions;
    }
}
