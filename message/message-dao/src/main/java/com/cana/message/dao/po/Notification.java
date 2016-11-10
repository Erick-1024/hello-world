/**
* Copyright (c) 2015, Cana and/or its affiliates. All rights reserved.
*/
package com.cana.message.dao.po;

import java.io.Serializable;
import java.util.Date;

public class Notification implements Serializable {
    /**
    *主键
    */
    private String id;

    /**
    *消息类型
    */
    private String type;

    /**
    *是否已读
    */
    private Boolean isRead;

    /**
    *发送消息的员工ID
    */
    private String sendUserId;

    /**
    *消息接收员工ID
    */
    private String receiveUserId;

    /**
    *消息接收公司ID
    */
    private String receiveCustomerId;

    /**
    *消息内容
    */
    private String content;

    /**
    *点击查看详情指向的链接
    */
    private String detailUrl;

    /**
    *创建时间
    */
    private Date createTime;

    /**
    *更新时间
    */
    private Date updateTime;

    /**
    *更新员工ID
    */
    private String updateUserId;

    private static final long serialVersionUID = 1L;

    /**
    *主键
    */
    public String getId() {
        return id;
    }

    /**
    *主键
    */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
    *消息类型
    */
    public String getType() {
        return type;
    }

    /**
    *消息类型
    */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
    *是否已读
    */
    public Boolean getIsRead() {
        return isRead;
    }

    /**
    *是否已读
    */
    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }

    /**
    *发送消息的员工ID
    */
    public String getSendUserId() {
        return sendUserId;
    }

    /**
    *发送消息的员工ID
    */
    public void setSendUserId(String sendUserId) {
        this.sendUserId = sendUserId == null ? null : sendUserId.trim();
    }

    /**
    *消息接收员工ID
    */
    public String getReceiveUserId() {
        return receiveUserId;
    }

    /**
    *消息接收员工ID
    */
    public void setReceiveUserId(String receiveUserId) {
        this.receiveUserId = receiveUserId == null ? null : receiveUserId.trim();
    }

    /**
    *消息接收公司ID
    */
    public String getReceiveCustomerId() {
        return receiveCustomerId;
    }

    /**
    *消息接收公司ID
    */
    public void setReceiveCustomerId(String receiveCustomerId) {
        this.receiveCustomerId = receiveCustomerId == null ? null : receiveCustomerId.trim();
    }

    /**
    *消息内容
    */
    public String getContent() {
        return content;
    }

    /**
    *消息内容
    */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
    *点击查看详情指向的链接
    */
    public String getDetailUrl() {
        return detailUrl;
    }

    /**
    *点击查看详情指向的链接
    */
    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl == null ? null : detailUrl.trim();
    }

    /**
    *创建时间
    */
    public Date getCreateTime() {
        return createTime;
    }

    /**
    *创建时间
    */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
    *更新时间
    */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
    *更新时间
    */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
    *更新员工ID
    */
    public String getUpdateUserId() {
        return updateUserId;
    }

    /**
    *更新员工ID
    */
    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId == null ? null : updateUserId.trim();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Notification other = (Notification) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getIsRead() == null ? other.getIsRead() == null : this.getIsRead().equals(other.getIsRead()))
            && (this.getSendUserId() == null ? other.getSendUserId() == null : this.getSendUserId().equals(other.getSendUserId()))
            && (this.getReceiveUserId() == null ? other.getReceiveUserId() == null : this.getReceiveUserId().equals(other.getReceiveUserId()))
            && (this.getReceiveCustomerId() == null ? other.getReceiveCustomerId() == null : this.getReceiveCustomerId().equals(other.getReceiveCustomerId()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getDetailUrl() == null ? other.getDetailUrl() == null : this.getDetailUrl().equals(other.getDetailUrl()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getUpdateUserId() == null ? other.getUpdateUserId() == null : this.getUpdateUserId().equals(other.getUpdateUserId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getIsRead() == null) ? 0 : getIsRead().hashCode());
        result = prime * result + ((getSendUserId() == null) ? 0 : getSendUserId().hashCode());
        result = prime * result + ((getReceiveUserId() == null) ? 0 : getReceiveUserId().hashCode());
        result = prime * result + ((getReceiveCustomerId() == null) ? 0 : getReceiveCustomerId().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getDetailUrl() == null) ? 0 : getDetailUrl().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getUpdateUserId() == null) ? 0 : getUpdateUserId().hashCode());
        return result;
    }
}