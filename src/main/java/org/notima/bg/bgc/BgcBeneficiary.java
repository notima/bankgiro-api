package org.notima.bg.bgc;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "beneficiary")
@XmlType(propOrder = {
    "bic",
    "id",
    "name",
    "address",
    "account"
})
public class BgcBeneficiary {
    private String bic;
    private String id;
    private String name;
    private BgcAddress address;
    private BgcAccount account;

    @XmlAttribute
    public String getBic() {
        return bic;
    }
    public void setBic(String bic) {
        this.bic = bic;
    }

    @XmlAttribute
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    @XmlElement
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @XmlElement
    public BgcAddress getAddress() {
        return address;
    }
    public void setAddress(BgcAddress address) {
        this.address = address;
    }

    @XmlElement
    public BgcAccount getAccount() {
        return account;
    }
    public void setAccount(BgcAccount account) {
        this.account = account;
    }
}
