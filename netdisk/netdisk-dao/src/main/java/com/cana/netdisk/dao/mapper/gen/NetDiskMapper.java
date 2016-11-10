package com.cana.netdisk.dao.mapper.gen;

import com.cana.netdisk.dao.po.NetDisk;
import com.cana.netdisk.dao.po.NetDiskExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NetDiskMapper {
    int countByExample(NetDiskExample example);

    int deleteByExample(NetDiskExample example);

    int deleteByPrimaryKey(String id);

    int insert(NetDisk record);

    int insertSelective(NetDisk record);

    List<NetDisk> selectByExample(NetDiskExample example);

    NetDisk selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") NetDisk record, @Param("example") NetDiskExample example);

    int updateByExample(@Param("record") NetDisk record, @Param("example") NetDiskExample example);

    int updateByPrimaryKeySelective(NetDisk record);

    int updateByPrimaryKey(NetDisk record);

    NetDisk lockByPrimaryKey(@Param("id") String id);

    List<NetDisk> lockByExample(NetDiskExample example);
}