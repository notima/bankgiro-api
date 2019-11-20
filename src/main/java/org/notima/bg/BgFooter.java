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


public abstract class BgFooter extends BgRecord {

	protected int		count;
	protected double	amount;
	protected double	foreignAmount;
	protected String	foreignCurrency;
	
	public BgFooter(String code) {
		super(code);
	}
	
	public void incrementCount() {
		count++;
	}
	
	public void decrementCount() {
		count--;
	}
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public void incrementAmount(double amount) {
		this.amount += amount;
	}
	
	public void decrementAmount (double amount) {
		this.amount -= amount;
	}
	
	public void incrementForeignAmount(double amount) {
		this.foreignAmount += amount;
	}
	
	public void decrementForeignAmount(double amount) {
		this.foreignAmount -= amount;
	}
	
	public double getForeignAmount() {
		return foreignAmount;
	}

	public void setForeignAmount(double foreignAmount) {
		this.foreignAmount = foreignAmount;
	}

	public String getForeignCurrency() {
		return foreignCurrency;
	}

	public void setForeignCurrency(String foreignCurrency) {
		this.foreignCurrency = foreignCurrency;
	}

	public abstract String toRecordString();
	
	public abstract BgFooter parse(String line) throws BgParseException;
	
}
