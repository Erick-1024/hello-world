package com.cana.vbam.front.biz.controller.yundaex;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.dto.ObjectResult;
import com.cana.vbam.common.dto.PageResult;
import com.cana.yundaex.api.IYundaexContractSituationApi;
import com.cana.yundaex.common.dto.contract.ContractSituationQueryCriteria;
import com.cana.yundaex.common.dto.contract.ContractSituationResultDTO;
import com.esotericsoftware.minlog.Log;
import com.travelzen.framework.core.exception.WebException;

/**
 * @author hu
 *
 */
@Controller
@RequestMapping(value = "/yundaex/contract")
public class YdContractController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private IYundaexContractSituationApi contractSituationApi;
	
	@RequestMapping(value = "/gotoSituationListPage", method = RequestMethod.GET)
	public String gotoContractSituationList(){
		return "/page/yundaex/contract/contractSituation";
	}
	
	@RequestMapping(value = "/queryList", method = RequestMethod.POST)
	@ResponseBody
	public PageResult<ContractSituationResultDTO> queryContractSituationList(ContractSituationQueryCriteria queryCriteria){
		
		return contractSituationApi.findContractSituationByCondition(queryCriteria);
	}
	
	@RequestMapping(value = "/resendContract", method = RequestMethod.POST)
	@ResponseBody
	public ListResult<String> resendContract(String id){
		try{
			return contractSituationApi.resendContract(id);
			
		}catch(WebException e){
			Log.error(e.getMessage(), e);
			return ListResult.fail("重发合同失败");
		}
	}
}
