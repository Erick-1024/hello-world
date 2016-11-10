package com.cana.asset.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface IAssetCustomerMapper {

    /**
     * 返回保理商维护的所有客户id
     * @param factorId
     * @return
     */
    @Select("SELECT trim(id) as customer_id FROM asset_customer where factor_id = #{factorId}")
    public List<String> getCustomerIdListByFactorId(@Param("factorId") String factorId);
    
}
