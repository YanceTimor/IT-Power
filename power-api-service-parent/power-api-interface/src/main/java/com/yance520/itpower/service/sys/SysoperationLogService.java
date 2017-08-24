package com.yance520.itpower.service.sys;

import com.yance520.itpower.model.sys.SysOperationLog;

/**
 */
public interface SysoperationLogService {

    //保存用户操作日志
    Integer SaveLog(SysOperationLog log);


}
