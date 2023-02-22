package eu.deltasource.internship.bankingsystem.exceptions;

public class BankAlreadyExistsException extends RuntimeException {

    public BankAlreadyExistsException(String message) {
        super(message);
    }
}
