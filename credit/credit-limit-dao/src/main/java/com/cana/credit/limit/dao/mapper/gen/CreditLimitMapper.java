package com.cana.credit.limit.dao.mapper.gen;

import com.cana.credit.limit.dao.po.CreditLimit;
import com.cana.credit.limit.dao.po.CreditLimitExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CreditLimitMapper {
    int countByExample(CreditLimitExample example);

    int deleteByExample(CreditLimitExample example);

    int deleteByPrimaryKey(String id);

    int insert(CreditLimit record);

    int insertSelective(CreditLimit record);

    List<CreditLimit> selectByExample(CreditLimitExample example);

    CreditLimit selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CreditLimit record, @Param("example") CreditLimitExample example);

    int updateByExample(@Param("record") CreditLimit record, @Param("example") CreditLimitExample example);

    int updateByPrimaryKeySelective(CreditLimit record);

    int updateByPrimaryKey(CreditLimit record);

    CreditLimit lockByPrimaryKey(@Param("id") String id);

    List<CreditLimit> lockByExample(CreditLimitExample example);
}