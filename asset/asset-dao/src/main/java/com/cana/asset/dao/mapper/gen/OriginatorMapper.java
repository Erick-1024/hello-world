package com.cana.asset.dao.mapper.gen;

import com.cana.asset.dao.po.Originator;
import com.cana.asset.dao.po.OriginatorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OriginatorMapper {
    int countByExample(OriginatorExample example);

    int deleteByExample(OriginatorExample example);

    int deleteByPrimaryKey(String id);

    int insert(Originator record);

    int insertSelective(Originator record);

    List<Originator> selectByExample(OriginatorExample example);

    Originator selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Originator record, @Param("example") OriginatorExample example);

    int updateByExample(@Param("record") Originator record, @Param("example") OriginatorExample example);

    int updateByPrimaryKeySelective(Originator record);

    int updateByPrimaryKey(Originator record);

    Originator lockByPrimaryKey(@Param("id") String id);

    List<Originator> lockByExample(OriginatorExample example);
}