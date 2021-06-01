package org.notima.bg.bgc;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "rowPaymentReference")
public class BgcRowPaymentReference {
    private BgcPayment payment;

    @XmlElement
    public BgcPayment getPayment() {
        return payment;
    }

    public void setPayment(BgcPayment payment) {
        this.payment = payment;
    }
}
