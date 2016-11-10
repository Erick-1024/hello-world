package com.cana.asset.service.transaction.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cana.asset.dao.mapper.gen.CustomerMagMapper;
import com.cana.asset.dao.mapper.gen.CustomerMapper;
import com.cana.asset.dao.mapper.gen.CustomerMasMapper;
import com.cana.asset.dao.mapper.gen.CustomerPurchaseMapper;
import com.cana.asset.dao.mapper.gen.CustomerSaleMapper;
import com.cana.asset.dao.mapper.gen.CustomerStkMapper;
import com.cana.asset.dao.po.Customer;
import com.cana.asset.dao.po.CustomerExample;
import com.cana.asset.dao.po.CustomerMag;
import com.cana.asset.dao.po.CustomerMagExample;
import com.cana.asset.dao.po.CustomerMas;
import com.cana.asset.dao.po.CustomerMasExample;
import com.cana.asset.dao.po.CustomerPurchase;
import com.cana.asset.dao.po.CustomerPurchaseExample;
import com.cana.asset.dao.po.CustomerSale;
import com.cana.asset.dao.po.CustomerSaleExample;
import com.cana.asset.dao.po.CustomerStk;
import com.cana.asset.dao.po.CustomerStkExample;
import com.cana.asset.dao.utils.IDGenerator;
import com.cana.asset.service.convertors.CustomerCovertor;
import com.cana.asset.service.transaction.IAssetCustomerTransactionService;
import com.cana.asset.service.transaction.IAssetUserPrivilegeTransactionService;
import com.cana.asset.service.transaction.util.AssetCustomerPersistenceValidator;
import com.cana.vbam.common.asset.dto.CustomerDTO;
import com.cana.vbam.common.asset.dto.CustomerListRequestDTO;
import com.cana.vbam.common.asset.dto.CustomerListResponseDTO;
import com.cana.vbam.common.asset.dto.CustomerMagDTO;
import com.cana.vbam.common.asset.dto.CustomerMasDTO;
import com.cana.vbam.common.asset.dto.CustomerPurchaseDTO;
import com.cana.vbam.common.asset.dto.CustomerRequestDTO;
import com.cana.vbam.common.asset.dto.CustomerSaleDTO;
import com.cana.vbam.common.asset.dto.CustomerStkDTO;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.member.enums.user.UserType;
import com.cana.vbam.common.member.vo.UserVo;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.util.StringUtil;

/**
 * @author jiangzhou.Ren
 * @time 2016年7月22日下午1:25:33
 */
@Service
public class AssetCustomerTransactionServiceImpl implements IAssetCustomerTransactionService{
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private CustomerMapper customerMapper;
	
	@Resource
	private CustomerMagMapper customerMagMapper;
	
	@Resource
	private CustomerMasMapper customerMasMapper;
	
	@Resource
	private CustomerPurchaseMapper customerPurchaseMapper;
	
	@Resource
	private CustomerSaleMapper customerSaleMapper;
	
	@Resource
	private CustomerStkMapper customerStkMapper;

	@Resource
	private IAssetUserPrivilegeTransactionService assetUserPrivilegeTransactionService;
	
