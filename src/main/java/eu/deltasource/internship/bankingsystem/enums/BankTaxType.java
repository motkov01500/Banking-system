package eu.deltasource.internship.bankingsystem.enums;

public enum BankTaxType {

    DEPOSIT("deposit"),
    TRANSFER_SAME_BANK("transfer"),
    TRANSFER_DIFFERENT_BANK("transfer"),
    WITHDRAW("withdraw");

    private String transactionType;

    BankTaxType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getValue() {
        return transactionType;
    }
}
