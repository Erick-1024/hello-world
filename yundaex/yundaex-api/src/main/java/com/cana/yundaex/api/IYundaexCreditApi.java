
package com.cana.yundaex.api;

import java.math.BigDecimal;
import java.util.List;

import com.cana.vbam.common.yundaex.dto.creditLimit.YundaexAccessCreditLimitCustomerInfoDTO;
import com.cana.vbam.common.yundaex.dto.creditLimit.YundaexCreditAuditListDTO;
import com.cana.vbam.common.yundaex.dto.creditLimit.YundaexCreditLimitDTO;
import com.cana.vbam.common.yundaex.dto.creditLimit.YundaexCreditListMinDTO;
import com.cana.vbam.common.yundaex.dto.creditLimit.YundaexCreditListQueryDTO;
import com.cana.vbam.common.yundaex.dto.creditLimit.YundaexCreditQueryDTO;
import com.cana.yundaex.common.dto.apply.YdCustomerApplyRequestDTO;
import com.cana.yundaex.common.dto.limit.YdQueryCreditLimitDTO;
import com.cana.yundaex.common.dto.limit.YdQueryCreditLimitResponse;
import com.travelzen.framework.common.PageList;

/**
 * 韵达项目-授信额度API
 * @author guguanggong
 *
 */
public interface IYundaexCreditApi {
	
	/**
	 * 分页查询额度列表
	 * @param yundaexCreditQueryDTO
	 * @return
	 */
	public PageList<YundaexCreditLimitDTO> getYundaexCreditList(YundaexCreditQueryDTO yundaexCreditQueryDTO );
	
	/**
	 * 韵达客户额度申请，保存客户申请资料
	 * @param jsonAuditResult
	 * @throws Exception
	 */
	public void saveYundaexAuditResult(YdCustomerApplyRequestDTO jsonAuditResult)throws Exception;

	/**
	 * 已审核额度信息查询
	 * @param ydQueryCreditLimitDTO
	 * @return
	 */
	public YdQueryCreditLimitResponse queryCreditLimit(YdQueryCreditLimitDTO ydQueryCreditLimitDTO);

	/**
	 * 分页查询额度状态及审核信息
	 * @param creditQueryDTO
	 * @return
	 */
	public PageList<YundaexCreditListMinDTO> getYundaexCreditStateList(YundaexCreditListQueryDTO creditQueryDTO);

	/**
	 * 线下数据发送激活链接
	 * @param userId
	 * @throws Exception 
	 */
	public void sendActivationLink(String userId) throws Exception;

	/**
	 * 授信审核列表查询
	 * @param creditQueryDTO
	 * @return
	 */
	public PageList<YundaexCreditAuditListDTO> getCreditAuditList(YundaexCreditListQueryDTO creditQueryDTO);

	/**
	 * 授信审核驳回
	 * @param id
	 */
	public void creditAuditReject(String id);

	/**
	 * 授信审核通过
	 * @param id
	 */
	public void creditAuditPass(String id);
        
        /**
	 * 获取所有授信客户
	 * @return
	 */
	public List<YundaexAccessCreditLimitCustomerInfoDTO> getYundaexAccessCreditLimitCustomerInfoDTO();
	
	/**
	 * 根据评分获取等级
	 * @param score
	 * @return
	 */
	public String getYundaexGradeByScore(BigDecimal score);
}
