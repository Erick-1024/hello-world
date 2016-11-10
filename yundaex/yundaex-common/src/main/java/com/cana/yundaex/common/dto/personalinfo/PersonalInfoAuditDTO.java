package com.cana.yundaex.common.dto.personalinfo;

import java.io.Serializable;

import com.cana.yundaex.common.enums.PersonalInfoAuditStatus;

/**
 * @author hu
 *
 */
public class PersonalInfoAuditDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7346384499242420907L;
	
	private String id;
	
	private PersonalInfoAuditStatus auditStatus;

	private String auditorId;

    /**
     *
     */
    private String auditorName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public PersonalInfoAuditStatus getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(PersonalInfoAuditStatus auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getAuditorId() {
		return auditorId;
	}

	public void setAuditorId(String auditorId) {
		this.auditorId = auditorId;
	}

	public String getAuditorName() {
		return auditorName;
	}

	public void setAuditorName(String auditorName) {
		this.auditorName = auditorName;
	}
}
