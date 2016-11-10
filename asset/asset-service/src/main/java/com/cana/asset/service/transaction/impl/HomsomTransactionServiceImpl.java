package com.cana.asset.service.transaction.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.cana.asset.dao.mapper.gen.BusinessBasicInfoMapper;
import com.cana.asset.dao.mapper.gen.BusinessCounterpartyMapper;
import com.cana.asset.dao.mapper.gen.LoanInfoMapper;
import com.cana.asset.dao.mapper.gen.LoanPaidMapper;
import com.cana.asset.dao.mapper.gen.LoanPlanMapper;
import com.cana.asset.dao.po.AssetInvoiceInfo;
import com.cana.asset.dao.po.BusinessBasicInfo;
import com.cana.asset.dao.po.BusinessBasicInfoExample;
import com.cana.asset.dao.po.BusinessCounterparty;
import com.cana.asset.dao.po.BusinessCounterpartyExample;
import com.cana.asset.dao.po.Credit;
import com.cana.asset.dao.po.LoanInfo;
import com.cana.asset.dao.po.LoanInfoExample;
import com.cana.asset.dao.po.LoanPaid;
import com.cana.asset.dao.po.LoanPlan;
import com.cana.asset.dao.po.LoanPlanExample;
import com.cana.asset.dao.utils.IDGenerator;
import com.cana.asset.service.transaction.IAssetCreditTransactionService;
import com.cana.asset.service.transaction.IAssetInvoiceTransactionService;
import com.cana.asset.service.transaction.IAssetLoanInfoTransactionService;
import com.cana.asset.service.transaction.IHomsomTransactionService;
import com.cana.homsom.dao.mapper.IHomsomTicketMapper;
import com.cana.homsom.dao.mapper.gen.HomsomBuybackCounterpartyMapper;
import com.cana.homsom.dao.mapper.gen.HomsomBuybackTicketMapper;
import com.cana.homsom.dao.mapper.gen.HomsomCounterpartyConfigMapper;
import com.cana.homsom.dao.mapper.gen.HomsomDailyLoanReportMapper;
import com.cana.homsom.dao.mapper.gen.HomsomDailyRefundReportMapper;
import com.cana.homsom.dao.mapper.gen.HomsomDailyReportTransferDetailMapper;
import com.cana.homsom.dao.mapper.gen.HomsomSettlementCounterpartyMapper;
import com.cana.homsom.dao.mapper.gen.HomsomSettlementTicketMapper;
import com.cana.homsom.dao.mapper.gen.HomsomSettlementTrackMapper;
import com.cana.homsom.dao.mapper.gen.HomsomTicketMapper;
import com.cana.homsom.dao.po.HomsomBuybackCounterparty;
import com.cana.homsom.dao.po.HomsomBuybackCounterpartyExample;
import com.cana.homsom.dao.po.HomsomBuybackTicket;
import com.cana.homsom.dao.po.HomsomBuybackTicketExample;
import com.cana.homsom.dao.po.HomsomCounterpartyConfig;
import com.cana.homsom.dao.po.HomsomCounterpartyConfigKey;
import com.cana.homsom.dao.po.HomsomDailyLoanReport;
import com.cana.homsom.dao.po.HomsomDailyLoanReportExample;
import com.cana.homsom.dao.po.HomsomDailyLoanReportKey;
import com.cana.homsom.dao.po.HomsomDailyRefundReport;
import com.cana.homsom.dao.po.HomsomDailyRefundReportKey;
import com.cana.homsom.dao.po.HomsomDailyReportTransferDetail;
import com.cana.homsom.dao.po.HomsomSettlementCounterparty;
import com.cana.homsom.dao.po.HomsomSettlementCounterpartyExample;
import com.cana.homsom.dao.po.HomsomSettlementTicket;
import com.cana.homsom.dao.po.HomsomSettlementTicketExample;
import com.cana.homsom.dao.po.HomsomSettlementTicketExample.Criteria;
import com.cana.homsom.dao.po.HomsomSettlementTrack;
import com.cana.homsom.dao.po.HomsomSettlementTrackExample;
import com.cana.homsom.dao.po.HomsomTicket;
import com.cana.homsom.dao.po.HomsomTicketExample;
import com.cana.homsom.dao.utils.HomsomIDGenerator;
import com.cana.vbam.common.asset.loan.dto.EditAssetLoanRequest;
import com.cana.vbam.common.dto.ObjectResult;
import com.cana.vbam.common.homsom.dto.CounterpartyConfigDTO;
import com.cana.vbam.common.homsom.dto.HomsomBuyBackTicketExcelDTO;
import com.cana.vbam.common.homsom.dto.HomsomBuyBackTicketExcelRedisDTO;
import com.cana.vbam.common.homsom.dto.HomsomSettlementTicketDTO;
import com.cana.vbam.common.homsom.dto.HomsomSettlementTicketExcelDTO;
import com.cana.vbam.common.homsom.dto.HomsomSettlementTicketExcelRedisDTO;
import com.cana.vbam.common.homsom.dto.SubmitSettlementRequestDTO;
import com.cana.vbam.common.homsom.enums.Channel;
import com.cana.vbam.common.homsom.enums.DailyReportTransferState;
import com.cana.vbam.common.homsom.enums.DailyReportType;
import com.cana.vbam.common.homsom.enums.LoanState;
import com.cana.vbam.common.homsom.enums.SettlementState;
import com.cana.vbam.common.homsom.enums.SettlementTrackState;
import com.cana.vbam.common.homsom.enums.TicketDetailState;
import com.cana.vbam.common.homsom.enums.TicketState;
import com.cana.vbam.common.member.vo.UserVo;
import com.cana.vbam.common.repayment.enums.Currency;
import com.cana.vbam.common.repayment.enums.DateUnit;
import com.cana.vbam.common.repayment.enums.InterestRateUnit;
import com.cana.vbam.common.repayment.enums.RepaymentType;
import com.cana.vbam.common.repayment.enums.SettleStatus;
import com.cana.vbam.common.service.IVbamCommonService;
import com.cana.vbam.common.utils.CanaDateUtils;
import com.cana.vbam.common.utils.Constants;
import com.cana.vbam.common.utils.MoneyArithUtil;
import com.cana.vbam.common.utils.RedisUtils;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.travelzen.framework.config.tops.TopsConfEnum.ConfScope;
import com.travelzen.framework.config.tops.TopsConfReader;
import com.travelzen.framework.core.common.ReturnCode;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.core.util.MoneyUtil;
import com.travelzen.framework.core.util.NumberUtils;
import com.travelzen.framework.redis.client.SpringRedisClient;

@Service
public class HomsomTransactionServiceImpl implements IHomsomTransactionService{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	private IVbamCommonService commonService;
	@Resource
	private HomsomTicketMapper ticketMapper;
	@Resource
	private HomsomCounterpartyConfigMapper homsomCounterpartyConfigMapper;
	@Resource
	private HomsomDailyLoanReportMapper loanReportMapper;
	@Resource
	private BusinessBasicInfoMapper basicInfoMapper;
	@Resource
	private BusinessCounterpartyMapper counterpartyMapper;
	@Resource
	private IHomsomTicketMapper customizedTicketMapper;
	@Resource
	private  IAssetInvoiceTransactionService invoiceTransactionService;
	@Resource
	private IAssetLoanInfoTransactionService loanInfoTransactionService;
	@Resource
	private LoanInfoMapper loanInfoMapper;
	@Resource
	private LoanPlanMapper loanPlanMapper;
	@Resource
	private LoanPaidMapper loanPaidMapper;

	@Resource
	private HomsomDailyLoanReportMapper homsomDailyLoanReportMapper;
	@Resource
	private HomsomDailyReportTransferDetailMapper homsomDailyReportTransferDetailMapper;
	
	@Resource
	private HomsomSettlementCounterpartyMapper homsomSettlementCounterpartyMapper;
	
	@Resource
	private HomsomSettlementTicketMapper homsomSettlementTicketMapper;
	
	@Resource
	private HomsomBuybackCounterpartyMapper homsomBuybackCounterpartyMapper;
	
	@Resource
	private HomsomBuybackTicketMapper homsomBuybackTicketMapper;
	
	@Resource
	private HomsomSettlementTrackMapper homsomSettlementTrackMapper;
	@Resource
	private IAssetCreditTransactionService creditTransactionService;
	@Resource
	private HomsomDailyRefundReportMapper homsomDailyRefundReportMapper;
	
	private SpringRedisClient redisCache = SpringRedisClient.getInstance();
	
