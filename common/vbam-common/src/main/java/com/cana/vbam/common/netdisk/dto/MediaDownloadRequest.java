package com.cana.vbam.common.netdisk.dto;

import java.io.Serializable;

import com.cana.vbam.common.netdisk.enums.Module;

public class MediaDownloadRequest implements Serializable {

	private static final long serialVersionUID = 2448050049604915798L;

	private String id;
	
	private String groupId;
	
	private Module module;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}
	
}
