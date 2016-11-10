package com.cana.asset.api;

import com.cana.vbam.common.asset.dto.CustomerDTO;
import com.cana.vbam.common.asset.dto.CustomerListRequestDTO;
import com.cana.vbam.common.asset.dto.CustomerListResponseDTO;
import com.cana.vbam.common.asset.dto.CustomerRequestDTO;
import com.cana.vbam.common.dto.ListResult;

/**
 * 客户信息接口
 * @author jiangzhou.Ren
 * @time 2016年7月22日上午10:33:38
 */
public interface IAssetCustomerApi {
	
	/**
	 * @param CustomerListResponseDTO
	 * 客户列表查询
	 */
	public ListResult<CustomerListResponseDTO> getCustomerList(CustomerListRequestDTO request);
	
	
	/**
	 * @param customerId
	 * 客户详情查询
	 */
	public CustomerDTO getCustomerDetail(String userId,String customerId);
	
	/**
	 * 新增客户信息
	 * @param void
	 * @param customerRequest
	 * @return
	 */
	public void addCustomer(String userId,CustomerRequestDTO customerRequest) throws Exception;
	
	
	/**
	 * 修改客户信息
	 * @param projectRequest
	 * @return
	 */
	public void updateCustomer(String userId,CustomerRequestDTO customerRequest);
	
	
	/**
	 * 检查用客户名称是否存在
	 * @param username
	 * @return boolean
	 */
	public boolean checkCustomernameExist(String customerName,String id)throws Exception;
	
	/**
	 * 根据客户ID获取客户名称
	 * @param userId
	 * @param id
	 * @return
	 */
	public String getCustomerNameById(String userId, String id);
	
}
