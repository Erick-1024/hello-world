package com.cana.asset.dao.mapper.gen;

import com.cana.asset.dao.po.EnterpriseInfo;
import com.cana.asset.dao.po.EnterpriseInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EnterpriseInfoMapper {
    int countByExample(EnterpriseInfoExample example);

    int deleteByExample(EnterpriseInfoExample example);

    int deleteByPrimaryKey(String id);

    int insert(EnterpriseInfo record);

    int insertSelective(EnterpriseInfo record);

    List<EnterpriseInfo> selectByExample(EnterpriseInfoExample example);

    EnterpriseInfo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") EnterpriseInfo record, @Param("example") EnterpriseInfoExample example);

    int updateByExample(@Param("record") EnterpriseInfo record, @Param("example") EnterpriseInfoExample example);

    int updateByPrimaryKeySelective(EnterpriseInfo record);

    int updateByPrimaryKey(EnterpriseInfo record);

    EnterpriseInfo lockByPrimaryKey(@Param("id") String id);

    List<EnterpriseInfo> lockByExample(EnterpriseInfoExample example);
}