package org.notima.bg.einvoice;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlType(name = "country")
public class BgcAddressCountry {
    private String name;
    private String code;

    @XmlValue
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @XmlAttribute
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
}
