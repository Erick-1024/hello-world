package com.travelzen.framework.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;

public abstract class AbstractVO implements Serializable, Cloneable {

	private static final long serialVersionUID = 219791407721652173L;

	private String key;

	private DateTime createTime;

	private DateTime updateTime;

	private String creator;

	private String modifier;

	/** 预留 */
	private Map<String, String> reserved;

	/** 专门为特殊情况留的额外字段，正常情况下该字段没有值 */
	private Map<String, String> extra;

	public AbstractVO() {}

	public Map<String, String> getExtra() {
		return extra;
	}

	public void setExtra(Map<String, String> extra) {
		this.extra = extra;
	}

	public String getExtra(String key) {
		return extra == null ? null : extra.get(key);
	}

	public void putExtra(String key, String value) {
		if (extra == null) {
			extra = new HashMap<>();
		}
		extra.put(key == null ? "" : key, value == null ? "" : value);
	}

	public String removeExtra(String key) {
		return extra == null ? null : extra.remove(key);
	}

	public boolean containsExtra(String key) {
		return extra == null ? null : extra.containsKey(key);
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public DateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(DateTime createTime) {
		this.createTime = createTime;
	}

	public DateTime getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(DateTime updateTime) {
		this.updateTime = updateTime;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public Map<String, String> getReserved() {
		return reserved;
	}

	public void setReserved(Map<String, String> reserved) {
		this.reserved = reserved;
	}

}
