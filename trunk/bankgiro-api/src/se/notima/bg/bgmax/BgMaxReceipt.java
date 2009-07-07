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

import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import se.notima.bg.BgRecord;
import se.notima.bg.Transaction;

public class BgMaxReceipt implements Transaction {

	private Vector<BgRecord>	records = new Vector<BgRecord>();
	private double				amount;
	private Date				transDate;
	private String				senderBg;
	private String				name1;
	private String				name2;
	private Vector<String>		infoRecords = new Vector<String>();
	private String				address;
	private String				zipCode;
	private String				city;
	private String				country;
	private String				countryCode;
	private String				taxId;
	private int					reductionType;
	private Vector<BgMaxReference> references = new Vector<BgMaxReference>();
	private String				reference;
	private int					referenceType;
	private int					payChannel;
	private String				bgcRef;
	private boolean				scannedImage;
	
	public BgMaxReceipt(Date transDate) {
		this.transDate = transDate;
	}

	@Override
	public void addRecord(BgRecord record) {
		int code = new Integer(record.getTransCode()).intValue();
		if (code==20) {
			BgMaxTk20Record r = (BgMaxTk20Record)record;
			amount = r.getAmount();
			senderBg = r.getSenderBg();
			reference = r.getReference();
			referenceType = r.getReferenceType();
			payChannel = r.getPayChannel();
			bgcRef = r.getBgcRef();
			scannedImage = r.hasScannedImage();
			references.add(r.getReferenceRecord());
		}
		if (code==21) {
			BgMaxTk21Record r = (BgMaxTk21Record)record;
			amount = -r.getAmount();
			senderBg = r.getSenderBg();
			reference = r.getReference();
			referenceType = r.getReferenceType();
			payChannel = r.getPayChannel();
			bgcRef = r.getBgcRef();
			scannedImage = r.hasScannedImage();
			reductionType = r.getReductionType();
			references.add(r.getReferenceRecord());
		}
		if (code==22) {
			BgMaxTk22Record r = (BgMaxTk22Record)record;
			references.add(r.getReferenceRecord());
		}
		if (code==23) {
			BgMaxTk23Record r = (BgMaxTk23Record)record;
			references.add(r.getReferenceRecord());
		}
		if (code==25) {
			BgMaxTk25Record r = (BgMaxTk25Record)record;
			infoRecords.add(r.getInfoText());
		}
		if (code==26) {
			BgMaxTk26Record r = (BgMaxTk26Record)record;
			name1 = r.getName1();
			name2 = r.getName2();
		}
		if (code==27) {
			BgMaxTk27Record r = (BgMaxTk27Record)record;
			address = r.getAddress();
			zipCode = r.getZipCode();
		}
		if (code==28) {
			BgMaxTk28Record r = (BgMaxTk28Record)record;
			city = r.getCity();
			country = r.getCountry();
			countryCode = r.getCountryCode();
		}
		if (code==29) {
			BgMaxTk29Record r = (BgMaxTk29Record)record;
			taxId = r.getTaxId();
		}
		records.add(record);
	}
	
	@Override
	public double getAmount() {
		return amount;
	}

	/**
	 * Set's the sequence number for this payment
	 * 
	 * @param seqNo
	 */
	public void setSeqNo(int seqNo) {
		if (records!=null && records.size()>0) {
			for (int i=0; i<records.size(); i++) {
				records.get(i).setSeqNo(seqNo);
			}
		}
	}

	@Override
	public String toRecordString() {
		StringBuffer lines = new StringBuffer();
		if (records!=null && records.size()>0) {
			for (int i=0; i<records.size(); i++) {
				lines.append(records.get(i).toRecordString() + "\n");
			}
		}
		return(lines.toString());
	}


	@Override
	public Date getTransactionDate() {
		return transDate;
	}

	@Override
	public void setTransactionDate(Date d) {
		transDate = d;
	}

	public String getSenderBg() {
		return senderBg;
	}

	public void setSenderBg(String srcBg) {
		this.senderBg = srcBg;
	}

	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public String getName2() {
		return name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

	public Vector<String> getInfoRecords() {
		return infoRecords;
	}

	public void setInfoRecords(Vector<String> infoRecords) {
		this.infoRecords = infoRecords;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getTaxId() {
		return taxId;
	}

	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getReductionType() {
		return reductionType;
	}

	public void setReductionType(int reductionType) {
		this.reductionType = reductionType;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
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

	public String getBgcRef() {
		return bgcRef;
	}

	public void setBgcRef(String bgcRef) {
		this.bgcRef = bgcRef;
	}

	public boolean isScannedImage() {
		return scannedImage;
	}

	public void setScannedImage(boolean scannedImage) {
		this.scannedImage = scannedImage;
	}
	
	/**
	 * 
	 * @return	Returns all information available (except the reference)
	 */
	public String getAllInfo() {
		
		StringBuffer buf = new StringBuffer();
		Vector<String> info = getInfoRecords();
		String line;
		if (info!=null && info.size()>0) {
			for (Iterator<String> it = info.iterator(); it.hasNext();) {
				line = it.next();
				if (line!=null && line.trim().length()>0) {
					buf.append(line + "\n");
				}
			}
		}
		
		if (getAddress()!=null) buf.append(getAddress() + "\n");
		if (getZipCode()!=null) buf.append(getZipCode() + " ");
		if (getCity()!=null) buf.append(getCity());
		if(buf.charAt(buf.length()-1)=='\n') buf.deleteCharAt(buf.length()-1);
		
		return(buf.toString());
	}
	

}