package com.ddim.happygo.web.admin.specialDealer;


import java.sql.Timestamp;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;
import lombok.Data;
import com.ddim.happygo.util.ExcelImport;

/***
 * 建立日期：2015/03/12
 * 程式摘要：com.ddim.happygo.web.admin.news<P> 
 * 類別名稱：NewsForm.java<P>
 * 程式內容說明：最新消息表單物件<P>
 * @author Yvonne 
 * @version 1.0 
 * @since 1.0
 * */

@Data
public class SpecialDealerForm {

	private String id;
	private String name;
	
	@ExcelImport("特約商代碼")
	private String special_dealer_code;
	
	@ExcelImport("總公司名稱")
	private String head_office_name;
	
	@ExcelImport("特約商名稱")
	private String special_dealer_name;	
	
	@ExcelImport(value="特約商分類",kv="1-旅遊住宿;2-時尚購物;3-休閒娛樂;4-資訊3C")
	private String special_dealer_class;
	
	@ExcelImport("郵遞區號")
	private String zipcode;
	
	@ExcelImport("地址")
	private String address;
	
	@ExcelImport(value="是否顯示地址",kv="0-否;1-是")
	private String show_address;
	
	@ExcelImport("電話")
	private String phone;	
	
	@ExcelImport(value="是否顯示電話",kv="0-否;1-是")
	private String show_phone;
	
	@ExcelImport("簡介(PC)")
	private String introduce_pc;
	
	@ExcelImport("簡介(APP)")
	private String introduce_app;
	
	@ExcelImport("圖片")
	private String image;
	
	@ExcelImport("行動裝置圖片")
	private String image_app;
	
	@ExcelImport("TOP27用圖片")
	private String top27_image;
	
	@ExcelImport("基本兌點方式(PC)")
	private String basic_redemption_pc;
	
	@ExcelImport("基本兌點方式(APP)")
	private String basic_redemption_app;
	
	@ExcelImport(value="是否呈現",kv="0-不顯示;1-顯示")
	private String is_show;
	
	@ExcelImport(value="手機條碼哪裡用",kv="0-不可使用;1-可使用")
	private String mobile_phone_barcode;
	
	@ExcelImport("合約起日期")
	private Date contract_start_time;
	
	@ExcelImport("合約訖日期")
	private Date contract_end_time;
	
	@ExcelImport("連結網址")
	private String link;
	
	@ExcelImport(value="是否顯示連結網址",kv="0-否;1-是")
	private String show_link;
	
	@ExcelImport(value="連結開啟方式",kv="0-原視窗開啟;1-另開新視窗")
	private String open_method;	
	
	@ExcelImport(value="狀態",kv="0-停用;1-啟用")
	private String status;
	
	private int sort;	
	private String[] ids;
	private MultipartFile img_file;
	private MultipartFile image_app_file;
	private MultipartFile top27_image_file;
	
	private String excel;
	
	/**
	 * @return the excel
	 */
	public String getExcel() {
		return excel;
	}
	/**
	 * @param excel the excel to set
	 */
	public void setExcel(String excel) {
		this.excel = excel;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
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
	 * @return the ids
	 */
	public String[] getIds() {
		return ids;
	}
	/**
	 * @param ids the ids to set
	 */
	public void setIds(String[] ids) {
		this.ids = ids;
	}
	/**
	 * @return the img_file
	 */
	public MultipartFile getImg_file() {
		return img_file;
	}
	/**
	 * @param img_file the img_file to set
	 */
	public void setImg_file(MultipartFile img_file) {
		this.img_file = img_file;
	}
	/**
	 * @return the image_app_file
	 */
	public MultipartFile getImage_app_file() {
		return image_app_file;
	}
	/**
	 * @param image_app_file the image_app_file to set
	 */
	public void setImage_app_file(MultipartFile image_app_file) {
		this.image_app_file = image_app_file;
	}
	/**
	 * @return the top27_image_file
	 */
	public MultipartFile getTop27_image_file() {
		return top27_image_file;
	}
	/**
	 * @param top27_image_file the top27_image_file to set
	 */
	public void setTop27_image_file(MultipartFile top27_image_file) {
		this.top27_image_file = top27_image_file;
	}
	
}