/**
 * 
 */
package com.cana.yundaex.service;

import java.util.List;

import com.cana.yundaex.common.dto.YundaexCustomerApplyDTO;
import com.cana.yundaex.common.dto.YundaexCustomerApplyQueryDTO;

/**
 * 韵达项目-韵达 客户申请资料 操作服务
 * @author guguanggong
 *
 */
public interface IYundaexCustomerApplyService {

	/**
	 * 根据参数查询韵达客户申请资料
	 * @param applyQueryDTO
	 * @return
	 */
	public List<YundaexCustomerApplyDTO> getYdCustApplyByParam(YundaexCustomerApplyQueryDTO applyQueryDTO);

	/**
	 * 根据网点编号修改 是否有网点信息 标记
	 * @param dto
	 */
	public void updateCustomerApplyById(YundaexCustomerApplyDTO dto);
}
