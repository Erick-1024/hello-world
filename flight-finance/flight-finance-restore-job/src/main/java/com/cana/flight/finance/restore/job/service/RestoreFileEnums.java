package com.cana.flight.finance.restore.job.service;

public enum RestoreFileEnums {
	restore_file_subscript("读取到的文件下标"),
	restore_file_path("文件存放路径"),
	restore_file_pointer("读取的文件指针");
	private String desc;
	private RestoreFileEnums(String desc){
		this.desc=desc;
	}
	public String desc(){
		return desc;
	}
}
