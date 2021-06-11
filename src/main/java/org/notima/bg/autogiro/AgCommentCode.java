package org.notima.bg.autogiro;

import org.notima.bg.reference.BgCodeReference;
import org.notima.bg.reference.InvalidReferenceException;
import org.notima.util.NotimaUtil;

public class AgCommentCode extends BgCodeReference {

	public static String CANCELLED_BY_PAYER_OR_BANK = "02";
	public static String ACCOUNT_TYPE_NOT_APPROVED = "03";
	public static String CONSENT_MISSING = "04";
	public static String INVALID_ACCOUNT_OR_TAXID = "05";
	public static String CANCELLED_UNANSWERED_ACCOUNT_QUERY = "07";
	public static String UNKNOWN_BANKGIRO = "09";
	public static String CONSENT_ALREADY_GIVEN = "10";
	public static String INVALID_TAXID_OR_NO_CONSENT = "20";
	public static String INVALID_PAYERNUMBER = "21";
	public static String INVALID_BANKACCOUNT = "23";
	public static String INVALID_RECIPIENT = "29";
	public static String RECIPIENT_BANKGIRO_CLOSED = "30";
	public static String NEW_CONSENT = "32";
	public static String CANCELLED = "33";
	public static String CANCELLED_PAYER_BANKGIRO_CLOSED = "98";
	
	public AgCommentCode(String code) throws InvalidReferenceException {
		super(code);
	}
	
	@Override
	public String toMachineFormat(int positionsWide) {
		return NotimaUtil.fillToLength(code ,true, '0', positionsWide);
	}
	
	@Override
	public String toHumanReadable() {
		return code;
	}
	
	@Override
	protected void initValidCodes() {
	
		validCodes = new String[] {
				
				CANCELLED_BY_PAYER_OR_BANK,
				ACCOUNT_TYPE_NOT_APPROVED,
				CONSENT_MISSING,
				INVALID_ACCOUNT_OR_TAXID,
				CANCELLED_UNANSWERED_ACCOUNT_QUERY,
				UNKNOWN_BANKGIRO,
				CONSENT_ALREADY_GIVEN,
				INVALID_TAXID_OR_NO_CONSENT,
				INVALID_PAYERNUMBER,
				INVALID_BANKACCOUNT,
				INVALID_RECIPIENT,
				RECIPIENT_BANKGIRO_CLOSED,
				NEW_CONSENT,
				CANCELLED,
				CANCELLED_PAYER_BANKGIRO_CLOSED				
				
		};
		
	}

	
	
}
