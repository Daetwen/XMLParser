package edu.epam.task2.validator;

import org.testng.Assert;
import org.testng.annotations.Test;

public class XMLValidatorTest {

    private String FILE_PATH_SCHEMA = "src/main/resources/schema.xsd";
    private String FILE_PATH_VALID = "src/test/resources/valid.xml";
    private String FILE_PATH_INVALID1 = "src/test/resources/invalid1.xml";
    private String FILE_PATH_INVALID2 = "src/test/resources/invalid2.xml";
    private String FILE_PATH_INVALID3 = "src/test/resources/invalid3.xml";
    private String FILE_PATH_INVALID4 = "src/test/resources/invalid4.xml";

    @Test
    public void validateXMLPositiveTest() {
        boolean actual = XMLValidator.validateXML(FILE_PATH_VALID, FILE_PATH_SCHEMA);
        Assert.assertTrue(actual);
    }

    @Test
    public void validateXMLNegativeTest1() {
        boolean actual = XMLValidator.validateXML(FILE_PATH_INVALID1, FILE_PATH_SCHEMA);
        Assert.assertFalse(actual);
    }

    @Test
    public void validateXMLNegativeTest2() {
        boolean actual = XMLValidator.validateXML(FILE_PATH_INVALID2, FILE_PATH_SCHEMA);
        Assert.assertFalse(actual);
    }

    @Test
    public void validateXMLNegativeTest3() {
        boolean actual = XMLValidator.validateXML(FILE_PATH_INVALID3, FILE_PATH_SCHEMA);
        Assert.assertFalse(actual);
    }

    @Test
    public void validateXMLNegativeTest4() {
        boolean actual = XMLValidator.validateXML(FILE_PATH_INVALID4, FILE_PATH_SCHEMA);
        Assert.assertFalse(actual);
    }
}