	//查询客户信息列表
	@Override
	public ListResult<CustomerListResponseDTO> getCustomerList(UserVo userDetail,CustomerListRequestDTO request) {

		if (userDetail == null) {
			throw WebException.instance("用户信息为空");
		}
		StringUtil.trimObjectFields(request);
		CustomerExample customerExample = new CustomerExample();
		CustomerExample.Criteria criteria = customerExample.createCriteria();
		// 如果是保理商查询保理商创建的
		if (userDetail.getCustomer().getUserType().equals(UserType.FACTOR)) {
			criteria.andFactorIdEqualTo(userDetail.getCustomerId());
		}
		//查询有权限的客户ids
		Set<String> customerIdList = assetUserPrivilegeTransactionService.allowedCustomerIdList(userDetail.getCustomerId());
		if (userDetail.getCustomer().getUserType().equals(UserType.FINACE)) {
			if (CollectionUtils.isNotEmpty(customerIdList)){
				List<String> Ids = new ArrayList<String>(customerIdList);
				criteria.andIdIn(Ids);
			}else{
				return null;
			}
		}
		
		// 判断查询条件和sql条件拼接
		if (StringUtils.isNotBlank(request.getCustomerName())) {// 客户名称
			criteria.andCustomerNameLike("%" + request.getCustomerName() + "%");
		}
		if (null != request.getCustomerType()) {// 客户类型
			criteria.andCustomerTypeLike("%" + request.getCustomerType().name() + "%");
		}
		if (!CollectionUtils.isEmpty(request.getEnterpriseMaterialState())) {
			criteria.andEnterpriseMaterialStateIn(request.getEnterpriseMaterialState());
		}
		customerExample.setOrderByClause("create_time desc");
		// 分页参数
		customerExample.setLimitStart((request.getPage() - 1) * request.getPageSize());
		customerExample.setLimitEnd(request.getPageSize());
		List<Customer> customers = customerMapper.selectByExample(customerExample);
		int count = customerMapper.countByExample(customerExample);
		List<CustomerListResponseDTO> customerDTOs = CustomerCovertor.convertCustomertDao2ResDTO(customers);
		return ListResult.success(customerDTOs, count);
	}

	
	
	
	//查询客户详情
	@Override
	public CustomerDTO getCustomerDetail(UserVo userDetail, String customerId) {
		checkGetCustomerDetail(userDetail, customerId);
		// 如果是cana
		if (userDetail.getCustomer().getUserType().equals(UserType.CANA)) {
			// 查询客户基本信息和高级信息
			CustomerDTO customerDTO = queryCustomerInfo(customerId);
			return customerDTO;
			// 如果是保理商
		} else if (userDetail.getCustomer().getUserType().equals(UserType.FACTOR)) {
			CustomerDTO customerDTO = queryCustomerInfo(customerId);
			if (StringUtils.equals(customerDTO.getFactorId(), userDetail.getCustomerId())) {
				return customerDTO;
			} else {
				throw WebException.instance("没有权限查看详情");
			}
		} else if (userDetail.getCustomer().getUserType().equals(UserType.FINACE)) {
			boolean allow = assetUserPrivilegeTransactionService.allow(userDetail.getCustomerId(), customerId);
			if (allow == true) {
				CustomerDTO customerDTO = queryCustomerInfo(customerId);
				return customerDTO;
			} else {
				throw WebException.instance("没有权限查看详情");
			}
		}
		return null;
	}

	/**
	 * 查询客户信息方法抽取
	 * 
	 * @param customerId
	 * @return
	 */
	private CustomerDTO queryCustomerInfo(String customerId){
		//查询客户基本信息
		Customer customer = customerMapper.selectByPrimaryKey(customerId);
		//高管人员查询
		CustomerMagExample magExample = new CustomerMagExample();
		magExample.createCriteria().andCustomerIdEqualTo(customerId);
		List<CustomerMag> customerMags = customerMagMapper.selectByExample(magExample);
		//融资查询
		CustomerMasExample customerMasExample = new CustomerMasExample();
		customerMasExample.createCriteria().andCustomerIdEqualTo(customerId);
		List<CustomerMas> customerMass = customerMasMapper.selectByExample(customerMasExample);
		//股东查询
		CustomerStkExample customerStkExample = new CustomerStkExample();
		customerStkExample.createCriteria().andCustomerIdEqualTo(customerId);
		List<CustomerStk> customerStks = customerStkMapper.selectByExample(customerStkExample);
		//采购查询
		CustomerPurchaseExample purchaseExample = new CustomerPurchaseExample();
		purchaseExample.createCriteria().andCustomerIdEqualTo(customerId);
		List<CustomerPurchase> customerPurchases = customerPurchaseMapper.selectByExample(purchaseExample);
		//销售查询
		CustomerSaleExample saleExample = new CustomerSaleExample();
		saleExample.createCriteria().andCustomerIdEqualTo(customerId);
		List<CustomerSale> customerSales = customerSaleMapper.selectByExample(saleExample);
		CustomerDTO customerDTO = CustomerCovertor.convertorToCustomerDTO(customer,customerMags,customerMass,customerStks,customerPurchases,customerSales);
		return customerDTO;
		
	}
	
