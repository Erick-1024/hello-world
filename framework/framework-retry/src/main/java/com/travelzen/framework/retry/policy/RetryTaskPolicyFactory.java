package com.travelzen.framework.retry.policy;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.travelzen.framework.retry.dict.RetryTaskPolicy;


public class RetryTaskPolicyFactory {
	private static final Logger logger = LoggerFactory.getLogger(RetryTaskPolicyFactory.class);
	private static Map<RetryTaskPolicy, IRetryTaskPolicy> policies = new HashMap<>();
	/**
	 * 获取退后策略
	 * @param policyName
	 * @return
	 */
	public synchronized static IRetryTaskPolicy getPolicy(String policyName){
		try{
			if(StringUtils.isBlank(policyName))
				return null;
			policyName = StringUtils.trimToEmpty(policyName);
			RetryTaskPolicy policy = RetryTaskPolicy.valueOf(policyName);
			IRetryTaskPolicy instance = policies.get(policy);
			if(instance == null){
				switch(policy){
					case simple: 
						instance = new SimpleRetryTaskPolicy();
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
