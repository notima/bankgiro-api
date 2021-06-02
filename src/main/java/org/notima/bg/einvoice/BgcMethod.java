package org.notima.bg.einvoice;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlType(name = "method")
public class BgcMethod {
    private String method;
    private String code;

    @XmlValue
    public String getMethod() {
        return method;
    }
    public void setMethod(String method) {
        this.method = method;
    }

    @XmlAttribute
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
}
