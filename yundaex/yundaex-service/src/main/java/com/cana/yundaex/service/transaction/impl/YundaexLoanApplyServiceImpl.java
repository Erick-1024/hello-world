package com.cana.yundaex.service.transaction.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.cana.account.api.IAccountApi;
import com.cana.asset.api.IAssetApi;
import com.cana.asset.api.IAssetProjectManageApi;
import com.cana.credit.limit.dao.mapper.gen.CreditLimitMapper;
import com.cana.credit.limit.dao.po.CreditLimit;
import com.cana.credit.limit.dao.po.CreditLimitAudit;
import com.cana.member.api.IUserApi;
import com.cana.repayment.api.IFinanceReportApi;
import com.cana.repayment.service.bo.RepaymentLoanInfoBO;
import com.cana.repayment.service.transaction.IRepaymentTransactionService;
import com.cana.vbam.common.account.enums.AccountTradeStatus;
import com.cana.vbam.common.asset.dto.ContractInfoDTO;
import com.cana.vbam.common.asset.dto.FactorInfo;
import com.cana.vbam.common.asset.dto.ProjectInfo;
import com.cana.vbam.common.credit.enums.CreditTransferStatus;
import com.cana.vbam.common.credit.enums.Institution;
import com.cana.vbam.common.dto.PageResult;
import com.cana.vbam.common.member.dto.user.CustomerDetailDTO;
import com.cana.vbam.common.member.dto.user.UserSessionDTO;
import com.cana.vbam.common.member.enums.user.UserType;
import com.cana.vbam.common.repayment.dto.CreateLoanRequest;
import com.cana.vbam.common.repayment.dto.LoanInfoDetail;
import com.cana.vbam.common.repayment.dto.QueryLoanInfoListRequestDTO;
import com.cana.vbam.common.repayment.dto.QueryLoanInfoListResponseDTO;
import com.cana.vbam.common.repayment.enums.ChargeMethod;
import com.cana.vbam.common.repayment.enums.DateUnit;
import com.cana.vbam.common.repayment.enums.InterestRateUnit;
import com.cana.vbam.common.repayment.enums.RepaymentType;
import com.cana.vbam.common.repayment.rule.enums.DeductionRule;
import com.cana.vbam.common.service.IVbamCommonService;
import com.cana.vbam.common.utils.MoneyArithUtil;
import com.cana.vbam.common.yundaex.dto.apply.YdCustomerApplyDetailDTO;
import com.cana.vbam.common.yundaex.dto.loanApply.YundaexLoanApplyQueryDTO;
import com.cana.vbam.common.yundaex.dto.loanInfo.YundaexLoanFlowListDTO;
import com.cana.vbam.common.yundaex.dto.loanInfo.YundaexLoanFlowQueryDTO;
import com.cana.yundaex.common.dto.InterestRateDTO;
import com.cana.yundaex.common.dto.YundaexLoanInfoListRequest;
import com.cana.yundaex.common.dto.YundaexLoanInfoListResponse;
import com.cana.yundaex.common.dto.YundaexLoanInfoResponse;
import com.cana.yundaex.common.enums.RepaymentMethod;
import com.cana.yundaex.common.enums.YundaexCreditLimitAuditType;
import com.cana.yundaex.common.enums.YundaexTransferType;
import com.cana.yundaex.common.util.Constants;
import com.cana.yundaex.dao.mapper.YundaexLoanFlowMapper;
import com.cana.yundaex.dao.mapper.gen.YundaexCreditTransferMapper;
import com.cana.yundaex.dao.mapper.gen.YundaexLoanInfoRecordMapper;
import com.cana.yundaex.dao.mapper.gen.YundaexOutCustomerMapper;
import com.cana.yundaex.dao.po.ContractSignSituation;
import com.cana.yundaex.dao.po.YundaexCreditTransfer;
import com.cana.yundaex.dao.po.YundaexLoanInfoRecord;
import com.cana.yundaex.dao.po.YundaexOutCustomer;
import com.cana.yundaex.dao.po.YundaexOutCustomerExample;
import com.cana.yundaex.dao.utils.IDGenerator;
import com.cana.yundaex.service.IYundaexAuditService;
import com.cana.yundaex.service.IYundaexContractService;
import com.cana.yundaex.service.IYundaexCreditLimitAuditService;
import com.cana.yundaex.service.IYundaexCreditService;
import com.cana.yundaex.service.IYundaexCreditTradeService;
import com.cana.yundaex.service.IYundaexInterestRateService;
import com.cana.yundaex.service.IYundaexLoanInfoRecordService;
import com.cana.yundaex.service.IYundaexMessageService;
import com.cana.yundaex.service.IYundaexRetryTaskService;
import com.cana.yundaex.service.constant.Constant;
import com.cana.yundaex.service.convertors.YundaexLoanApplyConvert;
import com.cana.yundaex.service.transaction.IYundaexLoanApplyService;
import com.cana.yundaex.service.utils.YundaexTransferThreadPoolExecutor;
import com.dianping.cat.Cat;
import com.travelzen.framework.core.common.ReturnCode;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.core.util.MoneyUtil;
import com.travelzen.framework.util.DateUtils;
import com.travelzen.tops.mediaserver.client.MediaClientUtil;
import com.travelzen.tops.mediaserver.client.MediaClientUtil.MediaType;

