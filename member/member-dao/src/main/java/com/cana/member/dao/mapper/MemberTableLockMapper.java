/**
 * Copyright (c) 2015, travelzen and/or its affiliates. All rights reserved.
 */
package com.cana.member.dao.mapper;

import com.cana.member.dao.po.Role;
import com.cana.member.dao.po.User;

/**
 * 数据库表加锁接口select * for update 加锁的时候注明参数和意图
 *
 */
public interface MemberTableLockMapper {
	/**
	 * 通过id给member_user表加锁
	 * 
	 * @param userId
	 * @return userInfo
	 */
	public User lockMemberUserById(String userId);
	
	/**
	 * 通过用户名给member_user表加锁
	 * 
	 * @param username
	 * @return userInfo
	 */
	public User lockMemberUserByUsername(String username);
	
	/**
	 * 通过id给member_role表加锁
	 * 
	 * @param roleId
	 * @return roleInfo
	 */
	public Role lockMemberRoleById(String roleId);
}
