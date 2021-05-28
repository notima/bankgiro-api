package org.notima.bg.bgc;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "invoiceRow")
public class BgcInvoiceRow {
    private String id;
    private BgcArticle article;
    private String text;
    private BgcRowOrderReference rowOrderReference;
    private BgcRowPriceListReference rowPriceListReference;
    private BgcRowTenderReference rowTenderReference;
    private List<BgcRowDeliveryReference> rowDeliveryReferences;
    private BgcRowRemainderReferen rowRemainderReferen;
    private BgcRowInvoiceReference rowInvoiceReference;
    private BgcRowPaymentReference rowPaymentReference;
    private BgcTypedResourceReference rowDocumentReference;
    private BgcPeriod period;
    private BgcQuantity quantity;
    private BgcQuantity deliveredQuantity;
    private BgcAmount unitPrice;
    private String costCenter;
    private BgcDiscount discount;
    private BgcAmount netAmount;
    private BgcVATAmount vatAmount;
    private BgcAmount totalAmount;
    private BgcExpences expences;
    private BgcAmount currencyRate;

    @XmlAttribute
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    
    @XmlElement
    public BgcArticle getArticle() {
        return article;
    }
    public void setArticle(BgcArticle article) {
        this.article = article;
    }

    @XmlElement
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    @XmlElement
    public BgcRowOrderReference getRowOrderReference() {
        return rowOrderReference;
    }
    public void setRowOrderReference(BgcRowOrderReference rowOrderReference) {
        this.rowOrderReference = rowOrderReference;
    }

    @XmlElement
    public BgcRowPriceListReference getRowPriceListReference() {
        return rowPriceListReference;
    }
    public void setRowPriceListReference(BgcRowPriceListReference rowPriceListReference) {
        this.rowPriceListReference = rowPriceListReference;
    }

    @XmlElement
    public BgcRowTenderReference getRowTenderReference() {
        return rowTenderReference;
    }
    public void setRowTenderReference(BgcRowTenderReference rowTenderReference) {
        this.rowTenderReference = rowTenderReference;
    }

    @XmlElement
    public List<BgcRowDeliveryReference> getRowDeliveryReferences() {
        return rowDeliveryReferences;
    }
    public void setRowDeliveryReferences(List<BgcRowDeliveryReference> rowDeliveryReferences) {
        this.rowDeliveryReferences = rowDeliveryReferences;
    }

    @XmlElement
    public BgcRowRemainderReferen getRowRemainderReferen() {
        return rowRemainderReferen;
    }
    public void setRowRemainderReferen(BgcRowRemainderReferen rowRemainderReferen) {
        this.rowRemainderReferen = rowRemainderReferen;
    }

    @XmlElement
    public BgcRowInvoiceReference getRowInvoiceReference() {
        return rowInvoiceReference;
    }
    public void setRowInvoiceReference(BgcRowInvoiceReference rowInvoiceReference) {
        this.rowInvoiceReference = rowInvoiceReference;
    }

    @XmlElement
    public BgcRowPaymentReference getRowPaymentReference() {
        return rowPaymentReference;
    }
    public void setRowPaymentReference(BgcRowPaymentReference rowPaymentReference) {
        this.rowPaymentReference = rowPaymentReference;
    }

    @XmlElement
    public BgcTypedResourceReference getRowDocumentReference() {
        return rowDocumentReference;
    }
    public void setRowDocumentReference(BgcTypedResourceReference rowDocumentReference) {
        this.rowDocumentReference = rowDocumentReference;
    }

    @XmlElement
    public BgcPeriod getPeriod() {
        return period;
    }
    public void setPeriod(BgcPeriod period) {
        this.period = period;
    }

    @XmlElement
    public BgcQuantity getQuantity() {
        return quantity;
    }
    public void setQuantity(BgcQuantity quantity) {
        this.quantity = quantity;
    }

    @XmlElement
    public BgcQuantity getDeliveredQuantity() {
        return deliveredQuantity;
    }
    public void setDeliveredQuantity(BgcQuantity deliveredQuantity) {
        this.deliveredQuantity = deliveredQuantity;
    }

    @XmlElement
    public BgcAmount getUnitPrice() {
        return unitPrice;
    }
    public void setUnitPrice(BgcAmount unitPrice) {
        this.unitPrice = unitPrice;
    }

    @XmlElement
    public String getCostCenter() {
        return costCenter;
    }
    public void setCostCenter(String costCenter) {
        this.costCenter = costCenter;
    }

    @XmlElement
    public BgcDiscount getDiscount() {
        return discount;
    }
    public void setDiscount(BgcDiscount discount) {
        this.discount = discount;
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
    public BgcExpences getExpences() {
        return expences;
    }
    public void setExpences(BgcExpences expences) {
        this.expences = expences;
    }

    @XmlElement
    public BgcAmount getCurrencyRate() {
        return currencyRate;
    }
    public void setCurrencyRate(BgcAmount currencyRate) {
        this.currencyRate = currencyRate;
    }
}
