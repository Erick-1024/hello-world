package com.cana.asset.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.cana.asset.dao.mapper.gen.AssetInvoiceBasicInfoMapper;
import com.cana.asset.dao.mapper.gen.AssetInvoiceInfoMapper;
import com.cana.asset.dao.mapper.gen.LoanInfoMapper;
import com.cana.asset.dao.po.AssetInvoiceBasicInfoExample;
import com.cana.asset.dao.po.AssetInvoiceInfo;
import com.cana.asset.dao.po.AssetInvoiceInfoExample;
import com.cana.asset.dao.po.LoanInfo;
import com.cana.asset.dao.po.LoanInfoExample;
import com.cana.asset.service.IAssetFactorBusinessService;
import com.cana.asset.service.IHomsomService;
import com.cana.asset.service.convertors.HomsomLoanConvertor;
import com.cana.asset.service.convertors.HomsomTicketCovertor;
import com.cana.asset.service.transaction.IHomsomTransactionService;
import com.cana.homsom.dao.mapper.IHomsomRepaymentNoticeMapper;
import com.cana.homsom.dao.mapper.ISettlementSummaryMapper;
import com.cana.homsom.dao.mapper.gen.HomsomBuybackCounterpartyMapper;
import com.cana.homsom.dao.mapper.gen.HomsomBuybackTicketMapper;
import com.cana.homsom.dao.mapper.gen.HomsomCounterpartyConfigMapper;
import com.cana.homsom.dao.mapper.gen.HomsomDailyLoanReportMapper;
import com.cana.homsom.dao.mapper.gen.HomsomSettlementCounterpartyMapper;
import com.cana.homsom.dao.mapper.gen.HomsomSettlementTicketMapper;
import com.cana.homsom.dao.mapper.gen.HomsomSettlementTrackMapper;
import com.cana.homsom.dao.mapper.gen.HomsomTicketMapper;
import com.cana.homsom.dao.po.HomsomBuybackCounterparty;
import com.cana.homsom.dao.po.HomsomBuybackCounterpartyExample;
import com.cana.homsom.dao.po.HomsomBuybackTicket;
import com.cana.homsom.dao.po.HomsomBuybackTicketExample;
import com.cana.homsom.dao.po.HomsomCounterpartyConfig;
import com.cana.homsom.dao.po.HomsomCounterpartyConfigExample;
import com.cana.homsom.dao.po.HomsomDailyLoanReportExample;
import com.cana.homsom.dao.po.HomsomRepaymentNoticePO;
import com.cana.homsom.dao.po.HomsomSettlementCounterparty;
import com.cana.homsom.dao.po.HomsomSettlementCounterpartyExample;
import com.cana.homsom.dao.po.HomsomSettlementTicket;
import com.cana.homsom.dao.po.HomsomSettlementTicketExample;
import com.cana.homsom.dao.po.HomsomSettlementTrack;
import com.cana.homsom.dao.po.HomsomSettlementTrackExample;
import com.cana.homsom.dao.po.HomsomTicket;
import com.cana.homsom.dao.po.HomsomTicketExample;
import com.cana.homsom.dao.po.manual.SettlementSummary;
import com.cana.homsom.dao.utils.HomsomIDGenerator;
import com.cana.setting.service.transaction.ICanaCalendarTransactionService;
import com.cana.vbam.common.asset.dto.BusinessCounterpartyDTO;
import com.cana.vbam.common.asset.dto.CounterpartySearchDTO;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.dto.PageResult;
import com.cana.vbam.common.enums.AjaxResponseStatus;
import com.cana.vbam.common.homsom.dto.CounterpartyConfigDTO;
import com.cana.vbam.common.homsom.dto.CounterpartyConfigRequestDTO;
import com.cana.vbam.common.homsom.dto.HomsomBuyBackTicketExcelDTO;
import com.cana.vbam.common.homsom.dto.HomsomBuyBackTicketExcelListRequest;
import com.cana.vbam.common.homsom.dto.HomsomBuyBackTicketExcelRedisDTO;
import com.cana.vbam.common.homsom.dto.HomsomBuybackCounterpartyExcelDTO;
import com.cana.vbam.common.homsom.dto.HomsomSettlementCounterpartyExcelDTO;
import com.cana.vbam.common.homsom.dto.HomsomSettlementTicketDTO;
import com.cana.vbam.common.homsom.dto.HomsomSettlementTicketExcelDTO;
import com.cana.vbam.common.homsom.dto.HomsomSettlementTicketExcelListRequest;
import com.cana.vbam.common.homsom.dto.HomsomSettlementTicketExcelRedisDTO;
import com.cana.vbam.common.homsom.dto.LoanAuditDTO;
import com.cana.vbam.common.homsom.dto.LoanAuditDetailRequest;
import com.cana.vbam.common.homsom.dto.LoanAuditExcelDTO;
import com.cana.vbam.common.homsom.dto.LoanAuditListRequest;
import com.cana.vbam.common.homsom.dto.PaymentDataRequestDTO;
import com.cana.vbam.common.homsom.dto.SettlementCounterpartyDTO;
import com.cana.vbam.common.homsom.dto.SettlementSummaryDTO;
import com.cana.vbam.common.homsom.dto.StatementExcelDTO;
import com.cana.vbam.common.homsom.dto.Ticket;
import com.cana.vbam.common.homsom.dto.TicketDTO;
import com.cana.vbam.common.homsom.dto.TicketExcelDTO;
import com.cana.vbam.common.homsom.dto.TicketImportRequest;
import com.cana.vbam.common.homsom.dto.TicketListRequest;
import com.cana.vbam.common.homsom.dto.TicketLoanDetailDTO;
import com.cana.vbam.common.homsom.dto.TicketLoanRequestDTO;
import com.cana.vbam.common.homsom.enums.Channel;
import com.cana.vbam.common.homsom.enums.LoanState;
import com.cana.vbam.common.homsom.enums.OrderType;
import com.cana.vbam.common.homsom.enums.SettlementState;
import com.cana.vbam.common.homsom.enums.TicketDetailState;
import com.cana.vbam.common.repayment.enums.DateUnit;
import com.cana.vbam.common.repayment.enums.InterestRateUnit;
import com.cana.vbam.common.repayment.enums.RepaymentType;
import com.cana.vbam.common.repayment.enums.SettleStatus;
import com.cana.vbam.common.service.impl.VbamCommonService;
import com.cana.vbam.common.utils.Constants;
import com.cana.vbam.common.utils.InterestCalcUtil;
import com.cana.vbam.common.utils.MoneyArithUtil;
import com.cana.vbam.common.utils.RedisUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.travelzen.framework.config.tops.TopsConfEnum.ConfScope;
import com.travelzen.framework.config.tops.TopsConfReader;
import com.travelzen.framework.core.common.ReturnCode;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.core.util.MoneyUtil;
import com.travelzen.framework.core.util.StringUtil;
import com.travelzen.framework.dao.rdbms.SequenceGenerator;
import com.travelzen.framework.redis.client.SpringRedisClient;
import com.travelzen.framework.util.DateUtils;

@Service
public class HomsomServiceImpl implements IHomsomService{
	
	@Resource
	private SequenceGenerator seqGen;
	
	@Resource
	private IHomsomTransactionService transactionService;
	
	@Resource
	private HomsomTicketMapper homsomTicketMapper;
	
	@Resource
	private HomsomDailyLoanReportMapper homsomDailyLoanReportMapper;
	
	@Resource
	private IAssetFactorBusinessService assetFactorBusinessService;

	@Resource
	private HomsomCounterpartyConfigMapper homsomCounterpartyConfigMapper;
	
	@Resource
	private HomsomSettlementTrackMapper homsomSettlementTrackMapper;
	
	@Resource
	private AssetInvoiceBasicInfoMapper assetInvoiceBasicInfoMapper;
	
	@Resource
	private AssetInvoiceInfoMapper assetInvoiceInfoMapper;
	
	@Resource
	private LoanInfoMapper loanInforMapper;
	
	private SpringRedisClient redisCache = SpringRedisClient.getInstance();

	@Resource
	private HomsomSettlementCounterpartyMapper homsomSettlementCounterpartyMapper;
	
	@Resource
	private HomsomSettlementTicketMapper homsomSettlementTicketMapper;
	
	@Resource
	private HomsomBuybackCounterpartyMapper homsomBuybackCounterpartyMapper;
	
	@Resource
	private HomsomBuybackTicketMapper homsomBuybackTicketMapper;
	
	@Resource
	private ISettlementSummaryMapper settlementSummaryMapper;
	
	@Resource
	private ICanaCalendarTransactionService canaCalendarTransactionServiceImpl;
	
	@Resource
	private IHomsomRepaymentNoticeMapper homsomRepaymentNoticeMapper;
	
	@Resource
	private VbamCommonService commonService;
	
	@Override
	public void ticketImport(TicketImportRequest request){
		List<HomsomTicket> ticketList = checkAndConvert(request.getTickets());
		for(HomsomTicket ticket : ticketList)
			ticket.setChannel(request.getChannel());
		transactionService.ticketImport(Channel.valueOf(request.getChannel()), ticketList);
	}

	@Override
	public ListResult<TicketDTO> getTicketList(TicketListRequest ticketListRequest) {
		HomsomTicketExample homsomTicketExample = getTicketListExample(ticketListRequest);
		homsomTicketExample.setLimitStart((ticketListRequest.getPage() - 1) * ticketListRequest.getPageSize());
		homsomTicketExample.setLimitEnd(ticketListRequest.getPageSize());
		return ListResult.success(HomsomTicketCovertor.convertHomsomTicket2TicketDTO(homsomTicketMapper.selectByExample(homsomTicketExample)), homsomTicketMapper.countByExample(homsomTicketExample));
	}
	
