package com.mdbs.web;

import java.io.IOException;

public class RederctException extends IOException{
	private static final long serialVersionUID = 1L;
	private String msg = null;
	public RederctException(){
	}
	public RederctException(String msg){
		this.setMsg(msg);
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
