package com.cana.vbam.common.member.dto.role;

import java.io.Serializable;

import com.cana.vbam.common.member.enums.user.UserType;

public class RoleSearchCriterionDTO implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1504726705407529048L;

	/**
	 * 角色id
	 */
	private String id;
	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	/**
	  *主账号Id
	*/
	private String masterId;
	
	/**
	 * 角色名称
	 */
	private String roleName;

    /**
    *类型（一级，二级）
    */
    private String type;
    
    /**
     * 查询的起始时间
     */
    private String beginTime;
    
    /**
     * 查询的结束时间
     */
    private String endTime;
    
    /**
     * 页码，从1开始
     */
    private int page = 1;
    
    /**
     * 每页的行数
     */
    private int pageSize;
    
    private UserType userType;
	/**
	 * all get and set method
	 * @return
	 */
	public String getMasterId()
	{
		return masterId;
	}

	public void setMasterId(String masterId)
	{
		this.masterId = masterId;
	}

	public String getRoleName()
	{
		return roleName;
	}

	public void setRoleName(String roleName)
	{
		this.roleName = roleName;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	/**
	 * @return the beginTime
	 */
	public String getBeginTime()
	{
		return beginTime;
	}

	/**
	 * @param beginTime the beginTime to set
	 */
	public void setBeginTime(String beginTime)
	{
		this.beginTime = beginTime;
	}

	/**
	 * @return the endTime
	 */
	public String getEndTime()
	{
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(String endTime)
	{
		this.endTime = endTime;
	}
	public int getPage()
	{
		return page;
	}

	public void setPage(int page)
	{
		this.page = page;
	}

	public int getPageSize()
	{
		return pageSize;
	}

	public void setPageSize(int pageSize)
	{
		this.pageSize = pageSize;
	}

	public UserType getUserType()
	{
		return userType;
	}

	public void setUserType(UserType userType)
	{
		this.userType = userType;
	}
}
