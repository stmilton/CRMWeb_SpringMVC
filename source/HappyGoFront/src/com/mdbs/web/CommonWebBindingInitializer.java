package com.mdbs.web;

import java.sql.Timestamp;
import java.util.Date;


import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

import com.mdbs.web.editor.DateEditor;
import com.mdbs.web.editor.DoubleEditor;
import com.mdbs.web.editor.IntegerEditor;
import com.mdbs.web.editor.LongEditor;
import com.mdbs.web.editor.StringTrimEditor;

public class CommonWebBindingInitializer implements WebBindingInitializer {
	@Override
	public void initBinder(WebDataBinder binder, WebRequest request) {
		binder.registerCustomEditor(Timestamp.class, DateEditor.getInstance(Timestamp.class));
		binder.registerCustomEditor(Date.class, DateEditor.getInstance(Date.class));
		//binder.registerCustomEditor(java.sql.Date.class, DateEditor.getInstance(java.sql.Date.class));
		//binder.registerCustomEditor(Calendar.class, DateEditor.getInstance(Calendar.class));
		binder.registerCustomEditor(String.class, StringTrimEditor.getInstance());
		binder.registerCustomEditor(Integer.class, IntegerEditor.getInstance());
		binder.registerCustomEditor(int.class, IntegerEditor.getInstance("0"));
		binder.registerCustomEditor(Long.class, LongEditor.getInstance());
		binder.registerCustomEditor(long.class, LongEditor.getInstance("0"));
		binder.registerCustomEditor(Double.class, DoubleEditor.getInstance());
		binder.registerCustomEditor(double.class, DoubleEditor.getInstance("0"));
	}
}