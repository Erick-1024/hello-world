package com.cana.asset.dao.mapper.gen;

import com.cana.asset.dao.po.BusinessBasicInfo;
import com.cana.asset.dao.po.BusinessBasicInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BusinessBasicInfoMapper {
    int countByExample(BusinessBasicInfoExample example);

    int deleteByExample(BusinessBasicInfoExample example);

    int deleteByPrimaryKey(String id);

    int insert(BusinessBasicInfo record);

    int insertSelective(BusinessBasicInfo record);

    List<BusinessBasicInfo> selectByExample(BusinessBasicInfoExample example);

    BusinessBasicInfo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") BusinessBasicInfo record, @Param("example") BusinessBasicInfoExample example);

    int updateByExample(@Param("record") BusinessBasicInfo record, @Param("example") BusinessBasicInfoExample example);

    int updateByPrimaryKeySelective(BusinessBasicInfo record);

    int updateByPrimaryKey(BusinessBasicInfo record);

    BusinessBasicInfo lockByPrimaryKey(@Param("id") String id);

    List<BusinessBasicInfo> lockByExample(BusinessBasicInfoExample example);
}