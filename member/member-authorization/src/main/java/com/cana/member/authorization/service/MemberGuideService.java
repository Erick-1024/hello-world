package com.cana.member.authorization.service;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.cana.member.api.IUserApi;
import com.cana.member.authorization.common.SecurityContextUtils;
import com.cana.vbam.common.member.dto.user.CustomerDetailDTO;
import com.cana.vbam.common.member.dto.user.UserSessionDTO;
import com.cana.vbam.common.member.enums.user.UserGuideStatus;
import com.cana.vbam.common.member.enums.user.UserType;

@Component("memberGuideService")
public class MemberGuideService {

	private static final Logger LGR = LoggerFactory.getLogger(MemberGuideService.class);
	
	@Resource(name = "userApiImpl")
	private IUserApi userService;

	/**
	 * 判断当前客户是否需要进入引导页面
	 */
	public boolean needRedirect2GuidePage(){
	    UserSessionDTO sessionDTO = SecurityContextUtils.getUserFromSession().getUserData();

	    boolean needGuide = isFactorNeedToGoGuide(sessionDTO) || isFinaceNeedToGoGuide(sessionDTO);
        return needGuide;
	}

	private boolean isFactorNeedToGoGuide(UserSessionDTO sessionDTO) {
	    boolean needGuideBySession =
	            StringUtils.isBlank(sessionDTO.getMasterId())
                && UserType.FACTOR.equals(sessionDTO.getUserType())
                && (sessionDTO.getGuideStatus() == null || !sessionDTO.getGuideStatus().contains(UserGuideStatus.COMFIRMED_RULE));

	    if (needGuideBySession) {
	        CustomerDetailDTO customer = userService.queryCustomerDetail(sessionDTO.getId());
            if (customer.getGuideStatus() == null || !customer.getGuideStatus().contains(UserGuideStatus.COMFIRMED_RULE)) {
                return true;
            }
	    }
	    return false;
	}

	private boolean isFinaceNeedToGoGuide(UserSessionDTO sessionDTO) {
	    boolean needGuideBySession =
	            StringUtils.isBlank(sessionDTO.getMasterId())
                && UserType.FINACE.equals(sessionDTO.getUserType())
                && sessionDTO.getGuideStatus() != null 
                && (sessionDTO.getGuideStatus().contains(UserGuideStatus.NEED_GENERATE_CONTRACT)
                || sessionDTO.getGuideStatus().contains(UserGuideStatus.NEED_GENERATE_CONTRACT_YUNDAEX));

	    if (needGuideBySession) {
            CustomerDetailDTO customer = userService.queryCustomerDetail(sessionDTO.getId());
            if (customer.getGuideStatus() != null && (customer.getGuideStatus().contains(UserGuideStatus.NEED_GENERATE_CONTRACT)
            		|| customer.getGuideStatus().contains(UserGuideStatus.NEED_GENERATE_CONTRACT_YUNDAEX))) {
                return true;
            }
        }
        return false;
	}
	
}
