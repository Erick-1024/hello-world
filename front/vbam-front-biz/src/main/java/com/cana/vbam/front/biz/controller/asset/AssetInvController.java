package com.cana.vbam.front.biz.controller.asset;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cana.asset.api.IAssetInvApi;
import com.cana.member.authorization.common.SecurityContextUtils;
import com.cana.vbam.common.asset.dto.BusinessCounterpartyDTO;
import com.cana.vbam.common.asset.dto.InvoiceListDTO;
import com.cana.vbam.common.asset.dto.InvoiceQueryDTO;
import com.cana.vbam.common.asset.dto.InvoiceRedisDTO;
import com.cana.vbam.common.asset.enums.ActionMode;
import com.cana.vbam.common.asset.enums.BusinessProduct;
import com.cana.vbam.common.asset.enums.CreditCurrencyType;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.dto.ObjectResult;
import com.cana.vbam.common.dto.PageResult;
import com.cana.vbam.common.member.dto.user.UserSessionDTO;
import com.cana.vbam.front.biz.utils.AssetExcelIEUtil;
import com.travelzen.framework.common.PageList;
import com.travelzen.framework.core.exception.WebException;

/**
 * 应收账款管理controller
 * 
 * @author guguanggong
 *
 */
@Controller
@RequestMapping("/asset/invoice")
public class AssetInvController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private IAssetInvApi assetInvApi;

	@RequestMapping(value = "/invoiceList", method = { RequestMethod.GET })
	public String invoiceList(Model model) {
		logger.info("进入应收账款列表页面");
		model.addAttribute("CreditCurrencyType",CreditCurrencyType.values());
		model.addAttribute("BusinessProduct",BusinessProduct.values());
		return "page/asset/invoice/invoiceList";
	}
	
	/**
	 * 查询应收账款列表页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/invoiceList", method = { RequestMethod.POST })
	@ResponseBody
	public ListResult<InvoiceListDTO> searchList(InvoiceQueryDTO queryDTO, Model model) {
		try {
			UserSessionDTO userSessionDTO = SecurityContextUtils.getUserDTOFromSession();
			PageList<InvoiceListDTO> pageLists = assetInvApi.getInvoiceList(queryDTO, userSessionDTO);
			return ListResult.success(pageLists.getRecords(), pageLists.getTotalRecords());
		} catch (Exception e) {
			logger.error("获取应收账款列表错误", e);
			return ListResult.fail(e.getMessage());
		}
	}

	/**
	 * 返回应收账款excel导入页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/invImportList", method = { RequestMethod.GET })
	public String invImportList(Model model) {
		logger.info("进入应收账款excel导入页面");
		model.addAttribute("key", assetInvApi.generateRediskey());
		return "page/asset/invoice/invImportList";
	}

	
	/**
	 * 导入Excel的按钮
	 * @param excel
	 * @return
	 */
	@RequestMapping(value="importInvoiceInfoExcel",method=RequestMethod.POST)
	@ResponseBody
	public void importLoanInfoExcel(@RequestParam MultipartFile excel,@RequestParam String rediskey,HttpServletResponse httpServletResponse)throws IOException{
		httpServletResponse.setContentType("text/html");
		if(excel != null){
			try{
				List<InvoiceRedisDTO> list = new ArrayList<>();
				String fileName = excel.getOriginalFilename();
				InputStream inputStream = excel.getInputStream();
				//读取第一个sheet和 15个单元格
				list = analyzeExcel(inputStream, fileName,15,0);
				if (null == list){
					logger.info("Excel数据为空！");
					httpServletResponse.getWriter().write("{\"status\":\"FAILED\",\"message\":\"Excel数据为空！\"}");
					return;
				}
				//list 存入 redis 数据库
				assetInvApi.batchSaveToRedis(SecurityContextUtils.getCustomerId(),rediskey, list);
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
	 * 从导入的Excel的输入流中读取文件中的信息
	 * @param inputStream
	 * @param fileName
	 * @param columnTotalNum
	 * @param sheetNum
	 * @return
	 * @throws Exception 
	 */
	private List<InvoiceRedisDTO> analyzeExcel(InputStream inputStream, String fileName, int columnTotalNum, int sheetNum) throws Exception {
        List<InvoiceRedisDTO> list = new ArrayList<>();
        List<List<String>> listAll = AssetExcelIEUtil.readFromInputStream(inputStream, fileName, columnTotalNum, sheetNum,1);
		if(null == listAll || listAll.size()<=0)
		{
			logger.info("Excel文件的输入流为空！");
			return null;
		}
		for(List<String> eachList : listAll){
			int i = 0;
			InvoiceRedisDTO invDTO = new InvoiceRedisDTO();
			invDTO.setSequence(eachList.get(i++));
			invDTO.setInvoiceSequence(eachList.get(i++));
			invDTO.setBusinessContractNo(eachList.get(i++).trim());
			invDTO.setMemberName(eachList.get(i++).trim());
			invDTO.setBusinessProductDesc(eachList.get(i++).trim());
			invDTO.setCurrencyDesc(eachList.get(i++).trim());
			invDTO.setCounterparty(eachList.get(i++).trim());
			invDTO.setInvoiceNo(eachList.get(i++).trim());
			invDTO.setNominvoiceAmt(eachList.get(i++).trim());
			invDTO.setInvoiceAmt(eachList.get(i++).trim());
			invDTO.setFinancingRatio(eachList.get(i++).trim());
			invDTO.setInvoiceDate(eachList.get(i++).trim());
			invDTO.setDueDate(eachList.get(i++).trim()); 
			invDTO.setExpenseSubject(eachList.get(i++).trim());
			invDTO.setAmount(eachList.get(i++).trim());
			list.add(invDTO);
		}
		return list;
	}

	/**
	 * 应收账款信息  页面回显
	 * @param key
	 * @param status
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/query/{status}")
	@ResponseBody
	public ListResult<InvoiceListDTO> queryInvoiceInfoFromRedis(@RequestParam String key, @PathVariable String status, int page, int pageSize){
		PageList<InvoiceListDTO> pageLists = assetInvApi.queryInvoiceInfoFromRedis(key, SecurityContextUtils.getOperatorId(), status, page, pageSize);
		return ListResult.success(pageLists.getRecords(), pageLists.getTotalRecords());
	}
	
	/**
	 * 测试 用
	 * @param rediskey
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/gotoPassRedis", method = { RequestMethod.GET })
	public String gotoPassRedis(@RequestParam String rediskey,Model model) {
		logger.info("进入应收账款列表页面");
		model.addAttribute("key", rediskey);
		return "page/asset/invoice/testredisinvoiceList";
	}
	
	/**
	 * 测试 用
	 * @param rediskey
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/queryPassRedis" ,method = { RequestMethod.POST })
	@ResponseBody
	public ListResult<InvoiceRedisDTO> queryPassRedis(@RequestParam String rediskey,int page, int pageSize){
		PageList<InvoiceRedisDTO> pageLists = assetInvApi.queryPassRedis(rediskey,page,pageSize);
		return ListResult.success(pageLists.getRecords(), pageLists.getTotalRecords());
	}
	

	/**
	 * 应收账款导入（mysql）
	 */
	@RequestMapping(value = "/importExcelInvList", method = RequestMethod.POST)
	@ResponseBody
	public ObjectResult<String> importExcelInvList(@RequestParam String redisKey) {
		try {
			logger.info("导入已筛选后的应收账款");
			String customerId = SecurityContextUtils.getCustomerId();
			String operatorId = SecurityContextUtils.getOperatorId();
			assetInvApi.importExcelInvList(redisKey, customerId, operatorId);
			return ObjectResult.success("提交成功");
		} catch (WebException e) {
			logger.error("导入已筛选后的应收账款", e);
			return ObjectResult.fail(e.getMessage());
		}catch (Exception e) {
			logger.error("导入已筛选后的应收账款", e);
			return ObjectResult.fail("提交失败");
		}
	}


	//新建  invoiceCreate
	@RequestMapping(value = "/invoiceCreate", method = { RequestMethod.GET })
	public String invoiceCreate(Model model) {
		logger.info("应收账款新增");
		return "page/asset/invoice/invoiceCreate";
	}
	
	//查询  invoiceDetail
	@RequestMapping(value = "/invoiceDetail", method = { RequestMethod.GET })
	public String invoiceDetail(@RequestParam String id,Model model) {
		logger.info("应收账款查询");
		UserSessionDTO userSessionDTO = SecurityContextUtils.getUserDTOFromSession();
		InvoiceListDTO listDTO = assetInvApi.getInvoiceManage(id,userSessionDTO);
		model.addAttribute("invListDTO", listDTO);
		return "page/asset/invoice/invoiceDetails";
	}
	
	//修改  invoiceUpdate
	@RequestMapping(value = "/invoiceUpdate", method = { RequestMethod.GET })
	public String invoiceUpdate(@RequestParam String id,Model model) {
		logger.info("应收账款修改");
		UserSessionDTO userSessionDTO = SecurityContextUtils.getUserDTOFromSession();
		InvoiceListDTO listDTO = assetInvApi.getInvoiceManage(id,userSessionDTO);
		model.addAttribute("invListDTO", listDTO);
		return "page/asset/invoice/invoiceUpdate";
	}
	//删除  invoiceDelete
	@RequestMapping(value = "/invoiceDelete/{id}", method = { RequestMethod.GET })
	@ResponseBody
	public ObjectResult<String> invoiceDelete(@PathVariable("id") String id) {
		try {
			logger.info("应收账款管理删除");
			UserSessionDTO userSessionDTO = SecurityContextUtils.getUserDTOFromSession();
			assetInvApi.delBusManage(id,userSessionDTO);
			return ObjectResult.success("删除成功");
		} catch (Exception e) {
			logger.error("应收账款管理删除", e);
			return ObjectResult.fail(e.getMessage());
		}
	}
	
	/**
	 * 根据业务合同号查询业务合同信息
	 * @param queryDTO
	 * @return
	 */
	@RequestMapping(value = "/getBusinessInfo", method = RequestMethod.POST)
	@ResponseBody
	public ObjectResult<InvoiceListDTO> getBusinessInfo(InvoiceQueryDTO queryDTO) {
		try {
			String customerId = SecurityContextUtils.getCustomerId();
			InvoiceListDTO invoiceListDTO = assetInvApi.getBusinessInfo(queryDTO,customerId);
			return ObjectResult.success("成功", invoiceListDTO);
		} catch (WebException e) {
			logger.error("查询业务合同信息", e);
			return ObjectResult.fail(e.getMessage());
		}catch (Exception e) {
			logger.error("查询业务合同信息", e);
			return ObjectResult.fail("未知异常");
		}
	}
	
	/**
	 * 查询业务合同中交易对手信息(业务合同号、交易对手名称)
	 * @param queryDTO
	 * @return
	 */
	@RequestMapping(value = "/queryBusinessCounterpartyDTO", method = RequestMethod.POST)
	@ResponseBody
	public PageResult<BusinessCounterpartyDTO> queryBusinessCounterpartyDTO(InvoiceQueryDTO queryDTO) {
		PageResult<BusinessCounterpartyDTO> infoDTOs = assetInvApi.queryBusinessCounterpartyDTO(queryDTO);
		return infoDTOs;
	}
	
	
	/**
	 * 修改应收账款
	 * 
	 * @param applyQueryDTO
	 * @return
	 */
	@RequestMapping(value = "/updateInvManage", method = RequestMethod.POST)
	@ResponseBody
	public ObjectResult<String> updateInvManage(@RequestBody InvoiceListDTO invoiceListDTO) {
		UserSessionDTO user = SecurityContextUtils.getUserDTOFromSession();
		try {
		    invoiceListDTO.setCurrency(CreditCurrencyType.RMB.name());
			ActionMode actionMode = ActionMode.valueOf(invoiceListDTO.getActionMode());
			switch (actionMode) {
				case UPDATE: // 列表修改
					logger.info("应收账款管理页面");
					assetInvApi.updateInvManage(invoiceListDTO,user);
					break;
				case ADD: // 列表新增
					logger.info("应收账款管理页面");
					assetInvApi.createInvManage(invoiceListDTO,user);
					break;
				default:
					break;
			}
			return ObjectResult.success("应收账款"+actionMode.desc()+"成功");
		} catch (WebException e) {
			logger.error("修改应收账款", e);
			return ObjectResult.fail(e.getMessage());
		}catch (Exception e) {
			logger.error("修改应收账款", e);
			return ObjectResult.fail("提交失败");
		}
	}

	/**
	 * 删除业务合同（有应收账款）
	 * 
	 * @param applyQueryDTO
	 * @return
	 *//*
	@RequestMapping(value = "/delInvList", method = RequestMethod.POST)
	@ResponseBody
	public ObjectResult<String> delBusManage(@RequestBody InvoiceListDTO invoiceListDTO) {
		try {
			ActionMode actionMode = ActionMode.valueOf(invoiceListDTO.getActionMode());
			switch (actionMode) {
				case REDIS_DEL: // excel应收账款导入删除
					logger.info("excel应收账款导入删除");
					// 根据应收账款基本信息ID删除
					assetInvApi.delExcelInvDelToRedis(invoiceListDTO);
					break;
				case DEL: // 列表删除
					logger.info("应收账款管理删除");
					assetInvApi.delBusManage(invoiceListDTO.getId());
					break;
				default:
					break;
			}
			return ObjectResult.success("成功");
		} catch (Exception e) {
			logger.error("参数校验失败", e);
			return ObjectResult.fail(e.getMessage());
		}
	}*/
}
