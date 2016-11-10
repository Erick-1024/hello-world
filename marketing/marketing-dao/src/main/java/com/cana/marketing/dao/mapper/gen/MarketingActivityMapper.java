package com.cana.marketing.dao.mapper.gen;

import com.cana.marketing.dao.po.MarketingActivity;
import com.cana.marketing.dao.po.MarketingActivityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MarketingActivityMapper {
    int countByExample(MarketingActivityExample example);

    int deleteByExample(MarketingActivityExample example);

    int deleteByPrimaryKey(String id);

    int insert(MarketingActivity record);

    int insertSelective(MarketingActivity record);

    List<MarketingActivity> selectByExample(MarketingActivityExample example);

    MarketingActivity selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") MarketingActivity record, @Param("example") MarketingActivityExample example);

    int updateByExample(@Param("record") MarketingActivity record, @Param("example") MarketingActivityExample example);

    int updateByPrimaryKeySelective(MarketingActivity record);

    int updateByPrimaryKey(MarketingActivity record);

    MarketingActivity lockByPrimaryKey(@Param("id") String id);

    List<MarketingActivity> lockByExample(MarketingActivityExample example);
}