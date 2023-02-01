package eu.deltasource.internship.bankingsystem.exceptions;

import eu.deltasource.internship.bankingsystem.exceptions.messages.ExceptionMessage;

public class AnyAccountIsNotCurrentFailsTransferException extends RuntimeException{

    public AnyAccountIsNotCurrentFailsTransferException(String message) {
        super(message);
    }
}
