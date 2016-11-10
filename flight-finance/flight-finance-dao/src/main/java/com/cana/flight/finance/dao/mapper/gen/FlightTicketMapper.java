package com.cana.flight.finance.dao.mapper.gen;

import com.cana.flight.finance.dao.po.FlightTicket;
import com.cana.flight.finance.dao.po.FlightTicketExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FlightTicketMapper {
    int countByExample(FlightTicketExample example);

    int deleteByExample(FlightTicketExample example);

    int deleteByPrimaryKey(String recordId);

    int insert(FlightTicket record);

    int insertSelective(FlightTicket record);

    List<FlightTicket> selectByExample(FlightTicketExample example);

    FlightTicket selectByPrimaryKey(String recordId);

    int updateByExampleSelective(@Param("record") FlightTicket record, @Param("example") FlightTicketExample example);

    int updateByExample(@Param("record") FlightTicket record, @Param("example") FlightTicketExample example);

    int updateByPrimaryKeySelective(FlightTicket record);

    int updateByPrimaryKey(FlightTicket record);
}