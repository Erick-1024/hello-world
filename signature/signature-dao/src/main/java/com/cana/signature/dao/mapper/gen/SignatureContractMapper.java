package com.cana.signature.dao.mapper.gen;

import com.cana.signature.dao.po.SignatureContract;
import com.cana.signature.dao.po.SignatureContractExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SignatureContractMapper {
    int countByExample(SignatureContractExample example);

    int deleteByExample(SignatureContractExample example);

    int deleteByPrimaryKey(String id);

    int insert(SignatureContract record);

    int insertSelective(SignatureContract record);

    List<SignatureContract> selectByExampleWithBLOBs(SignatureContractExample example);

    List<SignatureContract> selectByExample(SignatureContractExample example);

    SignatureContract selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SignatureContract record, @Param("example") SignatureContractExample example);

    int updateByExampleWithBLOBs(@Param("record") SignatureContract record, @Param("example") SignatureContractExample example);

    int updateByExample(@Param("record") SignatureContract record, @Param("example") SignatureContractExample example);

    int updateByPrimaryKeySelective(SignatureContract record);

    int updateByPrimaryKeyWithBLOBs(SignatureContract record);

    SignatureContract lockByPrimaryKey(@Param("id") String id);

    List<SignatureContract> lockByExample(SignatureContractExample example);
}