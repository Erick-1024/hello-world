package com.cana.credit.api;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.cana.flight.finance.common.dto.CreditAgentRepaymentDTO;
import com.cana.flight.finance.common.dto.CreditPayDTO;
import com.cana.flight.finance.common.dto.CreditRefundDTO;
import com.cana.flight.finance.common.dto.CreditTradeDTO;
import com.cana.flight.finance.common.dto.CreditTradeQueryCriteria;
import com.cana.flight.finance.common.dto.QueryCreditTradeStateDTO;
import com.cana.vbam.common.credit.dto.apply.AccessRuleDTO;
import com.cana.vbam.common.credit.dto.apply.CustomerApplyDetailDTO;
import com.cana.vbam.common.credit.dto.apply.CustomerApplyListQueryDTO;
import com.cana.vbam.common.credit.dto.apply.CustomerApplyMinDTO;
import com.cana.vbam.common.credit.dto.apply.CustomerApplyRequestDTO;
import com.cana.vbam.common.credit.dto.apply.TravelzenCustomerAuditResultDTO;
import com.cana.vbam.common.credit.dto.limit.CreditUsedLimitDTO;
import com.cana.vbam.common.credit.dto.limit.CreditUsedLimitInfo;
import com.cana.vbam.common.credit.dto.limit.CreditUsedLimitInfoQueryCriteria;
import com.cana.vbam.common.credit.dto.limit.CreditUsedLimitRequest;
import com.cana.vbam.common.credit.dto.limit.CustomerLimitListQueryDTO;
import com.cana.vbam.common.credit.dto.limit.CustomerLimitListResponseDTO;
import com.cana.vbam.common.credit.dto.limit.PreGenerateCreditLimitResponse;
import com.cana.vbam.common.credit.dto.limit.QueryCreditLimitDTO;
import com.cana.vbam.common.credit.dto.limit.QueryCreditLimitResponse;
import com.cana.vbam.common.credit.dto.marketing.CurrentActivityRequest;
import com.cana.vbam.common.credit.dto.marketing.CurrentActivityResponse;
import com.cana.vbam.common.credit.dto.marketing.PrepayProductRequest;
import com.cana.vbam.common.credit.dto.marketing.PrepayProductResponse;
import com.cana.vbam.common.credit.dto.outcustomer.OutCustomerDTO;
import com.cana.vbam.common.credit.dto.outcustomer.OutCustomerQuery;
import com.cana.vbam.common.credit.dto.trade.CreditStatementDTO;
import com.cana.vbam.common.credit.dto.trade.CreditTradeFlowDTO;
import com.cana.vbam.common.credit.dto.trade.CreditTradeOperateDTO;
import com.cana.vbam.common.credit.dto.trade.CreditTradeStateResultDTO;
import com.cana.vbam.common.credit.dto.trade.CreditUsedLimit;
import com.cana.vbam.common.credit.dto.white.WhiteCustomerDTO;
import com.cana.vbam.common.credit.dto.white.WhiteCustomerParamDTO;
import com.cana.vbam.common.credit.dto.white.WhiteCustomerRuleDTO;
import com.cana.vbam.common.credit.dto.white.WhiteCustomerRuleParamDTO;
import com.cana.vbam.common.credit.enums.AccessRuleFitObject;
import com.cana.vbam.common.credit.enums.CreditTransferStatus;
import com.cana.vbam.common.credit.openapi.CreditLoanDetailRequest;
import com.cana.vbam.common.credit.openapi.CreditLoanDetailResponse;
import com.cana.vbam.common.credit.openapi.LoanInfoDetailRequest;
import com.cana.vbam.common.credit.openapi.LoanListResponse;
import com.cana.vbam.common.dto.PageResult;
import com.cana.vbam.common.report.dto.MonitorMoneyDTO;
import com.travelzen.framework.common.PageList;

public interface ICreditApi {

	/**
	 * 客户申请初步校验
	 * @param customerId 客户Id
	 * @return 返回是否符合初步校验
	 */
	public Boolean precheck(String customerId);
	
	/**
	 * 查询白名单规则列表
	 */
	public PageList<WhiteCustomerRuleDTO> getWhiteCustomerRules(WhiteCustomerRuleParamDTO param);
	
