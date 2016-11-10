package com.cana.asset.service.transaction.util;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;

import com.cana.vbam.common.asset.dto.SpecialProgramRequestDTO;
import com.cana.vbam.common.asset.enums.SpecialProgramStatus;
import com.cana.vbam.common.member.vo.UserVo;
import com.travelzen.framework.core.exception.WebException;

/**
 * 专项计划参数校验
 * @author jiangzhou.Ren
 * @time 2016年8月30日下午5:44:29
 */
public class AssetSpecialProgramPersistenceValidator {
	public static boolean checkSpecialProgramInfoFieldsIsValid (SpecialProgramRequestDTO request,UserVo userDetail){
		if (userDetail == null) {
			throw WebException.instance("userDetail为空");
		}
		if (StringUtils.isBlank(request.getSpecialProgramName())) {
			WebException.instance("专项计划名称不能为空");
		}
		if (StringUtils.isBlank(request.getUnderlyingAssetType())) {
			WebException.instance("基础资产类型不能为空");
		}
		if (StringUtils.isBlank(request.getEstimateEstablishmentDate())) {
			WebException.instance("预计成立日期不能为空");
		}
		if (!EnumUtils.isValidEnum(SpecialProgramStatus.class,request.getStatus().name())) {
			WebException.instance("状态不合法");
		}
		if (StringUtils.isBlank(request.getManagerName())) {
			WebException.instance("管理人名称不能为空");
		}
		if (CollectionUtils.isEmpty(request.getOriginators())) {
			WebException.instance("原始权益人不能为空");
		}
		if (CollectionUtils.isEmpty(request.getServiceAgencys())) {
			WebException.instance("资产服务机构不能为空");
		}
		
		return true;
	}
	
	//校验发行后专项计划成立请求参数不能为空
	public static boolean checkIssueSpecialProgramRequest (SpecialProgramRequestDTO request,UserVo userDetail){
		if (userDetail == null) {
			throw WebException.instance("userDetail为空");
		}
		if(StringUtils.isBlank(request.getEstablishmentDate())){
			throw WebException.instance("成立日期不能为空");
		}
		if(StringUtils.isBlank(request.getEstimateDueDate())){
			throw WebException.instance("预计到期日期不能为空");
		}
		if(StringUtils.isBlank(request.getStatutoryDueDate())){
			throw WebException.instance("法定到期日期不能为空");
		}
		return true;
		
	}
	
}
