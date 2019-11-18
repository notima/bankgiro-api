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


public class BgMaxTk23Record extends BgRecord {

	private static Pattern	linePattern1 = Pattern.compile("23(\\d{10})(.{25})(\\d{18})(\\d)(\\d)(\\d{12})(\\d).*");	
	
	private BgMaxReference reference = new BgMaxReference();	
	
	public BgMaxTk23Record() {
		super("23");
	}

	@Override
	public BgRecord parse(String line) throws BgParseException {
		Matcher m = linePattern1.matcher(line);
		if (m.matches()) {
			reference.reference = BgUtil.trimLeadingZeros(m.group(2));
			reference.amount = BgUtil.parseAmountStr(m.group(3));
			reference.referenceType = Integer.parseInt(m.group(4));
			reference.payChannel = Integer.parseInt(m.group(5));
			reference.scannedImage = Integer.parseInt(m.group(7)) >0;
			return(this);
		} else {
			throw new BgParseException(line);
		}
	}

	@Override
	public String toRecordString() {
		// TODO Auto-generated method stub
		return null;
	}

	public BgMaxReference getReferenceRecord() {
		return(reference);
	}
	
}
