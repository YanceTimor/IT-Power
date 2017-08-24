package com.yance520.itpower.service.impl.sys;

import com.yance520.itpower.mapper.sys.SysOperationLogMapper;
import com.yance520.itpower.model.sys.SysOperationLog;
import com.yance520.itpower.service.sys.SysoperationLogService;

import javax.annotation.Resource;

/**
 */
public class SysoperationLogServiceImpl implements SysoperationLogService {

    @Resource
    private SysOperationLogMapper sysOperationLogMapper;

    @Override
    public Integer SaveLog(SysOperationLog log) {
        return sysOperationLogMapper.saveLog(log);
    }
}
