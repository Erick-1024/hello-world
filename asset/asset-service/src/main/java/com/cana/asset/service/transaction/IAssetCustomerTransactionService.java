package com.cana.asset.service.transaction;

import java.util.Set;

import com.cana.vbam.common.asset.dto.CustomerDTO;
import com.cana.vbam.common.asset.dto.CustomerListRequestDTO;
import com.cana.vbam.common.asset.dto.CustomerListResponseDTO;
import com.cana.vbam.common.asset.dto.CustomerRequestDTO;
import com.cana.vbam.common.asset.dto.CustomerResponseDTO;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.member.vo.UserVo;

/**
 * @author jiangzhou.Ren
 * @time 2016年7月22日下午1:24:54
 */
public interface IAssetCustomerTransactionService {
	
	/**
	 * @param customerIdList 
	 * @param CustomerListResponseDTO
	 * 客户列表查询
	 */
	public ListResult<CustomerListResponseDTO> getCustomerList(UserVo userDetail,CustomerListRequestDTO request);
	
	
	
	/**
	 * @param customerId
	 * 客户详情查询
	 */
	public CustomerDTO getCustomerDetail(UserVo userDetail,String customerId);
	
	
	/**
	 * 新增客户信息
	 * @param void
	 * @param customerRequest
	 * @return
	 */
	public void addCustomer(UserVo userDetail,CustomerRequestDTO customerRequest);
	
	
	/**
	 * 修改客户信息
	 * @param projectRequest
	 * @return
	 */
	public void updateCustomer(UserVo userDetail,CustomerRequestDTO customerRequest);
	
	
	
	 /**
     * 检查客户名称是否存在
     * 
     * @param username
     * @return boolean
     */
	public boolean checkCustomernameExist(String customerName,String id);

}
