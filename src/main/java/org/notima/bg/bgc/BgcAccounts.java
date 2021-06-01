package org.notima.bg.bgc;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "accounts")
public class BgcAccounts {
    private List<BgcAccount> accounts;

    @XmlElement(name = "account")
    public List<BgcAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<BgcAccount> accounts) {
        this.accounts = accounts;
    }
}
