package com.cana.asset.service.transaction.util;

import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;

import com.cana.vbam.common.asset.dto.CustomerRequestDTO;
import com.cana.vbam.common.asset.enums.CustomerCityTypeEnum;
import com.cana.vbam.common.asset.enums.CustomerTypeEnum;
import com.cana.vbam.common.asset.enums.EconomicCategoryEnum;
import com.cana.vbam.common.asset.enums.IndustryTypeEnum;
import com.cana.vbam.common.member.vo.UserVo;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.util.ValidationUtil;

/**
 * 客户新增，修改信息校验
 * @author jiangzhou.Ren
 * @time 2016年7月26日下午5:44:21
 */
public class AssetCustomerPersistenceValidator {
	
	public static boolean checkCustomerInfoFieldsIsValid(UserVo userDetail,CustomerRequestDTO customerRequest){
		if (userDetail == null) {
			throw WebException.instance("userDetail为空");
		}
		if (StringUtils.isBlank(customerRequest.getCustomerName())) {
			throw WebException.instance("客户名称不能为空");
		}
		if (!EnumUtils.isValidEnum(CustomerTypeEnum.class, customerRequest.getCustomerType().name())) {
			throw WebException.instance("客户类型不合法");
		}
		if (StringUtils.isBlank(customerRequest.getContactName())) {
			throw WebException.instance("联系人不能为空");
		}
		if (!ValidationUtil.isValidatePattern(ValidationUtil.MOBILENO_PATTERN, customerRequest.getMobileNo())){ 
			throw WebException.instance("电话号码不合法");
		}
		if (!ValidationUtil.isValidatePattern(ValidationUtil.EMAIL_PATTERN, customerRequest.getMail())){
			throw WebException.instance("电子邮箱不合法");
		}
		if (StringUtils.isBlank(customerRequest.getCompanyAddress())) {
			throw WebException.instance("公司地址不能为空");
		}
		if (!EnumUtils.isValidEnum(EconomicCategoryEnum.class, customerRequest.getEconomicCategory().name())) {
			throw WebException.instance("经济类型不合法");
		}
		if (!EnumUtils.isValidEnum(IndustryTypeEnum.class, customerRequest.getIndustry().name())) {
			throw WebException.instance("所属行业状态不合法");
		}
		if (!EnumUtils.isValidEnum(CustomerCityTypeEnum.class, customerRequest.getCity().name())) {
			throw WebException.instance("所属地区不合法");
		}
		if (StringUtils.isBlank(customerRequest.getBusinessLicenceCode())) {
			throw WebException.instance("营业执照号不能为空");
		}
		if (StringUtils.isBlank(customerRequest.getTaxRegistrationCertificateCode())) {
			throw WebException.instance("税务登记证号码不能为空");
		}
		if (StringUtils.isBlank(customerRequest.getOrganizationCode())) {
			throw WebException.instance("组织机构代码不能为空");
		}
		if (StringUtils.isBlank(customerRequest.getLegalRepresentative())) {
			throw WebException.instance("法定代表人不能为空");
		}
		if (customerRequest.getRegisteredCapital() == null) {
			throw WebException.instance("注册资本不能为空");
		}
		return true;
	}
}
