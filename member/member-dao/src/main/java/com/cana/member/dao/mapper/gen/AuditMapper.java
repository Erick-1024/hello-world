package com.cana.member.dao.mapper.gen;

import com.cana.member.dao.po.Audit;
import com.cana.member.dao.po.AuditExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AuditMapper {
    int countByExample(AuditExample example);

    int deleteByExample(AuditExample example);

    int deleteByPrimaryKey(String id);

    int insert(Audit record);

    int insertSelective(Audit record);

    List<Audit> selectByExample(AuditExample example);

    Audit selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Audit record, @Param("example") AuditExample example);

    int updateByExample(@Param("record") Audit record, @Param("example") AuditExample example);

    int updateByPrimaryKeySelective(Audit record);

    int updateByPrimaryKey(Audit record);
}