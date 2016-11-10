package com.cana.vbam.common.netdisk.dto;

import java.io.Serializable;
import java.util.Date;

import com.cana.vbam.common.netdisk.enums.Type;

public class MediaDTO implements Serializable {

	private static final long serialVersionUID = 4053439037227454313L;

	private String id;
	
	private Type type;
	
	private String name;
	
	private Long size;
	
	private Date createTime;
	
	private boolean canDelete;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public boolean isCanDelete() {
		return canDelete;
	}

	public void setCanDelete(boolean canDelete) {
		this.canDelete = canDelete;
	}
	
}