	public PageList<WhiteCustomerDTO> getWhiteCustomers(WhiteCustomerParamDTO param);
	/**
	 * 保存真旅推送的客户资料
	 * @param customerApplyDTO
	 * @throws Exception
	 */
	public void saveTravelzenAuditResult(CustomerApplyRequestDTO customerApplyDTO)throws Exception;
	/**
	 * 分页查询客户额度审核列表
	 * @param customerApplyListQueryDTO 查询条件
	 * @return
	 */
	public PageList<CustomerApplyMinDTO> getCustomerApplyList(CustomerApplyListQueryDTO customerApplyListQueryDTO);
	
	/**
	 * 查询审核信息
	 * @param id customer_apply表的Id
	 * @param canaId 客户的CanaId
	 * @return
	 */
	public CustomerApplyDetailDTO getCustomerApplyInfo(String id);
	
	/**
	 * 真旅网客户人工审核
	 * @param resultDTO
	 */
	public void auditTravelzenCustomer(TravelzenCustomerAuditResultDTO resultDTO);
	
	/**
	 * 分页查询客户额度列表
	 * @param customerLimitListQueryDTO
	 * @return
	 */
	public PageList<CustomerLimitListResponseDTO> getCustomerLimitList(CustomerLimitListQueryDTO customerLimitListQueryDTO);
	
	/**
	 * 查询客户某段时间内的额度使用值(只将支付金额求和，没有减去退款金额)
	 * @param startDate 开始时间
	 * @param endDate 结束时间
	 * @param institution 机构
	 * @return
	 */
	public List<CreditUsedLimit> queryUsedLimit(Date startDate, Date endDate, String institution);
	
	/**
	 * 查询客户某个时间前使用掉的额度
	 * @param date 时间点（不包括）
	 * @param institution 机构
	 * @param productId 产品ID
	 * @return
	 */
	public List<CreditUsedLimit> queryUsedLimit(Date date, String institution, String productId);
	
	/**
	 * 激活该凯拿用户的所有真旅的未激活的额度
	 * @param memberId 用户ID
	 * @deprecated
	 */
	public void activateCreditLimit(String memberId);

	/**
	 * 激活某一条额度
	 * @param limitId 用户ID
	 */
	public void activateCreditLimitByLimitId(String limitId);
	
	/**
	 * 获取所有的准入规则（对batch_no降序）
	 * @return
	 */
	public List<AccessRuleDTO> queryAccessRule(AccessRuleFitObject fitObject);
	
	/**
	 * 获取所有通过额度审核的外部客户。<br/>
	 * Map&lt;institution, Map&lt;outCustomerId, memberId>>
	 * @return
	 */
	public Map<String, Map<String, String>> getAllOutCustomer();
	
	/**
	 * 获取所用符合条件的外部客户
	 * @param outCustomerQuery
	 * @return
	 */
	public List<OutCustomerDTO> getOutCustomerDTO(OutCustomerQuery outCustomerQuery);
	
	/**
	 * 获取所有通过额度审核的客户数量
	 * @param institution 机构
	 * @return
	 */
	public int getOutCustomerNumber(String institution);
	
	/**
	 * 授信支付接口
	 * @param creditPayDTO 传入值
	 * @return 如果支付成功的话返回Cana平台的交易流水号
	 */
	public String creditPay(CreditPayDTO creditPayDTO);
	
	/**
	 * 授信退款接口
	 * @param creditRefundDTO 传入值
	 * @return 如果退款受理成功的话（不包含转账）返回Cana平台的交易流水号
	 */
	public String creditRefund(CreditRefundDTO creditRefundDTO);

	/**
	 * 授信代还款接口
	 * @param creditAgentRepymentDTO 传入值
	 * @return 如果代还款受理成功的话（不包含转账）返回Cana平台的交易流水号
	 */
	public String creditAgentRepayment(CreditAgentRepaymentDTO creditAgentRepaymentDTO);
	
	/**
	 * 授信支付、退款状态查询
	 * @param queryDTO
	 * @return
	 */
	public CreditTradeStateResultDTO queryCreditTradeState(QueryCreditTradeStateDTO queryDTO);
	
