package edu.epam.task2.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import edu.epam.task2.entity.Banks;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.Set;

public class BanksHandler extends DefaultHandler {

    private static Logger logger = LogManager.getLogger();
    private Set<Banks> banks;

    public Set<Banks> getBanks() {
        return banks;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {

    }

    @Override
    public void endElement(String uri, String localName, String qName) {

    }

    @Override
    public void characters(char[] ch, int start, int length) {

    }
}
