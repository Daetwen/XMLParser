package edu.epam.task2.entity;

import java.time.YearMonth;

public class Banks {

    private String name;
    private Country country;
    private DepositType type;
    private String depositor;
    private int amountOnDeposit;
    private int profitability;
    private YearMonth timeConstraints;
    private String accountID;

    public Banks() {
        this.accountID = "";
    }

    public Banks(String name,
                 Country country,
                 DepositType type,
                 String depositor,
                 int amountOnDeposit,
                 int profitability,
                 YearMonth timeConstraints,
                 String accountID) {
        this.name = name;
        this.country = country;
        this.type = type;
        this.depositor = depositor;
        this.amountOnDeposit = amountOnDeposit;
        this.profitability = profitability;
        this.timeConstraints = timeConstraints;
        this.accountID = accountID;
    }

    public String getName() {
        return this.name;
    }

    public Country getCountry() {
        return this.country;
    }

    public DepositType getType() {
        return this.type;
    }

    public String getDepositor() {
        return this.depositor;
    }

    public int getAmountOnDeposit() {
        return this.amountOnDeposit;
    }

    public int getProfitability() {
        return this.profitability;
    }

    public YearMonth getTimeConstraints() {
        return this.timeConstraints;
    }

    public String getAccountID() {
        return this.accountID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public void setType(DepositType type) {
        this.type = type;
    }

    public void setDepositor(String depositor) {
        this.depositor = depositor;
    }

    public void setAmountOnDeposit(int amountOnDeposit) {
        this.amountOnDeposit = amountOnDeposit;
    }

    public void setProfitability(int profitability) {
        this.profitability = profitability;
    }

    public void setTimeConstraints(YearMonth timeConstraints) {
        this.timeConstraints = timeConstraints;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Bank: ");
        result.append("name = ").append(this.name);
        result.append("; accountID = ").append(this.accountID);
        result.append("; country = ").append(this.country);
        result.append("; deposit type = ").append(this.type);
        result.append("; depositor = ").append(this.depositor);
        result.append("; amount on deposit = ").append(this.amountOnDeposit);
        result.append("; profitability = ").append(this.profitability);
        result.append("; time constraints = ").append(this.timeConstraints);
        return result.toString();
    }
}
