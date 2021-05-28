package org.notima.bg.bgc;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlType(name = "vatAmount")
public class BgcVATAmount {
    private double amount;
    private String currency;
    private double rate;
    private double base;
    private String vatCode;
    private String currencyAC;
    private double baseAC;
    private double vatAmount;
    private String vatCodeAC;
    private String text;

    @XmlValue
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }

    @XmlAttribute
    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @XmlAttribute
    public double getRate() {
        return rate;
    }
    public void setRate(double rate) {
        this.rate = rate;
    }

    @XmlAttribute
    public double getBase() {
        return base;
    }
    public void setBase(double base) {
        this.base = base;
    }

    @XmlAttribute
    public String getVatCode() {
        return vatCode;
    }
    public void setVatCode(String vatCode) {
        this.vatCode = vatCode;
    }

    @XmlAttribute
    public String getCurrencyAC() {
        return currencyAC;
    }
    public void setCurrencyAC(String currencyAC) {
        this.currencyAC = currencyAC;
    }

    @XmlAttribute
    public double getBaseAC() {
        return baseAC;
    }
    public void setBaseAC(double baseAC) {
        this.baseAC = baseAC;
    }

    @XmlAttribute
    public double getVatAmount() {
        return vatAmount;
    }
    public void setVatAmount(double vatAmount) {
        this.vatAmount = vatAmount;
    }

    @XmlAttribute
    public String getVatCodeAC() {
        return vatCodeAC;
    }
    public void setVatCodeAC(String vatCodeAC) {
        this.vatCodeAC = vatCodeAC;
    }

    @XmlAttribute
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
}
