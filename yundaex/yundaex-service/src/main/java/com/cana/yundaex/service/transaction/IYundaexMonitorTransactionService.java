package com.cana.yundaex.service.transaction;

import java.util.List;

import com.cana.yundaex.common.dto.monitor.YundaexMonitorImportDTO;
import com.cana.yundaex.dao.po.YundaexCustomerApply;

public interface IYundaexMonitorTransactionService {

	/**
	 * 导入已通过检查的韵达监控数据
	 * @param passYundaexMonitorImportDTOs
	 */
	void importExcelToDB(List<YundaexMonitorImportDTO> passYundaexMonitorImportDTOs);

	boolean monitorScheduler(YundaexCustomerApply yundaexCustomerApply, String memberId, String currentDate);

}
