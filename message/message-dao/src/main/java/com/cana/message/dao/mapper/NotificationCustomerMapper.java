package com.cana.message.dao.mapper;

import java.util.List;

import com.cana.message.dao.po.Notification;
import com.cana.vbam.common.message.dto.NotificationQueryCriteria;

public interface NotificationCustomerMapper {
	
	int count(NotificationQueryCriteria criteria);

    List<Notification> find(NotificationQueryCriteria criteria);
    
}