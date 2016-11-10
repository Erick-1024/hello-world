package com.cana.yundaex.dao.mapper.gen;

import com.cana.yundaex.dao.po.YundaexTstationInfo;
import com.cana.yundaex.dao.po.YundaexTstationInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface YundaexTstationInfoMapper {
    int countByExample(YundaexTstationInfoExample example);

    int deleteByExample(YundaexTstationInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(YundaexTstationInfo record);

    int insertSelective(YundaexTstationInfo record);

    List<YundaexTstationInfo> selectByExample(YundaexTstationInfoExample example);

    YundaexTstationInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") YundaexTstationInfo record, @Param("example") YundaexTstationInfoExample example);

    int updateByExample(@Param("record") YundaexTstationInfo record, @Param("example") YundaexTstationInfoExample example);

    int updateByPrimaryKeySelective(YundaexTstationInfo record);

    int updateByPrimaryKey(YundaexTstationInfo record);
}