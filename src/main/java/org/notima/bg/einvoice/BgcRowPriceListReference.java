package org.notima.bg.einvoice;

import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "rowPriceListReference")
public class BgcRowPriceListReference {
    private String id;
    private String rowID;
    private Date date;
    private String URL;
    private String text;

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
}
