package org.notima.bg.bgc;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "section")
@XmlType(propOrder = { "id", "bgnr", "bgnrType", "originatorID", "sectionDetails", "documents" })
public class BgcSection {
    private String id;
    private String bgnr;
    private String originatorID;
    private String bgnrType;
    private BgcSectionDetails sectionDetails;
    private List<BgcDocument> documents;

    @XmlAttribute
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlAttribute
    public String getBgnr() {
        return bgnr;
    }

    public void setBgnr(String bgnr) {
        this.bgnr = bgnr;
    }

    @XmlAttribute
    public String getOriginatorID() {
        return originatorID;
    }

    public void setOriginatorID(String originatorID) {
        this.originatorID = originatorID;
    }

    @XmlAttribute
    public String getBgnrType() {
        return bgnrType;
    }

    public void setBgnrType(String bgnrType) {
        this.bgnrType = bgnrType;
    }

	@XmlElement(name="sectionDetails")
    public BgcSectionDetails getSectionDetails() {
        return sectionDetails;
    }

    public void setSectionDetails(BgcSectionDetails sectionDetails) {
        this.sectionDetails = sectionDetails;
    }

	@XmlElement(name="document")
    public List<BgcDocument> getDocuments() {
        return documents;
    }

    public void setDocuments(List<BgcDocument> documents) {
        this.documents = documents;
    }
}
