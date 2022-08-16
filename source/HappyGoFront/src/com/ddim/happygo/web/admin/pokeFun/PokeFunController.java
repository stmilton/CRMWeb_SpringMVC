package com.ddim.happygo.web.admin.pokeFun;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ddim.happygo.model.BigBanner;
import com.ddim.happygo.model.Coupon;
import com.ddim.happygo.model.Footer;
import com.ddim.happygo.model.Header;
import com.ddim.happygo.model.LeftSquare;
import com.ddim.happygo.model.LongAds;
import com.ddim.happygo.model.MajorFunction;
import com.ddim.happygo.model.Marquee;
import com.ddim.happygo.model.MinorFunction;
import com.ddim.happygo.model.RightSquare;
import com.ddim.happygo.service.BigBannerService;
import com.ddim.happygo.service.CouponService;
import com.ddim.happygo.service.FooterService;
//import com.ddim.happygo.model.Manager;
//import com.ddim.happygo.model.ManagerRole;
//import com.ddim.happygo.model.ManagerTask;
import com.ddim.happygo.service.HeaderService;
import com.ddim.happygo.service.LeftSquareService;
import com.ddim.happygo.service.LongAdsService;
import com.ddim.happygo.service.MajorFunctionService;
import com.ddim.happygo.service.MarqueeService;
import com.ddim.happygo.service.MinorFunctionService;
import com.ddim.happygo.service.RightSquareService;
//import com.ddim.happygo.service.ManagerRoleService;
//import com.ddim.happygo.service.ManagerService;
//import com.ddim.happygo.service.ManagerTaskService;
//import com.ddim.happygo.util.ManagerUtil;
import com.mdbs.util.LogUtil;
import com.mdbs.util.WebUtil;

/**
 * 建立日期：2015年3月7日
 * 程式摘要：com.ddim.happygo.web.admin.login<P> 
 * 類別名稱：LoginController.java<P>
 * 程式內容說明：管理者登入控制項<P>
 * @author Yvonne
 * */
@Controller
public class PokeFunController {
	
	@Value("${file.read.url}")
	private String fileReadUrl;
	
	@Value("${majorfunction.path}")
	private String majorFunctionPath;
	
	@Autowired
	private HeaderService headerService;
	
	@Autowired
	private FooterService footerService;
	
	@Autowired
	private MajorFunctionService majorFunctionService;
	
	@Autowired
	private MinorFunctionService minorFunctionService;
	
	final String[] DISALLOWED_FIELDS = new String[] { "dummy" };

	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
		binder.setDisallowedFields(DISALLOWED_FIELDS);
	}
	
	private void setCommonData(HttpServletRequest request) throws Exception {
		request.setAttribute("majorFunctionImagePath", fileReadUrl + majorFunctionPath);
	}

	/**
	 * 戳戳樂頁面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/home/pokefun")
	public String index(HttpServletRequest request,ModelMap model) throws Exception {
		try {
			setCommonData(request);
			List<Header> headerList= headerService.getStateOnPage();
			model.addAttribute("headerList", headerList);
			List<Footer> footerList = footerService.getStateOnPage();
			model.addAttribute("footerList", footerList);
			List<MajorFunction> majorFunctionList = majorFunctionService.getStateOnPage();
			model.addAttribute("majorFunctionList", majorFunctionList);
			List<MinorFunction> minorFunctionList = minorFunctionService.getStateOnPage();
			model.addAttribute("minorFunctionList", minorFunctionList);
		}
		catch (Exception e) {
			LogUtil.setErrorLog("list", e);
			return "home/pokefun";
		}
		return "home/pokefun";
	}
}
