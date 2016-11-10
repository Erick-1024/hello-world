package com.cana.asset.dao.mapper.gen;

import com.cana.asset.dao.po.SpecialProgramLog;
import com.cana.asset.dao.po.SpecialProgramLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SpecialProgramLogMapper {
    int countByExample(SpecialProgramLogExample example);

    int deleteByExample(SpecialProgramLogExample example);

    int deleteByPrimaryKey(String id);

    int insert(SpecialProgramLog record);

    int insertSelective(SpecialProgramLog record);

    List<SpecialProgramLog> selectByExample(SpecialProgramLogExample example);

    SpecialProgramLog selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SpecialProgramLog record, @Param("example") SpecialProgramLogExample example);

    int updateByExample(@Param("record") SpecialProgramLog record, @Param("example") SpecialProgramLogExample example);

    int updateByPrimaryKeySelective(SpecialProgramLog record);

    int updateByPrimaryKey(SpecialProgramLog record);

    SpecialProgramLog lockByPrimaryKey(@Param("id") String id);

    List<SpecialProgramLog> lockByExample(SpecialProgramLogExample example);
}