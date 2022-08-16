package com.mdbs.util;

import java.util.regex.Pattern;

public class ValidatorUtil {

	private static final Pattern ASCII_PATTERN = Pattern
			.compile("[\\x00-\\x7F]*");

	/**
	 * @param msg
	 * @return
	 */
	public static boolean isPureASCII(String msg) {
		boolean result = false;
		if (ASCII_PATTERN.matcher(msg).matches()) {
			result = true;
		}
		return result;
	}

	public static final Pattern IP_PATTERN = Pattern
			.compile("^(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}$");

	/**
	 * @param ipAddr
	 * @return
	 */
	public static boolean isValidIPAddr(String ipAddr) {
		boolean result = false;
		if (IP_PATTERN.matcher(ipAddr).matches()) {
			result = true;
		}
		return result;
	}

	public static final Pattern MAC_PATTERN = Pattern
			.compile("((([0-9a-fA-F]){1,2}[-:]){5}([0-9a-fA-F]){1,2})");

	/**
	 * @param macAddr
	 * @return
	 */
	public static boolean isValidMacAddr(String macAddr) {
		boolean result = false;
		if (MAC_PATTERN.matcher(macAddr).matches()) {
			result = true;
		}
		return result;
	}

	public static final Pattern MSISDN_PATTERN = Pattern.compile("\\d{10}");

	/**
	 * 驗證手機號碼
	 * @param msisdn
	 * @return
	 */
	public static boolean isValidMobile(String msisdn) {
		boolean result = false;
		if (MSISDN_PATTERN.matcher(msisdn).matches()) {
			result = true;
		}
		return result;
	}

	private static final Pattern EMAIL_PATTERN = Pattern
			.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

	/**
	 * 驗證Email
	 * @param email
	 * @return
	 */
	public static boolean isValidEmail(String email) {
		boolean result = false;
		if (EMAIL_PATTERN.matcher(email).matches()) {
			result = true;
		}
		return result;
	}

	public static final Pattern TWPID_PATTERN = Pattern
			.compile("[ABCDEFGHJKLMNPQRSTUVXYWZIO][12]\\d{8}");

	/**
	 * 身份證字號檢查程式，身份證字號規則： 字母(ABCDEFGHJKLMNPQRSTUVXYWZIO)對應一組數(10~35)，
	 * 令其十位數為X1，個位數為X2；( 如Ａ：X1=1 , X2=0 )；D表示2~9數字 Y = X1 + 9*X2 + 8*D1 + 7*D2 +
	 * 6*D3 + 5*D4 + 4*D5 + 3*D6 + 2*D7+ 1*D8 + D9 如Y能被10整除，則表示該身份證號碼為正確，否則為錯誤。
	 * 臺北市(A)、臺中市(B)、基隆市(C)、臺南市(D)、高雄市(E)、臺北縣(F)、
	 * 宜蘭縣(G)、桃園縣(H)、嘉義市(I)、新竹縣(J)、苗栗縣(K)、臺中縣(L)、
	 * 南投縣(M)、彰化縣(N)、新竹市(O)、雲林縣(P)、嘉義縣(Q)、臺南縣(R)、
	 * 高雄縣(S)、屏東縣(T)、花蓮縣(U)、臺東縣(V)、金門縣(W)、澎湖縣(X)、 陽明山(Y)、連江縣(Z)
	 * 
	 */
	public static boolean isValidTWPID(String twpid) {
		boolean result = false;
		String pattern = "ABCDEFGHJKLMNPQRSTUVXYWZIO";
		if (TWPID_PATTERN.matcher(twpid.toUpperCase()).matches()) {
			int code = pattern.indexOf(twpid.toUpperCase().charAt(0)) + 10;
			int sum = 0;
			sum = (int) (code / 10) + 9 * (code % 10) + 8
					* (twpid.charAt(1) - '0') + 7 * (twpid.charAt(2) - '0') + 6
					* (twpid.charAt(3) - '0') + 5 * (twpid.charAt(4) - '0') + 4
					* (twpid.charAt(5) - '0') + 3 * (twpid.charAt(6) - '0') + 2
					* (twpid.charAt(7) - '0') + 1 * (twpid.charAt(8) - '0')
					+ (twpid.charAt(9) - '0');
			if ((sum % 10) == 0) {
				result = true;
			}
		}
		return result;
	}

	public static final Pattern TWBID_PATTERN = Pattern.compile("^[0-9]{8}$");

	/**
	 * 營利事業統一編號檢查程式 可至 http://www.etax.nat.gov.tw/ 查詢營業登記資料
	 * 
	 */
	public static boolean isValidTWBID(String twbid) {
		boolean result = false;
		String weight = "12121241";
		boolean type2 = false; // 第七個數是否為七
		if (TWBID_PATTERN.matcher(twbid).matches()) {
			int tmp = 0, sum = 0;
			for (int i = 0; i < 8; i++) {
				tmp = (twbid.charAt(i) - '0') * (weight.charAt(i) - '0');
				sum += (int) (tmp / 10) + (tmp % 10); // 取出十位數和個位數相加
				if (i == 6 && twbid.charAt(i) == '7') {
					type2 = true;
				}
			}
			if (type2) {
				if ((sum % 10) == 0 || ((sum + 1) % 10) == 0) { // 如果第七位數為7
					result = true;
				}
			} else {
				if ((sum % 10) == 0) {
					result = true;
				}
			}
		}
		return result;
	}

