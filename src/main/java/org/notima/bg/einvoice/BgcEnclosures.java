package org.notima.bg.einvoice;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "enclosures")
public class BgcEnclosures {
    private List<BgcEnclosureDetails> enclosureDetails;

    @XmlElement
    public List<BgcEnclosureDetails> getEnclosureDetails() {
        return enclosureDetails;
    }

    public void setEnclosureDetails(List<BgcEnclosureDetails> enclosureDetails) {
        this.enclosureDetails = enclosureDetails;
    }
}
