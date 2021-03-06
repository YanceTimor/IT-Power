package com.yance520.itpower.service.sys;


import com.yance520.itpower.model.sys.SysLog;

import java.util.List;
import java.util.Map;

/**
 * Author : 杨杨
 * Date : 2017/08/20
 * Description : 系统日志
 * 
 */
public interface SysLogService {
	
	SysLog queryObject(Long id);
	
	List<SysLog> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SysLog sysLog);
	
	void update(SysLog sysLog);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
