package org.notima.bg.autogiro;

public enum AgPaymentIntervalEnum {

    ONCE(0),
    MONTHLY(1),
    QUARTERLY(2),
    SEMIANNUALLY(3),
    ANNUALLY(4),
    MONTHLY_END_OF_MONTH(5),
    QUARTERLY_END_OF_MONTH(6),
    SEMIANNUALLY_END_OF_MONTH(7),
    ANNUALLY_END_OF_MONTH(8);

    private int intValue;

    private AgPaymentIntervalEnum(int val) {
        intValue = val;
    }

    public int getIntValue() {
        return intValue;
    };

}
