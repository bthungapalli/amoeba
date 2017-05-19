package com.js.amoeba.utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.js.amoeba.domain.Availability;

import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.TimeZone;
import net.fortuna.ical4j.model.TimeZoneRegistry;
import net.fortuna.ical4j.model.TimeZoneRegistryFactory;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.Version;
import net.fortuna.ical4j.util.CompatibilityHints;

public class CalendarSync {
private final static String DATE_PATTERN = "yyyy-MM-dd'T'HH:mm a";
	
	public Calendar sendCalendarSync(Availability availability, String hostEmail, String subject, String description)
			throws ParseException, URISyntaxException, IOException {
		// Creating a new calendar
		net.fortuna.ical4j.model.Calendar calendar = new net.fortuna.ical4j.model.Calendar();
		//String timeZoneValue = fetchTimeZone(availability.get());
		calendar.getProperties().add(new ProdId("-//Ben Fortuna//iCal4j 1.0//EN"));
		calendar.getProperties().add(Version.VERSION_2_0);
		CompatibilityHints.setHintEnabled(CompatibilityHints.KEY_OUTLOOK_COMPATIBILITY, true);
		  final TimeZoneRegistry registry = TimeZoneRegistryFactory.getInstance().createRegistry();
		  // final TimeZone timezone = registry.getTimeZone(timeZoneValue);//availability.getTimeZone());
				
		DateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);
		return calendar;
}

	private String fetchTimeZone(String timeZone) {
		// TODO Auto-generated method stub
		return null;
	}

}
