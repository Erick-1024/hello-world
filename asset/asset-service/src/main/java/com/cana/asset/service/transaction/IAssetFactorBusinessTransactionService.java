package com.cana.asset.service.transaction;

import java.util.Set;

import com.cana.vbam.common.asset.dto.FactorBusinessDTO;
import com.cana.vbam.common.asset.enums.LoanState;
import com.cana.vbam.common.member.vo.UserVo;

public interface IAssetFactorBusinessTransactionService {
	
	/**
	 * 保存保理业务信息
	 * @param factorBusinessDTO
	 */
	public String saveFactorBusinessInfo(FactorBusinessDTO factorBusinessDTO);
	
	/**
	 * 删除业务合同及相关信息
	 * @param id
	 * @param userVo
	 */
	public void deleteFactorBusiness(String id, UserVo userVo);

	/**
	 * 查询保理业务信息(通过业务合同号)
	 * @param businessContractNo
	 * @return
	 */
	public FactorBusinessDTO queryFactorBusinessInfoByBusinessContractNo(String businessContractNo, String factorId);
	
	/**
	 * 批量更新放款状态
	 * @param contractNoSet
	 * @param loanState
	 */
	public void updateLoanState(Set<String> contractNoSet, LoanState loanState);
}

