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

package se.notima.bg.bgmax;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import se.notima.bg.BgParseException;
import se.notima.bg.BgRecord;
import se.notima.bg.BgUtil;

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
			senderBg = BgUtil.trimLeadingZeros(m.group(1));
			reference.reference = BgUtil.trimLeadingZeros(m.group(2));
			reference.amount = BgUtil.parseAmountStr(m.group(3));
			reference.referenceType = new Integer(m.group(4));
			reference.payChannel = new Integer(m.group(5));
			bgcRef = m.group(6);
			reference.scannedImage = new Integer(m.group(7)).intValue()>0;
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
		// TODO Auto-generated method stub
		return null;
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