	@Override
	public List<TicketExcelDTO> getExcelDTOs(TicketListRequest ticketListRequest) {
		HomsomTicketExample homsomTicketExample = getTicketListExample(ticketListRequest);
		return HomsomTicketCovertor.convertHomsomTicket2TicketExcelDTO(homsomTicketMapper.selectByExample(homsomTicketExample));
	}

	@Override
	public ListResult<CounterpartyConfigDTO> getCounterpartyConfig(CounterpartyConfigRequestDTO requestDTO) {
		CounterpartySearchDTO searchDTO = new CounterpartySearchDTO();
		searchDTO.setBusinessContractNo(getContractNo(requestDTO.getChannel()));
		searchDTO.setPage(requestDTO.getPage());
		searchDTO.setPageSize(requestDTO.getPageSize());
		PageResult<BusinessCounterpartyDTO> counterpartyDTOList = assetFactorBusinessService.queryBusinessCounterpartyDTO(searchDTO);
		return CounterpartyConfigDTOConvert(requestDTO, counterpartyDTOList);
	}
	
	@Override
	public ListResult<LoanAuditDTO> getLoanAuditList(LoanAuditListRequest loanAuditListRequest) {
		HomsomDailyLoanReportExample homsomDailyLoanReportExample = getLoanAuditListExample(loanAuditListRequest);
		homsomDailyLoanReportExample.setLimitStart((loanAuditListRequest.getPage() - 1) * loanAuditListRequest.getPageSize());
		homsomDailyLoanReportExample.setLimitEnd(loanAuditListRequest.getPageSize());
		return ListResult.success(HomsomLoanConvertor.convertHomsomDailyLoanReport2LoanAuditDTO(homsomDailyLoanReportMapper.selectByExample(homsomDailyLoanReportExample)), homsomDailyLoanReportMapper.countByExample(homsomDailyLoanReportExample));
	}
	
	@Override
	public List<LoanAuditExcelDTO> getExcelDTOs(LoanAuditListRequest loanAuditListRequest) {
		HomsomDailyLoanReportExample homsomDailyLoanReportExample = getLoanAuditListExample(loanAuditListRequest);
		return HomsomLoanConvertor.convertHomsomDailyLoanReport2LoanAuditExcelDTO(homsomDailyLoanReportMapper.selectByExample(homsomDailyLoanReportExample));
	}
	
	@Override
	public ListResult<LoanAuditDTO> getLoanAuditDetailList(LoanAuditDetailRequest loanAuditDetailRequest) {
		HomsomDailyLoanReportExample homsomDailyLoanReportExample = new HomsomDailyLoanReportExample();
		com.cana.homsom.dao.po.HomsomDailyLoanReportExample.Criteria criteria = homsomDailyLoanReportExample.createCriteria();
		criteria.andChannelEqualTo(loanAuditDetailRequest.getChannel().name());
		criteria.andCounterpartyIdNotEqualTo("");
		if(StringUtils.isNotBlank(loanAuditDetailRequest.getLoanDate()))
			criteria.andDateEqualTo(loanAuditDetailRequest.getLoanDate());
		homsomDailyLoanReportExample.setOrderByClause("counterparty_name");
		homsomDailyLoanReportExample.setLimitStart((loanAuditDetailRequest.getPage() - 1) * loanAuditDetailRequest.getPageSize());
		homsomDailyLoanReportExample.setLimitEnd(loanAuditDetailRequest.getPageSize());
		return ListResult.success(HomsomLoanConvertor.convertHomsomDailyLoanReport2LoanAuditDTO(homsomDailyLoanReportMapper.selectByExample(homsomDailyLoanReportExample)), homsomDailyLoanReportMapper.countByExample(homsomDailyLoanReportExample));
	}
	
	@Override
	public String generateSettlementRedisKey() {
		return DateTimeUtil.datetime12()+ seqGen.getNextSeq(Constants.SEQUENCE_NAME_HOMSOM_SETTLEMENT_REDIS_KEY, 4);
	}
	
	@Override
	public void importSettlement(List<List<String>> list, String userId, String rediskey, String mediaId, Channel channel) {
		HomsomSettlementTicketExcelRedisDTO redisDTO = null;
		Map<String, HomsomSettlementTicketExcelDTO> passMap = null;
		Map<String, HomsomSettlementTicketExcelDTO> unPassMap = null;
		Map<String, HomsomSettlementTicketExcelDTO> currentPassMap = new HashMap<>();
		String key = RedisUtils.generateHomsomSettlementRedisKeyByOperator(rediskey, userId);
		Object excelRedis = redisCache.read(key);
		if(null != excelRedis) {
			redisDTO = (HomsomSettlementTicketExcelRedisDTO)excelRedis;
			passMap = redisDTO.getPassMap();
			unPassMap = redisDTO.getUnPassMap();
		} else {
			redisDTO = new HomsomSettlementTicketExcelRedisDTO();
			passMap = new HashMap<>();
			unPassMap = new HashMap<>();
		}
		String settlementDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		for (List<String> ticket : list) {
			HomsomSettlementTicketExcelDTO homsomSettlementTicketExcelDTO = new HomsomSettlementTicketExcelDTO();
			homsomSettlementTicketExcelDTO.setBuybackDate(ticket.get(6));
			homsomSettlementTicketExcelDTO.setSettleDate(settlementDate);
			genenrateExcelDTO(ticket, homsomSettlementTicketExcelDTO);
			try {
				if(!DateTimeUtil.validateDate10((homsomSettlementTicketExcelDTO.getBuybackDate())))
					throw WebException.instance("回购日期格式不正确");
				checkForm(homsomSettlementTicketExcelDTO, passMap, currentPassMap);
			} catch (WebException e) {
				addUnPass(e.getMessage(), homsomSettlementTicketExcelDTO, unPassMap);
			}
		}
		checkTicket(currentPassMap, unPassMap, channel);
		if(currentPassMap.size() > 0) {
			Map<String, String> loanId2TicketNoMap = new HashMap<>();
			List<LoanInfo> loanInfos = getLoanInfo(currentPassMap, loanId2TicketNoMap, channel);
			for (LoanInfo loanInfo : loanInfos) {
				HomsomSettlementTicketExcelDTO excelDTO = null;
				try {
					excelDTO = currentPassMap.get(loanId2TicketNoMap.get(loanInfo.getId()));
					checkLoanInfo(excelDTO, currentPassMap, loanId2TicketNoMap, loanInfo);
					excelDTO.setBuybackDate(loanInfo.getDueDate());
					excelDTO.setLoanDays(DateTimeUtil.diffInDay(DateTimeUtil.parseDate10(excelDTO.getLoanDate()), DateTimeUtil.parseDate10(excelDTO.getSettleDate())));
					calInterestAndCommonAttribute(loanInfo, excelDTO, mediaId, channel);
				} catch (WebException e) {
					moveUnpass(e.getMessage(), excelDTO, unPassMap, currentPassMap);
				}
			}
		}
		checkSettlement(currentPassMap, unPassMap, channel);
		redisDTO.setUnPassMap(unPassMap);
		passMap.putAll(currentPassMap);
		redisDTO.setPassMap(passMap);
		redisCache.save(key, redisDTO);
	}
	
	@Override
	public void importBuyBack(List<List<String>> list, String userId, String rediskey, String mediaId, Channel channel) {
		HomsomBuyBackTicketExcelRedisDTO redisDTO = null;
		Map<String, HomsomBuyBackTicketExcelDTO> passMap = null;
		Map<String, HomsomBuyBackTicketExcelDTO> unPassMap = null;
		Map<String, HomsomBuyBackTicketExcelDTO> currentPassMap = new HashMap<>();
		String key = RedisUtils.generateHomsomSettlementRedisKeyByOperator(rediskey, userId);
		Object excelRedis = redisCache.read(key);
		if(null != excelRedis) {
			redisDTO = (HomsomBuyBackTicketExcelRedisDTO)excelRedis;
			passMap = redisDTO.getPassMap();
			unPassMap = redisDTO.getUnPassMap();
		} else {
			redisDTO = new HomsomBuyBackTicketExcelRedisDTO();
			passMap = new HashMap<>();
			unPassMap = new HashMap<>();
		}
		String buyBackDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		for (List<String> ticket : list) {
			HomsomBuyBackTicketExcelDTO homsomBuyBackTicketExcelDTO = new HomsomBuyBackTicketExcelDTO();
			homsomBuyBackTicketExcelDTO.setBuybackDate(buyBackDate);
			genenrateExcelDTO(ticket, homsomBuyBackTicketExcelDTO);
			try {
				checkForm(homsomBuyBackTicketExcelDTO, passMap, currentPassMap);
			} catch (WebException e) {
				addUnPass(e.getMessage(), homsomBuyBackTicketExcelDTO, unPassMap);
			}
		}
		checkTicket(currentPassMap, unPassMap, channel);
		if(currentPassMap.size() > 0) {
			Map<String, String> loanId2TicketNoMap = new HashMap<>();
			List<LoanInfo> loanInfos = getLoanInfo(currentPassMap, loanId2TicketNoMap, channel);
			for (LoanInfo loanInfo : loanInfos) {
				HomsomBuyBackTicketExcelDTO excelDTO = null;
				try {
					excelDTO = currentPassMap.get(loanId2TicketNoMap.get(loanInfo.getId()));
					checkLoanInfo(excelDTO, currentPassMap, loanId2TicketNoMap, loanInfo);
					excelDTO.setLoanDays(DateTimeUtil.diffInDay(DateTimeUtil.parseDate10(excelDTO.getLoanDate()), DateTimeUtil.parseDate10(excelDTO.getBuybackDate())));
					calInterestAndCommonAttribute(loanInfo, excelDTO, mediaId, channel);
				} catch (WebException e) {
					moveUnpass(e.getMessage(), excelDTO, unPassMap, currentPassMap);
				}
			}
		}
		checkSettlement(currentPassMap, unPassMap, channel);
		redisDTO.setUnPassMap(unPassMap);
		passMap.putAll(currentPassMap);
		redisDTO.setPassMap(passMap);
		redisCache.save(key, redisDTO);
	}
	
