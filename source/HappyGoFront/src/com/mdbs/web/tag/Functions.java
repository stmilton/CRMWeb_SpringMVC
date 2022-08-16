package com.mdbs.web.tag;

import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Ringo
 */
public final class Functions {
	private static final Logger logger = LoggerFactory.getLogger(Functions.class);
	
	private Functions() {
	}
	
	public static String urlEncode(final String value) {
		try {
			return URLEncoder.encode(value, "UTF-8");
		} catch (Exception e) {
			logger.error("", e);
			return "";
		}
	}
	
	public static String newString(final byte[] value) {
		try {
			return new String(value);
		} catch (Exception e) {
			logger.error("", e);
			return "";
		}
	}
	
	public static String nl2br(final String value) {
		try {
			return value.replaceAll("\r\n", "<br/>").replaceAll("\r", "<br/>").replaceAll("\n", "<br/>");
		} catch (Exception e) {
			//logger.error("", e);
			return "";
		}
	}
	

	/*
     * ascii = 0.5 word, others = 1 word
     */
    public static String cutByWord(final String source, final int words) throws Exception {
		int width = words * 2;
        StringBuilder sb = new StringBuilder();
		for (char c : source.toCharArray()) {
			width -= (c < 128) ? 1 : 2;
			if (width < 0) {
				break;
			}
			sb.append(c);
		}
        return sb.toString();
    }

	/*
     * ascii = 0.5 word, others = 1 word
     */
    public static boolean isOverWord(final String source, final int words) throws Exception {
		int width = words * 2;
		for (char c : source.toCharArray()) {
			width -= (c < 128) ? 1 : 2;
			if (width < 0) {
				return true;
			}
		}
        return false;
    }
}
