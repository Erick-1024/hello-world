package com.cana.asset.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.cana.account.api.IAccountApi;
import com.cana.asset.service.IAssetProjectPersistenceService;
import com.cana.asset.service.transaction.IAssetProjectPersistenceTransactionService;
import com.cana.member.api.IMemberQueryApi;
import com.cana.vbam.common.account.dto.AccountDTO;
import com.cana.vbam.common.asset.dto.ProjectFactorDTO;
import com.cana.vbam.common.asset.dto.ProjectRequestDTO;
import com.cana.vbam.common.asset.dto.ProjectStatusResponseDTO;
import com.cana.vbam.common.member.enums.user.UserType;
import com.cana.vbam.common.member.vo.CustomerVo;
import com.cana.vbam.common.member.vo.UserVo;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.travelzen.framework.core.exception.WebException;

@Service
public class AssetProjectPersistenceServiceImpl implements IAssetProjectPersistenceService {


	@Resource
	private IMemberQueryApi memberQueryApi;
	@Resource
	private IAssetProjectPersistenceTransactionService assetProjectPersistenceTransactionService;
	@Resource
	private IAccountApi accountApi;

	@Override
	public ProjectStatusResponseDTO addProject(String userId, ProjectRequestDTO projectRequest) {
		if (projectRequest == null)
			throw WebException.instance("新增项目不能为空");
		if (StringUtils.isNotEmpty(projectRequest.getId()))
			throw WebException.instance("新增项目Id必须为空");
		if (projectRequest.getHavePermissionModifyDocument() == null)
			throw WebException.instance("请求中是否有对合同的修改权限字段必须有值");

		UserVo userVo = memberQueryApi.findUserById(userId);
		checkFactorUser(userVo);
		
		CustomerVo coreCustomer = getCustomerByName(UserType.CORECOMPANY, projectRequest.getCoreCompanyName());
		Map<String, CustomerVo> factorMaps = getCustomerMaps(projectRequest);
		Map<String, AccountDTO> accountMaps = getAccountMaps(userVo.getCustomerId(), projectRequest);
		return assetProjectPersistenceTransactionService.addProject(userVo, projectRequest, coreCustomer, factorMaps, accountMaps);
	}

	@Override
	public ProjectStatusResponseDTO updateProject(String userId, ProjectRequestDTO projectRequest) {
		if (projectRequest == null || StringUtils.isEmpty(projectRequest.getId()))
			throw WebException.instance("修改项目Id不能为空");
		if (projectRequest.getHavePermissionModifyDocument() == null)
			throw WebException.instance("请求中是否有对合同的修改权限字段必须有值");

		UserVo userVo = memberQueryApi.findUserById(userId);
		checkFactorUser(userVo);
		
		CustomerVo coreCustomer = getCustomerByName(UserType.CORECOMPANY, projectRequest.getCoreCompanyName());
		Map<String, CustomerVo> factorMaps = getCustomerMaps(projectRequest);
		Map<String, AccountDTO> accountMaps = getAccountMaps(userVo.getCustomerId(), projectRequest);
		return assetProjectPersistenceTransactionService.updateProject(userVo, projectRequest, coreCustomer, factorMaps, accountMaps);
	}
	
	private Map<String, AccountDTO> getAccountMaps(String customerId, ProjectRequestDTO projectRequest) {
		Map<String, AccountDTO> accountMaps = Maps.newHashMap();
		Set<String> accountNos = Sets.newHashSet();
		
		if (StringUtils.isNotEmpty(projectRequest.getCoreAccountNo())) {
			accountNos.add(projectRequest.getCoreAccountNo());
		}
		if (CollectionUtils.isNotEmpty(projectRequest.getProjectFactors())) {
			for (ProjectFactorDTO factorDTO : projectRequest.getProjectFactors()) {
				if (StringUtils.isNotEmpty(factorDTO.getAccountNo())) {
					accountNos.add(factorDTO.getAccountNo());
				}
			}
		}
		if (CollectionUtils.isEmpty(accountNos)) {
			return accountMaps;
		}

		List<AccountDTO> accountDTOs = accountApi.getAccountByNos(customerId, accountNos.toArray(new String[accountNos.size()]));
		
		if (CollectionUtils.isNotEmpty(accountDTOs)) {
			for (AccountDTO accountDTO : accountDTOs) {
				accountMaps.put(accountDTO.getAccountNo(), accountDTO);
			}
		}

		return accountMaps;
	}
	
	/**
	 * 检查用户是否有新建项目的权限
	 * @param userId
	 */
	private void checkFactorUser(UserVo user){
		if (user == null || user.getCustomer() == null) {
			throw WebException.instance("用户ID无效");
		}
		if (user.getCustomer().getUserType() != UserType.FACTOR) {
			throw WebException.instance("只有资金方用户才能编辑项目");
		}
	}
	
	private CustomerVo getCustomerByName(UserType userType, String companyName) {
		if (StringUtils.isNotEmpty(companyName)) {
			return memberQueryApi.findCustomerByName(userType, companyName);
		}
		return null;
	}
	
	private Map<String, CustomerVo> getCustomerMaps(ProjectRequestDTO projectRequest) {
		Map<String, CustomerVo> factorMaps = Maps.newHashMap();
		if (CollectionUtils.isEmpty(projectRequest.getProjectFactors())) {
			return factorMaps;
		}

		for (ProjectFactorDTO factorDTO : projectRequest.getProjectFactors()) {
			
			if (StringUtils.isEmpty(factorDTO.getCompanyName())) {
				continue;
			}
			
			CustomerVo factor = getCustomerByName(UserType.FACTOR, factorDTO.getCompanyName());
			if (factor != null) {
				factorMaps.put(factor.getCustomerName(), factor);
			}
		}

		return factorMaps;
	}

}
