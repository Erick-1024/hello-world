package com.cana.asset.service.transaction;

import java.util.Map;

import com.cana.vbam.common.account.dto.AccountDTO;
import com.cana.vbam.common.asset.dto.ProjectRequestDTO;
import com.cana.vbam.common.asset.dto.ProjectStatusResponseDTO;
import com.cana.vbam.common.member.vo.CustomerVo;
import com.cana.vbam.common.member.vo.UserVo;

/**
 * @author XuMeng
 * 项目管理持久化业务
 */
public interface IAssetProjectPersistenceTransactionService {

	/**
	 * 新增项目
	 * @param userVo 当前登录用户，调用方保证必须有值
	 * @param projectRequest 请求对象
	 * @param coreCustomer 核心企业，不存在则传空
	 * @param factorMaps 核心企业map，key为企业名称，value为企业对象，如果不存在，则传入空map
	 * @param accountMaps 账户，key为银行账号，value为账号实体对象，如果不存在，则传入空map
	 */
	public ProjectStatusResponseDTO addProject(UserVo userVo, ProjectRequestDTO projectRequest,
			CustomerVo coreCustomer, Map<String, CustomerVo> factorMaps,
			Map<String, AccountDTO> accountMaps);

	/**
	 * 修改项目
	 * @param userVo 当前登录用户，调用方保证必须有值
	 * @param projectRequest 请求对象
	 * @param coreCustomer 核心企业，不存在则传空
	 * @param factorMaps 核心企业map，key为企业名称，value为企业对象，如果不存在，则传入空map
	 * @param accountMaps 账户，key为银行账号，value为账号实体对象，如果不存在，则传入空map
	 */
	public ProjectStatusResponseDTO updateProject(UserVo userVo, ProjectRequestDTO projectRequest,
			CustomerVo coreCustomer, Map<String, CustomerVo> factorMaps,
			Map<String, AccountDTO> accountMaps);

}
