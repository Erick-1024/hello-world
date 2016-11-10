package com.cana.asset.service.impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.cana.asset.dao.mapper.gen.AssetInvoiceBasicInfoMapper;
import com.cana.asset.dao.mapper.gen.AssetInvoiceInfoMapper;
import com.cana.asset.dao.mapper.gen.ExpenseMapper;
import com.cana.asset.dao.po.AssetInvoiceBasicInfo;
import com.cana.asset.dao.po.AssetInvoiceBasicInfoExample;
import com.cana.asset.dao.po.AssetInvoiceBasicInfoExample.Criteria;
import com.cana.asset.dao.po.AssetInvoiceInfo;
import com.cana.asset.dao.po.AssetInvoiceInfoExample;
import com.cana.asset.dao.po.Expense;
import com.cana.asset.dao.po.ExpenseExample;
import com.cana.asset.service.IAssetInvoiceService;
import com.cana.asset.service.convertors.InvoiceConvertor;
import com.cana.asset.service.transaction.IAssetLoanInfoTransactionService;
import com.cana.asset.service.transaction.IAssetUserPrivilegeTransactionService;
import com.cana.asset.service.transaction.util.DataPermissionValidator;
import com.cana.vbam.common.asset.dto.ExpenseDTO;
import com.cana.vbam.common.asset.dto.InvoiceInfoDTO;
import com.cana.vbam.common.asset.dto.InvoiceInfoQueryDTO;
import com.cana.vbam.common.asset.dto.InvoiceListDTO;
import com.cana.vbam.common.asset.dto.InvoiceQueryDTO;
import com.cana.vbam.common.asset.dto.UserPrivilegeDTO;
import com.cana.vbam.common.asset.enums.ExpenseType;
import com.cana.vbam.common.asset.enums.LoanState;
import com.cana.vbam.common.member.dto.user.UserSessionDTO;
import com.cana.vbam.common.member.enums.user.UserType;
import com.google.common.collect.Lists;
import com.travelzen.framework.common.PageList;
import com.travelzen.framework.core.common.ReturnCode;
import com.travelzen.framework.core.exception.WebException;

@Service
public class AssetInvoiceServiceImpl implements IAssetInvoiceService {

	@Resource
	private AssetInvoiceBasicInfoMapper assetInvoiceBasicInfoMapper;
	
	@Resource
	private AssetInvoiceInfoMapper assetInvoiceInfoMapper;
	
	@Resource 
	private ExpenseMapper expenseMapper;
	
	@Resource
	private IAssetLoanInfoTransactionService assetLoanInfoTransactionService; 
	
	@Resource
	private IAssetUserPrivilegeTransactionService assetUserPrivilegeTransactionService;
	
	@Resource
	private DataPermissionValidator dataPermissionValidator;
	
	@Override
	public PageList<InvoiceListDTO> getInvoiceList(InvoiceQueryDTO queryDTO, UserSessionDTO userSessionDTO) {

		PageList<InvoiceListDTO> result = new PageList<InvoiceListDTO>();
		AssetInvoiceBasicInfoExample invBasicInfoExample = new AssetInvoiceBasicInfoExample();
		//拼接sql搜索条件
		invBasicInfoCriteria(invBasicInfoExample,queryDTO,userSessionDTO);
		invBasicInfoExample.setOrderByClause("create_time desc");
		int pageSize = queryDTO.getPageSize();
		invBasicInfoExample.setLimitStart((queryDTO.getPage() -1) * pageSize);
		invBasicInfoExample.setLimitEnd(pageSize);
		
		List<AssetInvoiceBasicInfo> assetInvoiceList = assetInvoiceBasicInfoMapper.selectByExample(invBasicInfoExample); 
		if(CollectionUtils.isEmpty(assetInvoiceList))
			return result;
		List<InvoiceListDTO> InvoiceListDTOs = InvoiceConvertor.convertorInvListToInvoiceDTO(assetInvoiceList);
		//放款状态
		for(InvoiceListDTO invListDTO :InvoiceListDTOs){
			String businessContractNo2 = invListDTO.getBusinessContractNo();
			boolean flag = assetLoanInfoTransactionService.checkContractNoHasLoan(businessContractNo2); 
			if(flag){
				invListDTO.setLoanState(LoanState.LOANED.name()); 
				invListDTO.setLoanStateDesc(LoanState.LOANED.desc());
			}else{
				invListDTO.setLoanState(LoanState.UNLOAN.name()); 
				invListDTO.setLoanStateDesc(LoanState.UNLOAN.desc());
			}
		}
		result.setRecords(InvoiceListDTOs);
		result.setTotalRecords(assetInvoiceBasicInfoMapper.countByExample(invBasicInfoExample));
		return result;
	}


