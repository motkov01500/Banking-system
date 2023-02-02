package eu.deltasource.internship.bankingsystem.exceptions;

public class NoNeededAmountToWithdrawException extends RuntimeException {

    public NoNeededAmountToWithdrawException(String message) {
        super(message);
    }
}
