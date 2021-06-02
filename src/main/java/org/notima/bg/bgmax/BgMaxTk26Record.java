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
import org.notima.util.NotimaUtil;


public class BgMaxTk26Record extends BgRecord {

	private static Pattern	linePattern1 = Pattern.compile("26(.{35})(.*)");	

	private String	name1;
	private String	name2;
	
	public BgMaxTk26Record() {
		super("26");
	}

	@Override
	public BgRecord parse(String line) throws BgParseException {
		Matcher m = linePattern1.matcher(line);
		if (m.matches()) {
			name1 = m.group(1).trim();
			name2 = m.group(2).trim();
			return(this);
		} else {
			throw new BgParseException(line);
		}
		
	}

	@Override
	public String toRecordString() {
		StringBuffer line = new StringBuffer(transCode);
		line.append(NotimaUtil.fillToLength(name1,  false,  ' ',  35));
		line.append(NotimaUtil.fillToLength(name2, false, ' ', 35));
		while(line.length()<80) {
			line.append(" ");
		}
		return line.toString();
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

}
