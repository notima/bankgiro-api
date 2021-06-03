package org.notima.bg.einvoice;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "currency")
@XmlType(propOrder = { 
    "currencyCode", 
    "rate", 
    "rateEntity", 
    "codeAC", 
    "exchangeRateAC" ,
    "rateEntityAC"
})
public class BgcCurrency {
    private String currencyCode;
    private Double rate;
    private Double rateEntity;
    private String codeAC;
    private Double exchangeRateAC;
    private Double rateEntityAC;

    @XmlAttribute(name = "code")
    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
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
    public Double getRateEntity() {
        return rateEntity;
    }

    public void setRateEntity(Double rateEntity) {
        this.rateEntity = rateEntity;
    }

    @XmlAttribute
    public String getCodeAC() {
        return codeAC;
    }

    public void setCodeAC(String codeAC) {
        this.codeAC = codeAC;
    }

    @XmlAttribute
    @XmlJavaTypeAdapter(BgcNumberFormatAdapter.class)
    public Double getExchangeRateAC() {
        return exchangeRateAC;
    }

    public void setExchangeRateAC(Double exchangeRateAC) {
        this.exchangeRateAC = exchangeRateAC;
    }

    @XmlAttribute
    @XmlJavaTypeAdapter(BgcNumberFormatAdapter.class)
    public Double getRateEntityAC() {
        return rateEntityAC;
    }

    public void setRateEntityAC(Double rateEntityAC) {
        this.rateEntityAC = rateEntityAC;
    }
    
}
