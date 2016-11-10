package com.cana.vbam.common.dto;

import java.io.Serializable;

public class MediaFileDTO implements Serializable {

	private static final long serialVersionUID = 5901848913615004799L;

	private String mediaId;
	
	private String fileName;

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}
