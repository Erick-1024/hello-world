package com.cana.report.api;
import java.math.BigDecimal;
import java.util.List;

import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.dto.ObjectResult;
import com.cana.vbam.common.report.dto.AccountFundDailyReportQueryDTO;
import com.cana.vbam.common.report.dto.AccountFundYearReportQueryDTO;
import com.cana.vbam.common.report.dto.MonitorDataDataDTO;
import com.cana.vbam.common.report.dto.MonitorMetricAndDataQueryDTO;
import com.cana.vbam.common.report.dto.MonitorMetricAndDataQueryYundaDTO;
import com.cana.vbam.common.report.dto.MonitorMetricData;
import com.cana.vbam.common.report.dto.MonitorMetricDataDTO;
import com.cana.vbam.common.report.dto.MonitorMetricDataYundaDTO;
import com.cana.vbam.common.report.dto.MonitorMetricYundaDTO;
import com.cana.vbam.common.report.dto.MonitorSummaryDTO;
import com.cana.vbam.common.report.dto.MonitorSummaryQueryDTO;
import com.cana.vbam.common.report.dto.MonitorSummaryQueryYundaDTO;
import com.cana.vbam.common.report.dto.MonitorSummaryYundaDTO;
import com.cana.vbam.common.report.dto.ReportAccountFundCountDTO;
import com.cana.vbam.common.report.dto.ReportAccountFundDTO;
import com.cana.vbam.common.report.dto.ReportFactorFinanceCountDTO;
import com.cana.vbam.common.report.dto.ReportFactorFinanceDayDTO;
import com.cana.vbam.common.report.dto.ReportFactorFinanceSearchCriteria;
import com.cana.vbam.common.report.dto.ReportFactorFinanceYearDTO;
import com.cana.vbam.common.report.dto.ReportFundMonthlyResultDTO;
import com.cana.vbam.common.report.dto.ReportFundMonthlySearchCriteria;

public interface IReportApi
{
	/**
	 * 查询融资日报表
	 * @param searchCriteria
	 * @return
	 */
	public ListResult<ReportFactorFinanceDayDTO> queryRepaymentDailyReport(ReportFactorFinanceSearchCriteria searchCriteria);
	
	/**
	 * 不分页查询融资日报表
	 * @param searchCriteria
	 * @return
	 */
	public List<ReportFactorFinanceDayDTO> queryRepaymentDailyReportNoPaging(ReportFactorFinanceSearchCriteria searchCriteria);
	
	/**
	 * 查询融资年报表
	 * @param searchCriteria
	 * @return
	 */
	public ListResult<ReportFactorFinanceYearDTO> queryRepaymentAnnualReport(ReportFactorFinanceSearchCriteria searchCriteria);

	/**
	 * 不分页查询融资年报表
	 * @param searchCriteria
	 * @return
	 */
	public List<ReportFactorFinanceYearDTO> queryRepaymentAnnualReportNoPaging(ReportFactorFinanceSearchCriteria searchCriteria);
	
	/**
	 * 查询融资计数报表
	 * @param searchCriteria
	 * @return
	 */
	public ListResult<ReportFactorFinanceCountDTO> queryRepaymentCountReport(ReportFactorFinanceSearchCriteria searchCriteria);

	/**
	 * 不分页查询融资计数报表
	 * @param searchCriteria
	 * @return
	 */
	public List<ReportFactorFinanceCountDTO> queryRepaymentCountReportNoPaging(ReportFactorFinanceSearchCriteria searchCriteria);
	
	/**
	 * 查询账户资金日报表
	 * @param query
	 * @return
	 */
	ListResult<ReportAccountFundDTO> queryAccountFundDailyReport(AccountFundDailyReportQueryDTO query);
	
	/**
	 * 查询账户资金年报表
	 * @param query
	 * @return
	 */
	ListResult<ReportAccountFundDTO> queryAccountFundYearReport(AccountFundYearReportQueryDTO query);
	
	/**
	 * 查询账户资金统计日报表
	 * @param query
	 * @return
	 */
	ListResult<ReportAccountFundCountDTO> queryAccountFundDailyCountReport(AccountFundDailyReportQueryDTO query);
	
