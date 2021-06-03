package org.notima.bg.einvoice;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "sectionDetails")
public class BgcSectionDetails {
    private int totalDocuments;

	@XmlElement(name="totalDocuments")
    public int getTotalDocuments() {
        return totalDocuments;
    }

    public void setTotalDocuments(int totalDocuments) {
        this.totalDocuments = totalDocuments;
    }


}
