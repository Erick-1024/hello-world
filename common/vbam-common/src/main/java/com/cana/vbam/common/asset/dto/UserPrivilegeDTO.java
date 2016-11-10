package com.cana.vbam.common.asset.dto;

import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;

import com.google.common.collect.Lists;

/**
 * @author hu
 *
 */
public class UserPrivilegeDTO {

	private String factorId;
	
	private Set<String> customerIds;

	public String getFactorId() {
		return factorId;
	}

	public void setFactorId(String factorId) {
		this.factorId = factorId;
	}

	public Set<String> getCustomerIds() {
		return customerIds;
	}
	
	public List<String> getCustomerIdsList(){
		return Lists.newArrayList(customerIds);
	}
	
	public String getCustomerIdsStr(){
		if(CollectionUtils.isNotEmpty(customerIds)){
			String str = "";
			for(String customerId : customerIds){
				if(!"".equals(str))
					str += ",";
				str += customerId;
			}
			return str;
		}
		return null;
	}

	public void setCustomerIds(Set<String> customerIds) {
		this.customerIds = customerIds;
	} 
}
