package org.notima.bg.bgc;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "invoiceDetails")
public class BgcInvoiceDetails {
    private BgcInvoiceType invoiceType;
    private List<BgcIdentifier> identifiers;
    private String invoiceNumber;
    private Date invoiceDate;
    private BgcPeriod invoicePeriod;
    private BgcPriceListReference priceListReference;
    private BgcTenderReference tenderReference;
    private BgcOrderReference orderReference;
    private BgcRemainderReference remainderReference;
    private BgcInvoiceReference invoiceReference;
    private BgcAgreementReference agreementReference;
    private List<BgcTypedResourceReference> documentReferences;
    private BgcInvoiceTotal invoiceTotal;
    private String costCenter;
    private List<BgcVATAmount> vatAmountList;
    private List<String> textList;
    private List<String> urlList;
    private List<BgcImages> imagesList;
    private List<BgcExpences> expencesList;
    private List<BgcDiscountDetails> discountDetailsList;
    private BgcFreightDetails freightDetails;
    private List<BgcProjectReference> projectReferences;

    @XmlElement
    public BgcInvoiceType getInvoiceType() {
        return invoiceType;
    }
    
    public void setInvoiceType(BgcInvoiceType invoiceType) {
        this.invoiceType = invoiceType;
    }

    @XmlElement(name = "identifier")
    public List<BgcIdentifier> getIdentifiers() {
        return identifiers;
    }

    public void setIdentifiers(List<BgcIdentifier> identifiers) {
        this.identifiers = identifiers;
    }

    @XmlElement
    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    @XmlElement
    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    @XmlElement
    public BgcPeriod getInvoicePeriod() {
        return invoicePeriod;
    }

    public void setInvoicePeriod(BgcPeriod invoicePeriod) {
        this.invoicePeriod = invoicePeriod;
    }

    @XmlElement
    public BgcPriceListReference getPriceListReference() {
        return priceListReference;
    }

    public void setPriceListReference(BgcPriceListReference priceListReference) {
        this.priceListReference = priceListReference;
    }

    @XmlElement
    public BgcTenderReference getTenderReference() {
        return tenderReference;
    }

    public void setTenderReference(BgcTenderReference tenderReference) {
        this.tenderReference = tenderReference;
    }

    @XmlElement
    public BgcOrderReference getOrderReference() {
        return orderReference;
    }

    public void setOrderReference(BgcOrderReference orderReference) {
        this.orderReference = orderReference;
    }

    @XmlElement
    public BgcRemainderReference getRemainderReference() {
        return remainderReference;
    }

    public void setRemainderReference(BgcRemainderReference remainderReference) {
        this.remainderReference = remainderReference;
    }

    @XmlElement
    public BgcInvoiceReference getInvoiceReference() {
        return invoiceReference;
    }

    public void setInvoiceReference(BgcInvoiceReference invoiceReference) {
        this.invoiceReference = invoiceReference;
    }

    @XmlElement
    public BgcAgreementReference getAgreementReference() {
        return agreementReference;
    }

    public void setAgreementReference(BgcAgreementReference agreementReference) {
        this.agreementReference = agreementReference;
    }

    @XmlElement(name = "documentReference")
    public List<BgcTypedResourceReference> getDocumentReferences() {
        return documentReferences;
    }

    public void setDocumentReferences(List<BgcTypedResourceReference> documentReferences) {
        this.documentReferences = documentReferences;
    }

    @XmlElement
    public BgcInvoiceTotal getInvoiceTotal() {
        return invoiceTotal;
    }

    public void setInvoiceTotal(BgcInvoiceTotal invoiceTotal) {
        this.invoiceTotal = invoiceTotal;
    }

    @XmlElement
    public String getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(String costCenter) {
        this.costCenter = costCenter;
    }

    @XmlElement(name = "vatAmount")
    public List<BgcVATAmount> getVatAmountList() {
        return vatAmountList;
    }

    public void setVatAmountList(List<BgcVATAmount> vatAmountList) {
        this.vatAmountList = vatAmountList;
    }

    @XmlElement(name = "text")
    public List<String> getTextList() {
        return textList;
    }

    public void setTextList(List<String> textList) {
        this.textList = textList;
    }

    @XmlElement(name = "URL")
    public List<String> getUrlList() {
        return urlList;
    }

    public void setUrlList(List<String> urlList) {
        this.urlList = urlList;
    }

    @XmlElement(name = "images")
    public List<BgcImages> getImagesList() {
        return imagesList;
    }

    public void setImagesList(List<BgcImages> imagesList) {
        this.imagesList = imagesList;
    }

    @XmlElement(name = "expenses")
    public List<BgcExpences> getExpencesList() {
        return expencesList;
    }

    public void setExpencesList(List<BgcExpences> expencesList) {
        this.expencesList = expencesList;
    }

    @XmlElement(name = "discountDetails")
    public List<BgcDiscountDetails> getDiscountDetailsList() {
        return discountDetailsList;
    }

    public void setDiscountDetailsList(List<BgcDiscountDetails> discountDetailsList) {
        this.discountDetailsList = discountDetailsList;
    }

    @XmlElement
    public BgcFreightDetails getFreightDetails() {
        return freightDetails;
    }

    public void setFreightDetails(BgcFreightDetails freightDetails) {
        this.freightDetails = freightDetails;
    }

    @XmlElement(name = "projectReference")
    public List<BgcProjectReference> getProjectReferences() {
        return projectReferences;
    }

    public void setProjectReferences(List<BgcProjectReference> projectReferences) {
        this.projectReferences = projectReferences;
    }
}
