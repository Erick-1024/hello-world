package com.cana.credit.dao.mapper;

import java.util.List;

import com.cana.vbam.common.credit.dto.limit.CustomerLimitListQueryDTO;
import com.cana.vbam.common.credit.dto.limit.CustomerLimitListResponseDTO;

public interface CreditLimitCustomMapper {
	
	int count(CustomerLimitListQueryDTO criteria);

    List<CustomerLimitListResponseDTO> find(CustomerLimitListQueryDTO criteria);
}
