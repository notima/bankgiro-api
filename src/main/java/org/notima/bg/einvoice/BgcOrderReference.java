package org.notima.bg.einvoice;

import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "orderReference")
@XmlType(propOrder = { 
    "id", 
    "buyerID", 
    "rowID", 
    "date",
    "URL",
    "text",
    "buyerDate",
    "buyerURL",
    "quantity",
    "confirmedQuantity"
})
public class BgcOrderReference {
    private String id;
    private String buyerID;
    private String rowID;
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
    public String getBuyerID() {
        return buyerID;
    }

    public void setBuyerID(String buyerID) {
        this.buyerID = buyerID;
    }

    @XmlAttribute
    public String getRowID() {
        return rowID;
    }

    public void setRowId(String rowID) {
        this.rowID = rowID;
    }

    @XmlElement
    @XmlJavaTypeAdapter(BgcDateFormatAdapter.class)
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
    @XmlJavaTypeAdapter(BgcDateFormatAdapter.class)
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
