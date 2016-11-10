package com.cana.vbam.common.utils;

import java.io.Serializable;

public class Counter implements Serializable {

	private static final long serialVersionUID = -3193704957522880842L;

	private int count;

	public Counter() {}

	public Counter(int count) {
		this.count = count;
	}

	public int count() {
		return this.count;
	}

	public void increase() {
		this.count++;
	}

	public void decrease() {
		this.count--;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
