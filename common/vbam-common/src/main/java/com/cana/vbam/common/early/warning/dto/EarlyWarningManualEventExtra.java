package com.cana.vbam.common.early.warning.dto;

import java.io.Serializable;
import java.util.List;

import com.cana.vbam.common.dto.MediaFileDTO;

public class EarlyWarningManualEventExtra implements Serializable {

	private static final long serialVersionUID = 5556885378939098004L;

	private String eventOrigin;
	
	private List<MediaFileDTO> medias;
	
	public String getEventOrigin() {
		return eventOrigin;
	}

	public void setEventOrigin(String eventOrigin) {
		this.eventOrigin = eventOrigin;
	}

	public List<MediaFileDTO> getMedias() {
		return medias;
	}

	public void setMedias(List<MediaFileDTO> medias) {
		this.medias = medias;
	}

}