	/**
	 * 授信额度预生成查询
	 */
	public PreGenerateCreditLimitResponse preGenerateCreditLimit(String customerId);
	
	/**
	 * 授信额度余额查询
	 */
	public QueryCreditLimitResponse queryCreditLimitBalance(QueryCreditLimitDTO queryDTO);
	
	/**
	 * 从额度表中查询客户的外部平台ID和CNAN平台ID号
	 * @param projectId
	 * @param companyName
	 * @param outCustomerName
	 * @return Map&lt;outCustomerId, memberId>
	 */
	public Map<String, String> queryOutCustomerIdAndMemberId(String projectId, String companyName, String outCustomerName);
	
	/**
	 * 查询授信的余额情况
	 * @param creditUsedLimitInfoQueryCriteria
	 * @return
	 */
	public List<CreditUsedLimitInfo> queryCreditUsedLimitInfo(CreditUsedLimitInfoQueryCriteria creditUsedLimitInfoQueryCriteria);
	
	/** 
	 * 从授信表中查询对应的外部客户名称
	 * @param productId
	 * @param memberId
	 * @param outCustomerId
	 * @return
	 */
	public String queryOutCustomerName(String productId, String memberId, String outCustomerId);
	
	public int queryCountCreditUsedLimitInfo(CreditUsedLimitInfoQueryCriteria creditUsedLimitInfoQueryCriteria);
	
	/**
	 * 查询放款明细详情
	 * @param summaryId 汇总编号
	 * @param loginFinanceId 如果登录用户是融资商，则需要传此值
	 */
	public PageResult<CreditTradeDTO> queryCreditLoanDetailsDetail(int page, int pageSize,
			String summaryId, String loginFinanceId);
	
	/**
	 * 查询退款明细列表
	 * @param criteria 查询条件
	 * @return
	 */
	public PageResult<CreditTradeDTO> queryCreditTrades(CreditTradeQueryCriteria criteria);
	
	/**
	 * 查询放款流水列表
	 * @param criteria
	 * @return
	 */
	public PageResult<CreditTradeFlowDTO> queryCreditFlowList(CreditTradeQueryCriteria criteria);

	/**
	 * 获取对账单（没有分页）
	 * @param criteria
	 * @return
	 */
	public PageResult<CreditStatementDTO> getCreditStatementNoPaging(CreditTradeQueryCriteria criteria);
	
	/**
	 * 获取转账交易的信息
	 * @param id
	 * @return
	 */
	public CreditTradeOperateDTO getCreditTransferInfo(String id);
	
	/**
	 * 人工操作放款失败的交易
	 */
	public CreditTransferStatus manualOperateCreditTransfer(String id,String operatorId);
	
	/**
	 * openApi
	 * 查询放款信息列表
	 * @param request
	 * @return
	 */
	public LoanListResponse queryLoanInfoList(LoanInfoDetailRequest request) throws Exception;
	/** 获取账单明细详情
	 * @param request
	 * @return
	 */
	public CreditLoanDetailResponse getCreditLoanDetail(CreditLoanDetailRequest request)throws Exception;
	
	public String getCanaFinanceIdByOutCustomerId(String institution, String outCustomerId);
	
	/**
	 * 在支付前获取当前客户可以使用的产品及促销信息
	 * @param currentActivityRequest
	 * @return
	 */
	public CurrentActivityResponse getCurrentActivity(CurrentActivityRequest currentActivityRequest);
	
	/**
	 * 在支付前获取当前客户可以使用的产品及促销信息
	 * @param prepayProductRequest
	 * @return
	 */
	public PrepayProductResponse getPrepayProduct(PrepayProductRequest prepayProductRequest);
	
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
	
	/**
	 * 韵达监控列表数据额度查询
	 * @param creditUsedLimitRequest
	 * @return
	 */
	public List<CreditUsedLimitDTO> getCreditUsedLimitInfo(CreditUsedLimitRequest creditUsedLimitRequest);
	
	/**
	 * 韵达监控返回总条数
	 * @param creditUsedLimitRequest
	 * @return
	 */
	public int getCreditUsedLimitInfoCount(CreditUsedLimitRequest creditUsedLimitRequest);
	
}
