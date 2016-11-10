/**
 * 
 */
package com.cana.yundaex.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cana.credit.limit.dao.mapper.gen.CreditLimitMapper;
import com.cana.credit.limit.dao.po.CreditLimit;
import com.cana.credit.limit.dao.po.CreditLimitExample;
import com.cana.vbam.common.credit.enums.Institution;
import com.cana.vbam.common.service.IVbamCommonService;
import com.cana.vbam.common.utils.MoneyArithUtil;
import com.cana.vbam.common.yundaex.dto.apply.YdCustomerApplyDetailDTO;
import com.cana.vbam.common.yundaex.dto.creditLimit.YundaexCreditAuditListDTO;
import com.cana.vbam.common.yundaex.dto.creditLimit.YundaexCreditLimitDTO;
import com.cana.vbam.common.yundaex.dto.creditLimit.YundaexCreditListMinDTO;
import com.cana.vbam.common.yundaex.dto.creditLimit.YundaexCreditListQueryDTO;
import com.cana.vbam.common.yundaex.dto.creditLimit.YundaexCreditQueryDTO;
import com.cana.yundaex.common.dto.limit.YdQueryCreditLimitDTO;
import com.cana.yundaex.common.dto.limit.YdQueryCreditLimitResponse;
import com.cana.yundaex.common.enums.YundaexAuditState;
import com.cana.yundaex.common.enums.YundaexCreditLimitGenerateState;
import com.cana.yundaex.common.enums.YundaexMode;
import com.cana.yundaex.common.util.Constants;
import com.cana.yundaex.dao.mapper.gen.YundaexCreditMonitorMapper;
import com.cana.yundaex.dao.mapper.gen.YundaexCustomerApplyMapper;
import com.cana.yundaex.dao.mapper.gen.YundaexCustomerGradeMapper;
import com.cana.yundaex.dao.mapper.gen.YundaexOutCustomerMapper;
import com.cana.yundaex.dao.po.YundaexCreditMonitor;
import com.cana.yundaex.dao.po.YundaexCreditMonitorExample;
import com.cana.yundaex.dao.po.YundaexCustomerApply;
import com.cana.yundaex.dao.po.YundaexCustomerApplyExample;
import com.cana.yundaex.dao.po.YundaexCustomerApplyExample.Criteria;
import com.cana.yundaex.dao.po.YundaexCustomerGrade;
import com.cana.yundaex.dao.po.YundaexCustomerGradeExample;
import com.cana.yundaex.dao.po.YundaexOutCustomer;
import com.cana.yundaex.dao.po.YundaexOutCustomerExample;
import com.cana.yundaex.service.IYundaexAuditService;
import com.cana.yundaex.service.IYundaexCreditService;
import com.cana.yundaex.service.convertors.YundaexCreditConvert;
import com.cana.yundaex.service.transaction.IYundaexAutomaticRulesTransactionService;
import com.cana.yundaex.service.transaction.IYundaexCreditLimitTransactionService;
import com.travelzen.framework.common.PageList;
import com.travelzen.framework.core.common.ReturnCode;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.util.MoneyUtil;
import com.travelzen.framework.util.DateUtils;

/**
 * 韵达项目-授信额度服务接口 实现类
 * 
 * @author guguanggong
 *
 */
@Service
public class YundaexCreditServiceImpl implements IYundaexCreditService {

	@Resource
	private CreditLimitMapper yundaexCreditLimitMapper;
	
	@Resource
	private YundaexOutCustomerMapper yundaexOutCustomerMapper;
	
	@Resource
	private YundaexCustomerApplyMapper yundaexCustomerApplyMapper;
	
	@Resource
	private YundaexCustomerGradeMapper yundaexCustomerGradeMapper;
	
	@Resource
	private YundaexCreditMonitorMapper yundaexCreditMonitorMapper;
	
