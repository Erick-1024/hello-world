package com.cana.setting.dao.mapper.gen;

import com.cana.setting.dao.po.CanaCalendar;
import com.cana.setting.dao.po.CanaCalendarExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CanaCalendarMapper {
    int countByExample(CanaCalendarExample example);

    int deleteByExample(CanaCalendarExample example);

    int deleteByPrimaryKey(String date);

    int insert(CanaCalendar record);

    int insertSelective(CanaCalendar record);

    List<CanaCalendar> selectByExample(CanaCalendarExample example);

    CanaCalendar selectByPrimaryKey(String date);

    int updateByExampleSelective(@Param("record") CanaCalendar record, @Param("example") CanaCalendarExample example);

    int updateByExample(@Param("record") CanaCalendar record, @Param("example") CanaCalendarExample example);

    int updateByPrimaryKeySelective(CanaCalendar record);

    int updateByPrimaryKey(CanaCalendar record);

    CanaCalendar lockByPrimaryKey(@Param("date") String date);

    List<CanaCalendar> lockByExample(CanaCalendarExample example);
}