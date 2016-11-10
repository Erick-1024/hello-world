package com.cana.vbam.common.netdisk.dto;

import java.io.Serializable;

import com.cana.vbam.common.netdisk.enums.Module;
import com.cana.vbam.common.netdisk.enums.Type;

public class SaveMediaRequest implements Serializable {

	private static final long serialVersionUID = -5822584526092242088L;

	private byte[] Contents;
	
	private String fileName;
	
	private Module module;
	
	private Type type;
	
	private String groupId;
	
	private String path;
	
	private String creatorId;

	public byte[] getContents() {
		return Contents;
	}

	public void setContents(byte[] contents) {
		Contents = contents;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
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

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}
	
}
