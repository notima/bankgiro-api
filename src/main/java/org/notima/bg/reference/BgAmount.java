package org.notima.bg.reference;

import java.math.BigInteger;

import org.notima.util.NotimaUtil;

public class BgAmount extends BgReference {

    private BigInteger  amount;
    private int         divisor = 100;

    public BgAmount() {
        amount = BigInteger.valueOf(0);
    }

    public BgAmount(double amountDouble) {
        amount = BigInteger.valueOf(Math.round(amountDouble*divisor));
    }

    public double getAmountAsDouble() {
        return Double.valueOf(amount.doubleValue()/divisor);
    }

    @Override
    public String toMachineFormat(int positionsWide) {
        return NotimaUtil.fillToLength(amount.toString(), true, '0', positionsWide);
    }

    @Override
    public String toHumanReadable() {
        return Double.toString(getAmountAsDouble());
    }

}
