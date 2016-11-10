package com.cana.yundaex.api;

import com.cana.vbam.common.dto.PageResult;
import com.cana.yundaex.common.dto.personalinfo.PersonalInfoAuditDTO;
import com.cana.yundaex.common.dto.personalinfo.PersonalInfoQueryCriteria;
import com.cana.yundaex.common.dto.personalinfo.PersonalInfoRequestDTO;
import com.cana.yundaex.common.dto.personalinfo.PersonalInfoResultDTO;
import com.cana.yundaex.common.enums.PersonalInfoType;

/**
 * @author hu
 *
 */
public interface IYundaexPersonalInfoApi {

	/**
	 * 发送上传个人信息链接
	 * @param customerId
	 */
	public PersonalInfoType sendPersonalInfoLink(String customerId);
	
	public void createPersonalInfo(PersonalInfoRequestDTO requestDTO);
	
	/**
	 * 更新个人信息
	 * @param requestDTO
	 */
	public void updatePersonalInfo(PersonalInfoRequestDTO requestDTO);
	
	/**
	 * 根据条件查询个人信息
	 * @param queryCriteria
	 */
	public PageResult<PersonalInfoResultDTO> findPersonalInfoByCondition(PersonalInfoQueryCriteria queryCriteria);
	
	/**
	 * 根据id查询个人信息
	 * @param id
	 */
	public PersonalInfoResultDTO findPersonalInfoById(String id);
	
	/**
	 * 获取额度
	 * @param id
	 */
	public Long getCreditLimitInfo(String customerId);
	
	/**
	 * 审核个人信息
	 * @param id
	 * @param auditStatus
	 */
	public void auditPersonalInfo(PersonalInfoAuditDTO auditDTO);
	
	/**
	 * 重发上传信息链接邮件
	 * @param id
	 */
	public void resendSubmitInfoLink(String id);
	
	/**
	 * 更新证书dn
	 * @param id
	 * @param subjectDn
	 */
	public void updatePersonalSubjectDn(String id, String subjectDn);
}
