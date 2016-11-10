package com.cana.vbam.common.member.dto.role;

import java.io.Serializable;
import java.util.Date;

/**
 * 根据 masterId 查询的角色信息 dto
 * @author dev3
 *
 */
public class RoleSearchResultDTO implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 903100194916600394L;

	/**
    *主键
    */
    private String id;
	
	/**
	  *主账号Id
	*/
	private String masterId;
	
	/**
	 * 角色名称
	 */
	private String roleName;
	
    /**
    *权限（以分号隔开）
    */
    private String permissions;

	public String getPermissions()
	{
		return permissions;
	}

	public void setPermissions(String permissions)
	{
		this.permissions = permissions;
	}

	/**
	 * 角色创建日期
	 */
	private Date createtime;
	
	/**
	 * 角色类型,只有企业类型的用户有
	 */
	private String roleType;
	/**
	 * all get and set method
	 * @return
	 */
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMasterId() {
		return masterId;
	}

	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**
	 * @return the createtime
	 */
	public Date getCreatetime()
	{
		return createtime;
	}

	/**
	 * @param createtime the createtime to set
	 */
	public void setCreatetime(Date createtime)
	{
		this.createtime = createtime;
	}

	public String getRoleType()
	{
		return roleType;
	}

	public void setRoleType(String roleType)
	{
		this.roleType = roleType;
	}
	
}
