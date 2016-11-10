package com.cana.asset.service.transaction;

import java.util.List;

import com.cana.vbam.common.asset.underlyingasset.dto.EditUnderlyingAssetRequest;
import com.cana.vbam.common.asset.underlyingasset.dto.EnterAssetPoolRequest;
import com.cana.vbam.common.asset.underlyingasset.dto.QueryFactorLoanForUnderlyingAssetRequest;
import com.cana.vbam.common.asset.underlyingasset.dto.QueryFactorLoanForUnderlyingAssetResponse;
import com.cana.vbam.common.asset.underlyingasset.dto.UnderlyingAssetUpdateDTO;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.member.vo.UserVo;

/**
 * 基础资产业务类。
 *
 * <p> 基础资产的数据来源有两个，一个是通过excel导入的基础资产，这部分数据与原保理业务无任何关联。
 * 另一个是业务人员选择的保理放款作为基础资产存在的，这部分基础资产的主要数据来源为保理业务模块，而只在基础资产表
 * 中冗余一些必要字段，比如放款编号、业务合同号、借款人名称、放款日、到期日，
 * 其他不为空的字段为资产池状态、基础资产类型、创建人，剩余字段需要在查询时，实时到业务模块中去取
 *
 * @author XuMeng
 *
 */
public interface IUnderlyingAssetTransactionService {

	/**
	 * 保理放款转为基础资产接口
	 */
	public void createUnderlyingAssetByFactorLoan(UserVo userVo, List<String> loanInfoIds);

	/**
	 * 查询可以转为基础资产的保理放款列表
	 */
	public ListResult<QueryFactorLoanForUnderlyingAssetResponse> queryFactorLoanForUnderlyingAsset(
			UserVo userVo, QueryFactorLoanForUnderlyingAssetRequest request);

	/**
	 * 基础资产删除接口，该基础资产必需处于未入池状态，由基础资产模块的保理商进行操作
	 */
	public void deleteUnderlyingAsset(UserVo userVo, String underlyingAssetId);

	/**
	 * 基础资产绑定专项计划接口，即入备选池接口，基础资产必需处于未入池状态，方可入备选池
	 * <p> 此接口为基础资产模块提供，由对基础资产拥有权限的保理商进行操作，并只能将基础资产入到自己能访问的专享计划池中
	 */
	public void bindSpecialProgram(UserVo userVo, EnterAssetPoolRequest request);

	/**
	 * 基础资产解绑专项计划接口，即基础资产模块出备选池接口，基础资产必需处于备选池状态
	 * <p> 此接口为基础资产模块提供，由对基础资产拥有权限的保理商进行操作，解除基础资产和专享计划的绑定关系
	 */
	public void unbindSpecialProgram(UserVo userVo, String underlyingAssetId);

	/**
	 * 检查该专项计划的所有基础资产是否允许出备选池
	 * @param programId 专项计划ID
	 */
	public boolean checkUnbindForDeleteProgram(String programId);

	/**
	 * 对该专项计划的所有基础资产执行出备选池操作
	 * @param programId 专项计划ID
	 */
	public void checkAndUnbindForDeleteProgram(UserVo userVo, String programId);

	/**
	 * 入池接口，基础资产的入池必需处于备选池时，方可入池
	 * <p> 此接口为资产池模块提供，由券商进行操作，需检查券商是否对该项计划拥有操作权限
	 */
	public void enterAssetPool(UserVo userVo, List<String> underlyingAssetIds);

	/**
	 * 出池，将处于已入池的基础资产进行出池操作，并彻底删除此基础资产
	 * <p> 此接口为资产池模块提供，由券商进行操作，需检查券商是否对该项计划拥有操作权限
	 */
	public void outAssetPoolAndDelete(UserVo userVo, String underlyingAssetId);

	/**
	 * 资产池模块调用的待入池接口，此时的基础资产必需处于已入池状态，将资产池状态修改为备选池状态，保留和专项计划的绑定关系
	 * <p> 此接口为资产池模块提供，由券商进行操作，需检查券商是否对该项计划拥有操作权限
	 */
	public void outAssetPoolAndKeepBind(UserVo userVo, String underlyingAssetId);

	/**
	 * 赎回，将处于已入池的基础资产进行赎回操作，并彻底删除此基础资产
	 * <p> 实现同出池接口，仅仅记录日志不同
	 */
	public void redeemAssetPool(UserVo userVo, String underlyingAssetId);

	/**
	 * excel录入基础资产校验接口
	 */
	public void checkImportUnderlyingAssetRequest(UserVo userVo, EditUnderlyingAssetRequest request);

	/**
	 *  excel录入基础资产接口
	 */
	public void importUnderlyingAsset(UserVo userVo, List<EditUnderlyingAssetRequest> requests);

	/**
	 * 基础资产修改接口，只有excel导入的方可修改
	 */
	public void updateUnderlyingAsset(UserVo userVo, EditUnderlyingAssetRequest request);
	
	/**
	 * 更新基础资产标志位
	 * @param updateDTO
	 */
	public void updateUnderlyingAssetInCondition(UnderlyingAssetUpdateDTO updateDTO);
}
