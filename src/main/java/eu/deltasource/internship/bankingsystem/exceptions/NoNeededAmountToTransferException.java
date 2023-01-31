package eu.deltasource.internship.bankingsystem.exceptions;

import eu.deltasource.internship.bankingsystem.exceptions.messages.ExceptionMessage;

public class NoNeededAmountToTransferException extends RuntimeException{
    public NoNeededAmountToTransferException() {
        super(ExceptionMessage.NO_NEEDED_AMOUNT_TO_TRANSFER.getMessage());
    }
}
