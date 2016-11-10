package com.cana.vbam.front.biz.controller.yundaex;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cana.member.authorization.common.SecurityContextUtils;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.dto.ObjectResult;
import com.cana.vbam.front.biz.utils.AssetExcelIEUtil;
import com.cana.yundaex.api.IYundaexLineImportApi;
import com.cana.yundaex.common.dto.YundaexLineImportDTO;
import com.travelzen.framework.common.PageList;
import com.travelzen.framework.core.exception.WebException;

@Controller
@RequestMapping(value = "test")
public class TestCustApplyExcelController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private IYundaexLineImportApi yundaexLineImportApi;

	@RequestMapping(value = "/custApplyExcel")
	public String customerApply(Model model) {
		logger.info("进入线下数据导入页面");
		model.addAttribute("key", yundaexLineImportApi.generateRediskey());
		return "page/yundaex/lineImport/testCustApplyExcel";
	}

	/**
	 * 导入Excel的按钮
	 * 
	 * @param excel
	 * @return
	 */
	@RequestMapping(value = "/excel", method = RequestMethod.POST)
	@ResponseBody
	public ObjectResult<String> importLoanInfoExcel(@RequestParam MultipartFile excel, @RequestParam String rediskey,
			HttpServletResponse httpServletResponse) throws IOException {
		if (excel != null) {
			try {
				List<YundaexLineImportDTO> list = new ArrayList<>();
				String fileName = excel.getOriginalFilename();
				InputStream inputStream = excel.getInputStream();
				list = analyzeExcel(inputStream, fileName, 16, 0);
				if (null == list) {
					logger.info("Excel数据为空！");
					return ObjectResult.fail("error");
				}
				yundaexLineImportApi.batchSaveToRedis(SecurityContextUtils.getCustomerId(), rediskey, list);
				return ObjectResult.success("success");
			} catch (Exception e) {
				logger.error("读取Excel文件异常", e);
				return ObjectResult.fail("error");
			}
		} else {
			logger.info("Excel文件为空！");
			return ObjectResult.fail("error");
		}
	}

	/**
	 * 查询redis数据
	 * 
	 * @param rediskey
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/queryLineRedis/{status}", method = { RequestMethod.POST })
	@ResponseBody
	public ListResult<YundaexLineImportDTO> queryLineRedis(@RequestParam String rediskey,@PathVariable String status, int page, int pageSize) {
		PageList<YundaexLineImportDTO> pageLists = yundaexLineImportApi.queryLineRedis(rediskey,status, page, pageSize);
		return ListResult.success(pageLists.getRecords(), pageLists.getTotalRecords());
	}

	/**
	 * 将redis数据导入mysql
	 */
	@RequestMapping(value = "/importExcelList", method = RequestMethod.POST)
	@ResponseBody
	public ObjectResult<String> importExcelInvList(@RequestParam String redisKey) {
		try {
			logger.info("导入线下数据");
			String customerId = SecurityContextUtils.getCustomerId();
			String operatorId = SecurityContextUtils.getOperatorId();
			yundaexLineImportApi.importExcelList(redisKey, customerId, operatorId);
			return ObjectResult.success("成功");
		} catch (WebException e) {
			logger.error("导入线下数据", e);
			return ObjectResult.fail(e.getMessage());
		} catch (Exception e) {
			logger.error("导入线下数据", e);
			return ObjectResult.fail("提交失败");
		}
	}

	/**
	 * 从导入的Excel的输入流中读取文件中的信息
	 * 
	 * @param inputStream
	 * @param fileName
	 * @param columnTotalNum
	 * @param sheetNum
	 * @return
	 * @throws Exception
	 */
	private List<YundaexLineImportDTO> analyzeExcel(InputStream inputStream, String fileName, int columnTotalNum,
			int sheetNum) throws Exception {
		List<YundaexLineImportDTO> list = new ArrayList<>();
		List<List<String>> listAll = AssetExcelIEUtil.readFromInputStream(inputStream, fileName, columnTotalNum,
				sheetNum, 1);
		if (null == listAll || listAll.size() <= 0) {
			logger.info("Excel文件的输入流为空！");
			return null;
		}
		for (List<String> eachList : listAll) {
			int i = 1;
			YundaexLineImportDTO dto = new YundaexLineImportDTO();
			dto.setStationNo(eachList.get(i++));
			dto.setStationName(eachList.get(i++));
			dto.setStationMgr(eachList.get(i++));
			dto.setProvince(eachList.get(i++));
			dto.setCity(eachList.get(i++));
			dto.setAddress(eachList.get(i++));
			dto.setBusiLimit(Long.valueOf(eachList.get(i++)));
			dto.setBailBalance(eachList.get(i++));
			dto.setYundaexJudge(eachList.get(i++));
			dto.setPayAccountName(eachList.get(i++));
			dto.setPayAccount(eachList.get(i++));
			dto.setPayAccountAddress(eachList.get(i++));
			dto.setCustIdno(eachList.get(i++));
			dto.setCustName(eachList.get(i++));
			dto.setCustPhone(eachList.get(i++));
			list.add(dto);
		}
		return list;
	}

}
