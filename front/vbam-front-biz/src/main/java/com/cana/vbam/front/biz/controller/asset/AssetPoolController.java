package com.cana.vbam.front.biz.controller.asset;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cana.asset.api.IAssetPoolApi;
import com.cana.member.authorization.common.SecurityContextUtils;
import com.cana.vbam.common.asset.dto.AssetInPoolDTO;
import com.cana.vbam.common.asset.dto.AssetPacketDTO;
import com.cana.vbam.common.asset.dto.AssetpoolListDTO;
import com.cana.vbam.common.asset.dto.SpecialProgramQueryDTO;
import com.cana.vbam.common.asset.enums.BasicAssetType;
import com.cana.vbam.common.asset.enums.BusinessProduct;
import com.cana.vbam.common.asset.enums.SpecialProgramStatus;
import com.cana.vbam.common.asset.loan.dto.LoanPaidDTO;
import com.cana.vbam.common.asset.loan.dto.LoanPlanDTO;
import com.cana.vbam.common.asset.underlyingasset.dto.EnterAssetPoolRequest;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.dto.ObjectResult;
import com.travelzen.framework.common.PageList;
import com.travelzen.framework.core.exception.WebException;


/**
 * 资产池
 * @author 
 *
 */
@Controller
@RequestMapping("/asset/pool")
public class AssetPoolController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private IAssetPoolApi assetPoolApi;
	
	/**
	 * 资产池列表
	 */
	@RequestMapping(value = "/assetpoolList", method = { RequestMethod.GET })
	public String assetPoolList(Model model){
		logger.info("进入资产池列表");
		model.addAttribute("SpecialProgramStatus",SpecialProgramStatus.values());
		model.addAttribute("UnderlyingAssetType",BasicAssetType.values());
		return "page/asset/assetpool/assetPoolList";
	}
	
	@RequestMapping(value = "/assetpoolList", method = { RequestMethod.POST })
	@ResponseBody
	public ListResult<AssetpoolListDTO> searchList(SpecialProgramQueryDTO queryDTO, Model model) {
		try {
			String userId = SecurityContextUtils.getUserDTOFromSession().getId();
			PageList<AssetpoolListDTO> pageLists = assetPoolApi.getAssetpoolList(queryDTO, userId);
			return ListResult.success(pageLists.getRecords(), pageLists.getTotalRecords());
		} catch (Exception e) {
			logger.error("获取资产池列表错误", e);
			return ListResult.fail(e.getMessage());
		}
	}
	
	/**
	 * 资产池管理
	 * @param id 专项计划ID
	 * @param status 状态 封包、成立..
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/assetpoolManage", method = { RequestMethod.GET })
	public String assetpoolManage(@RequestParam String id,@RequestParam String status, Model model){
		logger.info("进入资产池管理页面");
		String userId = SecurityContextUtils.getUserDTOFromSession().getId();
		AssetpoolListDTO assetpoolListDTO = assetPoolApi.getAssetPoolDetails(id, status, userId);
		model.addAttribute("assetpoolListDTO", assetpoolListDTO);
		return "page/asset/assetpool/manage";
	}
	
	/**
	 * 管理页面列表
	 * @param queryDTO
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/assetpoolManageList", method = { RequestMethod.POST })
	@ResponseBody
	public ListResult<AssetInPoolDTO> assetpoolManageList(SpecialProgramQueryDTO queryDTO, Model model) {
		logger.info("查询资产池管理已入池列表");
		String userId = SecurityContextUtils.getUserDTOFromSession().getId();
		PageList<AssetInPoolDTO> pageLists = assetPoolApi.getAssetpoolManageList(queryDTO, userId);
		return ListResult.success(pageLists.getRecords(), pageLists.getTotalRecords());
	}
	
	/**
	 * 管理页面 赎回
	 * @param underlyingAssetId
	 * @return
	 */
	@RequestMapping(value = "/redeemAssetPool", method = { RequestMethod.GET })
	@ResponseBody
	public ObjectResult<String> redeemAssetPool(@RequestParam String underlyingAssetId) {
		try {
			logger.info("资产池管理页面赎回");
			String userId = SecurityContextUtils.getUserDTOFromSession().getId();
			assetPoolApi.redeemAssetPool(userId, underlyingAssetId);
			return ObjectResult.success("赎回成功");
		} catch (WebException e) {
			logger.error("资产池赎回异常", e);
			return ObjectResult.fail(e.getMessage());
		} catch (Exception e) {
			logger.error("资产池赎回异常", e);
			return ObjectResult.fail("系统异常");
		}
	}
	
	/**
	 * 管理页面 待入池
	 * @param underlyingAssetId
	 */
	@RequestMapping(value = "/outAssetPoolAndKeepBind", method = { RequestMethod.GET })
	@ResponseBody
	public ObjectResult<String> outAssetPoolAndKeepBind(@RequestParam String underlyingAssetId) {
		try {
			logger.info("资产池管理页面待入池");
			String userId = SecurityContextUtils.getUserDTOFromSession().getId();
			assetPoolApi.outAssetPoolAndKeepBind(userId, underlyingAssetId);
			return ObjectResult.success("待入池成功");
		} catch (WebException e) {
			logger.error("资产池待入池异常", e);
			return ObjectResult.fail(e.getMessage());
		} catch (Exception e) {
			logger.error("资产池待入池异常", e);
			return ObjectResult.fail("系统异常");
		}
	}
	
	/**
	 * 管理页面 出池
	 * @param underlyingAssetId
	 */
	@RequestMapping(value = "/outAssetPoolAndDelete", method = { RequestMethod.GET })
	@ResponseBody
	public ObjectResult<String> outAssetPoolAndDelete(@RequestParam String underlyingAssetId) {
		try {
			logger.info("资产池管理页面出池");
			String userId = SecurityContextUtils.getUserDTOFromSession().getId();
			assetPoolApi.outAssetPoolAndDelete(userId, underlyingAssetId);
			return ObjectResult.success("出池成功");
		} catch (WebException e) {
			logger.error("资产池出池异常", e);
			return ObjectResult.fail(e.getMessage());
		} catch (Exception e) {
			logger.error("资产池出池异常", e);
			return ObjectResult.fail("系统异常");
		}
	}
	
	/**
	 * 管理页面 还款计划
	 */
	@RequestMapping(value = "/getLoanPaidList", method = { RequestMethod.POST })
	@ResponseBody
	public List<LoanPlanDTO> getLoanPaidList(@RequestParam String underlyingAssetId) {
		logger.info("资产池管理页面还款计划");
		String userId = SecurityContextUtils.getUserDTOFromSession().getId();
		List<LoanPlanDTO> LoanPlanDTOs = assetPoolApi.getLoanPaidList(userId, underlyingAssetId);
		return LoanPlanDTOs;
	}
	
	/**
	 * 管理页面 历史还款
	 */
	@RequestMapping(value = "/getLoanHistoryList", method = { RequestMethod.POST })
	@ResponseBody
	public ListResult<LoanPaidDTO> getLoanHistoryList(@RequestParam String underlyingAssetId,@RequestParam int page,@RequestParam int pageSize) {
		try {
			String userId = SecurityContextUtils.getUserDTOFromSession().getId();
			return assetPoolApi.getLoanHistoryList(underlyingAssetId, page, pageSize, userId);
		} catch (WebException e) {
			logger.error("资产池管理页面历史还款", e);
			return ListResult.fail(e.getMessage());
		} catch (Exception e) {
			logger.error("资产池管理页面历史还款", e);
			return ListResult.fail("系统异常");
		}
	}
	
	/**
	 * 入池页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/assetpoolEnter", method = { RequestMethod.GET })
	public String assetpoolEnter(@RequestParam String id, @RequestParam String status, Model model){
		logger.info("进入资产入池页面");
		String userId = SecurityContextUtils.getUserDTOFromSession().getId();
		AssetpoolListDTO assetpoolListDTO = assetPoolApi.getAssetPoolDetails(id, status, userId);
		model.addAttribute("assetpoolListDTO", assetpoolListDTO);
		model.addAttribute("businessProductArry", BusinessProduct.values());
		return "page/asset/assetpool/enter";
	}
	
	/**
	 * 入池
	 * @param packetDTO
	 * @return
	 */
	@RequestMapping(value = "assetpoolEnter",method = RequestMethod.POST )
	@ResponseBody
	public ObjectResult<String> enter(@RequestBody EnterAssetPoolRequest request){
		try {
			String userId = SecurityContextUtils.getOperatorId();
			assetPoolApi.enterAssetPool(userId, request.getUnderlyingAssetIds());
			return ObjectResult.success("入池成功");
		} catch (Exception e) {
			logger.error("资产池入池异常",e);
			return ObjectResult.fail(e.getMessage());
		}
	}
	
	/**
	 * 封包 页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/assetpoolPacket", method = { RequestMethod.GET })
	public String assetpoolPacket(@RequestParam String id, Model model){
		logger.info("进入资产池封包页面");
		String userId = SecurityContextUtils.getUserDTOFromSession().getId();
		AssetpoolListDTO assetpoolListDTO = assetPoolApi.getAssetpoolPacket(id, userId);
		model.addAttribute("assetpoolListDTO", assetpoolListDTO);
		return "page/asset/assetpool/packet";
	}
	
	/**
	 * 封包 提交
	 * @param packetDTO
	 * @return
	 */
	@RequestMapping(value = "packet",method = RequestMethod.POST )
	@ResponseBody
	public ObjectResult<String> packet(AssetPacketDTO packetDTO){
		try {
			String userId = SecurityContextUtils.getUserDTOFromSession().getId();
			assetPoolApi.packet(packetDTO, userId);
			return ObjectResult.success("封包成功");
		}catch (WebException e) {
			logger.error("资产池封包异常",e);
			return ObjectResult.fail(e.getMessage());
		} 
		catch (Exception e) {
			logger.error("资产池封包异常",e);
			return ObjectResult.fail("封包异常");
		}
	}
	
}
