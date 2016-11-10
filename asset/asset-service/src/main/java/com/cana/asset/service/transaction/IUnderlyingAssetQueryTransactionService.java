package com.cana.asset.service.transaction;

import java.util.List;

import com.cana.vbam.common.asset.enums.UnderlyingAssetPoolStatus;
import com.cana.vbam.common.asset.loan.dto.LoanPaidDTO;
import com.cana.vbam.common.asset.loan.dto.LoanPlanDTO;
import com.cana.vbam.common.asset.underlyingasset.dto.UnderlyingAssetDTO;
import com.cana.vbam.common.asset.underlyingasset.dto.UnderlyingAssetQueryDTO;
import com.cana.vbam.common.asset.underlyingasset.enums.RequestDirection;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.member.vo.UserVo;

/**
 * 基础资产查询类。
 * @author XuMeng
 *
 */
public interface IUnderlyingAssetQueryTransactionService {

	/**
	 * 为基础资产模块提供的列表查询接口
	 * <p> 搜索条件为放款编号、业务合同号、借款人名称、放款日、到期日。
	 * <p> 数据权限：保理商仅可以查看是自己创建的基础资产，而非保理商，
	 * 需要先获取自己能访问的专项计划列表，通过专项计划编号列表来查询所有关联这些专项计划编号的基础资产。
	 * <p> 对返回结果进行补全，在基础资产中分有手工录入的和保理业务关联过来的基础资产，
	 * 对于手工录入的基础资产，不用和其他数据表做关联，并允许修改操作，
	 * 而对于保理业务关联过来的基础资产，其几乎所有数据需要从保理业务那边实时查询，这是需要注意避免N＋1问题。
	 */
	public ListResult<UnderlyingAssetDTO> queryUnderlyingAssets(UserVo userVo, UnderlyingAssetQueryDTO queryDTO, RequestDirection requestDirection);
	
	/**
	 * 全量查询备入池的基础资产 
	 */
	public List<UnderlyingAssetDTO> queryUnderlyingAssetData(UnderlyingAssetQueryDTO queryDTO);

	/**
	 * 基础资产详情接口
	 * 同基础资产列表查询接口
	 */
	public UnderlyingAssetDTO getUnderlyingAssetDetail(UserVo userVo, String underlyingAssetId);

	/**
	 * 基础资产详情接口
	 * 同基础资产列表查询接口
	 */
	public List<UnderlyingAssetDTO> getUnderlyingAssetDetailList(List<String> underlyingAssetIdList, UnderlyingAssetPoolStatus status);

	/**
	 * 查询基础资产的还款计划列表
	 */
	public List<LoanPlanDTO> getLoanPlans(UserVo userVo, String underlyingAssetId);

	/**
	 * 查询基础资产的还款明细
	 */
	public ListResult<LoanPaidDTO> getLoanPaids(UserVo userVo, String underlyingAssetId, int page, int pageSize);
}
