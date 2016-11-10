package com.cana.message.server.service.transaction.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cana.common.dao.mapper.gen.PropertiesMapper;
import com.cana.common.dao.po.Properties;
import com.cana.message.dao.mapper.NotificationCustomerMapper;
import com.cana.message.dao.mapper.gen.NotificationMapper;
import com.cana.message.dao.po.Notification;
import com.cana.message.server.service.transaction.INotificationTransactionService;
import com.cana.vbam.common.dto.PageResult;
import com.cana.vbam.common.message.dto.NotificationMessageDTO;
import com.cana.vbam.common.message.dto.NotificationQueryCriteria;
import com.cana.vbam.common.message.enums.NotificationType;
import com.cana.vbam.common.service.IVbamCommonService;
import com.cana.vbam.common.utils.Constants;
import com.google.common.collect.Lists;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.dao.rdbms.SequenceGenerator;

@Service
public class NotificationTransactionService implements INotificationTransactionService {
    private Logger log = LoggerFactory.getLogger(NotificationTransactionService.class);
    
    @Resource
    private NotificationCustomerMapper notificationCustomerMapper;

    @Resource
    private NotificationMapper notificationMapper;
    
    @Resource
    private SequenceGenerator seqGen;
    
    @Resource
	private IVbamCommonService vbamCommonService;
    
    @Resource
	private PropertiesMapper propertiesMapper;
    
    @Override
    public void saveNotification(NotificationMessageDTO message) {
        log.info("notification:" + message.toString());
        if(StringUtils.isNotBlank(message.getMessageId())){
        	String messageId = getMessageId(message);
            Properties txRecord = propertiesMapper.selectByPrimaryKey(messageId);
            if(txRecord == null){
            	vbamCommonService.addProperties(messageId, "true");
            }else return;
        }
        Notification notification = new Notification();
        if (message.getType() == null
                || StringUtils.isBlank(message.getContent())) {
            return;
        }
        if (StringUtils.isBlank(message.getReceiveCustomerId())
                && StringUtils.isBlank(message.getReceiveUserId())) {
            return;
        }
        notification.setType(message.getType().name());
        notification.setIsRead(false);
        notification.setSendUserId(message.getSendUserId());
        if (StringUtils.isNotBlank(message.getReceiveCustomerId())) {
            notification.setReceiveCustomerId(message.getReceiveCustomerId());
        } else {
            notification.setReceiveUserId(message.getReceiveUserId());
        }
        notification.setContent(message.getContent());
        notification.setDetailUrl(message.getDetailURL());
        notification.setCreateTime(new Date());
        notification.setId(generateNotificationId());
        notificationMapper.insert(notification);
    }
    
    /**
	 * 返回的id用于分布式事务判断操作是否执行过
	 * @param message
	 * @return
	 */
	private String getMessageId(NotificationMessageDTO message) {
		return "message-server:notification:" + message.getMessageId();
	}
    
    private String generateNotificationId() {
        return DateTimeUtil.datetime12() + 
                seqGen.getNextSeq(Constants.MESSAGE_NOTIFICATION_ID, 4);
    }
    
    @Override
    public PageResult<NotificationMessageDTO> queryNotifications(NotificationQueryCriteria criteria) {
    	criteria = switchNotificationCriteria(criteria);
    	int total = notificationCustomerMapper.count(criteria);
    	List<Notification> notifications = notificationCustomerMapper.find(criteria);
    	List<NotificationMessageDTO> notificationMessageDTOs = convert2NotificationMessageDTOs(notifications);
		return new PageResult<NotificationMessageDTO>(notificationMessageDTOs,total);
    }

    /**
     * NotificationQueryCriteria查询条件转换
     * @param criteria
     * @return
     */
    private NotificationQueryCriteria switchNotificationCriteria(NotificationQueryCriteria criteria){
    	NotificationQueryCriteria criteriaDB = new NotificationQueryCriteria();
    	criteriaDB.setPage(criteria.getPage() < 1 ? 1 : criteria.getPage());
		criteriaDB.setPageSize(criteria.getPageSize() < 1 ? 10 : criteria.getPageSize());
		criteriaDB.setType(criteria.getType());
		if(StringUtils.isNotBlank(criteria.getBeginTime()))
			criteriaDB.setBeginTime(criteria.getBeginTime());
		if(StringUtils.isNotBlank(criteria.getEndTime()))
			criteriaDB.setEndTime(stringPlusDay(criteria.getEndTime()));
		criteriaDB.setIsRead(criteria.getIsRead());
		criteriaDB.setUserId(criteria.getUserId());
		criteriaDB.setCustomerId(criteria.getCustomerId());
		if(criteria.getType()==null && criteria.getNotificationTypes()!=null && criteria.getNotificationTypes().size()!=0)
			criteriaDB.setNotificationTypes(criteria.getNotificationTypes());
    	return criteriaDB;
    }
    
    /**
     * 给定日期加一天
     * @param originDate
     * @return
     */
    private String stringPlusDay(String originDate){
    	SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		String newDate = sdf.format(new DateTime(originDate).plusDays(1).toDate());
		return newDate;
	}
    
    /**
     * 消息list pos转dtos
     * @param notifications
     * @return
     */
    private List<NotificationMessageDTO> convert2NotificationMessageDTOs(List<Notification> notifications){
    	if (notifications == null || notifications.isEmpty())
    	    return Lists.newArrayList();
    	List<NotificationMessageDTO> dtos = Lists.newArrayList();
    	for(Notification notification : notifications){
    		NotificationMessageDTO dto = new NotificationMessageDTO();
    		dto.setId(notification.getId());
    		if(StringUtils.isNotBlank(notification.getType())){
    			NotificationType type = NotificationType.valueOf(notification.getType());
    			dto.setType(type);
	    		dto.setTypeDesc(type.getDesc());
    		}
    		dto.setRead(notification.getIsRead());
    		if(notification.getIsRead()!=null){
    			if(notification.getIsRead())
    				dto.setReadDesc("已读");
    			else
    				dto.setReadDesc("未读");
    		}    			
    		dto.setSendUserId(notification.getSendUserId());
    		dto.setReceiveUserId(notification.getReceiveUserId());
    		dto.setReceiveCustomerId(notification.getReceiveCustomerId());
    		dto.setContent(notification.getContent());
    		dto.setDetailURL(notification.getDetailUrl());
    		dto.setCreateTime(notification.getCreateTime());
    		dtos.add(dto);
    	}
    	return dtos;
    	
    }
    
    /**
	 * 更新消息状态（未读 => 已读）
	 * @param notificationId
	 * @param userId
	 * @return
	 */
	@Override
	public boolean updateReadStatus(String notificationId, String userId) {
		Notification notification = notificationMapper.selectByPrimaryKey(notificationId);
		if(!notification.getIsRead()){
			notification.setIsRead(true);
			notification.setUpdateTime(new Date());
			notification.setUpdateUserId(userId);
			notificationMapper.updateByPrimaryKeySelective(notification);
		}
		return true;
	}
}
