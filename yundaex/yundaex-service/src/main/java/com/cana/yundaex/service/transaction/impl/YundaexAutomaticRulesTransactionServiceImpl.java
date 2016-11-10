package com.cana.yundaex.service.transaction.impl;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.cana.member.api.IUserApi;
import com.cana.vbam.common.yundaex.dto.creditLimit.YundaexAccessCreditLimitCustomerInfoDTO;
import com.cana.yundaex.common.dto.YundaexGradeScoreDTO;
import com.cana.yundaex.common.dto.apply.YdCustomerApply4MemberUserDTO;
import com.cana.yundaex.common.enums.Institution;
import com.cana.yundaex.common.enums.YundaexApplyType;
import com.cana.yundaex.common.enums.YundaexAuditState;
import com.cana.yundaex.common.enums.YundaexCreditLimitGenerateState;
import com.cana.yundaex.common.enums.YundaexGrade;
import com.cana.yundaex.common.util.Constants;
import com.cana.yundaex.dao.mapper.gen.YundaexCompositeCostMapper;
import com.cana.yundaex.dao.mapper.gen.YundaexCustomerApplyMapper;
import com.cana.yundaex.dao.mapper.gen.YundaexCustomerGradeMapper;
import com.cana.yundaex.dao.mapper.gen.YundaexGradeInfoMapper;
import com.cana.yundaex.dao.mapper.gen.YundaexGradeModelMapper;
import com.cana.yundaex.dao.mapper.gen.YundaexOutCustomerMapper;
import com.cana.yundaex.dao.mapper.gen.YundaexTstationInfoMapper;
import com.cana.yundaex.dao.po.YundaexAuditRule;
import com.cana.yundaex.dao.po.YundaexCompositeCost;
import com.cana.yundaex.dao.po.YundaexCompositeCostExample;
import com.cana.yundaex.dao.po.YundaexCustomerApply;
import com.cana.yundaex.dao.po.YundaexCustomerApplyExample;
import com.cana.yundaex.dao.po.YundaexCustomerGrade;
import com.cana.yundaex.dao.po.YundaexCustomerGradeExample;
import com.cana.yundaex.dao.po.YundaexGradeInfo;
import com.cana.yundaex.dao.po.YundaexGradeInfoExample;
import com.cana.yundaex.dao.po.YundaexGradeModel;
import com.cana.yundaex.dao.po.YundaexGradeModelExample;
import com.cana.yundaex.dao.po.YundaexOutCustomer;
import com.cana.yundaex.dao.po.YundaexOutCustomerExample;
import com.cana.yundaex.dao.po.YundaexStationOperation;
import com.cana.yundaex.dao.po.YundaexTstationInfo;
import com.cana.yundaex.dao.po.YundaexTstationInfoExample;
import com.cana.yundaex.service.IYundaexMessageService;
import com.cana.yundaex.service.IYundaexRetryTaskService;
import com.cana.yundaex.service.transaction.IYundaexAutomaticRulesTransactionService;
import com.cana.yundaex.service.utils.NewestStationOperationHolder;
import com.dianping.cat.Cat;
import com.dianping.cat.message.Transaction;
import com.travelzen.framework.core.common.ReturnClass;
import com.travelzen.framework.core.common.ReturnCode;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.time.DateTimeUtil;

@Service
public class YundaexAutomaticRulesTransactionServiceImpl implements IYundaexAutomaticRulesTransactionService {

	private static final ObjectMapper MAPPER = new ObjectMapper();
	@Resource
	private YundaexTstationInfoMapper ydTstationMapper;

	@Resource
	private YundaexCustomerApplyMapper ydCustomerApplyMapper;

	@Resource
	private IYundaexRetryTaskService ydRetryTaskService;

	@Resource
	private YundaexCompositeCostMapper ydCompositeCostMapper;

	@Resource
	private IYundaexMessageService messageService;

	@Resource
	private YundaexGradeModelMapper ydGradeModelMapper;

	@Resource
	private YundaexGradeInfoMapper ydGradeInfoMapper;

	@Resource
	private YundaexCustomerGradeMapper ydCustomerGradeMapper;

	@Resource
	private YundaexOutCustomerMapper ydOutCustomerMapper;
	
	@Resource
	private YundaexTstationInfoMapper ydTstationInfoMapper;
	
