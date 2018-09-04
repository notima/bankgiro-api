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


public class BgMaxTk28Record extends BgRecord {

	private static Pattern	linePattern1 = Pattern.compile("28(.{35})(.{35})(.{2}).*");	
	
	private String	city;
	private String	country;
	private String	countryCode;
	
	
	public BgMaxTk28Record() {
		super("28");
		// TODO Auto-generated constructor stub
	}

	@Override
	public BgRecord parse(String line) throws BgParseException {
		Matcher m = linePattern1.matcher(line);
		if (m.matches()) {
			city = m.group(1).trim();
			country = m.group(2).trim();
			countryCode = m.group(3).trim();
			return(this);
		} else {
			throw new BgParseException(line);
		}
	}

	@Override
	public String toRecordString() {
		StringBuffer line = new StringBuffer(transCode);
		line.append(BgUtil.fillToLength(city,  false,  ' ',  35));
		line.append(BgUtil.fillToLength(country, false, ' ', 35));
		if (countryCode!=null && !"SE".equalsIgnoreCase(countryCode)) {
			line.append(BgUtil.fillToLength(countryCode.toUpperCase(), false, ' ', 2));
		}
		while(line.length()<80) {
			line.append(" ");
		}
		return line.toString();
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	
	
}
