package com.cana.yundaex.dao.mapper.gen;

import com.cana.yundaex.dao.po.YundaexCompositeCost;
import com.cana.yundaex.dao.po.YundaexCompositeCostExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface YundaexCompositeCostMapper {
    int countByExample(YundaexCompositeCostExample example);

    int deleteByExample(YundaexCompositeCostExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(YundaexCompositeCost record);

    int insertSelective(YundaexCompositeCost record);

    List<YundaexCompositeCost> selectByExample(YundaexCompositeCostExample example);

    YundaexCompositeCost selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") YundaexCompositeCost record, @Param("example") YundaexCompositeCostExample example);

    int updateByExample(@Param("record") YundaexCompositeCost record, @Param("example") YundaexCompositeCostExample example);

    int updateByPrimaryKeySelective(YundaexCompositeCost record);

    int updateByPrimaryKey(YundaexCompositeCost record);
}