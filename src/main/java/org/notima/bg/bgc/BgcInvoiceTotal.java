package org.notima.bg.bgc;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "invoiceTotal")
@XmlType(propOrder = { 
    "netAmount", 
    "vatAmount", 
    "totalAmount",
    "roundingAmount"
})
public class BgcInvoiceTotal {
    private BgcAmount netAmount;
    private BgcVATAmount vatAmount;
    private BgcAmount totalAmount;
    private BgcAmount roundingAmount;

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
