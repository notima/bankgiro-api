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

public class BgMaxReference {

    public static final int     REFTYPE_BLANK_NOREF_GIVEN = 0;
    public static final int     REFTYPE_BLANK_NOREF_POSSIBLE = 1;
    public static final int     REFTYPE_OCR = 2;
    public static final int     REFTYPE_OCR_NOCHECK = 3;
    public static final int     REFTYPE_NOOCR = 4;
    public static final int     REFTYPE_OCR_INCORRECT = 5;
    
    public static final int		PAYCHANNEL_EBANK = 1;
    public static final	int		PAYCHANNEL_LB = 2;
    public static final int		PAYCHANNEL_PHYSICAL_FORM = 3;
    public static final int		PAYCHANNEL_AUTOGIRO = 4;

    public String	senderBg;
    public String	bgcRef;
	public String	reference;
	public double	amount;
	public int		referenceType;
	public int		payChannel;
	public boolean	scannedImage;
	
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getReferenceType() {
		return referenceType;
	}
	public void setReferenceType(int referenceType) {
		this.referenceType = referenceType;
	}
	public int getPayChannel() {
		return payChannel;
	}
	public void setPayChannel(int payChannel) {
		this.payChannel = payChannel;
	}
	public boolean isScannedImage() {
		return scannedImage;
	}
	public void setScannedImage(boolean scannedImage) {
		this.scannedImage = scannedImage;
	}
	public String getSenderBg() {
		return senderBg;
	}
	public void setSenderBg(String senderBg) {
		this.senderBg = senderBg;
	}
	public String getBgcRef() {
		return bgcRef;
	}
	public void setBgcRef(String bgcRef) {
		this.bgcRef = bgcRef;
	}
	
	
	
}