	@Resource
    private IUserApi userApi;

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public boolean checkApplyByAutomaticRules(YundaexCustomerApply apply, YundaexAuditRule newestAutomaticRule) {
		String stationNo = apply.getStationNo();
		Transaction t = Cat.newTransaction("service", "系统审核");
		t.addData("stationNo", stationNo);
		ReturnClass retClass = new ReturnClass(ReturnCode.SUCCESS);
		try {
			String applyType = apply.getApplyType(); 
			//接口申请走 系统审核规则
			if(applyType.equals(YundaexApplyType.INTERFACE_APPLY.name()))
				retClass = checkApply(apply, newestAutomaticRule);
			t.setStatus(Transaction.SUCCESS); 
			Cat.logMetricForCount("系统审核成功");
		} catch (Exception e) {
			logger.error(stationNo + "系统审核失败", e);
			t.setStatus(e);
			Cat.logError(e);
			Cat.logMetricForCount("系统审核失败");
			throw e;
		}finally {
			t.complete();
		}
		
		// 揽派件增长率
		BigDecimal rate = getRecSendRate(stationNo);
		String retMsg = retClass.getMessage();
		ReturnCode retCode = retClass.getRetCode();
		if (ReturnCode.SUCCESS.equals(retCode)) {
			updateYDCustomerApplyByStation(stationNo, rate, YundaexAuditState.ACCESS.name(), null,newestAutomaticRule.getBatchNo());
			return true;
		} else {
			updateYDCustomerApplyByStation(stationNo, rate, YundaexAuditState.NOTACCESS.name(), retMsg,newestAutomaticRule.getBatchNo());
			if(apply.getNotifyFlag()){
				ydRetryTaskService.createAuditResultNotify(Institution.yundaex, stationNo,YundaexAuditState.NOTACCESS.name(), null, null);
				messageService.sendYundaexAuditResultMessageAndMail(apply);
			}
			return false;
		}
	}

	/**
	 * 根据站点编号 更新客户申请表
	 * 
	 * @param stationNo
	 * @param auditState
	 * @param auditRemarks
	 */
	private void updateYDCustomerApplyByStation(String stationNo, BigDecimal rate, String auditState, String auditRemarks,
			Integer batchNo) {
		YundaexCustomerApply customerApply = new YundaexCustomerApply();
		customerApply.setAutomaticAuditRuleBatchNo(batchNo);
		customerApply.setAccessAutomaticState(auditState);
		customerApply.setAutomaticAuditRemarks(auditRemarks);
		customerApply.setRecandsendGrowthRate(rate==null? BigDecimal.ZERO:rate);
		if (YundaexAuditState.ACCESS.name().equals(auditState))
			customerApply.setAccessManualState(YundaexAuditState.WAIT.name());
		YundaexCustomerApplyExample example = new YundaexCustomerApplyExample();
		example.createCriteria().andStationNoEqualTo(stationNo);
		ydCustomerApplyMapper.updateByExampleSelective(customerApply, example);

	}

