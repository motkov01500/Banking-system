package eu.deltasource.internship.bankingsystem.enums;

public enum Currency {

    USD("USD"),
    BGN("BGN");

    private String value;

    Currency(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
