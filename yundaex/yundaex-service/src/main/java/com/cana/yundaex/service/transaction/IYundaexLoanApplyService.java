package com.cana.yundaex.service.transaction;

import com.cana.vbam.common.dto.PageResult;
import com.cana.vbam.common.member.dto.user.UserSessionDTO;
import com.cana.vbam.common.yundaex.dto.loanApply.YundaexLoanApplyQueryDTO;
import com.cana.vbam.common.yundaex.dto.loanInfo.YundaexLoanFlowListDTO;
import com.cana.vbam.common.yundaex.dto.loanInfo.YundaexLoanFlowQueryDTO;
import com.cana.yundaex.common.dto.YundaexLoanInfoListRequest;
import com.cana.yundaex.common.dto.YundaexLoanInfoListResponse;

/**
 * @author guguanggong
 *
 */
public interface IYundaexLoanApplyService {

	/**
	 * 放款申请
	 * 
	 * @param ckeckDTO
	 * @throws Exception
	 */
	public void creditLoanApply(YundaexLoanApplyQueryDTO loanApplyDTO, UserSessionDTO userSessionDTO) throws Exception;

	/**
	 * 转账实体卡成功，完成放款信息、还款计划等
	 * @throws Exception 
	 * 
	 */
	public void executeLoanTask();

	/**
	 * 获取申请用款流水列表
	 * @param queryDTO
	 * @return 
	 */
	public PageResult<YundaexLoanFlowListDTO> queryCreditFlowList(YundaexLoanFlowQueryDTO loanFlowQueryDTO, String memberId);

	/**
	 * 韵达 放款信息查询接口
	 * @param yundaexLoanInfoListRequest
	 * @return
	 * @throws Exception 
	 */
	public YundaexLoanInfoListResponse getYundaexLoanInfoList(YundaexLoanInfoListRequest yundaexLoanInfoListRequest) throws Exception;
	
	/**
	 * 检验额度
	 * @param memberId
	 * @param fee
	 */
	public void checkLimitBalanceEnough(String memberId, Long fee);
}