	/**
	 * 检查申请是否符合韵达系统审核规则
	 * 
	 * @param apply
	 * @param newestAuditRule
	 * @return
	 */
	private ReturnClass checkApply(YundaexCustomerApply apply, YundaexAuditRule newestAuditRule) {
		String stationNo = apply.getStationNo();
		// 申请区域不能在西藏自治区/新疆维吾尔自治区
		String applycustomerAddress = newestAuditRule.getApplycustomerAddress();

		if (applycustomerAddress.indexOf(apply.getProvince()) != -1) {
			// 站点省份属于西藏，新疆等地区
			return new ReturnClass(ReturnCode.YP5106, applycustomerAddress);
		}
		// 与韵达合作年限大于等于２年
		int cooperationPeriod = newestAuditRule.getCooperationPeriod();
		Long busiLimit = apply.getBusiLimit();
		if (busiLimit < cooperationPeriod)
			return new ReturnClass(ReturnCode.YP5107, cooperationPeriod);
		//韵达站点数据 多于24条
		YundaexTstationInfoExample example = new YundaexTstationInfoExample();
		example.createCriteria().andStationNoEqualTo(stationNo);
		List<YundaexTstationInfo> stationInfos = ydTstationMapper.selectByExample(example);
		if (stationInfos == null || stationInfos.size() < cooperationPeriod*12)
			return new ReturnClass(ReturnCode.YP5109);
		// 揽派件增长率
		BigDecimal rate = getRecSendRate(stationNo);
		if (rate == null || rate.compareTo(newestAuditRule.getReceiveSendGrowthRate()) ==-1)
			return new ReturnClass(ReturnCode.YP5108, bigDecimal2percent(rate==null?new BigDecimal(0):rate),bigDecimal2percent(newestAuditRule.getReceiveSendGrowthRate()));

		return new ReturnClass(ReturnCode.SUCCESS);
	}

	
	/**
	 * 计算站点最近12个月与上12个月揽派件总数的比例
	 * 
	 * @param stationNo
	 * @return
	 */
	private BigDecimal getRecSendRate(String stationNo) {
		String farBeginDate = dateCalculateAndFormat(24);
		String farEndDate = dateCalculateAndFormat(12);
		YundaexTstationInfoExample farExample = new YundaexTstationInfoExample();
		farExample.createCriteria().andStationNoEqualTo(stationNo).andStatmonthGreaterThanOrEqualTo(farBeginDate)
				.andStatmonthLessThan(farEndDate);
		List<YundaexTstationInfo> farStationInfos = ydTstationMapper.selectByExample(farExample);
		if (CollectionUtils.isEmpty(farStationInfos))
			return null;
		int farTotal = 0;
		for (YundaexTstationInfo stationIfo : farStationInfos) {
			farTotal += stationIfo.getReceiveTotal() + stationIfo.getSendTotal();
		}

		String nearBeginDate = dateCalculateAndFormat(12);
		String nearEndDate = dateCalculateAndFormat(0);
		YundaexTstationInfoExample nearExample = new YundaexTstationInfoExample();
		nearExample.createCriteria().andStationNoEqualTo(stationNo).andStatmonthGreaterThanOrEqualTo(nearBeginDate)
				.andStatmonthLessThan(nearEndDate);
		List<YundaexTstationInfo> nearStationInfos = ydTstationMapper.selectByExample(nearExample);
		if (CollectionUtils.isEmpty(nearStationInfos))
			return null;
		int nearTotal = 0;
		for (YundaexTstationInfo stationIfon : nearStationInfos) {
			nearTotal += stationIfon.getReceiveTotal() + stationIfon.getSendTotal();
		}
		BigDecimal far = new BigDecimal(farTotal==0?1:farTotal);
		BigDecimal near = new BigDecimal(nearTotal);
		BigDecimal rate = near.subtract(far).divide(far, 5, BigDecimal.ROUND_HALF_UP);
		return rate;
	}

	/**
	 * 当前时间 - 指定月份 并转换成当月的第一天，yyyy-MM格式 2016年1月16日 - 0 = 2016-01
	 * 2016年1月16日 - 1 = 2015-12
	 * 
	 * @param minusMonths
	 * @return
	 */
	private String dateCalculateAndFormat(int minusMonths) {
		Date vaildDate = new DateTime().minusMonths(minusMonths).toDate();
		Date vaildDatetruncate = DateTimeUtil.truncate(vaildDate, Calendar.MONTH);
		String vaildDateFormat = DateTimeUtil.format(vaildDatetruncate, "yyyy-MM");
		return vaildDateFormat;
	}

	/**
	 * 将 0.05 转换成 "5%"
	 * 
	 * @param bigDecimal
	 * @return
	 */
	private String bigDecimal2percent(BigDecimal bigDecimal) {
		return bigDecimal.multiply(new BigDecimal(100)).toString() + "%";
	}

	/**
	 * 系统评级
	 * 
	 * @throws Exception
	 */
	@Override
	public boolean gradeCustomerLevel(YundaexCustomerApply apply) throws Exception {
		String stationNo = apply.getStationNo();
		//计算保证金与日资金需求的比率.
		BigDecimal bailRatio = getBailRatio(apply);
		//非接口数据不比较 保证金与日资金需求的比率
		if(apply.getApplyType().equals(YundaexApplyType.INTERFACE_APPLY.name())&&bailRatio.compareTo(new BigDecimal(Constants.DayMoney))==-1){
			updateCustomerApplyGradeState(stationNo,bailRatio, YundaexAuditState.NOTACCESS.name(), null);
			if(apply.getNotifyFlag()){
				//发送审核结果通知
				sendNotify(apply);
			}
			return false;
		}else{
			Transaction t = Cat.newTransaction("service", "评分计算");
			t.addData("stationNo", stationNo);
			BigDecimal score = new BigDecimal(0);
			try {
				//封装 评分模型DTO
			    YundaexGradeScoreDTO ydGradeScoreDTO = getGradeScoreDTO(apply);
				// 计算客户分数
				score = getGradeScore(ydGradeScoreDTO);
				t.setStatus(Transaction.SUCCESS);
				Cat.logMetricForCount("评分计算成功");
			} catch (Exception e) {
				logger.error(stationNo + "评分计算失败", e);
				t.setStatus(e);
				Cat.logError(e);
				Cat.logMetricForCount("评分计算失败");
				throw e;
			} finally {
				t.complete();
			}
			//进行评级操作
			return grade(score,bailRatio,apply);
		}
	}

