package com.yance520.itpower.mapper.report;

import com.yance520.itpower.mapper.base.BaseMapper;
import com.yance520.itpower.model.report.PortalOpenapiReport;

/**
 * 
 */
public interface PortalOpenapiReportMapper extends BaseMapper<PortalOpenapiReport> {
    /**
     * 产生新的编码
     *
     * @return
     */
    String getNewMaxCode();
}
