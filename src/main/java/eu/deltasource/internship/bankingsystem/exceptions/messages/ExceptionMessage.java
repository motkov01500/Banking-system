package eu.deltasource.internship.bankingsystem.exceptions.messages;

public enum ExceptionMessage {

    ALREADY_ACCOUNT_WITH_SAME_IBAN_Exception("There is already account with the same IBAN."),
    NO_NEEDED_AMOUNT_TO_TRANSFER("There is no needed amount to transfer."),
    ANY_ACCOUNT_IS_NOT_CURRENT("You can not transfer money if any of given accounts is different from current account."),
    NO_NEEDED_AMOUNT_TO_WITHDRAW("There is no needed amount to withdraw.");
    private String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
