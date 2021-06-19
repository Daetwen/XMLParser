package edu.epam.task2.entity;

import java.time.YearMonth;
import java.util.Objects;

public abstract class Banks {

    private String name;
    private Country country;
    private DepositType type;
    private String depositor;
    private long amountOnDeposit;
    private YearMonth timeConstraints;
    private String accountID;
    private int profitability;
    private BankType bankType;

    private static String DEFAULT_ID = "";
    private static int DEFAULT_PROFITABILITY = 0;
    private static BankType DEFAULT_BANK_TYPE = BankType.COMMERCIAL;

    public Banks() {
        this.accountID = DEFAULT_ID;
        this.profitability = DEFAULT_PROFITABILITY;
        this.bankType = DEFAULT_BANK_TYPE;
    }

    public Banks(String name,
                 Country country,
                 DepositType type,
                 String depositor,
                 long amountOnDeposit,
                 YearMonth timeConstraints,
                 String accountID,
                 int profitability,
                 BankType bankType) {
        this.name = name;
        this.country = country;
        this.type = type;
        this.depositor = depositor;
        this.amountOnDeposit = amountOnDeposit;
        this.timeConstraints = timeConstraints;
        this.accountID = accountID;
        this.profitability = profitability;
        this.bankType = bankType;
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

    public long getAmountOnDeposit() {
        return this.amountOnDeposit;
    }

    public YearMonth getTimeConstraints() {
        return this.timeConstraints;
    }

    public String getAccountID() {
        return this.accountID;
    }

    public int getProfitability() {
        return this.profitability;
    }

    public BankType getBankType() {
        return bankType;
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

    public void setAmountOnDeposit(long amountOnDeposit) {
        this.amountOnDeposit = amountOnDeposit;
    }

    public void setTimeConstraints(YearMonth timeConstraints) {
        this.timeConstraints = timeConstraints;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public void setProfitability(int profitability) {
        this.profitability = profitability;
    }

    public void setBankType(BankType bankType) {
        this.bankType = bankType;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Banks banks = (Banks) object;
        return Objects.equals(this.name, banks.name) &&
                this.country == banks.country &&
                this.type == banks.type &&
                Objects.equals(this.depositor, banks.depositor) &&
                this.amountOnDeposit == banks.amountOnDeposit &&
                Objects.equals(this.timeConstraints, banks.timeConstraints) &&
                Objects.equals(this.accountID, banks.accountID) &&
                this.profitability == banks.profitability &&
                this.bankType == banks.bankType;
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;

        result = result * prime + name.hashCode();
        result = result * prime + country.hashCode();
        result = result * prime + type.hashCode();
        result = result * prime + depositor.hashCode();
        result = result * prime + Long.hashCode(amountOnDeposit);
        result = result * prime + timeConstraints.hashCode();
        result = result * prime + accountID.hashCode();
        result = result * prime + Integer.hashCode(profitability);
        result = result * prime + bankType.hashCode();

        return result;
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
        result.append("; bank type = ").append(this.bankType);
        return result.toString();
    }
}
