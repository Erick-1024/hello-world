package com.cana.vbam.common.asset.dto;

import java.io.Serializable;

/**
 * 项目新增时候传递合同两个字段：合同名字和合同文件版本日期
 * @author jiangzhou.Ren
 * @time 2016年6月17日上午10:42:42
 */
public class ProjectDocumentLessDTO implements Serializable{

	
	private static final long serialVersionUID = 7602603052479744523L;
	
	 /**
     *文件版本日期
     */
    private String version;

    /**
     *显示名称
     */
    private String name;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