	private <T extends HomsomSettlementTicketDTO> void checkTicket(Map<String, T> currentPassMap, Map<String, T> unPassMap, Channel channel) {
		if(currentPassMap.size() > 0) {
			HomsomTicketExample homsomTicketExample = new HomsomTicketExample();
			homsomTicketExample.createCriteria().andChannelEqualTo(channel.name()).andTicketNoIn(new ArrayList<>(currentPassMap.keySet()));
			List<HomsomTicket> homsomTickets = homsomTicketMapper.selectByExample(homsomTicketExample);
			boolean checkExist = homsomTickets.size() != currentPassMap.size();
			Set<String> alreadyExist = new HashSet<>();
			for (HomsomTicket homsomTicket : homsomTickets) {
				T excelDTO = null;
				try {
					excelDTO = currentPassMap.get(homsomTicket.getTicketNo());
					if(!TicketDetailState.LOAN_GENERATED.name().equals(homsomTicket.getDetailState()))
						throw WebException.instance("没有放款");
					if(!homsomTicket.getAgentCode().equals(excelDTO.getAgentCode()))
						throw WebException.instance("单位编号和已存在的客票不一致");
					if(!homsomTicket.getIssueDate().equals(excelDTO.getIssueDate()))
						throw WebException.instance("开票日期和已存在的客票不一致");
					if(!homsomTicket.getAgentName().equals(excelDTO.getCounterpartyName()))
						throw WebException.instance("单位客户名称和已存在的客票不一致");
					if(!homsomTicket.getOrderId().equals(excelDTO.getOrderId()))
						throw WebException.instance("销售单号和已存在的客票一致");
					if(checkExist)
						alreadyExist.add(excelDTO.getTicketNo());
				} catch (WebException e) {
					moveUnpass(e.getMessage(), excelDTO, unPassMap, currentPassMap);
				}
			}
			if(checkExist) {
				Iterator<Entry<String, T>> iterators = currentPassMap.entrySet().iterator();
				Map.Entry<String, T> entry = null;
				while (iterators.hasNext()) {
					entry = iterators.next();
					try {
						if(!alreadyExist.contains(entry.getKey()))
							throw WebException.instance("不存在该客票记录");
						else
							alreadyExist.remove(entry.getKey());
					}  catch (WebException e) {
						entry.getValue().setReason(e.getMessage());
						iterators.remove();
						unPassMap.put(entry.getKey(), entry.getValue());
					}
				}
			}
		}
	}
	
	private <T extends HomsomSettlementTicketDTO> List<LoanInfo> getLoanInfo(Map<String, T> currentPassMap, Map<String, String> loanId2TicketNoMap, Channel channel) {
		AssetInvoiceBasicInfoExample assetInvoiceBasicInfoExample = new AssetInvoiceBasicInfoExample();
		assetInvoiceBasicInfoExample.createCriteria().andBusinessContractNoEqualTo(getContractNo(channel));
		AssetInvoiceInfoExample assetInvoiceInfoExample = new AssetInvoiceInfoExample();
		assetInvoiceInfoExample.createCriteria().andInvoiceBaseIdEqualTo(assetInvoiceBasicInfoMapper.selectByExample(assetInvoiceBasicInfoExample).get(0).getId()).andInvoiceNoIn(new ArrayList<>(currentPassMap.keySet()));
		List<AssetInvoiceInfo> assetInvoiceInfos = assetInvoiceInfoMapper.selectByExample(assetInvoiceInfoExample);
		for (AssetInvoiceInfo assetInvoiceInfo : assetInvoiceInfos)
			loanId2TicketNoMap.put(assetInvoiceInfo.getLoanInfoId(), assetInvoiceInfo.getInvoiceNo());
		LoanInfoExample loanInfoExample = new LoanInfoExample();
		loanInfoExample.createCriteria().andIdIn(new ArrayList<>(loanId2TicketNoMap.keySet()));
		return loanInforMapper.selectByExample(loanInfoExample);
	}
	
	private <T extends HomsomSettlementTicketDTO> void checkSettlement(Map<String, T> currentPassMap, Map<String, T> unPassMap, Channel channel) {
		if(currentPassMap.size() > 0) {
			HomsomSettlementTrackExample homsomSettlementTrackExample = new HomsomSettlementTrackExample();
			homsomSettlementTrackExample.createCriteria().andChannelEqualTo(channel.name()).andTicketNoIn(new ArrayList<>(currentPassMap.keySet()));
			List<HomsomSettlementTrack> homsomSettlementTracks = homsomSettlementTrackMapper.selectByExample(homsomSettlementTrackExample);
			for (HomsomSettlementTrack homsomSettlementTrack : homsomSettlementTracks)
				moveUnpass("该客票已核销或者已回购", currentPassMap.get(homsomSettlementTrack.getTicketNo()), unPassMap, currentPassMap);
		}
	}
	
	private <T extends HomsomSettlementTicketDTO> void genenrateExcelDTO(List<String> ticket, T excelDTO) {
		excelDTO.setIssueDate(ticket.get(0));
		excelDTO.setAgentCode(ticket.get(1));
		excelDTO.setCounterpartyName(ticket.get(2));
		excelDTO.setOrderId(ticket.get(3));
		excelDTO.setTicketNo(ticket.get(4));
		excelDTO.setLoanAmount((long) (Float.parseFloat(ticket.get(7)) * 100));
		excelDTO.setLoanDate(ticket.get(8));
		excelDTO.setRemark(ticket.get(9));
		excelDTO.setBillingPeriod(ticket.get(11));
		excelDTO.setSettlePeriod(ticket.get(12));
		excelDTO.setRefundFeeOf20per((long) (Float.parseFloat(ticket.get(14)) * 100));
	}
	
	private <T extends HomsomSettlementTicketDTO> void checkForm(T excelDTO, Map<String, T> passMap, Map<String, T> currentPassMap) {
		if(!DateTimeUtil.validateDate10((excelDTO.getIssueDate())))
			throw WebException.instance("开票日期格式不正确");
		if(!DateTimeUtil.validateDate10((excelDTO.getLoanDate())))
			throw WebException.instance("提现日期格式不正确");
		if(passMap.containsKey(excelDTO.getTicketNo()) || currentPassMap.containsKey(excelDTO.getTicketNo()))
			throw WebException.instance("重复的客票");
		currentPassMap.put(excelDTO.getTicketNo(), excelDTO);
	}
	
	private <T extends HomsomSettlementTicketDTO> void addUnPass(String reason, T excelDTO, Map<String, T> unPassMap) {
		excelDTO.setReason(reason);
		unPassMap.put(excelDTO.getTicketNo(), excelDTO);
	}
	
	private <T extends HomsomSettlementTicketDTO> void moveUnpass(String reason, T excelDTO, Map<String, T> unPassMap, Map<String, T> currentPassMap) {
		currentPassMap.remove(excelDTO.getTicketNo());
		addUnPass(reason, excelDTO, unPassMap);
	}
	
	private <T extends HomsomSettlementTicketDTO> void checkLoanInfo(T excelDTO, Map<String, T> currentPassMap, Map<String, String> loanId2TicketNoMap, LoanInfo loanInfo) {
		excelDTO = currentPassMap.get(loanId2TicketNoMap.get(loanInfo.getId()));
		if(!loanInfo.getFinanceAmount().equals(excelDTO.getLoanAmount()))
			throw WebException.instance("提现金额有误");
		if(!loanInfo.getLoanDate().equals(excelDTO.getLoanDate()))
			throw WebException.instance("提现日期有误");
	}
	
	private <T extends HomsomSettlementTicketDTO> void calInterestAndCommonAttribute(LoanInfo loanInfo, T excelDTO, String mediaId, Channel channel) {
		excelDTO.setId(HomsomIDGenerator.generateSettlementTicketId());
		excelDTO.setChannel(channel.name());
		excelDTO.setState(SettlementState.UNSETTLE.name());
		excelDTO.setLoanNo(loanInfo.getId());
		excelDTO.setCounterpartyId(loanInfo.getCounterpartyId());
		excelDTO.setInterestAmount(InterestCalcUtil.calcInterest(excelDTO.getLoanAmount(), InterestRateUnit.valueOf(loanInfo.getInterestRateUnit()), loanInfo.getInterestRate(), DateUnit.DAY, excelDTO.getLoanDays()));
		excelDTO.setMediaId(mediaId);
		excelDTO.setCreateTime(new Date());
		excelDTO.setUpdateTime(new Date());
	}
	
	@Override
	public ListResult<HomsomSettlementTicketExcelDTO> getHomsomSettlementTicketExcelDTOFromRedis(HomsomSettlementTicketExcelListRequest homsomSettlementTicketExcelListRequest) {
		Object object = redisCache.read(RedisUtils.generateHomsomSettlementRedisKeyByOperator(homsomSettlementTicketExcelListRequest.getRediskey(), homsomSettlementTicketExcelListRequest.getUserId()));
		if(object == null)
			return ListResult.fail("无数据");
		HomsomSettlementTicketExcelRedisDTO redisDTO = (HomsomSettlementTicketExcelRedisDTO) object;
		List<HomsomSettlementTicketExcelDTO> homsomSettlementTicketExcelDTOs = homsomSettlementTicketExcelListRequest.isPassed() ? new ArrayList<>(redisDTO.getPassMap().values()) : new ArrayList<>(redisDTO.getUnPassMap().values());
		int totalNum = homsomSettlementTicketExcelDTOs.size();
		homsomSettlementTicketExcelDTOs = homsomSettlementTicketExcelDTOs.subList((homsomSettlementTicketExcelListRequest.getPage() -1) * homsomSettlementTicketExcelListRequest.getPageSize(), homsomSettlementTicketExcelListRequest.getPage() * homsomSettlementTicketExcelListRequest.getPageSize() < totalNum ? homsomSettlementTicketExcelListRequest.getPage() * homsomSettlementTicketExcelListRequest.getPageSize() : totalNum);
		return ListResult.success(homsomSettlementTicketExcelDTOs, totalNum);
	}

