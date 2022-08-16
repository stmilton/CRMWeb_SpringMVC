package com.ddim.happygo.web.admin.homepage;

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
public class HomepageController {
	
	@Value("${file.read.url}")
	private String fileReadUrl;
	
	@Value("${majorfunction.path}")
	private String majorFunctionPath;
	
	@Value("${bigBanner.path}")
	private String bigBannerPath;
	
	@Value("${leftSquare.path}")
	private String leftSquarePath;
	
	@Value("${rightSquare.path}")
	private String rightSquarePath;
	
	@Value("${longAds.path}")
	private String longAdsPath;
	
	@Value("${coupon.path}")
	private String couponPath;
	
	@Autowired
	private HeaderService headerService;
	
	@Autowired
	private FooterService footerService;
	
	@Autowired
	private MajorFunctionService majorFunctionService;
	
	@Autowired
	private MinorFunctionService minorFunctionService;
	
	@Autowired
	private BigBannerService bigBannerService;
	
	@Autowired
	private LeftSquareService leftSquareService;
	
	@Autowired
	private RightSquareService rightSquareService;
	
	@Autowired
	private LongAdsService longAdsService;
	
	@Autowired
	private MarqueeService marqueeService;
	
	@Autowired
	private CouponService couponService;
	
	final String[] DISALLOWED_FIELDS = new String[] { "dummy" };

	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
		binder.setDisallowedFields(DISALLOWED_FIELDS);
	}
	
	private void setCommonData(HttpServletRequest request) throws Exception {
		request.setAttribute("majorFunctionImagePath", fileReadUrl + majorFunctionPath);
		request.setAttribute("bigBannerImagePath", fileReadUrl + bigBannerPath);
		request.setAttribute("leftSquareImagePath", fileReadUrl + leftSquarePath);
		request.setAttribute("rightSquareImagePath", fileReadUrl + rightSquarePath);
		request.setAttribute("longAdsImagePath", fileReadUrl + longAdsPath);
		request.setAttribute("couponImagePath", fileReadUrl + couponPath);

	}

	/**
	 * 後台首頁
	 * 
	 * @return
	 */
	@RequestMapping(value = "/home/homepage")
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
			List<BigBanner> bigBannerList = bigBannerService.getStateOnPage();
			model.addAttribute("bigBannerList", bigBannerList);
			List<LeftSquare> leftSquareList = leftSquareService.getStateOnPage();
			model.addAttribute("leftSquareList", leftSquareList);
			List<RightSquare> rightSquareList = rightSquareService.getStateOnPage();
			model.addAttribute("rightSquareList", rightSquareList);
			List<LongAds> longAdsList = longAdsService.getStateOnPage();
			model.addAttribute("longAdsList", longAdsList);
			List<Marquee> marqueeList = marqueeService.getStateOnPage();
			model.addAttribute("marqueeList", marqueeList);
			List<Coupon> couponList = couponService.getStateOnPage();
			model.addAttribute("couponList", couponList);
		}
		
		catch (Exception e) {
			LogUtil.setErrorLog("list", e);
			return "home/homepage";
		}
		return "home/homepage";
	}

}