	/**
	 * 计算保证金与日资金需求的比率.
	 * @param apply
	 * @return
	 */
	private BigDecimal getBailRatio(YundaexCustomerApply apply) {
		BigDecimal bailBalance = new BigDecimal(apply.getBailBalance()==null?0:apply.getBailBalance());
		BigDecimal bail =  getBail(apply.getId());
		//获取日资金需求
		BigDecimal dayBail = bail.divide(new BigDecimal(30), 2, BigDecimal.ROUND_HALF_UP);
		BigDecimal bailRatio = bailBalance.divide(new BigDecimal(100)).divide(dayBail, 4, BigDecimal.ROUND_HALF_UP);
		return bailRatio;
	}

	/**
	 * 评级模型，对评级分数满足要求的，ACCESS
	 * @param score
	 * @param bailRatio
	 * @param apply
	 * @return
	 * @throws Exception
	 */
	private boolean grade(BigDecimal score, BigDecimal bailRatio, YundaexCustomerApply apply) throws Exception {
		String stationNo = apply.getStationNo();
		YundaexGradeInfo yundaexGradeInfo = getYundaexGradeInfoByScore(score);
		if (yundaexGradeInfo != null) {
			// 把信息插入到韵达客户评级等级表中
			insertYDCustomerGrade(yundaexGradeInfo, stationNo, score);
			//线下数据直接通过评分
			if (score.compareTo(new BigDecimal(Constants.minScore))==1 || apply.getApplyType().equals(YundaexApplyType.OFFLINE_APPLY.name())) {
				// 更改GradeState为access,CreditLimitGenerateState为wait
				updateCustomerApplyGradeState(stationNo, bailRatio,YundaexAuditState.ACCESS.name(), null);
				// 创建新用户 保存站点编号与memberId 关联表
				createAndSaveUser(apply);
				return true;
			} else {
				// 评分小于等于70
				String reason = "你的评级分数为：" + score+",小于等于"+Constants.minScore;
				updateCustomerApplyGradeState(stationNo, bailRatio,YundaexAuditState.NOTACCESS.name(), reason);
				if(apply.getNotifyFlag()){
					//发送审核结果通知
					sendNotify(apply);
				}
				return false;
			}
		} else {
			throw WebException.instance("客户评级信息为空，分数为："+score);
		}
	}

	private void createAndSaveUser(YundaexCustomerApply apply) throws Exception {
		String userId = userApi.generateUserId();
		//保存站点编号与memberId 关联表
		saveUserComparison(userId, apply.getStationNo(), apply.getStationName());
		 // 创建重试任务 将审核通过客户信息插入到member_user中
		 createCustomerByCredit(apply, userId);
	}

	private void sendNotify(YundaexCustomerApply apply) {
		messageService.sendYundaexAuditResultMessageAndMail(apply);
		ydRetryTaskService.createAuditResultNotify(Institution.yundaex, apply.getStationNo(),YundaexAuditState.NOTACCESS.name(), null, null);
	}

	/**
	 * 转换为评级需要的对象
	 * @param apply
	 * @return
	 */
	private YundaexGradeScoreDTO getGradeScoreDTO(YundaexCustomerApply apply) {
		YundaexGradeScoreDTO ydGradeScoreDTO = new YundaexGradeScoreDTO();
		BeanUtils.copyProperties(apply, ydGradeScoreDTO);
		String stationNo = apply.getStationNo();
		String city = apply.getCity();
		YundaexCompositeCostExample costExample = new YundaexCompositeCostExample();
		costExample.createCriteria().andStationCityLike("%" + city + "%");
		List<YundaexCompositeCost> ydCompositeCosts = ydCompositeCostMapper.selectByExample(costExample);
		if(CollectionUtils.isNotEmpty(ydCompositeCosts)){
			YundaexCompositeCost ydCompositeCost = ydCompositeCosts.get(0);
			String cityLevel = ydCompositeCost.getCityLevel();   		// 城市划分
			ydGradeScoreDTO.setCityLevel(cityLevel);
		}else{
			ydGradeScoreDTO.setCityLevel("OTHER");
		}
		BigDecimal recSendRate = getRecSendRate(stationNo); // 揽派件增长率
		ydGradeScoreDTO.setRecAndSendGrowthRate(recSendRate);
		return ydGradeScoreDTO;
	}