	@Override
	public ListResult<HomsomBuyBackTicketExcelDTO> getHomsomBuyBackTicketExcelDTOFromRedis(HomsomBuyBackTicketExcelListRequest homsomBuyBackTicketExcelListRequest) {
		Object object = redisCache.read(RedisUtils.generateHomsomSettlementRedisKeyByOperator(homsomBuyBackTicketExcelListRequest.getRediskey(), homsomBuyBackTicketExcelListRequest.getUserId()));
		if(object == null)
			return ListResult.fail("无数据");
		HomsomBuyBackTicketExcelRedisDTO redisDTO = (HomsomBuyBackTicketExcelRedisDTO) object;
		List<HomsomBuyBackTicketExcelDTO> homsomBuyBackTicketExcelDTOs = homsomBuyBackTicketExcelListRequest.isPassed() ? new ArrayList<>(redisDTO.getPassMap().values()) : new ArrayList<>(redisDTO.getUnPassMap().values());
		int totalNum = homsomBuyBackTicketExcelDTOs.size();
		homsomBuyBackTicketExcelDTOs = homsomBuyBackTicketExcelDTOs.subList((homsomBuyBackTicketExcelListRequest.getPage() -1) * homsomBuyBackTicketExcelListRequest.getPageSize(), homsomBuyBackTicketExcelListRequest.getPage() * homsomBuyBackTicketExcelListRequest.getPageSize() < totalNum ? homsomBuyBackTicketExcelListRequest.getPage() * homsomBuyBackTicketExcelListRequest.getPageSize() : totalNum);
		return ListResult.success(homsomBuyBackTicketExcelDTOs, totalNum);
	}
	
	@Override
	public ListResult<TicketLoanDetailDTO> queryTicketLoanDetail(TicketLoanRequestDTO queryDTO){
		ListResult<TicketLoanDetailDTO> result = new ListResult<TicketLoanDetailDTO>();
		switch(queryDTO.getPaymentType()){
		case SETTLEMENT:
			HomsomSettlementTicketExample settlementTicketExample = new HomsomSettlementTicketExample();
			settlementTicketExample.createCriteria().andChannelEqualTo(queryDTO.getChannel().name()).andSettlementCounterpartyIdEqualTo(queryDTO.getSettlementCounterpartyId()).andStateEqualTo(queryDTO.getState().name());
			result.setTotalNum(homsomSettlementTicketMapper.countByExample(settlementTicketExample));
			settlementTicketExample.setOrderByClause("update_time");
			settlementTicketExample.setLimitStart((queryDTO.getPage() - 1) * queryDTO.getPageSize());
			settlementTicketExample.setLimitEnd(queryDTO.getPageSize());
			result.setData(settlementTicketConvert(homsomSettlementTicketMapper.selectByExample(settlementTicketExample)));
			break;
		case BUYBACK:
			HomsomBuybackTicketExample buybackTicketExample = new HomsomBuybackTicketExample();
			buybackTicketExample.createCriteria().andChannelEqualTo(queryDTO.getChannel().name()).andBuybackCounterpartyIdEqualTo(queryDTO.getBuybackCounterpartyId()).andStateEqualTo(queryDTO.getState().name());
			result.setTotalNum(homsomBuybackTicketMapper.countByExample(buybackTicketExample));
			buybackTicketExample.setOrderByClause("update_time");
			buybackTicketExample.setLimitStart((queryDTO.getPage() - 1) * queryDTO.getPageSize());
			buybackTicketExample.setLimitEnd(queryDTO.getPageSize());
			result.setData(buyBackTicketConvert(homsomBuybackTicketMapper.selectByExample(buybackTicketExample)));
			break;
		default:
			throw WebException.instance("没有该类型的还款");
		}
		result.setStatus(AjaxResponseStatus.SUCCESS);
		return result;
	}

	@Override
	public ListResult<SettlementCounterpartyDTO> querySettlementCounterpartyDetail(TicketLoanRequestDTO queryDTO){
		ListResult<SettlementCounterpartyDTO> result = new ListResult<SettlementCounterpartyDTO>();
		switch(queryDTO.getPaymentType()){
		case SETTLEMENT:
			HomsomSettlementCounterpartyExample settlementCounterpartyExample = new HomsomSettlementCounterpartyExample();
			settlementCounterpartyExample.createCriteria().andChannelEqualTo(queryDTO.getChannel().name()).andStateEqualTo(SettlementState.UNSETTLE.name());
			result.setTotalNum(homsomSettlementCounterpartyMapper.countByExample(settlementCounterpartyExample));
			settlementCounterpartyExample.setOrderByClause("update_time");
			settlementCounterpartyExample.setLimitStart((queryDTO.getPage() - 1) * queryDTO.getPageSize());
			settlementCounterpartyExample.setLimitEnd(queryDTO.getPageSize());
			result.setData(settlementCounterpartyConvert(homsomSettlementCounterpartyMapper.selectByExample(settlementCounterpartyExample)));
			break;
		case BUYBACK:
			HomsomBuybackCounterpartyExample buybackCounterpartyExample = new HomsomBuybackCounterpartyExample();
			buybackCounterpartyExample.createCriteria().andChannelEqualTo(queryDTO.getChannel().name()).andStateEqualTo(SettlementState.UNSETTLE.name());
			result.setTotalNum(homsomBuybackCounterpartyMapper.countByExample(buybackCounterpartyExample));
			buybackCounterpartyExample.setOrderByClause("update_time");
			buybackCounterpartyExample.setLimitStart((queryDTO.getPage() - 1) * queryDTO.getPageSize());
			buybackCounterpartyExample.setLimitEnd(queryDTO.getPageSize());
			result.setData(buyBackCounterpartyConvert(homsomBuybackCounterpartyMapper.selectByExample(buybackCounterpartyExample)));
			break;
		default:
			throw WebException.instance("没有该类型的还款");
		}
		result.setStatus(AjaxResponseStatus.SUCCESS);
		return result;
	}
	
	@Override
	public SettlementSummaryDTO querySummaryDataByPaymentType(PaymentDataRequestDTO queryDTO){
		SettlementSummaryDTO settlementSummary = new SettlementSummaryDTO();
		settlementSummary.setDate(commonService.getCurrentDate());
		SettlementSummary summaryData = new SettlementSummary();
		switch(queryDTO.getPaymentType()){
		case SETTLEMENT:
			summaryData = settlementSummaryMapper.querySettleSummaryData(queryDTO);
			break;
		case BUYBACK:
			summaryData = settlementSummaryMapper.queryBuybackSummaryData(queryDTO);
			break;
		default:
			throw WebException.instance("没有该类型的还款");
		}
		if(null == summaryData){
			summaryData = new SettlementSummary();
		}
		summaryDataConvert(settlementSummary, summaryData);
		return settlementSummary;
	}
	
	@Override
	public Map<String, StatementExcelDTO> getSettlementExcelDetailList(Channel channel) {
		Map<String, StatementExcelDTO> returnMap = new HashMap<>();
		List<HomsomSettlementCounterparty> homsomSettlementCounterparties = getUnSettleAndTodaySettledHomsomSettlementCounterparties(channel);
		if(homsomSettlementCounterparties.size() > 0) {
			List<String> homsomSettlementCounterpartyIds = new ArrayList<>();
			for(HomsomSettlementCounterparty homsomSettlementCounterparty : homsomSettlementCounterparties)
				homsomSettlementCounterpartyIds.add(homsomSettlementCounterparty.getId());
			HomsomSettlementTicketExample homsomSettlementTicketExample = new HomsomSettlementTicketExample();
			homsomSettlementTicketExample.createCriteria().andSettlementCounterpartyIdIn(homsomSettlementCounterpartyIds);
			List<HomsomSettlementTicket> homsomSettlementTickets = homsomSettlementTicketMapper.selectByExample(homsomSettlementTicketExample);
			Map<String, HomsomSettlementTicket> handledHomsomSettlementTicketMap = new HashMap<>();
			SimpleDateFormat formater = new SimpleDateFormat("yyyy/M/d");
			SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
			for (HomsomSettlementTicket homsomSettlementTicket : homsomSettlementTickets) {
				try {
					boolean isHandled = handledHomsomSettlementTicketMap.containsKey(homsomSettlementTicket.getTicketNo());
					HomsomSettlementTicket oldHomsomSettlementTicket = handledHomsomSettlementTicketMap.get(homsomSettlementTicket.getTicketNo());
					SettlementState newHomsomSettlementTicketSettlementState = SettlementState.valueOf(homsomSettlementTicket.getState());
					if(!isHandled || (isHandled && SettlementState.valueOf(oldHomsomSettlementTicket.getState()) == SettlementState.UNSETTLE && (newHomsomSettlementTicketSettlementState == SettlementState.SETTLED || (newHomsomSettlementTicketSettlementState == SettlementState.UNSETTLE && oldHomsomSettlementTicket.getUpdateTime().before(homsomSettlementTicket.getUpdateTime()))))) {
						StatementExcelDTO statementExcelDTO = new StatementExcelDTO();
						BeanUtils.copyProperties(homsomSettlementTicket, statementExcelDTO);
						statementExcelDTO.setSettleAmount(MoneyUtil.cent2Yuan(homsomSettlementTicket.getLoanAmount() + homsomSettlementTicket.getInterestAmount()));
						statementExcelDTO.setSettleDate(formater.format(parser.parse(homsomSettlementTicket.getSettleDate())));
						statementExcelDTO.setIssueDate(formater.format(parser.parse(homsomSettlementTicket.getIssueDate())));
						statementExcelDTO.setBuybackDate(formater.format(parser.parse(homsomSettlementTicket.getBuybackDate())));
						statementExcelDTO.setLoanAmount(MoneyUtil.cent2Yuan(homsomSettlementTicket.getLoanAmount()));
						statementExcelDTO.setLoanDate(formater.format(parser.parse(homsomSettlementTicket.getLoanDate())));
						statementExcelDTO.setBillingPeriod("自然月");
						statementExcelDTO.setSettlePeriod(homsomSettlementTicket.getLoanDays() + "天");
						statementExcelDTO.setInterestAmount(MoneyUtil.cent2Yuan(homsomSettlementTicket.getInterestAmount()));
						statementExcelDTO.setRefundFeeOf20per(MoneyUtil.cent2Yuan(homsomSettlementTicket.getRefundFeeOf20per()));
						statementExcelDTO.setState(SettlementState.valueOf(homsomSettlementTicket.getState()).desc());
						returnMap.put(homsomSettlementTicket.getTicketNo(),statementExcelDTO);
						handledHomsomSettlementTicketMap.put(homsomSettlementTicket.getTicketNo(), homsomSettlementTicket);
					}
				} catch (ParseException e) {
					throw WebException.instance("时间格式化错误");
				}
			}
		}
		return returnMap;
	}

