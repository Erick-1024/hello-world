package com.cana.credit.service.transaction;

import java.util.Map;

import com.cana.credit.dao.po.CreditTransfer;
import com.cana.flight.finance.common.dto.CreditAgentRepaymentDTO;
import com.cana.flight.finance.common.dto.CreditPayDTO;
import com.cana.flight.finance.common.dto.CreditRefundDTO;
import com.cana.vbam.common.asset.dto.ContractInfoDTO;
import com.cana.vbam.common.asset.dto.ProjectInfo;
import com.cana.vbam.common.credit.enums.CreditTransferStatus;

public interface ICreditTradeTransactionService {


	/**
	 * 授信支付接口
	 * @param project
	 * @param creditPayDTO
	 * @param contractInfoDTO
	 * @return 如果支付成功的话返回Cana平台的交易流水号
	 */
	public CreditTransfer creditPay(ProjectInfo project, CreditPayDTO creditPayDTO, ContractInfoDTO contractInfoDTO);
	
	/**
	 * 授信退款接口
	 * @param creditRefundDTO
	 * @return
	 */
	public  CreditTransfer creditRefund(CreditRefundDTO creditRefundDTO);
	
	/**
	 * 转账失败的处理（指客户转账给保理商这个操作失败）
	 * @param creditTransfer
	 */
	public void creditTransferFail(CreditTransfer creditTransfer);
	
	/**
	 * 退款成功的处理（指客户转账给保理商这个操作成功）
	 * @param creditTransfer
	 * @param extra 用户保存其他传入传出参数
	 * @param toAccountNo 恢复额度后多的部分的金额去向
	 * @param travelzenFinanceProduct
	 * @return
	 */
	public CreditTransfer creditRefundSuccess(CreditTransfer creditTransfer, final Map<Object, Object> extra, String toAccountNo);

	/**
	 * 代还款成功的处理（指真旅网转账给保理商这个操作成功）
	 * @param creditTransfer
	 * @return
	 */
	public void creditAgentRepaymentSuccess(CreditTransfer creditTransfer);
	
	/**
	 * 代还款接口
	 * @param creditAgentRepaymentDTO
	 * @return
	 */
	public  CreditTransfer creditAgentRepayment(ProjectInfo projectInfo,CreditAgentRepaymentDTO creditAgentRepaymentDTO,ContractInfoDTO contractInfoDTO);
	
	/**
	 * 更新授信转账状态
	 * @param creditTransfer 授信转账实体
	 * @param creditTransferStatus 最新的授信转账状态
	 */
	public void updateCreditTransferStatus(CreditTransfer creditTransfer, CreditTransferStatus creditTransferStatus);
	
	public CreditTransfer manualUpdateCreditTransfer(String id,String operaterId);
	
	public void updateCreditTransferStatusById(String id,CreditTransferStatus creditTransferStatus);
	
}
