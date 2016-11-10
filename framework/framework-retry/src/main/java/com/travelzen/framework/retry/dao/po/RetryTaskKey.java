package com.travelzen.framework.retry.dao.po;

import java.io.Serializable;

public class RetryTaskKey implements Serializable {
    /**
     *任务类型
     */
    private String taskType;

    /**
     *任务id
     */
    private String taskId;

    private static final long serialVersionUID = 1L;

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
     *任务id
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     *任务id
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId == null ? null : taskId.trim();
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
        RetryTaskKey other = (RetryTaskKey) that;
        return (this.getTaskType() == null ? other.getTaskType() == null : this.getTaskType().equals(other.getTaskType()))
            && (this.getTaskId() == null ? other.getTaskId() == null : this.getTaskId().equals(other.getTaskId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTaskType() == null) ? 0 : getTaskType().hashCode());
        result = prime * result + ((getTaskId() == null) ? 0 : getTaskId().hashCode());
        return result;
    }
}