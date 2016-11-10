package com.cana.credit.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cana.credit.dao.mapper.gen.CustomerApplyMapper;
import com.cana.credit.dao.po.CustomerApply;
import com.cana.credit.dao.po.CustomerApplyExample;
import com.cana.credit.service.IAccessRulesService;
import com.cana.credit.service.IWhiteListService;
import com.cana.credit.service.transaction.IAccessRulesTransationService;
import com.cana.credit.service.utils.SendResultMessageUtil;
import com.cana.flight.finance.dao.mapper.gen.TzCustomerInfoMapper;
import com.cana.flight.finance.dataaccess.travelzen.api.ITravelzenDataApi;
import com.cana.flight.finance.service.IRepaymentService;
import com.cana.vbam.common.credit.enums.AccessAutomaticState;

@Service
public class AccessRulesServiceImpl implements IAccessRulesService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	private CustomerApplyMapper customerApplyMapper;
	
	@Resource 
	private IWhiteListService whiteListService;
	
	@Resource
	private TzCustomerInfoMapper tzCustomerInfoMapper;

	@Resource
	private IRepaymentService repaymentServiceImpl;
	
	@Resource
	private ITravelzenDataApi travelzenDataApiImpl;
	
	@Resource
	private IAccessRulesTransationService accessRulesTransationServiceImpl;
	
	@Override
	public void checkApplysByAccessRules() {
		CustomerApplyExample example = new CustomerApplyExample();
		example.createCriteria().andAccessAutomaticStateEqualTo(AccessAutomaticState.WAIT.name());
		List<CustomerApply> customerApplys = customerApplyMapper.selectByExample(example);
		int accessCount = 0;
		for(CustomerApply apply : customerApplys){
			boolean isSuccess = accessRulesTransationServiceImpl.checkApplyByAccessRules(apply);
			if(isSuccess)
				accessCount++;
			else{
				//自动准入不通过
				SendResultMessageUtil.sendAutomaticMessage(apply);
			}
		}
		logger.info("这次准入规则一共处理了{}个申请，通过了{}个申请",customerApplys.size(),accessCount);
	}
}
