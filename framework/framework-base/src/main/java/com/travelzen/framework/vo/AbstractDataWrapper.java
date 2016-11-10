package com.travelzen.framework.vo;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractDataWrapper <T extends AbstractVO> implements Serializable {

	private static final long serialVersionUID = 675955290425425708L;

	private long count;

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public abstract List<T> getData();

	public abstract void setData(List<T> data);

}
