package com.cana.message.server.service.transaction;

import com.cana.vbam.common.dto.PageResult;
import com.cana.vbam.common.message.dto.NotificationMessageDTO;
import com.cana.vbam.common.message.dto.NotificationQueryCriteria;

public interface INotificationTransactionService {

    public void saveNotification(NotificationMessageDTO message);

    public PageResult<NotificationMessageDTO> queryNotifications(NotificationQueryCriteria criteria);
    
    public boolean updateReadStatus(String notificationId,String userId);
}
