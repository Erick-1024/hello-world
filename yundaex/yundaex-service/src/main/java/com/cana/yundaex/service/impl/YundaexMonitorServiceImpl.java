package com.cana.yundaex.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cana.common.dao.mapper.gen.PropertiesMapper;
import com.cana.common.dao.po.Properties;
import com.cana.vbam.common.consts.ReportConstant;
import com.cana.vbam.common.service.IVbamCommonService;
import com.cana.yundaex.common.enums.YundaexAuditState;
import com.cana.yundaex.common.enums.YundaexCreditLimitGenerateState;
import com.cana.yundaex.common.util.Constants;
import com.cana.yundaex.dao.mapper.gen.YundaexCreditMonitorMapper;
import com.cana.yundaex.dao.mapper.gen.YundaexCustomerApplyMapper;
import com.cana.yundaex.dao.mapper.gen.YundaexCustomerApplyMonitorMapper;
import com.cana.yundaex.dao.mapper.gen.YundaexOutCustomerMapper;
import com.cana.yundaex.dao.mapper.gen.YundaexTstationInfoMapper;
import com.cana.yundaex.dao.po.YundaexCreditMonitor;
import com.cana.yundaex.dao.po.YundaexCreditMonitorExample;
import com.cana.yundaex.dao.po.YundaexCustomerApply;
import com.cana.yundaex.dao.po.YundaexCustomerApplyExample;
import com.cana.yundaex.dao.po.YundaexCustomerApplyMonitor;
import com.cana.yundaex.dao.po.YundaexCustomerApplyMonitorExample;
import com.cana.yundaex.dao.po.YundaexOutCustomer;
import com.cana.yundaex.dao.po.YundaexOutCustomerExample;
import com.cana.yundaex.dao.po.YundaexTstationInfo;
import com.cana.yundaex.dao.po.YundaexTstationInfoExample;
import com.cana.yundaex.service.IYundaexMonitorService;
import com.cana.yundaex.service.transaction.IYundaexCreditLimitTransactionService;
import com.cana.yundaex.service.transaction.IYundaexMonitorTransactionService;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.util.DateUtils;

