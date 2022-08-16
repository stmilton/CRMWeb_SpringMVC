package com.mdbs.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mdbs.util.WebUtil;

public class LogUtil {
	
	final static Logger logger = LoggerFactory.getLogger(LogUtil.class);

	/**
	 * @param managerId
	 * @param message
	 */
	public static void setSaveLog(String managerId, String message) {
		String className = Thread.currentThread().getStackTrace()[2].getFileName();
		try {
			className = className.substring(0, className.indexOf("."));
		} catch (Exception ex) {
		}
		String methodName = className + "." + Thread.currentThread().getStackTrace()[2].getMethodName();
		logger.info(WebUtil.removeControlCharacter("[" + methodName + "] [" + managerId + "] - " + message));
	}

	/**
	 * 置放Action log，不須傳入method name
	 * @param action
	 * @param message
	 */
	public static void setActionLog(String action, String message) {
		String className = Thread.currentThread().getStackTrace()[2].getFileName();
		try {
			className = className.substring(0, className.indexOf("."));
		} catch (Exception ex) {
		}
		String methodName = className + "." + Thread.currentThread().getStackTrace()[2].getMethodName();
		logger.info(WebUtil.removeControlCharacter(methodName + "-ACTION:" + action + ";MESSAGE : " + message));
	}
	
	/**
	 * 置放Action Exception log
	 * @param action 動作
	 */
	public static void setErrorLog(String action, Exception e) {
		String className = Thread.currentThread().getStackTrace()[2].getFileName();
		try{
			className = className.substring(0, className.indexOf("."));
		} catch (Exception ex) {}
		String methodName = className + "." + Thread.currentThread().getStackTrace()[2].getMethodName();
		logger.error(WebUtil.removeControlCharacter(methodName + "-ACTION:" + action + " is error!"), e);
	}

	/**
	 * 記錄LOG
	 * @param message 訊息
	 */
	public static void setInfoLog(String message) {
		String className = Thread.currentThread().getStackTrace()[2].getFileName();
		try {
			className = className.substring(0, className.indexOf("."));
		} catch (Exception ex) {
		}
		String methodName = className + "." + Thread.currentThread().getStackTrace()[2].getMethodName();
		logger.info(WebUtil.removeControlCharacter("[" + methodName + "]-" + message));
	}
	
	/**
	 * 記錄LOG
	 * @param message 訊息
	 */
	public static void setDebugLog(String message) {
		String className = Thread.currentThread().getStackTrace()[2].getFileName();
		try {
			className = className.substring(0, className.indexOf("."));
		} catch (Exception ex) {
		}
		String methodName = className + "." + Thread.currentThread().getStackTrace()[2].getMethodName();
		logger.debug(WebUtil.removeControlCharacter("[" + methodName + "]-" + message));
	}
}