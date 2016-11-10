package com.cana.yundaex.service.transaction;

import java.util.List;

import com.cana.vbam.common.dto.ListResult;
import com.cana.yundaex.common.dto.personalinfo.PersonalInfoAuditDTO;
import com.cana.yundaex.common.dto.personalinfo.PersonalInfoRequestDTO;
import com.cana.yundaex.dao.po.YundaexPersonalInfo;

/**
 * @author hu
 *
 */
public interface IYundaexPersonalInfoTransactionService {

	/**
	 * 批量增加
	 * @param infoList
	 */
	public void BatchCreatePersonalInfo(List<YundaexPersonalInfo> infoList);
	/**
	 * 更新个人信息
	 * @param requestDTO
	 */
	public void updatePersonalInfo(PersonalInfoRequestDTO requestDTO);
	
	/**
	 * 审核个人信息
	 * @param id
	 * @param auditStatus
	 */
	public void auditPersonalInfo(PersonalInfoAuditDTO auditDTO);
	
	/**
	 * 更新证书dn
	 * @param id
	 * @param subjectDn
	 */
	public void updatePersonalSubjectDn(String id, String subjectDn);

	/**
	 * 更新安全码，发送上传信息链接
	 * @param id
	 */
	public void resendSubmitLink(String id);
	
	/**
	 * 更新安全码，发送合同签署链接
	 * @param id
	 */
	public ListResult<String> resendSignContractLink(String customerId, List<String> type);
}