	@Override
	public Collection<StatementExcelDTO> getBuyBackExcelDetailList(Channel channel) {
		Map<String, StatementExcelDTO> returnMap = new HashMap<>();
		List<HomsomBuybackCounterparty> homsomBuybackCounterparties = getUnBuybackAndTodayBuybackHomsomBuybackCounterparties();
		if(homsomBuybackCounterparties.size() > 0) {
			List<String> homsomBuybackCounterpartyIds = new ArrayList<>();
			for(HomsomBuybackCounterparty homsomBuybackCounterparty : homsomBuybackCounterparties)
				homsomBuybackCounterpartyIds.add(homsomBuybackCounterparty.getId());
			HomsomBuybackTicketExample homsomBuybackTicketExample = new HomsomBuybackTicketExample();
			homsomBuybackTicketExample.createCriteria().andBuybackCounterpartyIdIn(homsomBuybackCounterpartyIds);
			List<HomsomBuybackTicket> homsomBuybackTickets = homsomBuybackTicketMapper.selectByExample(homsomBuybackTicketExample);
			Map<String, HomsomBuybackTicket> handledHomsomBuybackTicketMap = new HashMap<>();
			SimpleDateFormat formater = new SimpleDateFormat("yyyy/M/d");
			SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
			for (HomsomBuybackTicket homsomBuybackTicket : homsomBuybackTickets) {
				try {
					boolean isHandled = handledHomsomBuybackTicketMap.containsKey(homsomBuybackTicket.getTicketNo());
					HomsomBuybackTicket oldHomsomBuybackTicket = handledHomsomBuybackTicketMap.get(homsomBuybackTicket.getTicketNo());
					SettlementState newHomsomBuybackTicketSettlementState = SettlementState.valueOf(homsomBuybackTicket.getState());
					if(!isHandled || (isHandled && SettlementState.valueOf(oldHomsomBuybackTicket.getState()) == SettlementState.UNSETTLE && (newHomsomBuybackTicketSettlementState == SettlementState.SETTLED || (newHomsomBuybackTicketSettlementState == SettlementState.UNSETTLE && oldHomsomBuybackTicket.getUpdateTime().before(homsomBuybackTicket.getUpdateTime()))))) {
						StatementExcelDTO statementExcelDTO = new StatementExcelDTO();
						BeanUtils.copyProperties(homsomBuybackTicket, statementExcelDTO);
						statementExcelDTO.setIssueDate(formater.format(parser.parse(homsomBuybackTicket.getIssueDate())));
						statementExcelDTO.setSettleAmount(MoneyUtil.cent2Yuan(homsomBuybackTicket.getLoanAmount() + homsomBuybackTicket.getInterestAmount()));
						statementExcelDTO.setSettleDate(formater.format(parser.parse(homsomBuybackTicket.getBuybackDate())));
						statementExcelDTO.setBuybackDate(formater.format(parser.parse(homsomBuybackTicket.getBuybackDate())));
						statementExcelDTO.setLoanAmount(MoneyUtil.cent2Yuan(homsomBuybackTicket.getLoanAmount()));
						statementExcelDTO.setLoanDate(formater.format(parser.parse(homsomBuybackTicket.getLoanDate())));
						statementExcelDTO.setBillingPeriod("自然月");
						statementExcelDTO.setSettlePeriod(homsomBuybackTicket.getLoanDays() + "天");
						statementExcelDTO.setInterestAmount(MoneyUtil.cent2Yuan(homsomBuybackTicket.getInterestAmount()));
						statementExcelDTO.setRefundFeeOf20per(MoneyUtil.cent2Yuan(homsomBuybackTicket.getRefundFeeOf20per()));
						statementExcelDTO.setState(SettlementState.valueOf(homsomBuybackTicket.getState()).desc());
						returnMap.put(homsomBuybackTicket.getTicketNo(), statementExcelDTO);
						handledHomsomBuybackTicketMap.put(homsomBuybackTicket.getTicketNo(), homsomBuybackTicket);
					}
				} catch (ParseException e) {
					throw WebException.instance("时间格式化错误");
				}
			}
		}
		return returnMap.values();
	}
	
	@Override
	public Collection<HomsomSettlementCounterpartyExcelDTO> getSettlementCounterpartyExcelList(Channel channel) {
		Collection<HomsomSettlementCounterpartyExcelDTO> returnValue = new ArrayList<>();
		List<HomsomSettlementCounterparty> homsomSettlementCounterparties = getUnSettleAndTodaySettledHomsomSettlementCounterparties(channel);
		if(homsomSettlementCounterparties.size() > 0) {
			Map<String, HomsomSettlementCounterparty> counterpartyId2HomsomSettlementCounterpartyMap = new HashMap<>();
			for (HomsomSettlementCounterparty homsomSettlementCounterparty : homsomSettlementCounterparties) {
				if(counterpartyId2HomsomSettlementCounterpartyMap.containsKey(homsomSettlementCounterparty.getCounterpartyId())) {
					HomsomSettlementCounterparty preHomsomSettlementCounterparty = counterpartyId2HomsomSettlementCounterpartyMap.get(homsomSettlementCounterparty.getCounterpartyId());
					preHomsomSettlementCounterparty.setSettleAmount(preHomsomSettlementCounterparty.getSettleAmount() + homsomSettlementCounterparty.getSettleAmount());
					preHomsomSettlementCounterparty.setLoanAmount(preHomsomSettlementCounterparty.getLoanAmount() + homsomSettlementCounterparty.getLoanAmount());
					preHomsomSettlementCounterparty.setInterestAmount(preHomsomSettlementCounterparty.getInterestAmount() + homsomSettlementCounterparty.getInterestAmount());
					preHomsomSettlementCounterparty.setActualSettleAmount(preHomsomSettlementCounterparty.getActualSettleAmount() + homsomSettlementCounterparty.getActualSettleAmount());
					preHomsomSettlementCounterparty.setRefundAmount(preHomsomSettlementCounterparty.getRefundAmount() + homsomSettlementCounterparty.getRefundAmount());
					preHomsomSettlementCounterparty.setBuybackAmount(preHomsomSettlementCounterparty.getBuybackAmount() + homsomSettlementCounterparty.getBuybackAmount());
				} else
					counterpartyId2HomsomSettlementCounterpartyMap.put(homsomSettlementCounterparty.getCounterpartyId(), homsomSettlementCounterparty);
			}
			Collection<HomsomSettlementCounterparty> sortedHomsomSettlementCounterprties = counterpartyId2HomsomSettlementCounterpartyMap.values();
			for (HomsomSettlementCounterparty homsomSettlementCounterparty : sortedHomsomSettlementCounterprties) {
				HomsomSettlementCounterpartyExcelDTO excelDTO = new HomsomSettlementCounterpartyExcelDTO();
				excelDTO.setCounterpartyName(homsomSettlementCounterparty.getCounterpartyName());
				excelDTO.setSettleAmount(MoneyUtil.cent2Yuan(homsomSettlementCounterparty.getSettleAmount()));
				excelDTO.setTotalSettlementAmount(MoneyUtil.cent2Yuan(homsomSettlementCounterparty.getLoanAmount() + homsomSettlementCounterparty.getInterestAmount()));
				excelDTO.setActualSettleAmount(MoneyUtil.cent2Yuan(homsomSettlementCounterparty.getActualSettleAmount()));
				excelDTO.setRefundAmount(MoneyUtil.cent2Yuan(homsomSettlementCounterparty.getRefundAmount()));
				excelDTO.setBuybackAmount(MoneyUtil.cent2Yuan(homsomSettlementCounterparty.getBuybackAmount()));
				returnValue.add(excelDTO);
			}
		}
		return returnValue;
	}

