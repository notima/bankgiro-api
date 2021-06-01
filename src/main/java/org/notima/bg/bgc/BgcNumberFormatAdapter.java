package org.notima.bg.bgc;


import java.text.DecimalFormat;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class BgcNumberFormatAdapter extends XmlAdapter<String, Double> {
    String pattern = "0.00";
    DecimalFormat decimalFormat = new DecimalFormat(pattern);

    @Override
    public Double unmarshal(String v) throws Exception {
        if(v != null && !v.isEmpty()){
            return decimalFormat.parse(v).doubleValue();
        }else{
            return null;
        }
    }

    @Override
    public String marshal(Double v) throws Exception {
        if(v != null){
            return decimalFormat.format(v);
        }else{
            return null;
        }
    }
    
}
