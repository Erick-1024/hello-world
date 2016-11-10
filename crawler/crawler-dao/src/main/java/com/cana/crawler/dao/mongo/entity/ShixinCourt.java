package com.cana.crawler.dao.mongo.entity;

import java.util.ArrayList;
import java.util.List;

import com.cana.vbam.common.crawler.enums.CourtExecutionSubject;
import com.github.jmkgreen.morphia.annotations.Entity;
import com.github.jmkgreen.morphia.annotations.Index;
import com.github.jmkgreen.morphia.annotations.Indexes;

/**
 * 失信被执行
 * 
 * @author renshui
 *
 */
@Entity("ShixinCourt")
@Indexes(@Index(value="code,name", unique=true))
public class ShixinCourt extends BaseMorphiaEntity {

	private static final long serialVersionUID = 269842974311972090L;
	
	//被执行人主体类型：自然人,组织
	private CourtExecutionSubject subject;
	//姓名或者组织名称
	private String name;
	//身份证号或者组织结构代码
	private String code;
	// 失信记录数
	private int count;
	// 失信记录列表, 若为null，代表爬取不成功
	private List<ShixinCourtItem> items = new ArrayList<>();
	
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
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List<ShixinCourtItem> getItems() {
		return items;
	}
	public void setItems(List<ShixinCourtItem> items) {
		this.items = items;
	}
}
