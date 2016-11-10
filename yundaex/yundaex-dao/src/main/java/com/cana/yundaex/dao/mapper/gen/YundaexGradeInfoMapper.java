package com.cana.yundaex.dao.mapper.gen;

import com.cana.yundaex.dao.po.YundaexGradeInfo;
import com.cana.yundaex.dao.po.YundaexGradeInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface YundaexGradeInfoMapper {
    int countByExample(YundaexGradeInfoExample example);

    int deleteByExample(YundaexGradeInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(YundaexGradeInfo record);

    int insertSelective(YundaexGradeInfo record);

    List<YundaexGradeInfo> selectByExample(YundaexGradeInfoExample example);

    YundaexGradeInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") YundaexGradeInfo record, @Param("example") YundaexGradeInfoExample example);

    int updateByExample(@Param("record") YundaexGradeInfo record, @Param("example") YundaexGradeInfoExample example);

    int updateByPrimaryKeySelective(YundaexGradeInfo record);

    int updateByPrimaryKey(YundaexGradeInfo record);
}