package org.notima.bg.bgc;

import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "orderReference")
public class BgcOrderReference {
    private String id;
    private String buyerId;
    private String rowId;
    private Date date;
    private String URL;
    private String text;
    private Date buyerDate;
    private String buyerURL;
    private BgcQuantity quantity;
    private BgcQuantity confirmedQuantity;

    @XmlAttribute
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlAttribute
    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    @XmlAttribute
    public String getRowId() {
        return rowId;
    }

    public void setRowId(String rowId) {
        this.rowId = rowId;
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
    public Date getBuyerDate() {
        return buyerDate;
    }

    public void setBuyerDate(Date buyerDate) {
        this.buyerDate = buyerDate;
    }

    @XmlElement
    public String getBuyerURL() {
        return buyerURL;
    }

    public void setBuyerURL(String buyerURL) {
        this.buyerURL = buyerURL;
    }

    @XmlElement
    public BgcQuantity getQuantity() {
        return quantity;
    }

    public void setQuantity(BgcQuantity quantity) {
        this.quantity = quantity;
    }

    @XmlElement
    public BgcQuantity getConfirmedQuantity() {
        return confirmedQuantity;
    }

    public void setConfirmedQuantity(BgcQuantity confirmedQuantity) {
        this.confirmedQuantity = confirmedQuantity;
    }
}
