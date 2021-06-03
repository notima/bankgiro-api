package org.notima.bg.einvoice;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "batchDetails")
@XmlType(propOrder = { "bgnr", "originatorID", "intermediator", "totalSections", "totalDocuments" })
public class BgcBatchDetails {
    private String bgnr;
    private String originatorID;
    private String intermediator;
    private int totalSections;
    private int totalDocuments;

	@XmlElement(name="bgnr")
    public String getBgnr() {
        return bgnr;
    }

    public void setBgnr(String bgnr) {
        this.bgnr = bgnr;
    }

	@XmlElement(name="originatorID")
    public String getOriginatorID() {
        return originatorID;
    }

    public void setOriginatorID(String originatorID) {
        this.originatorID = originatorID;
    }

	@XmlElement(name="intermediator")
    public String getIntermediator() {
        return intermediator;
    }

    public void setIntermediator(String intermediator) {
        this.intermediator = intermediator;
    }

	@XmlElement(name="totalSections")
    public int getTotalSections() {
        return totalSections;
    }

    public void setTotalSections(int totalSections) {
        this.totalSections = totalSections;
    }

	@XmlElement(name="totalDocuments")
    public int getTotalDocuments() {
        return totalDocuments;
    }

    public void setTotalDocuments(int totalDocuments) {
        this.totalDocuments = totalDocuments;
    }
}
