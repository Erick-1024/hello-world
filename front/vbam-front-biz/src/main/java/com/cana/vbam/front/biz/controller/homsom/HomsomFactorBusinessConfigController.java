package com.cana.vbam.front.biz.controller.homsom;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cana.asset.api.IHomsomApi;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.dto.ObjectResult;
import com.cana.vbam.common.homsom.dto.CounterpartyConfigDTO;
import com.cana.vbam.common.homsom.dto.CounterpartyConfigRequestDTO;
import com.cana.vbam.common.homsom.enums.Channel;
import com.cana.vbam.common.repayment.enums.RepaymentType;
import com.travelzen.framework.core.exception.WebException;

@Controller
@RequestMapping("homsom/config")
public class HomsomFactorBusinessConfigController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private IHomsomApi homsomApiImpl;
	
	@RequestMapping(value = "/goto/list/{channel}", method = { RequestMethod.GET })
	public String gotolist(Model model) {
		logger.info("进入保理业务配置页面");
		model.addAttribute("repaymentTypes", RepaymentType.factorBusinessInterestType());
		return "page/homsom/config/list";
	}

	@RequestMapping(value = "/get/list/{channel}", method = { RequestMethod.POST })
	@ResponseBody
	public ListResult<?> getList(CounterpartyConfigRequestDTO requestDTO, @PathVariable Channel channel) {
		try {
			requestDTO.setChannel(channel);
			return homsomApiImpl.getCounterpartyConfig(requestDTO);
		}  catch (WebException e) {
			logger.error(e.getMessage(), e);
			return ListResult.fail(e.getMessage());
		} catch(Exception e){
			logger.error("未知异常", e);
			return ListResult.fail("未知异常");
		}
	}

	@RequestMapping(value = "/get/counterpartyConfig/{channel}", method = { RequestMethod.POST })
	@ResponseBody
	public ObjectResult<?> getCounterpartyConfig(CounterpartyConfigDTO queryDTO, @PathVariable Channel channel) {
		try {
			queryDTO.setChannel(channel);
			return homsomApiImpl.getCounterpartyConfigDTO(queryDTO);
		}  catch (WebException e) {
			logger.error(e.getMessage(), e);
			return ObjectResult.fail(e.getMessage());
		} catch(Exception e){
			logger.error("未知异常", e);
			return ObjectResult.fail("未知异常");
		}
	}

	@RequestMapping(value = "/save/counterpartyConfig/{channel}", method = { RequestMethod.POST })
	@ResponseBody
	public ObjectResult<?> saveCounterpartyConfig(CounterpartyConfigDTO counterpartyConfigDTO, @PathVariable Channel channel) {
		try {
			counterpartyConfigDTO.setChannel(channel);
			return homsomApiImpl.modifyCounterpartyConfig(counterpartyConfigDTO);
		}  catch (WebException e) {
			logger.error(e.getMessage(), e);
			return ObjectResult.fail(e.getMessage());
		} catch(Exception e){
			logger.error("未知异常", e);
			return ObjectResult.fail("未知异常");
		}
	}
	
}
