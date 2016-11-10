package com.cana.vbam.common.asset.enums;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * @author yihong.tang
 * @time 2016.5.18
 */
public enum ProjectStatusEnum {
	
	CREATE("立项"), 
	GOING("进行中"), 
	PAUSE("暂停"),
	CLOSE("结束");

	private String desc;

	ProjectStatusEnum(String desc) {
		this.desc = desc;
	}

	public String desc() {
		return desc;
	}

	public static List<ProjectStatusEnum> getNextValidStatus(ProjectStatusEnum prevStatus) {
		return validStatusMap.get(prevStatus);
	}

	//项目状态转移合法值
	private static Map<ProjectStatusEnum, List<ProjectStatusEnum>> validStatusMap = Maps.newHashMap();
	static {
		validStatusMap.put(null, Lists.newArrayList(ProjectStatusEnum.CREATE, ProjectStatusEnum.GOING));
		validStatusMap.put(ProjectStatusEnum.CREATE, Lists.newArrayList(ProjectStatusEnum.CREATE, ProjectStatusEnum.GOING));
		validStatusMap.put(ProjectStatusEnum.GOING, Lists.newArrayList(ProjectStatusEnum.GOING, ProjectStatusEnum.PAUSE, ProjectStatusEnum.CLOSE));
		validStatusMap.put(ProjectStatusEnum.PAUSE, Lists.newArrayList(ProjectStatusEnum.PAUSE, ProjectStatusEnum.GOING));
		validStatusMap.put(ProjectStatusEnum.CLOSE, Lists.newArrayList(ProjectStatusEnum.CLOSE));
	}
}
