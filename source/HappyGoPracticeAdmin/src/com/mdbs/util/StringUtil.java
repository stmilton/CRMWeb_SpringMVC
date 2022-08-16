package com.mdbs.util;

import java.util.Date;

public class StringUtil {
	
	/**
	 * @param object
	 * @return
	 */
	public static boolean isTrimEmptyString(Object object) {
		return object == null || object.toString().trim().length() == 0;
	}

	/**
	 * @param object
	 * @return
	 */
	public static boolean isEmptyString(Object object) {
		return object == null || object.toString().length() == 0;
	}

	/**
	 * @param object
	 * @return
	 */
	public static String getTrimString(Object object) {
		return getTrimString(object, null);
	}

	/**
	 * @param obj
	 * @return
	 */
	public static boolean isEmptyOrZero(Object obj) {
		try {
			if (obj == null) {
				return true;
			} else {
				if (obj instanceof String)
					return "".equals(obj);
				else
					return "0".equals(String.valueOf(obj));
			}
		} catch (Exception e) {
		}
		return false;
	}

	/**
	 * @param obj
	 * @return
	 */
	public static boolean isEmpty(Object obj) {
		try {
			if (obj == null) {
				return true;
			} else {
				if (obj instanceof String)
					return "".equals(obj);
			}
		} catch (Exception e) {
		}
		return false;
	}

	/**
	 * @param object
	 * @param defaultValue
	 * @return
	 */
	public static String getTrimString(Object object, String defaultValue) {
		if (object == null)
			return defaultValue;
		return object.toString().trim();
	}

	public static long getLong(Object object, long l) {
		Long result = getLong(object);
		if (result == null) {
			result = l;
		}
		return result;
	}

	public static Long getLong(Object object) {
		if (object != null) {
			if (object instanceof Number)
				return ((Number) object).longValue();
			try {
				return Long.parseLong(String.valueOf(object));
			} catch (NumberFormatException e) {
			}
		}
		return null;
	}

	public static int getInteger(Object object, int i) {
		Integer result = getInteger(object);
		if (result == null)
			result = i;
		return result;
	}

	public static Integer getInteger(Object object) {
		if (object != null) {
			if (object instanceof Number)
				return ((Number) object).intValue();
			try {
				return Integer.parseInt(String.valueOf(object));
			} catch (NumberFormatException e) {
			}
		}
		return null;
	}

	public static String formatFloat(String valueString) {
		try {
			double value = Double.parseDouble(valueString);
			return NumberUtil.formatFloat(value);
		} catch (Exception e) {
			return "";
		}
	}

	public static String formatInteger(String valueString) {
		try {
			int value = Integer.parseInt(valueString);
			return NumberUtil.formatInteger(value);
		} catch (Exception e) {
			return "";
		}
	}

	public static String toDoubleString(String string, int rate, int mod) {
		try {
			long value = Long.parseLong(string);
			return String.valueOf(NumberUtil.long2double(value, rate, mod));
		} catch (Exception e) {

		}
		return string;
	}

	public static Date jsonDateToDate(String jsonDate) {
		try {
			// "/Date(1321867151710)/"
			int idx1 = jsonDate.indexOf("(");
			int idx2 = jsonDate.indexOf(")");
			String s = jsonDate.substring(idx1 + 1, idx2);
			long l = Long.valueOf(s);
			return new Date(l);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 
	 * @param keys
	 *            keys
	 * @return String
	 */
	public static String urlEncoderBig5(String keys) {
		String encode = "BIG5";
		return urlEncoder(keys, encode);
	}

	/**
	 * 
	 * @param keys
	 *            keys
	 * @param encoding
	 *            encoding
	 * @return String
	 */
	public static String urlEncoder(String keys, String encoding) {
		try {
			return java.net.URLEncoder.encode(keys, encoding);
		} catch (Exception e) {
			return keys;
		}
	}
}
