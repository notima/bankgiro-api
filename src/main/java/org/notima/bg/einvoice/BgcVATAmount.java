package org.notima.bg.einvoice;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlType(name = "vatAmount")
public class BgcVATAmount {
    private Double amount;
    private String currency;
    private Double rate;
    private Double base;
    private String vatCode;
    private String currencyAC;
    private Double baseAC;
    private Double vatAmountAC;
    private String vatCodeAC;
    private String text;

    @XmlValue
    @XmlJavaTypeAdapter(BgcNumberFormatAdapter.class)
    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) {
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
    @XmlJavaTypeAdapter(BgcNumberFormatAdapter.class)
    public Double getRate() {
        return rate;
    }
    public void setRate(Double rate) {
        this.rate = rate;
    }

    @XmlAttribute
    @XmlJavaTypeAdapter(BgcNumberFormatAdapter.class)
    public Double getBase() {
        return base;
    }
    public void setBase(Double base) {
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
    @XmlJavaTypeAdapter(BgcNumberFormatAdapter.class)
    public Double getBaseAC() {
        return baseAC;
    }
    public void setBaseAC(Double baseAC) {
        this.baseAC = baseAC;
    }

    @XmlAttribute
    @XmlJavaTypeAdapter(BgcNumberFormatAdapter.class)
    public Double getVatAmountAC() {
        return vatAmountAC;
    }
    public void setVatAmountAC(Double vatAmountAC) {
        this.vatAmountAC = vatAmountAC;
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
