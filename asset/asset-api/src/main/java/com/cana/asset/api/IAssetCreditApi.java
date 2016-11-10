package com.cana.asset.api;

import com.cana.vbam.common.asset.dto.CreditAuditDTO;
import com.cana.vbam.common.asset.dto.CreditAuditQueryCriteria;
import com.cana.vbam.common.asset.dto.CreditCheckModifyResultDTO;
import com.cana.vbam.common.asset.dto.CreditDTO;
import com.cana.vbam.common.asset.dto.CreditQueryCriteria;
import com.cana.vbam.common.asset.dto.CreditRequestDTO;
import com.cana.vbam.common.asset.dto.CustomerCreditDTO;
import com.cana.vbam.common.asset.dto.CustomerCreditQueryCriteria;
import com.cana.vbam.common.dto.ListResult;

/**
 * @author hu
 *
 */
public interface IAssetCreditApi {

	/**
	 * 授信客户列表查询客户信息
	 * @param queryCriteria
	 * @return
	 */
	public ListResult<CustomerCreditDTO> searchCreditCustomerByCondition(CustomerCreditQueryCriteria queryCriteria);
	
	/**
	 * 获取所有客户，并根据额度信息进行排序
	 * @param crieria
	 * @return
	 */
	public ListResult<CustomerCreditDTO> getCustomers(CustomerCreditQueryCriteria crieria);
	
	/**
	 * 获取总额度
	 * @param customerId
	 * @return
	 */
	public Long[] getCustomerLimit(String customerId);
	
	/**
	 * 获取可用额度
	 * @param customerId
	 * @return
	 */
	public Long getCustomerAvailableLimit(String customerId);
	
	/**
	 * 根据客户获取授信
	 * @param customerId
	 * @return
	 */
	public ListResult<CreditDTO> getCreditsByCustomerId(CreditQueryCriteria querycriteria);
	
	/**
	 * 获取详情
	 * @param creditId
	 * @return
	 */
	public CreditDTO getCreditById(String creditId, String userId);
	
	/**
	 * 根据业务合同号获取额度
	 * @param businessContractNo
	 * @return
	 */
	public CreditDTO getCreditByBusinessContractNo(String businessContractNo, String customerId, String userId);
	
	/**
	 * 校验业务合同号是否存在
	 * @param businessContractNo
	 * @param creditId
	 * @return
	 */
	public boolean checkBusinessContactNoExist(String businessContractNo, String creditId);
	/**
	 * 申请
	 * @param request
	 */
	public void applyCredit(CreditRequestDTO request);
	
	/**
	 * 校验额度是否可以修改
	 * @param creditId
	 * @return
	 */
	public CreditCheckModifyResultDTO checkCreditForModify(String creditId);
	
	/**
	 * 修改
	 * @param request
	 */
	public void modifyCredit(CreditRequestDTO request);
	
	/**
	 * 冻结
	 * @param creditId
	 */
	public void freezeCredit(String creditId, String userId);
	
	/**
	 * 解冻
	 * @param creditId
	 */
	public void unfreezeCredit(String creditId, String userId);
	
	/**
	 * 撤销
	 * @param creditId
	 */
	public void revokeCredit(String creditId, String userId);
	
	/**
	 * 作废
	 * @param creditId
	 */
	public void cancelCredit(String creditId, String userId);
	
	/**
	 * 根据条件搜索授信操作日志
	 * @param criteria
	 * @return
	 */
	public ListResult<CreditAuditDTO> searchCreditAuditByCondition(CreditAuditQueryCriteria criteria);
}
