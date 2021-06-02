package org.notima.bg.einvoice;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "seller")
@XmlType(propOrder = { 
    "id", 
    "buyerID", 
    "bgnr", 
    "name", 
    "vatDetails", 
    "postalAddress" ,
    "salesContact",
    "mainContact",
    "accounts",
    "sender",
    "bank",
    "gs1"
})
public class BgcSeller {
    private String id;
    private String buyerID;
    private String bgnr;
    private String bgcID;
    private String name;
    private BgcVATDetails vatDetails;
    private BgcAddress postalAddress;
    private BgcContact salesContact;
    private BgcContact mainContact;
    private BgcAccounts accounts;
    private BgcInvoiceSender sender;
    private BgcBankInformation bank;
    private String gs1;

    @XmlAttribute
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlAttribute
    public String getBuyerID() {
        return buyerID;
    }

    public void setBuyerID(String buyerID) {
        this.buyerID = buyerID;
    }

    @XmlAttribute
    public String getBgnr() {
        return bgnr;
    }

    public void setBgnr(String bgnr) {
        this.bgnr = bgnr;
    }

    @XmlAttribute
    public String getBgcID() {
        return bgcID;
    }

    public void setBgcID(String bgcID) {
        this.bgcID = bgcID;
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
    public BgcContact getSalesContact() {
        return salesContact;
    }

    public void setSalesContact(BgcContact salesContact) {
        this.salesContact = salesContact;
    }

    @XmlElement
    public BgcContact getMainContact() {
        return mainContact;
    }

    public void setMainContact(BgcContact mainContact) {
        this.mainContact = mainContact;
    }

    @XmlElement
    public BgcAccounts getAccounts() {
        return accounts;
    }

    public void setAccounts(BgcAccounts accounts) {
        this.accounts = accounts;
    }

    @XmlElement
    public BgcInvoiceSender getSender() {
        return sender;
    }

    public void setSender(BgcInvoiceSender sender) {
        this.sender = sender;
    }

    @XmlElement
    public BgcBankInformation getBank() {
        return bank;
    }

    public void setBank(BgcBankInformation bank) {
        this.bank = bank;
    }

    @XmlElement
    public String getGs1() {
        return gs1;
    }

    public void setGs1(String gs1) {
        this.gs1 = gs1;
    }
}
