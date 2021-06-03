package org.notima.bg.einvoice;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * BANK IDENTITIES:
 * Bank name                Short namn (ENAR)       BankID              CLNR
 * Danske Bank              OEB                     0035                1200
 * SEB                      SEB                     0019                5000
 * Handelsbanken            SHB                     0027                6000
 * Skandiabanken            SKB                     0043                9150
 * Swedbank                 FSPA                    0051                8000
 * Nordea                   NB                      0078                3000
 * Länsförsäkringar Bank    LFB                     0124                9020
 * Sparbanken Öresund       FINN                    0086                9300
 * ICA banken               ICA                     0116                9270
 * Sparbanken Syd           SYD                     0159                9570
 * DNB                      DNB                     0132                9190
 */
@XmlRootElement(name = "bank")
public class BgcBankInformation {
    /**
     * Alfa numerical code that identifies the bank
     * 
     * E.g. SEB
     */
    private String id;
    /**
     * Numberical code that identifies the bank,
     * such as main clearing number, clearing number, etc.
     */
    private String code;
    /**
     * Name of the bank
     */
    private String name;

    @XmlAttribute
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    @XmlAttribute
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    @XmlElement
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
