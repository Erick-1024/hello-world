package com.travelzen.framework.retry.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.travelzen.framework.retry.dao.po.RetryTask;

public interface RetryTaskCustomMapper {

    List<RetryTask> getOldestRetryTask(@Param("taskTypes")List<String> taskTypes, @Param("top")Integer top);

}