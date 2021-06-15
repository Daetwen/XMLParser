package edu.epam.task2.entity;

public enum BanksTagsEnum {
    BANKS,
    PRIVATE_BANK,
    NATIONAL_BANK,
    NAME,
    COUNTRY,
    TYPE,
    DEPOSITOR,
    AMOUNT_ON_DEPOSIT,
    TIME_CONSTRAINTS,
    CREDIT_RATE,
    MORTGAGE_RATE,
    REFINANCING_RATE;

    private char UPPER_HYPHEN = '-';
    private char LOWER_UNDERSCORE = '_';

    @Override
    public String toString() {
        return name().toLowerCase().replace(LOWER_UNDERSCORE, UPPER_HYPHEN);
    }
}
