package org.notima.bg.bgc;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "bankCustomer")
public class BgcBankCustomer {
    private BgcIdentifier identifier;
    private BgcBankInformation bank;
    private boolean changeableAmount;

    @XmlElement
    public BgcIdentifier getIdentifier() {
        return identifier;
    }

    public void setIdentifier(BgcIdentifier identifier) {
        this.identifier = identifier;
    }

    @XmlElement
    public BgcBankInformation getBank() {
        return bank;
    }

    public void setBank(BgcBankInformation bank) {
        this.bank = bank;
    }

    @XmlElement
    public boolean isChangeableAmount() {
        return changeableAmount;
    }

    public void setChangeableAmount(boolean changeableAmount) {
        this.changeableAmount = changeableAmount;
    }
}
