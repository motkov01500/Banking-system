package eu.deltasource.internship.bankingsystem.exceptions;

import eu.deltasource.internship.bankingsystem.exceptions.messages.ExceptionMessage;

public class NoNeededAmountToWithdrawException extends RuntimeException {

    public NoNeededAmountToWithdrawException() {
        super(ExceptionMessage.NO_NEEDED_AMOUNT_TO_WITHDRAW.getMessage());
    }
}
