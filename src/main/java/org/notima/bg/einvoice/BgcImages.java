package org.notima.bg.einvoice;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "images")
@XmlType(propOrder = { 
    "url", 
    "images"
})
public class BgcImages {
    private String url;
    private List<BgcImage> images;

    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }

    public List<BgcImage> getImages() {
        return images;
    }

    public void setImages(List<BgcImage> images) {
        this.images = images;
    }
}
