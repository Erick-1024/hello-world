package com.cana.vbam.common.member.dto.permission;

import java.io.Serializable;
import java.util.Date;

import com.cana.vbam.common.enums.PermissionType;

/**
 *
 * @since Nov 6, 20154:38:36 PM
 * @author dev1
 *
 */
public class PermissionDTO implements Serializable,Comparable<PermissionDTO> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2025389085834103778L;

	/**
    *key值
    */
    private String id;

    /**
    *权限名称
    */
    private String name;

    /**
    *权限对应的地址
    */
    private String url;

    /**
    *前端所属的前端项目
    */
    private String platform;

    /**
    *MODULE, BUTTON, PERM; 三种权限类型
    */
    private PermissionType type;

    /**
    *父级权限
    */
    private String parentId;

    /**
    *序号
    */
    private Integer ord;

    /**
    *权限描述
    */
    private String description;

    /**
    *创建时间
    */
    private Date createTime;

    /**
    *更新时间
    */
    private Date updateTime;
    
    /**
     * 权限可用性
     */
    private boolean granted = true;

	/**
	 * @return the granted
	 */
	public boolean isGranted()
	{
		return granted;
	}

	/**
	 * @param granted the granted to set
	 */
	public void setGranted(boolean granted)
	{
		this.granted = granted;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public PermissionType getType() {
		return type;
	}

	public void setType(PermissionType type) {
		this.type = type;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Integer getOrd() {
		return ord;
	}

	public void setOrd(Integer ord) {
		this.ord = ord;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int compareTo(PermissionDTO o)
	{
		// TODO Auto-generated method stub
		if(this.ord > o.getOrd())
			return 1;
		else if(this.ord < o.getOrd())
			return -1;
		else
			return 0;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PermissionDTO other = (PermissionDTO) obj;
		if (id == null)
		{
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


}
