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
import org.notima.bg.BgUtil;


public class BgMaxTk05Record extends BgRecord {
	
	private static Pattern	linePattern1 = Pattern.compile("05(\\d{10})([\\d|\\s]{10})(\\w{3}).*");

	private String 	recipientBg;
	private String	recipientPg;
	private String	currency;
	
	public BgMaxTk05Record() {
		super("05");
	}

	@Override
	public BgRecord parse(String line) throws BgParseException {
		Matcher m = linePattern1.matcher(line);
		if (m.matches()) {
			recipientBg = BgUtil.trimLeadingZeros(m.group(1));
			recipientPg = BgUtil.trimLeadingZeros(m.group(2));
			currency = m.group(3);
			return(this);
		} else {
			throw new BgParseException(line);
		}
		
	}

	public String getRecipientBg() {
		return recipientBg;
	}

	public void setRecipientBg(String aRecipientBg) {
		recipientBg = aRecipientBg;
		if (recipientBg==null) return;
		recipientBg = BgUtil.toDigitsOnly(recipientBg);
	}

	public String getRecipientPg() {
		return recipientPg;
	}

	public void setRecipientPg(String recipientPg) {
		this.recipientPg = recipientPg;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@Override
	public String toRecordString() {
		StringBuffer line = new StringBuffer(getTransCode());

		line.append(BgUtil.fillToLength(BgUtil.toDigitsOnly(recipientBg), true, '0', 10));
		line.append(BgUtil.fillToLength(BgUtil.toDigitsOnly(recipientPg), true, 
				((recipientPg==null || recipientPg.trim().length()==0) ? ' ' : '0'), 10));
		line.append(BgUtil.fillToLength(currency, true, ' ', 3));
		
		// Pad to 80
		while(line.length()<80) {
			line.append(" ");
		}
		
		return line.toString();
	}


}
