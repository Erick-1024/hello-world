package com.cana.repayment.service.util;

import java.math.BigDecimal;
import java.util.List;

import com.cana.repayment.dao.po.RepaymentLoanInfo;
import com.cana.repayment.dao.po.RepaymentRule;
import com.cana.repayment.dao.po.RepaymentSingleCollect;
import com.cana.repayment.service.bo.RepaymentLoanInfoBO;
import com.cana.repayment.service.bo.RepaymentSingleCollectBO;
import com.cana.vbam.common.repayment.dto.CreateLoanRequest;

public interface IRepaymentServiceHelper {

	/**
	 * 查找此时适用于保理商和融资客户的还款规则
	 * @param factorId
	 * @param financeId
	 * @return
	 */
	public RepaymentRule getRepaymentRule(String factorId, String financeId);
	
	/**
	 * 创建放款信息的还款规则
	 * @param loanInfo
	 * @param penaltyRate 罚息率，如果为空，则使用还款规则中的设置
	 */
	public void createLoanInfoConfig(RepaymentLoanInfo loanInfo, BigDecimal penaltyRate);
	
	/**
	 * 创建放款信息的还款规则
	 * @param loanInfo
	 * @param request 创建放款信息请求
	 */
	public void createLoanInfoConfig(RepaymentLoanInfo loanInfo, CreateLoanRequest request);
	
	/**
	 * 根据融资客户id和产品id锁定未还清的放款信息, 返回结果按照放款编号排序
	 * @param financeId
	 * @return
	 */
	public List<RepaymentLoanInfo> lockUnsettleLoanInfosByFinanceIdAndProductId(String financeId, String businessProductId);
	
	
	/**
	 * 生成账扣子任务id
	 * @return
	 */
	public String generateTaskItemId();
	
	/**
	 * 插入还款成功消息通知重试记录
	 * @param loanInfoBO
	 * @param repaymentRecord
	 */
	public void insertRepaymentSuccessNotificationRetryTaskRecord(RepaymentLoanInfoBO loanInfoBO, RepaymentSingleCollectBO repaymentRecord);
	
	/**
	 * 插入主动还款成功消息通知重试记录
	 * @param loanInfo
	 */
	public void insertActiveRepaymentSuccessNotificationRetryTaskRecord(RepaymentLoanInfo loanInfo, RepaymentSingleCollect repaymentRecord);


	/**
	 * 按照参数条件锁定放款记录
	 * @param financeId
	 * @param travlezenFinanceProductId
	 * @param payDate
	 * @return
	 */
	public RepaymentLoanInfo lockLoanInfoByFinanceIdAndProductIdAndLoanDate(String factorId, String financeId,
			String travlezenFinanceProductId, String payDate, String outCustomerId);

	/**
	 * 自动生成放款编号
	 * @return
	 */
	public String generateLoanNo();
	
	
	/**
	 * 插入调账成功消息通知重试记录
	 * @param loanInfo
	 */
	public void insertAdjustSuccessNotificationRetryTaskRecord(RepaymentLoanInfo loanInfo);
	
	/**
	 * 根据id锁定放款记录
	 * @param id
	 * @return
	 */
	public RepaymentLoanInfoBO lockLoanInfoById(String id);

	/**
	 * 组装VJ消息
	 * @param template
	 * @param dataItems
	 * @return
	 */
	public String generateVJMessageContent(String template, List<String> dataItems);
	
	/**
	 * 发送VJ短信消息
	 * @param content
	 * @param receivePhoneNum
	 */
	public void sendVJSmsMessage(String content, String receivePhoneNum);
	
}
