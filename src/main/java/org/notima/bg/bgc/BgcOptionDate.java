package org.notima.bg.bgc;

import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlType(name = "optionDate")
public class BgcOptionDate {
    public static final String OPTION_DATE_TYPE_DEBIT = "debit";
    public static final String OPTION_DATE_TYPE_OUR = "OUR";
    public static final String OPTION_DATE_TYPE_BEN = "BEN";
    public static final String OPTION_DATE_TYPE_SHA = "SHA";

    private Date date;
    private String type;

    @XmlValue
    @XmlJavaTypeAdapter(BgcDateFormatAdapter.class)
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    @XmlAttribute
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
}
