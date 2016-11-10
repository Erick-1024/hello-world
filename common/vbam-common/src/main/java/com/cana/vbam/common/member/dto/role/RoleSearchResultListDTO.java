package com.cana.vbam.common.member.dto.role;

import java.io.Serializable;
import java.util.List;

public class RoleSearchResultListDTO implements Serializable
{
	private static final long serialVersionUID = 8120282945438413647L;
	
	/**
	 * 查询到的DTO list
	 */
	private List<RoleSearchResultDTO> roleSearchResultDTOs;
	
	/**
	 * 查询到的结果条数
	 */
	private int totalNum;
	
	public List<RoleSearchResultDTO> getRoleSearchResultDTOs()
	{
		return roleSearchResultDTOs;
	}
	public void setRoleSearchResultDTOs(List<RoleSearchResultDTO> roleSearchResultDTOs)
	{
		this.roleSearchResultDTOs = roleSearchResultDTOs;
	}
	public int getTotalNum()
	{
		return totalNum;
	}
	public void setTotalNum(int totalNum)
	{
		this.totalNum = totalNum;
	}
	
}
