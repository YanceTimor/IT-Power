package com.yance520.itpower.mapper.report;

import com.yance520.itpower.mapper.base.BaseMapper;
import com.yance520.itpower.model.report.ReportModulePage;

/**
 * 报表专题信息表
 */
public interface ReportModulePageMapper extends BaseMapper<ReportModulePage> {

    /**
     * 批量删除对象
     *
     * @param code
     * @return
     */
    int deleteBatch(String[] code);

}
