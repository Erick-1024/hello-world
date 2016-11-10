package com.cana.message.api;

import com.cana.vbam.common.dto.PageResult;
import com.cana.vbam.common.message.dto.NotificationMessageDTO;
import com.cana.vbam.common.message.dto.NotificationQueryCriteria;

public interface INotificationApi {
	
	/**
	 * 查询消息中心列表
	 */
	public PageResult<NotificationMessageDTO> queryNotifications(NotificationQueryCriteria criteria);
	
	/**
	 * 更新消息状态（未读 => 已读）
	 * @param notificationId
	 * @param userId
	 * @return
	 */
	public boolean updateReadStatus(String notificationId, String userId);
}
