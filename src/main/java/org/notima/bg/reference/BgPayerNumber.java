package org.notima.bg.reference;

import org.notima.util.NotimaUtil;

/**
 * Payer number (betalarnummer) is used primarily for Autogiro.
 * It references the customer in the recipient's customer register.
 * 
 * @author Daniel Tamm
 *
 */
public class BgPayerNumber extends BgReference {

	private String payerNumber;
	
	public BgPayerNumber(String payerNumber) {
		this.payerNumber = payerNumber;
	}
	
	@Override
	public String toMachineFormat(int positionsWide) {
		
		return NotimaUtil.fillToLength(payerNumber, true, '0', positionsWide);
		
	}

	@Override
	public String toHumanReadable() {
		return payerNumber;
	}

}
