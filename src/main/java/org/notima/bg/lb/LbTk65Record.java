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

package org.notima.bg.lb;

import org.notima.bg.BgRecord;
import org.notima.bg.BgUtil;


/**
 * Informationspost till PG-betalning (TK54)
 *
 * @author Daniel Tamm
 */
public class LbTk65Record extends BgRecord {

    private String  recipientPg;
    private String  infoText;

    public LbTk65Record() {
        super("65");
    }

    public LbTk65Record(String recipientPg, String infoText) {
        super("65");
        this.recipientPg = BgUtil.toDigitsOnly(recipientPg);
        this.infoText = infoText;
    }

    @Override
    public String toRecordString() {
		StringBuffer line = new StringBuffer(getTransCode());

		StringBuffer seqStr = new StringBuffer(recipientPg);
		while(seqStr.length()<10) {
			// Prepend with zeroes
			seqStr.insert(0, "0");
		}
        seqStr.setLength(10);
		line.append(seqStr);
        StringBuffer infoBuf = new StringBuffer(infoText);
        while(infoBuf.length()<35) {
            infoBuf.append(" ");
        }
        infoBuf.setLength(35);
        line.append(infoBuf);
		while(line.length()<80) {
			line.append(" ");
		}
        return line.toString();
    }

    /**
     * @return the recipientPg
     */
    public String getRecipientPg() {
        return recipientPg;
    }

    /**
     * @param recipientPg the recipientPg to set
     */
    public void setRecipientPg(String recipientPg) {
        this.recipientPg = recipientPg;
    }

    /**
     * @return the infoText
     */
    public String getInfoText() {
        return infoText;
    }

    /**
     * @param infoText the infoText to set
     */
    public void setInfoText(String infoText) {
        this.infoText = infoText;
    }

	@Override
	public BgRecord parse(String line) {
		// TODO Auto-generated method stub
		return null;
	}



}
