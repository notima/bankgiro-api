package org.notima.bg.autogiro;

public class AgPaymentInterval {

    private AgPaymentIntervalEnum interval;

    public static AgPaymentInterval getOnce() {
        AgPaymentInterval pi = new AgPaymentInterval(AgPaymentIntervalEnum.ONCE);
        return pi;
    }

    public AgPaymentInterval(int iv) {
    	switch(iv) {
    		case 0: interval = AgPaymentIntervalEnum.ONCE;
    		break;
    		case 1: interval = AgPaymentIntervalEnum.MONTHLY;
    		break;
    		case 2: interval = AgPaymentIntervalEnum.QUARTERLY;
    		break;
    		case 3: interval = AgPaymentIntervalEnum.SEMIANNUALLY;
    		break;
    		case 4: interval = AgPaymentIntervalEnum.ANNUALLY;
    		break;
    		case 5: interval = AgPaymentIntervalEnum.MONTHLY_END_OF_MONTH;
    		break;
    		case 6: interval = AgPaymentIntervalEnum.QUARTERLY_END_OF_MONTH;
    		break;
    		case 7: interval = AgPaymentIntervalEnum.SEMIANNUALLY_END_OF_MONTH;
    		break;
    		case 8: interval = AgPaymentIntervalEnum.ANNUALLY_END_OF_MONTH;
    	}
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
