package edu.epam.task2.entity;

import java.time.YearMonth;

public class PrivateBank extends Banks {
    private PrivateBankNames privateBankName;

    public PrivateBank(String name,
                       Country country,
                       DepositType type,
                       String depositor,
                       int amountOnDeposit,
                       int profitability,
                       YearMonth timeConstraints,
                       String accountID,
                       PrivateBankNames privateBankNames) {
        super(name, country, type, depositor, amountOnDeposit, profitability, timeConstraints, accountID);
        this.privateBankName = privateBankNames;
    }

    public PrivateBankNames getPrivateBankNames() {
        return privateBankName;
    }

    public void setPrivateBankNames(PrivateBankNames privateBankNames) {
        this.privateBankName = privateBankNames;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(super.toString());
        result.append("; PrivateBankNames = ").append(this.privateBankName);
        return result.toString();
    }
}
