package edu.epam.task2.parser;

import edu.epam.task2.entity.*;
import edu.epam.task2.exception.BanksException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.YearMonth;
import java.util.HashSet;
import java.util.Set;

public class BankBuilderFactoryTest {

    private Set<Banks> expectedSet;
    private String FILE_PATH_VALID = "src/test/resources/valid.xml";

    @BeforeClass
    public void setUp() {
        expectedSet = new HashSet<>();

        PrivateBank privateBank1 = new PrivateBank(
                "Belarusbank",
                Country.BELARUS,
                DepositType.CUMULATIVE,
                "Ivanov I.I.",
                10000,
                YearMonth.parse("2021-09"),
                "ad01b1",
                10,
                BankType.COMMERCIAL,
                19,
                25);

        PrivateBank privateBank2 = new PrivateBank(
                "Alfa-Bank",
                Country.RUSSIA,
                DepositType.SAVINGS,
                "Petrov P.P.",
                50000,
                YearMonth.parse("2022-07"),
                "ad02b1",
                0,
                BankType.SAVINGS,
                15,
                20);

        NationalBank nationalBank1 = new NationalBank(
                "Belagroprombank",
                Country.BELARUS,
                DepositType.CUMULATIVE,
                "Sidorov S.V.",
                8000,
                YearMonth.parse("2022-01"),
                "nb01b1",
                3,
                BankType.COMMERCIAL,
                9);

        NationalBank nationalBank2 = new NationalBank(
                "Сentral bank",
                Country.RUSSIA,
                DepositType.METAL,
                "Petrov S.V.",
                8000000000L,
                YearMonth.parse("2026-01"),
                "nb02b1",
                5,
                BankType.EMISSIVE,
                8);

        expectedSet.add(privateBank1);
        expectedSet.add(privateBank2);
        expectedSet.add(nationalBank1);
        expectedSet.add(nationalBank2);
    }

    @DataProvider(name = "builder-provider")
    public Object[][] builderDataProvider() throws BanksException {
        return new Object[][] {
                { BankBuilderFactory.createBankBuilder("DOM")},
                { BankBuilderFactory.createBankBuilder("SAX")},
                { BankBuilderFactory.createBankBuilder("STAX")}
        };
    }

    @Test(dataProvider = "builder-provider")
    public void testBuildDevices(AbstractBankBuilder builder) throws BanksException {
        builder.buildSetBanks(FILE_PATH_VALID);
        Set<Banks> actualSet = builder.getBanks();
        Assert.assertEquals(actualSet, expectedSet);
    }

}
