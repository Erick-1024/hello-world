package com.cana.asset.service.transaction;

import java.util.List;

import com.cana.asset.dao.po.AssetInvoiceInfo;
import com.cana.asset.dao.utils.IDGenerator;
import com.cana.vbam.common.asset.dto.InvoiceExcelInfoDTO;
import com.cana.vbam.common.asset.dto.InvoiceListDTO;
import com.cana.vbam.common.asset.dto.InvoiceQueryDTO;
import com.cana.vbam.common.member.dto.user.UserSessionDTO;
import com.travelzen.framework.core.exception.WebException;

public interface IAssetInvoiceTransactionService {

	/**
	 * 应收账款 修改
	 * @param invoiceListDTO
	 */
	void updateInvManage(InvoiceListDTO invoiceListDTO,UserSessionDTO user);

	/**
	 * 应收账款删除
	 * @param id
	 */
	void delBusManage(String id,UserSessionDTO userSessionDTO );

	/**
	 * 将redis中数据转到mysql中
	 * @param invListDTO
	 * @param invExcelInfoDTO
	 * @param customerId
	 */
	void importExcelInvList(InvoiceListDTO invListDTO, InvoiceExcelInfoDTO invExcelInfoDTO,String customerId);

	/**
	 * 根据业务合同号(必输)、交易对手获取应收账款信息
	 */
	InvoiceListDTO getInvByExample(InvoiceQueryDTO queryDTO);

	/**
	 * 添加应收账款
	 * 
	 * @param contractNo
	 *            业务合同号
	 * @param invoices
	 *            添加的应收账款列表，需提供以下字段的值：
	 *            id（IDGenerator.generateAssetInvoiceInfoId()）、交易对手ID、交易对手、单证号码、
	 *            单证面额（单位分）、应收金额（单位分）、 融资比例（0～1之间的小数类型，保留5位小数）、
	 *            开票日（yyyy-MM-dd）、到期日（yyyy-MM-dd） 字段的值，其他字段值无需提供
	 * @throws WebException
	 *             当 contractNo 不存在应收账款基本信息时抛出，而该信息应在调用此接口之前存在
	 */
	void addInvoiceInfoByContractNo(String contractNo, List<AssetInvoiceInfo> invoices);
}
