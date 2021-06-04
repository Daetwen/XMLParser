package edu.epam.task2.entity;

import java.time.YearMonth;

public class NationalBank extends Banks {
    private NationalBankNames nationalBankName;

    public NationalBank(String name,
                       Country country,
                       DepositType type,
                       String depositor,
                       int amountOnDeposit,
                       int profitability,
                       YearMonth timeConstraints,
                       String accountID,
                        NationalBankNames nationalBankName) {
        super(name, country, type, depositor, amountOnDeposit, profitability, timeConstraints, accountID);
        this.nationalBankName = nationalBankName;
    }

    public NationalBankNames getNationalBankName() {
        return nationalBankName;
    }

    public void setNationalBankName(NationalBankNames nationalBankName) {
        this.nationalBankName = nationalBankName;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(super.toString());
        result.append("; NationalBankNames = ").append(this.nationalBankName);
        return result.toString();
    }
}
