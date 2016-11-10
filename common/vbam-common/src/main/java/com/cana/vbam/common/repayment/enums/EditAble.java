package com.cana.vbam.common.repayment.enums;

public enum EditAble {
		EDITABLE("可编辑"),
		EDITUNADLE("不可编辑");
		
		private String desc;
		
		private EditAble(String desc){
			this.desc = desc;
		} 
		
		public static EditAble getValue(String desc){
			if(EDITABLE.desc.equals(desc))
				return EDITABLE;
			if(EDITUNADLE.desc.equals(desc))
				return EDITUNADLE;
			return null;
		}
		
		public String desc() {
			return desc;
		}
}
