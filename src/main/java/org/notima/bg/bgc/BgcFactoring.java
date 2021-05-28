package org.notima.bg.bgc;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "factoring")
public class BgcFactoring {
    private String identifier;
    private String type;
    private String companyName;
    private List<BgcAccount> account;
    private String text;
    private BgcBankInformation bank;

    @XmlAttribute
    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @XmlAttribute
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlElement
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @XmlElement
    public List<BgcAccount> getAccount() {
        return account;
    }

    public void setAccount(List<BgcAccount> account) {
        this.account = account;
    }

    @XmlElement
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @XmlElement
    public BgcBankInformation getBank() {
        return bank;
    }

    public void setBank(BgcBankInformation bank) {
        this.bank = bank;
    }
}
