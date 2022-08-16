package com.mdbs.util;

import java.text.DecimalFormat;

public class NumberUtil {

	/**
	 * @param number
	 * @param rate
	 * @param mod
	 * @return
	 */
	public static double long2double(long number, int rate, int mod) {
		double r = 0;
		if (rate > mod) {
			r = Math.pow(10, rate - mod);
		}
		double m = Math.pow(10, mod);
		double result = (r == 0) ? 0 : ((double) number / r);
		result = (m == 0) ? 0 : ((double) Math.round(result) / m);
		return result;
	}

	/**
	 * @param value
	 * @return
	 */
	public static String formatFloat(double value) {
		try {
			DecimalFormat formatter = new DecimalFormat("#,###,###,###,###.##");
			String format = formatter.format(value);
			if (format.trim().length() == 0)
				format = "0.00";
			return format;
		} catch (Exception e) {
			return "0.00";
		}
	}

	/**
	 * @param value
	 * @return
	 */
	public static String formatInteger(long value) {
		try {
			DecimalFormat formatter = new DecimalFormat("#,###,###,###,###");
			String format = formatter.format(value);
			if (format.trim().length() == 0)
				format = "0";
			return format;
		} catch (Exception e) {
			return "0";
		}
	}

	/**
	 * @param value
	 * @return
	 */
	public static double getDouble(String value) {
		try {
			return Double.parseDouble(value);
		} catch (Exception e) {
			return 0.0;
		}
	}

	/**
	 * @param value
	 * @return
	 */
	public static int getInt(String value) {
		try {
			return Integer.parseInt(value);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * @param value
	 * @return
	 */
	public static long getLong(String value) {
		try {
			return Long.parseLong(value);
		} catch (Exception e) {
			return 0;
		}
	}
}
