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
