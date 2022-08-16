package com.mdbs.web.tag;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class stringBreakToBrTag extends BodyTagSupport  {
    private int _len;
    private String _symbol = "*";
                                                                                                
    public void setSymbol(String symbol) {
    	_symbol = symbol;
    }

    public void setLen(int len) {
    	_len = len;
    }
    public int doAfterBody() { 
        try { 
        	BodyContent body = getBodyContent();
            JspWriter out = body.getEnclosingWriter(); 
            String _value = body.getString();
            try
			{
            	_value = _value.replaceAll("\r\n","<br>");
				//_symbol.trim()
			}
			catch (Exception e){
				//e.printStackTrace();
			}
			out.print(_value);
         } 
         catch(Exception e) {} 

         return SKIP_BODY; 
    } 
}

