package com.cana.yundaex.service;


import java.util.List;

import com.cana.vbam.common.yundaex.dto.apply.YdCustomerApplyDetailDTO;
import com.cana.vbam.common.yundaex.dto.apply.YdCustomerApplyListQueryDTO;
import com.cana.vbam.common.yundaex.dto.apply.YdCustomerApplyMinDTO;
import com.cana.yundaex.common.dto.apply.YdCustomerApplyAddRequestDTO;
import com.cana.yundaex.dao.po.YundaexCustomerApply;
import com.travelzen.framework.common.PageList;

public interface IYundaexAuditService {

	/**
	 * 审核列表页面审核信息查询方法
	 * @param ydCustomerApplyListQueryDTO
	 * @return
	 */
	PageList<YdCustomerApplyMinDTO> getYundaexAuditList(YdCustomerApplyListQueryDTO ydCustomerApplyListQueryDTO);

	/**
	 * 审核列表详情信息
	 * @param id
	 * @return
	 */
	YdCustomerApplyDetailDTO getCustomerApplyInfo(String id);

	/**
	 * 保存补充资料到韵达客户申请表中
	 * @param ydCustomerApplyAddRequestDTO
	 */
	void saveAdditionInfo(YdCustomerApplyAddRequestDTO ydCustomerApplyAddRequestDTO);

	/**
	 * 引导页面 登陆客户的基本信息查询
	 * @param userId
	 * @return
	 */
	YdCustomerApplyDetailDTO getUserBaseInfo(String userId);


	/**
	 * 保存 YundaexCustomerApply
	 */
	void insertYundaexCustomerApply(YundaexCustomerApply yundaexCustomerApply);
	
	/**
	 * 保存 yundaexCustomerApplys list集合
	 * @param yundaexCustomerApplys
	 */
	void insertYundaexCustomerApplys(List<YundaexCustomerApply> yundaexCustomerApplys);

}
