package com.cana.yundaex.server.api.impl;



import javax.annotation.Resource;

import com.cana.member.api.IUserApi;
import com.cana.vbam.common.yundaex.dto.apply.YdCustomerApplyDetailDTO;
import com.cana.vbam.common.yundaex.dto.apply.YdCustomerApplyListQueryDTO;
import com.cana.vbam.common.yundaex.dto.apply.YdCustomerApplyMinDTO;
import com.cana.vbam.common.yundaex.dto.apply.YundaexCustomerAuditResultDTO;
import com.cana.yundaex.api.IYundaexAuditApi;
import com.cana.yundaex.common.dto.apply.YdCustomerApplyAddRequestDTO;
import com.cana.yundaex.dao.mapper.gen.YundaexCustomerApplyMapper;
import com.cana.yundaex.dao.po.YundaexCustomerApply;
import com.cana.yundaex.service.IYundaexAuditService;
import com.cana.yundaex.service.transaction.IYundaexCustomerApplyTransactionService;
import com.travelzen.framework.common.PageList;
import com.travelzen.framework.core.exception.WebException;

/**
 * 韵达　　审核额度实现类
 * @author xiaoyu
 *
 */
public class YundaexAuditApiImpl implements IYundaexAuditApi {

	@Resource
	private IYundaexAuditService yundaexAuditService;
	
	@Resource
	private YundaexCustomerApplyMapper ydCustomerApplyMapper;
	
	
	@Resource
	private IUserApi userApiImpl;
	
	@Resource
	private IYundaexCustomerApplyTransactionService ydCustomerApplyTransactionService;
	
	/**
	 * 保存补充资料
	 */
	@Override
	public void saveAdditionInfo(YdCustomerApplyAddRequestDTO ydCustomerApplyAddRequestDTO) {
		yundaexAuditService.saveAdditionInfo(ydCustomerApplyAddRequestDTO);
	}
	
	/**
	 * 审核列表页面审核信息查询方法
	 */
	@Override
	public PageList<YdCustomerApplyMinDTO> getCustomerApplyList(
			YdCustomerApplyListQueryDTO ydCustomerApplyListQueryDTO) {
		return yundaexAuditService.getYundaexAuditList(ydCustomerApplyListQueryDTO);
	}

	/**
	 * 审核列表详情
	 */
	@Override
	public YdCustomerApplyDetailDTO getCustomerApplyInfo(String id) {
		return yundaexAuditService.getCustomerApplyInfo(id);
	}

	/**
	 * 人工审核
	 */
	@Override
	public void auditYundaexCustomer(YundaexCustomerAuditResultDTO resultDTO) {
		YundaexCustomerApply ydCustomerApply = ydCustomerApplyMapper.selectByPrimaryKey(resultDTO.getCustomerApplyId());
		if(ydCustomerApply == null)
			throw WebException.instance("当前申请不存在");
		ydCustomerApplyTransactionService.auditYundaexCustomer(resultDTO);
	}

	/**
	 * 引导页面 登陆客户的基本信息查询
	 */
	@Override
	public YdCustomerApplyDetailDTO getUserBaseInfo(String userId) {
		
		YdCustomerApplyDetailDTO ydCustomerApplyDetailDTO = yundaexAuditService.getUserBaseInfo(userId);
		
		return ydCustomerApplyDetailDTO;
	}

}
