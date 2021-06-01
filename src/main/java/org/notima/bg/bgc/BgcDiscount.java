package org.notima.bg.bgc;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "discount")
@XmlType(propOrder = { 
    "type", 
    "percent",
    "text",
    "amount",
    "netAmount",
    "vatAmount"
})
public class BgcDiscount {
    private String type;
    private Double percent;
    private String text;
    private BgcAmount amount;
    private BgcAmount netAmount;
    private BgcVATAmount vatAmount;

    @XmlAttribute
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    @XmlAttribute
    @XmlJavaTypeAdapter(BgcNumberFormatAdapter.class)
    public Double getPercent() {
        return percent;
    }
    public void setPercent(Double percent) {
        this.percent = percent;
    }

    @XmlElement
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    @XmlElement
    public BgcAmount getAmount() {
        return amount;
    }
    public void setAmount(BgcAmount amount) {
        this.amount = amount;
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
}
