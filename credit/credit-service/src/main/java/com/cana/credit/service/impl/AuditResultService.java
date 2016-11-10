package com.cana.credit.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cana.credit.dao.po.CustomerApply;
import com.cana.credit.service.IAuditResultService;
import com.cana.credit.service.ICreditMessageService;
import com.cana.credit.service.transaction.ICreditCustomerApplyTransactionService;
import com.cana.flight.finance.dao.mapper.gen.TzCustomerInfoMapper;
import com.cana.flight.finance.dao.po.TzCustomerInfo;
import com.cana.flight.finance.dao.po.TzCustomerInfoExample;
import com.cana.vbam.common.credit.dto.apply.CustomerApplyRequestDTO;
import com.cana.vbam.common.credit.enums.NoticeScene;
import com.cana.vbam.common.credit.openapi.CreditNoticeParam;

@Service
public class AuditResultService implements IAuditResultService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private ICreditMessageService creditMessageServiceImpl; 
	
	@Resource
	private TzCustomerInfoMapper tzCustomerInfoMapper;
	
	@Resource
	private ICreditCustomerApplyTransactionService creditCustomerApplyTransactionServiceImpl;

	@Override
	public void saveTravelzenAuditResult(CustomerApplyRequestDTO customerApplyDTO) throws Exception {
		CustomerApply customerApply = creditCustomerApplyTransactionServiceImpl.saveTravelzenAuditResult(customerApplyDTO);
		CreditNoticeParam creditNoticeParam = new CreditNoticeParam();
		creditNoticeParam.setInwhitelist(customerApply.getInWhitelist());
		creditNoticeParam.setNoticeScene(NoticeScene.TZ_CUSTOMER_APPLY);
		creditNoticeParam.setCompanyName(customerApply.getCompanyName());
		creditNoticeParam.setEmail(customerApply.getEmail());
		creditNoticeParam.setPhoneNumber(customerApply.getPhoneNumber());
		creditNoticeParam.setAccessManualState(customerApply.getAccessManualState());
		logger.info("发送邮件和短信");
		creditMessageServiceImpl.sendMailForTzCustomerApply(creditNoticeParam);
		creditMessageServiceImpl.sendSmsMessageForTzCustomerApply(creditNoticeParam);
	}
	
	@Override
	public boolean checkTzCustomerInfoExist(String tzCustomerId){
		TzCustomerInfoExample example = new TzCustomerInfoExample();
		example.createCriteria().andTzCustomerIdEqualTo(tzCustomerId);
		List<TzCustomerInfo> tzCustomerInfos = tzCustomerInfoMapper.selectByExample(example);
		if(CollectionUtils.isNotEmpty(tzCustomerInfos))
			return true;
		return false;
	}

}
