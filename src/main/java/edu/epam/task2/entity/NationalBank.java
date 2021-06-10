package edu.epam.task2.entity;

import java.time.YearMonth;

public class NationalBank extends Banks {
    private int refinancingRate;

    public NationalBank() {
        refinancingRate = 0;
    }

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

    public int getRefinancingRate() {
        return refinancingRate;
    }

    public void setRefinancingRate(int refinancingRate) {
        this.refinancingRate = refinancingRate;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(super.toString());
        result.append("; refinancing-rate = ").append(this.refinancingRate);
        result.append("\n");
        return result.toString();
    }
}
