package com.mdbs.jdbc;

import com.mdbs.util.StringUtil;

public class PagingParameter {

	private String pN;
	private String pA;
	private String iA;
	private int pageSize;

	public PagingParameter() {

	}

	public String getpN() {
		return pN;
	}

	public void setpN(String pN) {
		this.pN = pN;
	}

	public String getpA() {
		return pA;
	}

	public void setpA(String pA) {
		this.pA = pA;
	}

	public String getiA() {
		return iA;
	}

	public void setiA(String iA) {
		this.iA = iA;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		int p = StringUtil.getInteger(pN, 1);
		if (p <= 0)
			p = 1;
		return p;
	}

	public int getPagesAvailable() {
		int p = StringUtil.getInteger(pA, 1);
		if (p <= 0)
			p = 1;
		return p;
	}

	public int getItemsAvailable() {
		int p = StringUtil.getInteger(iA, 0);
		if (p < 0)
			p = 0;
		return p;
	}
}
