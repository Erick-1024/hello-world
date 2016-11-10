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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cana.asset.api.IABSUnderlyingAssetApi;
import com.cana.member.authorization.common.SecurityContextUtils;
import com.cana.vbam.common.asset.underlyingasset.dto.UnderlyingAssetExcelDTO;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.dto.ObjectResult;
import com.cana.vbam.front.biz.utils.AssetExcelIEUtil;
import com.google.common.collect.Lists;
import com.travelzen.framework.core.exception.WebException;

@Controller
@RequestMapping("/asset/underlyingAsset")
public class ABSUnderlyingAssetImportController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private IABSUnderlyingAssetApi aBSUnderlyingAssetApi;

	@RequestMapping(value = "import", method = RequestMethod.GET)
	public String gotoImport(Model model) {
		model.addAttribute("rediskey", aBSUnderlyingAssetApi.generateUnderlyingAssetRedisKey());
		return "page/asset/underlyingAsset/underlyingAssetImport";
	}

	@RequestMapping(value = "upload", method = RequestMethod.POST)
	@ResponseBody
	public void upload(@RequestParam MultipartFile excel, @RequestParam String rediskey,
			HttpServletResponse httpServletResponse) throws IOException {
		String result = "true";
		if (excel != null) {
			try {
				List<UnderlyingAssetExcelDTO> list;
				String fileName = excel.getOriginalFilename();
				InputStream inputStream = excel.getInputStream();
				// 读取第一个sheet和 39个单元格
				list = analyzeLoanInfoExcel(inputStream, fileName, 39, 0);
				if (null == list) {
					logger.info("Excel数据为空！");
					result = "FAILED";
				}

				aBSUnderlyingAssetApi.importUnderlyingAssetExcel2Redis(list, SecurityContextUtils.getOperatorId(), rediskey);
			} catch (Exception e) {
				logger.error("读取Excel文件异常", e);
				result = "FAILED";
			}
		} else {
			logger.info("Excel文件为空！");
			result = "FAILED";
		}
		httpServletResponse.getWriter().write(result);
	}

	@RequestMapping(value = "query", method = RequestMethod.POST)
	@ResponseBody
	public ListResult<UnderlyingAssetExcelDTO> query(@RequestParam String rediskey,
			@RequestParam boolean passed, @RequestParam int page, @RequestParam int pageSize) {

		return aBSUnderlyingAssetApi.getUnderlyingAssetFromRedis(rediskey, SecurityContextUtils.getOperatorId(), passed, page, pageSize);

	}

	@RequestMapping(value = "import", method = RequestMethod.POST)
	@ResponseBody
	public ObjectResult<String> import2DB(@RequestParam String rediskey) {
		try {
			aBSUnderlyingAssetApi.importUnderlyingAssetExcel2DB(SecurityContextUtils.getOperatorId(), rediskey);
			return ObjectResult.success("基础资产导入成功");
		} catch (WebException e) {
			logger.error(e.getMessage(), e);
			return ObjectResult.fail(e.getMessage());
		}
	}

	private List<UnderlyingAssetExcelDTO> analyzeLoanInfoExcel(InputStream inputStream, String fileName,
			int columnTotalNum, int sheetNum) throws Exception {
		List<List<String>> listAll = AssetExcelIEUtil.readFromInputStream(inputStream, fileName, columnTotalNum,
				sheetNum, 1);
		if (null == listAll || listAll.size() <= 0) {
			logger.info("Excel文件的输入流为空！");
			return null;
		}
		List<UnderlyingAssetExcelDTO> list = Lists.newArrayList();
		for (List<String> eachList : listAll) {
			int i = 0;
			UnderlyingAssetExcelDTO excelDTO = new UnderlyingAssetExcelDTO();
			excelDTO.setUnderlyingAssetId(StringUtils.trim(eachList.get(i++)));
			excelDTO.setContractNo(StringUtils.trim(eachList.get(i++)));
			excelDTO.setCustomerName(StringUtils.trim(eachList.get(i++)));
			excelDTO.setCustomerEconomicCategory(StringUtils.trim(eachList.get(i++)));
			excelDTO.setCounterparty(StringUtils.trim(eachList.get(i++)));
			excelDTO.setCounterpartyEconomicCategory(StringUtils.trim(eachList.get(i++)));
			excelDTO.setCustomerCity(StringUtils.trim(eachList.get(i++)));
			excelDTO.setCustomerIndustry(StringUtils.trim(eachList.get(i++)));
			excelDTO.setCustomerRating(StringUtils.trim(eachList.get(i++)));
			excelDTO.setCounterpartyCity(StringUtils.trim(eachList.get(i++)));
			excelDTO.setCounterpartyIndustry(StringUtils.trim(eachList.get(i++)));
			excelDTO.setCounterpartyRating(StringUtils.trim(eachList.get(i++)));
			excelDTO.setGuaranteeInfo(StringUtils.trim(eachList.get(i++)));
			excelDTO.setGuaranteeType(StringUtils.trim(eachList.get(i++)));
			excelDTO.setGuaranteeCompanyInfo(StringUtils.trim(eachList.get(i++)));
			excelDTO.setGuaranteeCompanyType(StringUtils.trim(eachList.get(i++)));
			excelDTO.setGuaranteeGoodsNo(StringUtils.trim(eachList.get(i++)));
			excelDTO.setCreditLimit(StringUtils.trim(eachList.get(i++)));
			excelDTO.setCreditBalance(StringUtils.trim(eachList.get(i++)));
			excelDTO.setCounterpartyLimit(StringUtils.trim(eachList.get(i++)));
			excelDTO.setCounterpartyBalance(StringUtils.trim(eachList.get(i++)));
			excelDTO.setInvoiceAmount(StringUtils.trim(eachList.get(i++)));
			excelDTO.setInvoiceBalance(StringUtils.trim(eachList.get(i++)));
			excelDTO.setFinanceAmount(StringUtils.trim(eachList.get(i++)));
			excelDTO.setFinanceBalance(StringUtils.trim(eachList.get(i++)));
			excelDTO.setLoanDate(StringUtils.trim(eachList.get(i++)));
			excelDTO.setDueDate(StringUtils.trim(eachList.get(i++)));
			excelDTO.setRepaymentMethod(StringUtils.trim(eachList.get(i++)));
			excelDTO.setInterestRateUnitDesc(StringUtils.trim(eachList.get(i++)));
			excelDTO.setInterestRate(StringUtils.trim(eachList.get(i++)));
			excelDTO.setLoanPeriod(StringUtils.trim(eachList.get(i++)));
			excelDTO.setFiveLevelCategory(StringUtils.trim(eachList.get(i++)));
			excelDTO.setSettleStatusFlag(StringUtils.trim(eachList.get(i++)));
			excelDTO.setForwardFlag(StringUtils.trim(eachList.get(i++)));
			excelDTO.setForwardDaysStr(StringUtils.trim(eachList.get(i++)));
			excelDTO.setExtensionFlag(StringUtils.trim(eachList.get(i++)));
			excelDTO.setExtensionDaysStr(StringUtils.trim(eachList.get(i++)));
			excelDTO.setOverdueFlag(StringUtils.trim(eachList.get(i++)));
			excelDTO.setOverdueDaysStr(StringUtils.trim(eachList.get(i++)));

			if (!excelDTO.getInterestRate().endsWith("%"))
				excelDTO.setInterestRate(excelDTO.getInterestRate() + "%");
			list.add(excelDTO);
		}
		return list;
	}

}
