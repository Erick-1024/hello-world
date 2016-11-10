package com.cana.report.dao.mapper;

import java.util.List;
public interface IReportLoanInfoChangeTaskMapper
{
	/**
	 * 获取所有放款信息发生改变或者新增的放款信息的id
	 * 
	 */
	List<String> getAllChangedLoanInfoIds();
}
