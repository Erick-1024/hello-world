package com.cana.asset.search.handler;

/**
 * 批量更新索引
 * @author renshui
 *
 */
public interface IBatchIndexUpdateHandler {
	
	/**
	 * 准备发起一个新的批次
	 * @throws Exception
	 */
	public void prepare();
	
	/**
	 * 基础资产发生了变更
	 * @param assetId
	 */
	public void underlyingAssetChanged(String assetId);
	
	/**
	 * 客户信息发生了变更
	 * @param customerId
	 */
	public void customerChanged(String customerId);
	
	/**
	 * 还款计划发生了变更
	 * @param planId
	 * @param loanInfoId
	 */
	public void loanPlanChanged(String planId, String loanInfoId);
	
	/**
	 * 放款信息发生了变更
	 * @param loanInfoId
	 */
	public void loanInfoChanged(String loanInfoId);
	
	/**
	 * 授信额度发生了变更
	 * @param businessContractNo
	 */
	public void creditChanged(String businessContractNo);
	
	/**
	 * 记录本批次所有变更后，更新索引
	 */
	public void commit();

}
