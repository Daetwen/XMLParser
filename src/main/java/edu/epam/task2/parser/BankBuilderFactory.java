package edu.epam.task2.parser;

import edu.epam.task2.exception.BanksException;

public class BankBuilderFactory {
    private enum TypeParser {
        SAX, STAX, DOM
    }
    public static AbstractBankBuilder createBankBuilder(String typeParser) throws BanksException {
        TypeParser type = TypeParser.valueOf(typeParser.toUpperCase());
        switch (type) {
            case DOM:
                return new BanksDOMBuilder();
            case STAX:
                return new BanksStAXBuilder();
            case SAX:
                return new BanksSAXBuilder();
            default:
                throw new EnumConstantNotPresentException (type.getDeclaringClass(), type.name());
        }
    }
}
