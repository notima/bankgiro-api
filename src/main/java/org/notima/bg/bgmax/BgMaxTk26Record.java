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
		line.append(BgUtil.fillToLength(name1,  false,  ' ',  35));
		line.append(BgUtil.fillToLength(name2, false, ' ', 35));
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
