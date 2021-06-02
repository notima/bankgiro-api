package org.notima.bg.einvoice;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "bankCustomer")
@XmlType(propOrder = { 
    "identifier", 
    "bank", 
    "changeableAmount"
})
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
