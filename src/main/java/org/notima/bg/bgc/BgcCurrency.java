package org.notima.bg.bgc;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "currency")
public class BgcCurrency {
    private String currencyCode;
    private double rate;
    private double rateEntity;
    private String codeAC;
    private double exchangeRateAC;
    private double rateEntityAC;

    @XmlAttribute(name = "code")
    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    @XmlAttribute
    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    @XmlAttribute
    public double getRateEntity() {
        return rateEntity;
    }

    public void setRateEntity(double rateEntity) {
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
    public double getExchangeRateAC() {
        return exchangeRateAC;
    }

    public void setExchangeRateAC(double exchangeRateAC) {
        this.exchangeRateAC = exchangeRateAC;
    }

    @XmlAttribute
    public double getRateEntityAC() {
        return rateEntityAC;
    }

    public void setRateEntityAC(double rateEntityAC) {
        this.rateEntityAC = rateEntityAC;
    }
    
}