	/**
	 * 封装搜索条件
	 * @param invBasicInfoExample
	 * @param queryDTO
	 * @param userSessionDTO
	 */
	private void invBasicInfoCriteria(AssetInvoiceBasicInfoExample invBasicInfoExample, InvoiceQueryDTO queryDTO,
			UserSessionDTO userSessionDTO) {
		String id = userSessionDTO.getId();
		UserType userType = userSessionDTO.getUserType();
		String customerName = queryDTO.getCustomerName(); 
		String businessContractNo = queryDTO.getBusinessContractNo(); 
		String currencyType = queryDTO.getCurrencyType(); 
		String businessProduct = queryDTO.getBusinessProduct(); 
		
		Criteria invBasicInfoCriteria = invBasicInfoExample.createCriteria();
		switch (userType) {
		case FINACE:
			Set<String> allowedCustomerIdList = assetUserPrivilegeTransactionService.allowedCustomerIdList(userSessionDTO.getMasterId()==null?id:userSessionDTO.getMasterId());
			if(CollectionUtils.isNotEmpty(allowedCustomerIdList)){
				List<String> allowedCustomerIds = Lists.newArrayList(allowedCustomerIdList);
				invBasicInfoCriteria.andCustomerIdIn(allowedCustomerIds);
		    }
			break;
		case FACTOR:
			if(StringUtils.isNotBlank(id))
				invBasicInfoCriteria.andFactorIdEqualTo(id);
			break;
		default:
			throw WebException.instance(ReturnCode.NO_PERMISSION);
		}
		if(StringUtils.isNotBlank(customerName))
			invBasicInfoCriteria.andCustomerNameLike("%"+customerName.trim()+"%");
		if(StringUtils.isNotBlank(currencyType))
			invBasicInfoCriteria.andCurrencyEqualTo(currencyType);
		if(StringUtils.isNotBlank(businessContractNo))
			invBasicInfoCriteria.andBusinessContractNoEqualTo(businessContractNo);
		if(StringUtils.isNotBlank(businessProduct))
			invBasicInfoCriteria.andBusinessProductEqualTo(businessProduct);
	}


