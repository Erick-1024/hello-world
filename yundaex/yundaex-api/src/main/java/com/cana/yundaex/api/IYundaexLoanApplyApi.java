package com.cana.yundaex.api;

import com.cana.vbam.common.dto.PageResult;
import com.cana.vbam.common.member.dto.user.UserSessionDTO;
import com.cana.vbam.common.yundaex.dto.loanApply.YundaexLoanApplyDTO;
import com.cana.vbam.common.yundaex.dto.loanApply.YundaexLoanApplyQueryDTO;
import com.cana.vbam.common.yundaex.dto.loanInfo.YundaexLoanFlowListDTO;
import com.cana.vbam.common.yundaex.dto.loanInfo.YundaexLoanFlowQueryDTO;
import com.cana.yundaex.common.dto.YundaexLoanInfoListRequest;
import com.cana.yundaex.common.dto.YundaexLoanInfoListResponse;

/**
 * 韵达项目－放款申请
 * 
 * @author gugunaggong
 *
 */
public interface IYundaexLoanApplyApi {

	/**
	 * 根据韵达客户信息 页面展示放款信息
	 * 
	 * @param queryDTO
	 * @return
	 */
	public YundaexLoanApplyDTO getLoanApplyDetails(String memberId);

	/**
	 * 放款申请
	 * 
	 * @param ckeckDTO
	 * @throws Exception 
	 */
	public void creditLoanApply(YundaexLoanApplyQueryDTO yundaexLoanApplyQueryDTO, UserSessionDTO userSessionDTO) throws Exception;

	/**
	 * 获取申请用款流水列表
	 * @param queryDTO
	 * @return
	 */
	public PageResult<YundaexLoanFlowListDTO> queryLoanFlowList(YundaexLoanFlowQueryDTO loanFlowQueryDTO, String memberId);

	/**
	 * 韵达 放款信息查询接口
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
	
	/**
	 * 根据融资客户ID查询放款记录条数
	 * @param financeId
	 * @return
	 */
	public int countYundaexLoanInfoRecord(String financeId); 
}
