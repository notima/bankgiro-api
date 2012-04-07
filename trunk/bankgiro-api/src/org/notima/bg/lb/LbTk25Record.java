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
            seqStr = new StringBuffer(new Integer(seqNo).toString());
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