@Service
public class YundaexMonitorServiceImpl implements IYundaexMonitorService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	private YundaexCustomerApplyMapper yundaexCustomerApplyMapper;
	
	@Resource
	private YundaexCustomerApplyMonitorMapper yundaexCustomerApplyMonitorMapper;
	
	@Resource
	private YundaexOutCustomerMapper yundaexOutCustomerMapper;
	
	@Resource
	private YundaexTstationInfoMapper yundaexTstationInfoMapper;
	
	@Resource
	private YundaexCreditMonitorMapper yundaexCreditMonitorMapper;
	
	@Resource
	private PropertiesMapper propertiesMapper;
	
	@Resource
	private IYundaexMonitorTransactionService yundaexMonitorTransactionService; 
	
	@Resource
	private IYundaexCreditLimitTransactionService yundaexCreditLimitTransactionService;
	
	@Resource
	private IVbamCommonService vbamCommonService;
	
	
	@Override
	public void monitorScheduler() {
		logger.info("进入韵达监控定时任务");
		//是否更新最新的站点数据
		Properties stationPropertie = vbamCommonService.getProperties(Constants.YUNDAEX_STATION_PULL_DATE);
		Properties monitorPropertie = vbamCommonService.getProperties(ReportConstant.YUNDAEX_MONITOR_DATE);
		String currentDate = vbamCommonService.getCurrentDate();
		String virtualDate = DateUtils.format(DateUtils.getDate(currentDate, "yyyy-MM-dd"), 23);
//		if(stationPropertie !=null && DateTimeUtil.month7().equals(stationPropertie.getValue()) && (monitorPropertie == null ||!DateTimeUtil.month7().equals(monitorPropertie.getValue()))){
		if(stationPropertie !=null && virtualDate.equals(stationPropertie.getValue()) && (monitorPropertie == null ||!virtualDate.equals(monitorPropertie.getValue()))){
			//excel导入数据
			YundaexCustomerApplyMonitorExample monitorExample = new YundaexCustomerApplyMonitorExample();
			monitorExample.createCriteria().andStatusEqualTo(YundaexAuditState.WAIT.name()).andMonthEqualTo(virtualDate);
			List<YundaexCustomerApplyMonitor> monitors = yundaexCustomerApplyMonitorMapper.selectByExample(monitorExample);
			
			//客户申请数据
			YundaexCustomerApplyExample applyExample = new YundaexCustomerApplyExample();
			applyExample.createCriteria().andCreditLimitGenerateStateEqualTo(YundaexCreditLimitGenerateState.FINISH.name());
			List<YundaexCustomerApply> customerApplys = yundaexCustomerApplyMapper.selectByExample(applyExample);
			int ydMonitor = 0;
			int ydMonitorSize = 0;
			for(YundaexCustomerApply yundaexCustomerApply : customerApplys){
				boolean isSuccess = false;
				String memberId = getOutCustomerMemberId(yundaexCustomerApply.getStationNo());
				Boolean flag = checkIsNewestStationInfo(yundaexCustomerApply.getStationNo(), currentDate);
				if(flag){
					ydMonitorSize++;
					// 申请数据是否有变更
					for(YundaexCustomerApplyMonitor yundaexCustomerApplyMonitor : monitors){
						if(yundaexCustomerApply.getStationNo().equals(yundaexCustomerApplyMonitor.getStationNo())){
							yundaexCustomerApply.setStationAmount(yundaexCustomerApplyMonitor.getStationAmount());
							yundaexCustomerApply.setBusiLimit(yundaexCustomerApplyMonitor.getBusiLimit());
							yundaexCustomerApply.setBailBalance(yundaexCustomerApplyMonitor.getBailBalance());
							yundaexCustomerApply.setShortLoan(yundaexCustomerApplyMonitor.getShortLoan());
							yundaexCustomerApply.setLoanType(yundaexCustomerApplyMonitor.getLoanType());
							yundaexCustomerApply.setShortLoanLimit(yundaexCustomerApplyMonitor.getLoanLimit());
							yundaexCustomerApply.setLimitUnit(yundaexCustomerApplyMonitor.getLimitUnit());
							yundaexCustomerApply.setYundaexJudge(yundaexCustomerApplyMonitor.getYundaexJudge());
						}
					}
					isSuccess = yundaexMonitorTransactionService.monitorScheduler(yundaexCustomerApply, memberId, currentDate);
					if(isSuccess)
						ydMonitor++;
				}else{
					logger.info("没有该网点数据："+yundaexCustomerApply.getStationNo());
				}
			}
			if(ydMonitor > 0){
				// 更新properties
				Properties yundaexMonitorproperties = vbamCommonService.getProperties(ReportConstant.YUNDAEX_MONITOR_DATE);
				if(null == yundaexMonitorproperties)
					vbamCommonService.addProperties(ReportConstant.YUNDAEX_MONITOR_DATE, virtualDate);
//					vbamCommonService.addProperties(ReportConstant.YUNDAEX_MONITOR_DATE, DateTimeUtil.month7());
				else{
					Properties properties = new Properties();
					properties.setName(ReportConstant.YUNDAEX_MONITOR_DATE);
					vbamCommonService.updateProperties(properties, virtualDate);
//					vbamCommonService.updateProperties(properties, DateTimeUtil.month7());
				}
			}
			logger.info("这次韵达监控一共处理了{}个申请，通过了{}个申请", ydMonitorSize, ydMonitor);
		}
	}


	private Boolean checkIsNewestStationInfo(String stationNo, String currentDate) {
		YundaexTstationInfoExample  stationInfoExample = new YundaexTstationInfoExample();
		stationInfoExample.createCriteria().andStationNoEqualTo(stationNo).andStatmonthEqualTo(DateTimeUtil.month7(DateTimeUtil.addMonth(DateTimeUtil.getDate10(currentDate), -1)));
		List<YundaexTstationInfo> stationInfos = yundaexTstationInfoMapper.selectByExample(stationInfoExample);
		return CollectionUtils.isNotEmpty(stationInfos);
	}


	private String getOutCustomerMemberId(String stationNo) {
		YundaexOutCustomerExample outCustomerExample = new YundaexOutCustomerExample();
		outCustomerExample.createCriteria().andStationNoEqualTo(stationNo);
		List<YundaexOutCustomer> outCustomers = yundaexOutCustomerMapper.selectByExample(outCustomerExample);
		return outCustomers.get(0).getMemberId();
	}


	/**
	 * 韵达授信审核scheduler 上个月未审核的数据设置为未通过
	 */
	@Override
	public void creditAuditScheduler() {
		logger.info("进入韵达授信审核定时任务");
		YundaexCreditMonitorExample creditMonitorExample = new YundaexCreditMonitorExample();
		String currentDate = vbamCommonService.getCurrentDate();
		creditMonitorExample.createCriteria().andAuditStatusEqualTo(YundaexAuditState.WAIT.name()).andMonthEqualTo(DateTimeUtil.month7(DateTimeUtil.addMonth(DateTimeUtil.getDate10(currentDate), -1)));
		List<YundaexCreditMonitor> creditMonitors = yundaexCreditMonitorMapper.selectByExample(creditMonitorExample);
		int ydMonitor = 0;
		for(YundaexCreditMonitor YundaexCreditMonitor : creditMonitors){
			try {
				yundaexCreditLimitTransactionService.creditAuditReject(YundaexCreditMonitor.getId());
				ydMonitor ++;
			} catch (Exception e) {
				logger.info("韵达授信审核驳回失败,id="+YundaexCreditMonitor.getId(), e);
			}
		}
		logger.info("这次韵达授信审核一共处理了{}个申请，通过了{}个申请", creditMonitors.size(), ydMonitor);
	}

}
