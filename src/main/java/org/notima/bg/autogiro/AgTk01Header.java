package org.notima.bg.autogiro;

import java.util.Date;

import org.notima.bg.BgHeader;
import org.notima.bg.BgParseException;
import org.notima.bg.BgRecord;
import org.notima.bg.reference.BgBankgiroAccount;
import org.notima.bg.reference.BgCustomer;
import org.notima.bg.reference.BgCustomerNumber;
import org.notima.bg.reference.BgDate;
import org.notima.util.NotimaUtil;

public class AgTk01Header extends BgHeader {

	private BgDate	createDate;
	private AgContentCode 	contentCode;
	private BgBankgiroAccount	bgRecipient;
	private BgCustomerNumber	bgCustomerNo;
	
	public AgTk01Header(AgContentCode contentCode, BgCustomer recipient) {
		super("01");
		recordOwner = recipient;
		createDate = new BgDate();
		this.contentCode = contentCode;
		bgRecipient = recipient.getBgAccount();
		bgCustomerNo = recipient.getCustomerNo();
	}

	@Override
	public String getCurrency() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCurrency(String currency) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getSenderAccount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getPayDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getCreateDate() {
		return createDate.asDate();
	}

	@Override
	public BgRecord parse(String line) throws BgParseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toRecordString() {

		StringBuffer line = new StringBuffer(getTransCode());
		line.append(createDate.toMachineFormat(8));
		line.append(NotimaUtil.fillToLength("AUTOGIRO", false, ' ', 8));
		line.append(NotimaUtil.fillToLength("", false, ' ', 44));
		line.append(bgCustomerNo.toMachineFormat(6));
		line.append(bgRecipient.toMachineFormat(10));
		line.append(NotimaUtil.fillToLength("", false, ' ', 2));
		
		return line.toString();
	}

}
