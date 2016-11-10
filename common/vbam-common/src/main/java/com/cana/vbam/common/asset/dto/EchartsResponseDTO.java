package com.cana.vbam.common.asset.dto;

import java.io.Serializable;
import java.util.List;

public class EchartsResponseDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3362082500503400927L;
	private Object[] array;
	private Object[] enumArray;
	private List<Object[]> list;
	public Object[] getArray() {
		return array;
	}
	public void setArray(Object[] array) {
		this.array = array;
	}
	public List<Object[]> getList() {
		return list;
	}
	public void setList(List<Object[]> list) {
		this.list = list;
	}

	public Object[] getEnumArray() {
		return enumArray;
	}
	public void setEnumArray(Object[] enumArray) {
		this.enumArray = enumArray;
	}
}
