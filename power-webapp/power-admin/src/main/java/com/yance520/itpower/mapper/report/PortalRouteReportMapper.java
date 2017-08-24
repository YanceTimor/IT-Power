package com.yance520.itpower.mapper.report;

import com.yance520.itpower.mapper.base.BaseMapper;
import com.yance520.itpower.model.report.PortalRouteReport;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * 
 */
public interface PortalRouteReportMapper extends BaseMapper<PortalRouteReport> {

    /**
     * 根据编码查询
     * @param code
     * @return
     */
    PortalRouteReport queryObjectByCode(@Param("code") String code);

    /**
     * 产生新的编码
     *
     * @return
     */
    String getNewMaxCode();
}
