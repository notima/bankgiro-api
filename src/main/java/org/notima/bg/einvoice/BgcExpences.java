package org.notima.bg.einvoice;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "expenses")
@XmlType(propOrder = { 
    "type", 
    "text",
    "netAmount",
    "vatAmount",
    "totalAmount",
    "baseAmount",
})
public class BgcExpences {
    
    /**
     * Type of expense.
     * 
     * arbitrary codes.
     * 
     * E.g. HandlingCharges, EnergyTax, PackingCharges etc.
     */
    private String type;
    private String text;
    private BgcAmount netAmount;
    private BgcVATAmount vatAmount;
    private BgcAmount totalAmount;
    private BgcAmount baseAmount;

    @XmlAttribute
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    @XmlElement
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
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

    @XmlElement
    public BgcAmount getBaseAmount() {
        return baseAmount;
    }
    public void setBaseAmount(BgcAmount baseAmount) {
        this.baseAmount = baseAmount;
    }
}