	@Override
	public Collection<HomsomBuybackCounterpartyExcelDTO> getBuybackCounterpartyExcelList(Channel channel) {
		Collection<HomsomBuybackCounterpartyExcelDTO> returnValue = new ArrayList<>();
		List<HomsomBuybackCounterparty> homsomBuybackCounterparties = getUnBuybackAndTodayBuybackHomsomBuybackCounterparties();
		if(homsomBuybackCounterparties.size() > 0) {
			Map<String, HomsomBuybackCounterparty> counterpartyId2HomsomBuybackCounterpartyMap = new HashMap<>();
			for (HomsomBuybackCounterparty homsomBuybackCounterparty : homsomBuybackCounterparties) {
				if(counterpartyId2HomsomBuybackCounterpartyMap.containsKey(homsomBuybackCounterparty.getCounterpartyId())) {
					HomsomBuybackCounterparty preHomsomBuybackCounterparty = counterpartyId2HomsomBuybackCounterpartyMap.get(homsomBuybackCounterparty.getCounterpartyId());
					preHomsomBuybackCounterparty.setLoanAmount(preHomsomBuybackCounterparty.getLoanAmount() + homsomBuybackCounterparty.getLoanAmount());
					preHomsomBuybackCounterparty.setInterestAmount(preHomsomBuybackCounterparty.getInterestAmount() + homsomBuybackCounterparty.getInterestAmount());
					preHomsomBuybackCounterparty.setActualBuybackAmount(preHomsomBuybackCounterparty.getActualBuybackAmount() + homsomBuybackCounterparty.getActualBuybackAmount());
				} else
					counterpartyId2HomsomBuybackCounterpartyMap.put(homsomBuybackCounterparty.getCounterpartyId(), homsomBuybackCounterparty);
			}
			Collection<HomsomBuybackCounterparty> sortedHomsomBuybackCounterparties = counterpartyId2HomsomBuybackCounterpartyMap.values();
			for (HomsomBuybackCounterparty homsomBuybackCounterparty : sortedHomsomBuybackCounterparties) {
				HomsomBuybackCounterpartyExcelDTO excelDTO = new HomsomBuybackCounterpartyExcelDTO();
				excelDTO.setCounterpartyName(homsomBuybackCounterparty.getCounterpartyName());
				excelDTO.setTotalSettlementAmount(MoneyUtil.cent2Yuan(homsomBuybackCounterparty.getLoanAmount() + homsomBuybackCounterparty.getInterestAmount()));
				excelDTO.setActualBuybackAmount(MoneyUtil.cent2Yuan(homsomBuybackCounterparty.getActualBuybackAmount()));
				returnValue.add(excelDTO);
			}
		}
		return returnValue;
	}
	
