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


package org.notima.bg;

import java.util.Vector;


public interface BgSet {

	public void setRecords(Vector<Transaction> records);

    public Vector<Transaction> getRecords();

	public void addTransaction(Transaction trans);
	
	public java.util.Date getCreditRecordDate(String recipientBg, double amount);
	
	public String toRecordString();

    public String getCurrency();

    public String getRecipientBankAccount();

    public String getSenderBankAccount();
    
}