	//新增客户信息
	@Override
	public void addCustomer(UserVo userDetail,CustomerRequestDTO customerRequest) {
		StringUtil.trimObjectFields(customerRequest);
		//校验请求参数
		AssetCustomerPersistenceValidator.checkCustomerInfoFieldsIsValid(userDetail,customerRequest);
		String customerId = IDGenerator.generateAssetCustomerId();
		saveCustomer(userDetail,customerId,customerRequest);
		saveCustomerMag(customerId,customerRequest);
		saveCustomerSKT(customerId,customerRequest);
		saveCustomerMas(customerId,customerRequest);
		saveCustomerPurchase(customerId,customerRequest);
		saveCustomerSale(customerId,customerRequest);
		logger.info("创建客户信息");
	}

	//修改客户信息
	@Override
	public void updateCustomer(UserVo userDetail,CustomerRequestDTO customerRequest) {
		StringUtil.trimObjectFields(customerRequest);
		AssetCustomerPersistenceValidator.checkCustomerInfoFieldsIsValid(userDetail,customerRequest);
		
		Customer oldCustomer = customerMapper.selectByPrimaryKey(customerRequest.getId());
		if (oldCustomer == null)
			throw WebException.instance("客户ID不存在");
		if (!StringUtils.equals(userDetail.getCustomerId(), oldCustomer.getFactorId()))
			throw WebException.instance("当前客户无权限修改该客户信息");
		deleteOldCustomerData(customerRequest);
		
		saveCustomer(userDetail,oldCustomer.getId(),customerRequest);
		saveCustomerMag(oldCustomer.getId(),customerRequest);
		saveCustomerSKT(oldCustomer.getId(),customerRequest);
		saveCustomerMas(oldCustomer.getId(),customerRequest);
		saveCustomerPurchase(oldCustomer.getId(),customerRequest);
		saveCustomerSale(oldCustomer.getId(),customerRequest);
		logger.info("修改客户信息");
	}

	
	/**
	 * 校验获取客户详情请求参数的合法性
	 * @param userDetail
	 * @param customerId
	 * @throws WebException
	 */
	private void checkGetCustomerDetail(UserVo userDetail,String customerId) throws WebException {
		//请求参数校验
		if(userDetail == null){
			throw WebException.instance("userDetail为空");
		}
		if(StringUtils.isBlank(customerId)){
			throw WebException.instance("CustomerId为空");
		}
	}
	//保存客户基本信息
	private void saveCustomer(UserVo userDetail,String customerId,CustomerRequestDTO customerRequest){
		Customer customer = new Customer();
		customer.setId(customerRequest.getId());
		customer.setFactorId(userDetail.getCustomerId());
		customer.setCustomerName(customerRequest.getCustomerName());
		customer.setCustomerType(customerRequest.getCustomerType().name());
		customer.setContactName(customerRequest.getContactName());
		customer.setMobileNo(customerRequest.getMobileNo());
		customer.setMail(customerRequest.getMail());
		customer.setCompanyAddress(customerRequest.getCompanyAddress());
		customer.setEconomicCategory(customerRequest.getEconomicCategory().name());
		customer.setIndustry(customerRequest.getIndustry().name());
		customer.setCity(customerRequest.getCity().name());
		customer.setBusinessLicenceCode(customerRequest.getBusinessLicenceCode());
		customer.setBusinessLicenceCodeExpiryDate(customerRequest.getBusinessLicenceCodeExpiryDate());
		customer.setTaxRegistrationCertificateCode(customerRequest.getTaxRegistrationCertificateCode());
		customer.setTaxRegistrationCertificateCodeExpiryDate(customerRequest.getTaxRegistrationCertificateCodeExpiryDate());
		customer.setOrganizationCode(customerRequest.getOrganizationCode());
		customer.setOrganizationCodeExpiryDate(customerRequest.getOrganizationCodeExpiryDate());
		customer.setEnterpriseMaterialState(customerRequest.getEnterpriseMaterialState());
		customer.setRegisteredCapital(customerRequest.getRegisteredCapital());
		customer.setLegalRepresentative(customerRequest.getLegalRepresentative());
		if (StringUtils.isEmpty(customerRequest.getId())) {
			customer.setId(customerId);
			customerMapper.insertSelective(customer);
			logger.info("基本信息添加到数据库");
		} else {
			customer.setId(customerRequest.getId());
			customerMapper.updateByPrimaryKeySelective(customer);
		}
		logger.info("保存客户基本信息");
	}
	//保存客户高管
	private void saveCustomerMag(String customerId,CustomerRequestDTO customerRequest){
		if(CollectionUtils.isNotEmpty(customerRequest.getCustomerMags())){
			for(CustomerMagDTO customerMagDTO : customerRequest.getCustomerMags()){
				CustomerMag customerMag = new CustomerMag();
				customerMag.setCustomerId(customerId);
				customerMag.setId(customerMagDTO.getId());
				customerMag.setMagName(customerMagDTO.getMagName());
				customerMag.setMagDutyType(customerMagDTO.getMagDutyType());
				customerMag.setMagEducation(customerMagDTO.getMagEducation().name());
				customerMag.setExperience(customerMagDTO.getExperience());
				customerMag.setMagIdentityCardNo(customerMagDTO.getMagIdentityCardNo());
				customerMag.setMagProfession(customerMagDTO.getMagProfession());
				customerMag.setMagSex(customerMagDTO.getMagSex());
				if (StringUtils.isBlank(customerMagDTO.getId())) {
					 String customerMagId = IDGenerator.generateAssetCustomerMagId();
					customerMag.setId(customerMagId);
				} else {
					customerMag.setId(customerMagDTO.getId());
				}
				customerMagMapper.insertSelective(customerMag);
			}
		}
		logger.info("保存客户高管信息");
	}
	
	
	//保存股东信息
	private void saveCustomerSKT(String customerId,CustomerRequestDTO customerRequest){
		if(CollectionUtils.isNotEmpty(customerRequest.getCustomerStks())){
			for(CustomerStkDTO customerStkDTO : customerRequest.getCustomerStks()){
				CustomerStk stkDTO = new CustomerStk();
				stkDTO.setCustomerId(customerId);
				stkDTO.setStkType(customerStkDTO.getStkType().name());
				stkDTO.setStkName(customerStkDTO.getStkName());
				stkDTO.setStkPayamt(customerStkDTO.getStkPayamt());
				stkDTO.setStkPcnt(customerStkDTO.getStkPcnt());
				stkDTO.setStkPayamtType(customerStkDTO.getStkPayamtType().name());
				stkDTO.setStkStatus(customerStkDTO.getStkStatus());
				if(StringUtils.isBlank(customerStkDTO.getId())){
					String customerStkId = IDGenerator.generateAssetCustomerStkId();
					stkDTO.setId(customerStkId);
				}else {
					stkDTO.setId(customerStkDTO.getId());
				}
				customerStkMapper.insertSelective(stkDTO);
			}
		}
		logger.info("保存股东信息");
	}
	
	
	//保存融资信息
	private void saveCustomerMas(String customerId,CustomerRequestDTO customerRequest){
		if(CollectionUtils.isNotEmpty(customerRequest.getCustomerMass())){
			for(CustomerMasDTO customerMasDTO : customerRequest.getCustomerMass()){
				CustomerMas masDTO = new CustomerMas();
				masDTO.setCustomerId(customerId);
				masDTO.setFinancialInstitutionName(customerMasDTO.getFinancialInstitutionName());
				masDTO.setAmount(customerMasDTO.getAmount());
				masDTO.setStartDate(customerMasDTO.getStartDate());
				masDTO.setEndDate(customerMasDTO.getEndDate());
				masDTO.setGuaranteeType(customerMasDTO.getGuaranteeType());
				masDTO.setRemark(customerMasDTO.getRemark());
				if(StringUtils.isBlank(customerMasDTO.getId())){
					String customerMasId = IDGenerator.generateAssetCustomerMasId();
					masDTO.setId(customerMasId);
				}else {
					masDTO.setId(customerMasDTO.getId());
				}
				customerMasMapper.insertSelective(masDTO);
			}
		}
		logger.info("保存融资信息");
	}
	
	
	//保存客户采购信息
	private void saveCustomerPurchase(String customerId,CustomerRequestDTO customerRequest){
		if (CollectionUtils.isNotEmpty(customerRequest.getCustomerPurchases())) {
			for(CustomerPurchaseDTO customerPurchaseDTO :customerRequest.getCustomerPurchases()){
					CustomerPurchase purchaseDTO = new CustomerPurchase();
					purchaseDTO.setCustomerId(customerId);
					purchaseDTO.setSupplierName(customerPurchaseDTO.getSupplierName());
					purchaseDTO.setPurchaseLastYear(customerPurchaseDTO.getPurchaseLastYear());
					purchaseDTO.setPercent(customerPurchaseDTO.getPercent());
					purchaseDTO.setSettleType(customerPurchaseDTO.getSettleType().name());
					purchaseDTO.setAccountPayableBalance(customerPurchaseDTO.getAccountPayableBalance());
					if(StringUtils.isBlank(customerPurchaseDTO.getId())){
						String customerPurchaseId = IDGenerator.generateAssetCustomerPurchaseId();
						purchaseDTO.setId(customerPurchaseId);
					} else {
						purchaseDTO.setId(customerPurchaseDTO.getId());
					}
					customerPurchaseMapper.insertSelective(purchaseDTO);
			}
		}
		logger.info("保存客户采购信息");
	}
	
	
	//保存客户销售信息
	private void saveCustomerSale(String customerId, CustomerRequestDTO customerRequest){
		if(CollectionUtils.isNotEmpty(customerRequest.getCustomerSales())){
			for(CustomerSaleDTO customerSaleDTO : customerRequest.getCustomerSales()){
				CustomerSale saleDTO = new CustomerSale();
				saleDTO.setCustomerId(customerId);
				saleDTO.setSaleCustomerName(customerSaleDTO.getSaleCustomerName());
				saleDTO.setSaleLastYear(customerSaleDTO.getSaleLastYear());
				saleDTO.setPercent(customerSaleDTO.getPercent());
				saleDTO.setCooperationPeriod(customerSaleDTO.getCooperationPeriod());
				saleDTO.setAccountReceivableBalance(customerSaleDTO.getAccountReceivableBalance());
				if(StringUtils.isBlank(customerSaleDTO.getId())){
					String customerSaleId = IDGenerator.generateAssetCustomerSaleId();
					saleDTO.setId(customerSaleId);
				} else {
					saleDTO.setId(customerSaleDTO.getId());
				}
				customerSaleMapper.insertSelective(saleDTO);
			}
		}
		logger.info("保存客户销售信息");
	}
	
