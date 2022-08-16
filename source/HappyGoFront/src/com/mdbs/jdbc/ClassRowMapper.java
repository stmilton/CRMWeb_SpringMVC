/*
 * Created on : 2009/1/7
 */
package com.mdbs.jdbc;

import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

/**
 * 
 * @author Ringo
 */
public final class ClassRowMapper<T> implements RowMapper<T> {
	final Logger logger = LoggerFactory.getLogger(getClass());

	static final String SEARCH_METHOD_PREFIX = "set";
	static final String SKIP_COLUMN_PREFIX = "$";
	List<Map<String, Object>> operations;
	Class<T> clazz;

	public ClassRowMapper(final Class<T> clazz) throws Exception {
		this.clazz = clazz;
		this.operations = new ArrayList<Map<String, Object>>();

		Method[] methods = this.clazz.getMethods();// .getDeclaredMethods();
		for (int i = 0; i < methods.length; i++) {
			Method method = methods[i];
			String methodName = method.getName();
			if (!methodName.startsWith(SEARCH_METHOD_PREFIX)) {
				continue;
			}
			String columnName = methodName.substring(SEARCH_METHOD_PREFIX
					.length());
			if (columnName.startsWith(SKIP_COLUMN_PREFIX)) {
				continue;
			}

			Class<?>[] parameterTypes = method.getParameterTypes();
			if (1 != parameterTypes.length) {
				continue;
			}
			// logger.trace(String.format("columnName %s", columnName));
			Map<String, Object> operation = new HashMap<String, Object>();
			operation.put("setMethod", method);
			operation.put("columnName", columnName.toLowerCase());
			operation.put("getMethod",
					getSuitableMethod(parameterTypes[0].getName()));

			this.operations.add(operation);
		}
	}

	boolean isUnderscoreColumnName(final String columnName) {
		try {
			String s = columnName.substring(1);
			if (s.equals(s.toLowerCase())) {
				return true;
			}
		} catch (Exception e) {
		}
		return false;
	}

	public T mapRow(final ResultSet rs, final int index) throws SQLException {
		T object = null;
		try {
			object = clazz.newInstance();
			for (int i = 0; i < operations.size(); i++) {
				String columnName = null;
				try {
					Map<String, Object> operation = operations.get(i);
					columnName = (String) operation.get("columnName");
					Method setMethod = (Method) operation.get("setMethod");
					Method getMethod = (Method) operation.get("getMethod");
					Object value = getMethod.invoke(rs, columnName);
					if (!rs.wasNull()) {
						setMethod.invoke(object, value);
					}
				} catch (Exception e) { // some property can not get directly
										// from db column
					if (e.getCause() instanceof SQLException) {
						SQLException se = (SQLException) e.getCause();
						if (!"S022".equals(se.getSQLState()))
							continue;
					}
					logger.error(columnName, e);
				}
			}
		} catch (Exception e) {
			logger.error("", e);
			throw new SQLException(e.getMessage());
		}
		return object;
	}

	/*
	 * depends on ResultSet method
	 */
	Method getSuitableMethod(final String className) throws Exception {
		String classShortName = className
				.substring(className.lastIndexOf(".") + 1);
		String methodName = "get"
				+ classShortName.substring(0, 1).toUpperCase()
				+ classShortName.substring(1);
		if ("Integer".equals(classShortName)) {
			methodName = "getInt";
		}
		Method method = null;
		try {
			method = ResultSet.class.getMethod(methodName,
					new Class[] { String.class });
		} catch (Exception e) {
			method = ResultSet.class.getMethod("getObject",
					new Class[] { String.class });
		}
		return method;
	}
}
