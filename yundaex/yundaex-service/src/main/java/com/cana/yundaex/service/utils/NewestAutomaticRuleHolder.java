package com.cana.yundaex.service.utils;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.cana.yundaex.dao.mapper.gen.YundaexAuditRuleMapper;
import com.cana.yundaex.dao.po.YundaexAuditRule;
import com.cana.yundaex.dao.po.YundaexAuditRuleExample;

/**
 * 
 * NewestAutomaticRule类
 * 将最新的系统审核规则保存在内存，启动就重新取
 */
@Component
public class NewestAutomaticRuleHolder implements InitializingBean{


	private static final Logger logger = LoggerFactory.getLogger(NewestAutomaticRuleHolder.class);
	
	@Resource
	private YundaexAuditRuleMapper auditRuleMapper;
	public static YundaexAuditRule newestAccessRule = null;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		YundaexAuditRuleExample example = new YundaexAuditRuleExample(); 
		example.setOrderByClause("batch_no desc");
		example.setLimitStart(0);
		example.setLimitEnd(1);
		List<YundaexAuditRule> accessRules = auditRuleMapper.selectByExample(example);
		if (accessRules == null || accessRules.isEmpty()){
			logger.error("准入规则为空");
            newestAccessRule = null;
            return;
		}
		newestAccessRule = accessRules.get(0);
	}
}
