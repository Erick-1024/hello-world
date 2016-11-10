/**
 *  Copyright © 2015 Cana. All rights reserved.
 */
package com.cana.vbam.front.biz.controller.account;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cana.account.api.IAccountApi;
import com.cana.member.api.IRoleApi;
import com.cana.member.authorization.common.SecurityContextUtils;
import com.cana.vbam.common.account.dto.AccountTradeApplyDTO;
import com.cana.vbam.common.account.dto.AccountTradeApplyQueryCriteria;
import com.cana.vbam.common.account.enums.AccountTradeApplyStatus;
import com.cana.vbam.common.account.enums.AccountTradeApplyType;
import com.cana.vbam.common.dto.PageResult;
import com.travelzen.framework.core.exception.WebException;

/**
 * 审核中心
 * @author XuMeng
 *
 */
@Controller
@RequestMapping(value = "/account/audit")
public class AccountAuditCenterController {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private IAccountApi accountApi;
	@Resource
	private IRoleApi roleApi;

	/**
	 * 待自己审核的申请列表
	 */
	@RequestMapping(value = "list")
    public String listView(Model model){
	    model.addAttribute("applyTypeEnums", AccountTradeApplyType.values());
	    model.addAttribute("applyStatusEnums", AccountTradeApplyStatus.values());
        return "page/account/audit/listView";
    }

	/**
	 * 自己申请的申请列表
	 */
	@RequestMapping(value = "applys")
	public String applyBySelf(Model model){
        model.addAttribute("applyTypeEnums", AccountTradeApplyType.values());
        model.addAttribute("applyStatusEnums", AccountTradeApplyStatus.values());
		return "page/account/audit/applysView";
	}

	/**
	 * 搜索申请
	 */
	@RequestMapping(value = "search")
	@ResponseBody
    public PageResult<AccountTradeApplyDTO> search(AccountTradeApplyQueryCriteria criteria){
	    String userId = SecurityContextUtils.getUserDTOFromSession().getId();
	    try {
    	    PageResult<AccountTradeApplyDTO> result = accountApi.queryTradeApplys(userId, criteria);
            return result;
	    } catch (Exception e) {
	        logger.error("查询申请出错", e);
	        throw WebException.instance("服务器出错");
	    }
    }

    @RequestMapping(value = "redirect")
    public String redirect(String tradeApplyId){
        if (StringUtils.isBlank(tradeApplyId))
            throw WebException.instance("请求参数不能为空");
        String userId = SecurityContextUtils.getUserDTOFromSession().getId();
        PageResult<AccountTradeApplyDTO> result = null;
        try {
            AccountTradeApplyQueryCriteria criteria = new AccountTradeApplyQueryCriteria();
            criteria.setTradeApplyId(tradeApplyId);
            result = accountApi.queryTradeApplys(userId, criteria);
        } catch (Exception e) {
            logger.error("查询申请出错", e);
            throw WebException.instance("服务器出错");
        }
        if (CollectionUtils.isEmpty(result.getData()))
            throw WebException.instance("详情不存在");
        AccountTradeApplyDTO apply = result.getData().get(0);
        String page = "detail";
        boolean pendingAudit = AccountTradeApplyStatus.PENDINGAUDIT.equals(apply.getApplyStatus());
        boolean supervisionApplyAuditAuth = SecurityContextUtils.authorizeUrl("/account/supervision/audit");
        boolean tradeApplyAuditAuth = SecurityContextUtils.authorizeUrl("/account/trade/audit");
        String url = "redirect:";
        switch (apply.getApplyType()) {
        case CREATE_SUPERVISION:
        case REMOVE_SUPERVISION:
            if (pendingAudit && supervisionApplyAuditAuth) {
                page = "audit";
            }
            url += "/account/supervision/" + page + "?applyId=";
            break;
        case TRANSFER_FUND:
        case WITHDRAW_FUND:
            if (pendingAudit && tradeApplyAuditAuth) {
                page = "audit";
            }
            url += "/account/trade/" + page + "?tradeApplyId=";
            break;
        }
        return url + tradeApplyId;
    }
	
}
