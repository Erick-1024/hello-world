package com.cana.credit.service;

import java.util.List;
import java.util.Map;

import com.cana.vbam.common.credit.dto.limit.CreditUsedLimitInfo;
import com.cana.vbam.common.credit.dto.limit.CreditUsedLimitInfoQueryCriteria;
import com.cana.vbam.common.credit.dto.limit.CustomerLimitListQueryDTO;
import com.cana.vbam.common.credit.dto.limit.CustomerLimitListResponseDTO;
import com.cana.vbam.common.credit.dto.limit.QueryCreditLimitDTO;
import com.cana.vbam.common.credit.dto.limit.QueryCreditLimitResponse;
import com.travelzen.framework.common.PageList;

public interface ICreditLimitService {

	/**
	 * 计算已通过人工审核的客户申请授信额度
	 */
	public void calculateApplyCreditLimit();
	
	public PageList<CustomerLimitListResponseDTO> getCustomerLimitList(CustomerLimitListQueryDTO queryDTO);
	
	/**
     * 授信额度余额查询
     */
    public QueryCreditLimitResponse queryCreditLimitBalance(QueryCreditLimitDTO queryDTO);
    
	/**
	 * 查询授信余额的情况
	 * @param creditUsedLimitInfoQueryCriteria
	 * @return
	 */
	public List<CreditUsedLimitInfo> queryCreditUsedLimitInfo(CreditUsedLimitInfoQueryCriteria creditUsedLimitInfoQueryCriteria);
	
	public int queryCountCreditUsedLimitInfo(CreditUsedLimitInfoQueryCriteria creditUsedLimitInfoQueryCriteria);
    
	/**
	 * 从额度表中查询客户的外部平台ID和CNAN平台ID号
	 * @param projectId
	 * @param companyName
	 * @param outCustomerName
	 * @return Map&lt;outCustomerId, memberId>
	 */
	public Map<String, String> queryOutCustomerIdAndMemberId(String projectId, String companyName, String outCustomerName);
	
	/** 
	 * 从授信表中查询对应的外部客户名称
	 * @param productId
	 * @param memberId
	 * @param outCustomerId
	 * @return
	 */
	public String queryOutCustomerName(String productId, String memberId, String outCustomerId);
	
}
