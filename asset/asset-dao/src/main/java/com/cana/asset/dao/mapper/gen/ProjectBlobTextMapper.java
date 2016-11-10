package com.cana.asset.dao.mapper.gen;

import com.cana.asset.dao.po.ProjectBlobText;
import com.cana.asset.dao.po.ProjectBlobTextExample;
import com.cana.asset.dao.po.ProjectBlobTextWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectBlobTextMapper {
    int countByExample(ProjectBlobTextExample example);

    int deleteByExample(ProjectBlobTextExample example);

    int deleteByPrimaryKey(String projectId);

    int insert(ProjectBlobTextWithBLOBs record);

    int insertSelective(ProjectBlobTextWithBLOBs record);

    List<ProjectBlobTextWithBLOBs> selectByExampleWithBLOBs(ProjectBlobTextExample example);

    List<ProjectBlobText> selectByExample(ProjectBlobTextExample example);

    ProjectBlobTextWithBLOBs selectByPrimaryKey(String projectId);

    int updateByExampleSelective(@Param("record") ProjectBlobTextWithBLOBs record, @Param("example") ProjectBlobTextExample example);

    int updateByExampleWithBLOBs(@Param("record") ProjectBlobTextWithBLOBs record, @Param("example") ProjectBlobTextExample example);

    int updateByExample(@Param("record") ProjectBlobText record, @Param("example") ProjectBlobTextExample example);

    int updateByPrimaryKeySelective(ProjectBlobTextWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ProjectBlobTextWithBLOBs record);

    int updateByPrimaryKey(ProjectBlobText record);

    ProjectBlobText lockByPrimaryKey(@Param("projectId") String projectId);

    List<ProjectBlobText> lockByExample(ProjectBlobTextExample example);
}