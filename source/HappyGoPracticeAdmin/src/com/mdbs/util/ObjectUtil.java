package com.mdbs.util;

import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * @author Ringo
 */
public class ObjectUtil {

	/**
	 * @param original
	 * @param defaultValue
	 * @return
	 */
	public static String getFixedString(String original, String defaultValue) {
		return (original != null) ? original.trim() : defaultValue;
	}

	/**
	 * @param original
	 * @param copy
	 * @return
	 */
	public static Object getNotNullObject(Object original, Object copy) {
		return (original != null) ? original : copy;
	}

	/**
	 * @param object
	 * @return
	 */
	public static int getIntDefault0(Object object) {
		try {
			return Integer.parseInt(object.toString());
		} catch (Exception e) {
			return 0;
		}
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
	public static boolean containsNonSpace(Object object) {
		return (object != null && object.toString().trim().length() >= 1);
	}

	/**
	 * @param object
	 * @return
	 */
	public static String escapeHtml(Object object) {
		if (object == null)
			return "";
		String string = object.toString();
		StringBuffer sb = new StringBuffer();
		for (int i = 0, len = string.length(); i < len; i++) {
			char c = string.charAt(i);
			switch (c) {
			case '&':
				sb.append("&amp;");
				break;
			case '<':
				sb.append("&lt;");
				break;
			case '>':
				sb.append("&gt;");
				break;
			case '"':
				sb.append("&quot;");
				break;
			default:
				sb.append(c);
			}
		}
		return sb.toString();
	}

	/**
	 * @param object
	 * @param maxLength
	 * @return
	 */
	public static String textOnly(Object object, int maxLength) {
		if (object == null)
			return "";
		String text = object.toString().replaceAll("<*[^<>]*>", "");
		return text.substring(0,
				(text.length() > maxLength) ? maxLength : text.length());
	}

	/**
	 * @param object
	 * @return
	 */
	public static String textOnly(Object object) {
		if (object == null)
			return "";
		return object.toString().replaceAll("<*[^<>]*>", "");
	}

	/**
	 * @param s
	 * @param oldSub
	 * @param newSub
	 * @return
	 */
	public static String replace(String s, String oldSub, String newSub) {
		if ((s == null) || (oldSub == null) || (newSub == null)) {
			return null;
		}

		int y = s.indexOf(oldSub);

		if (y >= 0) {
			StringBuffer sb = new StringBuffer();
			int length = oldSub.length();
			int x = 0;

			while (x <= y) {
				sb.append(s.substring(x, y));
				sb.append(newSub);
				x = y + length;
				y = s.indexOf(oldSub, x);
			}

			sb.append(s.substring(x));

			return sb.toString();
		} else {
			return s;
		}
	}

	/**
	 * @param file
	 * @return
	 */
	public static boolean isNotEmptyFile(MultipartFile file) {
		return file != null && !file.isEmpty();
	}

}