	@Resource
	private IYundaexCreditLimitTransactionService ydCreditLimitTransactionService;
	
	@Resource
	private IVbamCommonService vbamCommonService;
	
	@Resource
	private IYundaexAuditService  yundaexAuditService;
	
	@Resource
	private IYundaexAutomaticRulesTransactionService  yundaexAutomaticRulesTransactionService;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 分页查询授信额度列表
	 */
	@Override
	public PageList<YundaexCreditLimitDTO> getYundaexCreditList(YundaexCreditQueryDTO yundaexCreditQueryDTO) {
		PageList<YundaexCreditLimitDTO> pageList = new PageList<YundaexCreditLimitDTO>();

		CreditLimitExample example = new CreditLimitExample();
		CreditLimitExample.Criteria criteria = example.createCriteria().andProjectIdEqualTo(Constants.YUNDAEX_ASSET_PROJECT_ID);
		
		int pageSize = yundaexCreditQueryDTO.getPageSize();
		example.setOrderByClause("create_time desc");
		example.setLimitStart((yundaexCreditQueryDTO.getPage() - 1) * pageSize);
		example.setLimitEnd(pageSize); // 一页显示多少条
		
        String companyName = yundaexCreditQueryDTO.getCompanyName();
        String startLimit = yundaexCreditQueryDTO.getLimitStart();
        String endLimit = yundaexCreditQueryDTO.getLimitEnd();
        String startDate = yundaexCreditQueryDTO.getEffectiveDateStart();
        String endDate = yundaexCreditQueryDTO.getEffectiveDateEnd();
        String status = yundaexCreditQueryDTO.getLimitStatus();
		if (StringUtils.isNotBlank(companyName)) {
			criteria.andCompanyNameLike("%"+ companyName +"%");
		}
		if (StringUtils.isNotBlank(startLimit)){
			criteria.andTotalLimitGreaterThanOrEqualTo(Long.valueOf(startLimit) * 100);
		}
        if (StringUtils.isNotBlank(endLimit))
            criteria.andTotalLimitLessThanOrEqualTo(Long.valueOf(endLimit) * 100);
        try {
            if (StringUtils.isNotBlank(startDate))
                criteria.andEffectiveDateGreaterThanOrEqualTo(DateUtils.formatDate(startDate, 19));
            if (StringUtils.isNotBlank(endDate))
                criteria.andEffectiveDateLessThanOrEqualTo(DateUtils.formatDate(endDate, 19));
        } catch (Exception e) {
            throw WebException.instance("输入查询时间格式有误");
        }
        if(StringUtils.isNotBlank(status))
            criteria.andStatusEqualTo(status);
        if(StringUtils.isNotBlank(yundaexCreditQueryDTO.getMemberId()))
        	criteria.andMemberIdEqualTo(yundaexCreditQueryDTO.getMemberId());
		
		List<CreditLimit> yundaexCreditLimits = yundaexCreditLimitMapper.selectByExample(example);

		List<YundaexCreditLimitDTO> yundaexCreditLimitDTOs = YundaexCreditConvert
				.convertYundaexCredit2YundaexCredit(yundaexCreditLimits);
		if (CollectionUtils.isEmpty(yundaexCreditLimitDTOs)) {
			return pageList;
		}
		
		for (YundaexCreditLimitDTO yundaexCreditLimitDTO : yundaexCreditLimitDTOs) {
			YdCustomerApplyDetailDTO applyDetailDTO = yundaexAuditService.getUserBaseInfo(yundaexCreditLimitDTO.getMemberId());
			if (applyDetailDTO != null && StringUtils.isNotBlank(applyDetailDTO.getApplyType())) {
				yundaexCreditLimitDTO.setApplyType(applyDetailDTO.getApplyType());
			}
		}
		
		pageList.setRecords(yundaexCreditLimitDTOs);
		Integer count = yundaexCreditLimitMapper.countByExample(example);
		pageList.setTotalRecords(count);
		return pageList;
	}

