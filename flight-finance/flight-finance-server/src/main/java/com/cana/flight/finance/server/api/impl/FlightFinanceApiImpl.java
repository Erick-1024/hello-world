package com.cana.flight.finance.server.api.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;

import com.cana.flight.finance.api.IFlightFinanceApi;
import com.cana.flight.finance.common.dto.CustomerSaleDataVO;
import com.cana.flight.finance.common.dto.DailySales;
import com.cana.flight.finance.service.IDailyBillService;
import com.cana.flight.finance.service.IFlightTicketService;
import com.cana.flight.finance.service.utils.IFlightFinanceServiceHelper;
import com.cana.vbam.common.credit.dto.apply.CustomerSaleDataDTO;
import com.cana.vbam.common.report.dto.MonitorMoneyDTO;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.core.util.MoneyUtil;

public class FlightFinanceApiImpl  implements IFlightFinanceApi{

	@Resource
	private IFlightTicketService flightTicketServiceImpl;
	
	@Resource
	private IDailyBillService dailyBillServiceImpl;
	
	@Resource
	private IFlightFinanceServiceHelper flightFinanceServiceHelper;
	
	@Override
	public Map<String, Long> getFlightTicketSales(List<String> customerIds, Date date) {
		return dailyBillServiceImpl.getFlightTicketSales(customerIds, DateTimeUtil.date10(date));
	}
	
	@Override
	public Map<String, Long> getQualifiedAR(List<String> customerIds, String startRecordId) {
		return flightTicketServiceImpl.getQualifiedAR(customerIds, startRecordId);
	}

	@Override
	public Map<String, Long> getFlightTakeOffSales(List<String> customerIds, String date10, String startRecordId) {
		return flightTicketServiceImpl.getFlightTakeOffSales(customerIds, date10, startRecordId);
	}
	
	@Override
	public List<MonitorMoneyDTO> getTakeOffSales(Map<String, String> outCustomerId2MemberIdMap, String date10, String startRecordId) {
		return flightTicketServiceImpl.getTakeOffSales(outCustomerId2MemberIdMap, date10, startRecordId);
	}
	
	@Override
	public List<DailySales> getDailySales(String startMonth, String endMonth, List<String> customerIds, int dayNumber) {
		return dailyBillServiceImpl.getDailySales(startMonth, endMonth, customerIds, dayNumber);
	}

	@Override
	public List<CustomerSaleDataDTO> getMonthAverageSales(String tzCustomerId, Date endMonth,int period) {
		List<CustomerSaleDataVO> customerApplySaleDataVOs = flightFinanceServiceHelper.getMonthAverageSales(tzCustomerId, endMonth,period);
		return convertCustomerApplySaleDataVOs2DTOs(customerApplySaleDataVOs);
	}

	//转换销售数据
		private List<CustomerSaleDataDTO> convertCustomerApplySaleDataVOs2DTOs(List<CustomerSaleDataVO> customerApplySaleDataVOs){
			if(CollectionUtils.isEmpty(customerApplySaleDataVOs))
				return null;
			List<CustomerSaleDataDTO> customerApplySaleDataDTOs = new ArrayList<>();
			for(CustomerSaleDataVO customerApplySaleDataVO : customerApplySaleDataVOs){
				CustomerSaleDataDTO customerApplySaleDataDTO = new CustomerSaleDataDTO();
				customerApplySaleDataDTO.setYear(customerApplySaleDataVO.getYear());
				customerApplySaleDataDTO.setMonth(customerApplySaleDataVO.getMonth());
				customerApplySaleDataDTO.setSaleMoneyStr(MoneyUtil.cent2Yuan(customerApplySaleDataVO.getSaleMoney()));
				customerApplySaleDataDTOs.add(customerApplySaleDataDTO);
			}
			return customerApplySaleDataDTOs;
		}
	
}
