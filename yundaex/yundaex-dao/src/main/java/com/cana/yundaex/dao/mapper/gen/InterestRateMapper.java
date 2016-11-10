package com.cana.yundaex.dao.mapper.gen;

import com.cana.yundaex.dao.po.InterestRate;
import com.cana.yundaex.dao.po.InterestRateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InterestRateMapper {
    int countByExample(InterestRateExample example);

    int deleteByExample(InterestRateExample example);

    int deleteByPrimaryKey(String id);

    int insert(InterestRate record);

    int insertSelective(InterestRate record);

    List<InterestRate> selectByExample(InterestRateExample example);

    InterestRate selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") InterestRate record, @Param("example") InterestRateExample example);

    int updateByExample(@Param("record") InterestRate record, @Param("example") InterestRateExample example);

    int updateByPrimaryKeySelective(InterestRate record);

    int updateByPrimaryKey(InterestRate record);

    InterestRate lockByPrimaryKey(@Param("id") String id);

    List<InterestRate> lockByExample(InterestRateExample example);
}