	private void createCustomerByCredit(YundaexCustomerApply ydCustomerAply, String userId) {
		YdCustomerApply4MemberUserDTO ydCustomerApply4MemberUserDTO = new YdCustomerApply4MemberUserDTO();
		ydCustomerApply4MemberUserDTO.setAuditorId(ydCustomerAply.getAuditorId());
		ydCustomerApply4MemberUserDTO.setBusinessLicenceNo(ydCustomerAply.getBusinessLicenceNo());
		ydCustomerApply4MemberUserDTO.setBusinessLicenceMediaId(ydCustomerAply.getBusinessLicenceMediaId());
		ydCustomerApply4MemberUserDTO.setCustEmail(ydCustomerAply.getCustEmail());
		ydCustomerApply4MemberUserDTO.setCustName(ydCustomerAply.getCustName());
		ydCustomerApply4MemberUserDTO.setCustPhone(ydCustomerAply.getCustPhone());
		ydCustomerApply4MemberUserDTO.setOrganizationNo(ydCustomerAply.getOrganizationNo());
		ydCustomerApply4MemberUserDTO.setOrganizationMediaId(ydCustomerAply.getOrganizationMediaId());
		ydCustomerApply4MemberUserDTO.setStationName(ydCustomerAply.getStationName());
		ydCustomerApply4MemberUserDTO.setTaxRegistrationCertificateNo(ydCustomerAply.getTaxRegistrationCertificateNo());
		ydCustomerApply4MemberUserDTO.setTaxRegistrationCertificateMediaId(ydCustomerAply.getTaxRegistrationCertificateMediaId());
		ydCustomerApply4MemberUserDTO.setLegalIdnoFrontMediaId(ydCustomerAply.getLegalIdnoFrontMediaId());
		ydCustomerApply4MemberUserDTO.setLegalIdnoBackMediaId(ydCustomerAply.getLegalIdnoBackMediaId());
		ydCustomerApply4MemberUserDTO.setUserId(userId);
		ydRetryTaskService.createYdCreateCustomer(ydCustomerApply4MemberUserDTO, ydCustomerAply.getId());
	}
	
	/**
	 * 站点编号与memberId 关联表
	 * 
	 * @param memberId
	 * @param stationNo
	 * @param stationName
	 */
	private void saveUserComparison(String memberId, String stationNo, String stationName) {
		YundaexOutCustomer ydOutCustomer = new YundaexOutCustomer();
		ydOutCustomer.setMemberId(memberId);
		ydOutCustomer.setStationNo(stationNo);
		ydOutCustomer.setInstitutionId(Institution.yundaex.name());
		ydOutCustomer.setStationName(stationName);
		ydOutCustomerMapper.insert(ydOutCustomer);
	}

	private void updateCustomerApplyGradeState(String stationNo, BigDecimal bailRatio, String state, String message) {
		YundaexCustomerApply customerApply = new YundaexCustomerApply();
		customerApply.setBailRatio(bailRatio);
		customerApply.setGradeState(state);
		customerApply.setGradeStateRemarks(message);
		if (YundaexAuditState.ACCESS.name().equals(state))
			customerApply.setCreditLimitGenerateState(YundaexCreditLimitGenerateState.WAIT.name());
		YundaexCustomerApplyExample example = new YundaexCustomerApplyExample();
		example.createCriteria().andStationNoEqualTo(stationNo);
		ydCustomerApplyMapper.updateByExampleSelective(customerApply, example);
	}

