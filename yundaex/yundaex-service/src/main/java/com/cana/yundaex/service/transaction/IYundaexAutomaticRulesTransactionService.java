package com.cana.yundaex.service.transaction;

import java.math.BigDecimal;
import java.util.List;

import com.cana.vbam.common.yundaex.dto.creditLimit.YundaexAccessCreditLimitCustomerInfoDTO;
import com.cana.yundaex.dao.po.YundaexAuditRule;
import com.cana.yundaex.dao.po.YundaexCustomerApply;
import com.cana.yundaex.dao.po.YundaexGradeInfo;

/**
 * 系统执行的任务
 * @author xiaoyu
 *
 */
public interface IYundaexAutomaticRulesTransactionService {

	/**
	 * 系统审核
	 * @param apply
	 * @param newestAutomaticRule
	 * @return
	 */
	boolean checkApplyByAutomaticRules(YundaexCustomerApply apply, YundaexAuditRule newestAutomaticRule);

	/**
	 * 系统评级
	 * @param apply
	 * @return
	 */
	boolean gradeCustomerLevel (YundaexCustomerApply apply) throws Exception;

	/**
	 * 根据评级分数获取韵达评级信息
	 */
	YundaexGradeInfo getYundaexGradeInfoByScore(BigDecimal score);
	
	/**
	 * 查询所有通过额度审核的客户信息
	 */
	List<YundaexAccessCreditLimitCustomerInfoDTO> getYundaexAccessCreditLimitCustomerInfoDTO();
}
