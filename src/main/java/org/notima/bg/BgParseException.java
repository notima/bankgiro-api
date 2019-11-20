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

/**
 * Exception class for parse exceptions
 * @author Daniel Tamm
 *
 */
public class BgParseException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7871720174925010981L;
	private String	m_line;
	
	public BgParseException(String line) {
		super(line);
		m_line = line;
	}
	
	public BgParseException(String message, String line) {
		super(message);
		m_line = line;
	}
	
	public String getLine() {
		return(m_line);
	}
	
}