	private void insertYDCustomerGrade(YundaexGradeInfo yundaexGradeInfo, String stationNo, BigDecimal gradeScore) {
		YundaexCustomerGradeExample gradeExample = new YundaexCustomerGradeExample();
		gradeExample.createCriteria().andStationNoEqualTo(stationNo);
		List<YundaexCustomerGrade> ydCustomerGrades = ydCustomerGradeMapper.selectByExample(gradeExample); 
		if(CollectionUtils.isNotEmpty(ydCustomerGrades)){
			YundaexCustomerGrade ydCustomerGrade = ydCustomerGrades.get(0); 
			ydCustomerGrade.setBeta(yundaexGradeInfo.getBeta());
			ydCustomerGrade.setGrade(yundaexGradeInfo.getGrade());
			ydCustomerGrade.setPoints(gradeScore);
			ydCustomerGrade.setStationNo(stationNo);
			ydCustomerGrade.setRaito(yundaexGradeInfo.getRatio());
			ydCustomerGradeMapper.updateByPrimaryKeySelective(ydCustomerGrade);
		}else{
			YundaexCustomerGrade ydCustomerGrade = new YundaexCustomerGrade();
			ydCustomerGrade.setBeta(yundaexGradeInfo.getBeta());
			ydCustomerGrade.setGrade(yundaexGradeInfo.getGrade());
			ydCustomerGrade.setPoints(gradeScore);
			ydCustomerGrade.setStationNo(stationNo);
			ydCustomerGrade.setRaito(yundaexGradeInfo.getRatio());
			ydCustomerGradeMapper.insert(ydCustomerGrade);
		}
	}

	/**
	 * 计算用户评级分数
	 * @param apply
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	private BigDecimal getGradeScore(YundaexGradeScoreDTO apply)throws JsonParseException, JsonMappingException, IOException {
		BigDecimal score = new BigDecimal(0);
		YundaexGradeModelExample gradleExample = new YundaexGradeModelExample();
		List<YundaexGradeModel> ydGradeModelList = ydGradeModelMapper.selectByExample(gradleExample);
		if(CollectionUtils.isNotEmpty(ydGradeModelList)){
			for (YundaexGradeModel ydGradeModel : ydGradeModelList) {
				//根据二级指标获取该对象中的值
				Map<String, Object> map = getSecondTargetValue(ydGradeModel.getSecondTarget(),apply);
				
				if(ydGradeModel.getType().equals(YundaexGrade.INTERVAL.name())){
					score = getScore(ydGradeModel.getDetails(), new BigDecimal(map.get(ydGradeModel.getSecondTarget()).toString()), score, new BigDecimal(ydGradeModel.getWeight()));
					continue;
				}else if(ydGradeModel.getType().equals(YundaexGrade.SINGLE.name())){
					score = getSingleScore(ydGradeModel.getDetails(),map.get(ydGradeModel.getSecondTarget()).toString(), score, new BigDecimal(ydGradeModel.getWeight()));
					continue;
				}else{
					throw WebException.instance("错误的二级指标TYPE");
				}
			}
			return score;
		}else{
			throw WebException.instance("获取评级模型配置表失败");
		}
		
	}
	

/**
	 * 获取月资金需求
	 * @param id
	 * @param divide
	 * @param divide2
	 * @return
	 */
	private BigDecimal getBail(String id) {
		YundaexStationOperation newestStaionOeration = NewestStationOperationHolder.newestStaionOperation;
		YundaexCustomerApply ydCustomerApply = ydCustomerApplyMapper.selectByPrimaryKey(id);
		if(ydCustomerApply == null)
			throw WebException.instance("授信客户的申请不存在");
		if(newestStaionOeration == null)
			throw WebException.instance("站点经营状况信息为空");
		BigDecimal avgReceiveNo = getAvgReceiveOrSendNo(ydCustomerApply.getStationNo(),1);
		BigDecimal receiveGrowthRate = getReceiveOrSendGrowthRate(ydCustomerApply.getStationNo(),1);
		BigDecimal forecastRecNo = avgReceiveNo.multiply(new BigDecimal(1).add(receiveGrowthRate));
		BigDecimal bail = forecastRecNo.multiply(newestStaionOeration.getSendIncome().add(newestStaionOeration.getTransitFeeBail()).add(newestStaionOeration.getOtherFeeBail()));
		return bail;
	}
	
