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

package org.notima.bg;

import org.notima.bg.reference.BgCustomer;

/**
 * Generic header for LB-files
 * @author Daniel Tamm
 *
 */
public abstract class BgHeader extends BgRecord {

	private String			headerType;
	
	protected BgCustomer	recordOwner;
	
	public BgHeader(String code) {
		super(code);
	}
	
	public String getHeaderType() {
		return headerType;
	}
	
	public void setHeaderType(String headerType) {
		this.headerType = headerType;
	}

	
	/**
	 * The owner of the records that this header describes.
	 * 
	 * @return
	 */
	public BgCustomer getRecordOwner() {
		return recordOwner;
	}

	public void setRecordOwner(BgCustomer recordOwner) {
		this.recordOwner = recordOwner;
	}

	public abstract String getCurrency();

	public abstract void setCurrency(String currency);
	
	public abstract String getSenderAccount();
	
	public abstract java.util.Date getPayDate();
	
	public abstract java.util.Date getCreateDate();
	
}
