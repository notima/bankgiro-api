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

package se.notima.bg;


/**
 * Generic header for LB-files
 * @author Daniel Tamm
 *
 */
public abstract class BgHeader extends BgRecord {

	private String			headerType;
	
	public BgHeader(String code) {
		super(code);
	}
	
	public String getHeaderType() {
		return headerType;
	}
	
	public void setHeaderType(String headerType) {
		this.headerType = headerType;
	}
	
	public abstract String getCurrency();

	public abstract void setCurrency(String currency);
	
	public abstract String getSenderAccount();
	
	public abstract java.util.Date getPayDate();
	
	public abstract java.util.Date getCreateDate();
	
}
