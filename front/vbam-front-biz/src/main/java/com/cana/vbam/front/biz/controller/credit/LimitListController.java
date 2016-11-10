package com.cana.vbam.front.biz.controller.credit;

import java.util.Arrays;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cana.credit.api.ICreditApi;
import com.cana.member.authorization.common.SecurityContextUtils;
import com.cana.vbam.common.credit.dto.limit.CustomerLimitListQueryDTO;
import com.cana.vbam.common.credit.dto.limit.CustomerLimitListResponseDTO;
import com.cana.vbam.common.credit.enums.ApplyApplicantType;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.dto.ObjectResult;
import com.cana.vbam.common.enums.AjaxResponseStatus;
import com.cana.vbam.common.member.dto.user.UserSessionDTO;
import com.cana.vbam.common.member.enums.user.UserType;
import com.travelzen.framework.common.PageList;
import com.travelzen.framework.core.exception.WebException;

@Controller
@RequestMapping("/credit/limit")
public class LimitListController {
    
    @Resource
    ICreditApi creditApi;
     
    private Logger logger=LoggerFactory.getLogger(getClass());
    
    @RequestMapping(value="/customerList",method=RequestMethod.GET)
    public String showCustomerLimitList(Model model){
    	model.addAttribute("applicantTypes", Arrays.asList(ApplyApplicantType.values()));
        return "page/credit/limit/customerList";
    }
    
    @RequestMapping(value="/customerList",method=RequestMethod.POST)
    @ResponseBody
    public ListResult<CustomerLimitListResponseDTO> searchCusList(CustomerLimitListQueryDTO queryDTO){
        try {
        	UserSessionDTO userSessionDTO = SecurityContextUtils.getUserDTOFromSession();
        	if(userSessionDTO.getUserType()==UserType.FINACE){
        		String masterId = StringUtils.isBlank(userSessionDTO.getMasterId()) ? userSessionDTO.getId()
        				: userSessionDTO.getMasterId();
        		queryDTO.setMemberId(masterId);
        	}
            PageList<CustomerLimitListResponseDTO> response=creditApi.getCustomerLimitList(queryDTO);
            return ListResult.success(response.getRecords(), response.getTotalRecords());
        } catch (Exception e) {
            logger.info("获取额度列表异常:"+e);
            return ListResult.fail("获取列表异常");
        }
    }
    @RequestMapping(value="/active" ,method=RequestMethod.POST)
    @ResponseBody
    public ObjectResult<Boolean> activeCreditLimit(String limitId){
        ObjectResult<Boolean> result=new ObjectResult<Boolean>();
        try{
           creditApi.activateCreditLimitByLimitId(limitId);
           result.setData(true);
           result.setStatus(AjaxResponseStatus.SUCCESS);
        }catch(WebException e){
            logger.error("额度激活失败", e);
            result.setStatus(AjaxResponseStatus.FAILED);
            result.setMessage(e.getMessage());
        }catch(Exception e){
            logger.error("额度激活失败", e);
            result.setStatus(AjaxResponseStatus.FAILED);
            result.setMessage("额度激活失败");
        }
        return result;
    }

}
