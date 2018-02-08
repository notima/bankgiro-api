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

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.*;

import org.notima.bg.BgHeader;
import org.notima.bg.BgParseException;
import org.notima.bg.BgRecord;


public class BgMaxTk01Header extends BgHeader {

	public static final int DEFAULT_VERSION = 1;
	
	private int 	version = DEFAULT_VERSION;
	private Date	createDate;
	private boolean	testFile;
	
	private static Pattern	linePattern1 = Pattern.compile("01BGMAX               (\\d{2})(\\d{20})(\\w).*");
	private static SimpleDateFormat	dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	private static DecimalFormat versionFormat = new DecimalFormat("00");
	
	public BgMaxTk01Header() {
		super("01");
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public boolean isTestFile() {
		return testFile;
	}

	public void setTestFile(boolean testFile) {
		this.testFile = testFile;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public BgRecord parse(String line) throws BgParseException {
		Matcher m = linePattern1.matcher(line);
		if (m.matches()) {
			version = new Integer(m.group(1)).intValue();
			try {
				createDate = dateFormat.parse(m.group(2).substring(0, 14));
			} catch (java.text.ParseException pe) {
				throw new BgParseException("File date: " + m.group(2) + " not valid. ", line);
			}
			testFile = "T".equalsIgnoreCase(m.group(3));
			return(this);
		} else {
			throw new BgParseException(line);
		}
	}
	
	
	@Override
	public String getCurrency() {
		return null;
	}

	@Override
	public void setCurrency(String currency) {
		
	}
	
	@Override
	public Date getPayDate() {
		return createDate;
	}

	@Override
	public String getSenderAccount() {
		return null;
	}


	@Override
	public String toRecordString() {
		StringBuffer line = new StringBuffer(getTransCode());
		line.append("BGMAX               ");
		line.append(versionFormat.format(version));
		line.append(dateFormat.format(createDate));
		line.append("000000"); // Microseconds
		line.append(isTestFile() ? "T" : "P");
		
		// Pad to 80
		while(line.length()<80) {
			line.append(" ");
		}
		
		return line.toString();
	}

	@Override
	public Date getCreateDate() {
		return(createDate);
	}

}
