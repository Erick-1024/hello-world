package com.cana.vbam.common.message.dto;

import java.io.Serializable;
import java.util.Date;

import com.cana.vbam.common.message.enums.NotificationType;

public class NotificationMessageDTO implements Serializable{
    private static final long serialVersionUID = 5167887289738490749L;

    //发送消息时需要的字段
    private String messageId;           // 消息id，用于分布式事务消息判重
    private NotificationType type;      //消息类型，不可为空
    private String sendUserId;          //发送员工ID
    private String receiveUserId;       //发送给某一个员工的通知，若设置了receiveCustomerId，则忽略此值
    private String receiveCustomerId;   //发送给某一个公司所有员工的通知
    private String content;             //消息内容，不可为空
    private String detailURL;           //点击查看详情指向的链接，暂只支持相对地址

    //下面的字段为页面显示消息时使用的
    private String id;					//消息id
    private String typeDesc;            //消息类型描述
    private boolean read;               //是否已读
    private String readDesc;            //消息状态 已读，未读
    private Date createTime;            //创建时间

    public NotificationType getType() {
        return type;
    }
    public void setType(NotificationType type) {
        this.type = type;
    }
    public String getSendUserId() {
        return sendUserId;
    }
    public void setSendUserId(String sendUserId) {
        this.sendUserId = sendUserId;
    }
    public String getReceiveCustomerId() {
        return receiveCustomerId;
    }
    public void setReceiveCustomerId(String receiveCustomerId) {
        this.receiveCustomerId = receiveCustomerId;
    }
    public String getReceiveUserId() {
        return receiveUserId;
    }
    public void setReceiveUserId(String receiveUserId) {
        this.receiveUserId = receiveUserId;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getDetailURL() {
        return detailURL;
    }
    public void setDetailURL(String detailURL) {
        this.detailURL = detailURL;
    }
    @Override
    public String toString() {
        return "NotificationMessageDTO [type=" + type + ", content=" + content
                + ", sendUserId=" + sendUserId + ", receiveCustomerId="
                + receiveCustomerId + ", receiveUserId=" + receiveUserId
                + ", detailURL=" + detailURL + "]";
    }
	public boolean isRead() {
		return read;
	}
	public void setRead(boolean read) {
		this.read = read;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getTypeDesc() {
		return typeDesc;
	}
	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}
	public String getReadDesc() {
		return readDesc;
	}
	public void setReadDesc(String readDesc) {
		this.readDesc = readDesc;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
}
