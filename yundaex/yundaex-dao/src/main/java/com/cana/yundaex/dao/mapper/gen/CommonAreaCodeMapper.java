package com.cana.yundaex.dao.mapper.gen;

import com.cana.yundaex.dao.po.CommonAreaCode;
import com.cana.yundaex.dao.po.CommonAreaCodeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CommonAreaCodeMapper {
    int countByExample(CommonAreaCodeExample example);

    int deleteByExample(CommonAreaCodeExample example);

    int deleteByPrimaryKey(String areaCode);

    int insert(CommonAreaCode record);

    int insertSelective(CommonAreaCode record);

    List<CommonAreaCode> selectByExample(CommonAreaCodeExample example);

    CommonAreaCode selectByPrimaryKey(String areaCode);

    int updateByExampleSelective(@Param("record") CommonAreaCode record, @Param("example") CommonAreaCodeExample example);

    int updateByExample(@Param("record") CommonAreaCode record, @Param("example") CommonAreaCodeExample example);

    int updateByPrimaryKeySelective(CommonAreaCode record);

    int updateByPrimaryKey(CommonAreaCode record);
}