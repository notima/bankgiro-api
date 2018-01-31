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
