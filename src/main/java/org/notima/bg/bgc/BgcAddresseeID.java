package org.notima.bg.bgc;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlType(name = "addresseeID")
public class BgcAddresseeID {
    private String adresseeID;
    private String type;

    @XmlValue
    public String getAdresseeID() {
        return adresseeID;
    }

    public void setAdresseeID(String adresseeID) {
        this.adresseeID = adresseeID;
    }

    @XmlAttribute
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
