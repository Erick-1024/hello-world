package com.cana.vbam.common.netdisk.dto;

import java.io.Serializable;

import com.cana.vbam.common.netdisk.enums.Type;

public class MediaDownloadDTO implements Serializable {

	private static final long serialVersionUID = -6243424944032326636L;

	private Type type;
	
	private String name;
	
	private String path;
	
	private byte[] Contents;

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

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public byte[] getContents() {
		return Contents;
	}

	public void setContents(byte[] contents) {
		Contents = contents;
	}
	
}
