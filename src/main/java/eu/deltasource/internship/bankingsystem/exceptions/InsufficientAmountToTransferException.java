package eu.deltasource.internship.bankingsystem.exceptions;

public class InsufficientAmountToTransferException extends RuntimeException{

    public InsufficientAmountToTransferException(String message) {
        super(message);
    }
}
