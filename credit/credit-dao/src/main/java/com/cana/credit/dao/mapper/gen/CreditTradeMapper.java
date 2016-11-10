package com.cana.credit.dao.mapper.gen;

import com.cana.credit.dao.po.CreditTrade;
import com.cana.credit.dao.po.CreditTradeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CreditTradeMapper {
    int countByExample(CreditTradeExample example);

    int deleteByExample(CreditTradeExample example);

    int deleteByPrimaryKey(String id);

    int insert(CreditTrade record);

    int insertSelective(CreditTrade record);

    List<CreditTrade> selectByExample(CreditTradeExample example);

    CreditTrade selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CreditTrade record, @Param("example") CreditTradeExample example);

    int updateByExample(@Param("record") CreditTrade record, @Param("example") CreditTradeExample example);

    int updateByPrimaryKeySelective(CreditTrade record);

    int updateByPrimaryKey(CreditTrade record);

    CreditTrade lockByPrimaryKey(@Param("id") String id);

    List<CreditTrade> lockByExample(CreditTradeExample example);
}