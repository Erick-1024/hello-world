package com.cana.credit.service.utils;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.cana.credit.dao.mapper.gen.AccessRuleMapper;
import com.cana.credit.dao.po.AccessRule;
import com.cana.credit.dao.po.AccessRuleExample;
import com.cana.vbam.common.credit.enums.AccessRuleFitObject;

/**
 * tangyihong
 * newestAccessRule类
 * 将最新的准入规则保存在内存，启动就重新取
 */
@Component
public class NewestAccessRuleHolder implements InitializingBean{


	private static final Logger logger = LoggerFactory.getLogger(NewestAccessRuleHolder.class);
	
	@Resource
	private AccessRuleMapper accessRuleMapper;
	
	public static AccessRule whiteCustomerNewestAccessRule = null;
	
	public static AccessRule nonWhiteCustomerNewestAccessRule = null;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		List<AccessRule> whiteCustomerAccessRules = getAccessRule(AccessRuleFitObject.WHITE_CUSTOMER);
		if (whiteCustomerAccessRules == null || whiteCustomerAccessRules.isEmpty()){
			logger.error("白名单准入规则为空");
			whiteCustomerNewestAccessRule = null;
		}
		else
			whiteCustomerNewestAccessRule = whiteCustomerAccessRules.get(0);
		
		List<AccessRule> nonWhiteCustomerAccessRules = getAccessRule(AccessRuleFitObject.NON_WHITE_CUSTOMER);
		if (nonWhiteCustomerAccessRules == null || nonWhiteCustomerAccessRules.isEmpty()){
			logger.error("非白名单准入规则为空");
			nonWhiteCustomerNewestAccessRule = null;
		}
		else
			nonWhiteCustomerNewestAccessRule = nonWhiteCustomerAccessRules.get(0);
	}
	
	private List<AccessRule> getAccessRule(AccessRuleFitObject fitObject){
		AccessRuleExample example = new AccessRuleExample(); 
		example.setOrderByClause("batch_no desc");
		example.setLimitStart(0);
		example.setLimitEnd(1);
		example.createCriteria().andFitObjectEqualTo(fitObject.name());
		return accessRuleMapper.selectByExample(example);
	}
}
