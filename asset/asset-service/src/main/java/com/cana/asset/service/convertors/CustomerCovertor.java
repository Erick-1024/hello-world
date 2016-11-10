package com.cana.asset.service.convertors;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.cana.asset.dao.po.Customer;
import com.cana.asset.dao.po.CustomerMag;
import com.cana.asset.dao.po.CustomerMas;
import com.cana.asset.dao.po.CustomerPurchase;
import com.cana.asset.dao.po.CustomerSale;
import com.cana.asset.dao.po.CustomerStk;
import com.cana.vbam.common.asset.dto.CustomerDTO;
import com.cana.vbam.common.asset.dto.CustomerListResponseDTO;
import com.cana.vbam.common.asset.dto.CustomerMagDTO;
import com.cana.vbam.common.asset.dto.CustomerMasDTO;
import com.cana.vbam.common.asset.dto.CustomerPurchaseDTO;
import com.cana.vbam.common.asset.dto.CustomerSaleDTO;
import com.cana.vbam.common.asset.dto.CustomerStkDTO;
import com.cana.vbam.common.asset.enums.CustomerCityTypeEnum;
import com.cana.vbam.common.asset.enums.CustomerManEducationEnum;
import com.cana.vbam.common.asset.enums.CustomerSettleTypeEnum;
import com.cana.vbam.common.asset.enums.CustomerStkPayamtTypeEnum;
import com.cana.vbam.common.asset.enums.CustomerStkTypeEnum;
import com.cana.vbam.common.asset.enums.CustomerTypeEnum;
import com.cana.vbam.common.asset.enums.EconomicCategoryEnum;
import com.cana.vbam.common.asset.enums.IndustryTypeEnum;
import com.cana.vbam.common.customer.enums.CustomerMaterialSubmitState;

/**
 * @author jiangzhou.Ren
 * @time 2016年7月22日下午2:36:18
 */
@Component
public class CustomerCovertor {
	/**
	 * 客户列表
	 * @param customers
	 * @return
	 */
	public static List<CustomerListResponseDTO> convertCustomertDao2ResDTO(List<Customer> customers) {
		List<CustomerListResponseDTO> customerListDTOs = new ArrayList<CustomerListResponseDTO>();
		for (Customer customer : customers) {
			CustomerListResponseDTO response = new CustomerListResponseDTO();
			response.setId(customer.getId());
			response.setCustomerName(customer.getCustomerName());
			if (StringUtils.isNotBlank(customer.getCustomerType())) {
				response.setCustomerType(CustomerTypeEnum.valueOf(customer.getCustomerType()));
			}
			if (StringUtils.isNotBlank(customer.getIndustry())) {
				response.setIndustry(IndustryTypeEnum.valueOf(customer.getIndustry()));
			}
			response.setCityDesc(CustomerCityTypeEnum.valueOf(customer.getCity ()).desc());
			response.setCustomerTypeDesc(CustomerTypeEnum.valueOf(customer.getCustomerType()).desc());
			response.setIndustryDesc(IndustryTypeEnum.valueOf(customer.getIndustry()).desc());
			response.setEnterpriseMaterialState(CustomerMaterialSubmitState.valueOf(customer.getEnterpriseMaterialState()).desc());
			response.setUpdateTime(customer.getUpdateTime());
			customerListDTOs.add(response);
		}
		return customerListDTOs;
	}

	/**
	 * 客户详情查询
	 * @param customer
	 * @param customerMags
	 * @param customerMass
	 * @param customerStks
	 * @param customerPurchases
	 * @param customerSales
	 * @return
	 */
	public static CustomerDTO convertorToCustomerDTO(Customer customer, List<CustomerMag> customerMags,
			List<CustomerMas> customerMass, List<CustomerStk> customerStks, List<CustomerPurchase> customerPurchases,
			List<CustomerSale> customerSales) {
		// 客户基本信息
		CustomerDTO customerDTO = toCustomerDTO(customer);
		ArrayList<CustomerMagDTO> customerMagDTO = toCustomerMagDTO(customerMags);
		ArrayList<CustomerMasDTO> customerMasDTO = toCustomerMasDTO(customerMass);
		ArrayList<CustomerStkDTO> customerStkDTO = toCustomerStkDTO(customerStks);
		ArrayList<CustomerPurchaseDTO> customerPurchaseDTO = toCustomerPurchaseDTO(customerPurchases);
		ArrayList<CustomerSaleDTO> customerSaleDTO = toCustomerSaleDTO(customerSales);
		checkIsEmpty(customerDTO, customerMagDTO, customerMasDTO, customerStkDTO, customerPurchaseDTO, customerSaleDTO);
		
		return customerDTO;
	}