	@Override
	public InvoiceListDTO getInvoiceManage(String basicId,UserSessionDTO userSessionDTO) {
		if(StringUtils.isBlank(basicId))
			throw WebException.instance("应收账款基本信息id为空");
		UserType userType = userSessionDTO.getUserType();
		String masterId = userSessionDTO.getMasterId()==null?userSessionDTO.getId():userSessionDTO.getMasterId();
		AssetInvoiceBasicInfoExample invBasecInfoExample = new AssetInvoiceBasicInfoExample();
	    invBasecInfoExample.createCriteria().andIdEqualTo(basicId); 
		List<AssetInvoiceBasicInfo> assetInvoiceBasicInfos = assetInvoiceBasicInfoMapper.selectByExample(invBasecInfoExample);
		if(CollectionUtils.isNotEmpty(assetInvoiceBasicInfos)){
			AssetInvoiceBasicInfo assetInvoiceBasicInfo = assetInvoiceBasicInfos.get(0); 
			UserPrivilegeDTO userPrivilegeDTO = dataPermissionValidator.checkDataPermissionsByUsertype(userType, masterId,assetInvoiceBasicInfo.getCustomerId());
		    // 保理商 数据权限校验
			if(StringUtils.isNotBlank(userPrivilegeDTO.getFactorId())){
				if(!assetInvoiceBasicInfo.getFactorId().equals(userPrivilegeDTO.getFactorId()))
					throw WebException.instance("没有该应收账款的数据权限");
			}
			InvoiceListDTO invDTO = InvoiceConvertor.convertorInvBasicInfoToInvDTO(assetInvoiceBasicInfos.get(0));
			//放款状态
			boolean flag = assetLoanInfoTransactionService.checkContractNoHasLoan(invDTO.getBusinessContractNo());
			if(flag){
				invDTO.setLoanState(LoanState.LOANED.name()); 
				invDTO.setLoanStateDesc(LoanState.LOANED.desc());
			}else{
				invDTO.setLoanState(LoanState.UNLOAN.name()); 
				invDTO.setLoanStateDesc(LoanState.UNLOAN.desc());
			}
			
			AssetInvoiceInfoExample invExample = new AssetInvoiceInfoExample();
			invExample.createCriteria().andInvoiceBaseIdEqualTo(basicId);
			invExample.setOrderByClause("update_time desc");
			List<AssetInvoiceInfo> invList = assetInvoiceInfoMapper.selectByExample(invExample); 
			List<InvoiceInfoDTO> invoiceInfoDTOs = InvoiceConvertor.convertorAssetInvInfoToInvInfoDTO(invList,invDTO.getBusinessContractNo());
			invDTO.setInvoiceInfoDTOs(invoiceInfoDTOs);
			
			ExpenseExample expenseExample = new ExpenseExample();
			expenseExample.createCriteria().andRefidEqualTo(basicId).andReftypeEqualTo(ExpenseType.INVOICE.name());
			expenseExample.setOrderByClause("sequence asc");
			List<Expense> expenses = expenseMapper.selectByExample(expenseExample);
			List<ExpenseDTO> expenseDTOs = InvoiceConvertor.convertorExpensesToExpenseDTO(expenses);
			invDTO.setExpenseDTOs(expenseDTOs);
			return invDTO;
		}else{
			throw WebException.instance("没有该应收账款信息，应收账款id："+basicId);
		}
	}


	@Override
	public AssetInvoiceBasicInfo getAssetInvoiceBasicInfoByBusinessContractNo(String businessContractNo) {
		AssetInvoiceBasicInfoExample assetInvoiceBasicInfoExample = new AssetInvoiceBasicInfoExample();
		assetInvoiceBasicInfoExample.createCriteria().andBusinessContractNoEqualTo(businessContractNo);
		List<AssetInvoiceBasicInfo> assetInvoiceBasicInfos = assetInvoiceBasicInfoMapper.selectByExample(assetInvoiceBasicInfoExample);
		if(assetInvoiceBasicInfos.size() == 0)
			return null;
		return assetInvoiceBasicInfos.get(0);
	}


	@Override
	public List<InvoiceInfoDTO> getUnloanInvoiceInfoDTOs(InvoiceInfoQueryDTO invoiceInfoQueryDTO) {
		AssetInvoiceInfoExample assetInvoiceInfoExample = new AssetInvoiceInfoExample();
		assetInvoiceInfoExample.createCriteria().andInvoiceBaseIdEqualTo(invoiceInfoQueryDTO.getInvoiceBaseId()).andCounterpartyIdEqualTo(invoiceInfoQueryDTO.getCounterpartyId()).andLoanInfoIdIsNull();
		return InvoiceConvertor.convertorAssetInvInfoToInvInfoDTO(assetInvoiceInfoMapper.selectByExample(assetInvoiceInfoExample), invoiceInfoQueryDTO.getBusinessContractNo());
	}

}
