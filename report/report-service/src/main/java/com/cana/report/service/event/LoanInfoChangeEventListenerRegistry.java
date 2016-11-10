package com.cana.report.service.event;

import java.util.HashMap;
import java.util.Map;

import com.cana.vbam.common.repayment.enums.LoanInfoChangeType;

/**
 * 监听器注册中心
 * @author dev3
 *
 */
public class LoanInfoChangeEventListenerRegistry {

	private static Map<LoanInfoChangeType, ILoanInfoChangeEventListener> registry = new HashMap<>();
	
	static {
		// 在这里做绑定
		registry(LoanInfoChangeType.created, new CreatedEventListener());
		registry(LoanInfoChangeType.modify, new ModifyEventListener());
		registry(LoanInfoChangeType.append_finance_amount, new AppendFinanceAmountEventListener());
		registry(LoanInfoChangeType.offline_repayment, new OfflineRepaymentEventListener());
		registry(LoanInfoChangeType.adjust, new AdjustEventListener());
		registry(LoanInfoChangeType.deduct, new DeductEventListener());
		registry(LoanInfoChangeType.extension_charge_generate, new ExtensionChargeGenerateEventListener());
		registry(LoanInfoChangeType.overdue_generate, new OverdueGenerateEventListener());
		registry(LoanInfoChangeType.penalty_generate, new PenaltyGenerateEventListener());
		registry(LoanInfoChangeType.refund, new RefundEventListener());
		registry(LoanInfoChangeType.active_repayment, new OfflineRepaymentEventListener());
		registry(LoanInfoChangeType.tz_account, new OfflineRepaymentEventListener());
		
	}
	
	/**
	 * 注册事件处理器
	 * @param eventType
	 * @param listener
	 */
	public static synchronized void registry(LoanInfoChangeType eventType, ILoanInfoChangeEventListener listener) {
		registry.put(eventType, listener);
	}
	
	/**
	 * 获取指定事件类型关联的事件处理器
	 * @param eventType
	 * @return
	 */
	public static synchronized ILoanInfoChangeEventListener getListener(LoanInfoChangeType eventType){
		return registry.get(eventType);
	}
	
	
}
