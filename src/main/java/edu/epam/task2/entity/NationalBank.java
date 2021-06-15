package edu.epam.task2.entity;

import java.time.YearMonth;
import java.util.Objects;

public class NationalBank extends Banks {
    private int refinancingRate;

    public NationalBank() {
        refinancingRate = 0;
    }

    public NationalBank(String name,
                       Country country,
                       DepositType type,
                       String depositor,
                       long amountOnDeposit,
                       YearMonth timeConstraints,
                       String accountID,
                       int profitability,
                       BankType bankType,
                       int refinancingRate) {
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
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        if (!super.equals(object)) {
            return false;
        }
        NationalBank that = (NationalBank) object;
        return refinancingRate == that.refinancingRate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), refinancingRate);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(super.toString());
        result.append("; refinancing-rate = ").append(this.refinancingRate);
        result.append("\n");
        return result.toString();
    }
}
