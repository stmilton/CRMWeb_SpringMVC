package com.mdbs.web.tag;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class nl2br extends BodyTagSupport {
	private static final long serialVersionUID = 1L;

	public int doAfterBody() {
		try {
			BodyContent body = getBodyContent();
			JspWriter out = body.getEnclosingWriter();
			String _value = body.getString();
			out.print(_value.replaceAll("\r\n", "<br/>").replaceAll("\r", "<br/>").replaceAll("\n", "<br/>"));
		} catch (Exception e) {
		}
		return SKIP_BODY;
	}
}
