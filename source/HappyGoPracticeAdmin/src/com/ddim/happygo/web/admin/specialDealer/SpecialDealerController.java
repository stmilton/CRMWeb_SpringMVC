package com.ddim.happygo.web.admin.specialDealer;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.ddim.happygo.Constants;
import com.ddim.happygo.MessageConstants;
import com.ddim.happygo.model.SpecialDealer;
import com.ddim.happygo.service.ExcelToSpecialDealerService;
import com.ddim.happygo.service.SpecialDealerService;
import com.ddim.happygo.util.ExcelUtils;
import com.ddim.happygo.util.ManagerUtil;
import com.ddim.happygo.util.NewExcelUtils;
import com.mdbs.jdbc.Page;
import com.mdbs.jdbc.PagingParameter;
import com.mdbs.util.LogUtil;

/**
 * 建立日期：2015年3月7日
 * 程式摘要：com.ddim.happygo.web.admin.specialDealer<P> 
 * 類別名稱：SpecialDealerController.java<P>
 * 程式內容說明：最新消息控制項<P>
 * @author Yvonne
 * */
@Controller
@RequestMapping(value = "/admin/specialdealer")
public class SpecialDealerController {
	
	@Value("${page_size}")
	private int pageItems;
	
	@Autowired
	private SpecialDealerService specialDealerService;
	
	@Autowired
	private ExcelToSpecialDealerService excelToSpecialDealerService;
	
	@Autowired
	private ManagerUtil managerUtil;
	
	@Autowired
	private SpecialDealerFormValidator formValidator;
	
	@Autowired
	private ExcelFormValidator excelFormValidator;
	
	
	@Resource(name = "statusOption")
	private Map<String, String> statusOption;

	@Resource(name = "openMethodOption")
	private Map<String, String> openMethodOption;
	
	@Resource(name = "specialDealerClassOption")
	private Map<String, String> specialDealerClassOption;
	
	@Resource(name = "booleanOption")
	private Map<String, String> showAddressOption;
	
	@Resource(name = "booleanOption")
	private Map<String, String> showPhoneOption;
	
	@Resource(name = "isShowOption")
	private Map<String, String> isShowOption;
	
	@Resource(name = "mobilePhoneBarcodeOption")
	private Map<String, String> mobilePhoneBarcodeOption;
	
	@Resource(name = "booleanOption")
	private Map<String, String> showLinkOption;
	
	@Value("${file.upload.path}")
	private String fileUploadPath;
	
	@Value("${specialDealer.path}")
	private String specialDealerPath;
	
	@Value("${excel.path}")
	private String excelPath;
	
	@Value("${file.read.url}")
	private String fileReadUrl;

	protected String getRightID() {
		return Constants.SPECIAL_DEALER_TASK_KEY;
	}

	protected String getPath() {
		return "admin/specialDealer";
	}
	
