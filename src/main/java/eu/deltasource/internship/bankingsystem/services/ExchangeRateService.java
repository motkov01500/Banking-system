package eu.deltasource.internship.bankingsystem.services;

import eu.deltasource.internship.bankingsystem.models.BankAccount;

import java.math.BigDecimal;

public class ExchangeRateService {

    private BigDecimal USD_TO_BGN = new BigDecimal("1.81");
    private BigDecimal BGN_TO_USD = new BigDecimal("0.55");

    private BigDecimal getExchangeRateFromBGNToUSD() {
        return BGN_TO_USD;
    }

    private BigDecimal getExchangeRateFromUSDToBGN() {
        return USD_TO_BGN;
    }

    public BigDecimal getExchangeRate(BankAccount sourceAccount, BankAccount targetAccount) {
        String exchangeRate = String.format("%s_TO_%s", sourceAccount.getCurrency().getValue(), targetAccount.getCurrency().getValue());

        if (exchangeRate.equals("USD_TO_BGN"))
            return getExchangeRateFromUSDToBGN();

        if (exchangeRate.equals("BGN_TO_USD"))
            return getExchangeRateFromBGNToUSD();

        return new BigDecimal("1");
    }
}
