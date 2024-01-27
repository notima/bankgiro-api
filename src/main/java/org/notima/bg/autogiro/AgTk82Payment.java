package org.notima.bg.autogiro;

import org.notima.bg.BgParseException;
import org.notima.bg.BgRecord;
import org.notima.bg.reference.BgAmount;
import org.notima.bg.reference.BgBankgiroAccount;
import org.notima.bg.reference.BgDate;
import org.notima.bg.reference.BgPayerNumber;
import org.notima.bg.reference.BgReference;
import org.notima.util.NotimaUtil;

public class AgTk82Payment extends BgRecord implements AgTransaction {

	private BgDate	payDate;	
    private AgPaymentInterval paymentInterval;
    private int     numberOfRenewals;
    private BgPayerNumber   payerNumber;
    private BgAmount        amount;
    private BgBankgiroAccount  recipientBgAccount;
    private BgReference     reference;

	public AgTk82Payment() {
		super("82");
        payDate = new BgDate();
        payDate.setLocalDate(null); // Immediate as default
	}
    
    public BgDate getPayDate() {
        return payDate;
    }

    public void setPayDate(BgDate payDate) {
        this.payDate = payDate;
    }

    public AgPaymentInterval getPaymentInterval() {
        return paymentInterval;
    }

    public void setPaymentInterval(AgPaymentInterval paymentInterval) {
        this.paymentInterval = paymentInterval;
    }

    public int getNumberOfRenewals() {
        return numberOfRenewals;
    }

    public void setNumberOfRenewals(int numberOfRenewals) {
        this.numberOfRenewals = numberOfRenewals;
    }

    public BgPayerNumber getPayerNumber() {
        return payerNumber;
    }

    public void setPayerNumber(BgPayerNumber payerNumber) {
        this.payerNumber = payerNumber;
    }

    public BgAmount getAmount() {
        return amount;
    }

    public void setAmount(BgAmount amount) {
        this.amount = amount;
    }

    public BgBankgiroAccount getRecipientBgAccount() {
        return recipientBgAccount;
    }

    public void setRecipientBgAccount(BgBankgiroAccount recipientBgAccount) {
        this.recipientBgAccount = recipientBgAccount;
    }

    public BgReference getReference() {
        return reference;
    }

    public void setReference(BgReference reference) {
        this.reference = reference;
    }

    @Override
    public BgRecord parse(String line) throws BgParseException {
        throw new UnsupportedOperationException("Unimplemented method 'parse'");
    }

    @Override
    public String toRecordString() {

        StringBuffer line = new StringBuffer(getTransCode());
        line.append(payDate.toMachineFormat(8));
        line.append(paymentInterval.toRecordString());
        line.append(toNumberOfRenewalsString());
        line.append(" "); // Reserve
        line.append(payerNumber.toMachineFormat(16));
        line.append(amount.toMachineFormat(12));
        line.append(recipientBgAccount.toMachineFormat(10));
        line.append(reference.toMachineFormat(16));
        line.append(NotimaUtil.fillToLength(" ", true, ' ', 11));

        return line.toString();
    }

    public boolean isRecurringPayment() {
        if (paymentInterval!=null && paymentInterval.getInterval().getIntValue()>0) {
            return true;
        }
        return false;
    }

    private String toNumberOfRenewalsString() {
        if (!isRecurringPayment()) {
            return "   ";
        }
        if (numberOfRenewals<=0) {
            return "   ";
        } else {
            if (numberOfRenewals>999) {
                numberOfRenewals = 999;
            }
            return 
                NotimaUtil.fillToLength(
                    Integer.toString(numberOfRenewals), 
                    true, 
                    '0', 3);
        }
    }

}
