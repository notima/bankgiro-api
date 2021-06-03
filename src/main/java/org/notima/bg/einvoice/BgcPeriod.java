package org.notima.bg.einvoice;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "period")
public class BgcPeriod {
    private Date startDate;
    private Date endDate;

    @XmlElement
    @XmlJavaTypeAdapter(BgcDateFormatAdapter.class)
    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @XmlElement
    @XmlJavaTypeAdapter(BgcDateFormatAdapter.class)
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
