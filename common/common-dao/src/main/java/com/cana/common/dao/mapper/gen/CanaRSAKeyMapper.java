package com.cana.common.dao.mapper.gen;

import com.cana.common.dao.po.CanaRSAKey;
import com.cana.common.dao.po.CanaRSAKeyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CanaRSAKeyMapper {
    int countByExample(CanaRSAKeyExample example);

    int deleteByExample(CanaRSAKeyExample example);

    int deleteByPrimaryKey(String institutionId);

    int insert(CanaRSAKey record);

    int insertSelective(CanaRSAKey record);

    List<CanaRSAKey> selectByExample(CanaRSAKeyExample example);

    CanaRSAKey selectByPrimaryKey(String institutionId);

    int updateByExampleSelective(@Param("record") CanaRSAKey record, @Param("example") CanaRSAKeyExample example);

    int updateByExample(@Param("record") CanaRSAKey record, @Param("example") CanaRSAKeyExample example);

    int updateByPrimaryKeySelective(CanaRSAKey record);

    int updateByPrimaryKey(CanaRSAKey record);
}