package org.notima.bg.bgc;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "epiDetails")
@XmlType(propOrder = {
    "identificationDetails",
    "beneficiary",
    "paymentInstructions"
})
public class BgcEPIDetails {
    private BgcIdentificationDetails identificationDetails;
    private BgcBeneficiary beneficiary;
    private BgcPaymentInstructions paymentInstructions;

    @XmlElement
    public BgcIdentificationDetails getIdentificationDetails() {
        return identificationDetails;
    }
    public void setIdentificationDetails(BgcIdentificationDetails identificationDetails) {
        this.identificationDetails = identificationDetails;
    }

    @XmlElement
    public BgcBeneficiary getBeneficiary() {
        return beneficiary;
    }
    public void setBeneficiary(BgcBeneficiary beneficiary) {
        this.beneficiary = beneficiary;
    }

    @XmlElement
    public BgcPaymentInstructions getPaymentInstructions() {
        return paymentInstructions;
    }
    public void setPaymentInstructions(BgcPaymentInstructions paymentInstructions) {
        this.paymentInstructions = paymentInstructions;
    }
}
