package com.mdbs.web.editor;

import java.beans.PropertyEditorSupport;
import java.util.HashMap;

import com.mdbs.util.StringUtil;




/**
 *
 * @author Lio
 */
public class LongEditor extends PropertyEditorSupport {

	private static HashMap<String, LongEditor> map = null;

	public static LongEditor getInstance() {
		return getInstance("");
	}

	public static LongEditor getInstance(String key) {
		if (map == null)
			map = new HashMap<String, LongEditor>();
		LongEditor obj = map.get(key);
		if (obj == null) {
			obj = new LongEditor(key);
			map.put(key, obj);
		}
		return obj;
	}

	Long l_defaultValue = null;
	String defaultValue = "";

	public LongEditor() {
		l_defaultValue = null;
		defaultValue = "";
	}

	public LongEditor(String defaultValue) {
		l_defaultValue = null;
		this.defaultValue = "";
		if (defaultValue != null){
			this.defaultValue = defaultValue.trim();
			if( this.defaultValue.length() > 0 ){
				l_defaultValue = Long.parseLong(this.defaultValue);
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
				setValue(Long.valueOf(StringUtil.getTrimString(text)));
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
