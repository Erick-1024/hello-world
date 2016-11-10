package com.cana.yundaex.service;

import com.cana.yundaex.dao.po.YundaexCustomerGrade;

/**
 * 韵达客户评级接口
 * @author xiaoyu
 *
 */
public interface IYundaexCustomerGradeService {

	/**
	 * 根据客户id获取用户等级信息
	 * @param userId
	 */
	public YundaexCustomerGrade getUserGrade(String userId);
}