	/**
	 * @param CustomerId
	 * 更新之前删除原来数据库高级信息
	 * @param customerRequest
	 */
	private void deleteOldCustomerData(CustomerRequestDTO customerRequest) {
		//删除高级
		CustomerMagExample magExample = new CustomerMagExample();
		magExample.createCriteria().andCustomerIdEqualTo(customerRequest.getId());
		customerMagMapper.deleteByExample(magExample);
		//删除融资
		CustomerMasExample customerMasExample = new CustomerMasExample();
		customerMasExample.createCriteria().andCustomerIdEqualTo(customerRequest.getId());
		customerMasMapper.deleteByExample(customerMasExample);
		//删除股东
		CustomerStkExample customerStkExample = new CustomerStkExample();
		customerStkExample.createCriteria().andCustomerIdEqualTo(customerRequest.getId());
		customerStkMapper.deleteByExample(customerStkExample);
		//删除采购
		CustomerPurchaseExample purchaseExample = new CustomerPurchaseExample();
		purchaseExample.createCriteria().andCustomerIdEqualTo(customerRequest.getId());
		customerPurchaseMapper.deleteByExample(purchaseExample);
		//删除销售
		CustomerSaleExample saleExample = new CustomerSaleExample();
		saleExample.createCriteria().andCustomerIdEqualTo(customerRequest.getId());
		customerSaleMapper.deleteByExample(saleExample);
	}



	//校验客户名称是否存在
	@Override
	public boolean checkCustomernameExist(String customerName,String id) {
		CustomerExample customerExample = new CustomerExample();
		CustomerExample.Criteria criteria = customerExample.createCriteria();
		if(StringUtils.isNotBlank(id)){
			criteria.andIdNotEqualTo(id);
		}
		criteria.andCustomerNameEqualTo(customerName);
		List<Customer> customers = customerMapper.selectByExample(customerExample);
		if (CollectionUtils.isEmpty(customers))
			return false;
		return true;
	}
	
	
	
	
	
}
