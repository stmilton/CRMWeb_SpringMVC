package com.ddim.happygo.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/***
 * 建立日期：2016/1/10 
 * 程式摘要：com.ddim.mpos.domain
 * 類別名稱：CardMemberJoinBasic.java
 * 程式內容說明：最新消息物件
 * @author Yvonne
 * */
public class SpecialDealer implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	private String special_dealer_code;
	private String head_office_name;
	private String special_dealer_name;
	private String special_dealer_class;
	private String zipcode;
	private String address;
	private String show_address;
	private String phone;	
	private String show_phone;
	private String introduce_pc;
	private String introduce_app;
	private String image;
	private String image_app;
	private String top27_image;
	private String basic_redemption_pc;
	private String basic_redemption_app;
	private String is_show;
	private String mobile_phone_barcode;
	private Date contract_start_time;
	private Date contract_end_time;
	private String link;
	private String show_link;
	private String open_method;	
	private String status;
	private int sort;
	private String creator;
	private Timestamp create_time;
	private String modifier;
	private Timestamp modify_time;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the special_dealer_code
	 */
	public String getSpecial_dealer_code() {
		return special_dealer_code;
	}
	/**
	 * @param special_dealer_code the special_dealer_code to set
	 */
	public void setSpecial_dealer_code(String special_dealer_code) {
		this.special_dealer_code = special_dealer_code;
	}
	/**
	 * @return the head_office_name
	 */
	public String getHead_office_name() {
		return head_office_name;
	}
	/**
	 * @param head_office_name the head_office_name to set
	 */
	public void setHead_office_name(String head_office_name) {
		this.head_office_name = head_office_name;
	}
	/**
	 * @return the special_dealer_name
	 */
	public String getSpecial_dealer_name() {
		return special_dealer_name;
	}
	/**
	 * @param special_dealer_name the special_dealer_name to set
	 */
	public void setSpecial_dealer_name(String special_dealer_name) {
		this.special_dealer_name = special_dealer_name;
	}
	/**
	 * @return the special_dealer_class
	 */
	public String getSpecial_dealer_class() {
		return special_dealer_class;
	}
	/**
	 * @param special_dealer_class the special_dealer_class to set
	 */
	public void setSpecial_dealer_class(String special_dealer_class) {
		this.special_dealer_class = special_dealer_class;
	}
	/**
	 * @return the zipcode
	 */
	public String getZipcode() {
		return zipcode;
	}
	/**
	 * @param zipcode the zipcode to set
	 */
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the show_address
	 */
	public String getShow_address() {
		return show_address;
	}
	/**
	 * @param show_address the show_address to set
	 */
	public void setShow_address(String show_address) {
		this.show_address = show_address;
	}
	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * @return the show_phone
	 */
	public String getShow_phone() {
		return show_phone;
	}
	/**
	 * @param show_phone the show_phone to set
	 */
	public void setShow_phone(String show_phone) {
		this.show_phone = show_phone;
	}
	/**
	 * @return the introduce_pc
	 */
	public String getIntroduce_pc() {
		return introduce_pc;
	}
	/**
	 * @param introduce_pc the introduce_pc to set
	 */
	public void setIntroduce_pc(String introduce_pc) {
		this.introduce_pc = introduce_pc;
	}
	/**
	 * @return the introduce_app
	 */
	public String getIntroduce_app() {
		return introduce_app;
	}
	/**
	 * @param introduce_app the introduce_app to set
	 */
	public void setIntroduce_app(String introduce_app) {
		this.introduce_app = introduce_app;
	}
	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}
	/**
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}
	/**
	 * @return the image_app
	 */
	public String getImage_app() {
		return image_app;
	}
	/**
	 * @param image_app the image_app to set
	 */
	public void setImage_app(String image_app) {
		this.image_app = image_app;
	}
	/**
	 * @return the top27_image
	 */
	public String getTop27_image() {
		return top27_image;
	}
	/**
	 * @param top27_image the top27_image to set
	 */
	public void setTop27_image(String top27_image) {
		this.top27_image = top27_image;
	}
	/**
	 * @return the basic_redemption_pc
	 */
	public String getBasic_redemption_pc() {
		return basic_redemption_pc;
	}
	/**
	 * @param basic_redemption_pc the basic_redemption_pc to set
	 */
	public void setBasic_redemption_pc(String basic_redemption_pc) {
		this.basic_redemption_pc = basic_redemption_pc;
	}
	/**
	 * @return the basic_redemption_app
	 */
	public String getBasic_redemption_app() {
		return basic_redemption_app;
	}
	/**
	 * @param basic_redemption_app the basic_redemption_app to set
	 */
	public void setBasic_redemption_app(String basic_redemption_app) {
		this.basic_redemption_app = basic_redemption_app;
	}
	/**
	 * @return the is_show
	 */
	public String getIs_show() {
		return is_show;
	}
	/**
	 * @param is_show the is_show to set
	 */
	public void setIs_show(String is_show) {
		this.is_show = is_show;
	}
	/**
	 * @return the mobile_phone_barcode
	 */
	public String getMobile_phone_barcode() {
		return mobile_phone_barcode;
	}
	/**
	 * @param mobile_phone_barcode the mobile_phone_barcode to set
	 */
	public void setMobile_phone_barcode(String mobile_phone_barcode) {
		this.mobile_phone_barcode = mobile_phone_barcode;
	}
	/**
	 * @return the contract_start_time
	 */
	public Date getContract_start_time() {
		return contract_start_time;
	}
	/**
	 * @param contract_start_time the contract_start_time to set
	 */
	public void setContract_start_time(Date contract_start_time) {
		this.contract_start_time = contract_start_time;
	}
	/**
	 * @return the contract_end_time
	 */
	public Date getContract_end_time() {
		return contract_end_time;
	}
	/**
	 * @param contract_end_time the contract_end_time to set
	 */
	public void setContract_end_time(Date contract_end_time) {
		this.contract_end_time = contract_end_time;
	}
	/**
	 * @return the link
	 */
	public String getLink() {
		return link;
	}
	/**
	 * @param link the link to set
	 */
	public void setLink(String link) {
		this.link = link;
	}
	/**
	 * @return the show_link
	 */
	public String getShow_link() {
		return show_link;
	}
	/**
	 * @param show_link the show_link to set
	 */
	public void setShow_link(String show_link) {
		this.show_link = show_link;
	}
	/**
	 * @return the open_method
	 */
	public String getOpen_method() {
		return open_method;
	}
	/**
	 * @param open_method the open_method to set
	 */
	public void setOpen_method(String open_method) {
		this.open_method = open_method;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the sort
	 */
	public int getSort() {
		return sort;
	}
	/**
	 * @param sort the sort to set
	 */
	public void setSort(int sort) {
		this.sort = sort;
	}
	/**
	 * @return the creator
	 */
	public String getCreator() {
		return creator;
	}
	/**
	 * @param creator the creator to set
	 */
	public void setCreator(String creator) {
		this.creator = creator;
	}
	/**
	 * @return the create_time
	 */
	public Timestamp getCreate_time() {
		return create_time;
	}
	/**
	 * @param create_time the create_time to set
	 */
	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}
	/**
	 * @return the modifier
	 */
	public String getModifier() {
		return modifier;
	}
	/**
	 * @param modifier the modifier to set
	 */
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	/**
	 * @return the modify_time
	 */
	public Timestamp getModify_time() {
		return modify_time;
	}
	/**
	 * @param modify_time the modify_time to set
	 */
	public void setModify_time(Timestamp modify_time) {
		this.modify_time = modify_time;
	}
}
