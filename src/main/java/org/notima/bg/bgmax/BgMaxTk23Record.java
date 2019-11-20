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
