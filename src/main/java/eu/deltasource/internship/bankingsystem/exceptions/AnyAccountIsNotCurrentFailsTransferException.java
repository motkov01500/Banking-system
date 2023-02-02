package eu.deltasource.internship.bankingsystem.exceptions;

public class AnyAccountIsNotCurrentFailsTransferException extends RuntimeException{

    public AnyAccountIsNotCurrentFailsTransferException(String message) {
        super(message);
    }
}
