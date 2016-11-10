package com.cana.vbam.common.customer.dto;

import java.io.Serializable;

public class EnterpriseInfoDTO implements Serializable{

	private static final long serialVersionUID = -764126754703842415L;

	// 主键
	private String id;
	
	// 企业Id
	private String customerId;
	
	// 材料分类
	private String categlory;
	
	// 项目分类
	private String itemType;
	
	// 文件名
	private String fileName;
	
	// 文件Id
	private String mediaId;
	
	// 附件说明
	private String remark;
	
	// 资料顺序
	private String sequence;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCateglory() {
		return categlory;
	}

	public void setCateglory(String categlory) {
		this.categlory = categlory;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
}