	@Override
	public void ticketImport(Channel channel, List<HomsomTicket> ticketList) {
		if(commonService.lockPropertiesByName(Constants.HOMSOM_TICKET_IMPORT_PREFIX + channel.name()) == null)
			commonService.addProperties(Constants.HOMSOM_TICKET_IMPORT_PREFIX + channel.name(), "");
		for(HomsomTicket ticket : ticketList){
			HomsomTicket existingTicket = findTicketByTicketNo(channel, ticket.getTicketNo());
			if(existingTicket != null){
				if(!ticketEquals(ticket, existingTicket))
					throw WebException.instance(ReturnCode.REQ_PARAMS_ERROR, "客票已经存在，且关键信息不匹配，详情:" + new Gson().toJson(ticket));
				else{
					logger.info("客票已经存在，详情:" + new Gson().toJson(ticket));
					continue;
				}
			}else{
				ticket.setState(TicketState.INIT.name());
				ticket.setDetailState(TicketDetailState.INIT.name());
				ticket.setChannel(channel.name());
				ticketMapper.insertSelective(ticket);
			}
		}
	}
	
	@Override
	public ObjectResult<String> modifyCounterpartyConfig(CounterpartyConfigDTO counterpartyConfigDTO) {
		if(StringUtils.isBlank(counterpartyConfigDTO.getCounterpartyId()) || null == counterpartyConfigDTO.getChannel()){
			throw WebException.instance("渠道或交易对手为空");
		}
		HomsomCounterpartyConfigKey key = new HomsomCounterpartyConfigKey();
		key.setChannel(counterpartyConfigDTO.getChannel().name());
		key.setCounterpartyId(counterpartyConfigDTO.getCounterpartyId());
		HomsomCounterpartyConfig counterpartyConfig = homsomCounterpartyConfigMapper.selectByPrimaryKey(key);
		if(null == counterpartyConfig){
			counterpartyConfig = new HomsomCounterpartyConfig();
			BeanUtils.copyProperties(counterpartyConfigDTO, counterpartyConfig);
			counterpartyConfig.setChannel(counterpartyConfigDTO.getChannel().name());
			counterpartyConfig.setBuybackPeriod(Integer.parseInt(counterpartyConfigDTO.getBuybackPeriod()));
			counterpartyConfig.setLoanPeriod(Integer.parseInt(counterpartyConfigDTO.getLoanPeriod()));
			counterpartyConfig.setRepaymentMethod(counterpartyConfigDTO.getRepaymentMethod().name());
			homsomCounterpartyConfigMapper.insertSelective(counterpartyConfig);
		}else{
			counterpartyConfig.setBuybackPeriod(Integer.parseInt(counterpartyConfigDTO.getBuybackPeriod()));
			counterpartyConfig.setLoanPeriod(Integer.parseInt(counterpartyConfigDTO.getLoanPeriod()));
			counterpartyConfig.setRepaymentMethod(counterpartyConfigDTO.getRepaymentMethod().name());
			homsomCounterpartyConfigMapper.updateByPrimaryKeySelective(counterpartyConfig);
		}
		return ObjectResult.success();
	}
	
	
	@Override
	public ObjectResult<CounterpartyConfigDTO> getCounterpartyConfigDTO(CounterpartyConfigDTO queryDTO) {
		HomsomCounterpartyConfigKey key = new HomsomCounterpartyConfigKey();
		key.setChannel(queryDTO.getChannel().name());
		key.setCounterpartyId(queryDTO.getCounterpartyId());
		HomsomCounterpartyConfig counterpartyConfig = homsomCounterpartyConfigMapper.selectByPrimaryKey(key);
		if(null==counterpartyConfig){
			throw WebException.instance("交易对手配置信息不存在·");
		}
		CounterpartyConfigDTO counterpartyConfigDTO = new CounterpartyConfigDTO();
		BeanUtils.copyProperties(counterpartyConfig, counterpartyConfigDTO);
		counterpartyConfigDTO.setBuybackPeriod(counterpartyConfig.getBuybackPeriod().toString());
		counterpartyConfigDTO.setLoanPeriod(counterpartyConfig.getLoanPeriod().toString());
		counterpartyConfigDTO.setChannel(Channel.valueOf(counterpartyConfig.getChannel()));
		counterpartyConfigDTO.setRepaymentMethod(RepaymentType.valueOf(counterpartyConfig.getRepaymentMethod()));
		return ObjectResult.success(null, counterpartyConfigDTO);
	}

	@Override
	public HomsomDailyReportTransferDetail confirmLoan(String date, Channel channel) {
		HomsomDailyLoanReportKey homsomDailyLoanReportKey = new HomsomDailyLoanReportKey();
		homsomDailyLoanReportKey.setChannel(channel.name());
		homsomDailyLoanReportKey.setCounterpartyId("");
		homsomDailyLoanReportKey.setDate(date);
		HomsomDailyLoanReport homsomDailyLoanReport = homsomDailyLoanReportMapper.lockByPrimaryKey(homsomDailyLoanReportKey);
		if(homsomDailyLoanReport == null) {
			logger.error(String.format("不存在%s天的渠道为%s的放款审核记录", date, channel.name()));
			throw WebException.instance("找不到该放款申请记录");
		}
		if(LoanState.WAITING_FOR_LOAN != LoanState.valueOf(homsomDailyLoanReport.getLoanState())) {
			logger.error(String.format("在%s天的渠道为%s的放款审核记录状态为%s,不能进行放款", date, channel.name(), homsomDailyLoanReport.getLoanState()));
			throw WebException.instance("当前状态不能进行放款操作");
		}

		HomsomDailyReportTransferDetail transferDetail = addTransferDetail(homsomDailyLoanReport);
		if (Channel.SHFH == channel) {
			homsomDailyLoanReport.setLoanState(LoanState.TRANSFERING.name());
		} else {
			homsomDailyLoanReport.setLoanState(LoanState.LOANED.name());
		}
		homsomDailyLoanReport.setUpdateTime(new Date());
		homsomDailyLoanReportMapper.updateByPrimaryKeySelective(homsomDailyLoanReport);

		return transferDetail;
	}

	private HomsomDailyReportTransferDetail addTransferDetail(HomsomDailyLoanReport homsomDailyLoanReport) {
		if (false == Channel.SHFH.equals(homsomDailyLoanReport.getChannel()))
			return null;

		Properties transferConfigs = TopsConfReader.getConfProperties("properties/homsom_param.properties", ConfScope.R);

		HomsomDailyReportTransferDetail transferDetail = new HomsomDailyReportTransferDetail();
		transferDetail.setFactorAccountName(transferConfigs.getProperty(
				homsomDailyLoanReport.getChannel() + "-factor_account_name"));
		transferDetail.setFactorAccountNo(transferConfigs.getProperty(
				homsomDailyLoanReport.getChannel() + "-factor_account_no"));
		transferDetail.setTransferAccountName(transferConfigs.getProperty(
				homsomDailyLoanReport.getChannel() + "-transfer_account_name"));
		transferDetail.setTransferAccountNo(transferConfigs.getProperty(
				homsomDailyLoanReport.getChannel() + "-transfer_account_no"));
		transferDetail.setWithdrawAccountName(transferConfigs.getProperty(
				homsomDailyLoanReport.getChannel() + "-withdraw_account_name"));
		transferDetail.setWithdrawAccountNo(transferConfigs.getProperty(
				homsomDailyLoanReport.getChannel() + "-withdraw_account_no"));
		if (StringUtils.isAnyBlank(
				transferDetail.getFactorAccountName(), transferDetail.getFactorAccountNo(),
				transferDetail.getTransferAccountName(), transferDetail.getTransferAccountNo(),
				transferDetail.getWithdrawAccountName(), transferDetail.getWithdrawAccountNo())) {
			logger.error("当前channel[{}]存在必要配置项为空错误", homsomDailyLoanReport.getChannel());
			throw WebException.instance("存在必要配置项为空错误");
		}

		transferDetail.setId(HomsomIDGenerator.generateDailyReportTransferDetailId());
		transferDetail.setType(DailyReportType.LOAN.name());
		transferDetail.setChannel(homsomDailyLoanReport.getChannel());
		transferDetail.setDate(homsomDailyLoanReport.getDate());
		transferDetail.setAmount(homsomDailyLoanReport.getLoanAmount());
		transferDetail.setState(DailyReportTransferState.TRANSFERING.name());
		transferDetail.setTransferBusinessSeq(HomsomIDGenerator.generateBusinessSeq());
		transferDetail.setTransferStartTime(new Date());
		homsomDailyReportTransferDetailMapper.insertSelective(transferDetail);
		return transferDetail;
	}
	
	@Override
	public ObjectResult<String> selttlementConfirm(UserVo userVo, SubmitSettlementRequestDTO requestDTO){
		// 1、授信额度加锁
		Credit credit = creditTransactionService.lockByBussinessContractNo(
				TopsConfReader.getConfContent(Constants.HOMSOM_PARAM_PATH, "HOMSOM-contractNo", ConfScope.R));
		String data = "0.00";
		switch(requestDTO.getPaymentType()){
		case SETTLEMENT:
			data = settlementSolve(userVo, credit, requestDTO);
			break;
		case BUYBACK:
			data = buybackSolve(userVo, credit, requestDTO);
			break;
		default:
			throw WebException.instance("没有该类型的处理器");
		}
		return ObjectResult.success(data);
	}
	
