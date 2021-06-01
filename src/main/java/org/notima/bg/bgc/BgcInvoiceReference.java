package org.notima.bg.bgc;

import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * An invoice or a an invoice row may have a reference to a
 * previously created invoice. 
 * Applies to credit invoices or credit rows.
 * 
 * @author Oliver Norin
 */
@XmlRootElement(name = "invoiceReference")
@XmlType(propOrder = { 
    "id", 
    "rowID", 
    "date", 
    "URL", 
    "netAmount", 
    "vatAmount" ,
    "totalAmount"
})
public class BgcInvoiceReference {
    /**
     * Identity of invoice, invoice number.
     */
    private String id;
    /**
     * Points to a row/section.
     */
    private String rowID;
    /**
     * Invoice date.
     */
    private Date date;
    /**
     * Web link to the invoice
     */
    private String URL;
    /**
     * Amount without VAT of the original invoice.
     */
    private BgcAmount netAmount;
    /**
     * VAT amount of the original invoice.
     */
    private BgcVATAmount vatAmount;
    /**
     * Total amount including VAT of the original invoice.
     */
    private BgcAmount totalAmount;

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
    public BgcAmount getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(BgcAmount netAmount) {
        this.netAmount = netAmount;
    }

    @XmlElement
    public BgcVATAmount getVatAmount() {
        return vatAmount;
    }

    public void setVatAmount(BgcVATAmount vatAmount) {
        this.vatAmount = vatAmount;
    }

    @XmlElement
    public BgcAmount getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BgcAmount totalAmount) {
        this.totalAmount = totalAmount;
    }
}
