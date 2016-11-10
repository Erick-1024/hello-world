package com.travelzen.framework.core.dict;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

public enum FlowType {
	all("全部"), review("审核"), issue("出票");

	private String desc;

	private FlowType(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

	public List<Pair<String, String>> getFlowStateList() {
		List<Pair<String, String>> flowStates = new ArrayList<>();
		switch (this) {
		case all:
			for (AllQueryState queryState : AllQueryState.values()) {
				flowStates.add(Pair.of(queryState.name(), queryState.getDesc()));
			}
			break;
		case review:
			for (ReviewQueryState queryState : ReviewQueryState.values()) {
				flowStates.add(Pair.of(queryState.name(), queryState.getDesc()));
			}
			break;
		case issue:
			for (IssueQueryState queryState : IssueQueryState.values()) {
				flowStates.add(Pair.of(queryState.name(), queryState.getDesc()));
			}
			break;
		}
		return flowStates;
	}
}
