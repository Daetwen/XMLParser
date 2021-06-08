package edu.epam.task2.entity;

import java.time.YearMonth;

public class NationalBank extends Banks {
    private int refinancingRate;

    public NationalBank(String name,
                       Country country,
                       DepositType type,
                       String depositor,
                       int amountOnDeposit,
                       int profitability,
                       YearMonth timeConstraints,
                       String accountID,
                       int refinancingRate,
                       BankType bankType) {
        super(name, country, type, depositor, amountOnDeposit, timeConstraints, accountID, profitability, bankType);
        this.refinancingRate = refinancingRate;
    }

    public int getNationalBankName() {
        return refinancingRate;
    }

    public void setNationalBankName(int nationalBankName) {
        this.refinancingRate = refinancingRate;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(super.toString());
        result.append("; refinancing-rate = ").append(this.refinancingRate);
        return result.toString();
    }
}
