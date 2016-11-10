package com.cana.asset.service;

import java.util.List;

import com.cana.vbam.common.asset.loan.dto.AssetLoanInfoExcelDTO;
import com.cana.vbam.common.asset.loan.dto.AssetLoanPlanExcelDTO;
import com.cana.vbam.common.dto.ListResult;

/**
 * @author hu
 *
 */
public interface IAssetLoanImportService {

	/**
	 * 放款信息导入暂存redis
	 * @param loanExcelList
	 * @param operatorId
	 * @param rediskey
	 */
	public void importLoanInfoExcel2Redis(List<AssetLoanInfoExcelDTO> loanExcelList, String operatorId, String rediskey);
	
	/**
	 * 从redis获取放款
	 * @param redisKey
	 * @param operatorId
	 * @return
	 */
	public ListResult<AssetLoanInfoExcelDTO> getLoanInfoFromRedis(String redisKey, String operatorId, boolean passed, int page, int pageSize);

	/**
	 * 将导入的放款保存数据库
	 * @param operatorId
	 * @param rediskey
	 */
	public void importLoanInfoExcel2DB(String operatorId, String rediskey);
	
	/**
	 * 还款计划导入暂存redis
	 * @param loanExcelList
	 * @param operatorId
	 * @param rediskey
	 */
	public void importLoanPlanExcel2Redis(List<AssetLoanPlanExcelDTO> loanExcelList, String operatorId, String rediskey);
	
	/**
	 * 从redis获取放款
	 * @param redisKey
	 * @param operatorId
	 * @return
	 */
	public ListResult<AssetLoanPlanExcelDTO> getLoanPlanFromRedis(String redisKey, String operatorId, boolean passed, int page, int pageSize);

	/**
	 * 将导入的放款保存数据库
	 * @param operatorId
	 * @param rediskey
	 */
	public void importLoanPlanExcel2DB(String operatorId, String rediskey);
}
