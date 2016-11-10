package com.cana.wechat.openapi.controller;

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

import com.cana.vbam.common.dto.ObjectResult;
import com.cana.vbam.common.wechat.account.TradeRecordRequest;
import com.cana.wechat.api.IWeChatApi;
import com.travelzen.framework.core.exception.WebException;

@Controller
@RequestMapping(value = "/wechat/account")
public class WechatAccountController {

	@Resource
	private IWeChatApi weChatApi;

	private Logger logger = LoggerFactory.getLogger(getClass());

	@RequestMapping(value = "/tradeRecord", method = RequestMethod.GET)
	public String gotoTradeRecord(Model model) {
		return "";
	}

	@RequestMapping(value = "/tradeRecord", method = RequestMethod.POST)
	@ResponseBody
	public ObjectResult<?> getTradeRecord(@RequestBody TradeRecordRequest request) {
		try {
			return ObjectResult.success("微信——查询流水明细成功", weChatApi.queryTradeRecords(request));
		}catch(WebException e){
		    logger.error("微信——查询流水明细失败", e);
			return ObjectResult.fail(e.getMessage());
		}catch(Exception e){
		    logger.error("微信——查询流水明细异常", e);
		    return ObjectResult.fail("微信——查询流水明细异常");
        }
	}
	
	@RequestMapping(value = "/contactInfo", method = RequestMethod.GET)
	public String gotoCustomerWechatDetai(Model model) {
		return "";
	}
	
	@RequestMapping(value = "/contactInfo", method = RequestMethod.POST)
	@ResponseBody
	public ObjectResult<?> queryCustomerWechatDetai(@RequestParam String customerId) {
		try {
			return ObjectResult.success("微信——查询联系人信息成功", weChatApi.queryCustomerDetail(customerId));
		}catch(WebException e){
		    logger.error("微信——查询联系人信息失败", e);
			return ObjectResult.fail(e.getMessage());
		}catch(Exception e){
		    logger.error("微信——查询联系人信息异常", e);
		    return ObjectResult.fail("微信——查询联系人信息异常");
        }
	}
	
	@RequestMapping(value = "/modifyContactsInfo", method = RequestMethod.GET)
	public String gotoModifyContactsInfo(Model model) {
		return "";
	}
	@RequestMapping(value = "/updateContactName", method = RequestMethod.POST)
	@ResponseBody
	public ObjectResult<?> updateWechatContactName(@RequestParam String customerId, @RequestParam String contactName) {
		try {
			weChatApi.updateContactName(customerId, contactName);
			return ObjectResult.success("微信——修改联系人姓名成功");
		}catch(WebException e){
		    logger.error("微信——修改联系人姓名失败", e);
			return ObjectResult.fail(e.getMessage());
		}catch(Exception e){
		    logger.error("微信——修改联系人姓名异常", e);
		    return ObjectResult.fail("微信——修改联系人姓名异常");
        }
	}
	@RequestMapping(value = "/updateContactTel", method = RequestMethod.POST)
	@ResponseBody
	public ObjectResult<?> updateWechatContactTel(@RequestParam String customerId, @RequestParam String contactTel) {
		try {
			weChatApi.updateContactTel(customerId, contactTel);
			return ObjectResult.success("微信——修改联系人手机号码成功");
		}catch(WebException e){
			logger.error("微信——修改联系人手机号码失败", e);
			return ObjectResult.fail(e.getMessage());
		}catch(Exception e){
			logger.error("微信——修改联系人手机号码异常", e);
			return ObjectResult.fail("微信——修改联系人手机号码异常");
		}
	}
	@RequestMapping(value = "/updateContactMail", method = RequestMethod.POST)
	@ResponseBody
	public ObjectResult<?> updateWechatContactMail(@RequestParam String customerId, @RequestParam String contactMail) {
		try {
			weChatApi.updateContactMail(customerId, contactMail);
			return ObjectResult.success("微信——修改联系人邮箱成功");
		}catch(WebException e){
			logger.error("微信——修改联系人邮箱失败", e);
			return ObjectResult.fail(e.getMessage());
		}catch(Exception e){
			logger.error("微信——修改联系人邮箱异常", e);
			return ObjectResult.fail("微信——修改联系人邮箱异常");
		}
	}
	@RequestMapping(value = "/updateContactJobTitle", method = RequestMethod.POST)
	@ResponseBody
	public ObjectResult<?> updateWechatContactJobTitle(@RequestParam String customerId, @RequestParam String contactJobTitle) {
		try {
			weChatApi.updateContactTel(customerId, contactJobTitle);
			return ObjectResult.success("微信——修改联系人职称成功");
		}catch(WebException e){
			logger.error("微信——修改联系人职称失败", e);
			return ObjectResult.fail(e.getMessage());
		}catch(Exception e){
			logger.error("微信——修改联系人职称异常", e);
			return ObjectResult.fail("微信——修改联系人职称异常");
		}
	}
	
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public String gotoAccountInfo(Model model) {
		return "";
	}
	
	@RequestMapping(value = "/info", method = RequestMethod.POST)
	@ResponseBody
	public ObjectResult<?> getAccountInfo(@RequestParam String customerId) {
		try {
			return ObjectResult.success("微信——查询账户信息成功", weChatApi.getAccountInfo(customerId));
		}catch(WebException e){
		    logger.error("微信——查询账户信息失败", e);
			return ObjectResult.fail(e.getMessage());
		}catch(Exception e){
		    logger.error("微信——查询账户信息异常", e);
		    return ObjectResult.fail("微信——查询账户信息异常");
        }
	}
}
