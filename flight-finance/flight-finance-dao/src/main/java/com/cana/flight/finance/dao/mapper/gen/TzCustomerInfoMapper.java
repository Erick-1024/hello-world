package com.cana.flight.finance.dao.mapper.gen;

import com.cana.flight.finance.dao.po.TzCustomerInfo;
import com.cana.flight.finance.dao.po.TzCustomerInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TzCustomerInfoMapper {
    int countByExample(TzCustomerInfoExample example);

    int deleteByExample(TzCustomerInfoExample example);

    int deleteByPrimaryKey(Integer tzShortId);

    int insert(TzCustomerInfo record);

    int insertSelective(TzCustomerInfo record);

    List<TzCustomerInfo> selectByExample(TzCustomerInfoExample example);

    TzCustomerInfo selectByPrimaryKey(Integer tzShortId);

    int updateByExampleSelective(@Param("record") TzCustomerInfo record, @Param("example") TzCustomerInfoExample example);

    int updateByExample(@Param("record") TzCustomerInfo record, @Param("example") TzCustomerInfoExample example);

    int updateByPrimaryKeySelective(TzCustomerInfo record);

    int updateByPrimaryKey(TzCustomerInfo record);

    TzCustomerInfo lockByPrimaryKey(@Param("tzShortId") Integer tzShortId);

    List<TzCustomerInfo> lockByExample(TzCustomerInfoExample example);
}