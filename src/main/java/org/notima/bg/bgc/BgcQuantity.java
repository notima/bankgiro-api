package org.notima.bg.bgc;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlType(name = "quantity")
public class BgcQuantity {
    private int qunatity;
    private String unitCode;

    @XmlValue
    public int getQunatity() {
        return qunatity;
    }
    public void setQunatity(int qunatity) {
        this.qunatity = qunatity;
    }

    @XmlAttribute
    public String getUnitCode() {
        return unitCode;
    }
    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }
}
