package edu.epam.task2.parser;

import edu.epam.task2.entity.Banks;
import edu.epam.task2.exception.BanksException;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractBankBuilder {
    protected Set<Banks> banks;

    public AbstractBankBuilder() {
        banks = new HashSet<Banks>();
    }

    public AbstractBankBuilder(Set<Banks> banks) {
        this.banks = banks;
    }

    public Set<Banks> getBanks() {
        return this.banks;
    }

    abstract public void buildSetBanks(String filename) throws BanksException;
}
