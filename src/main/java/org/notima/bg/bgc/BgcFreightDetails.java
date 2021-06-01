package org.notima.bg.bgc;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "freightDetails")
@XmlType(propOrder = { 
    "id", 
    "sellerReferenceID",
    "text",
    "freightAmount",
    "vatAmount",
    "totalAmount"
})
public class BgcFreightDetails {
    private String id;
    private String sellerReferenceID;
    private String text;
    private BgcAmount freightAmount;
    private BgcVATAmount vatAmount;
    private BgcAmount totalAmount;

    @XmlAttribute
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    @XmlAttribute
    public String getSellerReferenceID() {
        return sellerReferenceID;
    }
    public void setSellerReferenceID(String sellerReferenceID) {
        this.sellerReferenceID = sellerReferenceID;
    }
    @XmlElement
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    @XmlElement
    public BgcAmount getFreightAmount() {
        return freightAmount;
    }
    public void setFreightAmount(BgcAmount freightAmount) {
        this.freightAmount = freightAmount;
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
}
