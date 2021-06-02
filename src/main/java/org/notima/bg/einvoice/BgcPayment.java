package org.notima.bg.einvoice;

import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "payment")
@XmlType(propOrder = { 
    "status",
    "dueDate", 
    "terms",
    "method",
    "overDueFine",
    "netAmount",
    "vatAmount",
    "totalAmount",
    "roundingAmount"
})
public class BgcPayment {
    private String status;
    private Date dueDate;
    private BgcTerms terms;
    private BgcMethod method;
    private BgcOverDueFine overDueFine;
    private BgcAmount netAmount;
    private BgcVATAmount vatAmount;
    private BgcAmount totalAmount;
    private BgcAmount roundingAmount;

    @XmlAttribute
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    @XmlElement
    @XmlJavaTypeAdapter(BgcDateFormatAdapter.class)
    public Date getDueDate() {
        return dueDate;
    }
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    @XmlElement
    public BgcTerms getTerms() {
        return terms;
    }
    public void setTerms(BgcTerms terms) {
        this.terms = terms;
    }

    @XmlElement
    public BgcMethod getMethod() {
        return method;
    }
    public void setMethod(BgcMethod method) {
        this.method = method;
    }

    @XmlElement
    public BgcOverDueFine getOverDueFine() {
        return overDueFine;
    }
    public void setOverDueFine(BgcOverDueFine overDueFine) {
        this.overDueFine = overDueFine;
    }

    @XmlElement
    public BgcAmount getNetAmount() {
        return netAmount;
    }
    public void setNetAmount(BgcAmount netAmount) {
        this.netAmount = netAmount;
    }

    @XmlElement
    public BgcVATAmount getVatAmount() {
        return vatAmount;
    }
    public void setVatAmount(BgcVATAmount vatAmount) {
        this.vatAmount = vatAmount;
    }

    @XmlElement
    public BgcAmount getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(BgcAmount totalAmount) {
        this.totalAmount = totalAmount;
    }

    @XmlElement
    public BgcAmount getRoundingAmount() {
        return roundingAmount;
    }
    public void setRoundingAmount(BgcAmount roundingAmount) {
        this.roundingAmount = roundingAmount;
    }
}
