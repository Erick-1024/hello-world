package com.cana.yundaex.dao.mapper.gen;

import com.cana.yundaex.dao.po.YundaexCustomerGrade;
import com.cana.yundaex.dao.po.YundaexCustomerGradeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface YundaexCustomerGradeMapper {
    int countByExample(YundaexCustomerGradeExample example);

    int deleteByExample(YundaexCustomerGradeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(YundaexCustomerGrade record);

    int insertSelective(YundaexCustomerGrade record);

    List<YundaexCustomerGrade> selectByExample(YundaexCustomerGradeExample example);

    YundaexCustomerGrade selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") YundaexCustomerGrade record, @Param("example") YundaexCustomerGradeExample example);

    int updateByExample(@Param("record") YundaexCustomerGrade record, @Param("example") YundaexCustomerGradeExample example);

    int updateByPrimaryKeySelective(YundaexCustomerGrade record);

    int updateByPrimaryKey(YundaexCustomerGrade record);
}