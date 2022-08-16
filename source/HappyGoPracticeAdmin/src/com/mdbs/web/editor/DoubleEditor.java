package com.mdbs.web.editor;

import java.beans.PropertyEditorSupport;
import java.util.HashMap;

import com.mdbs.util.StringUtil;




/**
 *
 * @author Lio
 */
public class DoubleEditor extends PropertyEditorSupport {

	private static HashMap<String, DoubleEditor> map = null;

	public static DoubleEditor getInstance() {
		return getInstance("");
	}

	public static DoubleEditor getInstance(String key) {
		if (map == null)
			map = new HashMap<String, DoubleEditor>();
		DoubleEditor obj = map.get(key);
		if (obj == null) {
			obj = new DoubleEditor(key);
			map.put(key, obj);
		}
		return obj;
	}

	Double l_defaultValue = null;
	String defaultValue = "";

	public DoubleEditor() {
		l_defaultValue = null;
		defaultValue = "";
	}

	public DoubleEditor(String defaultValue) {
		l_defaultValue = null;
		this.defaultValue = "";
		if (defaultValue != null){
			this.defaultValue = defaultValue.trim();
			if( this.defaultValue.length() > 0 ){
				l_defaultValue = Double.parseDouble(this.defaultValue);
			}
		}
	}

	@Override
	public String getAsText() {
		return (getValue() != null ? getValue().toString() : defaultValue);
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		try {
			if (StringUtil.isTrimEmptyString(text)) {
				setValue(l_defaultValue);
			} else {
				setValue(Double.valueOf(StringUtil.getTrimString(text)));
			}
		} catch (Exception e) {
			setValue(null);
		}
	}

	@Override
	public void setValue(Object value) {
		if(value == null){
			super.setValue(l_defaultValue);
		}else{
			super.setValue(value);
		}
	}

}
