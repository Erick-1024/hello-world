package com.cana.yundaex.dao.mapper;

import com.cana.yundaex.dao.po.YundaexCustomerApply;

public interface YundaexCreditTableLockMapper {

	YundaexCustomerApply lockCustomerApplyById(String id);
}
