package org.notima.bg.reference;

public abstract class BgReference {

	/**
	 * Formats the reference record to a format used in BG-files.
	 * 
	 * @param positionsWide		How wide (wished, the format decides if how much this is considered)
	 * @return		A machine readable format.
	 */
	public abstract String toMachineFormat(int positionsWide);
	
	/**
	 * Formats the reference record to a format easily read by a human.
	 * 
	 * @return		A human readable format.
	 */
	public abstract String toHumanReadable();
	
}