	final String[] DISALLOWED_FIELDS = new String[] { "dummy" };

	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
		binder.setDisallowedFields(DISALLOWED_FIELDS);
	}
	
	private void setCommonData(HttpServletRequest request) throws Exception {
		request.setAttribute("specialDealerClassOption", specialDealerClassOption);
		request.setAttribute("showAddressOption", showAddressOption);
		request.setAttribute("showPhoneOption", showPhoneOption);
		request.setAttribute("isShowOption", isShowOption);
		request.setAttribute("mobilePhoneBarcodeOption", mobilePhoneBarcodeOption);
		request.setAttribute("showLinkOption", showLinkOption);		
		request.setAttribute("statusOption", statusOption);
		request.setAttribute("openMethodOption", openMethodOption);
		request.setAttribute("imagePath", fileReadUrl + specialDealerPath);
		request.setAttribute("excelPath", fileReadUrl + excelPath);
		request.setAttribute("countList", specialDealerService.countList());
		
	}

	/**
	 * 列表
	 * @param request
	 * @param form
	 * @param paging
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/list")
	
	public String list(HttpServletRequest request, @ModelAttribute("command") SpecialDealerForm form, PagingParameter paging,
			ModelMap model) throws Exception {
		try {
			String returnPath = managerUtil.haveRight(request, getRightID());
			if (returnPath != null) {
				return returnPath;
			}
			setCommonData(request);
			paging.setPageSize(pageItems);
			Page<SpecialDealer> page = specialDealerService.findPage(form.getSpecial_dealer_class(),form.getName(), form.getStatus(), paging);
			model.addAttribute("page", page);
			return getPath() + "/list";
		} catch (Exception e) {
			LogUtil.setErrorLog("list", e);
			model.addAttribute("message", MessageConstants.MESSAGE_SYSTEM_BUSY);
			model.addAttribute("relativeUrl", Constants.ADMIN_INDEX_URI);
			return "common/jsAlert";
		}
	}

	/**
	 * 編輯
	 * @param request
	 * @param id
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/edit")
	public String edit(HttpServletRequest request, @ModelAttribute("command") SpecialDealerForm form, ModelMap model)
			throws Exception {
		try {
			String returnPath = managerUtil.haveRight(request, getRightID());
			if (returnPath != null) {
				return returnPath;
			}
			if (StringUtils.isNotBlank(form.getId())) {
				SpecialDealer data = specialDealerService.get(form.getId());
				if (data == null) {
					model.addAttribute("message", MessageConstants.MESSAGE_NO_DATA);
					model.addAttribute("relativeUrl", "list.do");
					return "common/jsAlert";
				}
				BeanUtils.copyProperties(data, form);
				request.setAttribute("zipcode", data.getZipcode());
			}
			setCommonData(request);
			return getPath() + "/edit";
		} catch (Exception e) {
			LogUtil.setErrorLog("edit", e);
			model.addAttribute("message", MessageConstants.MESSAGE_SYSTEM_BUSY);
			model.addAttribute("relativeUrl", Constants.ADMIN_INDEX_URI);
			return "common/jsAlert";
		}
	}
	
	/**
	 * 存檔
	 * @param request
	 * @param form
	 * @param result
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(HttpServletRequest request, @ModelAttribute("command") SpecialDealerForm form, BindingResult result,
			ModelMap model) throws Exception {
		try {
			String returnPath = managerUtil.haveRight(request, getRightID());
			if (returnPath != null) {
				return returnPath;
			}
			formValidator.validate(form, result, fileUploadPath + specialDealerPath);
			if (result.hasErrors()) {
				setCommonData(request);
				return getPath() + "/edit";
			}
			SpecialDealer entity = new SpecialDealer();
			BeanUtils.copyProperties(form, entity);
			specialDealerService.save(entity, managerUtil.getManagerAccount(request));
			LogUtil.setSaveLog(managerUtil.getManagerAccount(request),
					"save specialDealer success: " + entity.getId());
			model.addAttribute("message", MessageConstants.MESSAGE_SAVE_SUCCESS);
			model.addAttribute("relativeUrl", "list.do");
			return "common/jsAlert";
		} catch (Exception e) {
			LogUtil.setErrorLog("save", e);
			model.addAttribute("message", MessageConstants.MESSAGE_SYSTEM_BUSY);
			model.addAttribute("relativeUrl", Constants.ADMIN_INDEX_URI);
			return "common/jsAlert";
		}
	}

	/**
	 * 更新狀態
	 * @param request
	 * @param ids
	 * @param status
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(HttpServletRequest request, @ModelAttribute("command") SpecialDealerForm form, ModelMap model)
			throws Exception {
		try {
			String returnPath = managerUtil.haveRight(request, getRightID());
			if (returnPath != null) {
				return returnPath;
			}
			specialDealerService.updateStatus(form.getIds(), form.getStatus(), managerUtil.getManagerAccount(request));
			LogUtil.setSaveLog(managerUtil.getManagerAccount(request),
					"update specialDealer status success: " + form.getIds());
			model.addAttribute("message", MessageConstants.MESSAGE_SAVE_SUCCESS);
			model.addAttribute("relativeUrl", "list.do");
			return "common/jsAlert";
		} catch (Exception e) {
			LogUtil.setErrorLog("update", e);
			model.addAttribute("message", MessageConstants.MESSAGE_SYSTEM_BUSY);
			model.addAttribute("relativeUrl", Constants.ADMIN_INDEX_URI);
			return "common/jsAlert";
		}
	}

	/**
	 * 刪除
	 * @param request
	 * @param ids
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(HttpServletRequest request, @ModelAttribute("command") SpecialDealerForm form, ModelMap model)
			throws Exception {
		try {
			String returnPath = managerUtil.haveRight(request, getRightID());
			if (returnPath != null) {
				return returnPath;
			}
			specialDealerService.delete(form.getIds(), managerUtil.getManagerAccount(request));
			LogUtil.setSaveLog(managerUtil.getManagerAccount(request),
					"delete specialDealer success: " + form.getIds());
			model.addAttribute("message", MessageConstants.MESSAGE_DELETE_SUCCESS);
			model.addAttribute("relativeUrl", "list.do");
			return "common/jsAlert";
		} catch (Exception e) {
			LogUtil.setErrorLog("delete", e);
			model.addAttribute("message", MessageConstants.MESSAGE_SYSTEM_BUSY);
			model.addAttribute("relativeUrl", Constants.ADMIN_INDEX_URI);
			return "common/jsAlert";
		}
	}

	/**
	 * 變更排序
	 * @param request
	 * @param form
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateSort")
	public String updateSort(HttpServletRequest request, @ModelAttribute("command") SpecialDealerForm form, ModelMap model)
			throws Exception {
		try {
			String returnPath = managerUtil.haveRight(request, getRightID());
			if (returnPath != null) {
				return returnPath;
			}
			specialDealerService.updateSort(form.getId(), form.getSort(), managerUtil.getManagerAccount(request));
			LogUtil.setSaveLog(managerUtil.getManagerAccount(request),
					"update specialDealer sort success: " + form.getIds());
			model.addAttribute("message", "更新排序成功!");
			model.addAttribute("relativeUrl", "list.do");
			return "common/jsAlert";
		} catch (Exception e) {
			LogUtil.setErrorLog("updateSort", e);
			model.addAttribute("message", MessageConstants.MESSAGE_SYSTEM_BUSY);
			model.addAttribute("relativeUrl", Constants.ADMIN_INDEX_URI);
			return "common/jsAlert";
		}
	}
	
	/**
	 * Excel檔
	 * @param request
	 * @param id
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/importExcel")
	public String importExcel(HttpServletRequest request, @ModelAttribute("command") SpecialDealerForm form, ModelMap model) throws Exception {
		try {
			String returnPath = managerUtil.haveRight(request, getRightID());
			if (returnPath != null) {
				return returnPath;
			}
			
			return getPath() + "/importExcel";
		} catch (Exception e) {
			LogUtil.setErrorLog("importExcel", e);
			model.addAttribute("message", MessageConstants.MESSAGE_SYSTEM_BUSY);
			model.addAttribute("relativeUrl", Constants.ADMIN_INDEX_URI);
			return "common/jsAlert";
		}
	}
	
	/**
	 * 存Excel檔
	 * @param request
	 * @param form
	 * @param result
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveExcel", method = RequestMethod.POST)
	public String saveExcel(HttpServletRequest request, @ModelAttribute("command") ExcelForm form, BindingResult result,
			ModelMap model) throws Exception {
		
		try {
			String returnPath = managerUtil.haveRight(request, getRightID());
			if (returnPath != null) {
				return returnPath;
			}
			excelFormValidator.validate(form, result, fileUploadPath + excelPath);
			if (result.hasErrors()) {
				return getPath() + "/importExcel";
			}
			SpecialDealer entity = new SpecialDealer();			
			List<SpecialDealerForm> users = excelToSpecialDealerService.excelToForm(form.getExcel_file());
						
			StringBuilder validateMessage = new StringBuilder();
			
			for (SpecialDealerForm user : users) {
				excelFormValidator.validateContent(user, validateMessage);
				
				if (!validateMessage.toString().isEmpty()) {
					
					model.addAttribute("message", validateMessage.toString());
					model.addAttribute("relativeUrl", "importExcel.do");
					return "common/jsAlert";
//					return getPath() + "/importExcel";
				}
			 	BeanUtils.copyProperties(user, entity);
				specialDealerService.save(entity, managerUtil.getManagerAccount(request));
			}
			LogUtil.setSaveLog(managerUtil.getManagerAccount(request),
					"save specialDealer success: " + entity.getId());
			model.addAttribute("message", MessageConstants.MESSAGE_SAVE_SUCCESS);
			model.addAttribute("relativeUrl", "list.do");
			return "common/jsAlert";
		} catch (Exception e) {
			LogUtil.setErrorLog("saveExcel", e);
			model.addAttribute("message", MessageConstants.MESSAGE_SYSTEM_BUSY);
			model.addAttribute("relativeUrl", Constants.ADMIN_INDEX_URI);
			return "common/jsAlert";
		}
	}
	
	@RequestMapping(value = "/exportExcel")
	public String export(HttpServletRequest request, HttpServletResponse response , ModelMap model) throws Exception {

		
//		try {
//			String returnPath = managerUtil.haveRight(request, getRightID());
//			if (returnPath != null) {
//				return returnPath;
//			}
//			// 建立一個Excel檔案
//			HSSFWorkbook workbook = new HSSFWorkbook();
//			// 建立一個工作表
//			HSSFSheet sheet = workbook.createSheet("Sheet0");
//			// 新增表頭行
//			HSSFRow hssfRow = sheet.createRow(0);
//			// 設定單元格格式居中
//			HSSFCellStyle cellStyle = workbook.createCellStyle();
//			cellStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
//			// 新增表頭內容
//			String[] cols = {"總公司代號","總公司名稱","HG特約商名稱","狀態(0:停用 1:啟用)","啟用日","停用日","郵遞區號","地址","是否顯示地址(0:否 1:是)","電話","是否顯示電話(0:否 1:是)","簡介(PC)","簡介(app)","PC用圖片","行動裝置用圖片","TOP27用圖片","指定連結","是否顯示連結(0:否 1:是)","連結開啟方式(0:原視窗 1:新視窗)","基本兌點方式(PC)","基本兌點方式(app)","手機條碼哪裡用(0:不可使用 1:可使用)","特約商分類","是否呈現(0:否 1:是)"};
//			HSSFCell headCell = null;
//			int count = 0, rowCount = 0;
//			for (String col : cols) {
//				headCell = hssfRow.createCell(count);
//				headCell.setCellValue(col);
//				headCell.setCellStyle(cellStyle);
//				count++;
//			}
//			SpecialDealerForm form = new SpecialDealerForm();
//			
//			// 新增資料內容
//			HSSFCell cell = null;
////			for (String id : form.getIds()) {
////				count = 0;
////				hssfRow = sheet.createRow(rowCount+1);
////				AuthorizedSetting data = authorizedSettingService.get(id);
////				Date dateStart=new Date(data.getStart_time().getTime());
////				Date dateEnd=new Date(data.getEnd_time().getTime());
////				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
////				cell = hssfRow.createCell(count);cell.setCellValue(data.getCo_code());cell.setCellStyle(cellStyle);count++;cell = hssfRow.createCell(count);cell.setCellValue(data.getName());cell.setCellStyle(cellStyle);count++;cell = hssfRow.createCell(count);cell.setCellValue(data.getAuthorized_name());cell.setCellStyle(cellStyle);count++;cell = hssfRow.createCell(count);cell.setCellValue(data.getStatus());cell.setCellStyle(cellStyle);count++;cell = hssfRow.createCell(count);cell.setCellValue(simpleDateFormat.format(dateStart));cell.setCellStyle(cellStyle);count++;cell = hssfRow.createCell(count);cell.setCellValue(simpleDateFormat.format(dateEnd));cell.setCellStyle(cellStyle);count++;cell = hssfRow.createCell(count);cell.setCellValue(data.getPostal_code());cell.setCellStyle(cellStyle);count++;cell = hssfRow.createCell(count);cell.setCellValue(data.getAddress());cell.setCellStyle(cellStyle);count++;cell = hssfRow.createCell(count);cell.setCellValue(data.getIs_show_address());cell.setCellStyle(cellStyle);count++;cell = hssfRow.createCell(count);cell.setCellValue(data.getPhone());cell.setCellStyle(cellStyle);count++;cell = hssfRow.createCell(count);cell.setCellValue(data.getIs_show_phone());cell.setCellStyle(cellStyle);count++;cell = hssfRow.createCell(count);cell.setCellValue(data.getIntroduction_pc());cell.setCellStyle(cellStyle);count++;cell = hssfRow.createCell(count);cell.setCellValue(data.getIntroduction_app());cell.setCellStyle(cellStyle);count++;cell = hssfRow.createCell(count);cell.setCellValue(data.getImage());cell.setCellStyle(cellStyle);count++;cell = hssfRow.createCell(count);cell.setCellValue(data.getImage_mobile());cell.setCellStyle(cellStyle);count++;cell = hssfRow.createCell(count);cell.setCellValue(data.getImage_top27());cell.setCellStyle(cellStyle);count++;cell = hssfRow.createCell(count);cell.setCellValue(data.getUrl());cell.setCellStyle(cellStyle);count++;cell = hssfRow.createCell(count);cell.setCellValue(data.getIs_show_url());cell.setCellStyle(cellStyle);count++;cell = hssfRow.createCell(count);cell.setCellValue(data.getOpening_method());cell.setCellStyle(cellStyle);count++;cell = hssfRow.createCell(count);cell.setCellValue(data.getRedeeming_method_pc());cell.setCellStyle(cellStyle);count++;cell = hssfRow.createCell(count);cell.setCellValue(data.getRedeeming_method_app());cell.setCellStyle(cellStyle);count++;cell = hssfRow.createCell(count);cell.setCellValue(data.getBarcode());cell.setCellStyle(cellStyle);count++;cell = hssfRow.createCell(count);cell.setCellValue(data.getAuthorized_type());cell.setCellStyle(cellStyle);count++;cell = hssfRow.createCell(count);cell.setCellValue(data.getIs_show());cell.setCellStyle(cellStyle);count++;
////				rowCount++;
////			}
//			for (int i=0; i < count; i++) {
//				sheet.autoSizeColumn((short)i);
//			    sheet.setColumnWidth(i, sheet.getColumnWidth(i) * 15 / 10);
//			}
//			// 儲存Excel檔案
//			try {
//				SimpleDateFormat date_time_format = new SimpleDateFormat("yyyyMMddHHmmss");
//				Date date = new Date();
//				response.addHeader("Content-Disposition", "attachment;filename="+ new String("merchant_" + date_time_format.format(date) + ".xls"));
//				OutputStream os= new BufferedOutputStream(response.getOutputStream());
//				workbook.write(os);
//				os.flush();
//				os.close();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			LogUtil.setSaveLog(managerUtil.getManagerAccount(request),
//					"exportExcel authorizedSetting status success: " + form.getIds());
//			model.addAttribute("message", MessageConstants.MESSAGE_EXPORT_SUCCESS);
//			model.addAttribute("relativeUrl", "list.do");
//			return "common/jsAlert";
//		} catch (Exception e) {
//			LogUtil.setErrorLog("exportExcel", e);
//			model.addAttribute("message", MessageConstants.MESSAGE_SYSTEM_BUSY);
//			model.addAttribute("relativeUrl", Constants.LOGIN_PAGE);
//			return "common/jsAlert";
//		}
			
		
		try {
			String returnPath = managerUtil.haveRight(request, getRightID());
			if (returnPath != null) {
				return returnPath;
			}
			List<List<Object>> sheetDataList = excelToSpecialDealerService.exportExcel();
			// 匯出資料
			ExcelUtils.export(response, "Special_Dealer_List","特約商表", sheetDataList);
			
			model.addAttribute("message", MessageConstants.MESSAGE_SAVE_SUCCESS);
			model.addAttribute("relativeUrl", "list.do");
			return "common/jsAlert";
	
		}catch (Exception e) {
			LogUtil.setErrorLog("saveExcel", e);
			model.addAttribute("message", MessageConstants.MESSAGE_SYSTEM_BUSY);
			model.addAttribute("relativeUrl", Constants.ADMIN_INDEX_URI);
			return "common/jsAlert";
		}
	}
}
