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

package se.notima.bg.bgmax;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import se.notima.bg.BgFooter;
import se.notima.bg.BgParseException;

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
			countReceipts = new Integer(m.group(1)).intValue();
			countReductions = new Integer(m.group(2)).intValue();
			countExtraReferences = new Integer(m.group(3)).intValue();
			countWhat = new Integer(m.group(4)).intValue();
			return(this);
		} else {
			throw new BgParseException(line);
		}
	}

	@Override
	public String toRecordString() {
		return null;
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
