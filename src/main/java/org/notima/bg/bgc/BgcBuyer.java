package org.notima.bg.bgc;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "buyer")
@XmlType(propOrder = { 
    "id", 
    "sellerID", 
    "name", 
    "vatDetails", 
    "postalAddress" ,
    "contact",
    "recipient",
    "bankCustomer",
    "gs1"
})
public class BgcBuyer {
    private String id;
    private String sellerID;
    private String name;
    private BgcVATDetails vatDetails;
    private BgcAddress postalAddress;
    private BgcContact contact;
    private BgcRecipient recipient;
    private BgcBankCustomer bankCustomer;
    private String gs1;

    @XmlAttribute
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlAttribute
    public String getSellerID() {
        return sellerID;
    }

    public void setSellerID(String sellerID) {
        this.sellerID = sellerID;
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
    public BgcRecipient getRecipient() {
        return recipient;
    }

    public void setRecipient(BgcRecipient recipient) {
        this.recipient = recipient;
    }

    @XmlElement
    public BgcBankCustomer getBankCustomer() {
        return bankCustomer;
    }

    public void setBankCustomer(BgcBankCustomer bankCustomer) {
        this.bankCustomer = bankCustomer;
    }

    @XmlElement
    public String getGs1() {
        return gs1;
    }

    public void setGs1(String gs1) {
        this.gs1 = gs1;
    }
    
}
