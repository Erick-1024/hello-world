package com.cana.flight.finance.common.enums;

public enum ProduceType {

	ALL("全量"),
	PART("增量");
	
	private String desc;
	
	private ProduceType(String desc) {
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}
	
	
}
