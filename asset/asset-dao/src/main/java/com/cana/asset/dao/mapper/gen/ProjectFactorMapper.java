package com.cana.asset.dao.mapper.gen;

import com.cana.asset.dao.po.ProjectFactor;
import com.cana.asset.dao.po.ProjectFactorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectFactorMapper {
    int countByExample(ProjectFactorExample example);

    int deleteByExample(ProjectFactorExample example);

    int deleteByPrimaryKey(String id);

    int insert(ProjectFactor record);

    int insertSelective(ProjectFactor record);

    List<ProjectFactor> selectByExample(ProjectFactorExample example);

    ProjectFactor selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ProjectFactor record, @Param("example") ProjectFactorExample example);

    int updateByExample(@Param("record") ProjectFactor record, @Param("example") ProjectFactorExample example);

    int updateByPrimaryKeySelective(ProjectFactor record);

    int updateByPrimaryKey(ProjectFactor record);

    ProjectFactor lockByPrimaryKey(@Param("id") String id);

    List<ProjectFactor> lockByExample(ProjectFactorExample example);
}