package kr.or.ddit.taglibs;

import java.util.Calendar;
import java.util.TimeZone;

public class CalendarGenerator {
	public static Calendar generate() {
		return Calendar.getInstance();
	}
	
	public static TimeZone[] getTimeZones() {
		String[] zoneIds = TimeZone.getAvailableIDs();
		TimeZone[] zones = new TimeZone[zoneIds.length];
		for(int i=0; i<zoneIds.length; i++) {
			zones[i] = TimeZone.getTimeZone(zoneIds[i]);
		}
		return zones;
	}
}
