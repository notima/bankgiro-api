package org.notima.bg.einvoice;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlType(name = "unit")
public class BgcUnit {

    public static final String UNIT_CODE_LENGTH_MILLIMETER = "MMT";
    public static final String UNIT_CODE_LENGTH_CENTIMETER = "CMT";
    public static final String UNIT_CODE_LENGTH_DECIMETER = "DMT";
    public static final String UNIT_CODE_LENGTH_METER = "MTR";
    public static final String UNIT_CODE_LENGTH_HEKTOMETER = "HMT";
    public static final String UNIT_CODE_LENGTH_KILOMETER = "KMT";

    public static final String UNIT_CODE_MASS_MILLIGRAM = "MGM";
    public static final String UNIT_CODE_MASS_GRAM = "GRM";
    public static final String UNIT_CODE_MASS_HEKTOGRAM = "HGM";
    public static final String UNIT_CODE_MASS_KILOGRAM = "KGM";
    public static final String UNIT_CODE_MASS_TON = "TNE";
    public static final String UNIT_CODE_MASS_KILOTON = "KTN";

    public static final String UNIT_CODE_ENERGY_JOULE = "JOU";
    public static final String UNIT_CODE_ENERGY_KILOJOULE = "KJO";
    public static final String UNIT_CODE_ENERGY_WATTHOUR = "WHR";
    public static final String UNIT_CODE_ENERGY_KILOWATTHOUR = "KWH";
    public static final String UNIT_CODE_ENERGY_MEGAWATTHOUR = "MWH";
    public static final String UNIT_CODE_ENERGY_GIGAWATTHOUR = "GWH";

    public static final String UNIT_CODE_AREA_MILLIMETER_2 = "MMK";
    public static final String UNIT_CODE_AREA_CENTIMETER_2 = "CMK";
    public static final String UNIT_CODE_AREA_DECIMETER_2 = "DMK";
    public static final String UNIT_CODE_AREA_METER_2 = "MTK";
    public static final String UNIT_CODE_AREA_KILOMETER_2 = "KMK";
    public static final String UNIT_CODE_AREA_HECTARE = "HAR";

    public static final String UNIT_CODE_AMOUNT_PIECE = "PCE";
    public static final String UNIT_CODE_AMOUNT_PACKAGE = "PC";
    public static final String UNIT_CODE_AMOUNT_HUNDRED = "CEN";
    public static final String UNIT_CODE_AMOUNT_THOUSAND = "MIL";
    public static final String UNIT_CODE_AMOUNT_MILLION = "MIO";
    public static final String UNIT_CODE_AMOUNT_BILLION = "MLD";
    public static final String UNIT_CODE_AMOUNT_TRILLION = "BIL";
    public static final String UNIT_CODE_AMOUNT_QUINTILLION = "TRL";

    public static final String UNIT_CODE_TIME_SECOND = "SEC";
    public static final String UNIT_CODE_TIME_MINUTE = "MIN";
    public static final String UNIT_CODE_TIME_HOUR = "HUR";
    public static final String UNIT_CODE_TIME_DAY = "DAY";

    private String unit;
    private String code;

    @XmlValue
    public String getUnit() {
        return unit;
    }
    public void setUnit(String unit) {
        this.unit = unit;
    }

    @XmlAttribute
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
}
