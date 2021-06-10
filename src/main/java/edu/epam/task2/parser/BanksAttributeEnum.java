package edu.epam.task2.parser;

public enum BanksAttributeEnum {
    ID,
    PROFITABILITY,
    BANK_TYPE;

    private static char UPPER_HYPHEN = '-';
    private static char LOWER_UNDERSCORE = '_';

    @Override
    public String toString() {
        return name().toLowerCase().replace(LOWER_UNDERSCORE, UPPER_HYPHEN);
    }
}
