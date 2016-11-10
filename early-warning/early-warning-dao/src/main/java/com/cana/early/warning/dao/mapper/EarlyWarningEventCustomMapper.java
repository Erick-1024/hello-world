package com.cana.early.warning.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cana.vbam.common.early.warning.dto.EarlyWaringEventTypeDTO;

public interface EarlyWarningEventCustomMapper {

	public List<EarlyWaringEventTypeDTO> getEarlyWaringEventTypeGether(@Param("financeId") String financeId, @Param("outCustomerId") String outCustomerId, @Param("productId") String productId, @Param("states") List<String> states);
	
}
