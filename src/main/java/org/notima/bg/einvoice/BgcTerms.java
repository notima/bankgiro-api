package org.notima.bg.einvoice;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlType(name = "terms")
public class BgcTerms {
    private String terms;
    private String code;

    @XmlValue
    public String getTerms() {
        return terms;
    }
    public void setTerms(String terms) {
        this.terms = terms;
    }

    @XmlAttribute
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
}
