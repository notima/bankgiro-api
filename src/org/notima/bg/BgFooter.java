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
