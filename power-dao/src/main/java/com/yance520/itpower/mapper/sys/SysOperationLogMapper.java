package com.yance520.itpower.mapper.sys;

import com.yance520.itpower.mapper.base.BaseMapper;
import com.yance520.itpower.model.sys.SysOperationLog;

import java.util.List;
import java.util.Map;

/**
 * 用户操作日志
 */
public interface SysOperationLogMapper extends BaseMapper<SysOperationLog> {

    int reportTotal(Map<String, Object> map);

    List<Map<String,Object>> reportcount(Map<String, Object> map);

    //保存用户操作日志
    Integer saveLog(SysOperationLog log);

    /**
     * 近30天访问日志
     * @param map
     * @return
     */
    List<Map<String, Object>> queryVisit(Map<String, Object> map);

    /**
     * 当日各时段访问日志
     * @param map
     * @return
     */
    List<Map<String, Object>> queryVisitByData(Map<String, Object> map);

}
