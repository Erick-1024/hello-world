package com.cana.vbam.front.biz.controller.yundaex;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
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
import com.cana.report.api.IReportApi;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.dto.ObjectResult;
import com.cana.vbam.common.report.dto.MonitorMetricAndDataQueryYundaDTO;
import com.cana.vbam.common.report.dto.MonitorMetricDataYundaDTO;
import com.cana.vbam.common.report.dto.MonitorSummaryQueryYundaDTO;
import com.cana.vbam.common.report.dto.MonitorSummaryYundaDTO;
import com.cana.vbam.common.utils.Constants;
import com.cana.vbam.front.biz.utils.AssetExcelIEUtil;
import com.cana.yundaex.api.IYundaexMonitorApi;
import com.cana.yundaex.common.dto.monitor.YundaexMonitorImportDTO;
import com.travelzen.framework.common.PageList;
import com.travelzen.framework.core.exception.WebException;

/**
 * 韵达监控controller
 * @author jiangzhou.Ren
 * @time 2016年9月27日上午9:32:20
 */
@Controller
@RequestMapping(value = "/yundaex/monitor")
public class YdMonitorController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private IYundaexMonitorApi yundaexMonitorApi;
	
	@Resource
	private IReportApi reportApiImpl;
	
	/**
	 * 韵达监控列表页面
	 * @return
	 */
	@RequestMapping(value = "/list", method = { RequestMethod.GET })
	public String monitorListPage() {
		return "page/yundaex/monitor/list";
	}
	/**
	 * 韵达监控列表页数据
	 * @param monitorSummaryQueryDTO
	 * @return
	 */
	@RequestMapping(value = "/search/list", method = { RequestMethod.POST })
	@ResponseBody
	public ListResult<MonitorSummaryYundaDTO> getMonitorList(MonitorSummaryQueryYundaDTO monitorSummaryQueryDTO) {
		logger.info("查询韵达客户的监控列表信息");
		try {
			monitorSummaryQueryDTO.setProductId(Constants.YUNDAEX_FINANCE_PRODUCT_ID);
			return reportApiImpl.queryYundaexMonitorSummary(monitorSummaryQueryDTO);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("监控列表信息获取失败", e);
			return ListResult.fail("查询韵达监控列表信息获取失败");
		}
	}
	
	
	/**
	 * 韵达监控详情
	 * @param memberId
	 * @param customerName
	 * @param usedLimit
	 * @param model
	 * @throws UnsupportedEncodingException 
	 * @return
	 */
	@RequestMapping(value = "/detail", method = { RequestMethod.GET })
	public String monitorDetail(@RequestParam String memberId,@RequestParam String customerName,@RequestParam String usedLimit,@RequestParam String shortLoan,@RequestParam String bailBalance, Model model) throws UnsupportedEncodingException {
		logger.info("进入韵达监控跳转详情页面");
		model.addAttribute("memberId",memberId);
		model.addAttribute("customerName",customerName);
		model.addAttribute("usedLimit",usedLimit);
		if("null".equals(shortLoan)){
			shortLoan = "0";
		}
		model.addAttribute("shortLoan",shortLoan);
		if("null".equals(bailBalance)){
			bailBalance = "0";
		}
		model.addAttribute("bailBalance",bailBalance);
		return "page/yundaex/monitor/detail";	
	}
	
	/**
	 * 韵达监控详情
	 * @param monitorMetricDataQueryDTO
	 * @return
	 */
	@RequestMapping(value = "/detail/metric", method = { RequestMethod.POST })
	@ResponseBody
	public ListResult<MonitorMetricDataYundaDTO> getMonitorMetricDetail(MonitorMetricAndDataQueryYundaDTO monitorMetricDataQueryDTO) {
		try {
			logger.info("进入韵达监控详情页面");
			monitorMetricDataQueryDTO.setProductId(Constants.YUNDAEX_FINANCE_PRODUCT_ID);
			 List<MonitorMetricDataYundaDTO> monitorMetricDataDTOs = reportApiImpl.queryYundaexMonitorMetricDataDTO(monitorMetricDataQueryDTO);
			return ListResult.success(monitorMetricDataDTOs, monitorMetricDataDTOs.size());
		} catch (Exception e) {
			logger.error("韵达监控详情信息获取失败", e);
			return ListResult.fail(e.getMessage());
		}
	}
	
	/**
	 * 监控信息导入
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/monitorImport", method = { RequestMethod.GET })
	public String monitorImport(Model model) {
		logger.info("进入韵达监控excel导入页面");
		model.addAttribute("key", yundaexMonitorApi.generateRediskey());
		return "page/yundaex/monitor/monitorImport";
	}
	
	/**
	 * 导入Excel的按钮
	 * @param excel
	 * @return
	 */
	@RequestMapping(value="importMonitorInfoExcel",method=RequestMethod.POST)
	@ResponseBody
	public void importLoanInfoExcel(@RequestParam MultipartFile excel,@RequestParam String rediskey,HttpServletResponse httpServletResponse)throws IOException{
		httpServletResponse.setContentType("text/html");
		if(excel != null){
			try{
				List<YundaexMonitorImportDTO> list = new ArrayList<>();
				String fileName = excel.getOriginalFilename();
				InputStream inputStream = excel.getInputStream();
				//读取第一个sheet和 29个单元格
				list = analyzeExcel(inputStream, fileName, 29, 0);
				if (null == list){
					logger.info("Excel数据为空！");
					httpServletResponse.getWriter().write("{\"status\":\"FAILED\",\"message\":\"Excel数据为空！\"}");
					return;
				}
				//list 存入 redis 数据库
				yundaexMonitorApi.batchSaveToRedis(SecurityContextUtils.getCustomerId(),rediskey, list);
				httpServletResponse.getWriter().write("{\"status\":\"SUCCESS\",\"message\":\""+fileName+"\"}");
			} catch (Exception e) {
				logger.error("读取Excel文件异常",e);
				httpServletResponse.getWriter().write("{\"status\":\"FAILED\",\"message\":\"Excel文件异常！\"}");
			}
		}
		else {
			logger.info("Excel文件为空！");
			httpServletResponse.getWriter().write("{\"status\":\"FAILED\",\"message\":\"Excel数据为空！\"}");
		}
	}
	
	/**
	 * 查询redis中的监控数据
	 * @param key
	 * @param status
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/query/{status}")
	@ResponseBody
	public ListResult<YundaexMonitorImportDTO> queryMonitorInfoFromRedis(@RequestParam String key, @PathVariable String status, int page, int pageSize){
		PageList<YundaexMonitorImportDTO> pageLists = yundaexMonitorApi.queryMonitorInfoFromRedis(key, SecurityContextUtils.getCustomerId(), status, page, pageSize);
		return ListResult.success(pageLists.getRecords(), pageLists.getTotalRecords());
	}
	
	
	/**
	 * 从导入的Excel的输入流中读取文件中的信息
	 * @param inputStream
	 * @param fileName
	 * @param columnTotalNum
	 * @param sheetNum
	 * @return
	 * @throws Exception 
	 */
	private List<YundaexMonitorImportDTO> analyzeExcel(InputStream inputStream, String fileName, int columnTotalNum, int sheetNum) throws Exception {
        List<YundaexMonitorImportDTO> list = new ArrayList<>();
        List<List<String>> listAll = AssetExcelIEUtil.readFromInputStream(inputStream, fileName, columnTotalNum, sheetNum,3);
		if(null == listAll || listAll.size()<=0)
		{
			logger.info("Excel文件的输入流为空！");
			return null;
		}
		for(List<String> eachList : listAll){
			int i = 0;
			YundaexMonitorImportDTO monitorDTO = new YundaexMonitorImportDTO();
			monitorDTO.setSequecnce(eachList.get(i++));
			monitorDTO.setStationNo(StringUtils.trim(eachList.get(i++)));
			monitorDTO.setStationName(StringUtils.trim(eachList.get(i++)));
			monitorDTO.setStationMgr(StringUtils.trim(eachList.get(i++)));
			monitorDTO.setAddress(StringUtils.trim(eachList.get(i++)));
			monitorDTO.setBusiLimit(StringUtils.trim(eachList.get(i++)));
			monitorDTO.setBailBalance(StringUtils.trim(eachList.get(i++)));
			i = i + 6;
			monitorDTO.setYundaexJudge(StringUtils.trim(eachList.get(i++)));
			monitorDTO.setStationAmount(StringUtils.trim(eachList.get(i++)));
			i = i + 10;
			monitorDTO.setShortLoan(StringUtils.trim(eachList.get(i++)));
			monitorDTO.setLoanLimit(StringUtils.trim(eachList.get(i++)));
			monitorDTO.setLimitUnit(StringUtils.trim(eachList.get(i++)));
			monitorDTO.setLoanType(StringUtils.trim(eachList.get(i++)));
			list.add(monitorDTO);
		}
		return list;
	}
	
	/**
	 * 
	 */
	@RequestMapping(value = "/importExcelToDB", method = RequestMethod.POST)
	@ResponseBody
	public ObjectResult<String> importExcelToDB(@RequestParam String redisKey) {
		try {
			logger.info("导入已筛选后的韵达监控信息");
			String customerId = SecurityContextUtils.getCustomerId();
//			String operatorId = SecurityContextUtils.getOperatorId();
			yundaexMonitorApi.importExcelToDB(redisKey, customerId);
			return ObjectResult.success("提交成功");
		} catch (WebException e) {
			logger.error("导入已筛选后的韵达监控信息", e);
			return ObjectResult.fail(e.getMessage());
		}catch (Exception e) {
			logger.error("导入已筛选后的韵达监控信息", e);
			return ObjectResult.fail("提交失败");
		}
	}
}
