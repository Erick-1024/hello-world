package com.cana.crawler.shixincourt.server.task;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.cana.crawler.dao.mongo.dao.IShixinCourtBlackListDao;
import com.cana.crawler.dao.mongo.entity.ShixinCourtBlackList;
import com.cana.crawler.shixincourt.server.processor.ShixinCourtProcessor;
import com.cana.vbam.common.crawler.enums.CourtExecutionSubject;

/**
 * 爬取黑名单任务
 * 
 * @author renshui
 *
 */
@Service
public class CrawlBlackListTask {

	private static Logger logger = LoggerFactory.getLogger(CrawlBlackListTask.class);
	
	@Resource
	private IShixinCourtBlackListDao<ShixinCourtBlackList, ObjectId> dao;


	@Scheduled(fixedDelay = DateUtils.MILLIS_PER_MINUTE * 30)
	public void doTask() throws Exception {
		try{
			ShixinCourtProcessor processor = new ShixinCourtProcessor();
			List<Pair<String,String>> individualBlackList = new ArrayList<>();
			List<Pair<String,String>> companyBlackList = new ArrayList<>();
			processor.crawlNewBlackList(individualBlackList, companyBlackList);
			insertBlackList(CourtExecutionSubject.individual, individualBlackList);
			insertBlackList(CourtExecutionSubject.company, companyBlackList);
		}catch(Throwable thr){
			logger.error("", thr);
		}
	}


	private void insertBlackList(CourtExecutionSubject subject, List<Pair<String, String>> blackList) {
		
		if(CollectionUtils.isEmpty(blackList))
			return;
		
		for(Pair<String, String> pair : blackList){
			
			String code = StringUtils.trimToEmpty(pair.getRight());
			String name = StringUtils.trimToEmpty(pair.getLeft());
			
			ShixinCourtBlackList item = dao.findByCodeAndName(code, name);
			if(item != null)
				continue;
			
			item = new ShixinCourtBlackList();
			item.setSubject(subject);
			item.setCode(code);
			item.setName(name);
			item.setCreateDate(new DateTime());
			dao.create(item);
		}
		
	}

}
