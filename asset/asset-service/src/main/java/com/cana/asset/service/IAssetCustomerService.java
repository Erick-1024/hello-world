package com.cana.asset.service;

import com.cana.asset.dao.po.Customer;
import com.cana.vbam.common.asset.dto.CounterpartyListSearchDTO;
import com.cana.vbam.common.asset.dto.CustomerListResponseDTO;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.member.vo.UserVo;

public interface IAssetCustomerService {

	/**
	 * 根据客户ID获取客户名称
	 * @param id
	 * @return
	 */
	public Customer getCustomerNameById(String id);
	
	
	/**
	 * 按条件查询客户列表
	 * @param searchDTO
	 */
	public ListResult<CustomerListResponseDTO> queryCustomerListByCondition(UserVo userDetail, CounterpartyListSearchDTO searchDTO);
	
}
