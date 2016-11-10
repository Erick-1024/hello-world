package com.cana.vbam.front.biz.controller.asset;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cana.asset.api.IABSMarketResearchApi;
import com.cana.member.authorization.common.SecurityContextUtils;
import com.cana.vbam.common.asset.dto.MarketDataProductExcelDTO;
import com.cana.vbam.common.asset.dto.MarketDataProjectExcelDTO;
import com.cana.vbam.common.dto.ObjectResult;
import com.cana.vbam.front.biz.utils.AssetExcelIEUtil;
import com.google.common.collect.Lists;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.util.CollectionUtil;

@Controller
@RequestMapping(value="/asset/marketData")
public class ABSMarketDataController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private IABSMarketResearchApi absMarketResearchApi;
	
	@RequestMapping(value = "gotoImport", method = RequestMethod.GET)
	public String gotoLoanInfoImportPage(Model model){
		model.addAttribute("rediskey", absMarketResearchApi.generateMarketDataRedisKey());
		return "page/asset/marketresearch/import";
	}
	
	@RequestMapping(value = "importProjectList", method=RequestMethod.POST)
	@ResponseBody
	public ObjectResult<String> importLoanInfoExcel2DB(@RequestParam String rediskey){
		try{
			
			absMarketResearchApi.importMarketDataExcel2DB(SecurityContextUtils.getOperatorId(), rediskey);
			return ObjectResult.success("市场研究数据导入成功");
		}catch(WebException e){
			logger.error(e.getMessage(),e);
			return ObjectResult.fail(e.getMessage());
		}
	}
	
	/**
	 * @param excel
	 * @return
	 */
	@RequestMapping(value="importProjectExcel", method=RequestMethod.POST)
	@ResponseBody
	public void importLoanInfoExcel(@RequestParam MultipartFile excel,@RequestParam String rediskey, HttpServletResponse httpServletResponse)throws IOException{
		String result = "true";		
		if(excel != null){
			try{
				String fileName = excel.getOriginalFilename();
				InputStream inputStream = excel.getInputStream();
				
				List<MarketDataProjectExcelDTO> projectList = Lists.newArrayList();
				List<MarketDataProductExcelDTO> productList = Lists.newArrayList();
				analyzeMarketDataExcel(inputStream, fileName, projectList, productList);
				
				if (CollectionUtils.isEmpty(projectList) || CollectionUtils.isEmpty(productList)){
					logger.info("Excel数据为空！");
					result = "FAILED";
				}
				//list 存入 redis 数据库
				int num = absMarketResearchApi.importMarketDataExcel2Redis(projectList, productList, SecurityContextUtils.getOperatorId(), rediskey);
				result = "" + num;
			} catch (Exception e) {
				logger.error("读取Excel文件异常",e);
				result = "FAILED";
			}
		}
		else {
			logger.info("Excel文件为空！");
			result = "FAILED";
		}
		httpServletResponse.getWriter().write(result);
	}
	
	private void analyzeMarketDataExcel(InputStream inputStream, String fileName, List<MarketDataProjectExcelDTO> projectlist,
			List<MarketDataProductExcelDTO> productlist) throws Exception {
		
        List<List<List<String>>> list = AssetExcelIEUtil.readFromInputStreamMulSheet(inputStream, fileName, 
        		Lists.newArrayList(11, 10), Lists.newArrayList(0, 1), Lists.newArrayList(1, 1));
        if(null == list || list.size()<=0 || null == list.get(0) || list.get(0).size() <= 0 || null == list.get(1) || list.get(1).size() <= 0)
		{
			logger.info("Excel文件的输入流为空！");
		}
		for(List<String> eachList : list.get(0)){
			int i = 1;
			MarketDataProjectExcelDTO invDTO = new MarketDataProjectExcelDTO();
			invDTO.setProjectName(StringUtils.trim(eachList.get(i++)));
			invDTO.setValueDate(StringUtils.trim(eachList.get(i++)));
			invDTO.setOriginator(StringUtils.trim(eachList.get(i++)));
			invDTO.setIssueTotalAmount(StringUtils.trim(eachList.get(i++)));
			invDTO.setSupervisionAgency(StringUtils.trim(eachList.get(i++)));
			invDTO.setUnderlyingAssetType(StringUtils.trim(eachList.get(i++)));
			invDTO.setIssuer(StringUtils.trim(eachList.get(i++)));
			invDTO.setIssueMonth(StringUtils.trim(eachList.get(i++)));
			invDTO.setAaaAverageInterestRate(StringUtils.trim(eachList.get(i++)));
			invDTO.setPriorityAverageInterestRate(StringUtils.trim(eachList.get(i++)));
			projectlist.add(invDTO);
		}
		for(List<String> eachList : list.get(1)){
			int i = 0;
			MarketDataProductExcelDTO invDTO = new MarketDataProductExcelDTO();
			invDTO.setValueDate(StringUtils.trim(eachList.get(i++)));
			invDTO.setProjectName(StringUtils.trim(eachList.get(i++)));
			invDTO.setUnderlyingAssetType(StringUtils.trim(eachList.get(i++)));
			invDTO.setProductName(StringUtils.trim(eachList.get(i++)));
			invDTO.setIssueAmount(StringUtils.trim(eachList.get(i++)));
			invDTO.setDebtRating(StringUtils.trim(eachList.get(i++)));
			invDTO.setInterestRate(StringUtils.trim(eachList.get(i++)));
			invDTO.setRatingAgency(StringUtils.trim(eachList.get(i++)));
			invDTO.setAaaAverageInterestRate(StringUtils.trim(eachList.get(i++)));
			invDTO.setPriorityAverageInterestRate(StringUtils.trim(eachList.get(i++)));
			productlist.add(invDTO);
		}
	}
}
