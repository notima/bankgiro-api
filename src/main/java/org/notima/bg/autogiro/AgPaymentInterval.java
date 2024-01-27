package org.notima.bg.autogiro;

public class AgPaymentInterval {

    private AgPaymentIntervalEnum interval;

    public static AgPaymentInterval getOnce() {
        AgPaymentInterval pi = new AgPaymentInterval(AgPaymentIntervalEnum.ONCE);
        return pi;
    }

    public AgPaymentInterval(AgPaymentIntervalEnum i) {
        interval = i;
    }

    public AgPaymentIntervalEnum getInterval() {
        return interval;
    }

    public void setInterval(AgPaymentIntervalEnum interval) {
        this.interval = interval;
    }

    public String toRecordString() {
        if (interval == null)
            return ("0");
        return Integer.toString(interval.getIntValue());
    }

}
