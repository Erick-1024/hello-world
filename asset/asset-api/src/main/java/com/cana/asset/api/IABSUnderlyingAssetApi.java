package com.cana.asset.api;

import java.util.List;

import com.cana.vbam.common.asset.underlyingasset.dto.ConvertToUnderlyingAssetRequest;
import com.cana.vbam.common.asset.underlyingasset.dto.EditUnderlyingAssetRequest;
import com.cana.vbam.common.asset.underlyingasset.dto.EnterAssetPoolRequest;
import com.cana.vbam.common.asset.underlyingasset.dto.QueryFactorLoanForUnderlyingAssetRequest;
import com.cana.vbam.common.asset.underlyingasset.dto.QueryFactorLoanForUnderlyingAssetResponse;
import com.cana.vbam.common.asset.underlyingasset.dto.UnderlyingAssetDTO;
import com.cana.vbam.common.asset.underlyingasset.dto.UnderlyingAssetExcelDTO;
import com.cana.vbam.common.asset.underlyingasset.dto.UnderlyingAssetQueryDTO;
import com.cana.vbam.common.asset.underlyingasset.enums.RequestDirection;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.member.vo.UserVo;

/**
 * 基础资产接口
 * @author XuMeng
 *
 */
public interface IABSUnderlyingAssetApi {

	/**
	 * 保理放款转为基础资产接口
	 */
	public void createUnderlyingAssetByFactorLoan(String userId, ConvertToUnderlyingAssetRequest request);

	/**
	 * 查询可以转为基础资产的保理放款列表
	 */
	public ListResult<QueryFactorLoanForUnderlyingAssetResponse> queryFactorLoanForUnderlyingAsset(
			String userId, QueryFactorLoanForUnderlyingAssetRequest request);

	/**
	 * 基础资产删除接口，该基础资产必需处于未入池状态，由基础资产模块的保理商进行操作
	 */
	public void deleteUnderlyingAsset(String userId, String underlyingAssetId);

	/**
	 * 基础资产绑定专项计划接口，即入备选池接口，基础资产必需处于未入池状态，方可入备选池
	 * <p> 此接口为基础资产模块提供，由对基础资产拥有权限的保理商进行操作，并只能将基础资产入到自己能访问的专享计划池中
	 */
	public void bindSpecialProgram(String userId, EnterAssetPoolRequest request);

	/**
	 * 基础资产解绑专项计划接口，即基础资产模块出备选池接口，基础资产必需处于备选池状态
	 * <p> 此接口为基础资产模块提供，由对基础资产拥有权限的保理商进行操作，解除基础资产和专享计划的绑定关系
	 */
	public void unbindSpecialProgram(String userId, String underlyingAssetId);

	/**
	 * 入池接口，基础资产的入池必需处于备选池时，方可入池
	 * <p> 此接口为资产池模块提供，由券商进行操作，需检查券商是否对该项计划拥有操作权限
	 */
	public void enterAssetPool(String userId, List<String> underlyingAssetIds);

	/**
	 * 出池，将处于已入池的基础资产进行出池操作，并彻底删除此基础资产
	 * <p> 此接口为资产池模块提供，由券商进行操作，需检查券商是否对该项计划拥有操作权限
	 */
	public void outAssetPoolAndDelete(String userId, String underlyingAssetId);

	/**
	 * 资产池模块调用的待入池接口，此时的基础资产必需处于已入池状态，将资产池状态修改为备选池状态，保留和专项计划的绑定关系
	 * <p> 此接口为资产池模块提供，由券商进行操作，需检查券商是否对该项计划拥有操作权限
	 */
	public void outAssetPoolAndKeepBind(String userId, String underlyingAssetId);

	/**
	 * 赎回，将处于已入池的基础资产进行赎回操作，并彻底删除此基础资产
	 * <p> 实现同出池接口，仅仅记录日志不同
	 */
	public void redeemAssetPool(String userId, String underlyingAssetId);

	/**
	 * 基础资产修改接口，只有excel导入的方可修改
	 */
	public void updateUnderlyingAsset(String userId, EditUnderlyingAssetRequest request);

	/**
	 * excel导入时生成rediskey
	 */
	public String generateUnderlyingAssetRedisKey();

	/**
	 * 导入暂存redis
	 */
	public void importUnderlyingAssetExcel2Redis(List<UnderlyingAssetExcelDTO> loanExcelList, String operatorId, String rediskey);
	
	/**
	 * 从redis获取
	 */
	public ListResult<UnderlyingAssetExcelDTO> getUnderlyingAssetFromRedis(String redisKey, String operatorId, boolean passed, int page, int pageSize);

	/**
	 * 导入数据库
	 */
	public void importUnderlyingAssetExcel2DB(String operatorId, String rediskey);
	
	/**
	 * 查询基础资产列表
	 */
	public ListResult<UnderlyingAssetDTO> queryUnderlyingAssets(UserVo userVo, UnderlyingAssetQueryDTO queryDTO, RequestDirection requestDirection);
	
	/**
	 * 根据基础资产Id查询详情
	 */
	public UnderlyingAssetDTO getUnderlyingAssetDetail(UserVo userVo, String underlyingAssetId);

}
