/**
 * 
 */
package com.cana.asset.api;

import java.util.List;

import com.cana.vbam.common.asset.dto.BusinessCounterpartyDTO;
import com.cana.vbam.common.asset.dto.InvoiceInfoDTO;
import com.cana.vbam.common.asset.dto.InvoiceInfoQueryDTO;
import com.cana.vbam.common.asset.dto.InvoiceListDTO;
import com.cana.vbam.common.asset.dto.InvoiceQueryDTO;
import com.cana.vbam.common.asset.dto.InvoiceRedisDTO;
import com.cana.vbam.common.dto.PageResult;
import com.cana.vbam.common.member.dto.user.UserSessionDTO;
import com.travelzen.framework.common.PageList;

/**
 * @author guguanggong
 *
 */
public interface IAssetInvApi {

	/**
	 * 查询应收账款列表信息
	 * 
	 * @param queryDTO
	 * @param userSessionDTO
	 * @return
	 */
	public PageList<InvoiceListDTO> getInvoiceList(InvoiceQueryDTO queryDTO, UserSessionDTO userSessionDTO);

	/**
	 * 根据应收账款id查询应收账款信息
	 * 
	 * @param queryDTO
	 * @return
	 */
	public InvoiceListDTO getInvoiceManage(String id,UserSessionDTO userSessionDTO);

	/**
	 * 新建 应收账款信息
	 * @param invoiceListDTO
	 * @param user
	 */
	public void createInvManage(InvoiceListDTO invoiceListDTO, UserSessionDTO user);
	
	/**
	 * 提交修改后的应收账款 修改
	 * 
	 * @param applyQueryDTO
	 * @return
	 */
	public void updateInvManage(InvoiceListDTO invoiceListDTO,UserSessionDTO user);

	/**
	 *
	 * 应收账款导入
	 *
	 * @param invoiceListDTO
	 * @param customerId
	 * @param operatorId
	 */
	public void importExcelInvList(String key,String customerId,String operatorId);

	/**
	 * 根据业务合同号删除（标记业务合同、删除应收账款）
	 * @param id
	 */
	public void delBusManage(String id,UserSessionDTO userSessionDTO);

	/**
	 * 获取redisKey
	 * @return
	 */
	public String generateRediskey();

	/**
	 * 批量保存到redis
	 * @param customerId
	 * @param key
	 * @param list
	 * @throws Exception 
	 */
	public void batchSaveToRedis(String customerId, String key,List<InvoiceRedisDTO> list) throws Exception;

	/**
	 * 查询redis中的应收账款信息
	 * @param key
	 * @param status
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public PageList<InvoiceListDTO> queryInvoiceInfoFromRedis(String key,String operatorId ,String status, int page, int pageSize);

	/**
	 * 根据业务合同号查询业务合同信息
	 * @param queryDTO
	 * @param customerId
	 * @return
	 */
	public InvoiceListDTO getBusinessInfo(InvoiceQueryDTO queryDTO,String customerId);

	/**
	 * 根据业务合同号（必输）、交易对手获取应收账款信息
	 * @param queryDTO
	 * @return
	 */
	public InvoiceListDTO getInvByExample(InvoiceQueryDTO queryDTO);
	
	/**
	 * 获取未放款的应收账款
	 * @param invoiceInfoQueryDTO
	 * @return
	 */
	public List<InvoiceInfoDTO> getUnloanInvoiceInfoDTOs(InvoiceInfoQueryDTO invoiceInfoQueryDTO);

	/**
	 *  查询业务合同中交易对手信息(业务合同号、交易对手名称)
	 * @param queryDTO
	 * @return
	 */
	public PageResult<BusinessCounterpartyDTO> queryBusinessCounterpartyDTO(InvoiceQueryDTO queryDTO);

	/**
	 * 导入测试用
	 * @param generateInvoiceInfoRedisKeyByOperator
	 * @return
	 */
	public PageList<InvoiceRedisDTO> queryPassRedis(String generateInvoiceInfoRedisKeyByOperator,int page, int pageSize);

}