/**
 * 韵达项目--用款申请
 * 
 * @author guguanggong
 *
 */
@Service
public class YundaexLoanApplyServiceImpl implements IYundaexLoanApplyService {

	@Resource
	private IAssetApi assetApi;

	@Resource
	private IAssetProjectManageApi assetProjectManageApi;

	@Resource
	private IAccountApi accountApiImpl;

	@Resource
	private IYundaexCreditTradeService yundaexCreditTradeService;

	@Resource
	private YundaexCreditTransferMapper yundaexCreditTransferMapper;

	@Resource
	private YundaexLoanInfoRecordMapper yundaexLoanInfoRecordMapper;

	@Resource
	private YundaexTransferThreadPoolExecutor yundaexTransferThreadPoolExecutor;

	@Resource
	private IYundaexLoanInfoRecordService yundaexLoanInfoRecordService;

	@Resource
	private IRepaymentTransactionService repaymentTransactionService;

	@Resource
	private CreditLimitMapper creditLimitMapper;

	@Resource
	private IYundaexCreditLimitAuditService yundaexCreditLimitAuditService;

	@Resource
	private IYundaexRetryTaskService yundaexRetryTaskService;

	@Resource
	private IYundaexCreditService yundaexCreditService;

	@Resource
	private IYundaexContractService yundaexContractService;
	
	@Resource
	private YundaexLoanFlowMapper yundaexLoanFlowMapper;
	
	@Resource
	private YundaexOutCustomerMapper yundaexOutCustomerMapper;
	
	@Resource
	private IYundaexAuditService  yundaexAuditService;

	@Resource
	private IFinanceReportApi financeReportApi;
	
	@Resource
	private IYundaexMessageService yundaexMessageService; 
	
	@Resource
	private IYundaexInterestRateService yundaexInterestRateService;
	
	@Resource
	private IVbamCommonService vbamCommonService;
	
	@Resource
	private IUserApi userApi; 
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	private final String catLoanInfoSuccess = "放款记录生成成功";
	
	private final String catLoanInfoError = "放款记录生成失败";
	
	private final String catSuccess = "放款成功";

	private final String catFail = "转账实体卡失败";
	
	private final String catError = "放款失败";

	/**
	 * 放款申请
	 * 
	 * @throws Exception
	 */
	@Override
	public void creditLoanApply(YundaexLoanApplyQueryDTO yundaexLoanApplyQueryDTO, UserSessionDTO userSessionDTO) throws Exception {
		try {
			// 处理用款申请页面数据
			YundaexLoanApplyQueryDTO loanApplyQueryDTO = setLoanInfo(yundaexLoanApplyQueryDTO, userSessionDTO);
			
			// 拿到用户ID
			String masterId = getMasterId(userSessionDTO);
	
			// 项目信息
			ProjectInfo projectInfo = assetProjectManageApi.getProjectInfo(Constants.YUNDAEX_ASSET_PROJECT_ID);
			
			// 合同信息
			ContractInfoDTO contractInfoDTO = assetApi.getContractInfoByProtocolNo(loanApplyQueryDTO.getProtocolNo());
	
			// 1.检验数据
			checkLoanApply(loanApplyQueryDTO, projectInfo, contractInfoDTO, masterId);
	
			// 2.將word合同文件上传到媒体服务器并保存到合同表
			upload2MediaAndCreateContract(yundaexLoanApplyQueryDTO, projectInfo, masterId);
			
			// 3.提交放款记录
			String businessSeq = IDGenerator.generateCreditTransferBusinessSeq();
			YundaexLoanInfoRecord loanInfoRecord = insertYundaexLoanInfoRecord(loanApplyQueryDTO, projectInfo,
					contractInfoDTO, userSessionDTO, businessSeq);
	
			// 4.提交转账记录表
			YundaexCreditTransfer transfer = insertYundaexCreditTransfer(loanApplyQueryDTO, contractInfoDTO,
					projectInfo, loanInfoRecord.getId(), masterId, businessSeq);
	
			// 5.占用额度（韵达额度列表）
			String financeId = masterId; // 融资客户信息
			Long financeAmount = loanApplyQueryDTO.getPaymentFee(); // 融资金额
			CreditLimit creditLimit = yundaexCreditService.getCreditLimitByMemberId(financeId);
			Long newUsedLimit = updateCreditLimit(creditLimit, financeAmount, YundaexCreditLimitAuditType.OCCUPY.name()); // 修改额度
	
			// 6.新增额度变化（韵达额度变化表）
			insertCreditLimitAudit(creditLimit, newUsedLimit, loanInfoRecord, YundaexCreditLimitAuditType.OCCUPY.name());
	
			// 7.转账
			yundaexTransferThreadPoolExecutor.doTransfer(transfer);
			
			Cat.logMetricForCount(catLoanInfoSuccess);
		} catch(Exception e) {
			logger.error("转账实体卡错误", e);
			Cat.logMetricForCount(catLoanInfoError);
			throw e;
		}
	}
	
