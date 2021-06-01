package org.notima.bg.bgc;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Object in binary format or other format that can not be represented by
 * alfa numerical characters. Encoded as Base64 in order to be transported
 * in the xml-document.
 * 
 * @author Oliver Norin
 */
@XmlRootElement(name = "encodedObject")
@XmlType(propOrder = { 
    "objectID", 
    "format",
    "filename",
    "data"
})
public class BgcEncodedObject {
    private String objectID;
    private String format = "BASE64";
    private String filename;
    private String data;

    @XmlAttribute
    public String getObjectID() {
        return objectID;
    }
    public void setObjectID(String objectID) {
        this.objectID = objectID;
    }

    @XmlAttribute
    public String getFormat() {
        return format;
    }
    public void setFormat(String format) {
        this.format = format;
    }

    @XmlAttribute
    public String getFilename() {
        return filename;
    }
    public void setFilename(String filename) {
        this.filename = filename;
    }

    @XmlElement
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
}
