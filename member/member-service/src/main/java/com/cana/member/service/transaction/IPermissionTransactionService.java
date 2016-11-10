package com.cana.member.service.transaction;

import java.util.List;

import com.cana.member.dao.po.Permission;

/**
 *
 * @since Nov 6, 20155:17:32 PM
 * @author dev1
 *
 */
public interface IPermissionTransactionService {
	
	/**
	 * 根据角色获取权限
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public List<Permission> getPermissionByRole(String roleId) throws Exception;
	
	/**
	 * 权限从xml导入数据库
	 */
	public void loadPermissionDefinitions();
}
