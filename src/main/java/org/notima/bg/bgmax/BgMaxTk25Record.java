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


public class BgMaxTk25Record extends BgRecord {

	private static Pattern	linePattern1 = Pattern.compile("25(.*)");	

	private String	infoText;
	
	public BgMaxTk25Record() {
		super("25");
	}

	public BgMaxTk25Record(String text) {
		super("25");
		infoText = text;
	}
	
	@Override
	public BgRecord parse(String line) throws BgParseException {
		Matcher m = linePattern1.matcher(line);
		if (m.matches()) {
			infoText = m.group(1).trim();
			return(this);
		} else {
			throw new BgParseException(line);
		}
		
	}

	@Override
	public String toRecordString() {
		StringBuffer line = new StringBuffer(this.transCode);
		line.append(BgUtil.fillToLength(infoText, false, ' ', 50));
		while(line.length()<80) {
			line.append(" ");
		}
		return line.toString();
	}

	public String getInfoText() {
		return infoText;
	}

	public void setInfoText(String infoText) {
		this.infoText = infoText;
	}

}
