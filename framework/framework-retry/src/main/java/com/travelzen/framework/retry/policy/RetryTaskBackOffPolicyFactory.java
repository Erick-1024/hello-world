package com.travelzen.framework.retry.policy;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RetryTaskBackOffPolicyFactory {
	private static final Logger logger = LoggerFactory.getLogger(RetryTaskBackOffPolicyFactory.class);
	private static Map<RetryTaskBackOffPolicy, IRetryTaskBackOffPolicy> policies = new HashMap<>();
	/**
	 * 获取退后策略
	 * @param policyName
	 * @return
	 */
	public synchronized static IRetryTaskBackOffPolicy getPolicy(String policyName){
		try{
			if(StringUtils.isBlank(policyName))
				return null;
			policyName = StringUtils.trimToEmpty(policyName);
			RetryTaskBackOffPolicy policy = RetryTaskBackOffPolicy.valueOf(policyName);
			IRetryTaskBackOffPolicy instance = policies.get(policy);
			if(instance == null){
				switch(policy){
					case fixed: 
						instance = new FixedRetryTaskBackOffPolicy();
						break;
					default:
						return null;
				}
				policies.put(policy, instance);
			}
			return instance;
		}catch(Exception e){
			logger.error("", e);
			return null;
		}
	}
}
