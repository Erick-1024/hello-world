package com.cana.wechat.api;

import java.io.IOException;
import java.util.List;

import com.cana.vbam.common.member.dto.user.UserSessionDTO;
import com.cana.vbam.common.repayment.dto.FinanceInfo4FinanceDTO;
import com.cana.vbam.common.repayment.dto.RepaymentPlanActiveRepaymentDTO;
import com.cana.vbam.common.repayment.dto.RepaymentPlanSearchCriteriaDTO;
import com.cana.vbam.common.wechat.WechatConfig;
import com.cana.vbam.common.wechat.account.AccountWechatDTO;
import com.cana.vbam.common.wechat.account.TradeRecordRequest;
import com.cana.vbam.common.wechat.account.TradeRecordResponse;
import com.cana.vbam.common.wechat.member.user.CustomerWechatDetailDTO;
import com.cana.vbam.common.wechat.repayment.LoanInfoRequest;
import com.cana.vbam.common.wechat.repayment.LoanInfoResponse;

public interface IWeChatApi {
	
	/**
	 * yihong.tang
     * 查询 流水明细
     * @param request
     */
    public List<TradeRecordResponse> queryTradeRecords(TradeRecordRequest request);
    
    /**
     * yihong.tang
	 * 查询 融资信息
	 * @param request	
	 */
	public List<LoanInfoResponse> queryLoanInfos(LoanInfoRequest request);

	/**
	 * 查询企业客户的详情
	 * @param customerId
	 * @return
	 * @throws Exception
	 */
	public CustomerWechatDetailDTO queryCustomerDetail(String customerId);
	
	/**
	 * 修改联系人姓名
	 * @param userId
	 * @param contactName
	 * @return
	 * @throws Exception
	 */
	public void updateContactName(String userId, String contactName)throws Exception;
	/**
	 * 修改联系人手机号码
	 * @param userId
	 * @param mobileNum
	 * @return
	 * @throws Exception
	 */
	public void updateContactTel(String userId, String mobileNum)throws Exception;
	/**
	 * 修改联系人邮箱
	 * @param userId
	 * @param mail
	 * @return
	 * @throws Exception
	 */
	public void updateContactMail(String userId,String mail)throws Exception;
	/**
	 * 修改联系人职称
	 * @param userId
	 * @param jobTitle
	 * @return
	 * @throws Exception
	 */
	public void updateContactJobTitle(String userId, String jobTitle)throws Exception;
	
	/**
	 * 查询账户信息
	 * @throws Exception 
	 */
	public AccountWechatDTO getAccountInfo(String customerId);
	
	public WechatConfig getWechatWebConfig(String url) throws IOException;
	
	/**
	 * 获取融资客户登陆后的概要信息
	 * @param userSessionDTO
	 * @return
	 */
	public FinanceInfo4FinanceDTO getFinanceInfo(UserSessionDTO userSessionDTO);
	
	/**
	 * 获取预期的还款计划
	 * @param userSessionDTO
	 * @param searchDto
	 * @return
	 */
	public List<RepaymentPlanActiveRepaymentDTO> getOverdueRepaymentPlan(UserSessionDTO userSessionDTO, RepaymentPlanSearchCriteriaDTO searchDto);
	
	/**
	 * 获取7日应还的还款计划
	 * @param userSessionDTO
	 * @param searchDto
	 * @return
	 */
	public List<RepaymentPlanActiveRepaymentDTO> getRepaymentPlansWhtin7Days(UserSessionDTO userSessionDTO, RepaymentPlanSearchCriteriaDTO searchDto);
}
