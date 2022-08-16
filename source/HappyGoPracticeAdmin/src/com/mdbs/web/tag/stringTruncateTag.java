package com.mdbs.web.tag;

import java.io.UnsupportedEncodingException;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class stringTruncateTag extends BodyTagSupport {
	private static final long serialVersionUID = 1L;
	private boolean useBig5 = false;
	private int _len;
	private String _symbol = "...";

	public void setSymbol(String symbol) {
		if( symbol != null )
			_symbol = symbol.trim();
		else
			_symbol = "...";
	}

	public void setLen(int len) {
		if( len > 0 )
			_len = len;
		else
			_len = 0;
	}

	public int doAfterBody() {
		try {
			BodyContent body = getBodyContent();
			JspWriter out = body.getEnclosingWriter();
			String _value = body.getString();
			if( useBig5 ){
				try {
					byte[] strByte = _value.getBytes("big5");
					int strLen = strByte.length;
					if (_len >= strLen || _len < 1) {
	
					} else {
						int count = 0;
						for (int i = 0; i < _len; i++) {
							int value = (int) strByte[i];
							if (value < 0) {
								count++;
							}
						}
						if (count % 2 != 0) {
							_len = (_len == 1) ? _len + 1 : _len - 1;
						}
						String result = new String(strByte, 0, _len, "big5");
						byte[] b = result.substring(result.length() - 1).getBytes("big5");
						if (b.length == 1) {
							int value = (int) b[0];
							if (value == 63) {
								result = result.substring(0, result.length() - 1);
							}
						}
						_value = result + _symbol;
					}
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}else{
				if( _value.length() > _len ){
					_value = _value.substring(0,_len) + _symbol;
				}
			}
			out.print(_value);
		} catch (Exception e) {
		}

		return SKIP_BODY;
	}
}
