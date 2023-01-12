package eu.deltasource.internship.bankingsystem.services;

public interface BankService {
    void withDrawing(double amountToWithDraw,String iban);

    void depositing(double amountToDeposit,String sourceIban,String targetIban);
}
