package org.notima.bg.reference;

import org.notima.util.NotimaUtil;

/**
 * Customer number at Bankgirot.
 * 
 * @author Daniel Tamm
 *
 */
public class BgCustomerNumber extends BgReference {

	private String customerNumber;
	
	public BgCustomerNumber(String payerNumber) {
		this.customerNumber = payerNumber;
	}
	
	@Override
	public String toMachineFormat(int positionsWide) {
		
		return NotimaUtil.fillToLength(customerNumber, true, '0', positionsWide);
		
	}

	@Override
	public String toHumanReadable() {
		return customerNumber;
	}

}
