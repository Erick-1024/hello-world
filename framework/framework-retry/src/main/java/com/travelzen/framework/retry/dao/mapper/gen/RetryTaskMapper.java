package com.travelzen.framework.retry.dao.mapper.gen;

import com.travelzen.framework.retry.dao.po.RetryTask;
import com.travelzen.framework.retry.dao.po.RetryTaskExample;
import com.travelzen.framework.retry.dao.po.RetryTaskKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RetryTaskMapper {
    int countByExample(RetryTaskExample example);

    int deleteByExample(RetryTaskExample example);

    int deleteByPrimaryKey(RetryTaskKey key);

    int insert(RetryTask record);

    int insertSelective(RetryTask record);

    List<RetryTask> selectByExample(RetryTaskExample example);

    RetryTask selectByPrimaryKey(RetryTaskKey key);

    int updateByExampleSelective(@Param("record") RetryTask record, @Param("example") RetryTaskExample example);

    int updateByExample(@Param("record") RetryTask record, @Param("example") RetryTaskExample example);

    int updateByPrimaryKeySelective(RetryTask record);

    int updateByPrimaryKey(RetryTask record);
}