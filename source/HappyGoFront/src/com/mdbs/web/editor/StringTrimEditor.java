package com.mdbs.web.editor;

import java.beans.PropertyEditorSupport;
import java.util.HashMap;

/**
 * 
 * @author Lio
 */
public class StringTrimEditor extends PropertyEditorSupport {

	private static HashMap<String, StringTrimEditor> map = null;

	public static StringTrimEditor getInstance() {
		return getInstance("");
	}

	public static StringTrimEditor getInstance(String key) {
		if (map == null)
			map = new HashMap<String, StringTrimEditor>();
		StringTrimEditor obj = map.get(key);
		if (obj == null) {
			obj = new StringTrimEditor(key);
			map.put(key, obj);
		}
		return obj;
	}

	String defaultValue = "";

	public StringTrimEditor() {
	}

	public StringTrimEditor(String defaultValue) {
		if (defaultValue != null)
			this.defaultValue = defaultValue.trim();
	}

	public String getAsText() {
		return (getValue() != null ? getValue().toString() : defaultValue);
	}

	public void setAsText(String text) throws IllegalArgumentException {
		if (text != null)
			setValue(text.trim());
		else
			setValue(null);
	}
}
