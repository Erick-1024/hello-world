/**
 * 
 */
package com.cana.yundaex.service;

import com.cana.yundaex.dao.po.YundaexLoanInfoRecord;

/**
 * 韵达项目- 放款记录服务
 * 
 * @author guguanggong
 *
 */
public interface IYundaexLoanInfoRecordService {
	/**
	 * 根据流水号查放款记录
	 * @return
	 */
	public YundaexLoanInfoRecord getYundaexLoanInfoRecord(String businessSeq);
	
	/**
	 * 根据融资客户ID查询放款记录条数
	 * @param financeId
	 * @return
	 */
	public int countYundaexLoanInfoRecord(String financeId);

}
