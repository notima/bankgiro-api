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

import java.util.regex.*;

import org.notima.bg.BgParseException;
import org.notima.bg.BgRecord;
import org.notima.bg.BgUtil;


/**
 * Information message, related to a payment 
 * 
 * @author Daniel Tamm
 *
 */
public class LbTk25Record extends BgRecord {

	private static Pattern	linePattern1 = Pattern.compile("25(\\d{9})(.)(.*)");
	
	private String 	recipientBg;
	private String	infoText;
	
	public LbTk25Record() {
		super("25");
	}
	
	@Override
	public BgRecord parse(String line) throws BgParseException {
		Matcher m = linePattern1.matcher(line);
		if (m.matches()) {
			setRecipientBg((m.group(1) + m.group(2)).trim());
			setInfoText(m.group(3).trim());
		} else {
			throw new BgParseException(line);
		}
		
		return this;
	}

	@Override
	public String toRecordString() {
		StringBuffer line = new StringBuffer(getTransCode());

		StringBuffer seqStr;
        if (recipientBg==null) {
            seqStr = new StringBuffer(Integer.toString(seqNo));
            while(seqStr.length()<9) {
                // Prepend with zeroes
                seqStr.insert(0, "0");
            }
            // No check digit, leave empty
            seqStr.append(" ");
        } else {
            seqStr = new StringBuffer(recipientBg);
            while(seqStr.length()<10) {
                // Prepend with zeroes
                seqStr.insert(0, "0");
            }
        }
		line.append(seqStr);

		if (getInfoText()!=null && getInfoText().length()>50) {
			setInfoText(getInfoText().substring(0, 50));
		}
		line.append(getInfoText());
        while(line.length()<80) {
            line.append(" ");
        }
		return(line.toString());
	}

    /**
     * @return the recipientBg
     */
    public String getRecipientBg() {
        return recipientBg;
    }

    /**
     * @param recipientBg the recipientBg to set
     */
    public void setRecipientBg(String recipientBg) {
        this.recipientBg = BgUtil.toDigitsOnly(recipientBg);
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

}
