package com.yance520.itpower.service.sys;

import com.yance520.itpower.model.sys.SysStartLog;

import java.util.List;
import java.util.Map;

/**
 * Author : 杨杨
 * Date : 2017/08/20
 * Description : 系统启动日志记录
 */
public interface SysStartLogService {
	
	SysStartLog queryObject(Long id);
	
	List<SysStartLog> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SysStartLog sysStartLog);
	
	void update(SysStartLog sysStartLog);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
