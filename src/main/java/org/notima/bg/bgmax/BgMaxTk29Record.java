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

import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.notima.bg.BgParseException;
import org.notima.bg.BgRecord;
import org.notima.bg.BgUtil;
import org.notima.util.NotimaUtil;


public class BgMaxTk29Record extends BgRecord {

	private static Pattern	linePattern1 = Pattern.compile("29(\\d{12}).*");	

	private String	taxId;
	
	public BgMaxTk29Record() {
		super("29");
	}

	@Override
	public BgRecord parse(String line) throws BgParseException {
		Matcher m = linePattern1.matcher(line);
		if (m.matches()) {
			taxId = NotimaUtil.trimLeadingZeros(m.group(1));
			return(this);
		} else {
			throw new BgParseException(line);
		}
	}

	@Override
	public String toRecordString() {
		StringBuffer line = new StringBuffer(transCode);
		try {
			taxId = BgUtil.formatTaxId(taxId);
		} catch (ParseException pe) {
			pe.printStackTrace();
		}
		line.append(NotimaUtil.fillToLength(taxId,  true,  '0',  12));
		while(line.length()<80) {
			line.append(" ");
		}
		return line.toString();
	}

	public String getTaxId() {
		return taxId;
	}

	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}
	
	

}