	/**
	 * 已审核额度信息查询
	 */
	@Override
	public YdQueryCreditLimitResponse queryCreditLimit(YdQueryCreditLimitDTO ydQueryCreditLimitDTO) {
		
		String stationNo = ydQueryCreditLimitDTO.getStationNo();
		String sign = ydQueryCreditLimitDTO.getSign();
		if(StringUtils.isBlank(stationNo))
			throw WebException.instance(ReturnCode.YP7002);
		if(StringUtils.isBlank(sign))
			throw WebException.instance(ReturnCode.YP5131);
		
		// 对接口数据进入验签
		signVerify(ydQueryCreditLimitDTO);
		
		CreditLimitExample ydCreditLimitExample = new CreditLimitExample();
		YundaexOutCustomerExample ydOutCustomerExample = new YundaexOutCustomerExample();
		ydOutCustomerExample.createCriteria().andStationNoEqualTo(stationNo);
		List<YundaexOutCustomer> ydOutCustomers = yundaexOutCustomerMapper.selectByExample(ydOutCustomerExample);
		if(CollectionUtils.isEmpty(ydOutCustomers))
			throw WebException.instance(ReturnCode.YP7016);
		ydCreditLimitExample.createCriteria().andMemberIdEqualTo(ydOutCustomers.get(0).getMemberId());
		List<CreditLimit> ydCreditLimits = yundaexCreditLimitMapper.selectByExample(ydCreditLimitExample);
		if(CollectionUtils.isEmpty(ydCreditLimits))
            throw WebException.instance(ReturnCode.YP7012);
		return YundaexCreditConvert.convertYundaexCreditLimit2DTO(ydCreditLimits.get(0));
	}

	private void signVerify(YdQueryCreditLimitDTO ydQueryCreditLimitDTO) {
		StringBuffer str = new StringBuffer();
		str.append(ydQueryCreditLimitDTO.getStationNo());
		vbamCommonService.rsaVerify(str.toString().getBytes(), Institution.cana.name(), ydQueryCreditLimitDTO.getSign().getBytes(), true);
	}

	@Override
	public void calculateApplyCreditLimit() {
		YundaexCustomerApplyExample example  = new YundaexCustomerApplyExample();
		example.createCriteria().andCreditLimitGenerateStateEqualTo(YundaexCreditLimitGenerateState.WAIT.name());
		List<YundaexCustomerApply> ydCustomerApplys = yundaexCustomerApplyMapper.selectByExample(example);
		for(YundaexCustomerApply ydCustomerApply : ydCustomerApplys){
			ydCreditLimitTransactionService.calculateApplyCreditLimit(ydCustomerApply);
		}
		logger.info("这次计算额度一共处理了{}个申请",ydCustomerApplys.size());
	}


	/**
	 * 根据memberId获取该用户的额度记录
	 * 
	 * @param memberId
	 *            cana的用户Id
	 * @return
	 */
	public CreditLimit getCreditLimitByMemberId(String memberId) {
		CreditLimitExample example = new CreditLimitExample();
		example.createCriteria().andProjectIdEqualTo(Constants.YUNDAEX_ASSET_PROJECT_ID).andMemberIdEqualTo(memberId)
				.andCreditModeEqualTo(YundaexMode.SYNTHETICAL.name());
		List<CreditLimit> creditLimits = yundaexCreditLimitMapper.lockByExample(example);
		if (creditLimits == null || creditLimits.isEmpty())
			throw WebException.instance("授信额度不存在");
		if (creditLimits.size() > 1)
			throw WebException.instance("存在多个授信额度记录");
		return creditLimits.get(0);
	}

