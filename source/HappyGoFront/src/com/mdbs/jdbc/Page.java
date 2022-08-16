/*
 * Created on : 2011/2/24
 */
package com.mdbs.jdbc;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Ringo
 */
public class Page<E> {

	private int pageNo;
	private int pagesAvailable;
	private int pageSize;
	private int itemsAvailable;
	private List<E> pageItems = new ArrayList<E>();

	/**
	 * @return the pageNo
	 */
	public int getPageNo() {
		return pageNo;
	}

	/**
	 * @param pageNo
	 *            the pageNo to set
	 */
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	/**
	 * @return the pagesAvailable
	 */
	public int getPagesAvailable() {
		return pagesAvailable;
	}

	/**
	 * @param pagesAvailable
	 *            the pagesAvailable to set
	 */
	public void setPagesAvailable(int pagesAvailable) {
		this.pagesAvailable = pagesAvailable;
	}

	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize
	 *            the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return the itemsAvailable
	 */
	public int getItemsAvailable() {
		return itemsAvailable;
	}

	/**
	 * @param itemsAvailable
	 *            the itemsAvailable to set
	 */
	public void setItemsAvailable(int itemsAvailable) {
		this.itemsAvailable = itemsAvailable;
	}

	/**
	 * @return the pageItems
	 */
	public List<E> getPageItems() {
		return pageItems;
	}

	/**
	 * @param pageItems
	 *            the pageItems to set
	 */
	public void setPageItems(List<E> pageItems) {
		this.pageItems = pageItems;
	}
}
