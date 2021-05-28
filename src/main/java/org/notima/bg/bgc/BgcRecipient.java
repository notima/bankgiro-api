package org.notima.bg.bgc;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "recipient")
public class BgcRecipient {
    private String id;
    private String name;
    private BgcVATDetails vatDetails;
    private BgcAddress postalAddress;
    private BgcContact contact;
    private String gs1;

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
    public BgcVATDetails getVatDetails() {
        return vatDetails;
    }

    public void setVatDetails(BgcVATDetails vatDetails) {
        this.vatDetails = vatDetails;
    }

    @XmlElement
    public BgcAddress getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(BgcAddress postalAddress) {
        this.postalAddress = postalAddress;
    }

    @XmlElement
    public BgcContact getContact() {
        return contact;
    }

    public void setContact(BgcContact contact) {
        this.contact = contact;
    }

    @XmlElement
    public String getGs1() {
        return gs1;
    }

    public void setGs1(String gs1) {
        this.gs1 = gs1;
    }
}
