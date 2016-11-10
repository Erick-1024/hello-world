package com.cana.vbam.front.biz.controller.setting;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cana.member.authorization.common.SecurityContextUtils;
import com.cana.setting.api.ISettingApi;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.dto.ObjectResult;
import com.cana.vbam.common.setting.dto.CanaCalendarDTO;
import com.cana.vbam.common.setting.dto.CanaCalendarRequest;
import com.cana.vbam.common.utils.FrontExceptionHandler;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.util.ExcelIEUtil;
import com.travelzen.framework.util.ExcelUtils;

@Controller
@RequestMapping("/setting/calendar")
public class CanaCalendarController {

	@Resource
	private ISettingApi settingApiImpl;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String gotoList() {
		return "page/setting/calendar/list";
	}
	
	@RequestMapping(value = "/get/list", method = { RequestMethod.POST })
	@ResponseBody
	public ListResult<?> getList(CanaCalendarRequest canaCalendarRequest) {
		try {
			return settingApiImpl.getList(canaCalendarRequest);
		} catch (Exception e) {
			return FrontExceptionHandler.handleListResultException(e);
		}
	}
	
	@RequestMapping(value = "/modify", method = { RequestMethod.POST })
	@ResponseBody
	public ObjectResult<?> modify(CanaCalendarDTO canaCalendarDTO) {
		try {
			settingApiImpl.modify(canaCalendarDTO, SecurityContextUtils.getOperatorId());
			return ObjectResult.success("修改成功");
		} catch (Exception e) {
			return FrontExceptionHandler.handleObjectResultException(e);
		}
	}
	
	@RequestMapping(value = "import", method = RequestMethod.POST)
	public void importExcel(MultipartFile excel, HttpServletResponse httpServletResponse) throws IOException {
		try {
			List<List<String>> result = ExcelIEUtil.readFromInputStream(excel.getInputStream(), excel.getOriginalFilename(), 6, 0, 1);
			settingApiImpl.saveCalendar(result, SecurityContextUtils.getOperatorId());
			httpServletResponse.getWriter().write("{\"status\":\"SUCCESS\",\"message\":\"节假日导入成功！\"}");
		} catch (Exception e) {
			logger.error("excel导入错误", e);
			httpServletResponse.getWriter().write("{\"status\":\"FAILED\",\"message\":\"节假日导入失败！\"}");
		}
	}
	
	@RequestMapping(value = "export", method = RequestMethod.GET)
	public void exportExcel(HttpServletRequest request, HttpServletResponse response, CanaCalendarRequest canaCalendarRequest) throws UnsupportedEncodingException {
		String excelTitle = "公共假期";
		String fileName = excelTitle+".xls";
		if (request.getHeader("User-Agent").toUpperCase().matches(".*((MSIE)|(TRIDENT)|(EDGE)).*"))
			fileName = URLEncoder.encode(fileName, "UTF-8");// IE浏览器
		else
			fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
		response.reset();
		response.setContentType("application/vnd.ms-excel; charset=utf-8");
		response.addHeader("Content-Disposition", String.format(" attachment; filename=\"%s\"", fileName));
		response.setCharacterEncoding("utf-8");
		try {
			OutputStream out = response.getOutputStream();
			String[] excelHeaders = {"年", "月", "日", "周几", "是否假期", "假期名称"};
			ExcelUtils.exportExcel(excelTitle, excelHeaders, settingApiImpl.getExcelList(canaCalendarRequest), out, false);
		} catch (IOException e) {
			logger.error("生成公共假期文件失败", e);
			throw WebException.instance("生成公共假期文件失败");
		}
	}
	
}
