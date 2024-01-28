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

import java.util.*;


public class Receipt implements BgTransaction {

	private Vector<BgRecord>	records = new Vector<BgRecord>();
	
	public double getAmount() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setSeqNo(int seqNo) {
		// TODO Auto-generated method stub

	}

	public String toRecordString() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void addRecord(BgRecord rec) {
		records.add(rec);
	}

	public Date getTransactionDate() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setTransactionDate(Date d) {
		// TODO Auto-generated method stub
		
	}

	public double getForeignAmount() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getForeignCurrency() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isForeign() {
		// TODO Auto-generated method stub
		return false;
	}

	public BgSet getParentSet() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setParentSet(BgSet parentSet) {
		// TODO Auto-generated method stub
		
	}

}
