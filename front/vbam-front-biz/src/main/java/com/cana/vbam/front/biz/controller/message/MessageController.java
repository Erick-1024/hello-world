package com.cana.vbam.front.biz.controller.message;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cana.member.authorization.common.SecurityContextUtils;
import com.cana.message.api.INotificationApi;
import com.cana.vbam.common.dto.PageResult;
import com.cana.vbam.common.message.dto.NotificationMessageDTO;
import com.cana.vbam.common.message.dto.NotificationQueryCriteria;
import com.cana.vbam.common.message.enums.NotificationType;
import com.google.common.collect.Lists;

/**
 * 消息中心 
 * @author TangYihong
 */

@Controller
@RequestMapping(value = "/message/notification")
public class MessageController {
	
	private static final Logger logger = LoggerFactory.getLogger(MessageController.class);
	
	@Resource
	private INotificationApi notificationApi;
	
	/**
	 * 消息中心列表(全部)
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list(Model model){
		List<NotificationType> notificationTypes = checkNotificationAuthorize();
		model.addAttribute("notificationTypes", notificationTypes);
		model.addAttribute("isRead","");
		return "page/message/notification/list";
	}	
	
	/**
	 * 根据查询条件查询 消息列表
	 */
	@RequestMapping(value="/searchList")
	@ResponseBody
	public PageResult<NotificationMessageDTO> listNotification(NotificationQueryCriteria criteria){
		criteria.setUserId(SecurityContextUtils.getOperatorId());
		criteria.setCustomerId(SecurityContextUtils.getCustomerId());
		List<NotificationType> notificationTypes = checkNotificationAuthorize();
		if(criteria.getType()!=null && !notificationTypes.contains(criteria.getType()))
				criteria.setType(null);
		criteria.setNotificationTypes(notificationTypes);
		PageResult<NotificationMessageDTO> result = notificationApi.queryNotifications(criteria);
		return result;
	}
	
	/**
	 * 消息设为已读
	 * @param notificationId
	 * @param model
	 */
	@RequestMapping(value="/updateReadStatus")
	@ResponseBody
	public String updateReadStatus(@RequestParam String notificationId) {
		String result = "success";
		String userId = SecurityContextUtils.getOperatorId();
		try{
			notificationApi.updateReadStatus(notificationId, userId);
		}catch(Exception e){
            result = "设消息为已读失败";
            logger.error(e.getMessage(), e);
            return result;
        }
		return result;
	}
	
	/**
	 * 返回当前用户拥有消息中心相关的权限list
	 * @return
	 */
	private List<NotificationType> checkNotificationAuthorize(){
		List<NotificationType> notificationTypes = Lists.newArrayList();
		for(NotificationType notificationType : NotificationType.values()){
			//企业用户审批
			if(notificationType == NotificationType.CUSTOMER_REGISTRY){
				if(SecurityContextUtils.authorizePermKey("UM_TOTAL_DETAIL") || SecurityContextUtils.authorizePermKey("UM_UNAUDIT_DETAIL") || SecurityContextUtils.authorizePermKey("UM_AUDITED_DETAIL")){
					notificationTypes.add(notificationType);
					continue;
				}
			}
			//开户账户审核
			if(notificationType == NotificationType.CREATE_ACCOUNT){
				if(SecurityContextUtils.authorizePermKey("AM_APPLY_DETAIL") || SecurityContextUtils.authorizePermKey("AM_PENDING_AUDIT_DETAIL") || SecurityContextUtils.authorizePermKey("AM_HAVING_AUDIT_DETAIL")){
					notificationTypes.add(notificationType);
					continue;
				}
			}
			//新建监管审批，解除监管审批
			if(notificationType == NotificationType.CREATE_SUPERVISION || notificationType == NotificationType.REMOVE_SUPERVISION){
				if(SecurityContextUtils.authorizePermKey("AM_ACCOUNT_SUPERVISION_APPLY_DETAIL")){
					notificationTypes.add(notificationType);
					continue;
				}
			}
			//转账审批，提现审批
			if(notificationType == NotificationType.TRANSFER_FUND || notificationType == NotificationType.WITHDRAW_FUND){
				if(SecurityContextUtils.authorizePermKey("AM_ACCOUNT_TRADE_APPLY_DETAIL")){
					notificationTypes.add(notificationType);
					continue;
				}
			}
			//自动扣款，调账，主动还款
			if(notificationType == NotificationType.AUTO_DEDUCT_FUND || notificationType == NotificationType.ADJUST_FUND || notificationType == NotificationType.REFUND_REPAYMENT || notificationType == NotificationType.ACTIVE_REPAYMENT){
				if(SecurityContextUtils.authorizePermKey("FM_REPAYMENTINFO_MANAGE_PLANDETAIL")){
					notificationTypes.add(notificationType);
					continue;
				}
			}
		}
		return notificationTypes;
	}
}
