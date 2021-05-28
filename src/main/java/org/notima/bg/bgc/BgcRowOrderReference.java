package org.notima.bg.bgc;

import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "rowOrderReference")
public class BgcRowOrderReference {
    private String id;
    private String rowID;
    private String buyerID;
    private Date date;
    private String url;
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
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
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
