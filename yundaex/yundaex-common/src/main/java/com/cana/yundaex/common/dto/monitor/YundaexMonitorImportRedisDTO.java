package com.cana.yundaex.common.dto.monitor;

import java.io.Serializable;
import java.util.List;

public class YundaexMonitorImportRedisDTO implements Serializable {
	
	private static final long serialVersionUID = 2630571583781640536L;
	private List<YundaexMonitorImportDTO> passYundaexMonitorImportDTOs;
	private List<YundaexMonitorImportDTO> notPassYundaexMonitorImportDTOs;
	
	public List<YundaexMonitorImportDTO> getPassYundaexMonitorImportDTOs() {
		return passYundaexMonitorImportDTOs;
	}
	public void setPassYundaexMonitorImportDTOs(List<YundaexMonitorImportDTO> passYundaexMonitorImportDTOs) {
		this.passYundaexMonitorImportDTOs = passYundaexMonitorImportDTOs;
	}
	public List<YundaexMonitorImportDTO> getNotPassYundaexMonitorImportDTOs() {
		return notPassYundaexMonitorImportDTOs;
	}
	public void setNotPassYundaexMonitorImportDTOs(List<YundaexMonitorImportDTO> notPassYundaexMonitorImportDTOs) {
		this.notPassYundaexMonitorImportDTOs = notPassYundaexMonitorImportDTOs;
	}
	
	
	
}
