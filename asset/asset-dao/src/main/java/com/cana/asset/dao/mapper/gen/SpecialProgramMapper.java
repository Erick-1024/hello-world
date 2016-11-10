package com.cana.asset.dao.mapper.gen;

import com.cana.asset.dao.po.SpecialProgram;
import com.cana.asset.dao.po.SpecialProgramExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SpecialProgramMapper {
    int countByExample(SpecialProgramExample example);

    int deleteByExample(SpecialProgramExample example);

    int deleteByPrimaryKey(String id);

    int insert(SpecialProgram record);

    int insertSelective(SpecialProgram record);

    List<SpecialProgram> selectByExample(SpecialProgramExample example);

    SpecialProgram selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SpecialProgram record, @Param("example") SpecialProgramExample example);

    int updateByExample(@Param("record") SpecialProgram record, @Param("example") SpecialProgramExample example);

    int updateByPrimaryKeySelective(SpecialProgram record);

    int updateByPrimaryKey(SpecialProgram record);

    SpecialProgram lockByPrimaryKey(@Param("id") String id);

    List<SpecialProgram> lockByExample(SpecialProgramExample example);
}