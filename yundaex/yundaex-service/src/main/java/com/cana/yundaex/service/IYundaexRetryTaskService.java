package com.cana.yundaex.service;

import java.util.List;

import com.cana.repayment.dao.po.RepaymentLoanInfo;
import com.cana.vbam.common.member.dto.user.UserUpdateDTO;
import com.cana.vbam.common.yundaex.dto.apply.YdCustomerApplyDetailDTO;
import com.cana.yundaex.common.dto.YundaexTstationInfoQueryDTO;
import com.cana.yundaex.common.dto.apply.YdCustomerApply4MemberUserDTO;
import com.cana.yundaex.common.enums.Institution;

public interface IYundaexRetryTaskService {

	/**
	 * 韵达审核结果通知
	 * @param institution
	 * @param stationNo
	 * @param applyResult
	 * @param totalLimit
	 * @param canaUrl
	 */
	public void createAuditResultNotify(Institution institution, String stationNo, String applyResult,Long totalLimit, String  canaUrl);

	/**
	 * 创建授信用户
	 * @param ydCustomerApply4MemberUserDTO
	 * @param id
	 */
	public void createYdCreateCustomer(YdCustomerApply4MemberUserDTO ydCustomerApply4MemberUserDTO, String id);

	/**
	 * 韵达放款信息通知
	 * @param financeId
	 * @param info
	 * @param taskeId
	 * @param customerApplyDetailDTO
	 */
	public void createCreditTradeResult(String financeId, RepaymentLoanInfo info, String taskeId,YdCustomerApplyDetailDTO customerApplyDetailDTO);

	/**
	 * 激活用户，更新用户角色
	 * @param userUpdateDTO
	 */
	public void createUpdateUserRole(UserUpdateDTO userUpdateDTO);
	
	/**
	 * 拉取网点数据
	 * @param tstationInfo
	 */
	public void createStationPullTask(List<YundaexTstationInfoQueryDTO> queryDTOs);
	
	/**
	 * 同步网点数据
	 * @param queryDTOs
	 */
	public void createStationSynTask(List<YundaexTstationInfoQueryDTO> queryDTOs);

}