	/**
	 * 授信列表页面，整合数据的方法
	 */
	@Override
	public PageList<YundaexCreditListMinDTO> getYundaexCreditStateList(YundaexCreditListQueryDTO creditQueryDTO) {
		PageList<YundaexCreditListMinDTO> pageList = new PageList<YundaexCreditListMinDTO>();
		YundaexCustomerApplyExample ydCustomerApplyExample = new YundaexCustomerApplyExample();
		createCriteria(ydCustomerApplyExample,creditQueryDTO);
		int pageSize = creditQueryDTO.getPageSize(); 
		ydCustomerApplyExample.setOrderByClause("create_time desc");
		ydCustomerApplyExample.setLimitStart((creditQueryDTO.getPage() -1) * pageSize);
		ydCustomerApplyExample.setLimitEnd(pageSize);
		List<YundaexCustomerApply> ydCustomerApplys = yundaexCustomerApplyMapper.selectByExample(ydCustomerApplyExample);
		if(ydCustomerApplys.size() == 0)
			return pageList;
		//封装出页面所需要MinDTO的数据
		List<YundaexCreditListMinDTO> ydCreditListMinDTOs = YundaexCreditConvert.convertCustomerApply2CustomerApplyMinDTO(ydCustomerApplys);
		for(int i = 0;i<ydCreditListMinDTOs.size();i++){
			YundaexCreditListMinDTO yundaexCreditListMinDTO = ydCreditListMinDTOs.get(i);
			yundaexCreditListMinDTO.setGrade(getGrade(yundaexCreditListMinDTO.getStationNo())==null?"--":getGrade(yundaexCreditListMinDTO.getStationNo()).getGrade());
			yundaexCreditListMinDTO.setPoints(getGrade(yundaexCreditListMinDTO.getStationNo())==null?new BigDecimal(0):getGrade(yundaexCreditListMinDTO.getStationNo()).getPoints());
			yundaexCreditListMinDTO.setTotalLimit(getTotalLimit(yundaexCreditListMinDTO.getStationNo())==null?"--":MoneyUtil.formatMoney(MoneyUtil.cent2Yuan(getTotalLimit(yundaexCreditListMinDTO.getStationNo()))));
		}
		pageList.setRecords(ydCreditListMinDTOs);
		pageList.setTotalRecords(yundaexCustomerApplyMapper.countByExample(ydCustomerApplyExample));
		return pageList;
	}

	/**
	 * 封装搜索条件
	 * @param ydCustomerApplyExample
	 * @param creditQuertDTO
	 */
	private void createCriteria(YundaexCustomerApplyExample ydCustomerApplyExample,
			YundaexCreditListQueryDTO creditQueryDTO) {
		String auditState = creditQueryDTO.getAuditState(); 
		String customerName = creditQueryDTO.getCustomerName(); 
		String startDate = creditQueryDTO.getStartDate(); 
		String endDate = creditQueryDTO.getEndDate();
		Criteria createCriteria = ydCustomerApplyExample.createCriteria(); 
		if(StringUtils.isNotBlank(auditState)){
			if(YundaexCreditLimitGenerateState.FINISH.name().equals(auditState) || YundaexCreditLimitGenerateState.WAIT.name().equals(auditState))
				createCriteria.andCreditLimitGenerateStateEqualTo(auditState);
			else{
				createCriteria.andCreditLimitGenerateStateNotLike(YundaexCreditLimitGenerateState.FINISH.name()).andCreditLimitGenerateStateNotLike(YundaexCreditLimitGenerateState.WAIT.name());
				Criteria createCriteria2 = ydCustomerApplyExample.createCriteria();
				createCriteria2.andCreditLimitGenerateStateIsNull();
				try {
		            if (StringUtils.isNotBlank(startDate))
		            	createCriteria2.andAuditTimeGreaterThanOrEqualTo(startDate);
		            if (StringUtils.isNotBlank(endDate))
		            	createCriteria2.andAuditTimeLessThanOrEqualTo(endDate);
		        } catch (Exception e) {
		            throw WebException.instance("输入查询时间格式有误");
		        }
				if(StringUtils.isNotBlank(customerName)){
					createCriteria2.andStationNameLike("%"+customerName.trim()+"%");
				}
				createCriteria2.andAccessManualStateEqualTo(YundaexAuditState.ACCESS.name());
				ydCustomerApplyExample.or(createCriteria2);
				
			}
		}
		try {
            if (StringUtils.isNotBlank(startDate))
            	createCriteria.andAuditTimeGreaterThanOrEqualTo(startDate);
            if (StringUtils.isNotBlank(endDate))
            	createCriteria.andAuditTimeLessThanOrEqualTo(endDate);
        } catch (Exception e) {
            throw WebException.instance("输入查询时间格式有误");
        }
		if(StringUtils.isNotBlank(customerName)){
			createCriteria.andStationNameLike("%"+customerName.trim()+"%");
		}
		createCriteria.andAccessManualStateEqualTo(YundaexAuditState.ACCESS.name());
	}

