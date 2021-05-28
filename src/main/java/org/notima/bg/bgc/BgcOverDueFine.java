package org.notima.bg.bgc;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlType(name = "overDueFine")
public class BgcOverDueFine {
    /**
     * Description of the terms of over due fines.
     * 
     * Include everything in clear text that should 
     * be presented to the customer. E.g. 8% + reference interest rate.
     */
    private String terms;
    private double rate;

    @XmlValue
    public String getTerms() {
        return terms;
    }
    public void setTerms(String terms) {
        this.terms = terms;
    }

    @XmlAttribute
    public double getRate() {
        return rate;
    }
    public void setRate(double rate) {
        this.rate = rate;
    }
}
