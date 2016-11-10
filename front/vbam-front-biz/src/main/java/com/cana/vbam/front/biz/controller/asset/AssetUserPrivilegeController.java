package com.cana.vbam.front.biz.controller.asset;

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

import com.cana.asset.api.IAssetUserPrivilegeApi;
import com.cana.member.api.IMemberQueryApi;
import com.cana.member.authorization.common.SecurityContextUtils;
import com.cana.vbam.common.asset.dto.AddUserPrivilegeRequest;
import com.cana.vbam.common.asset.dto.CreditDTO;
import com.cana.vbam.common.asset.dto.QueryCompany4AddUserPrivilegeListItem;
import com.cana.vbam.common.asset.dto.QueryCompany4AddUserPrivilegeRequest;
import com.cana.vbam.common.asset.dto.QueryCustomer4AddUserPrivilegeListItem;
import com.cana.vbam.common.asset.dto.QueryCustomer4AddUserPrivilegeRequest;
import com.cana.vbam.common.asset.dto.QueryUserPrivilegeListItem;
import com.cana.vbam.common.asset.dto.QueryUserPrivilegeListRequest;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.dto.ObjectResult;
import com.cana.vbam.common.member.vo.UserVo;
import com.google.gson.Gson;

/**
 * @author renshui
 *
 */
@Controller
@RequestMapping("/asset/privilege")
public class AssetUserPrivilegeController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private IAssetUserPrivilegeApi privilegeApi;
	@Resource
	private IMemberQueryApi memberQueryApi;
	
	
	@RequestMapping(value = "/customerList", method = RequestMethod.GET )
	public String privilegeCustomerListPage(Model model) {
		return "page/asset/privilege/privilegeManage";
	}
	@RequestMapping(value = "/add", method = RequestMethod.POST )
	@ResponseBody
	public ObjectResult<Object> add(@RequestBody AddUserPrivilegeRequest request) {
		try {
			logger.info("添加用户权限请求:" + new Gson().toJson(request));
			String userId = SecurityContextUtils.getUserDTOFromSession().getId();
			UserVo currentLoginUserVO = memberQueryApi.findUserById(userId);
			privilegeApi.add(currentLoginUserVO, request);
			return ObjectResult.success();
		} catch (Exception e) {
			logger.error("",e);
			return ObjectResult.fail(e.getMessage());
		}
	}

	@RequestMapping(value = "/queryList", method = RequestMethod.POST)
	@ResponseBody
	public ListResult<QueryUserPrivilegeListItem> queryList(QueryUserPrivilegeListRequest request) {
		try {
			logger.info("查询权限列表请求:" + new Gson().toJson(request));
			String userId = SecurityContextUtils.getUserDTOFromSession().getId();
			UserVo currentLoginUserVO = memberQueryApi.findUserById(userId);
			return privilegeApi.queryList(currentLoginUserVO, request);
		} catch (Exception e) {
			logger.error("",e);
			return ListResult.fail(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/queryCompany4Add", method = RequestMethod.POST)
	@ResponseBody
	public ListResult<QueryCompany4AddUserPrivilegeListItem> queryCompany4Add(QueryCompany4AddUserPrivilegeRequest request) {
		try {
			logger.info("添加权限时查询企业信息请求:" + new Gson().toJson(request));
			String userId = SecurityContextUtils.getUserDTOFromSession().getId();
			UserVo currentLoginUserVO = memberQueryApi.findUserById(userId);
			return privilegeApi.queryCompany4Add(currentLoginUserVO, request);
		} catch (Exception e) {
			logger.error("",e);
			return ListResult.fail(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/queryCustomer4Add", method = RequestMethod.POST)
	@ResponseBody
	public ListResult<QueryCustomer4AddUserPrivilegeListItem> queryCustomer4Add(QueryCustomer4AddUserPrivilegeRequest request) {
		try {
			logger.info("添加权限时查询客户信息请求:" + new Gson().toJson(request));
			String userId = SecurityContextUtils.getUserDTOFromSession().getId();
			UserVo currentLoginUserVO = memberQueryApi.findUserById(userId);
			return privilegeApi.queryCustomer4Add(currentLoginUserVO, request);
		} catch (Exception e) {
			logger.error("",e);
			return ListResult.fail(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public ObjectResult<CreditDTO> delete(@RequestParam String id) {
		try {
			logger.info("删除用户权限请求:" + id);
			String userId = SecurityContextUtils.getUserDTOFromSession().getId();
			UserVo currentLoginUserVO = memberQueryApi.findUserById(userId);
			privilegeApi.delete(currentLoginUserVO, id);
			return ObjectResult.success();
		} catch (Exception e) {
			logger.error("",e);
			return ObjectResult.fail(e.getMessage());
		}
	}
	
}
