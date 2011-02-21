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

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.*;

import se.notima.bg.BgHeader;
import se.notima.bg.BgParseException;
import se.notima.bg.BgRecord;

public class BgMaxTk01Header extends BgHeader {

	private int 	version;
	private Date	createDate;
	private boolean	testFile;
	
	private static Pattern	linePattern1 = Pattern.compile("01BGMAX               (\\d{2})(\\d{20})(\\w).*");
	private static SimpleDateFormat	dateFormat = new SimpleDateFormat("yyyyMMddHHmm");
	
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
				createDate = dateFormat.parse(m.group(2).substring(0, 12));
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
		return null;
	}

	@Override
	public Date getCreateDate() {
		return(createDate);
	}

}
