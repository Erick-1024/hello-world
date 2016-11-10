package com.cana.asset.dao.mapper.gen;

import com.cana.asset.dao.po.ServiceAgency;
import com.cana.asset.dao.po.ServiceAgencyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ServiceAgencyMapper {
    int countByExample(ServiceAgencyExample example);

    int deleteByExample(ServiceAgencyExample example);

    int deleteByPrimaryKey(String id);

    int insert(ServiceAgency record);

    int insertSelective(ServiceAgency record);

    List<ServiceAgency> selectByExample(ServiceAgencyExample example);

    ServiceAgency selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ServiceAgency record, @Param("example") ServiceAgencyExample example);

    int updateByExample(@Param("record") ServiceAgency record, @Param("example") ServiceAgencyExample example);

    int updateByPrimaryKeySelective(ServiceAgency record);

    int updateByPrimaryKey(ServiceAgency record);

    ServiceAgency lockByPrimaryKey(@Param("id") String id);

    List<ServiceAgency> lockByExample(ServiceAgencyExample example);
}