package org.notima.bg.reference;

import org.notima.util.InvalidTaxIdFormatException;
import org.notima.util.NotimaUtil;
import org.notima.util.TaxIdFormatter;
import org.notima.util.TaxIdStructure;
import org.notima.util.UnknownTaxIdFormatException;

public class BgTaxId extends BgReference {

	private String internalTaxId;
	private String taxIdFormat;
	private String countryCode;

	public BgTaxId(String taxId, String countryCode) throws InvalidReferenceException, InvalidTaxIdFormatException, UnknownTaxIdFormatException {
		
		taxIdFormat = TaxIdFormatter.determineFormat(countryCode, taxId);
		
		if (TaxIdStructure.FMT_UNKNOWN.equals(taxIdFormat)) {
			throw new InvalidReferenceException(taxId + " is not a valid tax id");
		}
		
		this.countryCode = countryCode;
		
		// Convert to preferred format
		if ("SE".equalsIgnoreCase(countryCode)) {
			internalTaxId = TaxIdFormatter.printTaxId(taxIdFormat, taxId, TaxIdStructure.FMT_SE12);
			taxIdFormat = TaxIdStructure.FMT_SE12;
		} else {
			internalTaxId = taxId;
		}
		
	}
	
	@Override
	public String toMachineFormat(int positionsWide) {

		if (positionsWide>10) {
			String tmpTaxId = internalTaxId;
			if (internalTaxId.startsWith("16")) {
				tmpTaxId = internalTaxId.substring(2, internalTaxId.length());
			}
			return NotimaUtil.fillToLength(tmpTaxId, true, '0', positionsWide);
		} else {
			try {
				return TaxIdFormatter.printTaxId(taxIdFormat, internalTaxId, TaxIdStructure.FMT_SE10);
			} catch (UnknownTaxIdFormatException | InvalidTaxIdFormatException e) {
				return (NotimaUtil.fillToLength(NotimaUtil.toDigitsOnly(internalTaxId), true, '0', positionsWide));
			}
		}
		
	}

	@Override
	public String toHumanReadable() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getInternalTaxId() {
		return internalTaxId;
	}

	public void setInternalTaxId(String internalTaxId) {
		this.internalTaxId = internalTaxId;
	}

	public String getTaxIdFormat() {
		return taxIdFormat;
	}

	public void setTaxIdFormat(String taxIdFormat) {
		this.taxIdFormat = taxIdFormat;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	
	
}
