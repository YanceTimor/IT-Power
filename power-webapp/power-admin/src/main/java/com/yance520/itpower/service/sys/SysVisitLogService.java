package com.yance520.itpower.service.sys;

import com.yance520.itpower.model.sys.SysOperationLog;

import java.util.List;
import java.util.Map;

/**
 * Author : 杨杨
 * Date : 2017/08/21
 * Description :
 */
public interface SysVisitLogService {

    int reportTotal(Map<String,Object> map);

    List<Map<String,Object>> reportcount(Map<String,Object> map);

    SysOperationLog queryObject(Integer id);

    List<SysOperationLog> queryList(Map<String, Object> map);

    List<Map<String, Object>> queryVisit(Map<String, Object> map);

    List<Map<String, Object>> queryVisitByData(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(SysOperationLog sysOperationLog);

    void update(SysOperationLog sysOperationLog);

    void delete(Integer id);

    void deleteBatch(Integer[] ids);

}