	private String buybackSolve(UserVo userVo, Credit credit, SubmitSettlementRequestDTO requestDTO){
		// 2、查询交易对手核销状态
		HomsomBuybackCounterparty buybackCounterparty = homsomBuybackCounterpartyMapper.lockByPrimaryKey(requestDTO.getBuybackCounterpartyId());
		if(null == buybackCounterparty){
			throw WebException.instance("该交易对手不存在未回购的记录");
		}
		if(!StringUtils.equals(SettlementState.UNSETTLE.name(), buybackCounterparty.getState())){
			throw WebException.instance("状态为非未核销的状态不能回购");
		}
		// 3、查询所有机票记录，并区分选中及未选中的机票
		HomsomBuybackTicketExample buybackTicketExample = new HomsomBuybackTicketExample();
		com.cana.homsom.dao.po.HomsomBuybackTicketExample.Criteria criteria = buybackTicketExample.createCriteria();
		criteria.andBuybackCounterpartyIdEqualTo(requestDTO.getBuybackCounterpartyId()).andChannelEqualTo(requestDTO.getChannel().name());
//		if(CollectionUtils.isNotEmpty(requestDTO.getUnselectedTicketNoList())){
//			criteria.andTicketNoIn(requestDTO.getUnselectedTicketNoList());
//		}
//		// 未选中核销的机票记录
//		List<HomsomBuybackTicket> buybackTicketUnselectedList = homsomBuybackTicketMapper.selectByExample(buybackTicketExample);
//		if(buybackTicketUnselectedList.size() != requestDTO.getUnselectedTicketNoList().size()){
//			throw WebException.instance("本次核销的机票系统中不存在的票号");
//		}
//		buybackTicketExample = new HomsomBuybackTicketExample();
//		criteria = buybackTicketExample.createCriteria();
//		criteria.andBuybackCounterpartyIdEqualTo(requestDTO.getSettlementCounterpartyId()).andChannelEqualTo(requestDTO.getChannel().name());
		if(CollectionUtils.isNotEmpty(requestDTO.getUnselectedTicketNoList())){
			criteria.andTicketNoNotIn(requestDTO.getUnselectedTicketNoList());
		}
		// 选中待核销的机票
		List<HomsomBuybackTicket> buybackTicketSelectedList = homsomBuybackTicketMapper.lockByExample(buybackTicketExample);
		// 4、金额校验
//		buybackAmountValidate(requestDTO, buybackTicketSelectedList);
		// 5、检查票号是否已核销
		HomsomSettlementTrackExample settlementTrackExample = new HomsomSettlementTrackExample();
		settlementTrackExample.createCriteria().andTicketNoIn(getAllBuybackSelectedTicketNo(buybackTicketSelectedList, requestDTO.getUnselectedTicketNoList())).andCounterpartyIdEqualTo(requestDTO.getCounterpartyId());
		int settledNo = homsomSettlementTrackMapper.countByExample(settlementTrackExample);
		if(settledNo > 0){
			throw WebException.instance("本次回购的机票中包含已核销的票号");
		}
		// 5、更新机票状态
		long actualBuybackLoanAmount = 0;
		long actualBuybackInterestAmount = 0;
		for(HomsomBuybackTicket buybackTicket:buybackTicketSelectedList){
			buybackTicket.setState(SettlementState.SETTLED.name());
			actualBuybackLoanAmount += buybackTicket.getLoanAmount() ;
			actualBuybackInterestAmount += buybackTicket.getInterestAmount();
			homsomBuybackTicketMapper.updateByPrimaryKey(buybackTicket);
		}
		buybackCounterparty.setActualBuybackAmount(actualBuybackLoanAmount + actualBuybackInterestAmount);
		buybackCounterparty.setLoanAmount(actualBuybackLoanAmount);
		buybackCounterparty.setInterestAmount(actualBuybackInterestAmount);
		buybackCounterparty.setDealDate(commonService.getCurrentDate());
		buybackCounterparty.setState(SettlementState.SETTLED.name());
		buybackCounterparty.setState(SettlementState.SETTLED.name());
		homsomBuybackCounterpartyMapper.updateByPrimaryKey(buybackCounterparty);
		settleLoanInfoByBuybackTickets(userVo, credit, buybackTicketSelectedList);
		return MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(actualBuybackLoanAmount + actualBuybackInterestAmount));
	}

	/**
	 * 结算放款信息，修改还款计划中的已还信息，新建还款记录，恢复授信额度，新增核销履历记录
	 */
	private void settleLoanInfoBySettlementTickets(UserVo userVo, Credit credit, List<HomsomSettlementTicket> settlementTickets) {
		List<HomsomSettlementTrack> tracks = Lists.newArrayList();
		for (HomsomSettlementTicket ticket : settlementTickets) {
			HomsomSettlementTrack track = new HomsomSettlementTrack();
			track.setId(HomsomIDGenerator.generateSettlementTrackId());
			BeanUtils.copyProperties(ticket, track, "buybackDate");
			track.setLoanDays(CanaDateUtils.durationDays(ticket.getLoanDate(), ticket.getSettleDate()));
			track.setState(SettlementTrackState.SETTLED.name());
			track.setCreateTime(new Date());
			tracks.add(track);
			homsomSettlementTrackMapper.insertSelective(track);
		}
		settleLoanInfoBySettlementTracks(userVo, credit, tracks);
	}

	private void settleLoanInfoBySettlementTracks(UserVo userVo, Credit credit, List<HomsomSettlementTrack> tracks) {
		List<String> loanNos = Lists.newArrayList();
		for (HomsomSettlementTrack track : tracks) {
			loanNos.add(track.getLoanNo());
		}
		LoanInfoExample example = new LoanInfoExample();
		example.createCriteria().andIdIn(loanNos);
		List<LoanInfo> loanInfos = loanInfoMapper.lockByExample(example);
		if (loanInfos.size() != loanNos.size())
			throw WebException.instance("放款信息数目不正确");

		long totalPricinpal = 0l;
		for (HomsomSettlementTrack track : tracks) {
			String paidDate = track.getSettleDate() != null ? track.getSettleDate() : track.getBuybackDate();

			LoanPlanExample planExample = new LoanPlanExample();
			planExample.createCriteria().andLoanInfoIdEqualTo(track.getLoanNo());
			LoanPlan plan = loanPlanMapper.selectByExample(planExample).get(0);

			plan.setAccountInterest(track.getInterestAmount());
			plan.setPaidPrincipal(track.getLoanAmount());
			plan.setPaidInterest(track.getInterestAmount());
			plan.setPaidOverdue(track.getOverdueAmount());
			plan.setSettleStatus(SettleStatus.SETTLED.name());
			plan.setLastPaidDate(paidDate);
			plan.setRepaymentDate(paidDate);
			plan.setUpateTime(new Date());
			loanPlanMapper.updateByPrimaryKeySelective(plan);

			LoanPaid paid = new LoanPaid();
			paid.setId(IDGenerator.generateAssetLoanPaidId());
			paid.setLoanInfoId(plan.getLoanInfoId());
			paid.setLoanPlanId(plan.getId());
			paid.setPaidPrincipal(plan.getPaidPrincipal());
			paid.setPaidInterest(plan.getPaidInterest());
			paid.setPaidOverdue(plan.getPaidOverdue());
			paid.setPaidAmount(paid.getPaidPrincipal() + paid.getPaidInterest() + paid.getPaidOverdue());
			paid.setPaidDate(paidDate);
			paid.setCreateTime(new Date());
			paid.setUpdateTime(new Date());
			loanPaidMapper.insert(paid);

			LoanInfo loanInfo = new LoanInfo();
			loanInfo.setId(track.getLoanNo());
			loanInfo.setFinanceBalance(0l);
			loanInfo.setSettleStatus(SettleStatus.SETTLED.name());
			loanInfo.setUpdateTime(new Date());
			loanInfoMapper.updateByPrimaryKeySelective(loanInfo);

			totalPricinpal += track.getLoanAmount();
		}
		creditTransactionService.recoveryLimit(credit.getId(), totalPricinpal, userVo.getUserId());
	}

	/**
	 * 结算放款信息，修改还款计划中的已还信息，新建还款记录，恢复授信额度，新增核销履历记录
	 */
	private void settleLoanInfoByBuybackTickets(UserVo userVo, Credit credit, List<HomsomBuybackTicket> buybackTickets) {
		List<HomsomSettlementTrack> tracks = Lists.newArrayList();
		for (HomsomBuybackTicket ticket : buybackTickets) {
			HomsomSettlementTrack track = new HomsomSettlementTrack();
			track.setId(HomsomIDGenerator.generateSettlementTrackId());
			BeanUtils.copyProperties(ticket, track);
			track.setLoanDays(CanaDateUtils.durationDays(ticket.getLoanDate(), ticket.getBuybackDate()));
			track.setState(SettlementTrackState.BUYBACKED.name());
			track.setCreateTime(new Date());
			tracks.add(track);
			homsomSettlementTrackMapper.insertSelective(track);
		}

		settleLoanInfoBySettlementTracks(userVo, credit, tracks);
	}
	
	private String settlementSolve(UserVo userVo, Credit credit, SubmitSettlementRequestDTO requestDTO){
		// 2、查询交易对手核销状态
		HomsomSettlementCounterparty settlementCounterparty = homsomSettlementCounterpartyMapper.lockByPrimaryKey(requestDTO.getSettlementCounterpartyId());
		if(null == settlementCounterparty){
			throw WebException.instance("该交易对手不存在未核销的记录");
		}
		if(!StringUtils.equals(SettlementState.UNSETTLE.name(), settlementCounterparty.getState())){
			throw WebException.instance("状态为非未核销的状态不能核销");
		}
		// 3、查询所有机票记录，并区分选中及未选中的机票
		List<HomsomSettlementTicket> settlementTicketUnselectedList = Lists.newArrayList();
		List<HomsomSettlementTicket> settlementTicketSelectedList = Lists.newArrayList();
		if(requestDTO.isChooseAll()){
			HomsomSettlementTicketExample settlementTicketExample = new HomsomSettlementTicketExample();
			Criteria criteria = settlementTicketExample.createCriteria();
			criteria.andSettlementCounterpartyIdEqualTo(requestDTO.getSettlementCounterpartyId()).andChannelEqualTo(requestDTO.getChannel().name());
//			if(CollectionUtils.isNotEmpty(requestDTO.getUnselectedTicketNoList())){
//				criteria.andTicketNoNotIn(requestDTO.getUnselectedTicketNoList());
//			}
//			// 未选中核销的机票记录
//			settlementTicketUnselectedList = homsomSettlementTicketMapper.selectByExample(settlementTicketExample);
//			if(settlementTicketUnselectedList.size() != requestDTO.getUnselectedTicketNoList().size()){
//				throw WebException.instance("本次核销的机票包含系统中不存在的票号");
//			}
//			settlementTicketExample = new HomsomSettlementTicketExample();
//			criteria = settlementTicketExample.createCriteria();
//			criteria.andSettlementCounterpartyIdEqualTo(requestDTO.getSettlementCounterpartyId()).andChannelEqualTo(requestDTO.getChannel().name());
			if(CollectionUtils.isNotEmpty(requestDTO.getUnselectedTicketNoList())){
				criteria.andTicketNoIn(requestDTO.getUnselectedTicketNoList());
			}
			// 选中待核销的机票
			settlementTicketSelectedList = homsomSettlementTicketMapper.lockByExample(settlementTicketExample);
		}else{
			HomsomSettlementTicketExample settlementTicketExample = new HomsomSettlementTicketExample();
			Criteria criteria = settlementTicketExample.createCriteria();
			criteria.andSettlementCounterpartyIdEqualTo(requestDTO.getSettlementCounterpartyId()).andChannelEqualTo(requestDTO.getChannel().name());
			if(CollectionUtils.isNotEmpty(requestDTO.getSelectedTicketNoList())){
				criteria.andTicketNoIn(requestDTO.getSelectedTicketNoList());
			}
			// 选中待核销的机票
			settlementTicketSelectedList = homsomSettlementTicketMapper.lockByExample(settlementTicketExample);
			if(settlementTicketSelectedList.size() != requestDTO.getSelectedTicketNoList().size()){
				throw WebException.instance("本次核销的机票包含系统中不存在的票号");
			}
		}
		// 4、金额校验
		settlementAmountValidate(requestDTO, settlementTicketSelectedList);
		// 5、检查票号是否已核销
		HomsomSettlementTrackExample settlementTrackExample = new HomsomSettlementTrackExample();
		settlementTrackExample.createCriteria().andTicketNoIn(getAllSelectedTicketNo(settlementTicketSelectedList)).andCounterpartyIdEqualTo(requestDTO.getCounterpartyId());
		int settledNo = homsomSettlementTrackMapper.countByExample(settlementTrackExample);
		if(settledNo > 0){
			throw WebException.instance("本次核销的机票中包含已核销的票号");
		}
		// 6、更新机票状态
		settlementCounterparty.setActualSettleAmount(MoneyArithUtil.convertStringToMoney(requestDTO.getActualSettlementAmount()));
		settlementCounterparty.setRefundAmount(refundAmountCalculate(requestDTO, settlementTicketSelectedList));
		settlementCounterparty.setBuybackAmount(buybackAmountCalculate(requestDTO, settlementTicketUnselectedList));
		settlementCounterparty.setDealDate(commonService.getCurrentDate());
		settlementCounterparty.setState(SettlementState.SETTLED.name());
		long totalSettleAmount = 0;
		for(HomsomSettlementTicket settlementTicket:settlementTicketSelectedList){
			totalSettleAmount += (settlementTicket.getLoanAmount() + settlementTicket.getInterestAmount());
			settlementTicket.setState(SettlementState.SETTLED.name());
			settlementTicket.setBuybackDate(settlementTicket.getSettleDate());
			homsomSettlementTicketMapper.updateByPrimaryKey(settlementTicket);
		}
		settlementCounterparty.setSettleAmount(totalSettleAmount);
		settlementCounterparty.setState(SettlementState.SETTLED.name());
		homsomSettlementCounterpartyMapper.updateByPrimaryKey(settlementCounterparty);

		appendDailyRefundReport(settlementCounterparty);
		settleLoanInfoBySettlementTickets(userVo, credit, settlementTicketSelectedList);
		return MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(totalSettleAmount));
	}

	/**
	 * 如果是上海泛华项目，则追加日退款报表汇总记录
	 * @param settlementCounterparty
	 */
	private void appendDailyRefundReport(HomsomSettlementCounterparty settlementCounterparty) {
		if (false == Channel.SHFH.equals(settlementCounterparty.getChannel()))
			return;

		HomsomDailyRefundReportKey key = new HomsomDailyRefundReportKey();
		key.setChannel(settlementCounterparty.getChannel());
		key.setDate(settlementCounterparty.getDealDate());
		HomsomDailyRefundReport report = homsomDailyRefundReportMapper.selectByPrimaryKey(key);

		if (report == null) {
			report = new HomsomDailyRefundReport();
			report.setChannel(key.getChannel());
			report.setDate(key.getDate());
			report.setRefundAmount(settlementCounterparty.getRefundAmount());
			homsomDailyRefundReportMapper.insertSelective(report);
		} else {
			report.setRefundAmount(NumberUtils.getValue(report.getRefundAmount()) + settlementCounterparty.getRefundAmount());
			report.setUpdateTime(new Date());
			homsomDailyRefundReportMapper.updateByPrimaryKeySelective(report);
		}
	}
	
	private List<String> getAllSelectedTicketNo(List<HomsomSettlementTicket> settlementTicketSelectedList){
		List<String> selectedTicketNo = Lists.newArrayList();
		for(HomsomSettlementTicket settlementTicket:settlementTicketSelectedList){
			selectedTicketNo.add(settlementTicket.getTicketNo());
		}
		return selectedTicketNo;
	}

	private List<String> getAllBuybackSelectedTicketNo(List<HomsomBuybackTicket> buybackTicketSelectedList, List<String> unselectedTicketNoList){
		List<String> selectedTicketNo = Lists.newArrayList();
		for(HomsomBuybackTicket buybackTicket:buybackTicketSelectedList){
			selectedTicketNo.add(buybackTicket.getTicketNo());
		}
		selectedTicketNo.removeAll(unselectedTicketNoList);
		return selectedTicketNo;
	}
	
	private long buybackAmountCalculate(SubmitSettlementRequestDTO requestDTO, List<HomsomSettlementTicket> settlementTicketUnselectedList){
		if(CollectionUtils.isEmpty(settlementTicketUnselectedList)){
			return 0;
		}
		long unSelectLoanAmount = 0l;
		long unSelectInterestAmount = 0l;
		for(HomsomSettlementTicket settlementTicket:settlementTicketUnselectedList){
			unSelectLoanAmount += settlementTicket.getLoanAmount();
			unSelectLoanAmount += settlementTicket.getInterestAmount();
		}
		return (unSelectLoanAmount + unSelectInterestAmount);
	}
	
	private long refundAmountCalculate(SubmitSettlementRequestDTO requestDTO, List<HomsomSettlementTicket> settlementTicketSelectedList){
		long selectLoanAmount = 0l;
		long selectInterestAmount = 0l;
		for(HomsomSettlementTicket settlementTicket:settlementTicketSelectedList){
			selectLoanAmount += settlementTicket.getLoanAmount();
			selectInterestAmount += settlementTicket.getInterestAmount();
		}
		long refundAmount = MoneyArithUtil.convertStringToMoney(requestDTO.getActualSettlementAmount()) - (selectLoanAmount + selectInterestAmount);
		return refundAmount >= 0  ? refundAmount : 0 ;
	}
	
	private void settlementAmountValidate(SubmitSettlementRequestDTO requestDTO, List<HomsomSettlementTicket> settlementTicketSelectedList){
		long selectLoanAmount = 0l;
		long selectInterestAmount = 0l;
		for(HomsomSettlementTicket settlementTicket:settlementTicketSelectedList){
			selectLoanAmount += settlementTicket.getLoanAmount();
			selectInterestAmount += settlementTicket.getInterestAmount();
		}
		if(MoneyArithUtil.convertStringToMoney(requestDTO.getLoanAmount()) != (selectLoanAmount + selectInterestAmount)){
			throw WebException.instance("放款总金额不正确");
		}
		if(MoneyArithUtil.convertStringToMoney(requestDTO.getActualSettlementAmount()) >= (selectLoanAmount + selectInterestAmount)){
			throw WebException.instance("选中的票面价格与利息大于实际核销总金额");
		}
	}

