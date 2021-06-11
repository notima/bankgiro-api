package org.notima.bg.autogiro;

import org.notima.bg.BgFooter;
import org.notima.bg.BgParseException;
import org.notima.bg.reference.BgDate;
import org.notima.util.NotimaUtil;

public class AgTk09Footer extends BgFooter {

	private BgDate	createDate;	
	private int paymentCount = 0;
	
	public AgTk09Footer() {
		super("09");
		createDate = new BgDate();
	}

	@Override
	public String toRecordString() {

		StringBuffer line = new StringBuffer(getTransCode());
		line.append(createDate.toMachineFormat(8));
		line.append("9900"); // Bankgirots clearing number for some reason.
		line.append(NotimaUtil.fillToLength(Integer.toString(paymentCount), true, '0', 6));
		
		return line.toString();
	}

	@Override
	public BgFooter parse(String line) throws BgParseException {
		// TODO Auto-generated method stub
		return null;
	}

}
