package eu.deltasource.internship.bankingsystem.exceptions;

import eu.deltasource.internship.bankingsystem.exceptions.messages.ExceptionMessage;

public class NoNeededAmountToTransferException extends RuntimeException{

    public NoNeededAmountToTransferException(String message) {
        super(message);
    }
}
