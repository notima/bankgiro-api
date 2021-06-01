package org.notima.bg.bgc;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "paymentInstructions")
@XmlType(propOrder = {
    "id",
    "remittanceID",
    "instructedAmount",
    "optionDate"
})
public class BgcPaymentInstructions {
    private String id;
    private String remittanceID;
    private BgcAmount instructedAmount;
    private BgcOptionDate optionDate;

    @XmlAttribute
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    @XmlElement
    public String getRemittanceID() {
        return remittanceID;
    }
    public void setRemittanceID(String remittanceID) {
        this.remittanceID = remittanceID;
    }

    @XmlElement
    public BgcAmount getInstructedAmount() {
        return instructedAmount;
    }
    public void setInstructedAmount(BgcAmount instructedAmount) {
        this.instructedAmount = instructedAmount;
    }

    @XmlElement
    public BgcOptionDate getOptionDate() {
        return optionDate;
    }
    public void setOptionDate(BgcOptionDate optionDate) {
        this.optionDate = optionDate;
    }
}
