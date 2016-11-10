/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.travelzen.framework.retry.dao.po;

import java.io.Serializable;
import java.util.Date;

public class RetryTask extends RetryTaskKey implements Serializable {
    /**
     *重试策略
     */
    private String retryPolicy;

    /**
     *后退策略
     */
    private String backoffPolicy;

    /**
     *最大重试次数
     */
    private Long maxAttempts;

    /**
     *当前重试次数
     */
    private Long currentAttempts;

    /**
     *任务最晚执行时间
     */
    private Date taskDeadline;

    /**
     *两次重试固定执行间隔，单位秒
     */
    private Long fixedBackoffPeriod;

    /**
     *状态
	not_begin: 未开始
	processing: 处理中
	success: 处理成功
	fail: 处理失败
     */
    private String state;

    /**
     *执行信息
     */
    private String message;

    /**
     *数据
     */
    private String data;

    /**
     *创建时间
     */
    private Date createDate;

    /**
     *更新时间
     */
    private Date updateDate;

    /**
     *业务事务版本号， 用于跨request的事务控制
     */
    private String businessTransactionVersion;

    /**
     *处理超时时间， 单位秒， 如果为0代表不超时
     */
    private Long processTimeout;

    /**
     *该任务是否已经结束
     */
    private Boolean finished;

    /**
     *是否使用单独的线程池来运行此任务, 如果为false， 则使用通用的线程池
     */
    private Boolean useIsolatedThreadPool;

    /**
     *线程池大小, 只有在use_isolated_thread_pool=true的情况下此字段才有意义
     */
    private Long threadPoolSize;

    /**
     *任务最近一次执行的开始时间
     */
    private Date startTime;

    /**
     *任务最近一次执行的结束时间
     */
    private Date endTime;

    private static final long serialVersionUID = 1L;

    /**
     *重试策略
     */
    public String getRetryPolicy() {
        return retryPolicy;
    }

    /**
     *重试策略
     */
    public void setRetryPolicy(String retryPolicy) {
        this.retryPolicy = retryPolicy == null ? null : retryPolicy.trim();
    }

    /**
     *后退策略
     */
    public String getBackoffPolicy() {
        return backoffPolicy;
    }

    /**
     *后退策略
     */
    public void setBackoffPolicy(String backoffPolicy) {
        this.backoffPolicy = backoffPolicy == null ? null : backoffPolicy.trim();
    }

    /**
     *最大重试次数
     */
    public Long getMaxAttempts() {
        return maxAttempts;
    }

    /**
     *最大重试次数
     */
    public void setMaxAttempts(Long maxAttempts) {
        this.maxAttempts = maxAttempts;
    }

    /**
     *当前重试次数
     */
    public Long getCurrentAttempts() {
        return currentAttempts;
    }

    /**
     *当前重试次数
     */
    public void setCurrentAttempts(Long currentAttempts) {
        this.currentAttempts = currentAttempts;
    }

    /**
     *任务最晚执行时间
     */
    public Date getTaskDeadline() {
        return taskDeadline;
    }

    /**
     *任务最晚执行时间
     */
    public void setTaskDeadline(Date taskDeadline) {
        this.taskDeadline = taskDeadline;
    }

    /**
     *两次重试固定执行间隔，单位秒
     */
    public Long getFixedBackoffPeriod() {
        return fixedBackoffPeriod;
    }

    /**
     *两次重试固定执行间隔，单位秒
     */
    public void setFixedBackoffPeriod(Long fixedBackoffPeriod) {
        this.fixedBackoffPeriod = fixedBackoffPeriod;
    }

    /**
     *状态
	not_begin: 未开始
	processing: 处理中
	success: 处理成功
	fail: 处理失败
     */
    public String getState() {
        return state;
    }

    /**
     *状态
	not_begin: 未开始
	processing: 处理中
	success: 处理成功
	fail: 处理失败
     */
    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    /**
     *执行信息
     */
    public String getMessage() {
        return message;
    }

    /**
     *执行信息
     */
    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    /**
     *数据
     */
    public String getData() {
        return data;
    }

    /**
     *数据
     */
    public void setData(String data) {
        this.data = data == null ? null : data.trim();
    }

    /**
     *创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     *创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     *更新时间
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     *更新时间
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     *业务事务版本号， 用于跨request的事务控制
     */
    public String getBusinessTransactionVersion() {
        return businessTransactionVersion;
    }

    /**
     *业务事务版本号， 用于跨request的事务控制
     */
    public void setBusinessTransactionVersion(String businessTransactionVersion) {
        this.businessTransactionVersion = businessTransactionVersion == null ? null : businessTransactionVersion.trim();
    }

    /**
     *处理超时时间， 单位秒， 如果为0代表不超时
     */
    public Long getProcessTimeout() {
        return processTimeout;
    }

    /**
     *处理超时时间， 单位秒， 如果为0代表不超时
     */
    public void setProcessTimeout(Long processTimeout) {
        this.processTimeout = processTimeout;
    }

    /**
     *该任务是否已经结束
     */
    public Boolean getFinished() {
        return finished;
    }

    /**
     *该任务是否已经结束
     */
    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    /**
     *是否使用单独的线程池来运行此任务, 如果为false， 则使用通用的线程池
     */
    public Boolean getUseIsolatedThreadPool() {
        return useIsolatedThreadPool;
    }

