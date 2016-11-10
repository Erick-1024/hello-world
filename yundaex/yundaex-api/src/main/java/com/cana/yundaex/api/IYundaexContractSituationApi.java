package com.cana.yundaex.api;

import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.dto.PageResult;
import com.cana.yundaex.common.dto.contract.ContractSituationQueryCriteria;
import com.cana.yundaex.common.dto.contract.ContractSituationRequestDTO;
import com.cana.yundaex.common.dto.contract.ContractSituationResultDTO;

/**
 * @author hu
 *
 */
public interface IYundaexContractSituationApi {

	/**
	 * 创建或更新合同情况
	 * @param requstDTO
	 */
	public void createOrUpdateContractSituation(ContractSituationRequestDTO requstDTO);
	
	/**
	 * 更新合同情况
	 * @param requestDTO
	 */
	public PageResult<ContractSituationResultDTO> findContractSituationByCondition(ContractSituationQueryCriteria queryCriteria);

	/**
	 * 重发合同
	 * @param id
	 */
	public ListResult<String> resendContract(String id);
	
	/**
	 * 获取合同详情
	 * @param id
	 * @return
	 */
	public ContractSituationResultDTO getContractSituationById(String id);
	
	/**
	 * 个人合同签署状态更新
	 * @param personalId
	 * @param customerId
	 */
	public void signContract(String personalId, String customerId);
	
	/**
	 * 生成融资合同编号
	 * @param prefix
	 * @return
	 */
	public String generateFinanceContractSerialNumber(String prefix);
	
	/**
	 * 发送融资合同签署完成信息
	 * @param customerId
	 */
	public void sendFinanceContractSignedMessage(String customerId);
	
	/**
	 * 单笔融资合同
	 * @return
	 */
	public String generateSingleLoanNumber();
}
