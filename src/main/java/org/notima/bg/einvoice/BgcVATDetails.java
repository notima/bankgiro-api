package org.notima.bg.einvoice;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "vatDetails")
@XmlType(propOrder = { 
    "vatNumber", 
    "vatRegistration", 
    "homeTown"
})
public class BgcVATDetails {
    private String vatNumber;
    private String vatRegistration;
    private String homeTown;

    @XmlElement
    public String getVatNumber() {
        return vatNumber;
    }

    public void setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
    }

    @XmlElement
    public String getVatRegistration() {
        return vatRegistration;
    }

    public void setVatRegistration(String vatRegistration) {
        this.vatRegistration = vatRegistration;
    }

    @XmlElement
    public String getHomeTown() {
        return homeTown;
    }
    
    public void setHomeTown(String homeTown) {
        this.homeTown = homeTown;
    }
    
}
