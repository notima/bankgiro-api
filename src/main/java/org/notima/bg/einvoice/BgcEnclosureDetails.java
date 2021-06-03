package org.notima.bg.einvoice;

import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "enclosureDetails")
@XmlType(propOrder = { 
    "mimeCode", 
    "type",
    "name",
    "date",
    "URL",
    "id",
    "username",
    "password",
    "encodedObject"
})
public class BgcEnclosureDetails {

    public static final String ENCLOSURE_TYPE_CONTRACT = "CT";
    public static final String ENCLOSURE_TYPE_SUPPLIER_REF = "ACD";
    public static final String ENCLOSURE_TYPE_DELIVERY_NOTE = "DQ";
    public static final String ENCLOSURE_TYPE_IMAGE_URL = "ATS";
    public static final String ENCLOSURE_TYPE_ORIGINAL = "ORIGINAL";
    public static final String ENCLOSURE_TYPE_LOGOTYPE = "LOGOTYPE";
    public static final String ENCLOSURE_TYPE_SCANNED_DOC = "SCANNED";

    /**
     * E.g. image/jpeg
     * @see <a href="http://www.iana.org/assignments/media-types/index.html">registered types</a>
     */
    private String mimeCode;
    private String type;
    private String name;
    private Date date;
    /**
     * Link to the attachment or portal
     * where the attachment can be found.
     * 
     * E.g. http://www.bilageportalen.se/request.asp.
     * 
     * Do not use with encodedObject.
     */
    private String URL;
    /**
     * Document name, image name or other
     * id of the attachment required for
     * identifying a unique document.
     * 
     * E.g. bilaga1234.pdf or bilaga1234.
     * 
     * Used by the recipient for finding
     * the right attachment automatically
     * together with username and password
     * The information is attached as a
     * querystring with the field: id.
     * 
     * Do not use with encodedObject.
     */
    private String id;
    /**
     * Username for accessing the attachment
     * in a portal that requires username
     * and password.
     * 
     * If automation is used, it is sent as
     * a POST message (HTML form)
     * 
     * Do not use with encodedObject.
     */
    private String username;
    /**
     * Password for accessing the attachment
     * in a portal that requires username
     * and password.
     * 
     * If automation is used, it is sent as
     * a POST message (HTML form)
     * 
     * Do not use with encodedObject.
     */
    private String password;
    /**
     * Contains the attatchment when it is
     * sent as base64.
     * 
     * Alternative to using URL, id,
     * username and password.
     */
    private BgcEncodedObject encodedObject;

    @XmlAttribute
    public String getMimeCode() {
        return mimeCode;
    }
    public void setMimeCode(String mimeCode) {
        this.mimeCode = mimeCode;
    }

    @XmlAttribute
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    @XmlElement
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @XmlElement
    @XmlJavaTypeAdapter(BgcDateFormatAdapter.class)
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    @XmlElement
    public String getURL() {
        return URL;
    }
    public void setURL(String uRL) {
        URL = uRL;
    }

    @XmlElement
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    @XmlElement
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    @XmlElement
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @XmlElement
    public BgcEncodedObject getEncodedObject() {
        return encodedObject;
    }
    public void setEncodedObject(BgcEncodedObject encodedObject) {
        this.encodedObject = encodedObject;
    }
}
