package com.cana.vbam.common.message.dto;

import java.io.Serializable;
import java.util.List;

import com.cana.vbam.common.message.enums.NotificationType;

/**
 * 查询消息中心列表条件
 * @author TangYihong
 *
 */
public class NotificationQueryCriteria implements Serializable {

	private static final long serialVersionUID = 5171182657920565830L;
	
	private int pageSize;  //每页显示行数
	private int page = 1;  //页码
	private NotificationType type; //消息类型
	private String beginTime;  //消息开始时间，yyyy-MM-dd
    private String endTime; //消息结束时间，yyyy-MM-dd
    private Boolean isRead;  //是否已读
    private String userId;  //当前用户Id
    private String customerId;  //当前用户的公司Id
    private List<NotificationType> notificationTypes;  //当前用户拥有消息中心相关的权限list

    public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public NotificationType getType() {
		return type;
	}

	public void setType(NotificationType type) {
		this.type = type;
	}

	public Boolean getIsRead() {
		return isRead;
	}

	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public List<NotificationType> getNotificationTypes() {
		return notificationTypes;
	}

	public void setNotificationTypes(List<NotificationType> notificationTypes) {
		this.notificationTypes = notificationTypes;
	}
}
