/**
 * 
 */
package com.cana.asset.api;

import java.util.List;

import com.cana.vbam.common.asset.loan.dto.AssetLoanDTO;
import com.cana.vbam.common.asset.loan.dto.AssetLoanListRequest;
import com.cana.vbam.common.asset.loan.dto.AssetLoanPaidListRequest;
import com.cana.vbam.common.asset.loan.dto.AssetLoanPlanExcelDTO;
import com.cana.vbam.common.asset.loan.dto.AssetPaidPlanRequest;
import com.cana.vbam.common.asset.loan.dto.AssetLoanInfoExcelDTO;
import com.cana.vbam.common.asset.loan.dto.EditAssetLoanRequest;
import com.cana.vbam.common.asset.loan.dto.GenerateLoanPlanRequest;
import com.cana.vbam.common.asset.loan.dto.LoanPaidDTO;
import com.cana.vbam.common.asset.loan.dto.LoanPlanDTO;
import com.cana.vbam.common.dto.ListResult;
import com.travelzen.framework.core.exception.WebException;

/**
 * 
 * @author XuMeng
 *
 */
public interface IAssetLoanInfoApi {

	/**
	 * 删除某一笔没有发生还款的放款信息
	 * 
	 * 删除内容包括：
	 * 放款、还款计划、费用、应收帐款、恢复额度
	 * @param userId 当前登录用户
	 * @param loanInfoId 放款ID
	 */
	public void deleteLoanById(String userId, String loanInfoId);

	/**
	 * 自动生成还款计划
	 * @return 还款计划列表，返回值中保证有值的字段是：
	 * 融资余额、起息日、结息日、还款日、应还本金、应还利息、应还逾期、应还总金额、英文结清状态
	 */
	public List<LoanPlanDTO> generateLoanPlanDTO(GenerateLoanPlanRequest request) throws Exception;

	/**
	 * 创建放款
	 * @param userId 创建员工ID，仅支持保理商客户员工
	 */
	public String createAssetLoan(String userId, EditAssetLoanRequest request);

	/**
	 * 生成放款编号，新增放款时不再需要生成放款编号，页面直接显示自动生成字样即可
	 * 
	 * <p>新增放款时忽略loanInfoId字段
	 * @deprecated
	 */
	public String generateAssetLoanInfoId(String contractNo);

	/**
	 * 修改放款
	 * @param userId 修改员工ID，仅支持保理商客户员工
	 */
	public String updateAssetLoan(String userId, EditAssetLoanRequest request);
	
	/**
	 * 冲销一笔放款中的第一个未结清状态的还款计划
	 * 冲销后已还金额不能大于应还金额
	 * @param userId 当前还款冲销的员工，仅支持该放款的保理客户员工
	 * @param request 还款冲销请求对象
	 * @return 是否冲销成功
	 * @throws WebException
	 */
	public boolean paidAssetLoanPlan(String userId, AssetPaidPlanRequest request);
	
	/**
	 * 查询放款列表
	 * @param assetLoanListRequest
	 * @return
	 */
	public ListResult<AssetLoanDTO> getLoanList(AssetLoanListRequest assetLoanListRequest);
	
	/**
	 * 查询放款详情
	 * @param id 放款ID
	 * @param userId 用户ID
	 * @return
	 */
	public AssetLoanDTO getLoanDetail(String id, String userId);
	
	/**
	 * 查询历史还款明细列表
	 * @param assetLoanPaidListRequest
	 * @return
	 */
	public ListResult<LoanPaidDTO> getLoanPaidList(AssetLoanPaidListRequest assetLoanPaidListRequest);
	
	/**
	 * 导入放款2redis
	 * @param loanExcelList
	 * @param operatorId
	 * @param rediskey
	 */
	public void importExcelLoanInfo2Redis(List<AssetLoanInfoExcelDTO> loanExcelList, String operatorId, String rediskey);
	
	/**
	 * 从redis取放款
	 * @param redisKey
	 * @param operatorId
	 * @param passed
	 * @param page
	 * @param pageSize
	 */
	public ListResult<AssetLoanInfoExcelDTO> getLoanInfoFromRedis(String redisKey, String operatorId, boolean passed,
			int page, int pageSize);
	
	/**
	 * 导入放款保存数据库
	 * @param operatorId
	 * @param rediskey
	 */
	public void importLoanInfoExcel2DB(String operatorId, String rediskey);
	
	/**
	 * 导入还款2redis
	 * @param loanExcelList
	 * @param operatorId
	 * @param rediskey
	 */
	public void importExcelLoanPlan2Redis(List<AssetLoanPlanExcelDTO> loanPlanExcelList, String operatorId, String rediskey);
	
	/**
	 * 从redis取还款
	 * @param redisKey
	 * @param operatorId
	 * @param passed
	 * @param page
	 * @param pageSize
	 */
	public ListResult<AssetLoanPlanExcelDTO> getLoanPlanFromRedis(String redisKey, String operatorId, boolean passed,
			int page, int pageSize);
	
	/**
	 * 导入还款计划保存数据库
	 * @param operatorId
	 * @param rediskey
	 */
	public void importLoanPlanExcel2DB(String operatorId, String rediskey);
	
	/**
	 * 获取放款rediskey
	 * @return
	 */
	public String getLoanInfoRediskey();
	
	/**
	 * 获取还款rediskey
	 * @return
	 */
	public String getLoanPlanRediskey();
}
