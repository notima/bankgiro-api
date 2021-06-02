package org.notima.bg.einvoice;

import javax.xml.bind.annotation.XmlAttribute;

public class BgcTypedResourceReference extends BgcResourceReference {
    private String type;

    @XmlAttribute
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
