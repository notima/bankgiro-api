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

import org.notima.bg.BgFooter;
import org.notima.bg.BgParseException;
import org.notima.bg.BgUtil;


public class BgMaxTk70Footer extends BgFooter {

	private static Pattern	linePattern1 = Pattern.compile("70(\\d{8})(\\d{8})(\\d{8})(\\d{8}).*");

	private int		countReceipts;
	private int		countReductions;
	private int		countExtraReferences;
	private int		countWhat;
	
	public BgMaxTk70Footer() {
		super("70");
	}
	
	@Override
	public BgFooter parse(String line) throws BgParseException {
		Matcher m = linePattern1.matcher(line);
		if (m.matches()) {
			countReceipts = Integer.parseInt(m.group(1));
			countReductions = Integer.parseInt(m.group(2));
			countExtraReferences = Integer.parseInt(m.group(3));
			countWhat = Integer.parseInt(m.group(4));
			return(this);
		} else {
			throw new BgParseException(line);
		}
	}

	@Override
	public String toRecordString() {

		// Create "post-record"
		StringBuffer line = new StringBuffer(transCode);

		String receiptsString = Integer.toString(countReceipts);
		receiptsString = BgUtil.fillToLength(receiptsString, true, '0', 8);
		line.append(receiptsString);
		
		String reductionsStr = Integer.toString(countReductions);
		reductionsStr = BgUtil.fillToLength(reductionsStr, true, '0', 8);
		line.append(reductionsStr);
		
		String extraRefStr = Integer.toString(countExtraReferences);
		extraRefStr = BgUtil.fillToLength(extraRefStr, true, '0', 8);
		line.append(extraRefStr);
		
		String whatStr = Integer.toString(countWhat);
		whatStr = BgUtil.fillToLength(whatStr, true, '0', 8);
		line.append(whatStr);
		
		return line.toString();
		
	}

	public int getCountReceipts() {
		return countReceipts;
	}

	public void setCountReceipts(int countReceipts) {
		this.countReceipts = countReceipts;
	}

	public int getCountReductions() {
		return countReductions;
	}

	public void setCountReductions(int countReductions) {
		this.countReductions = countReductions;
	}

	public int getCountExtraReferences() {
		return countExtraReferences;
	}

	public void setCountExtraReferences(int countExtraReferences) {
		this.countExtraReferences = countExtraReferences;
	}

	public int getCountWhat() {
		return countWhat;
	}

	public void setCountWhat(int countWhat) {
		this.countWhat = countWhat;
	}

	
	
}
