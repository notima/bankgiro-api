package org.notima.bg.bgc;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "freightDetails")
public class BgcFreightDetails {
    private String text;
    private BgcAmount freightAmount;
    private BgcVATAmount vatAmount;
    private BgcAmount totalAmount;

    @XmlElement
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    @XmlElement
    public BgcAmount getFreightAmount() {
        return freightAmount;
    }
    public void setFreightAmount(BgcAmount freightAmount) {
        this.freightAmount = freightAmount;
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
