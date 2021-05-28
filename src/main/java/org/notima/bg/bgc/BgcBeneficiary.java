package org.notima.bg.bgc;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "beneficiary")
public class BgcBeneficiary {
    private String bic;
    private String id;
    private String name;
    private BgcAddress address;
    private BgcAccount account;
}
