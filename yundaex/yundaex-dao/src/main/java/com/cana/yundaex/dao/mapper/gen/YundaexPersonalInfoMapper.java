package com.cana.yundaex.dao.mapper.gen;

import com.cana.yundaex.dao.po.YundaexPersonalInfo;
import com.cana.yundaex.dao.po.YundaexPersonalInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface YundaexPersonalInfoMapper {
    int countByExample(YundaexPersonalInfoExample example);

    int deleteByExample(YundaexPersonalInfoExample example);

    int deleteByPrimaryKey(String id);

    int insert(YundaexPersonalInfo record);

    int insertSelective(YundaexPersonalInfo record);

    List<YundaexPersonalInfo> selectByExample(YundaexPersonalInfoExample example);

    YundaexPersonalInfo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") YundaexPersonalInfo record, @Param("example") YundaexPersonalInfoExample example);

    int updateByExample(@Param("record") YundaexPersonalInfo record, @Param("example") YundaexPersonalInfoExample example);

    int updateByPrimaryKeySelective(YundaexPersonalInfo record);

    int updateByPrimaryKey(YundaexPersonalInfo record);

    YundaexPersonalInfo lockByPrimaryKey(@Param("id") String id);

    List<YundaexPersonalInfo> lockByExample(YundaexPersonalInfoExample example);
}