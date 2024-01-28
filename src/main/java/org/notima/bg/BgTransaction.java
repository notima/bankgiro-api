package org.notima.bg;

import java.util.Date;

public interface BgTransaction extends Transaction {

	public void setSeqNo(int seqNo);
	
	public double getAmount();
	
	public double getForeignAmount();
	
	public String getForeignCurrency();
	
	public boolean isForeign();
	
	public void addRecord(BgRecord record);
	
	public Date getTransactionDate();
	
	public void setTransactionDate(Date d);
	
	public void setParentSet(BgSet parentSet);
	
	public BgSet getParentSet();

}
