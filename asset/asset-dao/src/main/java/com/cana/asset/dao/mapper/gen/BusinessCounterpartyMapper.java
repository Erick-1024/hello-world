package com.cana.asset.dao.mapper.gen;

import com.cana.asset.dao.po.BusinessCounterparty;
import com.cana.asset.dao.po.BusinessCounterpartyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BusinessCounterpartyMapper {
    int countByExample(BusinessCounterpartyExample example);

    int deleteByExample(BusinessCounterpartyExample example);

    int deleteByPrimaryKey(String id);

    int insert(BusinessCounterparty record);

    int insertSelective(BusinessCounterparty record);

    List<BusinessCounterparty> selectByExample(BusinessCounterpartyExample example);

    BusinessCounterparty selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") BusinessCounterparty record, @Param("example") BusinessCounterpartyExample example);

    int updateByExample(@Param("record") BusinessCounterparty record, @Param("example") BusinessCounterpartyExample example);

    int updateByPrimaryKeySelective(BusinessCounterparty record);

    int updateByPrimaryKey(BusinessCounterparty record);

    BusinessCounterparty lockByPrimaryKey(@Param("id") String id);

    List<BusinessCounterparty> lockByExample(BusinessCounterpartyExample example);
}