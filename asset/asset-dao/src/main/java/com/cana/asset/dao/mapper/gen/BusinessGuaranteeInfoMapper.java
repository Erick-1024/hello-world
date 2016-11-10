package com.cana.asset.dao.mapper.gen;

import com.cana.asset.dao.po.BusinessGuaranteeInfo;
import com.cana.asset.dao.po.BusinessGuaranteeInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BusinessGuaranteeInfoMapper {
    int countByExample(BusinessGuaranteeInfoExample example);

    int deleteByExample(BusinessGuaranteeInfoExample example);

    int deleteByPrimaryKey(String id);

    int insert(BusinessGuaranteeInfo record);

    int insertSelective(BusinessGuaranteeInfo record);

    List<BusinessGuaranteeInfo> selectByExample(BusinessGuaranteeInfoExample example);

    BusinessGuaranteeInfo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") BusinessGuaranteeInfo record, @Param("example") BusinessGuaranteeInfoExample example);

    int updateByExample(@Param("record") BusinessGuaranteeInfo record, @Param("example") BusinessGuaranteeInfoExample example);

    int updateByPrimaryKeySelective(BusinessGuaranteeInfo record);

    int updateByPrimaryKey(BusinessGuaranteeInfo record);

    BusinessGuaranteeInfo lockByPrimaryKey(@Param("id") String id);

    List<BusinessGuaranteeInfo> lockByExample(BusinessGuaranteeInfoExample example);
}