//	private void buybackAmountValidate(SubmitSettlementRequestDTO requestDTO, List<HomsomBuybackTicket> buybackTicketSelectedList){
//		long selectLoanAmount = 0l;
//		long selectInterestAmount = 0l;
//		for(HomsomBuybackTicket buybackTicket:buybackTicketSelectedList){
//			selectLoanAmount += buybackTicket.getLoanAmount();
//			selectInterestAmount += buybackTicket.getInterestAmount();
//		}
//		if(MoneyArithUtil.convertStringToMoney(requestDTO.getActualBuybackAmount()) != (selectLoanAmount + selectInterestAmount)){
//			throw WebException.instance("选中的票面价格与利息大于实际核销总金额");
//		}
//	}
	
	private boolean ticketEquals(HomsomTicket newTicket, HomsomTicket oldTicket) {
		if(!StringUtils.equals(newTicket.getAgentCode(), oldTicket.getAgentCode())){
			logger.error("agentCode不匹配，新的:{}, 旧的:{}", newTicket.getAgentCode(), oldTicket.getAgentCode());
			return false;
		}
		if(!StringUtils.equals(newTicket.getAgentName(), oldTicket.getAgentName())){
			logger.error("agentName不匹配，新的:{}, 旧的:{}", newTicket.getAgentName(), oldTicket.getAgentName());
			return false;
		}
		if(newTicket.getAmount().longValue() != oldTicket.getAmount().longValue()){
			logger.error("amount不匹配，新的:{}, 旧的:{}", newTicket.getAmount(), oldTicket.getAmount());
			return false;
		}
		if(!StringUtils.equals(newTicket.getIssueDate(), oldTicket.getIssueDate())){
			logger.error("issueDate不匹配，新的:{}, 旧的:{}", newTicket.getIssueDate(), oldTicket.getIssueDate());
			return false;
		}
		return true;
	}

	private HomsomTicket findTicketByTicketNo(Channel channel, String ticketNo) {
		HomsomTicketExample example = new HomsomTicketExample();
		example.createCriteria().andChannelEqualTo(channel.name()).andTicketNoEqualTo(ticketNo);
		List<HomsomTicket> ticketList = ticketMapper.selectByExample(example);
		return ticketList.size() == 0 ? null : ticketList.get(0);
	}

	@Override
	public void prepareGenerateLoan(Channel channel, String reportDate, String issueDate) {
		if(lockReport(channel.name(), reportDate, "") != null)
			return;
		initSummaryLoanReport(channel, reportDate);
		List<BusinessCounterparty> counterpartyList = getCounterpartyList(channel, null);
		for(BusinessCounterparty counterparty : counterpartyList){
			initCounterpartyReport(channel, reportDate, counterparty);
		}
		int snapshotTicketNum = customizedTicketMapper.makeTicketSnapshot(channel.name(), issueDate);
		logger.info("快照圈定的客票数量为{}", snapshotTicketNum);
		int discardTicketNum = customizedTicketMapper.markDiscardTickets(channel.name(), issueDate, getCounterpartyNameSet(counterpartyList));
		logger.info("作废的客票数量为{}", discardTicketNum);
		
	}
	
	private HomsomDailyLoanReport lockReport(String channel, String date, String counterpartyId){
		HomsomDailyLoanReportKey key = new HomsomDailyLoanReportKey();
		key.setChannel(channel);
		key.setDate(date);
		key.setCounterpartyId(counterpartyId);
		return loanReportMapper.lockByPrimaryKey(key);
	}

	/**
	 * 获取交易对手名称集合
	 * @param counterpartyList
	 * @return
	 */
	private Set<String> getCounterpartyNameSet(List<BusinessCounterparty> counterpartyList) {
		Set<String> set = new HashSet<>();
		for(BusinessCounterparty counterparty : counterpartyList)
			set.add(counterparty.getCounterparty());
		return set;
	}

	/**
	 * 初始化交易对手报表
	 * @param channel
	 * @param counterparty
	 */
	private void initCounterpartyReport(Channel channel, String date, BusinessCounterparty counterparty) {
		HomsomDailyLoanReport report = new HomsomDailyLoanReport();
		report.setChannel(channel.name());
		report.setDate(date);
		report.setCounterpartyId(counterparty.getCounterpartyId());
		report.setCounterpartyName(counterparty.getCounterparty());
		report.setLoanState(LoanState.UNFINISHED.name());
		loanReportMapper.insertSelective(report);
	}

	/**
	 * 初始化总报表
	 * @param channel
	 * @param date
	 */
	private void initSummaryLoanReport(Channel channel, String date) {
		HomsomDailyLoanReport report = new HomsomDailyLoanReport();
		report.setChannel(channel.name());
		report.setDate(date);
		report.setCounterpartyId("");
		report.setCounterpartyName("");
		report.setLoanState(LoanState.UNFINISHED.name());
		loanReportMapper.insertSelective(report);
	}

	/**
	 * 获取交易对手列表
	 * @param channel
	 * @return
	 */
	private List<BusinessCounterparty> getCounterpartyList(Channel channel, String counterpartyId){
		String businessContractNo = TopsConfReader.getConfContent("properties/homsom_param.properties", channel.name() + "-contractNo", ConfScope.R);
		BusinessBasicInfoExample infoExample = new BusinessBasicInfoExample();
		infoExample.createCriteria().andBusinessContractNoEqualTo(businessContractNo);
		List<BusinessBasicInfo> infoList = basicInfoMapper.selectByExample(infoExample);
		
		BusinessCounterpartyExample counterpartyExample = new BusinessCounterpartyExample();
		BusinessCounterpartyExample.Criteria criteria = counterpartyExample.createCriteria();
		criteria.andBusinessInfoIdEqualTo(infoList.get(0).getId());
		if(StringUtils.isNotBlank(counterpartyId))
			criteria.andCounterpartyIdEqualTo(counterpartyId);
		return counterpartyMapper.selectByExample(counterpartyExample);
	}
	
	
	/**
	 * 获取业务基本信息
	 * @param channel
	 * @return
	 */
	private BusinessBasicInfo getBusinessBasicInfo(Channel channel){
		String businessContractNo = TopsConfReader.getConfContent("properties/homsom_param.properties", channel.name() + "-contractNo", ConfScope.R);
		BusinessBasicInfoExample infoExample = new BusinessBasicInfoExample();
		infoExample.createCriteria().andBusinessContractNoEqualTo(businessContractNo);
		List<BusinessBasicInfo> infoList = basicInfoMapper.selectByExample(infoExample);
		return infoList.get(0);
	}
	
	/**
	 * 获取交易对手分报表
	 * @param channel
	 * @param date
	 * @return
	 */
	private List<HomsomDailyLoanReport> getCounterpartyLoanReportList(Channel channel, String date){
		HomsomDailyLoanReportExample example = new HomsomDailyLoanReportExample();
		example.createCriteria().andChannelEqualTo(channel.name())
		                        .andDateEqualTo(date)
		                        .andCounterpartyIdNotEqualTo("");
		return loanReportMapper.selectByExample(example);
	}
	
	

	@Override
	public void generateLoan(UserVo factorUser, Channel channel, String reportDate, String issueDate) {
		// 锁定总报表
		lockReport(channel.name(), reportDate, "");
		BusinessBasicInfo basicInfo = getBusinessBasicInfo(channel);
		for(HomsomDailyLoanReport counterpartyLoanReport : getCounterpartyLoanReportList(channel, reportDate)){
			if(!LoanState.UNFINISHED.name().equals(counterpartyLoanReport.getLoanState()))
				continue;
			logger.info("开始为交易对手[{}]生成放款", counterpartyLoanReport.getCounterpartyName());
			generateLoan4counterparty(factorUser, channel, reportDate, issueDate, counterpartyLoanReport, basicInfo);
			logger.info("为交易对手[{}]生成放款完成", counterpartyLoanReport.getCounterpartyName());
		}
		generateLoanCompleted(channel, reportDate);
	}

	/**
	 * 执行生成放款后的动作：更改总报表的状态
	 * @param channel
	 * @param date
	 */
	private void generateLoanCompleted(Channel channel, String reportDate) {
		HomsomDailyLoanReport summaryReport = lockReport(channel.name(), reportDate, "");
		if(summaryReport.getLoanAmount() == 0)
			summaryReport.setLoanState(LoanState.NO_LOAN.name());
		else if(summaryReport.getLoanAmount() > 0)
			summaryReport.setLoanState(LoanState.WAITING_FOR_LOAN.name());
		else throw WebException.instance("放款总金额为负，不正常");
		summaryReport.setUpdateTime(new Date());
		loanReportMapper.updateByPrimaryKey(summaryReport);
	}

	/**
	 * 生成交易对手分报表
	 * @param channel
	 * @param date
	 * @param counterpartyLoanReport
	 */
	private void generateLoan4counterparty(UserVo factorUser, Channel channel, String reportDate, String issueDate, HomsomDailyLoanReport counterpartyLoanReport, BusinessBasicInfo basicInfo) {
		BusinessCounterparty counterparty = getCounterpartyList(channel, counterpartyLoanReport.getCounterpartyId()).get(0);
		HomsomCounterpartyConfig counterpartyConfig = getCounterpartyConfig(channel, counterpartyLoanReport.getCounterpartyId());
		List<HomsomTicket> ticketList = customizedTicketMapper.getUngenerateLoanTickets4counterparty(channel.name(), issueDate, counterpartyLoanReport.getCounterpartyName());
		logger.info("交易对手:{}，待生成放款的客票数量:{}", counterpartyLoanReport.getCounterpartyName(), ticketList.size());
		for(HomsomTicket ticket : ticketList){
			generateLoan4Ticket(factorUser, channel, reportDate, counterpartyLoanReport, ticket, counterparty, counterpartyConfig, basicInfo);
		}
		generateLoan4counterpartyCompleted(channel, reportDate, counterpartyLoanReport);
	}
	
	/**
	 * 获取交易对手配置
	 * @param channel
	 * @param counterpartyId
	 * @return
	 */
	private HomsomCounterpartyConfig getCounterpartyConfig(Channel channel, String counterpartyId){
		HomsomCounterpartyConfigKey key = new HomsomCounterpartyConfigKey();
		key.setChannel(channel.name());
		key.setCounterpartyId(counterpartyId);
		return homsomCounterpartyConfigMapper.selectByPrimaryKey(key);
	}

	/**
	 * 生成完指定交易对手的放款后，执行此方法
	 * @param channel
	 * @param date
	 */
	private void generateLoan4counterpartyCompleted(Channel channel, String date, HomsomDailyLoanReport counterpartyLoanReport) {
		HomsomDailyLoanReport report = lockReport(channel.name(), date, counterpartyLoanReport.getCounterpartyId());
		if(report.getLoanAmount() == 0)
			report.setLoanState(LoanState.NO_LOAN.name());
		else if(report.getLoanAmount() > 0)
			report.setLoanState(LoanState.WAITING_FOR_LOAN.name());
		else throw WebException.instance("放款总金额为负，不正常");
		report.setUpdateTime(new Date());
		loanReportMapper.updateByPrimaryKey(report);
	}

	/**
	 * 为客票生成放款
	 * @param channel
	 * @param date
	 * @param counterpartyLoanReport
	 * @param ticket
	 */
	private void generateLoan4Ticket(UserVo factorUser, Channel channel, String reportDate, HomsomDailyLoanReport counterpartyLoanReport,
			HomsomTicket ticket, BusinessCounterparty counterparty, HomsomCounterpartyConfig counterpartyConfig, BusinessBasicInfo basicInfo) {
		
		HomsomDailyLoanReport summaryReport = lockReport(channel.name(), reportDate, "");
		HomsomDailyLoanReport counterpartyReport = lockReport(channel.name(), reportDate, counterpartyLoanReport.getCounterpartyId());
		ticket = ticketMapper.lockByPrimaryKey(ticket.getId());
		
		//交易客票状态是否是未处理
		if(!TicketState.INIT.name().equals(ticket.getState()))
			return;
		
		//生成应收账款
		AssetInvoiceInfo invoiceInfo = generateInvoice(counterpartyLoanReport, ticket, counterparty, counterpartyConfig);
		
		if(counterpartyConfig == null){ // 处理交易对手配置不存在的情况
			handleNoCounterpartyConfigError(ticket);
			return;
		}
		try{
			LoanInfo loanInfo = doGenerateLoan4Ticket(factorUser, counterpartyLoanReport, ticket, counterparty, counterpartyConfig, invoiceInfo, basicInfo);
			Date now = new Date();
			// 更新总报表
			summaryReport.setLoanAmount(summaryReport.getLoanAmount() + loanInfo.getFinanceAmount());
			summaryReport.setApplyAmount(summaryReport.getLoanAmount());
			summaryReport.setUpdateTime(now);
			loanReportMapper.updateByPrimaryKey(summaryReport);
			// 更新分报表
			counterpartyReport.setLoanAmount(counterpartyReport.getLoanAmount() + loanInfo.getFinanceAmount());
			counterpartyReport.setApplyAmount(counterpartyReport.getLoanAmount());
			counterpartyReport.setUpdateTime(now);
			loanReportMapper.updateByPrimaryKey(counterpartyReport);
			//更新客票状态
			ticket.setState(TicketState.HANDLED.name());
			ticket.setDetailState(TicketDetailState.LOAN_GENERATED.name());
			ticketMapper.updateByPrimaryKey(ticket);
			
		}catch(WebException we){
			if(we.getRetCode() == ReturnCode.FINANCE_AMOUNT_EXCEED){
				handleInsufficientLimitError(ticket);
				return;
			}
			throw we;
		}
		
	}

	/**
	 * 真正生成放款
	 * @param counterpartyLoanReport
	 * @param ticket
	 * @param counterparty
	 * @param counterpartyConfig
	 */
	private LoanInfo doGenerateLoan4Ticket(UserVo factorUser, HomsomDailyLoanReport counterpartyLoanReport, HomsomTicket ticket,
			BusinessCounterparty counterparty, HomsomCounterpartyConfig counterpartyConfig, AssetInvoiceInfo invoiceInfo, BusinessBasicInfo basicInfo) {
			EditAssetLoanRequest loanRequest = new EditAssetLoanRequest();
			String businessContractNo = TopsConfReader.getConfContent("properties/homsom_param.properties", counterpartyLoanReport.getChannel() + "-contractNo", ConfScope.R);
			loanRequest.setContractNo(businessContractNo);
			loanRequest.setCounterpartyId(counterpartyLoanReport.getCounterpartyId());
			loanRequest.setCurrency(Currency.RMB.name());
			loanRequest.setDayCountConvention(360);
			loanRequest.setLoanDate(counterpartyLoanReport.getDate());
			loanRequest.setDueDate(DateTimeUtil.addDay10(counterpartyLoanReport.getDate(), counterpartyConfig.getLoanPeriod() + counterpartyConfig.getBuybackPeriod()));
			loanRequest.setFinanceAmount(MoneyUtil.cent2Yuan(MoneyArithUtil.round(invoiceInfo.getFinancingRatio().multiply(new BigDecimal(invoiceInfo.getInvoiceAmt())), 0)));
			loanRequest.setInterestRate(MoneyArithUtil.mul(new BigDecimal(basicInfo.getInterestRate()), 100L).setScale(3).toString());
			loanRequest.setInterestRateUnit(InterestRateUnit.DAY.name());
			loanRequest.setInvoiceInfoIds(Arrays.asList(invoiceInfo.getId()));
			loanRequest.setLoanPeriod(counterpartyConfig.getLoanPeriod() + counterpartyConfig.getBuybackPeriod());
			loanRequest.setLoanPeriodUnit(DateUnit.DAY.name());
			loanRequest.setPenaltyRate(MoneyArithUtil.mul(new BigDecimal(basicInfo.getPenaltyRate()), 100L).setScale(3).toString());
			loanRequest.setRepaymentAccountNo(basicInfo.getSettlementAccount());
			loanRequest.setRepaymentType(counterpartyConfig.getRepaymentMethod());
			Pair<ReturnCode, String> response = loanInfoTransactionService.createAssetLoanForHomsom(factorUser, loanRequest);
			if (response.getLeft() == ReturnCode.SUCCESS)
				return loanInfoMapper.selectByPrimaryKey(response.getRight());
			else
				throw WebException.instance(response.getLeft());
	}

	/**
	 * 处理额度不足的错误
	 * @param ticket
	 */
	private void handleInsufficientLimitError(HomsomTicket ticket) {
		ticket.setState(TicketState.HANDLED.name());
		ticket.setDetailState(TicketDetailState.INSUFFICIENT_LIMIT.name());
		ticketMapper.updateByPrimaryKey(ticket);
	}

	/**
	 * 处理交易对手配置不存在的情况
	 * @param ticket
	 */
	private void handleNoCounterpartyConfigError(HomsomTicket ticket) {
		logger.error("交易对手:{}的配置不存在", ticket.getAgentName());
		ticket.setState(TicketState.HANDLED.name());
		ticket.setDetailState(TicketDetailState.NO_COUNTERPARTY_CONFIG.name());
		ticketMapper.updateByPrimaryKey(ticket);
	}

	/**
	 * 生成应收账款
	 * @param counterpartyLoanReport
	 * @param ticket
	 */
	private AssetInvoiceInfo generateInvoice(HomsomDailyLoanReport counterpartyLoanReport, HomsomTicket ticket, BusinessCounterparty counterparty, HomsomCounterpartyConfig counterpartyConfig) {
		AssetInvoiceInfo invoiceInfo = new AssetInvoiceInfo();
		invoiceInfo.setId(IDGenerator.generateAssetInvoiceInfoId());
		invoiceInfo.setCounterpartyId(counterpartyLoanReport.getCounterpartyId());
		invoiceInfo.setCounterparty(counterpartyLoanReport.getCounterpartyName());
		invoiceInfo.setInvoiceNo(ticket.getTicketNo());
		invoiceInfo.setInvoiceAmt(ticket.getAmount());
		invoiceInfo.setNominvoiceAmt(ticket.getAmount());
		invoiceInfo.setFinancingRatio(counterparty.getFinancingRatio());
		invoiceInfo.setInvoiceDate(ticket.getIssueDate());
		if(counterpartyConfig != null){
			invoiceInfo.setDueDate(DateTimeUtil.addDay10(ticket.getIssueDate(), counterpartyConfig.getLoanPeriod() + counterpartyConfig.getBuybackPeriod()));
		}else{ //交易对手配置不存在，使用默认值
			invoiceInfo.setDueDate(DateTimeUtil.addDay10(ticket.getIssueDate(),  TopsConfReader.getConfContentForInt("properties/homsom_param.properties", counterpartyLoanReport.getChannel() + "-DEFAULT_LOAN_PERIOD", ConfScope.R)
					                                                           + TopsConfReader.getConfContentForInt("properties/homsom_param.properties", counterpartyLoanReport.getChannel() + "-DEFAULT_BUYBACK_PERIOD", ConfScope.R)));
		}
		String businessContractNo = TopsConfReader.getConfContent("properties/homsom_param.properties", counterpartyLoanReport.getChannel() + "-contractNo", ConfScope.R);
		invoiceTransactionService.addInvoiceInfoByContractNo(businessContractNo, Arrays.asList(invoiceInfo));
		return invoiceInfo;
	}

	@Override
	public void importSettlement2DB(String userId, String rediskey) {
		Object object = redisCache.read(RedisUtils.generateHomsomSettlementRedisKeyByOperator(rediskey, userId));
		if(object == null)
			throw WebException.instance("无数据");
		HomsomSettlementTicketExcelRedisDTO redisDTO = (HomsomSettlementTicketExcelRedisDTO) object;
		Map<String, HomsomSettlementTicketExcelDTO> passMap = redisDTO.getPassMap();
		if(passMap.size() == 0)
			throw WebException.instance("无校验通过的还款核销明细");
		List<String> counterpartyIds = getCounterparty(passMap);
		Map<String, HomsomSettlementCounterparty> homsomSettlementCounterpartiesMap = new HashMap<>();
		List<HomsomSettlementCounterparty> homsomSettlementCounterparties = lockHomsomSettlementCounterparty(counterpartyIds, passMap.values().iterator().next().getChannel());
		lockHomsomBuybackCounterparty(counterpartyIds, passMap.values().iterator().next().getChannel());
		checkSettlementState(passMap, passMap.values().iterator().next().getChannel());
		Map<String, HomsomSettlementCounterparty> existHomsomSettlementCounterparties = new HashMap<>();
		List<String> existHomsomSettlementTicketNo = null;
		if(homsomSettlementCounterparties.size() > 0) {
			List<String> settlementCounterpartyIds = new ArrayList<>();
			for(HomsomSettlementCounterparty homsomSettlementCounterparty : homsomSettlementCounterparties) {
				existHomsomSettlementCounterparties.put(homsomSettlementCounterparty.getCounterpartyId(), homsomSettlementCounterparty);
				settlementCounterpartyIds.add(homsomSettlementCounterparty.getId());
			}
			HomsomSettlementTicketExample homsomSettlementTicketExample = new HomsomSettlementTicketExample();
			homsomSettlementTicketExample.createCriteria().andSettlementCounterpartyIdIn(settlementCounterpartyIds);
			List<HomsomSettlementTicket> list = homsomSettlementTicketMapper.selectByExample(homsomSettlementTicketExample);
			existHomsomSettlementTicketNo = new ArrayList<>();
			for (HomsomSettlementTicket homsomSettlementTicket : list)
				existHomsomSettlementTicketNo.add(homsomSettlementTicket.getTicketNo());
		}
		for (HomsomSettlementTicketExcelDTO excelDTO : passMap.values()) {
			HomsomSettlementTicket homsomSettlementTicket = new HomsomSettlementTicket();
			BeanUtils.copyProperties(excelDTO, homsomSettlementTicket);
			HomsomSettlementCounterparty homsomSettlementCounterparty = null;
			if(existHomsomSettlementCounterparties.containsKey(homsomSettlementTicket.getCounterpartyId())) {
				homsomSettlementCounterparty = existHomsomSettlementCounterparties.get(homsomSettlementTicket.getCounterpartyId());
				if(existHomsomSettlementTicketNo.contains(homsomSettlementTicket.getTicketNo())) {
					existHomsomSettlementTicketNo.remove(homsomSettlementTicket.getTicketNo());
					continue;
				} else
					updateCounterpartyAmount(homsomSettlementCounterparty, homsomSettlementTicket);
			} else if(homsomSettlementCounterpartiesMap.containsKey(homsomSettlementTicket.getCounterpartyId())) {
				homsomSettlementCounterparty = homsomSettlementCounterpartiesMap.get(homsomSettlementTicket.getCounterpartyId());
				updateCounterpartyAmount(homsomSettlementCounterparty, homsomSettlementTicket);
			} else {
				homsomSettlementCounterparty = new HomsomSettlementCounterparty();
				homsomSettlementCounterparty.setId(HomsomIDGenerator.generateSettlementCounterpartyId());
				homsomSettlementCounterparty.setChannel(excelDTO.getChannel());
				homsomSettlementCounterparty.setCounterpartyId(homsomSettlementTicket.getCounterpartyId());
				homsomSettlementCounterparty.setCounterpartyName(homsomSettlementTicket.getCounterpartyName());
				homsomSettlementCounterparty.setSettleAmount(homsomSettlementTicket.getLoanAmount() + homsomSettlementTicket.getInterestAmount());
				homsomSettlementCounterparty.setLoanAmount(homsomSettlementTicket.getLoanAmount());
				homsomSettlementCounterparty.setInterestAmount(homsomSettlementTicket.getInterestAmount());
				homsomSettlementCounterparty.setState(SettlementState.UNSETTLE.name());
				homsomSettlementCounterparty.setCreateTime(new Date());
				homsomSettlementCounterparty.setUpdateTime(new Date());
				homsomSettlementCounterpartiesMap.put(homsomSettlementCounterparty.getCounterpartyId(), homsomSettlementCounterparty);
			}
			homsomSettlementTicket.setSettlementCounterpartyId(homsomSettlementCounterparty.getId());
			homsomSettlementTicketMapper.insertSelective(homsomSettlementTicket);
		}
		Collection<HomsomSettlementCounterparty> existHomsomSettlementCounterpartiesCollection = existHomsomSettlementCounterparties.values();
		for (HomsomSettlementCounterparty homsomSettlementCounterparty : existHomsomSettlementCounterpartiesCollection) {
			homsomSettlementCounterparty.setUpdateTime(new Date());
			homsomSettlementCounterpartyMapper.updateByPrimaryKey(homsomSettlementCounterparty);
		}
		Collection<HomsomSettlementCounterparty> homsomSettlementCounterpartiesCollection = homsomSettlementCounterpartiesMap.values();
		for (HomsomSettlementCounterparty homsomSettlementCounterparty : homsomSettlementCounterpartiesCollection)
			homsomSettlementCounterpartyMapper.insertSelective(homsomSettlementCounterparty);
	}
	
	@Override
	public void importBuyBack2DB(String userId, String rediskey) {
		Object object = redisCache.read(RedisUtils.generateHomsomSettlementRedisKeyByOperator(rediskey, userId));
		if(object == null)
			throw WebException.instance("无数据");
		HomsomBuyBackTicketExcelRedisDTO redisDTO = (HomsomBuyBackTicketExcelRedisDTO) object;
		Map<String, HomsomBuyBackTicketExcelDTO> passMap = redisDTO.getPassMap();
		if(passMap.size() == 0)
			throw WebException.instance("无校验通过的还款核销明细");
		List<String> counterpartyIds = getCounterparty(passMap);
		Map<String, HomsomBuybackCounterparty> homsomSettlementCounterpartiesMap = new HashMap<>();
		lockHomsomSettlementCounterparty(counterpartyIds, passMap.values().iterator().next().getChannel());
		List<HomsomBuybackCounterparty> homsomBuybackCounterparties = lockHomsomBuybackCounterparty(counterpartyIds, passMap.values().iterator().next().getChannel());
		checkSettlementState(passMap, passMap.values().iterator().next().getChannel());
		Map<String, HomsomBuybackCounterparty> existHomsomBuybackCounterparties = new HashMap<>();
		List<String> existHomsomBuybackTicketNo = null;
		if(homsomBuybackCounterparties.size() > 0) {
			List<String> buybackCounterpartyIds = new ArrayList<>();
			for(HomsomBuybackCounterparty homsomBuybackCounterparty : homsomBuybackCounterparties) {
				existHomsomBuybackCounterparties.put(homsomBuybackCounterparty.getCounterpartyId(), homsomBuybackCounterparty);
				buybackCounterpartyIds.add(homsomBuybackCounterparty.getId());
			}
			HomsomBuybackTicketExample homsomBuybackTicketExample = new HomsomBuybackTicketExample();
			homsomBuybackTicketExample.createCriteria().andBuybackCounterpartyIdIn(buybackCounterpartyIds);
			List<HomsomBuybackTicket> list = homsomBuybackTicketMapper.selectByExample(homsomBuybackTicketExample);
			existHomsomBuybackTicketNo = new ArrayList<>();
			for (HomsomBuybackTicket homsomSettlementTicket : list)
				existHomsomBuybackTicketNo.add(homsomSettlementTicket.getTicketNo());
		}
		for (HomsomBuyBackTicketExcelDTO excelDTO : passMap.values()) {
			HomsomBuybackTicket homsomBuybackTicket = new HomsomBuybackTicket();
			BeanUtils.copyProperties(excelDTO, homsomBuybackTicket);
			HomsomBuybackCounterparty homsomBuybackCounterparty = null;
			if(existHomsomBuybackCounterparties.containsKey(homsomBuybackTicket.getCounterpartyId())) {
				homsomBuybackCounterparty = existHomsomBuybackCounterparties.get(homsomBuybackTicket.getCounterpartyId());
				if(existHomsomBuybackTicketNo.contains(homsomBuybackTicket.getTicketNo())) {
					existHomsomBuybackTicketNo.remove(homsomBuybackTicket.getTicketNo());
					continue;
				} else
					updateCounterpartyAmount(homsomBuybackCounterparty, homsomBuybackTicket);
			} else if(homsomSettlementCounterpartiesMap.containsKey(homsomBuybackTicket.getCounterpartyId())) {
				homsomBuybackCounterparty = homsomSettlementCounterpartiesMap.get(homsomBuybackTicket.getCounterpartyId());
				updateCounterpartyAmount(homsomBuybackCounterparty, homsomBuybackTicket);
			} else {
				homsomBuybackCounterparty = new HomsomBuybackCounterparty();
				homsomBuybackCounterparty.setId(HomsomIDGenerator.generateSettlementCounterpartyId());
				homsomBuybackCounterparty.setChannel(excelDTO.getChannel());
				homsomBuybackCounterparty.setCounterpartyId(homsomBuybackTicket.getCounterpartyId());
				homsomBuybackCounterparty.setCounterpartyName(homsomBuybackTicket.getCounterpartyName());
				homsomBuybackCounterparty.setLoanAmount(homsomBuybackTicket.getLoanAmount());
				homsomBuybackCounterparty.setInterestAmount(homsomBuybackTicket.getInterestAmount());
				homsomBuybackCounterparty.setState(SettlementState.UNSETTLE.name());
				homsomBuybackCounterparty.setCreateTime(new Date());
				homsomBuybackCounterparty.setUpdateTime(new Date());
				homsomSettlementCounterpartiesMap.put(homsomBuybackCounterparty.getCounterpartyId(), homsomBuybackCounterparty);
			}
			homsomBuybackTicket.setBuybackCounterpartyId(homsomBuybackCounterparty.getId());
			homsomBuybackTicketMapper.insertSelective(homsomBuybackTicket);
		}
		Collection<HomsomBuybackCounterparty> existHomsomSettlementCounterpartiesCollection = existHomsomBuybackCounterparties.values();
		for (HomsomBuybackCounterparty homsomBuybackCounterparty : existHomsomSettlementCounterpartiesCollection) {
			homsomBuybackCounterparty.setUpdateTime(new Date());
			homsomBuybackCounterpartyMapper.updateByPrimaryKey(homsomBuybackCounterparty);
		}
		Collection<HomsomBuybackCounterparty> homsomSettlementCounterpartiesCollection = homsomSettlementCounterpartiesMap.values();
		for (HomsomBuybackCounterparty homsomBuybackCounterparty : homsomSettlementCounterpartiesCollection)
			homsomBuybackCounterpartyMapper.insertSelective(homsomBuybackCounterparty);
	}
	
	private <T extends HomsomSettlementTicketDTO> List<HomsomSettlementCounterparty> lockHomsomSettlementCounterparty(List<String> counterpartyIds, String channel) {
		HomsomSettlementCounterpartyExample homsomSettlementCounterpartyExample = new HomsomSettlementCounterpartyExample();
		homsomSettlementCounterpartyExample.createCriteria().andChannelEqualTo(channel).andCounterpartyIdIn(counterpartyIds).andStateEqualTo(SettlementState.UNSETTLE.name());
		return homsomSettlementCounterpartyMapper.lockByExample(homsomSettlementCounterpartyExample);
	}
	
	private <T extends HomsomSettlementTicketDTO> List<HomsomBuybackCounterparty> lockHomsomBuybackCounterparty(List<String> counterpartyIds, String channel) {
		HomsomBuybackCounterpartyExample homsomBuybackCounterpartyExample = new HomsomBuybackCounterpartyExample();
		homsomBuybackCounterpartyExample.createCriteria().andChannelEqualTo(channel).andCounterpartyIdIn(counterpartyIds).andStateEqualTo(SettlementState.UNSETTLE.name());
		return homsomBuybackCounterpartyMapper.lockByExample(homsomBuybackCounterpartyExample);
	}
	
	private <T extends HomsomSettlementTicketDTO> void checkSettlementState(Map<String, T> passMap, String channel) {
		HomsomSettlementTrackExample homsomSettlementTrackExample = new HomsomSettlementTrackExample();
		homsomSettlementTrackExample.createCriteria().andChannelEqualTo(channel).andTicketNoIn(new ArrayList<>(passMap.keySet()));
		List<HomsomSettlementTrack> homsomSettlementTracks = homsomSettlementTrackMapper.selectByExample(homsomSettlementTrackExample);
		if(homsomSettlementTracks.size() > 0)
			throw WebException.instance("数据发生变更, 请重新导入");
	}
	
	private <T extends HomsomSettlementTicketDTO> List<String> getCounterparty(Map<String, T> passMap) {
		Collection<T> collections = passMap.values();
		List<String> returnValue = new ArrayList<>();
		for (T t : collections)
			if(!returnValue.contains(t.getCounterpartyId()))
				returnValue.add(t.getCounterpartyId());
		return returnValue;
	}
	
	private void updateCounterpartyAmount(HomsomSettlementCounterparty homsomSettlementCounterparty, HomsomSettlementTicket homsomSettlementTicket) {
		homsomSettlementCounterparty.setSettleAmount(homsomSettlementCounterparty.getSettleAmount() + homsomSettlementTicket.getLoanAmount() + homsomSettlementTicket.getInterestAmount());
		homsomSettlementCounterparty.setLoanAmount(homsomSettlementCounterparty.getLoanAmount() + homsomSettlementTicket.getLoanAmount());
		homsomSettlementCounterparty.setInterestAmount(homsomSettlementCounterparty.getInterestAmount() + homsomSettlementTicket.getInterestAmount());
	}
	
	private void updateCounterpartyAmount(HomsomBuybackCounterparty homsomBuybackCounterparty, HomsomBuybackTicket homsomBuybackTicket) {
		homsomBuybackCounterparty.setLoanAmount(homsomBuybackCounterparty.getLoanAmount() + homsomBuybackTicket.getLoanAmount());
		homsomBuybackCounterparty.setInterestAmount(homsomBuybackCounterparty.getInterestAmount() + homsomBuybackTicket.getInterestAmount());
	}
}
