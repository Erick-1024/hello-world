package com.cana.yundaex.dao.mapper.gen;

import com.cana.yundaex.dao.po.YundaexGradeModel;
import com.cana.yundaex.dao.po.YundaexGradeModelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface YundaexGradeModelMapper {
    int countByExample(YundaexGradeModelExample example);

    int deleteByExample(YundaexGradeModelExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(YundaexGradeModel record);

    int insertSelective(YundaexGradeModel record);

    List<YundaexGradeModel> selectByExample(YundaexGradeModelExample example);

    YundaexGradeModel selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") YundaexGradeModel record, @Param("example") YundaexGradeModelExample example);

    int updateByExample(@Param("record") YundaexGradeModel record, @Param("example") YundaexGradeModelExample example);

    int updateByPrimaryKeySelective(YundaexGradeModel record);

    int updateByPrimaryKey(YundaexGradeModel record);
}