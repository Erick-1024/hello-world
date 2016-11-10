package com.cana.member.api;

import com.cana.vbam.common.member.enums.user.UserType;
import com.cana.vbam.common.member.vo.CustomerVo;
import com.cana.vbam.common.member.vo.UserVo;

/**
 * 用户接口
 * @author XuMeng
 *
 */
public interface IMemberQueryApi {

	/**
	 * 通过员工ID查询员工信息，返回包含员工所在的企业
	 * @param userId
	 */
	UserVo findUserById(String userId);

	/**
	 * 查询某个客户类型下的企业名称，查询的客户状态为待激活和已激活的用户
	 * @param customerName，不可为空，完全匹配
	 * @param userType，不可为空
	 */
	CustomerVo findCustomerByName(UserType userType, String customerName);
}
