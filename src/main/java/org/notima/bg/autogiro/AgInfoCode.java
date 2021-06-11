package org.notima.bg.autogiro;

import org.notima.bg.reference.BgCodeReference;
import org.notima.bg.reference.InvalidReferenceException;
import org.notima.util.NotimaUtil;

public class AgInfoCode extends BgCodeReference {

	public static String CANCELED_BY_RECIPIENT = "03";
	public static String INITED_BY_RECIPIENT = "04";
	public static String CHANGE_OF_PAYER_NUMBER_INITED_BY_PAYER = "05";
	public static String CANCELLED_RECIPIENTS_BG_CANCELLED = "10";
	public static String REPLY_BANKACCOUNT_QUERY_FROM_BANK = "42";
	public static String CANCELLED_UNANSWERED_BANKACCOUNT_QUERY = "43";
	public static String CANCELLED_PAYERS_ACCOUNT_CANCELLED = "44";
	public static String CANCELLED_BY_PAYER_OR_BANK = "02";

	@Override
	protected void initValidCodes() {

		validCodes = new String[] {
				CANCELED_BY_RECIPIENT,
				INITED_BY_RECIPIENT,
				CHANGE_OF_PAYER_NUMBER_INITED_BY_PAYER,
				CANCELLED_RECIPIENTS_BG_CANCELLED,
				REPLY_BANKACCOUNT_QUERY_FROM_BANK,
				CANCELLED_UNANSWERED_BANKACCOUNT_QUERY,
				CANCELLED_PAYERS_ACCOUNT_CANCELLED,
				CANCELLED_BY_PAYER_OR_BANK
		};
		
	}
	
	/**
	 * Constructor. Only takes valid codes.
	 * 
	 * @param code			The code.
	 * @throws InvalidReferenceException
	 */
	public AgInfoCode(String code) throws InvalidReferenceException {

		super();
		
		if (code==null || code.trim().length()==0) 
			throw new InvalidReferenceException("Info code can't be empty");
		
		if (!isValidCode(code)) {
			throw new InvalidReferenceException(code);
		}
		
		this.code = code;
	}
	
	
	@Override
	public String toMachineFormat(int positionsWide) {
		return NotimaUtil.fillToLength(code ,true, '0', positionsWide);
	}
	@Override
	public String toHumanReadable() {
		return code;
	}
	
	
}
