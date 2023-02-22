package eu.deltasource.internship.bankingsystem.exceptions;

public class InvalidPersonalIdentificationNumberException extends RuntimeException{

    public InvalidPersonalIdentificationNumberException(String message) {
        super(message);
    }
}
