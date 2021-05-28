package org.notima.bg.bgc;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "legislationDetails")
public class BgcLegislationDetails {
    private String vatIncluded;
    private String euInfo;
    private String fTaxStatement;
    private String vatReference;

    @XmlElement
    public String getVatIncluded() {
        return vatIncluded;
    }
    public void setVatIncluded(String vatIncluded) {
        this.vatIncluded = vatIncluded;
    }

    @XmlElement
    public String getEuInfo() {
        return euInfo;
    }
    public void setEuInfo(String euInfo) {
        this.euInfo = euInfo;
    }

    @XmlElement
    public String getfTaxStatement() {
        return fTaxStatement;
    }
    public void setfTaxStatement(String fTaxStatement) {
        this.fTaxStatement = fTaxStatement;
    }

    @XmlElement
    public String getVatReference() {
        return vatReference;
    }
    public void setVatReference(String vatReference) {
        this.vatReference = vatReference;
    }
}
