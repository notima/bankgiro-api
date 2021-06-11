package org.notima.bg.autogiro;

import org.notima.bg.reference.BgCodeReference;
import org.notima.bg.reference.InvalidReferenceException;
import org.notima.util.NotimaUtil;

public class AgContentCode extends BgCodeReference {

	public static String CONSENTS = "AG-MEDAVI";
	public static String PAYMENTS = "BET.SPEC";
	public static String CANCELLATIONS = "STOPP TK";
	
	public AgContentCode(String code) throws InvalidReferenceException {
		super(code);
	}
	
	@Override
	protected void initValidCodes() {

		validCodes = new String[] {
			CONSENTS,
			PAYMENTS,
			CANCELLATIONS
		};

	}

	@Override
	public String toMachineFormat(int positionsWide) {
		return NotimaUtil.fillToLength(code, false, ' ', positionsWide);
	}

	@Override
	public String toHumanReadable() {
		return code;
	}

}
