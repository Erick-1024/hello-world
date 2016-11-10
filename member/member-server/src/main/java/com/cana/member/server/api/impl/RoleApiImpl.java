package com.cana.member.server.api.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.cana.member.api.IRoleApi;
import com.cana.member.service.transaction.IRoleService;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.enums.RoleType;
import com.cana.vbam.common.member.dto.role.RoleAddDTO;
import com.cana.vbam.common.member.dto.role.RoleSearchCriterionDTO;
import com.cana.vbam.common.member.dto.role.RoleSearchResultDTO;
import com.cana.vbam.common.member.dto.role.RoleUpdateDTO;
import com.cana.vbam.common.member.dto.user.RoleDTO;
import com.cana.vbam.common.member.enums.user.UserType;

public class RoleApiImpl implements IRoleApi
{
	@Resource
	private IRoleService roleServiecImpl;

	@Override
	public boolean checkEmployeeRoleNameExist(RoleSearchCriterionDTO roleSearchCriterionDTO) throws Exception
	{
		// TODO Auto-generated method stub
		return roleServiecImpl.checkEmployeeRoleNameExist(roleSearchCriterionDTO);
	}

	@Override
	public String addRole(RoleAddDTO roleAddDTO) throws Exception
	{
		// TODO Auto-generated method stub
		return roleServiecImpl.addRole(roleAddDTO);
	}

	@Override
	public boolean checkCompanyRoleNameExist(RoleSearchCriterionDTO roleSearchCriterionDTO) throws Exception
	{
		// TODO Auto-generated method stub
		return roleServiecImpl.checkCompanyRoleNameExit(roleSearchCriterionDTO);
	}

	@Override
	public ListResult<RoleSearchResultDTO> queryRoleList(RoleSearchCriterionDTO roleSearchCriterionDTO)
			throws Exception
	{
		// TODO Auto-generated method stub
		return roleServiecImpl.queryRoleList(roleSearchCriterionDTO);
	}

	@Override
	public Map<String, String> queryRolesIdAndName(String currentOperatorId, RoleType roleType, UserType userType) throws Exception {
		return roleServiecImpl.queryRolesIdAndName(currentOperatorId, roleType, userType);
	}

	@Override
	public RoleSearchResultDTO getRoleById(String roleId) throws Exception{
		return roleServiecImpl.getRoleById(roleId);
	}

	@Override
	public void updateRole(RoleUpdateDTO roleUpdateDTO) throws Exception
	{
		roleServiecImpl.updateRole(roleUpdateDTO);
	}

	@Override
	public Map<String, String> queryRolesByRoleId(List<RoleDTO> roleDTOList) throws Exception{
		// TODO Auto-generated method stub
		return roleServiecImpl.queryRolesByRoleId(roleDTOList);
	}

	@Override
	public void updateCompanyRoleByCana(RoleUpdateDTO roleUpdateDTO) throws Exception
	{
		roleServiecImpl.updateCompanyRoleByCana(roleUpdateDTO);
	}	
}