	/**
	 * 导出资金日报表
	 * @param query
	 * @return
	 */
	List<ReportAccountFundDTO> exportAcountFundDailyReport(AccountFundDailyReportQueryDTO query);
	
	/**
	 * 导出资金汇总表
	 * @param query
	 * @return
	 */
	List<ReportAccountFundDTO> exportAcountFundYearReport(AccountFundYearReportQueryDTO query);
	
	/**
	 * 导出资金计数报
	 * @param query
	 * @return
	 */
	List<ReportAccountFundCountDTO> exportAcountFundDailyCountReport(AccountFundDailyReportQueryDTO query);
	
	/**
	 * 查询监控概要信息
	 * @param customerName 客户名称 
	 * @return
	 */
	ListResult<MonitorSummaryDTO> queryMonitorSummary(MonitorSummaryQueryDTO monitorSummaryQueryDTO);
	
	/**
	 * 查询某个用户在某段时间内的监控指数数据
	 * @param monitorMetricDataQueryDTO
	 * @return
	 */
	List<MonitorMetricDataDTO> queryMonitorMetricDataDTO(MonitorMetricAndDataQueryDTO monitorMetricDataQueryDTO);
	
	/**
	 * 查询某个用户在某段时间内的监控数据
	 * @param monitorMetricDataQueryDTO
	 * @return
	 */
	List<MonitorDataDataDTO> queryMonitorDataDataDTO(MonitorMetricAndDataQueryDTO monitorMetricDataQueryDTO);
	
	/**
	 * 查询某个用户在某段时间内的监控指数数据(按监控生成时间升序排序)
	 * @param memberId 用户ID
	 * @param outCustomerId 外部平台客户ID
	 * @param productId 产品ID
	 * @param startDate 起始统计时间（包括）yyyy-MM-dd
	 * @param endDate 结束统计时间（包括）yyyy-MM-dd
	 * @return
	 */
	List<MonitorMetricData> queryMonitorMetricData(String memberId, String outCustomerId, String productId, String startDate, String endDate);
	
	/**
	 * 查询资金月报表数据
	 * @param searchCriteria
	 * @return
	 */
	ListResult<ReportFundMonthlyResultDTO> queryFundMonthlyReportData(ReportFundMonthlySearchCriteria searchCriteria);

	/**
	 * 查询资金月报表数据
	 * @param searchCriteria
	 * @return
	 */
	List<ReportFundMonthlyResultDTO> queryFundMonthlyReportDataWithoutPage(ReportFundMonthlySearchCriteria searchCriteria);

	/**
	 * 获取上个月的时间
	 * @return
	 */
	String getLastMonthDate();
	
	/**
	 * 获取当月总余额
	 * @param reportDate
	 * @return
	 */
	ObjectResult<String> getMonthlyBalanceSum(ReportFundMonthlySearchCriteria searchCriteria);

	/**
	 * 获取月报表主账户余额
	 * @param reportDate
	 * @return
	 */
	String getMainAccountBalance();
	
	//韵达监控接口
	
	/**
	 * 查询监控列表
	 * @param customerName 客户名称 
	 * @return
	 */
	ListResult<MonitorSummaryYundaDTO> queryYundaexMonitorSummary(MonitorSummaryQueryYundaDTO monitorSummaryQueryDTO);
	
	/**
	 * 查询某个用户在某段时间内的监控指数数据详情
	 * @param monitorMetricDataQueryDTO
	 * @return
	 */
	List<MonitorMetricDataYundaDTO> queryYundaexMonitorMetricDataDTO(MonitorMetricAndDataQueryYundaDTO monitorMetricDataQueryDTO);
	
	/**
	 * 为预警提供查询韵达监控数据
	 * @param memberId　韵达memberId
	 * @param productId　韵达项目id
	 * @param startDate　查询起始时间
	 * @param endDate　　　查询截止时间
	 * @return　MonitorMetricYundaDTO　
	 */
	public List<MonitorMetricYundaDTO> queryYundaMonitorMetricEarlyWarning(String memberId,String productId, String startDate,String endDate);


	public void save(BigDecimal param, String memberId, String stationNo, String yundaexAssetProjectId, String type, String currentDate);
	
	
}