	@Override
	public List<StatementExcelDTO> getRepaymentNoticeList(Channel channel) {
		String dueDate10 = null;
		try {
			dueDate10 = DateUtils.addDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()), 19, 3);
			int diffInDay = canaCalendarTransactionServiceImpl.getNotBeforeFirstWeekday(dueDate10);
			if(diffInDay != 0)
				dueDate10 = DateUtils.addDate(dueDate10, 19, diffInDay);
		} catch (Exception e) {
			throw WebException.instance("系统错误【时间格式化失败】！");
		}
		List<HomsomRepaymentNoticePO> homsomRepaymentNoticePOs = homsomRepaymentNoticeMapper.getRepaymentNoticeList(channel.name(), getContractNo(channel), dueDate10);
		List<StatementExcelDTO> returnValue = new ArrayList<>();
		for (HomsomRepaymentNoticePO homsomRepaymentNoticePO : homsomRepaymentNoticePOs) {
			StatementExcelDTO statementExcelDTO = new StatementExcelDTO();
			BeanUtils.copyProperties(homsomRepaymentNoticePO, statementExcelDTO);
			int loanDays = DateTimeUtil.diffInDay(DateTimeUtil.parseDate10(homsomRepaymentNoticePO.getLoanDate()), DateTimeUtil.parseDate10(homsomRepaymentNoticePO.getBuybackDate()));
			long interest = InterestCalcUtil.calcInterest(homsomRepaymentNoticePO.getLoanAmount(), InterestRateUnit.valueOf(homsomRepaymentNoticePO.getInterestRateUnit()), homsomRepaymentNoticePO.getInterestRate(), DateUnit.DAY, loanDays);
			statementExcelDTO.setSettlePeriod(loanDays + "天");
			statementExcelDTO.setInterestAmount(MoneyUtil.cent2Yuan(interest));
			statementExcelDTO.setSettleAmount(MoneyUtil.cent2Yuan(interest + homsomRepaymentNoticePO.getLoanAmount()));
			statementExcelDTO.setLoanAmount(MoneyUtil.cent2Yuan(homsomRepaymentNoticePO.getLoanAmount()));
			statementExcelDTO.setSettleDate(homsomRepaymentNoticePO.getBuybackDate());
			statementExcelDTO.setBillingPeriod("自然月");
			statementExcelDTO.setState(SettleStatus.UNSETTLE.desc());
			returnValue.add(statementExcelDTO);
		}
		return returnValue;
	}
	
	@Override
	public ListResult<String> buybackTicketListCheck(TicketLoanRequestDTO queryDTO){
		HomsomBuybackTicketExample example = new HomsomBuybackTicketExample();
		example.createCriteria().andBuybackCounterpartyIdEqualTo(queryDTO.getBuybackCounterpartyId()).andStateEqualTo(queryDTO.getState().name()).andChannelEqualTo(queryDTO.getChannel().name());
		List<HomsomBuybackTicket> buybackTicketList = homsomBuybackTicketMapper.selectByExample(example);
		HomsomSettlementTrackExample settlementTrackExample = new HomsomSettlementTrackExample();
		settlementTrackExample.createCriteria().andTicketNoIn(getAllUnsettleBuybackTicketNo(buybackTicketList)).andChannelEqualTo(queryDTO.getChannel().name());
		List<HomsomSettlementTrack> settlementTrackList = homsomSettlementTrackMapper.selectByExample(settlementTrackExample);
		if(CollectionUtils.isEmpty(settlementTrackList)){
			return ListResult.fail("未找到已核销机票数据，可以回购");
		}else{
			List<String> allSettledBuybackTicketNo = getAllSettledBuybackTicketNo(settlementTrackList);
			return ListResult.success(allSettledBuybackTicketNo, allSettledBuybackTicketNo.size());
		}
	}
	
	private List<String> getAllSettledBuybackTicketNo(List<HomsomSettlementTrack> settlementTrackList){
		List<String> settleTicketNoList = Lists.newArrayList();
		for(HomsomSettlementTrack settlementTrack:settlementTrackList){
			settleTicketNoList.add(settlementTrack.getTicketNo());
		}
		return settleTicketNoList;
	}
	
	private List<String> getAllUnsettleBuybackTicketNo(List<HomsomBuybackTicket> buybackTicketList){
		List<String> unsettleTicketNoList = Lists.newArrayList();
		for(HomsomBuybackTicket buybackTicket:buybackTicketList){
			unsettleTicketNoList.add(buybackTicket.getTicketNo());
		}
		return unsettleTicketNoList;
	}
	
	private void summaryDataConvert(SettlementSummaryDTO settlementSummary, SettlementSummary summaryData){
		settlementSummary.setActualBuybackAmount(MoneyArithUtil.convertMoneyToString(summaryData.getActualBuybackAmount()));
		settlementSummary.setActualSettleAmount(MoneyArithUtil.convertMoneyToString(summaryData.getActualSettleAmount()));
		settlementSummary.setBuybackAmount(MoneyArithUtil.convertMoneyToString(summaryData.getBuybackAmount()));
		settlementSummary.setRefundAmount(MoneyArithUtil.convertMoneyToString(summaryData.getRefundAmount()));
		settlementSummary.setSettleAmount(MoneyArithUtil.convertMoneyToString(summaryData.getSettleAmount()));
	}
	
	private List<TicketLoanDetailDTO> settlementTicketConvert(List<HomsomSettlementTicket> settlementTicketList){
		List<TicketLoanDetailDTO> ticketLoanDetailDTOList = Lists.newArrayList();
		for(HomsomSettlementTicket settlementTicket:settlementTicketList){
			TicketLoanDetailDTO ticketLoanDetailDTO = new TicketLoanDetailDTO();
			BeanUtils.copyProperties(settlementTicket, ticketLoanDetailDTO);
			ticketLoanDetailDTO.setChannel(Channel.valueOf(settlementTicket.getChannel()));
			ticketLoanDetailDTO.setLoanAmount(MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(settlementTicket.getLoanAmount())));
			ticketLoanDetailDTO.setInterestAmount(MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(settlementTicket.getInterestAmount())));
			ticketLoanDetailDTO.setLoanPeriod(DateTimeUtil.diffInDay(
					DateTimeUtil.parseDate10(commonService.getCurrentDate()), DateTimeUtil.parseDate10(settlementTicket.getLoanDate()))+"");
			ticketLoanDetailDTOList.add(ticketLoanDetailDTO);
		}
		return ticketLoanDetailDTOList;
	}
	
	private List<TicketLoanDetailDTO> buyBackTicketConvert(List<HomsomBuybackTicket> buybackTicketList){
		List<TicketLoanDetailDTO> ticketLoanDetailDTOList = Lists.newArrayList();
		for(HomsomBuybackTicket buybackTicket:buybackTicketList){
			TicketLoanDetailDTO ticketLoanDetailDTO = new TicketLoanDetailDTO();
			BeanUtils.copyProperties(buybackTicket, ticketLoanDetailDTO);
			ticketLoanDetailDTO.setChannel(Channel.valueOf(buybackTicket.getChannel()));
			ticketLoanDetailDTO.setLoanAmount(MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(buybackTicket.getLoanAmount())));
			ticketLoanDetailDTO.setInterestAmount(MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(buybackTicket.getInterestAmount())));
			ticketLoanDetailDTO.setLoanPeriod(DateTimeUtil.diffInDay(
					DateTimeUtil.parseDate10(commonService.getCurrentDate()), DateTimeUtil.parseDate10(buybackTicket.getLoanDate()))+"");
			ticketLoanDetailDTOList.add(ticketLoanDetailDTO);
		}
		return ticketLoanDetailDTOList;
	}
	
	private List<SettlementCounterpartyDTO> settlementCounterpartyConvert(List<HomsomSettlementCounterparty> settlementCounterpartyList){
		List<SettlementCounterpartyDTO> settlementCounterpartyDTOList = Lists.newArrayList();
		for(HomsomSettlementCounterparty settlementCounterparty:settlementCounterpartyList){
			SettlementCounterpartyDTO settlementCounterpartyDTO = new SettlementCounterpartyDTO();
			BeanUtils.copyProperties(settlementCounterparty, settlementCounterpartyDTO);
			settlementCounterpartyDTO.setLoanTotalAmount(MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(settlementCounterparty.getLoanAmount() + settlementCounterparty.getInterestAmount() + settlementCounterparty.getOverdueAmount())));
			settlementCounterpartyDTO.setSettleAmount(MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(settlementCounterparty.getSettleAmount())));
			settlementCounterpartyDTO.setActualSettleAmount(MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(settlementCounterparty.getActualSettleAmount())));
			settlementCounterpartyDTO.setRefundAmount(MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(settlementCounterparty.getRefundAmount())));
			settlementCounterpartyDTO.setBuybackAmount(MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(settlementCounterparty.getBuybackAmount())));
			settlementCounterpartyDTO.setState(SettlementState.valueOf(settlementCounterparty.getState()));
			settlementCounterpartyDTOList.add(settlementCounterpartyDTO);
		}
		return settlementCounterpartyDTOList;
	}

	private List<SettlementCounterpartyDTO> buyBackCounterpartyConvert(List<HomsomBuybackCounterparty> homsomBuybackCounterpartyList){
		List<SettlementCounterpartyDTO> settlementCounterpartyDTOList = Lists.newArrayList();
		for(HomsomBuybackCounterparty buybackCounterparty:homsomBuybackCounterpartyList){
			SettlementCounterpartyDTO settlementCounterpartyDTO = new SettlementCounterpartyDTO();
			BeanUtils.copyProperties(buybackCounterparty, settlementCounterpartyDTO);
			settlementCounterpartyDTO.setBuybackAmount(MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(buybackCounterparty.getLoanAmount() + buybackCounterparty.getInterestAmount() + buybackCounterparty.getOverdueAmount())));
			settlementCounterpartyDTO.setActualBuybackAmount(MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(buybackCounterparty.getActualBuybackAmount())));
			settlementCounterpartyDTO.setState(SettlementState.valueOf(buybackCounterparty.getState()));
			settlementCounterpartyDTOList.add(settlementCounterpartyDTO);
		}
		return settlementCounterpartyDTOList;
	}
	
	private HomsomDailyLoanReportExample getLoanAuditListExample(LoanAuditListRequest loanAuditListRequest) {
		HomsomDailyLoanReportExample homsomDailyLoanReportExample = new HomsomDailyLoanReportExample();
		com.cana.homsom.dao.po.HomsomDailyLoanReportExample.Criteria criteria = homsomDailyLoanReportExample.createCriteria();
		criteria.andChannelEqualTo(loanAuditListRequest.getChannel().name());
		criteria.andCounterpartyIdEqualTo("");
		if(StringUtils.isNotBlank(loanAuditListRequest.getStartDate()) && DateTimeUtil.validateDate10(loanAuditListRequest.getStartDate()))
			criteria.andDateGreaterThanOrEqualTo(loanAuditListRequest.getStartDate());
		if(StringUtils.isNotBlank(loanAuditListRequest.getEndDate()) && DateTimeUtil.validateDate10(loanAuditListRequest.getEndDate()))
			criteria.andDateLessThanOrEqualTo(loanAuditListRequest.getEndDate());
		if(!CollectionUtils.isEmpty(loanAuditListRequest.getLoanStates()) && loanAuditListRequest.getLoanStates().size() != LoanState.values().length) {
			List<String> loanStates = new ArrayList<>();
			for(LoanState loanState: loanAuditListRequest.getLoanStates())
				loanStates.add(loanState.name());
			criteria.andLoanStateIn(loanStates);
		}
		homsomDailyLoanReportExample.setOrderByClause("date desc");
		return homsomDailyLoanReportExample;
	}
	
	private HomsomTicketExample getTicketListExample(TicketListRequest ticketListRequest) {
		HomsomTicketExample homsomTicketExample = new HomsomTicketExample();
		com.cana.homsom.dao.po.HomsomTicketExample.Criteria criteria = homsomTicketExample.createCriteria();
		criteria.andChannelEqualTo(ticketListRequest.getChannel().name());
		if(StringUtils.isNotBlank(ticketListRequest.getIssueDateStart()) && DateTimeUtil.validateDate10(ticketListRequest.getIssueDateStart()))
			criteria.andIssueDateGreaterThanOrEqualTo(ticketListRequest.getIssueDateStart());
		if(StringUtils.isNotBlank(ticketListRequest.getIssueDateEnd()) && DateTimeUtil.validateDate10(ticketListRequest.getIssueDateEnd()))
			criteria.andIssueDateLessThanOrEqualTo(ticketListRequest.getIssueDateEnd());
		if(StringUtils.isNotBlank(ticketListRequest.getAgentName()))
			criteria.andAgentNameLike("%" + StringUtils.trim(ticketListRequest.getAgentName()) + "%");
		homsomTicketExample.setOrderByClause("issue_date desc");
		return homsomTicketExample;
	}
	
	private List<HomsomTicket> checkAndConvert(String ticketStr){
		if(StringUtils.isBlank(ticketStr))
			throw WebException.instance(ReturnCode.REQ_PARAMS_ERROR, "客票记录为空");
		List<Ticket> rawTicketList = new Gson().fromJson(ticketStr, new TypeToken<List<Ticket>>() {}.getType());
		if(CollectionUtils.isEmpty(rawTicketList))
			throw WebException.instance(ReturnCode.REQ_PARAMS_ERROR, "客票记录为空");
		List<HomsomTicket> ticketList = new ArrayList<>();
		for(Ticket rawTicket : rawTicketList)
			ticketList.add(checkAndConvert(rawTicket));
		return ticketList;
	}

	private HomsomTicket checkAndConvert(Ticket rawTicket) {
		HomsomTicket ticket = new HomsomTicket();
		ticket.setId(seqGen.getNextSeq(Constants.SEQUENCE_NAME_HOMSOM_TICKET_ID));
		StringUtil.trimObjectFields(rawTicket);
		
		if(StringUtils.isBlank(rawTicket.getAgentCode()))
			throw WebException.instance(ReturnCode.REQ_PARAMS_ERROR, "agentCode不能为空，详情:" + new Gson().toJson(rawTicket));
		if(rawTicket.getAgentCode().length() > 30)
			throw WebException.instance(ReturnCode.REQ_PARAMS_ERROR, "agentCode过长，详情:" + new Gson().toJson(rawTicket));
		ticket.setAgentCode(rawTicket.getAgentCode());
		
		if(StringUtils.isBlank(rawTicket.getAgentName()))
			throw WebException.instance(ReturnCode.REQ_PARAMS_ERROR, "agentName不能为空，详情:" + new Gson().toJson(rawTicket));
		if(rawTicket.getAgentName().length() > 100)
			throw WebException.instance(ReturnCode.REQ_PARAMS_ERROR, "agentName过长，详情:" + new Gson().toJson(rawTicket));
		ticket.setAgentName(rawTicket.getAgentName());
		
		if(StringUtils.isBlank(rawTicket.getOrderId()))
			throw WebException.instance(ReturnCode.REQ_PARAMS_ERROR, "orderId不能为空，详情:" + new Gson().toJson(rawTicket));
		if(rawTicket.getOrderId().length() > 32)
			throw WebException.instance(ReturnCode.REQ_PARAMS_ERROR, "orderId过长，详情:" + new Gson().toJson(rawTicket));
		ticket.setOrderId(rawTicket.getOrderId());
		
		if(rawTicket.getOrderType() !=  OrderType.BOOK)
			throw WebException.instance(ReturnCode.REQ_PARAMS_ERROR, "orderType的值有误，详情:" + new Gson().toJson(rawTicket));
		ticket.setOrderType(rawTicket.getOrderType().name());
		
		if(StringUtils.isBlank(rawTicket.getTicketNo()))
			throw WebException.instance(ReturnCode.REQ_PARAMS_ERROR, "ticketNo不能为空，详情:" + new Gson().toJson(rawTicket));
		if(rawTicket.getTicketNo().length() > 13)
			throw WebException.instance(ReturnCode.REQ_PARAMS_ERROR, "ticketNo过长，详情:" + new Gson().toJson(rawTicket));
		ticket.setTicketNo(rawTicket.getTicketNo());
		
		if(!DateTimeUtil.validateDate10(rawTicket.getIssueDate()))
			throw WebException.instance(ReturnCode.REQ_PARAMS_ERROR, "issueDate格式不正确，详情:" + new Gson().toJson(rawTicket));
		ticket.setIssueDate(rawTicket.getIssueDate());
		
		if(rawTicket.getAmount() < 0)
			throw WebException.instance(ReturnCode.REQ_PARAMS_ERROR, "amount不能为负，详情:" + new Gson().toJson(rawTicket));
		ticket.setAmount(rawTicket.getAmount());
		
		if(!StringUtils.equals(rawTicket.getIsDomestic(), "true") && !StringUtils.equals(rawTicket.getIsDomestic(), "false"))
			throw WebException.instance(ReturnCode.REQ_PARAMS_ERROR, "isDomestic值不对，详情:" + new Gson().toJson(rawTicket));
		ticket.setIsDomestic(StringUtils.equals(rawTicket.getIsDomestic(), "true") ? true : false);
		
		if(rawTicket.getPnr().length() != 6)
			throw WebException.instance(ReturnCode.REQ_PARAMS_ERROR, "pnr必须为6位，详情:" + new Gson().toJson(rawTicket));
		ticket.setPnr(rawTicket.getPnr());
		
		if(StringUtils.isNotBlank(rawTicket.getTicketOfficeNo()) && rawTicket.getTicketOfficeNo().length() > 16)
			throw WebException.instance(ReturnCode.REQ_PARAMS_ERROR, "ticketOfficeNo过长，详情:" + new Gson().toJson(rawTicket));
		ticket.setTicketOfficeNo(rawTicket.getTicketOfficeNo());
		
		if(StringUtils.isNotBlank(rawTicket.getAirline()) && rawTicket.getAirline().length() > 255)
			throw WebException.instance(ReturnCode.REQ_PARAMS_ERROR, "airline过长，详情:" + new Gson().toJson(rawTicket));
		ticket.setAirline(rawTicket.getAirline());
		
		if(StringUtils.isBlank(rawTicket.getItinerary()))
			throw WebException.instance(ReturnCode.REQ_PARAMS_ERROR, "itinerary不能为空，详情:" + new Gson().toJson(rawTicket));
		if(rawTicket.getItinerary().length() > 255)
			throw WebException.instance(ReturnCode.REQ_PARAMS_ERROR, "itinerary过长，详情:" + new Gson().toJson(rawTicket));
		ticket.setItinerary(rawTicket.getItinerary());
		
		if(StringUtils.isNotBlank(rawTicket.getDepartureDateTime()) && rawTicket.getDepartureDateTime().length() > 255)
			throw WebException.instance(ReturnCode.REQ_PARAMS_ERROR, "departureDateTime过长，详情:" + new Gson().toJson(rawTicket));
		ticket.setDepartureDateTime(rawTicket.getDepartureDateTime());
		
		if(StringUtils.isNotBlank(rawTicket.getCabinCode()) && rawTicket.getCabinCode().length() > 32)
			throw WebException.instance(ReturnCode.REQ_PARAMS_ERROR, "cabinCode过长，详情:" + new Gson().toJson(rawTicket));
		ticket.setCabinCode(rawTicket.getCabinCode());
		
		if(StringUtils.isBlank(rawTicket.getPassengerName()))
			throw WebException.instance(ReturnCode.REQ_PARAMS_ERROR, "passengerName不能为空，详情:" + new Gson().toJson(rawTicket));
		if(rawTicket.getPassengerName().length() > 50)
			throw WebException.instance(ReturnCode.REQ_PARAMS_ERROR, "passengerName过长，详情:" + new Gson().toJson(rawTicket));
		ticket.setPassengerName(rawTicket.getPassengerName());
		
		return ticket;
	}
	
	private ListResult<CounterpartyConfigDTO> CounterpartyConfigDTOConvert(CounterpartyConfigRequestDTO requestDTO, PageResult<BusinessCounterpartyDTO> counterpartyDTOList){
		ListResult<CounterpartyConfigDTO> result = new ListResult<CounterpartyConfigDTO>();
		List<CounterpartyConfigDTO> counterpartyConfigDTOList = Lists.newArrayList();
		HomsomCounterpartyConfigExample example = new HomsomCounterpartyConfigExample();
		example.createCriteria().andChannelEqualTo(requestDTO.getChannel().name()).andCounterpartyIdIn(getAllCounterpartyId(counterpartyDTOList.getData()));
		int totalNum = homsomCounterpartyConfigMapper.countByExample(example);
		example.setLimitStart((requestDTO.getPage() - 1) * requestDTO.getPageSize());
		example.setLimitEnd(requestDTO.getPageSize());
		example.setOrderByClause("update_time desc");
		int homsomCounterpartyNum = homsomCounterpartyConfigMapper.countByExample(example);
		List<HomsomCounterpartyConfig> counterpartyConfigList = homsomCounterpartyConfigMapper.selectByExample(example);
		if(homsomCounterpartyNum < counterpartyDTOList.getData().size()){
			Map<String, HomsomCounterpartyConfig> counterpartyConfigMap = convertToMap(counterpartyConfigList);
			for(BusinessCounterpartyDTO businessCounterpartyDTO:counterpartyDTOList.getData()){
				HomsomCounterpartyConfig counterpartyConfig = counterpartyConfigMap.get(businessCounterpartyDTO.getCounterpartyId());
				CounterpartyConfigDTO counterpartyConfigDTO = new CounterpartyConfigDTO();
				if(null == counterpartyConfig){
					counterpartyConfigDTO.setCounterparty(businessCounterpartyDTO.getCounterparty());
					counterpartyConfigDTO.setCounterpartyId(businessCounterpartyDTO.getCounterpartyId());
					counterpartyConfigDTO.setInConfigFlag(Boolean.FALSE);
					counterpartyConfigDTOList.add(counterpartyConfigDTO);
				}else{
					BeanUtils.copyProperties(counterpartyConfig, counterpartyConfigDTO);
					counterpartyConfigDTO.setBuybackPeriod(counterpartyConfig.getBuybackPeriod().toString());
					counterpartyConfigDTO.setLoanPeriod(counterpartyConfig.getLoanPeriod().toString());
					counterpartyConfigDTO.setChannel(Channel.valueOf(counterpartyConfig.getChannel()));
					counterpartyConfigDTO.setInConfigFlag(Boolean.TRUE);
					counterpartyConfigDTO.setRepaymentMethod(RepaymentType.valueOf(counterpartyConfig.getRepaymentMethod()));
					counterpartyConfigDTOList.add(counterpartyConfigDTO);
				}
			}
		}else{
			for(HomsomCounterpartyConfig counterpartyConfig : counterpartyConfigList){
				CounterpartyConfigDTO counterpartyConfigDTO = new CounterpartyConfigDTO();
				BeanUtils.copyProperties(counterpartyConfig, counterpartyConfigDTO);
				counterpartyConfigDTO.setBuybackPeriod(counterpartyConfig.getBuybackPeriod().toString());
				counterpartyConfigDTO.setLoanPeriod(counterpartyConfig.getLoanPeriod().toString());
				counterpartyConfigDTO.setChannel(Channel.valueOf(counterpartyConfig.getChannel()));
				counterpartyConfigDTO.setInConfigFlag(Boolean.TRUE);
				counterpartyConfigDTO.setRepaymentMethod(RepaymentType.valueOf(counterpartyConfig.getRepaymentMethod()));
				counterpartyConfigDTOList.add(counterpartyConfigDTO);
			}
		}
		result.setData(counterpartyConfigDTOList);
		result.setTotalNum(totalNum > counterpartyDTOList.getTotal() ? totalNum : counterpartyDTOList.getTotal());
		result.setStatus(AjaxResponseStatus.SUCCESS);
		return result;
	}
	
	private List<String> getAllCounterpartyId(List<BusinessCounterpartyDTO> counterpartyDTOList){
		List<String> counterpartyIdList = Lists.newArrayList();
		for(BusinessCounterpartyDTO businessCounterpartyDTO:counterpartyDTOList){
			counterpartyIdList.add(businessCounterpartyDTO.getCounterpartyId());
		}
		return counterpartyIdList;
	}
	
	private Map<String, HomsomCounterpartyConfig> convertToMap(List<HomsomCounterpartyConfig> counterpartyConfigList){
		Map<String, HomsomCounterpartyConfig> counterpartyConfigMap = Maps.newHashMap();
		for(HomsomCounterpartyConfig counterpartyConfig : counterpartyConfigList){
			counterpartyConfigMap.put(counterpartyConfig.getCounterpartyId(), counterpartyConfig);
		}
		return counterpartyConfigMap;
	}
	
	private List<HomsomSettlementCounterparty> getUnSettleAndTodaySettledHomsomSettlementCounterparties(Channel channel) {
		HomsomSettlementCounterpartyExample homsomSettlementCounterpartyExample = new HomsomSettlementCounterpartyExample();
		homsomSettlementCounterpartyExample.createCriteria().andChannelEqualTo(channel.name()).andStateEqualTo(SettlementState.UNSETTLE.name());
		homsomSettlementCounterpartyExample.or().andChannelEqualTo(channel.name()).andStateEqualTo(SettlementState.SETTLED.name()).andDealDateEqualTo(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		return homsomSettlementCounterpartyMapper.selectByExample(homsomSettlementCounterpartyExample);
	}

	private List<HomsomBuybackCounterparty> getUnBuybackAndTodayBuybackHomsomBuybackCounterparties() {
		HomsomBuybackCounterpartyExample homsomBuybackCounterpartyExample = new HomsomBuybackCounterpartyExample();
		homsomBuybackCounterpartyExample.createCriteria().andChannelEqualTo(Channel.HOMSOM.name()).andStateEqualTo(SettlementState.UNSETTLE.name());
		homsomBuybackCounterpartyExample.or().andChannelEqualTo(Channel.HOMSOM.name()).andStateEqualTo(SettlementState.SETTLED.name()).andDealDateEqualTo(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		return homsomBuybackCounterpartyMapper.selectByExample(homsomBuybackCounterpartyExample);
	}

	private String getContractNo(Channel channel) {
		String returnValue = null;
		switch (channel) {
		case HOMSOM:
			returnValue = TopsConfReader.getConfContent(Constants.HOMSOM_PARAM_PATH, "HOMSOM-contractNo", ConfScope.R);
			break;
		case SHFH:
			break;
		default:
			break;
		}
		return returnValue;
	}
	
}