package com.cana.vbam.front.biz.controller.asset;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cana.asset.api.IABSMarketResearchApi;
import com.cana.vbam.common.asset.dto.EchartsQuery;
import com.cana.vbam.common.asset.dto.EchartsResponseDTO;
import com.cana.vbam.common.asset.dto.MarketDataListDTO;
import com.cana.vbam.common.asset.dto.MarketDataQueryDTO;
import com.cana.vbam.common.asset.dto.MarketDataSummaryDTO;
import com.cana.vbam.common.asset.enums.SupervisionAgencyEnum;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.dto.ObjectResult;
import com.travelzen.framework.common.PageList;
import com.travelzen.framework.core.exception.WebException;

@Controller
@RequestMapping("/asset/marketResearch")
public class ABSMarketResearchController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private IABSMarketResearchApi absMarketResearchApi;
	
	//市场数据总览
	@RequestMapping(value="marketDataSummary", method=RequestMethod.GET)
	public String getMarketDataSummary(Model model){
		MarketDataSummaryDTO marketDataSummaryDTO = absMarketResearchApi.getMarketDataSummary();
		model.addAttribute("marketDataSummaryDTO", marketDataSummaryDTO);
		return "page/asset/marketresearch/summary";
	}
	
	
	@RequestMapping(value = "/summaryZoom", method = { RequestMethod.POST })
	@ResponseBody
	public ObjectResult<EchartsResponseDTO> summary(EchartsQuery queryDTO, Model model){
		 try {
			EchartsResponseDTO echartsResponseDTO = absMarketResearchApi.getMarketDataReport(queryDTO);
			 return ObjectResult.success("成功", echartsResponseDTO);
		} catch (WebException e) {
			logger.error("市场数据总览异常",e);
			return ObjectResult.fail(e.getMessage());
		}catch (Exception e) {
			logger.error("市场数据总览异常",e);
			return ObjectResult.fail("数据加载失败");
		}
	}
	
	//市场数据列表
	@RequestMapping(value="marketDataSearchList", method=RequestMethod.GET)
	public String marketDataSearchList(Model model){
		logger.info("市场数据列表页");
		model.addAttribute("SupervisionAgencyEnum", SupervisionAgencyEnum.values());
		model.addAttribute("UnderlyingAssetType", absMarketResearchApi.getUnderlyingAssetType());
		MarketDataSummaryDTO marketDataSummaryDTO = absMarketResearchApi.getMarketProduct();
		model.addAttribute("marketDataSummaryDTO", marketDataSummaryDTO);
		return "page/asset/marketresearch/list";
	}
	
	@RequestMapping(value="marketDataSearchList", method=RequestMethod.POST)
	@ResponseBody
	public ListResult<MarketDataListDTO> marketDataSearchList(MarketDataQueryDTO queryDTO, Model model){
		try {
			PageList<MarketDataListDTO> pageLists = absMarketResearchApi.getMarketDataSearchList(queryDTO);
			return ListResult.success(pageLists.getRecords(), pageLists.getTotalRecords());
		} catch (Exception e) {
			logger.error("获取市场数据列表页错误", e);
			return ListResult.fail(e.getMessage());
		}
	}
	
	//市场数据详情
	@RequestMapping(value="detail", method=RequestMethod.GET)
	public String marketDataDetail(@RequestParam String id, Model model){
		MarketDataListDTO marketDataListDTO = absMarketResearchApi.getMarketDataDetail(id);
		model.addAttribute("marketDataListDTO", marketDataListDTO);
		return "page/asset/marketresearch/detail";
	}

}
