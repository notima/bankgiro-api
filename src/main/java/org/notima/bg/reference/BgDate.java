package org.notima.bg.reference;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.notima.bg.BgUtil;
import org.notima.util.LocalDateUtils;

public class BgDate extends BgReference {

	private LocalDate internalDate;

	public BgDate() {
		internalDate = LocalDate.now();
	}

	public BgDate(LocalDate date) {
		internalDate = LocalDate.from(date);
	}
	
	public Date asDate() {
		if (internalDate!=null)
			return LocalDateUtils.asDate(internalDate);
		else
			return null;
	}

	public LocalDate getLocalDate() {
		return internalDate;
	}
	
	public void setLocalDate(LocalDate date) {
		internalDate = LocalDate.from(date);
	}
	
	@Override
	public String toMachineFormat(int positionWide) {
		
		if (positionWide>6) {
			return BgUtil.getLongDateString(LocalDateUtils.asDate(internalDate));
		} else {
			return BgUtil.getDateString(LocalDateUtils.asDate(internalDate));
		}
		
	}

	@Override
	public String toHumanReadable() {
		return internalDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
	}

}
