package edu.epam.task2.entity;

import java.time.YearMonth;
import java.util.Objects;

public class PrivateBank extends Banks {
    private int creditRate;
    private int mortgageRate;

    public PrivateBank() {
        creditRate = 0;
        mortgageRate = 0;
    }

    public PrivateBank(String name,
                       Country country,
                       DepositType type,
                       String depositor,
                       int amountOnDeposit,
                       YearMonth timeConstraints,
                       String accountID,
                       int profitability,
                       BankType bankType,
                       int creditRate,
                       int mortgageRate) {
        super(name, country, type, depositor, amountOnDeposit, timeConstraints, accountID, profitability, bankType);
        this.creditRate = creditRate;
        this.mortgageRate = mortgageRate;
    }

    public int getCreditRate() {
        return creditRate;
    }

    public void setCreditRate(int creditRate) {
        this.creditRate = creditRate;
    }

    public int getMortgageRate() {
        return mortgageRate;
    }

    public void setMortgageRate(int mortgageRate) {
        this.mortgageRate = mortgageRate;
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
        PrivateBank that = (PrivateBank) object;
        return creditRate == that.creditRate && mortgageRate == that.mortgageRate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), creditRate, mortgageRate);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(super.toString());
        result.append("; credit-rate = ").append(this.creditRate);
        result.append("; mortgage-rate = ").append(this.mortgageRate);
        result.append("\n");
        return result.toString();
    }
}
