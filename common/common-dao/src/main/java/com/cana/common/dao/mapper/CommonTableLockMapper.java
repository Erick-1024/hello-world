package com.cana.common.dao.mapper;

import com.cana.common.dao.po.Properties;


/**
 * 数据库表加锁接口select * for update 加锁的时候注明参数和意图
 *
 */
public interface CommonTableLockMapper {
	/**
	 * 通过name给common_properties表加锁
	 * 
	 * @param name
	 * @return
	 */
	public Properties lockCommonPropertiesByName(String name);
}