	/**
	 * 检查list是否为空
	 * @param customerDTO
	 * @param customerMagDTO
	 * @param customerMasDTO
	 * @param customerStkDTO
	 * @param customerPurchaseDTO
	 * @param customerSaleDTO
	 */
	private static void checkIsEmpty(CustomerDTO customerDTO, ArrayList<CustomerMagDTO> customerMagDTO,
			ArrayList<CustomerMasDTO> customerMasDTO, ArrayList<CustomerStkDTO> customerStkDTO,
			ArrayList<CustomerPurchaseDTO> customerPurchaseDTO, ArrayList<CustomerSaleDTO> customerSaleDTO) {
		if (null != customerMagDTO) {
			customerDTO.setCustomerMags(customerMagDTO);
		}
		if (null != customerStkDTO) {
			customerDTO.setCustomerStks(customerStkDTO);
		}
		if (null != customerPurchaseDTO) {
			customerDTO.setCustomerPurchases(customerPurchaseDTO);
		}
		if (null != customerSaleDTO) {
			customerDTO.setCustomerSales(customerSaleDTO);
		}
		if (null != customerMasDTO) {
			customerDTO.setCustomerMass(customerMasDTO);
		}
	}

	/**
	 * 客户基本信息
	 * @param customer
	 * @return
	 */
	private static CustomerDTO toCustomerDTO(Customer customer) {
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setId(customer.getId());
		customerDTO.setFactorId(customer.getFactorId());
		customerDTO.setCustomerName(customer.getCustomerName());
		customerDTO.setCustomerType(CustomerTypeEnum.valueOf(customer.getCustomerType()));
		customerDTO.setContactName(customer.getContactName());
		customerDTO.setMobileNo(customer.getMobileNo());
		customerDTO.setMail(customer.getMail());
		customerDTO.setCompanyAddress(customer.getCompanyAddress());
		customerDTO.setEconomicCategory(EconomicCategoryEnum.valueOf(customer.getEconomicCategory()));
		customerDTO.setIndustry(IndustryTypeEnum.valueOf(customer.getIndustry()));
		customerDTO.setCity(CustomerCityTypeEnum.valueOf(customer.getCity()));
		customerDTO.setBusinessLicenceCode(customer.getBusinessLicenceCode());
		customerDTO.setBusinessLicenceCodeExpiryDate(customer.getBusinessLicenceCodeExpiryDate());
		customerDTO.setTaxRegistrationCertificateCode(customer.getTaxRegistrationCertificateCode());
		customerDTO.setTaxRegistrationCertificateCodeExpiryDate(customer.getTaxRegistrationCertificateCodeExpiryDate());
		customerDTO.setOrganizationCode(customer.getOrganizationCode());
		customerDTO.setOrganizationCodeExpiryDate(customer.getOrganizationCodeExpiryDate());
		//法人代表
		customerDTO.setLegalRepresentative(customer.getLegalRepresentative());
		//注册资本
		customerDTO.setRegisteredCapital(customer.getRegisteredCapital());
		customerDTO.setEnterpriseMaterialState(CustomerMaterialSubmitState.valueOf(customer.getEnterpriseMaterialState()));
		return customerDTO;
	}

	/**
	 * 销售
	 * @param customerSales
	 * @return
	 */
	private static ArrayList<CustomerSaleDTO> toCustomerSaleDTO(List<CustomerSale> customerSales) {
		ArrayList<CustomerSaleDTO> customerSaleDTO = new ArrayList<CustomerSaleDTO>();
		for (CustomerSale customerSale : customerSales) {
			CustomerSaleDTO saleDTO = new CustomerSaleDTO();
			saleDTO.setId(customerSale.getId());
			saleDTO.setCustomerId(customerSale.getCustomerId());
			saleDTO.setSaleCustomerName(customerSale.getSaleCustomerName());
			saleDTO.setSaleLastYear(customerSale.getSaleLastYear());
			saleDTO.setPercent(customerSale.getPercent());
			saleDTO.setCooperationPeriod(customerSale.getCooperationPeriod());
			saleDTO.setAccountReceivableBalance(customerSale.getAccountReceivableBalance());
			customerSaleDTO.add(saleDTO);
		}
		return customerSaleDTO;
	}

