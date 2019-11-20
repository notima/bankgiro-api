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


package org.notima.bg.lb;

import java.util.regex.*;

import org.notima.bg.BgParseException;
import org.notima.bg.BgRecord;


/**
 * Namnpost
 * @author Daniel Tamm
 *
 */
public class LbTk26Record extends BgRecord {

	private static Pattern	linePattern1 = Pattern.compile("26(\\d{10})(.{35})(.*)");
	
	private String  recipientId;
	private String  recipientName;
	private String	info;
	
	public LbTk26Record() {
		super("26");
	}
	
	@Override
	public String toRecordString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BgRecord parse(String line) throws BgParseException {
		Matcher m = linePattern1.matcher(line);
		if (m.matches()) {
			recipientId = m.group(1);
			recipientName = m.group(2).trim();
			info = m.group(3).trim();
		} else {
			throw new BgParseException(line);
		}
		
		return this;
	}

	public String getRecipientId() {
		return recipientId;
	}

	public void setRecipientId(String recipientId) {
		this.recipientId = recipientId;
	}

	public String getRecipientName() {
		return recipientName;
	}

	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
	

}
