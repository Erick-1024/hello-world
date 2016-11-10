package com.cana.yundaex.api;

import com.cana.vbam.common.yundaex.dto.apply.YdCustomerApplyDetailDTO;
import com.cana.vbam.common.yundaex.dto.apply.YdCustomerApplyListQueryDTO;
import com.cana.vbam.common.yundaex.dto.apply.YdCustomerApplyMinDTO;
import com.cana.vbam.common.yundaex.dto.apply.YundaexCustomerAuditResultDTO;
import com.cana.yundaex.common.dto.apply.YdCustomerApplyAddRequestDTO;
import com.travelzen.framework.common.PageList;

/**
 * 韵达项目－授信申请
 * @author xiaoyu
 *
 */
public interface IYundaexAuditApi {

	/**
	 * 保存补充资料到韵达客户申请表中
	 * @param ydCustomerApplyAddRequestDTO
	 */
	void saveAdditionInfo(YdCustomerApplyAddRequestDTO ydCustomerApplyAddRequestDTO);
	
	/**
	 * 审核列表页面审核信息查询方法
	 * @param ydCustomerApplyListQueryDTO
	 * @return
	 */
	PageList<YdCustomerApplyMinDTO> getCustomerApplyList(YdCustomerApplyListQueryDTO ydCustomerApplyListQueryDTO);

	/**
	 * 审核列表详情
	 * @param id
	 * @return
	 */
	YdCustomerApplyDetailDTO getCustomerApplyInfo(String id);

	/**
	 * 额度申请-人工审核
	 * @param resultDTO
	 */
	void auditYundaexCustomer(YundaexCustomerAuditResultDTO resultDTO);

	/**
	 * 引导页面 登陆客户的基本信息查询
	 * @Param userId
	 */
	YdCustomerApplyDetailDTO getUserBaseInfo(String userId);
	
}
