package org.notima.bg.bgc;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "batch")
public class BgcBatch {

    public static final String STATUS_CERTIFICATION = "C";
    public static final String STATUS_TEST = "T";
    public static final String STATUS_PRODUCTION = "P";

    private String id;
    private String status;
    private String version;
    private BgcBatchDetails batchDetails;
    private List<BgcSection> sections;

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
