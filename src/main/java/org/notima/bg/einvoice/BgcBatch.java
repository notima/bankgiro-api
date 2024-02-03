package org.notima.bg.einvoice;

import java.io.StringWriter;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "batch")
@XmlType(propOrder = { "id", "status", "version", "batchDetails", "sections" })
public class BgcBatch {

    public static final String STATUS_CERTIFICATION = "C";
    public static final String STATUS_TEST = "T";
    public static final String STATUS_PRODUCTION = "P";

    private String id;
    private String status;
    private String version;
    private BgcBatchDetails batchDetails;
    private List<BgcSection> sections;

    @Override
    public String toString(){
        try {
            JAXBContext context = JAXBContext.newInstance(BgcBatch.class);
            Marshaller mar = context.createMarshaller();
            mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            StringWriter sw = new StringWriter();
            mar.marshal(this, sw);
            return sw.toString().replace("    ", "");
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }

    @XmlAttribute
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlAttribute
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlAttribute
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
    
	@XmlElement(name="batchDetails")
    public BgcBatchDetails getBatchDetails() {
        return batchDetails;
    }

    public void setBatchDetails(BgcBatchDetails batchDetails) {
        this.batchDetails = batchDetails;
    }

	@XmlElement(name="section")
    public List<BgcSection> getSections() {
        return sections;
    }

    public void setSections(List<BgcSection> sections) {
        this.sections = sections;
    }
}
