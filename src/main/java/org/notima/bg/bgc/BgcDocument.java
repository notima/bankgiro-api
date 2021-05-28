package org.notima.bg.bgc;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "document")
public class BgcDocument {
    private String id;
    private String type;
    private int templateID;
    private String distribution;
    private BgcDocumentDetails documentDetails;
    private BgcInvoice invoice;

    @XmlAttribute
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlAttribute
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlAttribute
    public int getTemplateID() {
        return templateID;
    }

    public void setTemplateID(int templateID) {
        this.templateID = templateID;
    }

    @XmlAttribute
    public String getDistribution() {
        return distribution;
    }

    public void setDistribution(String distribution) {
        this.distribution = distribution;
    }

    @XmlElement(name="documentDetails")
    public BgcDocumentDetails getDocumentDetails() {
        return documentDetails;
    }

    public void setDocumentDetails(BgcDocumentDetails documentDetails) {
        this.documentDetails = documentDetails;
    }

    @XmlElement
    public BgcInvoice getInvoice() {
        return invoice;
    }

    public void setInvoice(BgcInvoice invoice) {
        this.invoice = invoice;
    }
}
