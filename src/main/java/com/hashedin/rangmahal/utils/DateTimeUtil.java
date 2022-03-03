package com.hashedin.rangmahal.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Pratap Bhanu
 *
 */
public class DateTimeUtil {

	public static LocalDateTime getFromString(String str) {
		// 2020-04-13 18:00
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
		return dateTime;
	}

}
