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
