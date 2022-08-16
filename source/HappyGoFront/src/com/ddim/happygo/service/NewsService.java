package com.ddim.happygo.service;

import java.sql.Timestamp;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ddim.happygo.dao.NewsDao;
import com.ddim.happygo.model.Header;
import com.ddim.happygo.model.News;
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
public class NewsService {

	@Autowired
	private NewsDao newsDao;

	
	/**
	 * 取得啟用中的列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<News> getStateOnPage() throws Exception {
		return newsDao.getStateOnPage();
	}
}
