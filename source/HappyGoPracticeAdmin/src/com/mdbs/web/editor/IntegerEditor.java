package com.mdbs.web.editor;

import java.beans.PropertyEditorSupport;
import java.util.HashMap;

import com.mdbs.util.StringUtil;




/**
 *
 * @author Ringo
 * @modify by Lio
 */
public class IntegerEditor extends PropertyEditorSupport {

	private static HashMap<String, IntegerEditor> map = null;

	public static IntegerEditor getInstance() {
		return getInstance("");
	}

	public static IntegerEditor getInstance(String key) {
		if (map == null)
			map = new HashMap<String, IntegerEditor>();
		IntegerEditor obj = map.get(key);
		if (obj == null) {
			obj = new IntegerEditor(key);
			map.put(key, obj);
		}
		return obj;
	}

	Integer i_defaultValue = null;
	String defaultValue = "";

	public IntegerEditor() {
		i_defaultValue = null;
		defaultValue = "";
	}

	public IntegerEditor(String defaultValue) {
		i_defaultValue = null;
		this.defaultValue = "";
		if (defaultValue != null){
			this.defaultValue = defaultValue.trim();
			if( this.defaultValue.length() > 0 ){
				i_defaultValue = Integer.parseInt(this.defaultValue);
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
				setValue(i_defaultValue);
			} else {
				setValue(Integer.valueOf(StringUtil.getTrimString(text)));
			}
		} catch (Exception e) {
			setValue(null);
		}
	}

	@Override
	public void setValue(Object value) {
		if(value == null){
			super.setValue(i_defaultValue);
		}else{
			super.setValue(value);
		}
	}
}
