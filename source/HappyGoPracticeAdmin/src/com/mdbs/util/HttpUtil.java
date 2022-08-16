package com.mdbs.util;

import java.util.Iterator;
import java.util.Map;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;

import com.mdbs.util.LogUtil;

/**
 * 
 * @author vicky
 *
 */
public class HttpUtil {
	
	static final int DEFAULT_TIMES_RETRY = 3;
	static final int DEFAULT_SO_TIMEOUT = 5000; // Timeout in milliseconds

	public static String postJson(String url) throws Exception {
		PostMethod method = new PostMethod(url);
		try {
			HttpClient httpClient = new HttpClient();
			StringRequestEntity requestEntity = new StringRequestEntity("{}", "application/json", "UTF-8");
			method.setRequestEntity(requestEntity);
			int statusCode = httpClient.executeMethod(method);
			LogUtil.setActionLog("postJson", "statusCode=" + statusCode);
			String result = method.getResponseBodyAsString();
			return result;
		} catch (Exception e) {
			LogUtil.setErrorLog("postJson", e);
			throw e;
		} finally {
			method.releaseConnection();
		}
	}
	
	public static String postRequest(String url, Map<String, String> parameters) throws Exception {
		PostMethod method = new PostMethod(url);
		try {
			// set parameters
			if (parameters != null) {
				Iterator<Map.Entry<String, String>> iterator = parameters.entrySet().iterator();
				while (iterator.hasNext()) {
					Map.Entry<String, String> entry = iterator.next();
					method.addParameter(entry.getKey(), entry.getValue());
					LogUtil.setActionLog("postRequest", "key(value):" + entry.getKey() + "="
							+ entry.getValue());
				}
			}
			// Provide custom retry handler is necessary
			method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(DEFAULT_TIMES_RETRY, false));
			method.getParams().setContentCharset("UTF-8");
			method.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, DEFAULT_SO_TIMEOUT);

			HttpClient httpclient = new HttpClient();
			httpclient.executeMethod(method);
			int statusCode = method.getStatusCode();
			if (statusCode != HttpStatus.SC_OK) {
				LogUtil.setActionLog("postRequest", "statusCode=" + statusCode);
				throw new Exception();
			}
			String result = method.getResponseBodyAsString();
			return result;
		} catch (Exception e) {
			LogUtil.setErrorLog("postRequest", e);
			throw e;
		} finally {
			method.releaseConnection();
		}
	}
	
	public static String getRequest(String url, Map<String, String> parameters) throws Exception {
		GetMethod method = new GetMethod(url);
		NameValuePair[] params = new NameValuePair[parameters.size()];
		int i = 0;
		try {
			// set parameters
			if (parameters != null) {
				Iterator<Map.Entry<String, String>> iterator = parameters.entrySet().iterator();
				while (iterator.hasNext()) {
					Map.Entry<String, String> entry = iterator.next();
					params[i++] = new NameValuePair(entry.getKey(), entry.getValue());
					LogUtil.setActionLog("getRequest", "key(value):" + entry.getKey() + "="
							+ entry.getValue());
				}
			}
			method.setQueryString(params);// Provide custom retry handler is necessary
			method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(DEFAULT_TIMES_RETRY, false));
			method.getParams().setContentCharset("UTF-8");
			method.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, DEFAULT_SO_TIMEOUT);

			HttpClient httpclient = new HttpClient();
			httpclient.executeMethod(method);
			int statusCode = method.getStatusCode();
			if (statusCode != HttpStatus.SC_OK) {
				LogUtil.setActionLog("getRequest", "statusCode=" + statusCode);
				throw new Exception();
			}
			String result = method.getResponseBodyAsString();
			return result;
		} catch (Exception e) {
			LogUtil.setErrorLog("getRequest", e);
			throw e;
		} finally {
			method.releaseConnection();
		}
	}
	
	
}
