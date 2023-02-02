package eu.deltasource.internship.bankingsystem.exceptions;

public class NoNeededAmountToTransferException extends RuntimeException{

    public NoNeededAmountToTransferException(String message) {
        super(message);
    }
}
