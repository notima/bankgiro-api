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

import java.util.*;


public class Receipt implements Transaction {

	private Vector<BgRecord>	records = new Vector<BgRecord>();
	
	@Override
	public double getAmount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setSeqNo(int seqNo) {
		// TODO Auto-generated method stub

	}

	@Override
	public String toRecordString() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void addRecord(BgRecord rec) {
		records.add(rec);
	}

	@Override
	public Date getTransactionDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setTransactionDate(Date d) {
		// TODO Auto-generated method stub
		
	}

}
