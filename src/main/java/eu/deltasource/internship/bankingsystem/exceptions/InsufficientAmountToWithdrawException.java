package eu.deltasource.internship.bankingsystem.exceptions;

public class InsufficientAmountToWithdrawException extends RuntimeException {

    public InsufficientAmountToWithdrawException(String message) {
        super(message);
    }
}
