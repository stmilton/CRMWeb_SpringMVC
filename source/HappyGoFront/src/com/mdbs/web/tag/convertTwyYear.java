package com.mdbs.web.tag;

import java.util.Date;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.mdbs.util.DateUtil;

/**
 * 格式化民國年,回傳民國XXX/XX/XX(可指定-或是/)
 * @author vicky
 *
 */
public class convertTwyYear extends BodyTagSupport {
	private static final long serialVersionUID = 1L;

	private String separator;
	
	public String getSeparator() {
		return separator;
	}
	public void setSeparator(String separator) {
		this.separator = separator;
	}

	public int doAfterBody() {
		try {
			BodyContent body = getBodyContent();
			JspWriter out = body.getEnclosingWriter();
			String _value = body.getString();

			Date date = DateUtil.parse(_value, "yyyy-MM-dd HH:mm:ss");
			// 格式化民國年月日 yyy/M/d
			String chineseYear = "";
			if (separator != null && separator.length() > 0) {
				chineseYear = DateUtil.formatToChineseDate(date, separator);
			} else {
				chineseYear = DateUtil.formatToChineseDate(date, "/");
			}
			out.print(chineseYear);
		} catch (Exception e) {
		}
		return SKIP_BODY;
	}
}