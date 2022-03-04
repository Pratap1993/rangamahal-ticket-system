package com.chagu.rangmahal.utils;

import java.time.LocalTime;

import com.chagu.rangmahal.enums.ShowTimeEnum;

/**
 * @author Pratap Bhanu
 *
 */
public class ShowTimeUtil {

	public static Boolean isBookingOpen(String showTime) {
		if (showTime == null)
			return false;
		LocalTime currentTime = LocalTime.now();
		LocalTime morningShowTime = LocalTime.parse("08:45:00");
		LocalTime matineeShowTime = LocalTime.parse("14:45:00");
		LocalTime eveningShowTime = LocalTime.parse("17:45:00");
		if (showTime.equals(ShowTimeEnum.MORNING.name()) && currentTime.isAfter(morningShowTime)
				&& currentTime.isBefore(morningShowTime.plusMinutes(30))) {
			return true;
		} else if (showTime.equals(ShowTimeEnum.MATINEE.name()) && currentTime.isAfter(matineeShowTime)
				&& currentTime.isBefore(matineeShowTime.plusMinutes(30))) {
			return true;
		} else if (showTime.equals(ShowTimeEnum.EVENING.name()) && currentTime.isAfter(eveningShowTime)
				&& currentTime.isBefore(eveningShowTime.plusMinutes(30))) {
			return true;
		} else {
			return false;
		}
	}

}
