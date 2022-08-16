package com.ddim.happygo.service;

import java.sql.Timestamp;
import java.util.GregorianCalendar;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ddim.happygo.dao.MarqueeDao;
import com.ddim.happygo.model.Header;
import com.ddim.happygo.model.Marquee;
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
public class MarqueeService {

	@Autowired
	private MarqueeDao marqueeDao;

	
	/**
	 * 取得分頁
	 * 
	 * @param name
	 * @param status
	 * @param paging
	 * @return
	 * @throws Exception
	 */
	public Page<Marquee> findPage(String name, String status, PagingParameter paging) throws Exception {
		return marqueeDao.findPage(name, status, paging);
	}

	/**
	 * 取得總筆數
	 * 
	 * @return
	 * @throws Exception
	 */
	public int countList() throws Exception {
		return marqueeDao.countList();
	}

	/**
	 * 取得單一物件
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Marquee get(String id) throws Exception {
		return marqueeDao.get(id);
	}

	/**
	 * 儲存
	 * 
	 * @param entity
	 * @param modifier
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	public void save(Marquee entity, String modifier) throws Exception {
		Timestamp now = new Timestamp(new GregorianCalendar().getTimeInMillis());
		if (StringUtils.isBlank(entity.getId())) {
			entity.setId(Id.id20());
			entity.setCreator(modifier);
			entity.setCreate_time(now);
			entity.setModifier(modifier);
			entity.setModify_time(now);
			int dataNums = marqueeDao.countList();
			entity.setSort(dataNums+1);
			marqueeDao.insert(entity);
		} else {
			entity.setModifier(modifier);
			entity.setModify_time(now);
			marqueeDao.update(entity);
		}
	}

	/**
	 * 刪除
	 * 
	 * @param ids
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	public void delete(String[] ids, String modifier) throws Exception {
		for (String id : ids) {
			Marquee entity = get(id);
			marqueeDao.delete(entity.getSort(),entity.getId());
		}
	}

	/**
	 * 更新狀態
	 * 
	 * @param ids
	 * @param status
	 * @param modifier
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	public void updateStatus(String[] ids, String status, String modifier) throws Exception {
		for (String id : ids) {
			Marquee entity = get(id);
			marqueeDao.updateStatus(entity.getId(), status, modifier);
		}
	}

	/**
	 * 更新排序
	 * 
	 * @param id
	 * @param sort
	 * @param modifier
	 * @return
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	public void updateSort(String id, int sort, String modifier) throws Exception {
		Marquee entity = get(id);
		marqueeDao.updateSort(id, entity.getSort(), sort, modifier);
	}

}
