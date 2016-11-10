package com.travelzen.framework.vo;

import java.io.Serializable;
import java.util.Map;

import com.travelzen.framework.enums.SortOrder;

public class AbstractCriteria implements Serializable {

	private static final long serialVersionUID = -5284102676616436362L;

	private Integer offset;
	private Integer limit;
	private boolean noData;
	private boolean fillCreatorName;
	private boolean fillModifierName;
	private Map<String, SortOrder> sorting;

	public boolean getNoData() {
		return noData;
	}

	public void setNoData(boolean noData) {
		this.noData = noData;
	}

	public Map<String, SortOrder> getSorting() {
		return sorting;
	}

	public void setSorting(Map<String, SortOrder> sorting) {
		this.sorting = sorting;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public boolean getFillCreatorName() {
		return fillCreatorName;
	}

	public void setFillCreatorName(boolean fillCreatorName) {
		this.fillCreatorName = fillCreatorName;
	}

	public boolean getFillModifierName() {
		return fillModifierName;
	}

	public void setFillModifierName(boolean fillModifierName) {
		this.fillModifierName = fillModifierName;
	}

}
