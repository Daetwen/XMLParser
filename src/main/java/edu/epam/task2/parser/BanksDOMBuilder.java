package edu.epam.task2.parser;

import edu.epam.task2.entity.*;
import edu.epam.task2.exception.BanksException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.YearMonth;
import java.util.HashSet;
import java.util.Set;

public class BanksDOMBuilder extends AbstractBankBuilder {

    private static Logger logger = LogManager.getLogger();
    private Set<Banks> banks;
    private DocumentBuilder documentBuilder;
    private String privateBankTagName = BanksTagsEnum.PRIVATE_BANK.toString();
    private String nationalBankTagName = BanksTagsEnum.NATIONAL_BANK.toString();

    public BanksDOMBuilder() throws BanksException {
        this.banks = new HashSet<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            logger.error("Error in DOM builder: " + e);
            throw new BanksException("Error in DOM builder: " + e);
        }
    }

    public Set<Banks> getBanks() {
        return this.banks;
    }

    @Override
    public void buildSetBanks(String filename) throws BanksException {
        Document document;
        try {
            document = documentBuilder.parse(filename);
            Element root = document.getDocumentElement();
            NodeList privateBankList = root.getElementsByTagName(privateBankTagName);
            NodeList nationalBankList = root.getElementsByTagName(nationalBankTagName);
            for (int i = 0; i < privateBankList.getLength(); i++) {
                Element privateBankElement = (Element) privateBankList.item(i);
                PrivateBank privateBank = new PrivateBank();
                buildBank(privateBankElement, privateBank, privateBankTagName);
                banks.add(privateBank);
            }
            for (int i = 0; i < nationalBankList.getLength(); i++) {
                Element nationalBankElement = (Element) nationalBankList.item(i);
                NationalBank nationalBank = new NationalBank();
                buildBank(nationalBankElement, nationalBank, nationalBankTagName);
                banks.add(nationalBank);
            }
        } catch (IOException | SAXException e) {
            logger.error("Error in build in DOM builder: " + e);
            throw new BanksException("Error in build in DOM builder: " + e);
        }
    }

    private void buildBank(Element bankElement, Banks bank, String tagName) {
        bank.setAccountID(bankElement.getAttribute(BanksAttributeEnum.ID.toString()));
        if (!bankElement.getAttribute(BanksAttributeEnum.PROFITABILITY.toString()).isEmpty()) {
            bank.setProfitability(
                    Integer.parseInt(
                    bankElement.getAttribute(
                    BanksAttributeEnum.PROFITABILITY.toString())));
        }
        if (!bankElement.getAttribute(BanksAttributeEnum.BANK_TYPE.toString()).isEmpty()) {
            bank.setBankType(
                    BankType.valueOf(
                    bankElement.getAttribute(
                    BanksAttributeEnum.BANK_TYPE.toString())));
        }

        bank.setName(
                getElementTextContent(bankElement, BanksTagsEnum.NAME.toString()));
        bank.setCountry(Country.valueOf(
                getElementTextContent(bankElement, BanksTagsEnum.COUNTRY.toString())));
        bank.setType(DepositType.valueOf(
                getElementTextContent(bankElement, BanksTagsEnum.TYPE.toString())));
        bank.setDepositor(
                getElementTextContent(bankElement, BanksTagsEnum.DEPOSITOR.toString()));
        bank.setAmountOnDeposit(Long.valueOf(
                getElementTextContent(bankElement, BanksTagsEnum.AMOUNT_ON_DEPOSIT.toString())));
        bank.setTimeConstraints(YearMonth.parse(
                getElementTextContent(bankElement, BanksTagsEnum.TIME_CONSTRAINTS.toString())));

        if (tagName.equals(privateBankTagName)) {
            PrivateBank privateBank = (PrivateBank) bank;
            privateBank.setCreditRate(Integer.parseInt(
                    getElementTextContent(bankElement, BanksTagsEnum.CREDIT_RATE.toString())));
            privateBank.setMortgageRate(Integer.parseInt(
                    getElementTextContent(bankElement, BanksTagsEnum.MORTGAGE_RATE.toString())));
        } else {
            NationalBank nationalBank = (NationalBank) bank;
            nationalBank.setRefinancingRate(Integer.parseInt(
                    getElementTextContent(bankElement, BanksTagsEnum.REFINANCING_RATE.toString())));
        }
    }

    private String getElementTextContent(Element element, String elementName) {
        NodeList nodeList = element.getElementsByTagName(elementName);
        Node node = nodeList.item(0);
        return node.getTextContent();
    }
}
