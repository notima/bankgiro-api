package org.notima.bg.bgc;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "printInfo")
@XmlType(propOrder = { 
    "id", 
    "copyCode", 
    "exportFlag", 
    "languageCode", 
    "originalCode" ,
    "text",
    "postage",
    "layout",
    "acknowledgeEmail",
    "logo"
})
public class BgcPrintInfo {
    public static final String A_POSTAGE = "A";
    public static final String B_POSTAGE = "B";

    private String id;
    private String copyCode;
    private boolean exportFlag;
    private String languageCode;
    private String originalCode;
    private String text;
    private String postage = B_POSTAGE;
    private String layout;
    private String acknowledgeEmail;
    private String logo; // TODO: Find out what datatype this should be

    @XmlAttribute
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlElement
    public String getCopyCode() {
        return copyCode;
    }

    public void setCopyCode(String copyCode) {
        this.copyCode = copyCode;
    }

    @XmlElement
    public boolean isExportFlag() {
        return exportFlag;
    }

    public void setExportFlag(boolean exportFlag) {
        this.exportFlag = exportFlag;
    }

    @XmlElement
    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    @XmlElement
    public String getOriginalCode() {
        return originalCode;
    }

    public void setOriginalCode(String originalCode) {
        this.originalCode = originalCode;
    }

    @XmlElement
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @XmlElement
    public String getPostage() {
        return postage;
    }

    public void setPostage(String postage) {
        this.postage = postage;
    }

    @XmlElement
    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    @XmlElement
    public String getAcknowledgeEmail() {
        return acknowledgeEmail;
    }

    public void setAcknowledgeEmail(String acknowledgeEmail) {
        this.acknowledgeEmail = acknowledgeEmail;
    }

    @XmlElement(name = "imageBase64")
    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
