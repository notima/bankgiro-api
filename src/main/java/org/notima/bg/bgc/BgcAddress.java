package org.notima.bg.bgc;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "address")
public class BgcAddress {
    private String name;
    private String reference;
    private String street;
    private String postOfficeBox;
    private BgcAddressTown town;
    private BgcAddressCountry country;

    @XmlElement
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @XmlElement
    public String getReference() {
        return reference;
    }
    public void setReference(String reference) {
        this.reference = reference;
    }

    @XmlElement
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }

    @XmlElement
    public String getPostOfficeBox() {
        return postOfficeBox;
    }
    public void setPostOfficeBox(String postOfficeBox) {
        this.postOfficeBox = postOfficeBox;
    }

    @XmlElement
    public BgcAddressTown getTown() {
        return town;
    }
    public void setTown(BgcAddressTown town) {
        this.town = town;
    }

    @XmlElement
    public BgcAddressCountry getCountry() {
        return country;
    }
    public void setCountry(BgcAddressCountry country) {
        this.country = country;
    }
}
