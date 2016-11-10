package com.travelzen.framework.config.tops.zk;

import java.util.Arrays;

import com.google.common.base.Charsets;
import com.travelzen.framework.config.tops.TopsConfEnum;

public class ZkData {

	public TopsConfEnum.ConfLocation confLocation;

	public TopsConfEnum.ConfLocation getConfLocation() {
		return confLocation;
	}

	public void setConfLocation(TopsConfEnum.ConfLocation confLocation) {
		this.confLocation = confLocation;
	}

	public byte[] data;

	public byte[] getBytes() {
		return data;
	}

	@Override
	public String toString() {
		return "ZkData [confLocation=" + confLocation + ", data=" + Arrays.toString(data) + "]";
	}

	String getString() {
		return new String(data, Charsets.UTF_8);
	}

}
