package org.notima.bg.bgc;

import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "delivery")
@XmlType(propOrder = { 
    "id", 
    "noteNumber",
    "date",
    "period",
    "method",
    "terms",
    "deliveree",
    "logistics"
})
public class BgcDelivery {
    private String id;
    private String noteNumber;
    private Date date;
    private BgcPeriod period;
    private BgcMethod method;
    private BgcTerms terms;
    private BgcDeliveree deliveree;
    private BgcLogistics logistics;

    @XmlAttribute
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    @XmlElement
    public String getNoteNumber() {
        return noteNumber;
    }
    public void setNoteNumber(String noteNumber) {
        this.noteNumber = noteNumber;
    }

    @XmlElement
    @XmlJavaTypeAdapter(BgcDateFormatAdapter.class)
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    @XmlElement
    public BgcPeriod getPeriod() {
        return period;
    }
    public void setPeriod(BgcPeriod period) {
        this.period = period;
    }

    @XmlElement
    public BgcMethod getMethod() {
        return method;
    }
    public void setMethod(BgcMethod method) {
        this.method = method;
    }

    @XmlElement
    public BgcTerms getTerms() {
        return terms;
    }
    public void setTerms(BgcTerms terms) {
        this.terms = terms;
    }

    @XmlElement
    public BgcDeliveree getDeliveree() {
        return deliveree;
    }
    public void setDeliveree(BgcDeliveree deliveree) {
        this.deliveree = deliveree;
    }

    @XmlElement
    public BgcLogistics getLogistics() {
        return logistics;
    }
    public void setLogistics(BgcLogistics logistics) {
        this.logistics = logistics;
    }
}
