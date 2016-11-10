package com.cana.report.service.util;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.cana.report.dao.mapper.gen.ReportMonitorDataMapper;
import com.cana.report.dao.po.ReportMonitorData;
import com.cana.report.dao.po.ReportMonitorDataExample;
import com.cana.vbam.common.utils.Constants;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.dao.rdbms.SequenceGenerator;

@Service
public class ReportServiceHelper implements IReportServiceHelper{
	
	@Resource
	private ReportMonitorDataMapper monitorDataMapper;
	
	@Resource
	private SequenceGenerator seqGen;

	@Override
	public ReportMonitorData lockReportMonitorDataByMemberIdAndDateAndType(String memberId, String outCustomerId, String date, String type) {
		ReportMonitorDataExample example = new ReportMonitorDataExample();
		example.createCriteria().andMemberIdEqualTo(memberId)
								.andOutCustomerIdEqualTo(outCustomerId)
								.andDateEqualTo(date)
								.andTypeEqualTo(type);
		List<ReportMonitorData> monitorDataList = monitorDataMapper.lockByExample(example);
		if(CollectionUtils.isEmpty(monitorDataList))
			return null;
		else return monitorDataList.get(0);
	}

	@Override
	public String generateReportMonitorDataId() {
		return DateTimeUtil.datetime14() + seqGen.getNextSeq(Constants.SEQUENCE_NAME_REPORT_MONITOR_DATA, 4);
	}

	@Override
	public String generateReportMonitorMetricId() {
		return DateTimeUtil.datetime14() + seqGen.getNextSeq(Constants.SEQUENCE_NAME_REPORT_MONITOR_METRIC, 4);
	}

}