	public static boolean isValidQueryString(String sqlStr) {
		if (sqlStr == null || sqlStr.length() <= 0 || sqlStr.indexOf("@") >= 0
				|| sqlStr.indexOf("'") >= 0 || sqlStr.indexOf("") >= 0
				|| sqlStr.indexOf("\"") >= 0 || sqlStr.indexOf("%") >= 0
				|| sqlStr.indexOf("=") >= 0) {
			// "關鍵字不可包含 ( @ ' % \ = ) 等字元\n";
			return false;
		}
		return true;
	}

	public static boolean isValidInputText(String text) {
		String s1 = "";
		s1 = text.trim();
		for (int i = 0; i < s1.length(); i++) {
			int x = i + 1;
			if (s1.substring(i, x).equals("!")
					|| s1.substring(i, x).equals("@")
					|| s1.substring(i, x).equals("#")
					|| s1.substring(i, x).equals("$")
					|| s1.substring(i, x).equals("%")
					|| s1.substring(i, x).equals("^")
					|| s1.substring(i, x).equals("&")
					|| s1.substring(i, x).equals("*"))
			// 驗證字串特殊字元
			{
				return false;
			}
		}
		return true;
	}

	/**
	 * 驗證網址
	 * @param text
	 * @return
	 */
	public static boolean isValidUrl(String text) {
		String s1 = "";
		s1 = text.trim();
		if (s1.length() < 7) {
			return false;
		}
		boolean check = false;
		if (s1.length() >= 7) {
			if (s1.substring(0, 7).equalsIgnoreCase("http://")) {
				check = true;
			}
		}
		if (s1.length() >= 8) {
			if (s1.substring(0, 8).equalsIgnoreCase("https://")) {
				check = true;
			}
		}
		return check;
	}

	/**
	 * 
	 * @param text
	 * @return
	 */
	public static boolean isValidAndroidUrl(String text) {
		String s1 = "";
		s1 = text.trim();
		if (s1.length() < 9) {
			return false;
		}
		if (s1.substring(0, 9).equalsIgnoreCase("market://")) {
			return true;
		} else// 驗證開頭是否為http://
		{
			return false;
		}
	}

	/**
	 * 驗證含空白字元之英文
	 * @param name
	 * @return
	 */
	public static boolean isValidEn(String name) {
		boolean flag = false;
		if (name.matches("^[a-zA-Z\\s]*$")) {
			flag = true;
		}
		return flag;
		// 字串只能英文或數字
	}

	/**
	 * 驗證一般帳號
	 * @param account
	 * @return
	 */
	public static boolean isValidAccount(String account) {
		boolean flag = false;
		// ^[a-zA-Z0-9]+$
		if (account.matches("^[0-9a-zA-Z_]*$")) {
			flag = true;
		}
		return flag;
		// 字串只能英文或數字
	}

	/**
	 * 驗證email帳號或是一般帳號
	 * @param account
	 * @return
	 */
	public static boolean isValidAccountMail(String account) {
		boolean flag = false;
		// ^[a-zA-Z0-9]+$
		if (account.matches("^[0-9a-zA-Z_@.-]*$")) {
			flag = true;
		}
		return flag;
		// 字串只能英文或數字
	}

	/**
	 * 驗證中文或英文
	 * @param name
	 * @return
	 */
	public static boolean isValidName(String name) {
		boolean flag = false;

		if (name.matches("^[\u4e00-\u9fa5a-zA-Z]+$")) {
			flag = true;
		}
		return flag;
		// 字串只能中文或英文
	}

	/**
	 * 驗證地址
	 * @param address
	 * @return
	 */
	public static boolean isValidAddress(String address) {
		boolean flag = false;

		if (address.matches("^[\u4e00-\u9fa50-9]+$")) {
			flag = true;
		}
		return flag;
		// 字串只能中文或英文或數字
	}

	/**
	 * 驗證電話
	 * @param address
	 * @return
	 */
	public static boolean isValidPhone(String address) {
		boolean flag = false;

		if (address.matches("^[0-9-()#]+$")) {
			flag = true;
		}
		return flag;
		// 字串數字
	}

	/**
	 * 驗證密碼
	 * @param password
	 * @return
	 */
	public static boolean isPassword(String password) {
		boolean flag = false;

		if (password.matches("^[0-9a-zA-Z!@#$]*$")) {
			flag = true;
		}
		return flag;
		// 字串數字
	}

	/**
	 * 驗證英文或數字
	 * @param account
	 * @return
	 */
	public static boolean isValidDomain(String account) {
		boolean flag = false;
		// ^[a-zA-Z0-9]+$
		if (account.matches("^[0-9a-zA-Z-]*$")) {
			flag = true;
		}
		return flag;
		// 字串只能英文或數字
	}

	/**
	 * 驗證數字
	 * @param number
	 * @return
	 */
	public static boolean isNumber(String number) {
		boolean flag = false;

		if (number.matches("[0-9]*")) {
			flag = true;
		}
		return flag;
		// 字串只能數字
	}

}