	/**
	 * 计算站点月平均揽派件量(本年揽派件量/12)
	 * @param stationNo
	 * @param i 1：揽件 2：派件
	 * @return
	 */
	private BigDecimal getAvgReceiveOrSendNo(String stationNo,Integer j) {
		YundaexTstationInfoExample example = new YundaexTstationInfoExample();
		String beginDate = dateCalculateAndFormat(0);
		String endDate = dateCalculateAndFormat(12);
		example.createCriteria().andStationNoEqualTo(stationNo).andStatmonthGreaterThanOrEqualTo(endDate).andStatmonthLessThan(beginDate);
		List<YundaexTstationInfo> ydTstationInfos = ydTstationInfoMapper.selectByExample(example);
		int yearOfTotalRec = 0;
		int yearOfTotalSend = 0;
		for(YundaexTstationInfo ts : ydTstationInfos){
			yearOfTotalRec += ts.getReceiveTotal();
			yearOfTotalSend += ts.getSendTotal();
		}
		if(1 == j)
			return new BigDecimal(yearOfTotalRec).divide(new BigDecimal(12),2,BigDecimal.ROUND_HALF_UP);
		else
			return new BigDecimal(yearOfTotalSend).divide(new BigDecimal(12),2,BigDecimal.ROUND_HALF_UP);
		
		 
	}
	