	/**
	 * 获取授信额度
	 *    1：outCustomer中没有该数据，return null
	 *    2：outCustomer中有该数据,根据memberId,查找creditLimit中有无授信额度 
	 *       有：返回该totalLimit, 没有：可能因为额度为负值，在limitInfoRemarks字段中抽取该totalLimit.
	 * @param stationNo
	 * @return
	 */
	private String getTotalLimit(String stationNo) {
		YundaexOutCustomerExample outCustomerExample = new YundaexOutCustomerExample();
		outCustomerExample.createCriteria().andStationNoEqualTo(stationNo);
		List<YundaexOutCustomer> ydOutCustomerList = yundaexOutCustomerMapper.selectByExample(outCustomerExample); 
		if(CollectionUtils.isEmpty(ydOutCustomerList))
			return null;
		else{
			String memberId = ydOutCustomerList.get(0).getMemberId();
			CreditLimitExample limitExample = new CreditLimitExample();
			limitExample.createCriteria().andMemberIdEqualTo(memberId).andProjectIdEqualTo(Constants.YUNDAEX_ASSET_PROJECT_ID);
			List<CreditLimit> creditLimitList = yundaexCreditLimitMapper.selectByExample(limitExample); 
			if(CollectionUtils.isEmpty(creditLimitList)){
				YundaexCustomerApplyExample applyExample = new YundaexCustomerApplyExample();
				applyExample.createCriteria().andStationNoEqualTo(stationNo);
				List<YundaexCustomerApply> ydCustomerApplys = yundaexCustomerApplyMapper.selectByExample(applyExample); 
				String limitInfoRemarks = ydCustomerApplys.get(0).getLimitInfoRemarks();
				if(StringUtils.isNotBlank(limitInfoRemarks)){
					String[] split = limitInfoRemarks.split("="); 
					return split[split.length-1];
				}else{
					return null;
				}
			}else
				return creditLimitList.get(0).getTotalLimit().toString();
		}
	}

	private YundaexCustomerGrade getGrade(String stationNo) {
		YundaexCustomerGradeExample gradeExample = new YundaexCustomerGradeExample();
		gradeExample.createCriteria().andStationNoEqualTo(stationNo);
		List<YundaexCustomerGrade> ydCustomerGradeList = yundaexCustomerGradeMapper.selectByExample(gradeExample); 
		if(CollectionUtils.isNotEmpty(ydCustomerGradeList))
			return ydCustomerGradeList.get(0);
		else
			return null;
	}

