package com.cana.yundaex.service.transaction.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.cana.vbam.common.utils.MoneyArithUtil;
import com.cana.yundaex.common.dto.YundaexLineImportDTO;
import com.cana.yundaex.common.dto.YundaexLineImportRedisDTO;
import com.cana.yundaex.common.enums.TimeUnit;
import com.cana.yundaex.common.enums.YundaexApplyType;
import com.cana.yundaex.common.enums.YundaexAuditState;
import com.cana.yundaex.common.enums.YundaexJudge;
import com.cana.yundaex.dao.mapper.gen.YundaexCustomerApplyMapper;
import com.cana.yundaex.dao.po.YundaexCustomerApply;
import com.cana.yundaex.dao.utils.IDGenerator;
import com.cana.yundaex.service.IYundaexRetryTaskService;
import com.cana.yundaex.service.transaction.IYundaexLineImportService;
import com.cana.yundaex.service.transaction.IYundaexTstationInfoTransactionService;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.redis.client.SpringRedisClient;

@Service
public class YundaexLineImportServiceImpl implements IYundaexLineImportService {

	@Resource
	private YundaexCustomerApplyMapper ydCustomerApplyMapper;

	@Resource
	private IYundaexRetryTaskService yundaexRetryTaskService;
	
	@Resource
	private IYundaexTstationInfoTransactionService infoTransactionService;

	private final SpringRedisClient redisCache = SpringRedisClient.getInstance();

	@Override
	public void importExcelList(String redisKey) {
		// 获取redis数据
		YundaexLineImportRedisDTO redisDTO = (YundaexLineImportRedisDTO) redisCache.read(redisKey);
		if (redisDTO != null && CollectionUtils.isNotEmpty(redisDTO.getPassYundaexLineImportDTOs())) {
			for (YundaexLineImportDTO importDTO : redisDTO.getPassYundaexLineImportDTOs()) {
				// 将redis中数据转到mysql中
				YundaexCustomerApply ydCustomerApply = new YundaexCustomerApply();
				ydCustomerApply.setId(IDGenerator.generateCustomerApplyId());
				ydCustomerApply.setStationNo(importDTO.getStationNo());
				ydCustomerApply.setStationName(importDTO.getStationName());
				ydCustomerApply.setStationMgr(importDTO.getStationMgr());
				ydCustomerApply.setProvince(importDTO.getProvince());// 省
				ydCustomerApply.setCity(importDTO.getCity()); // 市
				ydCustomerApply.setAddress(importDTO.getAddress());
				ydCustomerApply.setBusiLimit(importDTO.getBusiLimit());
				ydCustomerApply.setBailBalance(MoneyArithUtil.convertStringToMoney(importDTO.getBailBalance()));
				ydCustomerApply.setYundaexJudge(YundaexJudge.getEnum(importDTO.getYundaexJudge()).name());
				ydCustomerApply.setCustName(importDTO.getCustName());
				ydCustomerApply.setPayAccountName(importDTO.getPayAccountName());
				ydCustomerApply.setPayAccount(importDTO.getPayAccount());
				// ydCustomerApply.setPayAccountAddress(importDTO.getPayAccountAddress());
				ydCustomerApply.setCustIdno(importDTO.getCustIdno());
				ydCustomerApply.setCustPhone(importDTO.getCustPhone());
				ydCustomerApply.setApplyCreditLimit(0L);
				ydCustomerApply.setLoanLimit("6个月");
				ydCustomerApply.setWhetherStationInfo("Y");
				ydCustomerApply.setYundaexExplain("线下数据");
				ydCustomerApply.setCreateTime(new Date());
				ydCustomerApply.setAccessAutomaticState(YundaexAuditState.WAIT.name());
				// 默认借款期限为3个月
				ydCustomerApply.setShortLoanLimit(3);
				ydCustomerApply.setLimitUnit(TimeUnit.MONTH.name());
				
				ydCustomerApply.setNotifyFlag(false);
				ydCustomerApply.setApplyType(YundaexApplyType.OFFLINE_APPLY.name());
				ydCustomerApplyMapper.insertSelective(ydCustomerApply);

				// 增加拉取数据网点经营数据任务
				infoTransactionService.stationPullTask(ydCustomerApply);
			}
		} else {
			throw WebException.instance("数据不存在");
		}

	}
}
