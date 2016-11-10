package com.cana.yundaex.common.dto;

import java.io.Serializable;
import java.util.List;

public class YundaexLineImportRedisDTO implements Serializable {
	
	private static final long serialVersionUID = 2630571583781640536L;
	private List<YundaexLineImportDTO> passYundaexLineImportDTOs;
	private List<YundaexLineImportDTO> notPassYundaexLineImportDTOs;
	
	public List<YundaexLineImportDTO> getPassYundaexLineImportDTOs() {
		return passYundaexLineImportDTOs;
	}
	public void setPassYundaexLineImportDTOs(List<YundaexLineImportDTO> passYundaexLineImportDTOs) {
		this.passYundaexLineImportDTOs = passYundaexLineImportDTOs;
	}
	public List<YundaexLineImportDTO> getNotPassYundaexLineImportDTOs() {
		return notPassYundaexLineImportDTOs;
	}
	public void setNotPassYundaexLineImportDTOs(List<YundaexLineImportDTO> notPassYundaexLineImportDTOs) {
		this.notPassYundaexLineImportDTOs = notPassYundaexLineImportDTOs;
	}
	
	
	
}