	@Override
	public PageList<YundaexCreditAuditListDTO> getCreditAuditList(YundaexCreditListQueryDTO creditQueryDTO) {
		PageList<YundaexCreditAuditListDTO> pageList = new PageList<YundaexCreditAuditListDTO>();
		String auditState = creditQueryDTO.getAuditState(); 
		String customerName = creditQueryDTO.getCustomerName(); 
		YundaexCreditMonitorExample creditMonitorExample = new YundaexCreditMonitorExample();
		com.cana.yundaex.dao.po.YundaexCreditMonitorExample.Criteria monitorCriteria = creditMonitorExample.createCriteria(); 
		if(StringUtils.isNotBlank(auditState))
			monitorCriteria.andAuditStatusEqualTo(auditState);
		if(StringUtils.isNotBlank(customerName))
			monitorCriteria.andStationNameLike("%"+customerName+"%");
		//只查询本月数据
		String currentDate = vbamCommonService.getCurrentDate();
		String virtualDate = DateUtils.format(DateUtils.getDate(currentDate, "yyyy-MM-dd"), 23);
//		monitorCriteria.andMonthEqualTo(DateTimeUtil.month7());
		monitorCriteria.andMonthEqualTo(virtualDate);
		int pageSize = creditQueryDTO.getPageSize(); 
		creditMonitorExample.setOrderByClause("create_time desc");
		creditMonitorExample.setLimitStart((creditQueryDTO.getPage() -1) * pageSize);
		creditMonitorExample.setLimitEnd(pageSize);
		
		List<YundaexCreditMonitor> creditMonitors = yundaexCreditMonitorMapper.selectByExample(creditMonitorExample);
		List<YundaexCreditAuditListDTO> list = new ArrayList<>();
		// 循环额度审核数据
		for(YundaexCreditMonitor yundaexCreditMonitor : creditMonitors){
			YundaexCreditAuditListDTO yundaexCreditAuditListDTO = new YundaexCreditAuditListDTO();
			// creditMonitor 是否存在上个月的数据
//			YundaexCreditMonitor lastCreditMonitor = getLastCreditMonitorData(yundaexCreditMonitor);
			yundaexCreditAuditListDTO.setId(yundaexCreditMonitor.getId());
			yundaexCreditAuditListDTO.setMemberId(yundaexCreditMonitor.getMemberId());
			yundaexCreditAuditListDTO.setStationNo(yundaexCreditMonitor.getStationNo());
			yundaexCreditAuditListDTO.setCompanyName(yundaexCreditMonitor.getStationName());
			yundaexCreditAuditListDTO.setBailRatio(new BigDecimal(yundaexCreditMonitor.getBailBalance()).divide(new BigDecimal(yundaexCreditMonitor.getDayRequirements()), 5, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)));
			yundaexCreditAuditListDTO.setGrade(yundaexAutomaticRulesTransactionService.getYundaexGradeInfoByScore(yundaexCreditMonitor.getYundaexGrade()).getGrade());
			yundaexCreditAuditListDTO.setLastGrade(yundaexAutomaticRulesTransactionService.getYundaexGradeInfoByScore(yundaexCreditMonitor.getLastYundaexGrade()).getGrade());
			yundaexCreditAuditListDTO.setPoints(yundaexCreditMonitor.getYundaexGrade());
			yundaexCreditAuditListDTO.setLastPoints(yundaexCreditMonitor.getLastYundaexGrade());
			yundaexCreditAuditListDTO.setTotalLimit(MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(yundaexCreditMonitor.getCreditLimit())));
			yundaexCreditAuditListDTO.setLastTotalLimit(MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(yundaexCreditMonitor.getLastCreditLimit())));
			yundaexCreditAuditListDTO.setAuditDate(yundaexCreditMonitor.getMonth());
			yundaexCreditAuditListDTO.setCreditLimitAuditState(yundaexCreditMonitor.getAuditStatus());
			yundaexCreditAuditListDTO.setCreditLimitAuditStateDesc(YundaexAuditState.valueOf(yundaexCreditMonitor.getAuditStatus()).desc());
			list.add(yundaexCreditAuditListDTO);
			}
		
		pageList.setRecords(list);
		pageList.setTotalRecords(yundaexCreditMonitorMapper.countByExample(creditMonitorExample));
		return pageList;
	}

}
