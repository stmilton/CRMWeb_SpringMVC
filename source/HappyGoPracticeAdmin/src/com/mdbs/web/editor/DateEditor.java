package com.mdbs.web.editor;

import java.beans.PropertyEditorSupport;
import java.lang.reflect.Constructor;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author Lio
 */
public class DateEditor extends PropertyEditorSupport {

	private static HashMap<String, DateEditor> map = null;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	public static DateEditor getInstance(Class<?> key1) {
		if (map == null)
			map = new HashMap<String, DateEditor>();

		String key = key1.getClass().getName();
		DateEditor obj = map.get(key);
		if (obj == null) {
			obj = new DateEditor(key1);
			map.put(key, obj);
		}
		return obj;
	}

	Class<?> convertClass;

	public DateEditor(Class<?> key1) {
		this.convertClass = key1;
	}

	public String getAsText() {
		try{
			return (getValue() != null ? dateFormat2.format(getValue()) : "");
		}catch(Exception e){
			return (getValue() != null ? dateFormat.format(getValue()) : "");
		}
	}

	@SuppressWarnings("deprecation")
	public void setAsText(String text) throws IllegalArgumentException {
		try {
			Date date;
			try{
				date = dateFormat2.parse(text);
				if (date.getHours() == 23 && date.getMinutes() == 59) {
					date.setSeconds(59);
				}
			}catch(Exception e){
				date = dateFormat.parse(text);
			}
			if (convertClass == Date.class) {
				setValue(date);
			} else {
				try {
					convertClass.asSubclass(Date.class);
					Constructor<?> constructor = convertClass
							.getConstructor(long.class);
					Object obj = constructor.newInstance(date.getTime());
					setValue(obj);
				} catch (ClassCastException e) {
					if (convertClass == Calendar.class) {
						Calendar obj = Calendar.getInstance();
						obj.setTimeInMillis(date.getTime());
						setValue(obj);
					}
					throw e;
				}
			}
		} catch (Exception e) {
			setValue(null);
		}
	}

}