	/**
	 * 计算揽派件增长率
	 * @param stationNo
	 * @param i 1：揽件 2：派件
	 * @return
	 */
	private BigDecimal getReceiveOrSendGrowthRate(String stationNo, int i) {
		YundaexTstationInfoExample example1 = new YundaexTstationInfoExample();
		YundaexTstationInfoExample example2 = new YundaexTstationInfoExample();
		String nearBeginDate = dateCalculateAndFormat(12);
		String nearEndDate = dateCalculateAndFormat(0);
		String farBeginDate = dateCalculateAndFormat(24);
		String farEndDate = dateCalculateAndFormat(12);
		example1.createCriteria().andStationNoEqualTo(stationNo).andStatmonthGreaterThanOrEqualTo(nearBeginDate).andStatmonthLessThan(nearEndDate);
		List<YundaexTstationInfo> nearYdTstationInfos = ydTstationInfoMapper.selectByExample(example1);
		example2.createCriteria().andStationNoEqualTo(stationNo).andStatmonthGreaterThanOrEqualTo(farBeginDate).andStatmonthLessThan(farEndDate);
		List<YundaexTstationInfo> farYdTstationInfos = ydTstationInfoMapper.selectByExample(example2);
		int nearOfTotalRec = 0;   //最近12个月的揽件总量
		int nearOfTotalSend = 0;  //最近12个月的派件总量
		for(YundaexTstationInfo ts : nearYdTstationInfos){
			nearOfTotalRec += ts.getReceiveTotal();
			nearOfTotalSend += ts.getSendTotal();
		}
		int farOfTotalRec = 0;    //上12个月的揽件总量
		int farOfTotalSend = 0;   //上12个月的派件总量 
		for(YundaexTstationInfo ts : farYdTstationInfos){
			farOfTotalRec += ts.getReceiveTotal();
			farOfTotalSend += ts.getSendTotal();
		}
		
		if(i == 1)
			return new BigDecimal(nearOfTotalRec).subtract(new BigDecimal(farOfTotalRec)).divide(new BigDecimal(farOfTotalRec==0?1:farOfTotalRec),5,BigDecimal.ROUND_HALF_UP);
		else
			return new BigDecimal(nearOfTotalSend).subtract(new BigDecimal(farOfTotalSend)).divide(new BigDecimal(farOfTotalSend==0?1:farOfTotalSend),5,BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * 获取 SINGLE("单值类型")的评级分数
	 * @param details
	 * @param param
	 * @param score
	 * @param weight
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
private BigDecimal getSingleScore(String details, String param, BigDecimal score, BigDecimal weight) throws JsonParseException, JsonMappingException, IOException {
	Map<String, String> readValue = MAPPER.readValue(details, Map.class);
	for(String key : readValue.keySet()){
		if(key.indexOf(param)!=-1){
			param = key;
			break;
		}else{
			if("OTHER".equals(key)){
				param = key;
				break;
			}else{
				continue;
			}
		}
	}
	return  new BigDecimal(readValue.get(param)).multiply(weight).add(score);
}

private Map<String, Object> getSecondTargetValue(String secondTarget, YundaexGradeScoreDTO apply) {
	Class clazz = apply.getClass();
	Field[] fields = clazz.getDeclaredFields();
	Map<String, Object> map = new HashMap<String, Object> ();
	for (int i = 0; i < fields.length; i++) {
		try {
			if(fields[i].getName().equals(secondTarget)){
				Object resultObject = invokeMethod(apply, fields[i].getName());
				map.put(fields[i].getName(), resultObject);
				break;
			}
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	return map;
}

	private Object invokeMethod(YundaexGradeScoreDTO apply, String fieldname) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class ownerClass = apply.getClass();
		Method method = null;
		method = ownerClass.getMethod(new StringBuffer("get").append(fieldname.substring(0, 1).toUpperCase()).append(fieldname.substring(1)).toString());
		Object object = null;
		object = method.invoke(apply);
		return object;
}

	/**
	 * 获取 INTERVAL("区间类型")的评级分数
	 * @param details
	 * @param param
	 * @param score
	 * @param weight
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	private BigDecimal getScore(String details, BigDecimal param, BigDecimal score, BigDecimal weight) throws JsonParseException, JsonMappingException, IOException {
		if(StringUtils.isNotBlank(details)){
			Map<String, String> readValue = MAPPER.readValue(details, Map.class);
			for (String key : readValue.keySet()) {
				String[] split = key.split("-");
				// a-b 区间的key
				if (split.length == 2) {
					if (param.compareTo(new BigDecimal(split[0])) >= 0 && param.compareTo(new BigDecimal(split[1])) == -1) {
						// 得分 map.get(key)
						score = new BigDecimal(readValue.get(key)).multiply(weight).add(score);
						break;
					}
				} else {
					if (key.indexOf("<") != -1) {
						if (param.compareTo(new BigDecimal(key.split("<")[1])) == -1) {
							// 得分=map.get(key)
							score = new BigDecimal(readValue.get(key)).multiply(weight).add(score);
							break;
						} else {
							continue;
						}
					} else if (key.indexOf(">") != -1) {
						if (param.compareTo(new BigDecimal(key.split(">")[1])) == 1) {
							// 得分=map.get(key)
							score = new BigDecimal(readValue.get(key)).multiply(weight).add(score);
							break;
						} else {
							continue;
						}
					} else {
						// 没有此key值与参数匹配
						throw WebException.instance("无法解析此key值，key=" + key);
					}
				}
			}
			return score;
		}else{
			throw WebException.instance("此参数:"+param+"的指标信息不存在。");
		}
	}

	@Override
	public YundaexGradeInfo getYundaexGradeInfoByScore(BigDecimal score) {
		if(null == score)
			return null;
		YundaexGradeInfoExample infoExample = new YundaexGradeInfoExample();
		infoExample.createCriteria().andMinPointsLessThan(score).andMaxPointsGreaterThanOrEqualTo(score);
		List<YundaexGradeInfo> ydGradeInfos = ydGradeInfoMapper.selectByExample(infoExample);
		if(CollectionUtils.isNotEmpty(ydGradeInfos))
			return ydGradeInfos.get(0);
		throw WebException.instance("评级分数异常");
	}

	/**
	 * 查询授信状态为finish的申请客户信息
	 */
	@Override
	public List<YundaexAccessCreditLimitCustomerInfoDTO> getYundaexAccessCreditLimitCustomerInfoDTO() {
		YundaexCustomerApplyExample applyExample = new YundaexCustomerApplyExample();
		applyExample.createCriteria().andCreditLimitGenerateStateEqualTo(YundaexCreditLimitGenerateState.FINISH.name());
		List<YundaexCustomerApply> applys = ydCustomerApplyMapper.selectByExample(applyExample);
		
		YundaexOutCustomerExample outCustomerExample = new YundaexOutCustomerExample();
		List<YundaexOutCustomer> outCustomers = ydOutCustomerMapper.selectByExample(outCustomerExample); 
		
		List<YundaexAccessCreditLimitCustomerInfoDTO> list = new ArrayList<>();
		if(CollectionUtils.isEmpty(applys) || CollectionUtils.isEmpty(outCustomers))
			return list;
		for(YundaexCustomerApply yundaexCustomerApply : applys){
			YundaexAccessCreditLimitCustomerInfoDTO customerInfoDTO = new YundaexAccessCreditLimitCustomerInfoDTO();
			customerInfoDTO.setId(yundaexCustomerApply.getId());
			customerInfoDTO.setStationNo(yundaexCustomerApply.getStationNo());
			customerInfoDTO.setStationName(yundaexCustomerApply.getStationName());
			for(YundaexOutCustomer YundaexOutCustomer : outCustomers){
				if(yundaexCustomerApply.getStationNo().equals(YundaexOutCustomer.getStationNo()))
					customerInfoDTO.setMemberId(YundaexOutCustomer.getMemberId());
			}
			list.add(customerInfoDTO);
		}
		
		return list;
	}
}
