package com.travelzen.framework.dao.mybatis.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface IBatchSequenceMapper {
	
	@Select("select common_batch_sequence(#{sequenceName}, #{allotment})")
	public long getNextSeq(@Param("sequenceName") String sequenceName, @Param("allotment") int allotment);

}
