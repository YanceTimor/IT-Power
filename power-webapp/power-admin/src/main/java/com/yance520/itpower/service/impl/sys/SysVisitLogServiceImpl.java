package com.yance520.itpower.service.impl.sys;

import com.yance520.itpower.mapper.sys.SysOperationLogMapper;
import com.yance520.itpower.model.sys.SysOperationLog;
import com.yance520.itpower.service.sys.SysVisitLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("sysVisitLogService")
public class SysVisitLogServiceImpl implements SysVisitLogService {
	@Autowired
	private SysOperationLogMapper sysOperationLogMapper;

	@Override
	public int reportTotal(Map<String, Object> map) {
		return sysOperationLogMapper.reportTotal(map);
	}

	@Override
	public List<Map<String, Object>> reportcount(Map<String, Object> map) {
		return sysOperationLogMapper.reportcount(map);
	}

	@Override
	public SysOperationLog queryObject(Integer id){
		return sysOperationLogMapper.queryObject(id);
	}
	
	@Override
	public List<SysOperationLog> queryList(Map<String, Object> map){
		return sysOperationLogMapper.queryList(map);
	}

	@Override
	public List<Map<String, Object>> queryVisit(Map<String, Object> map) {
		return sysOperationLogMapper.queryVisit(map);
	}

	@Override
	public List<Map<String, Object>> queryVisitByData(Map<String, Object> map) {
		return sysOperationLogMapper.queryVisitByData(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map){
		return sysOperationLogMapper.queryTotal(map);
	}
	
	@Override
	public void save(SysOperationLog sysOperationLog){
		sysOperationLogMapper.save(sysOperationLog);
	}
	
	@Override
	public void update(SysOperationLog sysOperationLog){
		sysOperationLogMapper.update(sysOperationLog);
	}
	
	@Override
	public void delete(Integer id){
		sysOperationLogMapper.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		sysOperationLogMapper.deleteBatch(ids);
	}
	
}