	/**
	 * 转账实体卡成功，完成放款信息、还款计划等
	 */
	@Override
	public void executeLoanTask() {
		try {
			// 查询交易中的实体卡转账记录
			List<YundaexCreditTransfer> yundaexCreditTransfers = yundaexCreditTradeService.getHandlingCreditTrade();
			for (YundaexCreditTransfer yundaexCreditTransfer : yundaexCreditTransfers) {
				String status = accountApiImpl.queryTradeStatus(yundaexCreditTransfer.getBusinessSeq());
				if (AccountTradeStatus.TRADE_SUCCESS.name().equals(status)) {
					logger.info("转账实体卡成功，进行放款信息录入");
					// 修改放款记录中的放款状态
					yundaexCreditTradeService.updateCreditTransferStatus(yundaexCreditTransfer, CreditTransferStatus.SUCCESS);
					// 处理放款流程
					loanProcess(yundaexCreditTransfer.getBusinessSeq());
					Cat.logMetricForCount(catSuccess);
				} else if (AccountTradeStatus.TRADE_FAIL.name().equals(status)
						|| (ReturnCode.NOT_FOUND.getRetCode().equals(status) && (new Date().getTime()
								- yundaexCreditTransfer.getTransferStartTime().getTime()) > Constant.transferTimeout)) {
					logger.info("转账实体卡失败，进行额度恢复");
					// 修改放款记录中的放款状态
					yundaexCreditTradeService.updateCreditTransferStatus(yundaexCreditTransfer, CreditTransferStatus.FAIL);
					// 恢复额度
					recoveryCreditLimit(yundaexCreditTransfer.getBusinessSeq());
					Cat.logMetricForCount(catFail);
				}
			}
		} catch (Exception e) {
			logger.error("转账实体卡错误", e);
			Cat.logMetricForCount(catError);
		}
	}
	
