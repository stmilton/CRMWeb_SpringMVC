package com.mdbs.jdbc;

import java.util.HashMap;
import java.util.Map;


public class ClassRowMapperFactory {

	private static Map<String, ClassRowMapper<?>> map = new HashMap<String, ClassRowMapper<?>>();

	public static <T> ClassRowMapper<T> get(Class<T> c) throws Exception {
		@SuppressWarnings("unchecked")
		ClassRowMapper<T> o = (ClassRowMapper<T>) map.get(c.getName());
		if (o == null)
			o = new ClassRowMapper<T>(c);
		return o;
	}
}


