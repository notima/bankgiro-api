package org.notima.bg.reference;

public class BgTextReference extends BgReference {

    private String  text;

    public BgTextReference(String text) {
        this.text = text;
    }

    @Override
    public String toMachineFormat(int positionsWide) {
        return org.notima.util.NotimaUtil.fillToLength(text, false, ' ', positionsWide);
    }

    @Override
    public String toHumanReadable() {
        return text;
    }

}
