/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.repayment.dao.po;

import java.io.Serializable;
import java.util.Date;

public class RepaymentDailyBatchTask implements Serializable {
    /**
     *主键
     */
    private String id;

    /**
     *放款信息Id
     */
    private String loanInfoId;

    /**
     *放款中的融资客户ID
     */
    private String customerId;

    /**
     *日期， 格式: yyyy-MM-dd
     */
    private String date;

    /**
     *该放款信息当日跑批任务条目数
     */
    private Integer taskNum;

    /**
     *下一个要执行任务的序列数
     */
    private Integer sequence;

    /**
     *下一个要执行的任务条目id
     */
    private String nextTaskItemId;

    /**
     *下一个要执行的任务条目的执行时间，格式: MM:ss
     */
    private String nextTaskItemExecuteTime;

    /**
     *
     */
    private String failTaskItemId;

    /**
     *失败原因
     */
    private String failMessage;

    /**
     *失败任务是否可重试
     */
    private Boolean canRetry;

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
     *放款信息Id
     */
    public String getLoanInfoId() {
        return loanInfoId;
    }

    /**
     *放款信息Id
     */
    public void setLoanInfoId(String loanInfoId) {
        this.loanInfoId = loanInfoId == null ? null : loanInfoId.trim();
    }

    /**
     *放款中的融资客户ID
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     *放款中的融资客户ID
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    /**
     *日期， 格式: yyyy-MM-dd
     */
    public String getDate() {
        return date;
    }

    /**
     *日期， 格式: yyyy-MM-dd
     */
    public void setDate(String date) {
        this.date = date == null ? null : date.trim();
    }

    /**
     *该放款信息当日跑批任务条目数
     */
    public Integer getTaskNum() {
        return taskNum;
    }

    /**
     *该放款信息当日跑批任务条目数
     */
    public void setTaskNum(Integer taskNum) {
        this.taskNum = taskNum;
    }

    /**
     *下一个要执行任务的序列数
     */
    public Integer getSequence() {
        return sequence;
    }

    /**
     *下一个要执行任务的序列数
     */
    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    /**
     *下一个要执行的任务条目id
     */
    public String getNextTaskItemId() {
        return nextTaskItemId;
    }

    /**
     *下一个要执行的任务条目id
     */
    public void setNextTaskItemId(String nextTaskItemId) {
        this.nextTaskItemId = nextTaskItemId == null ? null : nextTaskItemId.trim();
    }

    /**
     *下一个要执行的任务条目的执行时间，格式: MM:ss
     */
    public String getNextTaskItemExecuteTime() {
        return nextTaskItemExecuteTime;
    }

    /**
     *下一个要执行的任务条目的执行时间，格式: MM:ss
     */
    public void setNextTaskItemExecuteTime(String nextTaskItemExecuteTime) {
        this.nextTaskItemExecuteTime = nextTaskItemExecuteTime == null ? null : nextTaskItemExecuteTime.trim();
    }

    /**
     *
     */
    public String getFailTaskItemId() {
        return failTaskItemId;
    }

    /**
     *
     */
    public void setFailTaskItemId(String failTaskItemId) {
        this.failTaskItemId = failTaskItemId == null ? null : failTaskItemId.trim();
    }

    /**
     *失败原因
     */
    public String getFailMessage() {
        return failMessage;
    }

    /**
     *失败原因
     */
    public void setFailMessage(String failMessage) {
        this.failMessage = failMessage == null ? null : failMessage.trim();
    }

    /**
     *失败任务是否可重试
     */
    public Boolean getCanRetry() {
        return canRetry;
    }

    /**
     *失败任务是否可重试
     */
    public void setCanRetry(Boolean canRetry) {
        this.canRetry = canRetry;
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
        RepaymentDailyBatchTask other = (RepaymentDailyBatchTask) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getLoanInfoId() == null ? other.getLoanInfoId() == null : this.getLoanInfoId().equals(other.getLoanInfoId()))
            && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId()))
            && (this.getDate() == null ? other.getDate() == null : this.getDate().equals(other.getDate()))
            && (this.getTaskNum() == null ? other.getTaskNum() == null : this.getTaskNum().equals(other.getTaskNum()))
            && (this.getSequence() == null ? other.getSequence() == null : this.getSequence().equals(other.getSequence()))
            && (this.getNextTaskItemId() == null ? other.getNextTaskItemId() == null : this.getNextTaskItemId().equals(other.getNextTaskItemId()))
            && (this.getNextTaskItemExecuteTime() == null ? other.getNextTaskItemExecuteTime() == null : this.getNextTaskItemExecuteTime().equals(other.getNextTaskItemExecuteTime()))
            && (this.getFailTaskItemId() == null ? other.getFailTaskItemId() == null : this.getFailTaskItemId().equals(other.getFailTaskItemId()))
            && (this.getFailMessage() == null ? other.getFailMessage() == null : this.getFailMessage().equals(other.getFailMessage()))
            && (this.getCanRetry() == null ? other.getCanRetry() == null : this.getCanRetry().equals(other.getCanRetry()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpateTime() == null ? other.getUpateTime() == null : this.getUpateTime().equals(other.getUpateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getLoanInfoId() == null) ? 0 : getLoanInfoId().hashCode());
        result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
        result = prime * result + ((getDate() == null) ? 0 : getDate().hashCode());
        result = prime * result + ((getTaskNum() == null) ? 0 : getTaskNum().hashCode());
        result = prime * result + ((getSequence() == null) ? 0 : getSequence().hashCode());
        result = prime * result + ((getNextTaskItemId() == null) ? 0 : getNextTaskItemId().hashCode());
        result = prime * result + ((getNextTaskItemExecuteTime() == null) ? 0 : getNextTaskItemExecuteTime().hashCode());
        result = prime * result + ((getFailTaskItemId() == null) ? 0 : getFailTaskItemId().hashCode());
        result = prime * result + ((getFailMessage() == null) ? 0 : getFailMessage().hashCode());
        result = prime * result + ((getCanRetry() == null) ? 0 : getCanRetry().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpateTime() == null) ? 0 : getUpateTime().hashCode());
        return result;
    }
}