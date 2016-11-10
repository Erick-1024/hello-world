/**
* Copyright (c) 2015, Cana and/or its affiliates. All rights reserved.
*/
package com.cana.repayment.dao.po;

import java.io.Serializable;
import java.util.Date;

public class RepaymentDailyBatchTaskItem implements Serializable {
    /**
    *主键
    */
    private String id;

    /**
    *日跑批任务Id
    */
    private String taskId;

    /**
    *该条目在日跑批任务中的序列号，从0开始
    */
    private Integer sequence;

    /**
    *任务类型
    */
    private String taskType;

    /**
    *该任务条目的执行时间，格式: MM:ss
    */
    private String executeTime;

    /**
    *扩展字段
    */
    private String extraData;

    /**
    *执行描述
    */
    private String detail;

    /**
    *创建时间
    */
    private Date createTime;

    /**
    *更新时间
    */
    private Date upateTime;

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
    *日跑批任务Id
    */
    public String getTaskId() {
        return taskId;
    }

    /**
    *日跑批任务Id
    */
    public void setTaskId(String taskId) {
        this.taskId = taskId == null ? null : taskId.trim();
    }

    /**
    *该条目在日跑批任务中的序列号，从0开始
    */
    public Integer getSequence() {
        return sequence;
    }

    /**
    *该条目在日跑批任务中的序列号，从0开始
    */
    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    /**
    *任务类型
    */
    public String getTaskType() {
        return taskType;
    }

    /**
    *任务类型
    */
    public void setTaskType(String taskType) {
        this.taskType = taskType == null ? null : taskType.trim();
    }

    /**
    *该任务条目的执行时间，格式: MM:ss
    */
    public String getExecuteTime() {
        return executeTime;
    }

    /**
    *该任务条目的执行时间，格式: MM:ss
    */
    public void setExecuteTime(String executeTime) {
        this.executeTime = executeTime == null ? null : executeTime.trim();
    }

    /**
    *扩展字段
    */
    public String getExtraData() {
        return extraData;
    }

    /**
    *扩展字段
    */
    public void setExtraData(String extraData) {
        this.extraData = extraData == null ? null : extraData.trim();
    }

    /**
    *执行描述
    */
    public String getDetail() {
        return detail;
    }

    /**
    *执行描述
    */
    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
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
    public Date getUpateTime() {
        return upateTime;
    }

    /**
    *更新时间
    */
    public void setUpateTime(Date upateTime) {
        this.upateTime = upateTime;
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
        RepaymentDailyBatchTaskItem other = (RepaymentDailyBatchTaskItem) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTaskId() == null ? other.getTaskId() == null : this.getTaskId().equals(other.getTaskId()))
            && (this.getSequence() == null ? other.getSequence() == null : this.getSequence().equals(other.getSequence()))
            && (this.getTaskType() == null ? other.getTaskType() == null : this.getTaskType().equals(other.getTaskType()))
            && (this.getExecuteTime() == null ? other.getExecuteTime() == null : this.getExecuteTime().equals(other.getExecuteTime()))
            && (this.getExtraData() == null ? other.getExtraData() == null : this.getExtraData().equals(other.getExtraData()))
            && (this.getDetail() == null ? other.getDetail() == null : this.getDetail().equals(other.getDetail()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpateTime() == null ? other.getUpateTime() == null : this.getUpateTime().equals(other.getUpateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTaskId() == null) ? 0 : getTaskId().hashCode());
        result = prime * result + ((getSequence() == null) ? 0 : getSequence().hashCode());
        result = prime * result + ((getTaskType() == null) ? 0 : getTaskType().hashCode());
        result = prime * result + ((getExecuteTime() == null) ? 0 : getExecuteTime().hashCode());
        result = prime * result + ((getExtraData() == null) ? 0 : getExtraData().hashCode());
        result = prime * result + ((getDetail() == null) ? 0 : getDetail().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpateTime() == null) ? 0 : getUpateTime().hashCode());
        return result;
    }
}