    /**
     *是否使用单独的线程池来运行此任务, 如果为false， 则使用通用的线程池
     */
    public void setUseIsolatedThreadPool(Boolean useIsolatedThreadPool) {
        this.useIsolatedThreadPool = useIsolatedThreadPool;
    }

    /**
     *线程池大小, 只有在use_isolated_thread_pool=true的情况下此字段才有意义
     */
    public Long getThreadPoolSize() {
        return threadPoolSize;
    }

    /**
     *线程池大小, 只有在use_isolated_thread_pool=true的情况下此字段才有意义
     */
    public void setThreadPoolSize(Long threadPoolSize) {
        this.threadPoolSize = threadPoolSize;
    }

    /**
     *任务最近一次执行的开始时间
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     *任务最近一次执行的开始时间
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     *任务最近一次执行的结束时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     *任务最近一次执行的结束时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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
        RetryTask other = (RetryTask) that;
        return (this.getTaskType() == null ? other.getTaskType() == null : this.getTaskType().equals(other.getTaskType()))
            && (this.getTaskId() == null ? other.getTaskId() == null : this.getTaskId().equals(other.getTaskId()))
            && (this.getRetryPolicy() == null ? other.getRetryPolicy() == null : this.getRetryPolicy().equals(other.getRetryPolicy()))
            && (this.getBackoffPolicy() == null ? other.getBackoffPolicy() == null : this.getBackoffPolicy().equals(other.getBackoffPolicy()))
            && (this.getMaxAttempts() == null ? other.getMaxAttempts() == null : this.getMaxAttempts().equals(other.getMaxAttempts()))
            && (this.getCurrentAttempts() == null ? other.getCurrentAttempts() == null : this.getCurrentAttempts().equals(other.getCurrentAttempts()))
            && (this.getTaskDeadline() == null ? other.getTaskDeadline() == null : this.getTaskDeadline().equals(other.getTaskDeadline()))
            && (this.getFixedBackoffPeriod() == null ? other.getFixedBackoffPeriod() == null : this.getFixedBackoffPeriod().equals(other.getFixedBackoffPeriod()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
            && (this.getMessage() == null ? other.getMessage() == null : this.getMessage().equals(other.getMessage()))
            && (this.getData() == null ? other.getData() == null : this.getData().equals(other.getData()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getUpdateDate() == null ? other.getUpdateDate() == null : this.getUpdateDate().equals(other.getUpdateDate()))
            && (this.getBusinessTransactionVersion() == null ? other.getBusinessTransactionVersion() == null : this.getBusinessTransactionVersion().equals(other.getBusinessTransactionVersion()))
            && (this.getProcessTimeout() == null ? other.getProcessTimeout() == null : this.getProcessTimeout().equals(other.getProcessTimeout()))
            && (this.getFinished() == null ? other.getFinished() == null : this.getFinished().equals(other.getFinished()))
            && (this.getUseIsolatedThreadPool() == null ? other.getUseIsolatedThreadPool() == null : this.getUseIsolatedThreadPool().equals(other.getUseIsolatedThreadPool()))
            && (this.getThreadPoolSize() == null ? other.getThreadPoolSize() == null : this.getThreadPoolSize().equals(other.getThreadPoolSize()))
            && (this.getStartTime() == null ? other.getStartTime() == null : this.getStartTime().equals(other.getStartTime()))
            && (this.getEndTime() == null ? other.getEndTime() == null : this.getEndTime().equals(other.getEndTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTaskType() == null) ? 0 : getTaskType().hashCode());
        result = prime * result + ((getTaskId() == null) ? 0 : getTaskId().hashCode());
        result = prime * result + ((getRetryPolicy() == null) ? 0 : getRetryPolicy().hashCode());
        result = prime * result + ((getBackoffPolicy() == null) ? 0 : getBackoffPolicy().hashCode());
        result = prime * result + ((getMaxAttempts() == null) ? 0 : getMaxAttempts().hashCode());
        result = prime * result + ((getCurrentAttempts() == null) ? 0 : getCurrentAttempts().hashCode());
        result = prime * result + ((getTaskDeadline() == null) ? 0 : getTaskDeadline().hashCode());
        result = prime * result + ((getFixedBackoffPeriod() == null) ? 0 : getFixedBackoffPeriod().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getMessage() == null) ? 0 : getMessage().hashCode());
        result = prime * result + ((getData() == null) ? 0 : getData().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getUpdateDate() == null) ? 0 : getUpdateDate().hashCode());
        result = prime * result + ((getBusinessTransactionVersion() == null) ? 0 : getBusinessTransactionVersion().hashCode());
        result = prime * result + ((getProcessTimeout() == null) ? 0 : getProcessTimeout().hashCode());
        result = prime * result + ((getFinished() == null) ? 0 : getFinished().hashCode());
        result = prime * result + ((getUseIsolatedThreadPool() == null) ? 0 : getUseIsolatedThreadPool().hashCode());
        result = prime * result + ((getThreadPoolSize() == null) ? 0 : getThreadPoolSize().hashCode());
        result = prime * result + ((getStartTime() == null) ? 0 : getStartTime().hashCode());
        result = prime * result + ((getEndTime() == null) ? 0 : getEndTime().hashCode());
        return result;
    }
}