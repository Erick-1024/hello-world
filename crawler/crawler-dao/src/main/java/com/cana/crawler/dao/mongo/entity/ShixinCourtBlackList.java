package com.cana.crawler.dao.mongo.entity;

import com.cana.vbam.common.crawler.enums.CourtExecutionSubject;
import com.github.jmkgreen.morphia.annotations.Entity;
import com.github.jmkgreen.morphia.annotations.Index;
import com.github.jmkgreen.morphia.annotations.Indexes;

/**
 * 失信被执行黑名单
 * 
 * @author renshui
 *
 */
@Entity("ShixinCourtBlackList")
@Indexes(@Index(value="code,name", unique=true))
public class ShixinCourtBlackList extends BaseMorphiaEntity {
	
	private static final long serialVersionUID = -5770373886392718977L;
	//被执行人主体类型：自然人,组织
	private CourtExecutionSubject subject;
	//姓名或者组织名称
	private String name;
	//身份证号或者组织结构代码
	private String code;
	
	public CourtExecutionSubject getSubject() {
		return subject;
	}
	public void setSubject(CourtExecutionSubject subject) {
		this.subject = subject;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
