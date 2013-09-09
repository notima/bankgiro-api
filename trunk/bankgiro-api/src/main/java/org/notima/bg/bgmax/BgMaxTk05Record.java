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

	public void setRecipientBg(String recipientBg) {
		this.recipientBg = recipientBg;
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
		// TODO Auto-generated method stub
		return null;
	}


}