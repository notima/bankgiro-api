package org.notima.bg.bgc;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "webRequest")
@XmlType(propOrder = { 
    "url", 
    "webParameter"
})
public class BgcWebRequest {
    private String url;
    private List<String> webParameters;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @XmlElement(name = "webParameter")
    public List<String> getWebParameters() {
        return webParameters;
    }

    public void setWebParameters(List<String> webParameters) {
        this.webParameters = webParameters;
    }
}
