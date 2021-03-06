package org.notima.bg.einvoice;

import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "rowTenderReference")
public class BgcRowTenderReference {
    private String id;
    private String rowID;
    private String buyerID;
    private Date date;
    private String URL;
    private String text;
    private BgcQuantity quantity;

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
    public BgcQuantity getQuantity() {
        return quantity;
    }
    public void setQuantity(BgcQuantity quantity) {
        this.quantity = quantity;
    }
}
