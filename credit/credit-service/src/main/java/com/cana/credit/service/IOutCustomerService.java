package com.cana.credit.service;

import java.util.List;
import java.util.Map;

import com.cana.vbam.common.credit.dto.outcustomer.OutCustomerDTO;
import com.cana.vbam.common.credit.dto.outcustomer.OutCustomerQuery;
import com.cana.vbam.common.report.dto.MonitorMoneyDTO;

public interface IOutCustomerService {
	
	/**
	 * 获取所有通过额度审核的外部客户。<br/>
	 * Map&lt;institution, Map&lt;outCustomerId, memberId>>
	 * @return
	 */
	public Map<String, Map<String, String>> getAllOutCustomer();
	
	/**
	 * 获取所用通过额度审核的外部客户
	 * @param outCustomerQuery
	 * @return
	 */
	public List<OutCustomerDTO> getOutCustomerDTO(OutCustomerQuery outCustomerQuery);
	
	public String getCanaFinanceIdByOutCustomerId(String institution, String outCustomerId);
	
	/**
	 * 获取所有通过额度审核的客户数量
	 * @param institution 机构
	 * @return
	 */
	public int getOutCustomerNumber(String institution);
	
	/**
	 * 查询客户的在某一天内的销售额
	 * @param date10 日期(yyyy-MM-dd)
	 * @return
	 */
	public List<MonitorMoneyDTO> getFlightTicketSales(String date10);
	
	/**
	 * 批量计算获得合格AR余额(有价值客票票的（票面价+总税款）/总航班数×未起飞的航班数)
	 * @param startRecordId 搜索开始位置
	 * @return
	 */
	public List<MonitorMoneyDTO> getQualifiedAR(String startRecordId);
	
	/**
	 * 获取日均销售额
	 * @param startMonth 开始年月（包含）
	 * @param endMonth 结束年月（不包含）
	 * @param dayNumber 天数
	 * @return
	 */
	public List<MonitorMoneyDTO> getDailySales(String startMonth, String endMonth, int dayNumber);
	
}
