package com.mdbs.util;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.multipart.MultipartFile;

public class WebUtil {

	/**
	 * @param input
	 * @return
	 */
	public static final String removeControlCharacter(String input) {
		if (input == null) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < input.codePointCount(0, input.length()); i++) {
			int codePoint = input.codePointAt(i);
			if (!Character.isISOControl(codePoint)) {
				sb.appendCodePoint(codePoint);
			}
		}
		return sb.toString();
	}

	/**
	 * @param data
	 * @return
	 */
	public static Object converStringArray(Object data) {
		Object value = null;
		if (data instanceof String[])
			value = StringUtils.arrayToCommaDelimitedString((String[]) data);
		else
			value = data;
		return value;
	}

	/**
	 * @param mail
	 * @return
	 */
	public static boolean emailValidation(String mail) {
		if (mail != null)
			return mail
					.matches("^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$");
		return false;
	}

	/**
	 * @param object
	 * @return
	 */
	public static String escape(Object object) {
		if (object == null)
			return null;
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
			case '\'':
				sb.append("&#39");
				break;
			default:
				sb.append(c);
			}
		}
		return sb.toString();
	}

	/**
	 * @param request
	 * @return
	 */
	public static String getWebContextPath(HttpServletRequest request) {
		String path = request.getContextPath();
		if (path == null) {
			path = "/";
		} else if (!path.endsWith("/"))
			path = path + "/";
		return path;
	}

	/**
	 * @param className
	 * @return
	 */
	public static String getContextPath(Class<?> className) {
		String path = className.getResource("/").getPath().toString();
		try {
			path = java.net.URLDecoder.decode(path, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		int index = path.indexOf("WEB-INF");
		String realPath = path.substring(0, index - 1);
		return realPath;
	}

	/**
	 * @param request
	 * @param valueName
	 * @return
	 */
	public static String getCookie(HttpServletRequest request, String valueName) {
		return getCookie(request, valueName, null);
	}

	/**
	 * 抓取COOKIE的值
	 * 
	 * @param request
	 *            HTTP 要求
	 * @param valueName
	 *            COOKIE名稱
	 * @return 如果回null 代表沒COOKIE
	 */
	public static String getCookie(HttpServletRequest request,
			String valueName, String defaultvalueName) {
		String Value = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			int cookieSize = cookies.length;
			for (int i = 0; i < cookieSize; i++) {
				if (cookies[i].getName().equals(valueName)) {
					Value = cookies[i].getValue();
					break;
				}
			}
		}
		if (Value == null)
			return defaultvalueName;
		return Value;
	}

	/**
	 * @param response
	 * @param valueName
	 * @param path
	 */
	public static void removeCookie(HttpServletResponse response,
			String valueName, String path) {
		Cookie cookie = new Cookie(valueName, null);
		cookie.setMaxAge(0);
		cookie.setPath(path);
		response.addCookie(cookie);
	}

	public static String attrname = org.springframework.web.servlet.FrameworkServlet.SERVLET_CONTEXT_PREFIX
			+ "appServlet";

	/**
	 * @param name
	 * @param application
	 * @return
	 */
	public static Object getDispatcherServletSpringBean(String name,
			ServletContext application) {
		WebApplicationContext ctx = WebApplicationContextUtils
				.getWebApplicationContext(application, attrname);
		return ctx.getBean(name);
	}

	/**
	 * @param value
	 * @return
	 */
	public static int getInt(Object value) {
		return getInt(value, 0);
	}

	/**
	 * @param value
	 * @param defaultValue
	 * @return
	 */
	public static int getInt(Object value, int defaultValue) {
		Integer data = getInteger(value);
		if (data != null)
			return data.intValue();
		else
			return defaultValue;
	}

	/**
	 * @param value
	 * @return
	 */
	public static Integer getInteger(Object value) {
		Integer result = null;
		try {
			if (value != null) {
				int intdata = NumberFormat.getNumberInstance()
						.parse(String.valueOf(value)).intValue();
				result = new Integer(intdata);
			}
		} catch (Exception e) {
		}
		return result;
	}

	/**
	 * @param request
	 * @param name
	 * @return
	 */
	public static Integer getIntegerParameter(HttpServletRequest request,
			String name) {
		return getInteger(request.getParameter(name));
	}

	/**
	 * @param request
	 * @param name
	 * @return
	 */
	public static int getIntParameter(HttpServletRequest request, String name) {
		Integer data = getInteger(request.getParameter(name));
		if (data == null)
			return 0;
		else
			return data.intValue();
	}

	/**
	 * @param request
	 * @param name
	 * @param defVal
	 * @return
	 */
	public static int getIntParameter(HttpServletRequest request, String name,
			int defVal) {
		Integer data = getInteger(request.getParameter(name));
		if (data == null)
			return defVal;
		else
			return data.intValue();
	}

	/**
	 * @param value
	 * @return
	 */
	public static long getlong(String value) {
		long result = 0;
		try {
			if (value != null) {
				result = Long.parseLong(value);
			}
		} catch (Exception e) {
		}
		return result;
	}

	/**
	 * @param value
	 * @return
	 */
	public static Long getLong(Object value) {
		Long result = null;
		try {
			if (value != null) {
				long longdata = NumberFormat.getNumberInstance()
						.parse(value.toString()).longValue();
				result = new Long(longdata);
			}
		} catch (Exception e) {
		}
		return result;
	}

	/**
	 * @param request
	 * @param name
	 * @return
	 */
	public static Long getLongParameter(HttpServletRequest request, String name) {
		return getLong(request.getParameter(name));
	}

	/**
	 * @param request
	 * @return
	 */
	public static String getRefererDomain(HttpServletRequest request) {
		String referer = String.valueOf(request.getHeader("referer"));
		int pos = referer.indexOf("://");
		if (pos > 0)
			referer = referer.substring(pos + 3);
		pos = referer.indexOf(":");
		if (pos > 0) {
			referer = referer.substring(0, pos);
		} else {
			pos = referer.indexOf("/");
			if (pos > 0)
				referer = referer.substring(0, pos);
		}
		return referer;
	}

	/**
	 * 抓取連線IP的值
	 * 
	 * @param request
	 *            HTTP 要求
	 * @return IP
	 */
	public static String getRemoteAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		} else {
			String[] ips = ip.split(",");
			if (ips != null) {
				for (String ipsi : ips) {
					if (!"unknown".equalsIgnoreCase(ip)) {
						ip = ipsi;
						break;
					}
				}
			}
		}
		return ip;
	}

	/**
	 * @param request
	 * @return
	 */
	public static String getServerURLNoPath(HttpServletRequest request) {
		String servername = request.getServerName();
		String url = request.getScheme();
		int defaultport = 80;
		if ("https".equals(url))
			defaultport = 443;
		url += "://" + servername;
		if (request.getServerPort() != defaultport)
			url += ":" + request.getServerPort();
		return url;
	}

	/**
	 * @param request
	 * @return
	 */
	public static String getServerURL(HttpServletRequest request) {
		String url = getServerURLNoPath(request);
		url += getWebContextPath(request);
		return url;
	}

	/**
	 * @param value
	 * @return
	 */
	public static String getString(Object value) {
		return getString(value, "");
	}

	/**
	 * @param value
	 * @param defaultStr
	 * @return
	 */
	public static String getString(Object value, String defaultStr) {
		if (value == null)
			return defaultStr;
		else
			return value.toString();
	}

	/**
	 * @param request
	 * @param name
	 * @return
	 */
	public static String getStringParameter(HttpServletRequest request,
			String name) {
		return getString(request.getParameter(name));
	}

	/**
	 * @param request
	 * @param name
	 * @param defaultStr
	 * @return
	 */
	public static String getStringParameter(HttpServletRequest request,
			String name, String defaultStr) {
		return getString(request.getParameter(name), defaultStr);
	}

	/**
	 * @param value
	 * @return
	 */
	public static String getTrimString(String value) {
		if (value == null)
			return "";
		else
			return value.trim();
	}

	/**
	 * @param request
	 * @param name
	 * @return
	 */
	public static String getTrimStringParameter(HttpServletRequest request,
			String name) {
		return getTrimString(request.getParameter(name));
	}

	/**
	 * @param value
	 * @return
	 */
	public static boolean isEmpty(Object value) {
		if (value == null) {
			return true;
		} else if (value instanceof String) {
			if (((String) value).trim().length() == 0)
				return true;
		}
		return false;
	}

	/**
	 * @param value
	 * @return
	 */
	public static boolean isEmptyString(String value) {
		if (value == null) {
			return true;
		} else if (value.length() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * @param value
	 * @return
	 */
	public static boolean isTrimEmptyString(String value) {
		if (value == null) {
			return true;
		} else if (value.trim().length() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * @return
	 */
	public static String JavaScriptFunction_emailValidation() {
		return "function emailValidation(email){\n	var filter = /^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$/;\n	if (!filter.test(email)) return false;\n	return true;\n}";
	}

	/**
	 * @return
	 */
	public static String JavaScriptFunction_ssnValidation() {
		return "function ssnValidation(idvalue){\n var ALP_STR = \"ABCDEFGHJKLMNPQRSTUVXYWZIO\";\n var re =/^[A-Z]\\d{9}$/;\n if (!re.test(idvalue)) {return false;}\n var checksum = ALP_STR.indexOf(idvalue.substr(0,1)) + 10;\n checksum = Math.floor(checksum/10) + (checksum%10*9);\n for(var i=1; i<idvalue.length-1; i++) {checksum += idvalue.substr(i,1) * (9-i);}\n checksum += idvalue.substr(9,1)*1;\n var check1 = parseInt(checksum/10);\n var check2 = checksum/10;\n var check3 = (check2-check1)*10;\n if (checksum == check1*10) {}\n else {\n if (idvalue.substr(9,1) == (10-check3)) { }\n else { return false; }\n }\n}";
	}

	/**
	 * @param data
	 * @param join
	 * @return
	 */
	public static String joinArray(String[] data, String join) {
		if (data != null) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < data.length; i++) {
				if (i != 0)
					sb.append(join);
				sb.append(data[i]);
			}
			return sb.toString();
		}
		return "";
	}

	/**
	 * @param request
	 * @return
	 */
	public static boolean msie(HttpServletRequest request) {
		try {
			String userAgent = String.valueOf(request.getHeader("User-Agent")
					.toLowerCase());
			return userAgent.indexOf("msie ") > 0;
		} catch (Exception e) {
		}
		;
		return false;
	}

	/**
	 * @param enumer
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String printAll(Enumeration enumer) {
		StringBuffer sb = new StringBuffer();
		while (enumer.hasMoreElements()) {
			Object obj = enumer.nextElement();
			if (obj instanceof Map.Entry) {
				Map.Entry entry = (Map.Entry) obj;
				Object value = null;
				value = entry.getValue();
				sb.append("entry:").append(entry.getKey()).append(":")
						.append(value).append("\n");
			} else {
				sb.append("value:").append(obj).append("\n");
			}
		}
		return sb.toString();
	}

	/**
	 * @param iterator
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String printAll(Iterator iterator) {
		StringBuffer sb = new StringBuffer();
		while (iterator.hasNext()) {
			Object obj = iterator.next();
			if (obj instanceof Map.Entry) {
				Map.Entry entry = (Map.Entry) obj;
				Object value = null;
				value = entry.getValue();
				sb.append("entry:").append(entry.getKey()).append(":")
						.append(value).append("\n");
			} else {
				sb.append("value:").append(obj).append("\n");
			}
		}
		return sb.toString();
	}

	/**
	 * @param response
	 */
	public static void setNoCache(HttpServletResponse response) {
		response.setHeader("Pragma", "No-cache");
		response.setDateHeader("Expires", 1L);
		response.setHeader("Cache-Control", "no-cache");
		response.addHeader("Cache-Control", "no-store");
	}

	/**
	 * @param pattern
	 * @param str
	 * @return
	 */
	public static boolean simpleMatch(String pattern, String str) {
		if (pattern == null || str == null) {
			return false;
		}
		int firstIndex = pattern.indexOf('*');
		if (firstIndex == -1) {
			return pattern.equals(str);
		}
		if (firstIndex == 0) {
			if (pattern.length() == 1) {
				return true;
			}
			int nextIndex = pattern.indexOf('*', firstIndex + 1);
			if (nextIndex == -1) {
				return str.endsWith(pattern.substring(1));
			}
			String part = pattern.substring(1, nextIndex);
			int partIndex = str.indexOf(part);
			while (partIndex != -1) {
				if (simpleMatch(pattern.substring(nextIndex),
						str.substring(partIndex + part.length()))) {
					return true;
				}
				partIndex = str.indexOf(part, partIndex + 1);
			}
			return false;
		}
		return (str.length() >= firstIndex
				&& pattern.substring(0, firstIndex).equals(
						str.substring(0, firstIndex)) && simpleMatch(
					pattern.substring(firstIndex), str.substring(firstIndex)));
	}

	/**
	 * @param ssn
	 * @return
	 */
	public static boolean ssnValidation(String ssn) {
		String ALP_STR = "ABCDEFGHJKLMNPQRSTUVXYWZIO";
		if (!ssn.matches("^[A-Z]\\d{9}$"))
			return false;
		int checksum = ALP_STR.indexOf(ssn.substring(0, 1)) + 10;
		checksum = (int) (Math.floor(checksum / 10) + (checksum % 10 * 9));
		for (int i = 1; i < ssn.length() - 1; i++) {
			checksum += Integer.parseInt(ssn.substring(i, i + 1)) * (9 - i);
		}
		int d9 = Integer.parseInt(ssn.substring(9, 10));
		checksum += d9 * 1;
		int check1 = checksum / 10;
		int check2 = checksum / 10;
		int check3 = (check2 - check1) * 10;
		if (checksum == check1 * 10) {

		} else {
			if (d9 == (10 - check3)) {

			} else {
				return false;
			}
		}
		return true;
	}

	/**
	 * @param url
	 * @return
	 */
	public static String getForward(String url) {
		return String.format("forward:%s", url);
	}

	/**
	 * @param url
	 * @return
	 */
	public static String getRedirect(String url) {
		return String.format("redirect:%s", url);
	}

	/**
	 * @param dateString
	 * @param dateFormat
	 * @return
	 */
	public static Date dateValidation(String dateString, String dateFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		try {
			Date result = sdf.parse(dateString);
			if (dateString.equals(sdf.format(result)))
				return result;
		} catch (ParseException e) {
		}
		return null;
	}

	/**
	 * 取得上傳檔案名稱
	 * 
	 * @param file
	 * @param uploadFilePath
	 *            檔案位置
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public static String getUploadFileName(MultipartFile file,
			String uploadFilePath) throws IllegalStateException, IOException {
		return getUploadFileName(file, uploadFilePath, null);
	}

	/**
	 * 
	 * @param file
	 * @param uploadFilePath
	 * @param id
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public static String getUploadFileName(MultipartFile file,
			String uploadFilePath, String id) throws IllegalStateException,
			IOException {
		String originalFilename = file.getOriginalFilename();
		String extName = originalFilename.substring(originalFilename
				.lastIndexOf("."));
		String fileName = Id.id32() + extName;
		if (id != null)
			fileName = id + "/" + id + extName;

		File toFile = new File(WebUtil.removeControlCharacter(uploadFilePath),
				fileName);
		if ((null != toFile.getParentFile())
				&& (!toFile.getParentFile().exists())) {
			toFile.getParentFile().mkdirs();
		}
		file.transferTo(toFile);
		return fileName;
	}

	/**
	 * @param id
	 * @return
	 */
	public static Long converIdValue(long id) {
		if (id != 0)
			return id;
		return null;
	}

	/**
	 * @param status
	 * @return
	 */
	public static Long converStatusValue(long status) {
		if (status >= 0)
			return status;
		return null;
	}


	/**
	 * @param keys
	 * @return
	 */
	public static String URLEncoder(String keys) {
		String encode = "UTF-8";
		return URLEncoder(keys, encode);
	}

	/**
	 * @param keys
	 * @param encoding
	 * @return
	 */
	public static String URLEncoder(String keys, String encoding) {
		try {
			return java.net.URLEncoder.encode(keys, encoding);
		} catch (UnsupportedEncodingException uee) {
			return keys;
		}
	}

	/**
	 * @param keys
	 * @return
	 */
	public static String URLDecoder(String keys) {
		String encode = "UTF-8";
		return URLDecoder(keys, encode);
	}

	/**
	 * @param keys
	 * @param encoding
	 * @return
	 */
	public static String URLDecoder(String keys, String encoding) {
		try {
			return java.net.URLDecoder.decode(keys, encoding);
		} catch (UnsupportedEncodingException uee) {
			return keys;
		}
	}

	/**
	 * @param request
	 * @return
	 */
	public static String getSubDomain(HttpServletRequest request) {
		String subDomain = "";
		String[] serverNames = request.getServerName().split("\\.");
		for (int i = 1; i < serverNames.length; i++) {
			if (i > 1) {
				subDomain += ".";
			}
			subDomain += serverNames[i];
		}
		return subDomain;
	}
}