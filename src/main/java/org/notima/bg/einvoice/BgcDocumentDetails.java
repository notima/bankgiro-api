package org.notima.bg.einvoice;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "documentDetails")
@XmlType(propOrder = { "originatorBgnr", "originatorID", "originatorIntermediator", "addresseeID", "addresseeID2", "addresseeIntermediator" })
public class BgcDocumentDetails {
    private String originatorBgnr;
    private String originatorID;
    private String originatorIntermediator;
    private BgcAddresseeID addresseeID;
    private BgcAddresseeID addresseeID2;
    private String addresseeIntermediator;

	@XmlElement(name="originatorBgnr")
    public String getOriginatorBgnr() {
        return originatorBgnr;
    }

    public void setOriginatorBgnr(String originatorBgnr) {
        this.originatorBgnr = originatorBgnr;
    }

	@XmlElement(name="originatorID")
    public String getOriginatorID() {
        return originatorID;
    }

    public void setOriginatorID(String originatorID) {
        this.originatorID = originatorID;
    }

	@XmlElement(name="originatorIntermediator")
    public String getOriginatorIntermediator() {
        return originatorIntermediator;
    }

    public void setOriginatorIntermediator(String originatorIntermediator) {
        this.originatorIntermediator = originatorIntermediator;
    }

	@XmlElement(name="addresseeID")
    public BgcAddresseeID getAddresseeID() {
        return addresseeID;
    }

    public void setAddresseeID(BgcAddresseeID addresseeID) {
        this.addresseeID = addresseeID;
    }

	@XmlElement(name="addresseeID2")
    public BgcAddresseeID getAddresseeID2() {
        return addresseeID2;
    }

    public void setAddresseeID2(BgcAddresseeID addresseeID2) {
        this.addresseeID2 = addresseeID2;
    }

	@XmlElement(name="addresseeIntermediator")
    public String getAddresseeIntermediator() {
        return addresseeIntermediator;
    }

    public void setAddresseeIntermediator(String addresseeIntermediator) {
        this.addresseeIntermediator = addresseeIntermediator;
    }
}
