package org.notima.bg.reference;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.notima.bg.BgUtil;
import org.notima.util.LocalDateUtils;

public class BgDate extends BgReference {

	public String dfmt = "yyyy-MM-dd"; 
	
	private LocalDate internalDate;

	public BgDate() {
		internalDate = LocalDate.now();
	}

	public BgDate(LocalDate date) {
		internalDate = LocalDate.from(date);
	}
	
	public BgDate(String dateAsStr) {
		internalDate = LocalDate.parse(dateAsStr, DateTimeFormatter.ofPattern(dfmt));
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
		if (date==null)
			internalDate = null;
		else
			internalDate = LocalDate.from(date);
	}
	
	@Override
	public String toMachineFormat(int positionWide) {
		
		if (positionWide>6) {
			if (internalDate!=null)
				return BgUtil.getLongDateString(LocalDateUtils.asDate(internalDate));
			else
				return BgUtil.getLongDateString(null);
		} else {
			if (internalDate!=null)
				return BgUtil.getDateString(LocalDateUtils.asDate(internalDate));
			else
				return BgUtil.getDateString(null);
		}
		
	}

	@Override
	public String toHumanReadable() {
		return internalDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
	}

}
