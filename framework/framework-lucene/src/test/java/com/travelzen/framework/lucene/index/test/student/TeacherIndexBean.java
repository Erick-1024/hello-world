package com.travelzen.framework.lucene.index.test.student;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;

import com.travelzen.framework.lucene.index.bean.AbstractIndexBean;

public class TeacherIndexBean extends AbstractIndexBean {
	public String name;
	public String address;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	//########################################################3
	
	public static enum TeacherFieldEnum {
		name, address;
		private TeacherFieldEnum() {
		}
	}
	
	
	@Override
	public List<String> getAnalyzedField() {
		List<String> result = new ArrayList<>();
		result.add(TeacherFieldEnum.name.name());
		result.add(TeacherFieldEnum.address.name());
		return result;
	}

	@Override
	public List<String> getIndexField() {
		List<String> result = new ArrayList<>();
		result.add(TeacherFieldEnum.name.name());
		result.add(TeacherFieldEnum.address.name());
		return result;
	}

	@Override
	public List<String> getStoreField() {
		List<String> result = new ArrayList<>();
		result.add(TeacherFieldEnum.name.name());
		result.add(TeacherFieldEnum.address.name());
		return result;
	}

	@Override
	public Map<String, Float> getFieldBoost() {
		return null;
	}

	@Override
	public Map<String, Pair<String, String>> getHighlighter() {
		return null;
	}

}
