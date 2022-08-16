package com.ddim.happygo.web.admin.role;

/***
 * 建立日期：2015/03/12
 * 程式摘要：com.ddim.happygo.web.admin.role<P> 
 * 類別名稱：RoleForm.java<P>
 * 程式內容說明：後台角色表單物件<P>
 * @author Yvonne 
 * @version 1.0 
 * @since 1.0
 * */
public class RoleForm {

	private String id;
	private String name;
	private String status;
	private String[] tasksAry;
	private String[] parentItems;
	private String[] ids;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String[] getTasksAry() {
		return tasksAry;
	}

	public void setTasksAry(String[] tasksAry) {
		this.tasksAry = tasksAry;
	}

	public String[] getParentItems() {
		return parentItems;
	}

	public void setParentItems(String[] parentItems) {
		this.parentItems = parentItems;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

}