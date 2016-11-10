package com.cana.flight.finance.dao.mapper.gen;

import com.cana.flight.finance.dao.po.DailyBill;
import com.cana.flight.finance.dao.po.DailyBillExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DailyBillMapper {
    int countByExample(DailyBillExample example);

    int deleteByExample(DailyBillExample example);

    int deleteByPrimaryKey(String id);

    int insert(DailyBill record);

    int insertSelective(DailyBill record);

    List<DailyBill> selectByExample(DailyBillExample example);

    DailyBill selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") DailyBill record, @Param("example") DailyBillExample example);

    int updateByExample(@Param("record") DailyBill record, @Param("example") DailyBillExample example);

    int updateByPrimaryKeySelective(DailyBill record);

    int updateByPrimaryKey(DailyBill record);
}