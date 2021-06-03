package org.notima.bg.einvoice;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "invoice")
@XmlType(propOrder = { 
    "id", 
    "type", 
    "invoiceDetails", 
    "invoiceRows", 
    "seller", 
    "buyer" ,
    "payment",
    "delivery",
    "epiDetails",
    "currency",
    "printInfo",
    "factoring",
    "enclosures"
})
public class BgcInvoice {
    private String id;
    private String type;

    private BgcInvoiceDetails invoiceDetails;
    private List<BgcInvoiceRow> invoiceRows;
    private BgcSeller seller;
    private BgcBuyer buyer;
    private BgcPayment payment;
    private BgcDelivery delivery;
    private BgcEPIDetails epiDetails;
    private BgcCurrency currency;
    private BgcPrintInfo printInfo;
    private BgcFactoring factoring;
    private BgcEnclosures enclosures;

    @XmlAttribute
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlAttribute
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

	@XmlElement
    public BgcInvoiceDetails getInvoiceDetails() {
        return invoiceDetails;
    }

    public void setInvoiceDetails(BgcInvoiceDetails invoiceDetails) {
        this.invoiceDetails = invoiceDetails;
    }

	@XmlElement(name="invoiceRow")
    public List<BgcInvoiceRow> getInvoiceRows() {
        return invoiceRows;
    }

    public void setInvoiceRows(List<BgcInvoiceRow> invoiceRows) {
        this.invoiceRows = invoiceRows;
    }

	@XmlElement
    public BgcSeller getSeller() {
        return seller;
    }

    public void setSeller(BgcSeller seller) {
        this.seller = seller;
    }

    @XmlElement
    public BgcBuyer getBuyer() {
        return buyer;
    }

    public void setBuyer(BgcBuyer buyer) {
        this.buyer = buyer;
    }

    @XmlElement
    public BgcPayment getPayment() {
        return payment;
    }

    public void setPayment(BgcPayment payment) {
        this.payment = payment;
    }

    @XmlElement
    public BgcDelivery getDelivery() {
        return delivery;
    }

    public void setDelivery(BgcDelivery delivery) {
        this.delivery = delivery;
    }

    @XmlElement
    public BgcEPIDetails getEpiDetails() {
        return epiDetails;
    }

    public void setEpiDetails(BgcEPIDetails epiDetails) {
        this.epiDetails = epiDetails;
    }

    @XmlElement
    public BgcCurrency getCurrency() {
        return currency;
    }

    public void setCurrency(BgcCurrency currency) {
        this.currency = currency;
    }

    @XmlElement
    public BgcPrintInfo getPrintInfo() {
        return printInfo;
    }

    public void setPrintInfo(BgcPrintInfo printInfo) {
        this.printInfo = printInfo;
    }

    @XmlElement
    public BgcFactoring getFactoring() {
        return factoring;
    }

    public void setFactoring(BgcFactoring factoring) {
        this.factoring = factoring;
    }

    @XmlElement
    public BgcEnclosures getEnclosures() {
        return enclosures;
    }

    public void setEnclosures(BgcEnclosures enclosures) {
        this.enclosures = enclosures;
    }
}
