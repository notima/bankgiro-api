package org.notima.bg.bgc;

import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "agreementReference")
public class BgcAgreementReference {
    private String id;
    private String rowID;
    private String buyerID;
    private Date date;
    private String URL;
    private String text;
    private double agreementRate;
    private BgcAddress objectAddress;
    private BgcAmount capitalAmount;
    private BgcAddress creditorAddress;

    @XmlAttribute
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlAttribute
    public String getRowID() {
        return rowID;
    }

    public void setRowID(String rowID) {
        this.rowID = rowID;
    }

    @XmlAttribute
    public String getBuyerID() {
        return buyerID;
    }

    public void setBuyerID(String buyerID) {
        this.buyerID = buyerID;
    }

    @XmlElement
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @XmlElement
    public String getURL() {
        return URL;
    }

    public void setURL(String uRL) {
        URL = uRL;
    }

    @XmlElement
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @XmlElement
    public double getAgreementRate() {
        return agreementRate;
    }

    public void setAgreementRate(double agreementRate) {
        this.agreementRate = agreementRate;
    }

    @XmlElement
    public BgcAddress getObjectAddress() {
        return objectAddress;
    }

    public void setObjectAddress(BgcAddress objectAddress) {
        this.objectAddress = objectAddress;
    }

    @XmlElement
    public BgcAmount getCapitalAmount() {
        return capitalAmount;
    }

    public void setCapitalAmount(BgcAmount capitalAmount) {
        this.capitalAmount = capitalAmount;
    }

    @XmlElement
    public BgcAddress getCreditorAddress() {
        return creditorAddress;
    }

    public void setCreditorAddress(BgcAddress creditorAddress) {
        this.creditorAddress = creditorAddress;
    }
}
