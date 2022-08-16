package com.ddim.happygo.service;

import java.sql.Timestamp;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ddim.happygo.dao.CouponDao;
import com.ddim.happygo.model.BigBanner;
import com.ddim.happygo.model.Coupon;
import com.ddim.happygo.model.Header;
import com.mdbs.jdbc.Page;
import com.mdbs.jdbc.PagingParameter;
import com.mdbs.util.Id;

/**
 * 建立日期：2015年3月9日
 * 程式摘要：com.ddim.happygo.service<P> 
 * 類別名稱：ManagerTaskService.java<P>
 * 程式內容說明：最新消息服務<P>
 * @author Yvonne
 */
@Service
public class CouponService {

	@Autowired
	private CouponDao couponDao;

	/**
	 * 取得啟用中的列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Coupon> getStateOnPage() throws Exception {
		return couponDao.getStateOnPage();
	}
}
