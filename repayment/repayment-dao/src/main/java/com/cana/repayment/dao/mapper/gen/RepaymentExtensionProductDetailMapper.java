package com.cana.repayment.dao.mapper.gen;

import com.cana.repayment.dao.po.RepaymentExtensionProductDetail;
import com.cana.repayment.dao.po.RepaymentExtensionProductDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RepaymentExtensionProductDetailMapper {
    int countByExample(RepaymentExtensionProductDetailExample example);

    int deleteByExample(RepaymentExtensionProductDetailExample example);

    int deleteByPrimaryKey(String id);

    int insert(RepaymentExtensionProductDetail record);

    int insertSelective(RepaymentExtensionProductDetail record);

    List<RepaymentExtensionProductDetail> selectByExample(RepaymentExtensionProductDetailExample example);

    RepaymentExtensionProductDetail selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") RepaymentExtensionProductDetail record, @Param("example") RepaymentExtensionProductDetailExample example);

    int updateByExample(@Param("record") RepaymentExtensionProductDetail record, @Param("example") RepaymentExtensionProductDetailExample example);

    int updateByPrimaryKeySelective(RepaymentExtensionProductDetail record);

    int updateByPrimaryKey(RepaymentExtensionProductDetail record);

    RepaymentExtensionProductDetail lockByPrimaryKey(@Param("id") String id);

    List<RepaymentExtensionProductDetail> lockByExample(RepaymentExtensionProductDetailExample example);
}