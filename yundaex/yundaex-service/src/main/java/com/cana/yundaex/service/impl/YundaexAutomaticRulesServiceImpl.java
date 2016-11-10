package com.cana.yundaex.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cana.yundaex.common.enums.StationInfoType;
import com.cana.yundaex.common.enums.YundaexAuditState;
import com.cana.yundaex.dao.mapper.gen.YundaexCustomerApplyMapper;
import com.cana.yundaex.dao.po.YundaexAuditRule;
import com.cana.yundaex.dao.po.YundaexCustomerApply;
import com.cana.yundaex.dao.po.YundaexCustomerApplyExample;
import com.cana.yundaex.service.IYundaexAutomaticRulesService;
import com.cana.yundaex.service.transaction.IYundaexAutomaticRulesTransactionService;
import com.cana.yundaex.service.utils.NewestAutomaticRuleHolder;

@Service
public class YundaexAutomaticRulesServiceImpl implements IYundaexAutomaticRulesService{

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	private YundaexCustomerApplyMapper ydCustomerApplyMapper ;
	
	@Resource
	private IYundaexAutomaticRulesTransactionService ydAutomaticRulesTransationServiceImpl;
	
	@Override
	public void checkApplysByAutomaticRules() {
		logger.info("进入系统审核");
		YundaexCustomerApplyExample example = new YundaexCustomerApplyExample();
		example.createCriteria().andAccessAutomaticStateEqualTo(YundaexAuditState.WAIT.name()).andWhetherStationInfoEqualTo(StationInfoType.Y.name());
		List<YundaexCustomerApply> ydCustomerApplys = ydCustomerApplyMapper.selectByExample(example);
		YundaexAuditRule newestAutomaticRule = NewestAutomaticRuleHolder.newestAccessRule;
		
		int ydAccessCount = 0;
		for(YundaexCustomerApply apply : ydCustomerApplys){
			boolean isSuccess = ydAutomaticRulesTransationServiceImpl.checkApplyByAutomaticRules(apply, newestAutomaticRule);
			if(isSuccess)
				ydAccessCount++;
		}
		logger.info("这次准入规则一共处理了{}个申请，通过了{}个申请",ydCustomerApplys.size(),ydAccessCount);
	}

	@Override
	public void calculateCustomerGrade() {
		logger.info("进入系统评级");
		YundaexCustomerApplyExample example = new YundaexCustomerApplyExample();
		example.createCriteria().andGradeStateEqualTo(YundaexAuditState.WAIT.name());
		List<YundaexCustomerApply> ydCustomerApplys = ydCustomerApplyMapper.selectByExample(example);
		int ydGradeCount = 0;
		for(YundaexCustomerApply apply : ydCustomerApplys){
			boolean isSuccess = false;
			try {
				isSuccess = ydAutomaticRulesTransationServiceImpl.gradeCustomerLevel(apply);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("评级模型出错",e);
			}finally {
				if(isSuccess)
					ydGradeCount++;
				logger.info("这次评级模型一共处理了{}个申请，通过了{}个申请",ydCustomerApplys.size(),ydGradeCount);
			}
		}
	}

}
