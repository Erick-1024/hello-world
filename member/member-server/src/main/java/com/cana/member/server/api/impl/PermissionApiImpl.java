package com.cana.member.server.api.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.cana.member.api.IPermissionApi;
import com.cana.member.dao.mapper.gen.PermissionMapper;
import com.cana.member.dao.mapper.gen.RoleMapper;
import com.cana.member.dao.mapper.gen.UserMapper;
import com.cana.member.dao.po.Permission;
import com.cana.member.dao.po.PermissionExample;
import com.cana.member.dao.po.PermissionExample.Criteria;
import com.cana.member.dao.po.Role;
import com.cana.member.dao.po.User;
import com.cana.member.service.transaction.IPermissionTransactionService;
import com.cana.vbam.common.enums.Platform;
import com.cana.vbam.common.member.dto.permission.PermissionDTO;
import com.google.common.collect.Lists;
import com.travelzen.framework.core.exception.WebException;

/**
 *
 * @since Nov 6, 20154:58:26 PM
 * @author dev1
 *
 */
public class PermissionApiImpl implements IPermissionApi{

	private static final Logger LOGGER = LoggerFactory.getLogger(PermissionApiImpl.class);
	
	@Resource
	private PermissionMapper permissionMapper;
	
	@Resource
	private RoleMapper roleMapper;
	
	@Resource
	private UserMapper userMapper;
	
	@Resource(name = "permissionTransactionService")
	private IPermissionTransactionService permissionTransactionService;
	
	@Override
	public List<PermissionDTO> getPermissionByPlatform(Platform platform) throws Exception {
		LOGGER.info("find permissions by platform.");
		PermissionExample example = new PermissionExample();
		Criteria criteria = example.createCriteria();
		criteria.andPlatformEqualTo(platform.name());
		List<Permission> permissionList = permissionMapper.selectByExample(example);
		List<PermissionDTO> permissionDTOList = convert2PermissionDTO(permissionList);
		return permissionDTOList;
	}

	@Override
	public List<PermissionDTO> getPermissionByRole(String roleId) throws Exception{
		List<Permission> permissionList = permissionTransactionService.getPermissionByRole(roleId);
		List<PermissionDTO> permissionDTOList = convert2PermissionDTO(permissionList);
		return permissionDTOList;
	}

	@Override
	public List<PermissionDTO>  getPermissionByRoleList(List<String> roleIdList) throws Exception{
		Set<Permission> permissionSet = new HashSet<>();
		List<Permission> filterList = Lists.newArrayList();
		for(String roleId:roleIdList){
			permissionSet.addAll(permissionTransactionService.getPermissionByRole(roleId));
		}
		filterList.addAll(permissionSet);
		List<PermissionDTO> permissionDTOList = convert2PermissionDTO(filterList);
		return permissionDTOList;
	}
	

	@Override
	public List<String> getPermissionKeyByRole(String roleId) throws Exception {
		LOGGER.info("find permissionkey by role.");
		Role role = roleMapper.selectByPrimaryKey(roleId);
		String permissions = role.getPermissions();
		String [] permissionArr = permissions.split(";");
		return Arrays.asList(permissionArr);
	}
	
	/**
	 * DTO转化
	 * @param permissionList
	 * @return
	 */
	private List<PermissionDTO> convert2PermissionDTO(List<Permission> permissionList){
		List<PermissionDTO> permissionDTOList = new ArrayList<PermissionDTO>();
		if(!CollectionUtils.isEmpty(permissionList)){
			for(Permission permission : permissionList){
				PermissionDTO permissionDTO = new PermissionDTO();
				BeanUtils.copyProperties(permission, permissionDTO);
				permissionDTOList.add(permissionDTO);
			}
		}
		return permissionDTOList;
	}

	@Override
	public PermissionDTO getPermissionById(String id) throws Exception
	{
		Permission permission = permissionMapper.selectByPrimaryKey(id);
		PermissionDTO permissionDTO = new PermissionDTO();
		BeanUtils.copyProperties(permission, permissionDTO);
		return permissionDTO;
	}

	@Override
	public List<PermissionDTO> getAllPermissions() throws Exception
	{
		List<Permission> permissions = permissionMapper.selectByExample(new PermissionExample());
		List<PermissionDTO> permissionDTOList = convert2PermissionDTO(permissions);	
		return permissionDTOList;
	}
	
	@Override
	public List<PermissionDTO> getPermissionByUserId(String userId) throws Exception {
		if(StringUtils.isBlank(userId))
			throw WebException.instance("参数错误");
		User user = userMapper.selectByPrimaryKey(userId);
		if(null == user)
			throw WebException.instance("用户不存在");
		if(StringUtils.isBlank(user.getPermissions()))
			return Lists.newArrayList();
		Set<String> idSet = new TreeSet<>();
		for(String key : user.getPermissions().split(";")){
			if(StringUtils.isNotBlank(key))
				idSet.add(key);
		}
		List<String> idList = Lists.newArrayList();
		idList.addAll(idSet);
		if(CollectionUtils.isEmpty(idList))
			return Lists.newArrayList();
		PermissionExample example = new PermissionExample();
		example.createCriteria().andIdIn(idList);
		List<Permission> permissions = permissionMapper.selectByExample(example);
		List<PermissionDTO> permissionDTOList = convert2PermissionDTO(permissions);	
		return permissionDTOList;
	}
	
}
