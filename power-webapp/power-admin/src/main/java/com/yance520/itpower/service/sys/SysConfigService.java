package com.yance520.itpower.service.sys;

import com.yance520.itpower.model.sys.SysConfig;

import java.util.List;
import java.util.Map;

/**
 * Author : 杨杨
 * Date : 2017/08/20
 * Description : 系统配置信息
 * 
 */
public interface SysConfigService {
	
	/**
	 * 保存配置信息
	 */
	public void save(SysConfig config);
	
	/**
	 * 更新配置信息
	 */
	public void update(SysConfig config);
	
	/**
	 * 根据key，更新value
	 */
	public void updateValueByKey(String key, String value);
	
	/**
	 * 删除配置信息
	 */
	public void deleteBatch(String[] ids);
	
	/**
	 * 获取List列表
	 */
	public List<SysConfig> queryList(Map<String, Object> map);
	/**
	 * 获取总记录数
	 */
	public int queryTotal(Map<String, Object> map);
	
	public SysConfig queryObject(Long id);

	public SysConfig queryObject(String key);
	
	/**
	 * 根据key，获取配置的value值
	 * 
	 * @param key           key
	 * @param defaultValue  缺省值
	 */
	public String getValue(String key, String defaultValue);
	
}
