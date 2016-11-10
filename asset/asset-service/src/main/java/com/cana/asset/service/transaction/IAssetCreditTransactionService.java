package com.cana.asset.service.transaction;

import com.cana.asset.dao.po.Credit;
import com.cana.vbam.common.asset.dto.CreditCheckModifyResultDTO;
import com.cana.vbam.common.asset.dto.CreditRequestDTO;
import com.cana.vbam.common.member.vo.UserVo;

/**
 * @author hu
 *
 */
public interface IAssetCreditTransactionService {

	/**
	 * 根据业务合同号锁表,不存在返回null
	 * @param contractNo
	 * @return
	 */
	public Credit lockByBussinessContractNo(String contractNo);
	
	/**
	 * 恢复额度
	 * @param creditId
	 * @param usedLimit
	 * @param operatorId
	 */
	public void recoveryLimit(String creditId, long usedLimit, String operatorId);
	
	/**
	 * 可用额度检查
	 * @param creditId
	 * @param limit
	 * @param operatorId
	 */
	public boolean checkAvailableLimit(String creditId, long uselimit);
	
	/**
	 * 使用额度
	 * @param creditId
	 * @param limit
	 * @param operatorId
	 */
	public void useLimit(String creditId, long limit, String operatorId);
	
	/**
	 * 申请额度
	 * @param request
	 */
	public void applyCredit(CreditRequestDTO request, UserVo userVo);
	
	/**
	 * 校验额度是否可以修改
	 * @param request
	 * @return
	 */
	public CreditCheckModifyResultDTO checkCreditForModify(String creditId);
	
	/**
	 * 修改额度
	 * @param request
	 */
	public void modifyCredit(CreditRequestDTO request, UserVo userVo);
	
	/**
	 * 冻结
	 * @param creditId
	 */
	public void freezeCredit(String creditId, UserVo userVo);
	
	/**
	 * 解冻
	 * @param creditId
	 */
	public void unfreezeCredit(String creditId, UserVo userVo);
	
	/**
	 * 取消
	 * @param creditId
	 */
	public void revokeCredit(String creditId, UserVo userVo);
	
	/**
	 * 作废
	 * @param creditId
	 */
	public void cancelCredit(String creditId, UserVo userVo);
	
	/**
	 * 更改过期的额度状态
	 */
	public void updateExpireCreditState(String currentDate);

	/**
	 * 更改生效的额度状态
	 */
	public void updateEffectiveCreditState(String currentDate);
}
