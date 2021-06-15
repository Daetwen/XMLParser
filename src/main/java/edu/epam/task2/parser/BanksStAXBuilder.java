package edu.epam.task2.parser;

import edu.epam.task2.entity.*;
import edu.epam.task2.exception.BanksException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.YearMonth;
import java.util.HashSet;
import java.util.Set;

public class BanksStAXBuilder extends AbstractBankBuilder {
    private static Logger logger = LogManager.getLogger();
    private Set<Banks> banks;
    private XMLInputFactory factory;
    private char UPPER_HYPHEN = '-';
    private char LOWER_UNDERSCORE = '_';

    public BanksStAXBuilder() {
        banks = new HashSet<>();
        factory = XMLInputFactory.newInstance();
    }

    public Set<Banks> getBanks() {
        return banks;
    }

    @Override
    public void buildSetBanks(String filename) throws BanksException {
        FileInputStream inputStream = null;
        XMLStreamReader reader = null;
        String name;
        try {
            inputStream = new FileInputStream(new File(filename));
            reader = factory.createXMLStreamReader(inputStream);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();

                    if (name.equals(BanksTagsEnum.PRIVATE_BANK.toString())) {
                        PrivateBank privateBank = new PrivateBank();
                        buildBank(privateBank, reader);
                        banks.add(privateBank);
                    }

                    if (name.equals(BanksTagsEnum.NATIONAL_BANK.toString())) {
                        NationalBank nationalBank = new NationalBank();
                        buildBank(nationalBank, reader);
                        banks.add(nationalBank);
                    }
                }
            }
        } catch (XMLStreamException | IOException e) {
            logger.error("Error in StAX builder: " + e);
            throw new BanksException("Error in StAX builder: " + e);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException exception) {
                logger.error("Impossible close file in StAX builder: " + exception);
                throw new BanksException("Impossible close file in StAX builder: " + exception);
            }
        }
    }

    private void buildBank(Banks bank, XMLStreamReader reader) throws XMLStreamException {
        bank.setAccountID(reader.getAttributeValue(null, BanksAttributeEnum.ID.toString()));
        String profitability = reader.getAttributeValue(null, BanksAttributeEnum.PROFITABILITY.toString());
        String bankType = reader.getAttributeValue(null, BanksAttributeEnum.BANK_TYPE.toString());
        if (profitability != null) {
            bank.setProfitability(Integer.parseInt(profitability));
        }
        if (bankType != null) {
            bank.setBankType(BankType.valueOf(bankType));
        }
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    buildBankText(bank, reader, name);
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (name.equals(BanksTagsEnum.PRIVATE_BANK.toString()) ||
                            name.equals(BanksTagsEnum.NATIONAL_BANK.toString()))
                    return;
                    break;
            }
        }
    }

    private void buildBankText(Banks bank, XMLStreamReader reader, String name) throws XMLStreamException {
        name = name.trim().replace(UPPER_HYPHEN, LOWER_UNDERSCORE).toUpperCase();
        BanksTagsEnum currentTag = BanksTagsEnum.valueOf(name);
        switch (currentTag) {
            case NAME:
                bank.setName(getXMLText(reader));
                break;
            case COUNTRY:
                bank.setCountry(Country.valueOf(getXMLText(reader)));
                break;
            case TYPE:
                bank.setType(DepositType.valueOf(getXMLText(reader)));
                break;
            case DEPOSITOR:
                bank.setDepositor(getXMLText(reader));
                break;
            case AMOUNT_ON_DEPOSIT:
                bank.setAmountOnDeposit(Long.valueOf(getXMLText(reader)));
                break;
            case TIME_CONSTRAINTS:
                bank.setTimeConstraints(YearMonth.parse(getXMLText(reader)));
                break;
            case CREDIT_RATE: {
                PrivateBank privateBank = (PrivateBank) bank;
                privateBank.setCreditRate(Integer.parseInt(getXMLText(reader)));
                break;
            }
            case MORTGAGE_RATE: {
                PrivateBank privateBank = (PrivateBank) bank;
                privateBank.setMortgageRate(Integer.parseInt(getXMLText(reader)));
                break;
            }
            case REFINANCING_RATE: {
                NationalBank nationalBank = (NationalBank) bank;
                nationalBank.setRefinancingRate(Integer.parseInt(getXMLText(reader)));
                break;
            }
            default:
                throw new EnumConstantNotPresentException(
                        currentTag.getDeclaringClass(),
                        currentTag.name());
        }
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}
