package com.cana.vbam.front.biz.controller.early.warning;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cana.early.warning.api.IEarlyWarningApi;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.early.warning.dto.EarlyWarningLevelChangeHistoryDTO;
import com.cana.vbam.common.early.warning.dto.EarlyWarningLevelChangeHistoryRequest;
import com.cana.vbam.common.early.warning.enums.EarlywarningLevel;
import com.cana.vbam.common.utils.Constants;

@Controller
@RequestMapping("/earlywarning/levelchange")
public class EarlyWarningLevelChangeHistoryController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private IEarlyWarningApi earlyWarningApiImpl;
	
	@RequestMapping(value = "/list", method = { RequestMethod.GET })
	public String gotoEarlyWarningLevelChangeHistoryList(Model model) {
		logger.info("进入预警流水列表页面");
		model.addAttribute("earlywarningLevels", EarlywarningLevel.values());
		return "page/earlywarning/changehistory/list";
	}
	
	@RequestMapping(value = "/query/earlyWarningLevelChangeHistory", method = { RequestMethod.POST })
	@ResponseBody
	public ListResult<EarlyWarningLevelChangeHistoryDTO> queryEarlyWarningLevelChangeHistory(EarlyWarningLevelChangeHistoryRequest earlyWarningLevelChangeHistoryRequest) {
		try {
			earlyWarningLevelChangeHistoryRequest.setProductId(Constants.TRAVELZEN_FINANCE_PRODUCT_ID);
			return earlyWarningApiImpl.queryEarlyWarningLevelChangeHistory(earlyWarningLevelChangeHistoryRequest);
		} catch (Exception e) {
			logger.error("获取预流水列表错误", e);
			return ListResult.fail(e.getMessage());
		}
	}
	
}
