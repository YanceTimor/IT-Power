package com.yance520.itpower.mapper.sys;

import com.yance520.itpower.mapper.base.BaseMapper;
import com.yance520.itpower.model.sys.SysConfig;
import org.apache.ibatis.annotations.Param;

/**
 * 系统配置信息
 */
public interface SysConfigMapper extends BaseMapper<SysConfig> {
	
	/**
	 * 根据key，查询value
	 */
	String queryByKey(String paramKey);
	
	/**
	 * 根据key，更新value
	 */
	int updateValueByKey(@Param("key") String key, @Param("value") String value);
	
}
