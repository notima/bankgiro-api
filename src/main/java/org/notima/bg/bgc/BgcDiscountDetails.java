package org.notima.bg.bgc;

import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "discountDetails")
@XmlType(propOrder = { 
    "type", 
    "date",
    "baseAmount",
    "discount",
    "sumOfDiscountAndCharges"
})
public class BgcDiscountDetails {
    private String type;
    private Date date;
    private BgcAmount baseAmount;
    private BgcDiscount discount;
    private BgcAmount sumOfDiscountAndCharges;

    @XmlAttribute
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
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
    public BgcAmount getBaseAmount() {
        return baseAmount;
    }
    public void setBaseAmount(BgcAmount baseAmount) {
        this.baseAmount = baseAmount;
    }

    @XmlElement
    public BgcDiscount getDiscount() {
        return discount;
    }
    public void setDiscount(BgcDiscount discount) {
        this.discount = discount;
    }

    @XmlElement
    public BgcAmount getSumOfDiscountAndCharges() {
        return sumOfDiscountAndCharges;
    }
    public void setSumOfDiscountAndCharges(BgcAmount sumOfDiscountAndCharges) {
        this.sumOfDiscountAndCharges = sumOfDiscountAndCharges;
    }
}
