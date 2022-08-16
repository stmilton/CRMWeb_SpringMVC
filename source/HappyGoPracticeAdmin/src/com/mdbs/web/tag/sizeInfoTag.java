package com.mdbs.web.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class sizeInfoTag extends BodyTagSupport {
	private static final long serialVersionUID = 1L;
	private int width;
	private int height;

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public int doStartTag() throws JspException {
		try{
			JspWriter out = pageContext.getOut();
			if( width < 0 &&  height < 0 ){
				out.print("寬高不限");
			}else if( width < 0 ){
				out.print("不超出高"+height+"為限");
			}else if( height < 0 ){
				out.print("不超出寬"+width+"為限");
			}else{
				out.print("寬:"+width+" x 高:"+height);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return super.doStartTag();
	}
}
