package com.cana.vbam.common.early.warning.dto;

import java.io.Serializable;

public class EarlyWarningSystemEventRepresent implements Serializable {

	private static final long serialVersionUID = -3018705189403263090L;

	private String represent;
	
	private String standard;

	public String getRepresent() {
		return represent;
	}

	public void setRepresent(String represent) {
		this.represent = represent;
	}

	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}
	
}
