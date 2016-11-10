/**
 * 
 */
package com.cana.yundaex.service;

import com.cana.credit.limit.dao.po.CreditLimit;
import com.cana.vbam.common.yundaex.dto.creditLimit.YundaexCreditAuditListDTO;
import com.cana.vbam.common.yundaex.dto.creditLimit.YundaexCreditLimitDTO;
import com.cana.vbam.common.yundaex.dto.creditLimit.YundaexCreditListMinDTO;
import com.cana.vbam.common.yundaex.dto.creditLimit.YundaexCreditListQueryDTO;
import com.cana.vbam.common.yundaex.dto.creditLimit.YundaexCreditQueryDTO;
import com.cana.yundaex.common.dto.limit.YdQueryCreditLimitDTO;
import com.cana.yundaex.common.dto.limit.YdQueryCreditLimitResponse;
import com.travelzen.framework.common.PageList;

/**
 * 韵达项目-授信额度服务接口
 * 
 * @author guguanggong
 *
 */
public interface IYundaexCreditService {
	/**
	 * 分页查询授信额度列表
	 * 
	 * @param yundaexCreditQueryDTO
	 * @return
	 */
	public PageList<YundaexCreditLimitDTO> getYundaexCreditList(YundaexCreditQueryDTO yundaexCreditQueryDTO);

	/**
	 * 已审核额度信息查询
	 * @param ydQueryCreditLimitDTO
	 * @return
	 */
	public YdQueryCreditLimitResponse queryCreditLimit(YdQueryCreditLimitDTO ydQueryCreditLimitDTO);

	/**
	 * 计算额度
	 */
	public void calculateApplyCreditLimit();
	
	/**
	 * 根据memberId获取该用户的额度记录
	 * 
	 * @param memberId cana的用户Id
	 * @return
	 */
	public CreditLimit getCreditLimitByMemberId(String memberId);

	/**
	 * 分页查询额度状态及审核信息
	 * @param creditQueryDTO
	 * @return
	 */
	public PageList<YundaexCreditListMinDTO> getYundaexCreditStateList(YundaexCreditListQueryDTO creditQueryDTO);

	/**
	 * 授信审核列表 查询
	 * @param creditQueryDTO
	 * @return
	 */
	public PageList<YundaexCreditAuditListDTO> getCreditAuditList(YundaexCreditListQueryDTO creditQueryDTO);

}
