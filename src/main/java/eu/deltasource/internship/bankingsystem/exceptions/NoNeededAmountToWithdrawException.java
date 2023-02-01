package eu.deltasource.internship.bankingsystem.exceptions;

import eu.deltasource.internship.bankingsystem.exceptions.messages.ExceptionMessage;

public class NoNeededAmountToWithdrawException extends RuntimeException {

    public NoNeededAmountToWithdrawException(String message) {
        super(message);
    }
}
