package edu.epam.task2.parser;

import edu.epam.task2.entity.Banks;
import edu.epam.task2.exception.BanksException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.Set;

public class BanksSAXBuilder {

    private static Logger logger = LogManager.getLogger();
    private Set<Banks> banks;
    private BanksHandler banksHandler;
    private XMLReader reader;

    public BanksSAXBuilder() {
        banksHandler = new BanksHandler();
        try {
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(banksHandler);
        } catch (SAXException e) {
            logger.error("Error in SAX parser: " + e);
        }
    }

    public Set<Banks> getBanks() {
        return this.banks;
    }

    public void buildSetBanks(String filename) {
        try {
            reader.parse(filename);
        } catch (SAXException e) {
            logger.error("Error in SAX parser/builder: " + e);
        } catch (IOException e) {
            logger.error("Error in I/O stream in SAX parser/builder: " + e);
        }
        banks = banksHandler.getBanks();
    }

    public boolean parseSAXBanks() throws BanksException {
        boolean result = false;
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            reader = parser.getXMLReader();
            reader.setContentHandler(banksHandler);
            result = true;
        } catch (SAXException | ParserConfigurationException e) {
            logger.error("Error in SAX parsing of file.");
            throw new BanksException("Error in SAX parsing of file.");
        }
        return result;
    }
}
