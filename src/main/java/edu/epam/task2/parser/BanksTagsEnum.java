package edu.epam.task2.parser;

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

    private static char UPPER_HYPHEN = '-';
    private static char LOWER_UNDERSCORE = '_';

    @Override
    public String toString() {
        return name().toLowerCase().replace(LOWER_UNDERSCORE, UPPER_HYPHEN);
    }
}