package com.cana.report.service.transaction;

import java.util.List;
import java.util.Map;

import com.cana.vbam.common.member.enums.user.UserType;
import com.cana.vbam.common.repayment.message.dto.RepaymentSuccessMessage;
import com.cana.vbam.common.report.enums.ReportType;

public interface IReportTransactionService
{
	/**
	 * 执行单条放款信息发生变更的所有处理事件
	 * @param loanInfoId
	 * @throws Exception
	 */
	public void processLoanInfoChangeTask(String loanInfoId)throws Exception;
	
	/**
	 * 初始化日报表和年报表
	 * @param allFactorAndFinanceUsers 所有的已激活的保理商和融资商帐户
	 * @throws Exception
	 */
	public void initReportDayAndYear(Map<String, List<String>> allUserIds) throws Exception;
	
	/**
	 * 生成日报表ID
	 * 
	 * @return
	 * @throws Exception
	 */
	public String generateReportFactorFinanceDayId() throws Exception;

	/**
	 * 生成年报表Id
	 * 
	 * @return
	 * @throws Exception
	 */
	public String generateReportFactorFinanceYearId() throws Exception;
	
	/**
	 * 生成计数报表id
	 * @return
	 * @throws Exception
	 */
	public String generateReportFactorFinanceCountId() throws Exception;
	
	/**
	 * 初始化追踪记录表
	 * @param loanInfoIds
	 * @throws Exception
	 */
	public void initLoanInfoChangeTrace(List<String> loanInfoIds) throws Exception;
	
	/**
	 * 处理放款成功消息
	 * @param message
	 * @throws Exception
	 */
	public void processRepaymentSuccessMessage(RepaymentSuccessMessage message) throws Exception;
	
	/**
	 * 初始化没有报表的信息
	 * @param reportType
	 */
	public void initReport(ReportType reportType, String businessProduct, String own_id, UserType userType) throws Exception ;
	
}
