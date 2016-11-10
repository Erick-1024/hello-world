package com.cana.report.service.bo;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import com.cana.report.dao.po.ReportLoanInfoChangeTrace;

/**
 * 放款信息追踪表BO类
 * @author dev3
 *
 */
public class ReportLoanInfoChangeTraceBO extends ReportLoanInfoChangeTrace
{
	private static final long serialVersionUID = 9085084851102048839L;
	
	public ReportLoanInfoChangeTraceBO (ReportLoanInfoChangeTrace po)
	{
		BeanUtils.copyProperties(po, this);
	}

	/**
	 * 判断 放款信息追踪表 中放款信息Id 和 版本号 是否为空。
	 * @return
	 */
	public boolean isEmpty()
	{
		return StringUtils.isBlank(getLoanInfoId()) || StringUtils.isBlank(getLastTraceVersion());
	}
	
}
