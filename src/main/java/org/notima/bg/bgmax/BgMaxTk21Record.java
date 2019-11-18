/** ===================================================================
	Bankgiro Java API
    
    Copyright (C) 2009  Daniel Tamm
                        Notima Consulting Group Ltd

    This API-library is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    any later version.

    This API-library is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this API-library.  If not, see <http://www.gnu.org/licenses/>.

    =================================================================== */

package org.notima.bg.bgmax;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.notima.bg.BgParseException;
import org.notima.bg.BgRecord;
import org.notima.bg.BgUtil;


public class BgMaxTk21Record extends BgRecord {

	public static final int REDUCTION_FULL_CLOSED = 0;
	public static final int REDUCTION_PART_OPEN = 1;
	public static final int REDUCTION_FINAL_CLOSED = 2;
	
	private static Pattern	linePattern1 = Pattern.compile("21(\\d{10})(.{25})(\\d{18})(\\d)(\\d)(\\d{12})(\\d)(\\d).*");	
	
	private String	senderBg;
	private BgMaxReference reference = new BgMaxReference();
	private String	bgcRef;
	private int		reductionType;
	
	public BgMaxTk21Record() {
		super("21");
	}

	@Override
	public BgRecord parse(String line) throws BgParseException {
		Matcher m = linePattern1.matcher(line);
		if (m.matches()) {
			senderBg = BgUtil.trimLeadingZeros(m.group(1));
			reference.reference = BgUtil.trimLeadingZeros(m.group(2));
			reference.amount = BgUtil.parseAmountStr(m.group(3));
			reference.referenceType = Integer.parseInt(m.group(4));
			reference.payChannel = Integer.parseInt(m.group(5));
			bgcRef = m.group(6);
			reference.scannedImage = Integer.parseInt(m.group(7)) >0;
			reductionType = Integer.parseInt(m.group(8));
			return(this);
		} else {
			throw new BgParseException(line);
		}
	}

	public BgMaxReference getReferenceRecord() {
		return(reference);
	}
	
	@Override
	public String toRecordString() {

		StringBuffer line = new StringBuffer(getTransCode());

		StringBuffer seqStr;
        if (senderBg==null) {
            seqStr = new StringBuffer();
            while(seqStr.length()<10) {
                seqStr.append(" ");
            }
        } else {
            seqStr = new StringBuffer(senderBg);
            while(seqStr.length()<10) {
                // Prepend with zeroes
                seqStr.insert(0, "0");
            }
        }
		line.append(seqStr);
		
		// Reference
		String refStr = reference!=null ? reference.reference : "";
		String ref = BgUtil.fillToLength(refStr, false, ' ', 25);
		line.append(ref);
		
		// Amount
		line.append(BgUtil.getAmountStr(getAmount(), 18, false));
		
		// Reference Type
		line.append(reference!=null ? reference.referenceType : BgMaxReference.REFTYPE_BLANK_NOREF_GIVEN);

		// Paychannel
		line.append(reference!=null ? reference.payChannel : BgMaxReference.PAYCHANNEL_EBANK);
		
		// BGC documentnumber
		ref = BgUtil.fillToLength(bgcRef, true, '0', 12);
		line.append(ref);
		
		// Image marker
		line.append(reference!=null ? (reference.scannedImage ? "1" : "0") : "0");
		
		// Reduction code
		line.append(reductionType); // Default - Complete reduction, no remainder
		
		// Pad with spaces
		while(line.length()<81) {
			line.append(" ");
		}
		
		return line.toString();
		
	}

	public String getSenderBg() {
		return senderBg;
	}

	public void setSenderBg(String senderBg) {
		this.senderBg = senderBg;
	}

	public String getReference() {
		return reference.reference;
	}

	public void setReference(String reference) {
		this.reference.reference = reference;
	}

	public double getAmount() {
		return reference.amount;
	}

	public void setAmount(double amount) {
		this.reference.amount = amount;
	}

	public int getReferenceType() {
		return reference.referenceType;
	}

	public void setReferenceType(int referenceType) {
		this.reference.referenceType = referenceType;
	}

	public int getPayChannel() {
		return reference.payChannel;
	}

	public void setPayChannel(int payChannel) {
		this.reference.payChannel = payChannel;
	}

	public String getBgcRef() {
		return bgcRef;
	}

	public void setBgcRef(String bgcRef) {
		this.bgcRef = bgcRef;
	}

	public boolean hasScannedImage() {
		return reference.scannedImage;
	}

	public void setScannedImage(boolean scannedImage) {
		this.reference.scannedImage = scannedImage;
	}

	public int getReductionType() {
		return reductionType;
	}

	public void setReductionType(int reductionType) {
		this.reductionType = reductionType;
	}
	
	
}
