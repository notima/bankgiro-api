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

package se.notima.bg.bgmax;

public class BgMaxReference {

    public static final int     REFTYPE_BLANK_NOREF_GIVEN = 0;
    public static final int     REFTYPE_BLANK_NOREF_POSSIBLE = 1;
    public static final int     REFTYPE_OCR = 2;
    public static final int     REFTYPE_OCR_NOCHECK = 3;
    public static final int     REFTYPE_NOOCR = 4;
    public static final int     REFTYPE_OCR_INCORRECT = 5;

	public String	reference;
	public double	amount;
	public int		referenceType;
	public int		payChannel;
	public boolean	scannedImage;
	
}
