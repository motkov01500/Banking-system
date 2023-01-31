package eu.deltasource.internship.bankingsystem.exceptions;

import eu.deltasource.internship.bankingsystem.exceptions.messages.ExceptionMessage;

public class AlreadyAccountWithSameIBANException extends RuntimeException{

    public AlreadyAccountWithSameIBANException() {
        super(ExceptionMessage.ALREADY_ACCOUNT_WITH_SAME_IBAN_Exception.getMessage());
    }
}
