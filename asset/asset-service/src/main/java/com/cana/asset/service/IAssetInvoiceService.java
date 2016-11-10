package com.cana.asset.service;


import java.util.List;

import com.cana.asset.dao.po.AssetInvoiceBasicInfo;import com.cana.vbam.common.asset.dto.InvoiceInfoDTO;
import com.cana.vbam.common.asset.dto.InvoiceInfoQueryDTO;
import com.cana.vbam.common.asset.dto.InvoiceListDTO;
import com.cana.vbam.common.asset.dto.InvoiceQueryDTO;
import com.cana.vbam.common.member.dto.user.UserSessionDTO;
import com.travelzen.framework.common.PageList;

public interface IAssetInvoiceService {

	/**
	 * 查询应收账款列表信息
	 * @param queryDTO
	 * @param userSessionDTO
	 * @return
	 */
	PageList<InvoiceListDTO> getInvoiceList(InvoiceQueryDTO queryDTO, UserSessionDTO userSessionDTO);


	/**
	 * 应收账款详情查询
	 * @param id
	 * @return
	 */
	InvoiceListDTO getInvoiceManage(String id,UserSessionDTO userSessionDTO );

	/**
	 * 根据业务合同号获取应收账款基本信息
	 * @param businessContractNo
	 * @return
	 */
	public AssetInvoiceBasicInfo getAssetInvoiceBasicInfoByBusinessContractNo(String businessContractNo);
	
	/**
	 * 获取未放款的应收账款
	 * @param invoiceInfoQueryDTO
	 * @return
	 */
	public List<InvoiceInfoDTO> getUnloanInvoiceInfoDTOs(InvoiceInfoQueryDTO invoiceInfoQueryDTO);
	
}
