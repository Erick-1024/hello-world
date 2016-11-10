package com.cana.vbam.common.netdisk.dto;

import java.io.Serializable;

import com.cana.vbam.common.netdisk.enums.Module;
import com.cana.vbam.common.netdisk.enums.Type;

public class GetMediaListRequest implements Serializable {

	private static final long serialVersionUID = -2324138123930715888L;

	private String path;
	
	private String groupId;
	
	private Module module;
	
	private Type type;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
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

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
	
}
