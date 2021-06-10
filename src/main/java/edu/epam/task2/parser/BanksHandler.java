package edu.epam.task2.parser;

import edu.epam.task2.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.time.YearMonth;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class BanksHandler extends DefaultHandler {

    private static Logger logger = LogManager.getLogger();
    private Set<Banks> banks;
    private Banks currentBank;
    private BanksTagsEnum currentEnum;
    private EnumSet<BanksTagsEnum> withText;
    private String privateBankTagName = BanksTagsEnum.PRIVATE_BANK.toString();
    private String nationalBankTagName = BanksTagsEnum.NATIONAL_BANK.toString();
    private String attributeID = BanksAttributeEnum.ID.toString();
    private String attributeProfitability = BanksAttributeEnum.PROFITABILITY.toString();
    private String attributeBankType = BanksAttributeEnum.BANK_TYPE.toString();
    private char UPPER_HYPHEN = '-';
    private char LOWER_UNDERSCORE = '_';

    public BanksHandler() {
        banks = new HashSet<>();
        withText = EnumSet.range(BanksTagsEnum.NAME,BanksTagsEnum.REFINANCING_RATE);
    }

    public Set<Banks> getBanks() {
        return banks;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (privateBankTagName.equals(qName) || nationalBankTagName.equals(qName)) {
            if (privateBankTagName.equals(qName)) {
                currentBank = new PrivateBank();
            } else {
                currentBank = new NationalBank();
            }
            for (int i = 0; i < attributes.getLength(); i++) {
                String currentAttribute = attributes.getQName(i);
                if (currentAttribute.equals(attributeID)) {
                    currentBank.setAccountID(attributes.getValue(i));
                } else if (currentAttribute.equals(attributeProfitability)) {
                    currentBank.setProfitability(Integer.parseInt(attributes.getValue(i)));
                } else if (currentAttribute.equals(attributeBankType)) {
                    currentBank.setBankType(BankType.valueOf(attributes.getValue(i).toUpperCase()));
                }
            }
        } else {
            BanksTagsEnum localBanksTagsEnum = BanksTagsEnum.valueOf(
                    qName
                    .strip()
                    .replace(UPPER_HYPHEN, LOWER_UNDERSCORE)
                    .toUpperCase());
            if (withText.contains(localBanksTagsEnum)) {
                currentEnum = localBanksTagsEnum;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (privateBankTagName.equals(qName) || nationalBankTagName.equals(qName)) {
            banks.add(currentBank);
            currentBank = null;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String elementText = new String(ch, start, length).trim();
        if (currentEnum != null) {
            switch (currentEnum) {
                case NAME:
                    currentBank.setName(elementText);
                    break;
                case COUNTRY:
                    currentBank.setCountry(Country.valueOf(elementText));
                    break;
                case TYPE:
                    currentBank.setType(DepositType.valueOf(elementText));
                    break;
                case DEPOSITOR:
                    currentBank.setDepositor(elementText);
                    break;
                case AMOUNT_ON_DEPOSIT:
                    currentBank.setAmountOnDeposit(Long.valueOf(elementText));
                    break;
                case TIME_CONSTRAINTS:
                    currentBank.setTimeConstraints(YearMonth.parse(elementText));
                    break;
                case CREDIT_RATE: {
                    PrivateBank privateBank = (PrivateBank) currentBank;
                    privateBank.setCreditRate(Integer.parseInt(elementText));
                    break;
                }
                case MORTGAGE_RATE: {
                    PrivateBank privateBank = (PrivateBank) currentBank;
                    privateBank.setMortgageRate(Integer.parseInt(elementText));
                    break;
                }
                case REFINANCING_RATE: {
                    NationalBank nationalBank = (NationalBank) currentBank;
                    nationalBank.setRefinancingRate(Integer.parseInt(elementText));
                    break;
                }
                default:
                    throw new EnumConstantNotPresentException(
                        currentEnum.getDeclaringClass(),
                        currentEnum.name());
            }
        }
        currentEnum = null;
    }
}
