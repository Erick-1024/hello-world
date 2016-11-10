package com.cana.yundaex.service.transaction.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.cana.vbam.common.repayment.enums.InterestRateUnit;
import com.cana.vbam.common.utils.MoneyArithUtil;
import com.cana.yundaex.common.dto.InterestRateDTO;
import com.cana.yundaex.common.dto.InterestRateListDTO;
import com.cana.yundaex.common.enums.RepaymentDeadLine;
import com.cana.yundaex.common.enums.RepaymentMethod;
import com.cana.yundaex.common.util.Constants;
import com.cana.yundaex.dao.mapper.gen.InterestRateMapper;
import com.cana.yundaex.dao.po.InterestRate;
import com.cana.yundaex.dao.po.InterestRateExample;
import com.cana.yundaex.dao.po.InterestRateExample.Criteria;
import com.cana.yundaex.dao.po.YundaexCustomerGrade;
import com.cana.yundaex.dao.po.YundaexGradeInfo;
import com.cana.yundaex.service.IYundaexCustomerGradeService;
import com.cana.yundaex.service.transaction.IYundaexAutomaticRulesTransactionService;
import com.cana.yundaex.service.transaction.IYundaexInterestRateTransactionService;
import com.cana.yundaex.service.utils.YundaexIDGenerator;
import com.google.gson.Gson;
import com.travelzen.framework.core.exception.WebException;

/**
 * @author hu
 *
 */
@Service
public class YundaexInterestRateTransactionServiceImpl implements IYundaexInterestRateTransactionService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	@Resource
	private InterestRateMapper rateMapper;
	@Resource
	private IYundaexCustomerGradeService gradeService;
	@Resource
	private IYundaexAutomaticRulesTransactionService yundaexInterestRateTransactionService;
	
	@Override
	public void computeAndSaveInterestRate(String customerId) {
//		BigDecimal userGradeRatio = BigDecimal.valueOf(0.90);
		YundaexCustomerGrade grade= gradeService.getUserGrade(customerId);
		logger.info("韵达客户评分:{}, 等级:{}, 系数值:{}", grade.getPoints(), grade.getGrade(), grade.getRaito());
		BigDecimal userGradeRatio = grade.getRaito();
		for(RepaymentMethod method :RepaymentMethod.values()){
			InterestRate rate = new InterestRate();
			rate.setCustomerId(customerId);
			rate.setRepaymentMethod(method.name());
			rate.setCreateTime(new Date());
			rate.setInterestRate(computeBaseRate(method, method.deadLine(), userGradeRatio));
			rate.setId(YundaexIDGenerator.generateInterestRateId());
			rate.setRateUnit(InterestRateUnit.DAY.name());
			rateMapper.insertSelective(rate);
		}
	}
	
	/**
	 * 基本利率公式=基本利率 - 风险浮动利率幅度 * sum(定价维度*系数*权重）
	 * @return
	 */
	private BigDecimal computeBaseRate(RepaymentMethod method, RepaymentDeadLine deadLine, BigDecimal userGradeRatio){
		BigDecimal baseRate = BigDecimal.valueOf(Double.parseDouble(Constants.BASE_RATE));
		BigDecimal riskFloatRatio = BigDecimal.valueOf(Double.parseDouble(Constants.RISK_FLOAT_RATIO));
		BigDecimal dimensionSum = MoneyArithUtil.mul(BigDecimal.valueOf(method.ratio()), BigDecimal.valueOf(RepaymentMethod.WEIGHT)).add(
			MoneyArithUtil.mul(BigDecimal.valueOf(deadLine.ratio()), BigDecimal.valueOf(RepaymentDeadLine.WEIGHT))).add(
			MoneyArithUtil.mul(userGradeRatio, BigDecimal.valueOf(Double.parseDouble(Constants.CUSTOMER_GRADE_WEIGHT))));
		
		BigDecimal rate = baseRate.subtract(MoneyArithUtil.mul(riskFloatRatio, dimensionSum));
		logger.info("rate: baseRate:{} - riskFloatRatio:{}*({}:{}*weight:{}+{}:{}*weight:{}+userGrade:{}*weight:{}={})",
				Constants.BASE_RATE, Constants.RISK_FLOAT_RATIO, method.desc(), method.ratio(), RepaymentMethod.WEIGHT,
				deadLine.desc(), deadLine.ratio(), RepaymentDeadLine.WEIGHT, userGradeRatio, Constants.CUSTOMER_GRADE_WEIGHT, rate);
		
		BigDecimal dayRate = MoneyArithUtil.divide(rate, BigDecimal.valueOf(360), 5);
		return dayRate;
	}
	
	/**
	 * 根据客户ID，评级分数生成 利率json字符串
	 * @param customerId 用户ID
	 * @param score 客户评级分数
	 * @return
	 */
	@Override
	public String getInterestRate(String customerId, BigDecimal score) {
		YundaexGradeInfo gradeInfo = yundaexInterestRateTransactionService.getYundaexGradeInfoByScore(score);
		BigDecimal userGradeRatio = gradeInfo.getRatio();
		InterestRateListDTO rates = new InterestRateListDTO();
		List<InterestRateDTO> interestRates = new ArrayList<>();
		for(RepaymentMethod method :RepaymentMethod.values()){
			InterestRateDTO rate = new InterestRateDTO();
			rate.setCustomerId(customerId);
			rate.setRepaymentMethod(method.name());
			rate.setCreateTime(new Date());
			rate.setInterestRate(computeBaseRate(method, method.deadLine(), userGradeRatio));
			rate.setId(YundaexIDGenerator.generateInterestRateId());
			rate.setRateUnit(InterestRateUnit.DAY.name());
			interestRates.add(rate);
		}
		rates.setInterestRates(interestRates);
		return new Gson().toJson(rates);
	}

	/**
	 * 根据客户ID修改利率
	 * @param customerId 用户ID
	 * @param interestRate 利率json字符串
	 */
	@Override
	public void updateInterestRate(String customerId, String interestRate) {
		// 检验数据
		validateData(customerId, interestRate);
		
		// 利率json字符串转化为 对象
		InterestRateListDTO interestRateListDTO = new Gson().fromJson(interestRate, InterestRateListDTO.class);
		// 根据客户id修改利率
		if (interestRateListDTO == null) {
			throw WebException.instance("客户利率转化不成功");
		}
		
		// 根据客户ID删除客户利率
		InterestRateExample example = new InterestRateExample();
		Criteria criteria = example.createCriteria();
		criteria.andCustomerIdEqualTo(customerId);
		rateMapper.deleteByExample(example);
		
		// 创建一个客户 多个利率
		List<InterestRateDTO> dtos = interestRateListDTO.getInterestRates();
		for(InterestRateDTO rateDTO : dtos) {
			InterestRate rate = new InterestRate();
			BeanUtils.copyProperties(rateDTO, rate);
			rateMapper.insertSelective(rate);
		}
	}
	
	/**
	 * 检验数据
	 * @param customerId
	 * @param interestRate
	 */
	private void validateData(String customerId, String interestRate){
		if (StringUtils.isBlank(customerId)) {
			throw WebException.instance("客户ID不能为空");
		}

		if (StringUtils.isBlank(interestRate)) {
			throw WebException.instance("客户利率不能为空");
		}
	}
}
