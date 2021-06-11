package org.notima.bg.reference;

public abstract class BgCodeReference extends BgReference {

	protected String code;
	protected String[] validCodes;

	/**
	 * Make sure the init valid codes is always implements.
	 * It should populate the validCodes string array.
	 */
	protected abstract void initValidCodes();

	public BgCodeReference() {
		initValidCodes();
	}
	
	public BgCodeReference(String code) throws InvalidReferenceException {
		initValidCodes();
		if (!isValidCode(code))
			throw new InvalidReferenceException(code);
		
		this.code = code;
	}
	
	/**
	 * Checks if the code is valid
	 * 
	 * @param checkCode		The code to validate
	 * @return		True if the code is valid
	 */
	public boolean isValidCode(String checkCode) {
		
		boolean isValid = false;
		
		for (String validCode : validCodes) {
			if (validCode.equals(checkCode)) {
				isValid = true;
				break;
			}
		}

		return isValid;
	}
	
	
}
