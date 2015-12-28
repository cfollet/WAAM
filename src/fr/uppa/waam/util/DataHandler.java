package fr.uppa.waam.util;

import java.util.Date;

import org.ocpsoft.prettytime.PrettyTime;

public class DataHandler {
	public static String formatDistance(float meters) {
		if (meters < 1000) {
			return ((int) meters) + "m";
		} else if (meters < 10000) {
			return formatDec(meters / 1000f, 1) + "km";
		} else {
			return ((int) (meters / 1000f)) + "km";
		}
	}

	static String formatDec(float val, int dec) {
		int factor = (int) Math.pow(10, dec);
		int front = (int) (val);
		int back = (int) Math.abs(val * (factor)) % factor;

		return front + "." + back;
	}

	public static String formatDate(Date d) {
		PrettyTime p = new PrettyTime();
		return p.format(d);
	}
}
