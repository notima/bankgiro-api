package org.notima.bg.einvoice;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "image")
public class BgcImage {
    private String src;

    @XmlAttribute
    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}
