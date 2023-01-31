package eu.deltasource.internship.bankingsystem.exceptions;

import eu.deltasource.internship.bankingsystem.exceptions.messages.ExceptionMessage;

public class AnyAccountIsNotCurrentFailsTransferException extends RuntimeException{
    public AnyAccountIsNotCurrentFailsTransferException() {
        super(ExceptionMessage.ANY_ACCOUNT_IS_NOT_CURRENT.getMessage());
    }
}
