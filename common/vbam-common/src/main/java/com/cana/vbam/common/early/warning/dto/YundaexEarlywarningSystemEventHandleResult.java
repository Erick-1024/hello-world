package com.cana.vbam.common.early.warning.dto;

import java.io.Serializable;

import com.cana.vbam.common.early.warning.enums.EarlywarningLevel;
import com.cana.vbam.common.early.warning.enums.YundaexEarlywarningEventSubCategory;

public class YundaexEarlywarningSystemEventHandleResult implements Serializable {

	private static final long serialVersionUID = 1L;

	// 预警事件类型
	private YundaexEarlywarningEventSubCategory earlywarningEventSubCategory;
	
	// 预警等级
	private EarlywarningLevel earlywarningLevel;
	
	// 事件描述
	private String represent;
	
	// 附加信息
	private String extra;

	public YundaexEarlywarningEventSubCategory getEarlywarningEventSubCategory() {
		return earlywarningEventSubCategory;
	}

	public void setEarlywarningEventSubCategory(YundaexEarlywarningEventSubCategory earlywarningEventSubCategory) {
		this.earlywarningEventSubCategory = earlywarningEventSubCategory;
	}

	public EarlywarningLevel getEarlywarningLevel() {
		return earlywarningLevel;
	}

	public void setEarlywarningLevel(EarlywarningLevel earlywarningLevel) {
		this.earlywarningLevel = earlywarningLevel;
	}

	public String getRepresent() {
		return represent;
	}

	public void setRepresent(String represent) {
		this.represent = represent;
	}

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}
	
}
