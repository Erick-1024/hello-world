package com.cana.early.warning.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cana.early.warning.dao.mapper.gen.EarlywarningSystemEventGenerateRecordMapper;
import com.cana.early.warning.dao.po.EarlywarningSystemEventGenerateRecordExample;
import com.cana.early.warning.service.IEarlywarningSystemEventGenerateRecordService;

@Service
public class EarlywarningSystemEventGenerateRecordServiceImpl implements IEarlywarningSystemEventGenerateRecordService {

	@Resource
	private EarlywarningSystemEventGenerateRecordMapper earlywarningSystemEventGenerateRecordMapper;
	
	@Override
	public boolean isExistRecord(String productId, String financeId, String outCustomerId, Date date) {
		EarlywarningSystemEventGenerateRecordExample earlywarningSystemEventGenerateRecordExample = new EarlywarningSystemEventGenerateRecordExample();
		earlywarningSystemEventGenerateRecordExample.createCriteria().andProductIdEqualTo(productId).andFinanceIdEqualTo(financeId).andOutCustomerIdEqualTo(outCustomerId).andDateEqualTo(new SimpleDateFormat("yyyy-MM-dd").format(date));
		return earlywarningSystemEventGenerateRecordMapper.countByExample(earlywarningSystemEventGenerateRecordExample) == 1;
	}

	@Override
	public boolean isExistRecordWithMonth(String productId, String financeId, String outCustomerId, Date date) {
		EarlywarningSystemEventGenerateRecordExample earlywarningSystemEventGenerateRecordExample = new EarlywarningSystemEventGenerateRecordExample();
		earlywarningSystemEventGenerateRecordExample.createCriteria().andProductIdEqualTo(productId).andFinanceIdEqualTo(financeId).andOutCustomerIdEqualTo(outCustomerId).andDateEqualTo(new SimpleDateFormat("yyyy-MM").format(date));
		return earlywarningSystemEventGenerateRecordMapper.countByExample(earlywarningSystemEventGenerateRecordExample) == 1;
	}

}