	/**
	 * 采购
	 * @param customerPurchases
	 * @return
	 */
	private static ArrayList<CustomerPurchaseDTO> toCustomerPurchaseDTO(List<CustomerPurchase> customerPurchases) {
		ArrayList<CustomerPurchaseDTO> customerPurchaseDTO = new ArrayList<CustomerPurchaseDTO>();
		for (CustomerPurchase customerPurchase : customerPurchases) {
			CustomerPurchaseDTO purchaseDTO = new CustomerPurchaseDTO();
			purchaseDTO.setId(customerPurchase.getId());
			purchaseDTO.setCustomerId(customerPurchase.getCustomerId());
			purchaseDTO.setSupplierName(customerPurchase.getSupplierName());
			purchaseDTO.setPurchaseLastYear(customerPurchase.getPurchaseLastYear());
			purchaseDTO.setPercent(customerPurchase.getPercent());
			purchaseDTO.setSettleType(CustomerSettleTypeEnum.valueOf(customerPurchase.getSettleType()));
			purchaseDTO.setAccountPayableBalance(customerPurchase.getAccountPayableBalance());
			customerPurchaseDTO.add(purchaseDTO);
		}
		return customerPurchaseDTO;
	}

	/**
	 * 股东
	 * @param customerStks
	 * @return
	 */
	private static ArrayList<CustomerStkDTO> toCustomerStkDTO(List<CustomerStk> customerStks) {
		ArrayList<CustomerStkDTO> customerStkDTO = new ArrayList<CustomerStkDTO>();
		for (CustomerStk customerStk : customerStks) {
			CustomerStkDTO stkDTO = new CustomerStkDTO();
			stkDTO.setId(customerStk.getId());
			stkDTO.setCustomerId(customerStk.getCustomerId());
			stkDTO.setStkType(CustomerStkTypeEnum.valueOf(customerStk.getStkType()));
			stkDTO.setStkName(customerStk.getStkName());
			stkDTO.setStkPayamt(customerStk.getStkPayamt());
			stkDTO.setStkPcnt(customerStk.getStkPcnt());
			stkDTO.setStkPayamtType(CustomerStkPayamtTypeEnum.valueOf(customerStk.getStkPayamtType()));
			stkDTO.setStkStatus(customerStk.getStkStatus());
			customerStkDTO.add(stkDTO);
		}
		return customerStkDTO;
	}

	/**
	 * 融资
	 * @param customerMass
	 * @return
	 */
	private static ArrayList<CustomerMasDTO> toCustomerMasDTO(List<CustomerMas> customerMass) {
		ArrayList<CustomerMasDTO> customerMasDTO = new ArrayList<CustomerMasDTO>();
		for (CustomerMas customerMas : customerMass) {
			CustomerMasDTO masDTO = new CustomerMasDTO();
			masDTO.setId(customerMas.getId());
			masDTO.setCustomerId(customerMas.getCustomerId());
			masDTO.setFinancialInstitutionName(customerMas.getFinancialInstitutionName());
			masDTO.setAmount(customerMas.getAmount());
			masDTO.setStartDate(customerMas.getStartDate());
			masDTO.setEndDate(customerMas.getEndDate());
			masDTO.setGuaranteeType(customerMas.getGuaranteeType());
			masDTO.setRemark(customerMas.getRemark());
			customerMasDTO.add(masDTO);
		}
		return customerMasDTO;
	}

	/**
	 * 高管
	 * @param customerMags
	 * @return
	 */
	private static ArrayList<CustomerMagDTO> toCustomerMagDTO(List<CustomerMag> customerMags) {
		ArrayList<CustomerMagDTO> customerMagDTO = new ArrayList<CustomerMagDTO>();
		for (CustomerMag customerMag : customerMags) {
			CustomerMagDTO magDTO = new CustomerMagDTO();
			magDTO.setId(customerMag.getId());
			magDTO.setCustomerId(customerMag.getCustomerId());;
			magDTO.setMagName(customerMag.getMagName());
			magDTO.setMagDutyType(customerMag.getMagDutyType());
			magDTO.setMagEducation(CustomerManEducationEnum.valueOf(customerMag.getMagEducation()));
			magDTO.setExperience(customerMag.getExperience());
			magDTO.setMagIdentityCardNo(customerMag.getMagIdentityCardNo());
			magDTO.setMagProfession(customerMag.getMagProfession());
			magDTO.setMagSex(customerMag.getMagSex());
			customerMagDTO.add(magDTO);
		}
		return customerMagDTO;
	}
}