	/**
	 * 获取申请用款流水列表
	 * @return
	 */
	@Override
	public PageResult<YundaexLoanFlowListDTO> queryCreditFlowList(YundaexLoanFlowQueryDTO loanFlowQueryDTO, String memberId) {
		PageResult<YundaexLoanFlowListDTO> pageResult = new PageResult<YundaexLoanFlowListDTO>();
		
		// 拿到页面查询DTO
		YundaexLoanFlowQueryDTO queryDTO = getQueryDTO(loanFlowQueryDTO, memberId);
		
		// 查询页面数据
		List<YundaexLoanFlowListDTO> yundaexLoanFlowListDTOs = yundaexLoanFlowMapper.getYundaexLoanFlowList(queryDTO);
		int total = yundaexLoanFlowMapper.getYundaexLoanFlowCount(queryDTO);
		
		// 页面数据格式化
		for (YundaexLoanFlowListDTO yundaexLoanFlowListDTO : yundaexLoanFlowListDTOs) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			try {
				date = sdf.parse(yundaexLoanFlowListDTO.getCreateTime());
			} catch (ParseException e) {
				logger.info("日期格式化错误", e);
			}
			String createTime = DateUtils.format(date, 14);
			yundaexLoanFlowListDTO.setCreateTime(createTime);
			String fee = MoneyUtil.formatMoney(yundaexLoanFlowListDTO.getFee() == null ? 0 : yundaexLoanFlowListDTO.getFee());
			yundaexLoanFlowListDTO.setLoanAmt(fee);
		}
		pageResult.setData(yundaexLoanFlowListDTOs);
		pageResult.setTotal(total);
		return pageResult;
	}

	/**
	 * 放款信息查询接口
	 */
	@Override
	public YundaexLoanInfoListResponse getYundaexLoanInfoList(YundaexLoanInfoListRequest yundaexLoanInfoListRequest) throws Exception {
		// 检验接口数据
		checkLoanInfoRequestParam(yundaexLoanInfoListRequest);
		
		// 对接口数据进入验签
		signVerify(yundaexLoanInfoListRequest);
		
		// 根据站点编号拿到平台客户编号
		String stationNo = yundaexLoanInfoListRequest.getStationNo();
		String memberId = getUserMemberId(stationNo);
		
		List<YundaexLoanInfoResponse> yundaexLoanInfoResponses = getYdLoanInfos(memberId, yundaexLoanInfoListRequest);
		int count = 0;
		if (CollectionUtils.isNotEmpty(yundaexLoanInfoResponses)) {
			count = yundaexLoanInfoResponses.size();
		} 
		
		// 组成接口响应字段
		YundaexLoanInfoListResponse response = new YundaexLoanInfoListResponse();
		response.setStationNo(stationNo);
		response.setLoanInfo(yundaexLoanInfoResponses);
		response.setCount(String.valueOf(count));
		return response;
	}
	
	/**
	 * 处理放款流程
	 * 
	 * @param businessSeq
	 * @throws Exception
	 */
	private void loanProcess(String businessSeq) throws Exception {
		// 1.查询放款记录
		YundaexLoanInfoRecord yundaexLoanInfoRecord = yundaexLoanInfoRecordService.getYundaexLoanInfoRecord(businessSeq);

		// 2.保存融资信息、还款计划（融资模块）
		RepaymentLoanInfoBO info = saveLoanInfo(yundaexLoanInfoRecord);

		// 3.通知韵达成功（放款结果接口）
		String financeId = yundaexLoanInfoRecord.getFinanceId(); // 融资客户信息
		String taskId = yundaexLoanInfoRecord.getId();
		YdCustomerApplyDetailDTO customerApplyDetailDTO = yundaexAuditService.getUserBaseInfo(financeId);
		if (customerApplyDetailDTO == null) {
			throw WebException.instance("客户申请信息不存在");
		}
		yundaexRetryTaskService.createCreditTradeResult(financeId, info, taskId, customerApplyDetailDTO);
		
		// 4.发送短信\邮件通知
		CustomerDetailDTO customerDetailDTO = userApi.queryCustomerDetail(financeId);
		yundaexMessageService.sendLoanApplyMailAndSMS(info, customerApplyDetailDTO, customerDetailDTO);
	}
	
	/**
	 * 1.检验用款申请记录
	 * @param yundaexLoanApplyQueryDTO
	 * @param projectDTO
	 * @param contractInfoDTO
	 * @param masterId
	 */
	private void checkLoanApply(YundaexLoanApplyQueryDTO yundaexLoanApplyQueryDTO, ProjectInfo projectDTO,
			ContractInfoDTO contractInfoDTO, String masterId) {
		if (!(DateUtils.checkTimeInterval(9, 0, 17, 0) && DateUtils.checkWeekDay())) {
			logger.info("当前时间不能进行用款申请，当前时间{}", DateUtils.format(new Date(), 1));
			throw WebException.instance("当前时间不能进行用款申请");
		}
		if (yundaexLoanApplyQueryDTO == null) {
			logger.info("用款申请信息不存在");
			throw WebException.instance("用款申请信息不存在");
		}
		if (projectDTO == null) {
			logger.info("用款申请-项目信息不存在");
			throw WebException.instance("用款申请信息不存在");
		}
		if (contractInfoDTO == null) {
			logger.info("用款申请-应收款债权转让协议不存在");
			throw WebException.instance("用款申请-应收款债权转让协议不存在");
		}

		if (yundaexLoanApplyQueryDTO.getPaymentFee() == null || yundaexLoanApplyQueryDTO.getPaymentFee() <= 0) {
			logger.info("用款申请-申请放款金额不能填写为0");
			throw WebException.instance("用款申请-申请放款金额不能填写为0");
		}

		// 检查额度是否足够
		checkLimitBalanceEnough(masterId, yundaexLoanApplyQueryDTO.getPaymentFee());
		logger.info("用款申请检验通过");
	}

	/**
	 * 2.提交转账记录表
	 * 
	 * @param yundaexLoanApplyDTO
	 * @param projectDTO
	 * @param businessSeq
	 */
	private YundaexCreditTransfer insertYundaexCreditTransfer(YundaexLoanApplyQueryDTO yundaexLoanApplyQueryDTO,
			ContractInfoDTO contractInfoDTO, ProjectInfo projectInfo, String loanId, String masterId,
			String businessSeq) {
		FactorInfo factorInfo = projectInfo.getFactors().get(0); // 资金方信息
		ContractSignSituation contractSignSituation = yundaexContractService.getContractSituationByCusId(masterId);
		if (contractSignSituation == null) {
			logger.info("用款申请-客户{}合同签约情况不存在", masterId);
			throw WebException.instance("用款申请-合同签约情况不存在");
		}

		YundaexCreditTransfer transfer = new YundaexCreditTransfer();
		transfer.setId(IDGenerator.generateCreditTransferId());
		transfer.setBusinessSeq(businessSeq);
		transfer.setLoanId(loanId); // 放款ID
		transfer.setTransferStatus(CreditTransferStatus.HANDING.name()); // 转账状态
		transfer.setFee(yundaexLoanApplyQueryDTO.getPaymentFee()); // 转账金额
		transfer.setTransferType(YundaexTransferType.LOAD.name());
		transfer.setFromAccountNo(factorInfo.getAccountNo()); // 转出账户名称
		transfer.setFromAccountName(factorInfo.getCompanyName()); // 转出账号
		transfer.setToAccountNo(contractSignSituation.getPayAccountNo()); // 转入账号
		transfer.setToAccountName(contractSignSituation.getPayAccountName()); // 转入账户名
		transfer.setToAccountAddress(contractSignSituation.getPayAccountBank()); // 转入行地址
		transfer.setToLianHangNo(contractSignSituation.getPayLianHangNo()); // 联行号
		transfer.setOperatorId(masterId);
		transfer.setTransferStartTime(new Date());
		yundaexCreditTransferMapper.insertSelective(transfer);
		return transfer;
	}

	/**
	 * 3.提交放款记录
	 * 
	 * @param contractInfoDTO
	 * 
	 * @param yundaexLoanApplyDTO
	 * @param userSessionDTO
	 * @param businessSeq
	 * @return
	 */
	private YundaexLoanInfoRecord insertYundaexLoanInfoRecord(YundaexLoanApplyQueryDTO yundaexLoanApplyQueryDTO,
			ProjectInfo projectInfo, ContractInfoDTO contractInfoDTO, UserSessionDTO userSessionDTO, String businessSeq) {
		if (projectInfo == null) {
			throw WebException.instance("合同信息不存在");
		}
		if (CollectionUtils.isEmpty(projectInfo.getFactors())) {
			throw WebException.instance("资金方信息不存在");
		}

		String accountNo = contractInfoDTO.getAccountNo(); // 监管帐户

		YundaexLoanInfoRecord record = YundaexLoanApplyConvert.convertYundaexLoanApply2LoanInfoRecord(
				yundaexLoanApplyQueryDTO, projectInfo, contractInfoDTO, userSessionDTO, businessSeq, accountNo);
		yundaexLoanInfoRecordMapper.insertSelective(record);

		logger.info("用款申请记录保存成功");
		return record;
	}

	/**
	 * 判断额度余额是否足够（额度余额=总额度-已用额度）
	 * 
	 * @param memberId
	 * @param fee 交易金额
	 * @return
	 */
	public void checkLimitBalanceEnough(String memberId, Long fee) {
		CreditLimit yundaexCreditLimit = yundaexCreditService.getCreditLimitByMemberId(memberId);
		Long totalLimit = yundaexCreditLimit.getTotalLimit();
		Long usedLimit = fee == null ? 0 : yundaexCreditLimit.getUsedLimit();
		if (totalLimit == null || totalLimit - usedLimit < fee) {
			logger.info("客户{}授信余额不足", memberId);
			throw WebException.instance("可用额度余额不足");
		}

	}

	/**
	 * 恢复额度
	 * 
	 * @param businessSeq
	 */
	private void recoveryCreditLimit(String businessSeq) {
		// 1.查询放款记录
		YundaexLoanInfoRecord yundaexLoanInfoRecord = yundaexLoanInfoRecordService
				.getYundaexLoanInfoRecord(businessSeq);
		// 4.占用额度（韵达额度列表）
		String financeId = yundaexLoanInfoRecord.getFinanceId(); // 融资客户信息
		Long financeAmount = yundaexLoanInfoRecord.getFinanceAmount(); // 融资金额
		CreditLimit creditLimit = yundaexCreditService.getCreditLimitByMemberId(financeId);
		Long newUsedLimit = updateCreditLimit(creditLimit, financeAmount,
				YundaexCreditLimitAuditType.ERROROCCUPY.name()); // 修改额度

		// 5.新增额度变化（韵达额度变化表）
		insertCreditLimitAudit(creditLimit, newUsedLimit, yundaexLoanInfoRecord,
				YundaexCreditLimitAuditType.ERROROCCUPY.name());

	}

	/**
	 * 保存融资信息
	 * 
	 * @param yundaexLoanInfoRecord
	 * @return
	 * @throws Exception
	 */
	private RepaymentLoanInfoBO saveLoanInfo(YundaexLoanInfoRecord yundaexLoanInfoRecord) throws Exception {
		CreateLoanRequest payInfo = new CreateLoanRequest();
		BeanUtils.copyProperties(yundaexLoanInfoRecord, payInfo);
		payInfo.setInterestRateUnit(InterestRateUnit.getValue(yundaexLoanInfoRecord.getInterestRateUnit()));
		payInfo.setInstitutionName("韵达平台");
		payInfo.setLoanPeriod(Integer.valueOf(yundaexLoanInfoRecord.getLoanPeriod()));
		payInfo.setLoanPeriodUnit(DateUnit.getValue(yundaexLoanInfoRecord.getLoanPeriodUnit()));
		payInfo.setProductId(yundaexLoanInfoRecord.getBusinessProductId());
		payInfo.setProductName(yundaexLoanInfoRecord.getBusinessProduct());
		payInfo.setRepaymentMethod(RepaymentType.getValue(yundaexLoanInfoRecord.getRepaymentMethod()));
		payInfo.setInterestRateUnit(InterestRateUnit.getValue(yundaexLoanInfoRecord.getInterestRateUnit()));
		payInfo.setInterestRate(yundaexLoanInfoRecord.getInterestRate());
		
		payInfo.setFactorTransferInAccountNo(yundaexLoanInfoRecord.getFactorTransferInAccountNo()); // 保理商回款账号
		payInfo.setDeductionTime(yundaexLoanInfoRecord.getDeductionTime()); // 保理商指定账扣时间
		payInfo.setDeductionRule(DeductionRule.valueOf(yundaexLoanInfoRecord.getDeductionRule())); // 扣款规则
		payInfo.setExtensionDays(yundaexLoanInfoRecord.getExtensionDays()); // 展期天数
		payInfo.setExtensionRatio(yundaexLoanInfoRecord.getExtensionRatio()); // 展期上浮利率值 或者是 展期上浮比例
		payInfo.setExtensionChargeMethod(ChargeMethod.valueOf(yundaexLoanInfoRecord.getExtensionChargeMethod())); // 展期率计算方式
		payInfo.setEarlyRepaymentChargeRatio(yundaexLoanInfoRecord.getEarlyRepaymentChargeRatio()); // 提前还款手续费率
		payInfo.setUseHolidayPolicy(BooleanUtils.isTrue(yundaexLoanInfoRecord.getUseHolidayPolicy())); // 是否使用节假日
		payInfo.setPenaltyRatio(yundaexLoanInfoRecord.getPenaltyRate()); // 罚息率或者是 罚息上浮比例
		payInfo.setPenaltyChargeMethod(ChargeMethod.valueOf(yundaexLoanInfoRecord.getChargeMethod())); // 罚息计算方式
		
		RepaymentLoanInfoBO repaymentLoanInfoBO = repaymentTransactionService.createLoan(payInfo);
		return repaymentLoanInfoBO;
	}

	/**
	 * 更新该记录的授信额度
	 * 
	 * @param creditLimit
	 * @param fee 交易金额
	 * @param loanType 占用、还款标记
	 * @return
	 */
	private Long updateCreditLimit(CreditLimit creditLimit, Long financeAmount, String loanType) {
		Long newUsedLimit = creditLimit.getUsedLimit() == null ? 0 : creditLimit.getUsedLimit();
		if (YundaexCreditLimitAuditType.OCCUPY.name().equals(loanType)) {
			newUsedLimit = (creditLimit.getUsedLimit() == null ? 0 : creditLimit.getUsedLimit()) + financeAmount; // 可用余额
		} else {
			newUsedLimit = (creditLimit.getUsedLimit() == null ? 0 : creditLimit.getUsedLimit()) - financeAmount; // 可用余额
		}
		logger.info("客户{}的新额度是{}", creditLimit.getMemberId(), newUsedLimit);
		creditLimit.setUsedLimit(newUsedLimit);
		creditLimit.setUpdateTime(new Date());
		creditLimitMapper.updateByPrimaryKeySelective(creditLimit);
		return newUsedLimit;
	}

	/**
	 * 新增额度变化
	 * 
	 * @param newUsedLimit
	 * @param info
	 * @param yundaexLoanInfoRecord
	 */
	private void insertCreditLimitAudit(CreditLimit yundaexCreditLimit, Long newUsedLimit,
			YundaexLoanInfoRecord loanInfoRecord, String loanType) {
		CreditLimitAudit yundaexCreditLimitAudit = YundaexLoanApplyConvert
				.convertCreditLimit2CreditLimitAudit(yundaexCreditLimit, newUsedLimit, loanInfoRecord, loanType);
		yundaexCreditLimitAuditService.insertYunadexCreditLimitAudit(yundaexCreditLimitAudit);
	}

	/**
	 * 检查接口接收数据
	 * @param request
	 * @throws Exception 
	 */
	private void checkLoanInfoRequestParam(YundaexLoanInfoListRequest request) throws Exception {
		if (StringUtils.isBlank(request.getStationNo())) {
			throw WebException.instance(ReturnCode.YP7002);
		}
		if (StringUtils.isNotBlank(request.getStartBeginDate()) && StringUtils.isBlank(request.getEndBeginDate())) {
			throw WebException.instance(ReturnCode.YP8001);
		}

		if (StringUtils.isBlank(request.getStartBeginDate()) && StringUtils.isNotBlank(request.getEndBeginDate())) {
			throw WebException.instance(ReturnCode.YP8002);
		}

		if (StringUtils.isNotBlank(request.getStartExpireDate()) && StringUtils.isBlank(request.getEndExpireDate())) {
			throw WebException.instance(ReturnCode.YP8003);
		}

		if (StringUtils.isBlank(request.getStartExpireDate()) && StringUtils.isNotBlank(request.getEndExpireDate())) {
			throw WebException.instance(ReturnCode.YP8004);
		}

		if (StringUtils.isBlank(request.getStartBeginDate()) && StringUtils.isBlank(request.getStartExpireDate())
				&& StringUtils.isBlank(request.getEndBeginDate()) && StringUtils.isBlank(request.getEndExpireDate())) {
			throw WebException.instance(ReturnCode.YP8005);
		}
		
		if (StringUtils.isNotBlank(request.getStartBeginDate()) && StringUtils.isNotBlank(request.getEndBeginDate())) {
			int ret = DateUtils.compareDate(request.getStartBeginDate(), request.getEndBeginDate());
			if (ret > 0) {
				throw WebException.instance(ReturnCode.YP8008);
			}
		}
		
		if (StringUtils.isNotBlank(request.getStartExpireDate()) && StringUtils.isNotBlank(request.getEndExpireDate())) {
			int ret = DateUtils.compareDate(request.getStartExpireDate(), request.getEndExpireDate());
			if (ret > 0) {
				throw WebException.instance(ReturnCode.YP8009);
			}
		}
		
		if (StringUtils.isNotBlank(request.getStartBeginDate()) && StringUtils.isNotBlank(request.getEndBeginDate())) {
			int month = DateUtils.getMonthCompare(request.getStartBeginDate(), request.getEndBeginDate(), 2);
			if (month < 0) {
				throw WebException.instance(ReturnCode.YP8006);
			}
		}
		
		if (StringUtils.isNotBlank(request.getStartExpireDate()) && StringUtils.isNotBlank(request.getEndExpireDate())) {
			int month = DateUtils.getMonthCompare(request.getStartExpireDate(), request.getEndExpireDate(), 2);
			if (month < 0) {
				throw WebException.instance(ReturnCode.YP8007);
			}
		}
	}
	
	/**
	 * 对接口数据进入验签
	 * @param yundaexLoanInfoListRequest
	 */
	private void signVerify(YundaexLoanInfoListRequest yundaexLoanInfoListRequest) {
		StringBuffer loanBuf = new StringBuffer();
		loanBuf.append(yundaexLoanInfoListRequest.getStationNo())
				.append(StringUtils.isBlank(yundaexLoanInfoListRequest.getStartBeginDate()) ? "" : yundaexLoanInfoListRequest.getStartBeginDate())
				.append(StringUtils.isBlank(yundaexLoanInfoListRequest.getEndBeginDate()) ? "" : yundaexLoanInfoListRequest.getEndBeginDate())
				.append(StringUtils.isBlank(yundaexLoanInfoListRequest.getStartExpireDate()) ? "" : yundaexLoanInfoListRequest.getStartExpireDate())
				.append(StringUtils.isBlank(yundaexLoanInfoListRequest.getEndExpireDate()) ? "" : yundaexLoanInfoListRequest.getEndExpireDate());
		vbamCommonService.rsaVerify(loanBuf.toString().getBytes(), Institution.cana.name(), yundaexLoanInfoListRequest.getSign().getBytes(), true);
	}
	
	/**
	 * 根据站点编号拿到平台用户ID
	 * @param stationNo
	 * @return
	 */
	private String getUserMemberId(String stationNo) {
		YundaexOutCustomerExample ydOutCustomerExample = new YundaexOutCustomerExample();
		ydOutCustomerExample.createCriteria().andStationNoEqualTo(stationNo);
		List<YundaexOutCustomer> ydOutCustomers = yundaexOutCustomerMapper.selectByExample(ydOutCustomerExample);
		if(CollectionUtils.isEmpty(ydOutCustomers)) {
			throw WebException.instance(ReturnCode.YP7016);
		}
		if (ydOutCustomers.size() > 1) {
			throw WebException.instance(ReturnCode.YP7031);
		}
		
		String memberId = ydOutCustomers.get(0).getMemberId();
		return memberId;
	}
	
	/**
	 * 查询放款信息列表
	 */
	private List<YundaexLoanInfoResponse> getYdLoanInfos(String memberId, YundaexLoanInfoListRequest yundaexLoanInfoListRequest) throws Exception {
		YdCustomerApplyDetailDTO applyDetailDTO = yundaexAuditService.getUserBaseInfo(memberId);
		QueryLoanInfoListRequestDTO requestDto = new QueryLoanInfoListRequestDTO();
		
		requestDto.setFinanceId(memberId);
		requestDto.setBusinessProductId(Constants.YUNDAEX_ASSET_PROJECT_ID);
		requestDto.setLoanDateStart(yundaexLoanInfoListRequest.getStartBeginDate());
		requestDto.setLoanDateEnd(yundaexLoanInfoListRequest.getEndBeginDate());
		requestDto.setDueDateStart(yundaexLoanInfoListRequest.getStartExpireDate());
		requestDto.setDueDateEnd(yundaexLoanInfoListRequest.getEndExpireDate());
		requestDto.setOrderByClause(null); // 不进入分页
		QueryLoanInfoListResponseDTO queryLoanInfoListResponseDTO = financeReportApi.queryLoanInfoList(requestDto);
		
		List<YundaexLoanInfoResponse> yundaexLoanInfoResponses =new ArrayList<YundaexLoanInfoResponse>();
		for (LoanInfoDetail info : queryLoanInfoListResponseDTO.getLoanInfoDetailList()) {
			YundaexLoanInfoResponse yundaexLoanInfoResponse = new YundaexLoanInfoResponse();
			yundaexLoanInfoResponse.setStationNo(applyDetailDTO.getStationNo());
			yundaexLoanInfoResponse.setStationName(applyDetailDTO.getStationName());
			yundaexLoanInfoResponse.setCustName(applyDetailDTO.getCustName());
			yundaexLoanInfoResponse.setCustIdno(applyDetailDTO.getCustIdno());
			yundaexLoanInfoResponse.setPutoutno(info.getLoanNo()); // 放款编号
			yundaexLoanInfoResponse.setPutoutamt(info.getFinanceAmount()); // 放款金额
			yundaexLoanInfoResponse.setBegindate(info.getLoanDate()); // 放款日
			yundaexLoanInfoResponse.setEnddate(info.getDueDate()); // 到期日
			yundaexLoanInfoResponses.add(yundaexLoanInfoResponse);
		}
		return yundaexLoanInfoResponses;
	}
	
	/**
	 * 拿到平台用户
	 * @param userSessionDTO
	 * @return
	 */
	private String getMasterId(UserSessionDTO userSessionDTO){
		String masterId = "";
		if (userSessionDTO == null) {
			throw WebException.instance("系统错误");
		}
		if (userSessionDTO.getUserType() != UserType.FINACE) {
			throw WebException.instance("该用户不能操作用款申请");
		} else {
			masterId = StringUtils.isBlank(userSessionDTO.getMasterId()) ? userSessionDTO.getId() : userSessionDTO.getMasterId();
		}
		return masterId;
	}
	
	/**
	 * 页面查询DTO
	 * @param loanFlowQueryDTO
	 * @param memberId
	 * @return
	 */
	private YundaexLoanFlowQueryDTO getQueryDTO(YundaexLoanFlowQueryDTO loanFlowQueryDTO, String memberId) {
		YundaexLoanFlowQueryDTO queryDTO = new YundaexLoanFlowQueryDTO();
		if (StringUtils.isNotBlank(loanFlowQueryDTO.getCustomerName())) {
			queryDTO.setFinanceCompany(loanFlowQueryDTO.getCustomerName());
		}
		if (StringUtils.isNotBlank(loanFlowQueryDTO.getBusinessSeq())) {
			queryDTO.setBusinessSeq(loanFlowQueryDTO.getBusinessSeq());
		}
		if (StringUtils.isNotBlank(loanFlowQueryDTO.getStartDate())) {
			queryDTO.setStartDate(loanFlowQueryDTO.getStartDate());
		}
		if (StringUtils.isNotBlank(loanFlowQueryDTO.getEndDate())) {
			queryDTO.setEndDate(loanFlowQueryDTO.getEndDate());
		}
		if (StringUtils.isNotBlank(loanFlowQueryDTO.getTradeStatus())) {
			queryDTO.setTradeStatus(loanFlowQueryDTO.getTradeStatus());
		}
		if (StringUtils.isNotBlank(memberId)) {
			queryDTO.setOperatorId(memberId);
		}
		queryDTO.setPage(loanFlowQueryDTO.getPage());
		queryDTO.setPageSize(loanFlowQueryDTO.getPageSize());
		return queryDTO;
	}
	
	/**
	 * 处理用款申请页面数据
	 * @param applyQueryDTO
	 * @param userSessionDTO
	 * @return
	 */
	private YundaexLoanApplyQueryDTO setLoanInfo(YundaexLoanApplyQueryDTO applyQueryDTO, UserSessionDTO userSessionDTO) {
		String proid = applyQueryDTO.getProId();
		InterestRateDTO rate = yundaexInterestRateService.getInterestRateById(proid);
		BigDecimal interestRate = rate.getInterestRate().multiply(new BigDecimal(30));
		
		applyQueryDTO.setLoanPeriod(RepaymentMethod.getValues(rate.getRepaymentMethod()).deadLine().desc());
		applyQueryDTO.setLoanPeriodUnit(RepaymentMethod.getValues(rate.getRepaymentMethod()).deadLine().unit());
		applyQueryDTO.setInterestRate(interestRate);
		applyQueryDTO.setInterestRateUnit(InterestRateUnit.MONTH.desc());
		applyQueryDTO.setRepaymentMethod(RepaymentType.valueOf(rate.getRepaymentMethod()).desc());
		
		applyQueryDTO.setLoanDate(DateUtils.formatDate(new Date()));
		
		// 生成放款日 和 到期日
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.MONTH, Integer.valueOf(applyQueryDTO.getLoanPeriod()));
		String dueDate = DateUtils.findFormat(3).format(calendar.getTime());
		applyQueryDTO.setDueDate(dueDate);
		String loanAmt = applyQueryDTO.getLoanAmt();
		if (StringUtils.isNotBlank(loanAmt)) {
			applyQueryDTO.setPaymentFee(MoneyArithUtil.convertStringToMoney(MoneyUtil.parseMoney(loanAmt)));
		}
		return applyQueryDTO;
	}	
	
	/**
	 * 將word合同文件上传到媒体服务器并保存到合同表
	 * @param contractId
	 * @param wordContent
	 * @throws IOException 
	 * @throws Exception
	 */
	private void upload2MediaAndCreateContract(YundaexLoanApplyQueryDTO yundaexLoanApplyQueryDTO, ProjectInfo projectInfo,String masterId) throws Exception {
		FactorInfo factorInfo = projectInfo.getFactors().get(0); // 资金方信息
		String contractId = yundaexLoanApplyQueryDTO.getContractId();
		byte[] wordContent = yundaexLoanApplyQueryDTO.getWordContent();
		String factorId = factorInfo.getCompanyId();
		String fileName = "单笔融资合同-" + DateUtils.format(new Date(), 15);
		String fileSuffix = "doc";
        String mediaId = MediaClientUtil.upload(wordContent,MediaType.IMAGE, fileName+"."+fileSuffix);
		
        ContractInfoDTO contractInfoDTO = new ContractInfoDTO();
		contractInfoDTO.setContractId(contractId);
		contractInfoDTO.setProductId(Constants.YUNDAEX_ASSET_PROJECT_ID);
		contractInfoDTO.setMemberId(masterId);
		contractInfoDTO.setFactorId(factorId);
		contractInfoDTO.setMediaId(mediaId);
		contractInfoDTO.setFileName(fileName);
		contractInfoDTO.setFileSuffix(fileSuffix);
		contractInfoDTO.setEffectiveDate(DateTimeUtil.getTodayStr());
		contractInfoDTO.setDueDate(DateTimeUtil.date10(DateTimeUtil.addYear(new DateTime(), Integer.parseInt(Constants.CONTRACT_DUE_PERIOD))));
		assetApi.createContract(contractInfoDTO);
	} 
	
}
