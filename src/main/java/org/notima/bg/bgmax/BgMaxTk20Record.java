/** ===================================================================
	Bankgiro Java API
    
    Copyright (C) 2009  Daniel Tamm
						Notima Consulting Group Ltd
						
	Copyright (C) 2019  Notima System Integration AB

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.						

    =================================================================== */


package org.notima.bg.bgmax;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.notima.bg.BgParseException;
import org.notima.bg.BgRecord;
import org.notima.util.NotimaUtil;


public class BgMaxTk20Record extends BgRecord {

	private static Pattern	linePattern1 = Pattern.compile("20(\\d{10})(.{25})(\\d{18})(\\d)(\\d)(\\d{12})(\\d).*");	
	
	private String	senderBg;
	private BgMaxReference reference = new BgMaxReference();
	private String	bgcRef;
	
	public BgMaxTk20Record() {
		super("20");
	}

	@Override
	public BgRecord parse(String line) throws BgParseException {
		Matcher m = linePattern1.matcher(line);
		if (m.matches()) {
			senderBg = NotimaUtil.trimLeadingZeros(m.group(1));
			reference.reference = NotimaUtil.trimLeadingZeros(m.group(2));
			reference.amount = NotimaUtil.parseAmountStr(m.group(3));
			reference.referenceType = Integer.parseInt(m.group(4));
			reference.payChannel = Integer.parseInt(m.group(5));
			bgcRef = m.group(6);
			reference.scannedImage = Integer.parseInt(m.group(7)) >0;
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
		String ref = NotimaUtil.fillToLength(refStr, false, ' ', 25);
		line.append(ref);
		
		// Amount
		line.append(NotimaUtil.getAmountStr(getAmount(), 18, false));
		
		// Reference Type
		line.append(reference!=null ? reference.referenceType : BgMaxReference.REFTYPE_BLANK_NOREF_GIVEN);

		// Paychannel
		line.append(reference!=null ? reference.payChannel : BgMaxReference.PAYCHANNEL_EBANK);
		
		// BGC documentnumber
		ref = NotimaUtil.fillToLength(bgcRef, true, '0', 12);
		line.append(ref);
		
		// Image marker
		line.append(reference!=null ? (reference.scannedImage ? "1" : "0") : "0");

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

}
