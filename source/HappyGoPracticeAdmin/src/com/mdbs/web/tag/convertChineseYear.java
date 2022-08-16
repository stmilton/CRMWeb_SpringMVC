package com.mdbs.web.tag;

import java.util.Date;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.mdbs.util.DateUtil;

/**
 * 格式化民國年,回傳民國XXX年XX月XX日
 * @author vicky
 *
 */
public class convertChineseYear extends BodyTagSupport {
	private static final long serialVersionUID = 1L;

	public int doAfterBody() {
		try {
			BodyContent body = getBodyContent();
			JspWriter out = body.getEnclosingWriter();
			String _value = body.getString();

			Date date = DateUtil.parse(_value, "yyyy-MM-dd HH:mm:ss");
			// 格式化民國XXX年XX月XX日
			String chineseYear = DateUtil.formatToChineseDate(date);
			out.print(chineseYear);
		} catch (Exception e) {
		}
		return SKIP_BODY;
	}
}
