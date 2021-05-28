package org.notima.bg.bgc;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class BgcDiscount {
    private String type;
    private double percent;
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
    public double getPercent() {
        return percent;
    }
    public void setPercent(double percent) {
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
