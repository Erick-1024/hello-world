package com.travelzen.framework.lucene.index.test.student;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;

import com.travelzen.framework.lucene.index.bean.AbstractIndexBean;

public class StudentIndexBean extends AbstractIndexBean {

	public String s_Id;
	public String address;
	public String name;
	public Long age;
	public Boolean isChild;
	public String remark;
	public Collection<String> languages;
	public Date birthDay;
	public Collection<Date> markDate;

	public StudentIndexBean() {
	}

	public StudentIndexBean(String sId, String address, String name, Long age, Boolean isChilde, String remark, Collection<String> languages, Date birthDay,
			 Collection<Date> markDate) {
		this.s_Id = sId;
		this.address = address;
		this.name = name;
		this.age = age;
		this.isChild = isChilde;
		this.remark = remark;
		this.languages = languages;
		this.birthDay = birthDay;
		this.markDate = markDate;
	}

	public String getS_Id() {
		return s_Id;
	}

	public void setS_Id(String s_Id) {
		this.s_Id = s_Id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}

	public Boolean getIsChild() {
		return isChild;
	}

	public void setIsChild(Boolean isChild) {
		this.isChild = isChild;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Collection<String> getLanguages() {
		return languages;
	}

	public void setLanguages(Collection<String> languages) {
		this.languages = languages;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public Collection<Date> getMarkDate() {
		return markDate;
	}

	public void setMarkDate(Collection<Date> markDate) {
		this.markDate = markDate;
	}


	// enum
	public static enum StudentFieldEnum {
		s_Id, address, name, age, isChild, remark, languages, birthDay, markDate;
		private StudentFieldEnum() {
		}
	}

	public static List<String> getDeclaredField() {
		List<String> fields = new ArrayList<>();
		for (StudentIndexBean.StudentFieldEnum e : StudentIndexBean.StudentFieldEnum.values()) {
			fields.add(e.name());
		}
		return fields;
	}

	@Override
	public List<String> getAnalyzedField() {
		List<String> fields = new ArrayList<>();
		fields.add(StudentIndexBean.StudentFieldEnum.address.name());
		fields.add(StudentIndexBean.StudentFieldEnum.name.name());
		return fields;
	}

	@Override
	public List<String> getIndexField() {
		List<String> fields = new ArrayList<>();
		fields.add(StudentIndexBean.StudentFieldEnum.address.name());
		fields.add(StudentIndexBean.StudentFieldEnum.name.name());
		return fields;
	}

	@Override
	public List<String> getStoreField() {
		List<String> fields = new ArrayList<>();
		fields.add(StudentIndexBean.StudentFieldEnum.s_Id.name());
		fields.add(StudentIndexBean.StudentFieldEnum.address.name());
		fields.add(StudentIndexBean.StudentFieldEnum.name.name());
		fields.add(StudentIndexBean.StudentFieldEnum.isChild.name());
		fields.add(StudentIndexBean.StudentFieldEnum.age.name());
		fields.add(StudentIndexBean.StudentFieldEnum.remark.name());
		fields.add(StudentIndexBean.StudentFieldEnum.languages.name());
		fields.add(StudentIndexBean.StudentFieldEnum.birthDay.name());
		fields.add(StudentIndexBean.StudentFieldEnum.markDate.name());
		return fields;
	}

	@Override
	public Map<String, Float> getFieldBoost() {
		Map<String, Float> fieldBoost = new HashMap<>();
		fieldBoost.put(StudentIndexBean.StudentFieldEnum.address.name(), 2.0f);
		fieldBoost.put(StudentIndexBean.StudentFieldEnum.name.name(), 2.0f);
		return fieldBoost;
	}

	@Override
	public Map<String, Pair<String, String>> getHighlighter() {
		Map<String, Pair<String, String>> highlighterMap = new HashMap<>();
		Pair<String, String> remarkP = Pair.of("<font color='red'>", "</font>");
		highlighterMap.put(StudentIndexBean.StudentFieldEnum.remark.name(), remarkP);
		return highlighterMap;
	}

}
