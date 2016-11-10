package com.cana.asset.dao.mapper.gen;

import com.cana.asset.dao.po.ProjectDocument;
import com.cana.asset.dao.po.ProjectDocumentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectDocumentMapper {
    int countByExample(ProjectDocumentExample example);

    int deleteByExample(ProjectDocumentExample example);

    int deleteByPrimaryKey(String id);

    int insert(ProjectDocument record);

    int insertSelective(ProjectDocument record);

    List<ProjectDocument> selectByExample(ProjectDocumentExample example);

    ProjectDocument selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ProjectDocument record, @Param("example") ProjectDocumentExample example);

    int updateByExample(@Param("record") ProjectDocument record, @Param("example") ProjectDocumentExample example);

    int updateByPrimaryKeySelective(ProjectDocument record);

    int updateByPrimaryKey(ProjectDocument record);
}