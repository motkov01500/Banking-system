package eu.deltasource.internship.bankingsystem.exceptions;

public class InvalidBankIdentifierCodeException extends RuntimeException{

    public InvalidBankIdentifierCodeException(String message) {
        super(message);
    }
}
