package com.cana.asset.dao.mapper.gen;

import com.cana.asset.dao.po.AssetUserPrivilege;
import com.cana.asset.dao.po.AssetUserPrivilegeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AssetUserPrivilegeMapper {
    int countByExample(AssetUserPrivilegeExample example);

    int deleteByExample(AssetUserPrivilegeExample example);

    int deleteByPrimaryKey(String id);

    int insert(AssetUserPrivilege record);

    int insertSelective(AssetUserPrivilege record);

    List<AssetUserPrivilege> selectByExample(AssetUserPrivilegeExample example);

    AssetUserPrivilege selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") AssetUserPrivilege record, @Param("example") AssetUserPrivilegeExample example);

    int updateByExample(@Param("record") AssetUserPrivilege record, @Param("example") AssetUserPrivilegeExample example);

    int updateByPrimaryKeySelective(AssetUserPrivilege record);

    int updateByPrimaryKey(AssetUserPrivilege record);

    AssetUserPrivilege lockByPrimaryKey(@Param("id") String id);

    List<AssetUserPrivilege> lockByExample(AssetUserPrivilegeExample example);
}