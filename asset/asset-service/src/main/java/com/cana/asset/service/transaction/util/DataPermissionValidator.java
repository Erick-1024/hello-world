package com.cana.asset.service.transaction.util;

import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.cana.asset.service.transaction.IAssetUserPrivilegeTransactionService;
import com.cana.vbam.common.asset.dto.UserPrivilegeDTO;
import com.cana.vbam.common.member.enums.user.UserType;
import com.cana.vbam.common.member.vo.UserVo;
import com.travelzen.framework.core.common.ReturnCode;
import com.travelzen.framework.core.exception.WebException;

/**
 * @author hu
 *
 */
@Component
public class DataPermissionValidator {

	@Resource
	private IAssetUserPrivilegeTransactionService userPrivilegeTransactionService;
	
	public UserPrivilegeDTO checkDataPermissions(UserVo userVo, String customerId){
		UserPrivilegeDTO userPrivilege = new UserPrivilegeDTO();
		UserType userType = userVo.getCustomer().getUserType();
		switch (userType) {
//		case CANA:
//			break;
		case FACTOR:
			userPrivilege.setFactorId(userVo.getCustomerId());
			break;
		case FINACE:
			if(StringUtils.isBlank(customerId)){
				Set<String> customerIds = userPrivilegeTransactionService.allowedCustomerIdList(userVo.getCustomerId());
				userPrivilege.setCustomerIds(customerIds);
			}else{
				if(!userPrivilegeTransactionService.allow(userVo.getCustomerId(), customerId))
					throw WebException.instance(ReturnCode.NO_PERMISSION);
			}
			break;
		default:
			throw WebException.instance(ReturnCode.NO_PERMISSION);
		}
		return userPrivilege;
	}
	
	/**
	 * 根据当前操作用户获取有权查看客户列表
	 * @param userVo 当前操作的用户信息
	 * @return
	 */
	public UserPrivilegeDTO checkDataPermissions(UserVo userVo){
		UserPrivilegeDTO userPrivilege = new UserPrivilegeDTO();
		UserType userType = userVo.getCustomer().getUserType();
		switch (userType) {
			case FACTOR:
				userPrivilege.setFactorId(userVo.getCustomerId());
				break;
			case FINACE:
				userPrivilege.setCustomerIds(userPrivilegeTransactionService.allowedCustomerIdList(userVo.getCustomerId()));
				break;
			default:
				throw WebException.instance(ReturnCode.NO_PERMISSION);
		}
		return userPrivilege;
	}
	
	/**
	 * 判断当前操作的用户是否对某条记录具有数据权限
	 * @param userVo 当前操作用户信息
	 * @param customerId 记录的客户ID
	 * @param factorId 记录的保理商ID
	 */
	public void checkDataPermissions(UserVo userVo, String customerId, String factorId) {
		UserType userType = userVo.getCustomer().getUserType();
		switch (userType) {
			case FACTOR:
				if(!userVo.getCustomerId().equals(factorId))
					throw WebException.instance(ReturnCode.NO_PERMISSION);
				break;
			case FINACE:
				if(!userPrivilegeTransactionService.allow(userVo.getCustomerId(), customerId))
					throw WebException.instance(ReturnCode.NO_PERMISSION);
				break;
			default:
				throw WebException.instance(ReturnCode.NO_PERMISSION);
		}
	}
	
	/**
	 * 根据用户类型 FACTOR 返回保理商ID
	 *            FINACE 返回融资客户可查看的数据的customerId
	 * customerId 不为空 判断此masterId是否有查看customerId 的权限
	 * @param userType
	 * @param masterId
	 * @param customerId
	 * @return
	 */
	public UserPrivilegeDTO checkDataPermissionsByUsertype(UserType userType,String masterId, String customerId){
		UserPrivilegeDTO userPrivilege = new UserPrivilegeDTO();
		switch (userType) {
//		case CANA:
//			break;
		case FACTOR:
			userPrivilege.setFactorId(masterId);
			break;
		case FINACE:
			if(StringUtils.isBlank(customerId)){
				Set<String> customerIds = userPrivilegeTransactionService.allowedCustomerIdList(masterId);
				userPrivilege.setCustomerIds(customerIds);
			}else{
				if(!userPrivilegeTransactionService.allow(masterId, customerId))
					throw WebException.instance(ReturnCode.NO_PERMISSION);
			}
			break;
		default:
			throw WebException.instance(ReturnCode.NO_PERMISSION);
		}
		return userPrivilege;
	}
	
}
