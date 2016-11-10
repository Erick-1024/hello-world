package com.cana.member.api;

import java.util.List;

import com.cana.vbam.common.enums.Platform;
import com.cana.vbam.common.member.dto.permission.PermissionDTO;

/**
 *
 * 权限服务
 * @since Nov 6, 20154:25:56 PM
 * @author dev1
 *
 */
public interface IPermissionApi {
	
	/**
	 * 根据所属平台获取权限
	 * @param platform
	 * @return
	 * @throws Exception 
	 */
	public List<PermissionDTO> getPermissionByPlatform(Platform platform) throws Exception;
	
	/**
	 * 根据角色获取权限
	 * @param roleId
	 * @return
	 * @throws Exception 
	 */
	public List<PermissionDTO> getPermissionByRole(String roleId) throws Exception;

	/**
	 * 根据角色获取权限
	 * @param roleId
	 * @return
	 * @throws Exception 
	 */
	public List<PermissionDTO> getPermissionByRoleList(List<String> roleIdList) throws Exception;
	
	/**
	 * 获取所有权限
	 * @return
	 * @throws Exception 
	 */
	public List<PermissionDTO> getAllPermissions() throws Exception;
	
	/**
	 * 根据角色获取权限Id
	 * @param roleId
	 * @return
	 * @throws Exception 
	 */
	public List<String> getPermissionKeyByRole(String roleId) throws Exception;
	
	/**
	 * 根据权限Id获取相应权限对象
	 * @param id 权限id
	 * @return
	 * @throws Exception 
	 */
	public PermissionDTO getPermissionById(String id) throws Exception;
	
	/**
	 * 根据用户id获取个性权限
	 * @param id 权限id
	 * @return
	 * @throws Exception 
	 */
	public List<PermissionDTO> getPermissionByUserId(String userId) throws Exception;
	
}
