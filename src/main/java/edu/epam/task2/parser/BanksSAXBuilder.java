package edu.epam.task2.parser;

import edu.epam.task2.entity.Banks;
import edu.epam.task2.exception.BanksException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.Set;

public class BanksSAXBuilder extends AbstractBankBuilder {

    private static Logger logger = LogManager.getLogger();
    private Set<Banks> banks;
    private BanksHandler banksHandler = new BanksHandler();
    private XMLReader reader;

    public BanksSAXBuilder() throws BanksException {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            reader = parser.getXMLReader();
            reader.setContentHandler(banksHandler);
        } catch (ParserConfigurationException | SAXException e) {
            logger.error("Error in SAX parser: " + e);
            throw new BanksException("Error in SAX parser: " + e);
        }
    }

    public Set<Banks> getBanks() {
        return this.banks;
    }

    @Override
    public void buildSetBanks(String filename) throws BanksException {
        try {
            reader.parse(filename);
        } catch (SAXException e) {
            logger.error("Error in SAX parser/builder: " + e);
            throw new BanksException("Error in SAX parser/builder: " + e);
        } catch (IOException e) {
            logger.error("Error in I/O stream in SAX parser/builder: " + e);
            throw new BanksException("Error in I/O stream in SAX parser/builder: " + e);
        }
        banks = banksHandler.getBanks();
    }
}
