package com.yance520.itpower.mapper.report;

import com.yance520.itpower.mapper.base.BaseMapper;
import com.yance520.itpower.model.report.PortalReport;
import org.apache.ibatis.annotations.Param;

/**
 * 报表、数据结果集信息
 */
public interface PortalReportMapper extends BaseMapper<PortalReport> {

    /**
     * 根据编码查询
     * @param code
     * @return
     */
    PortalReport queryObjectByCode(@Param("code") String code);

    void deleteBatchByCodes(String[] codes);


    /**
     * 产生新的编码
     *
     * @return
     */
    String getNewMaxCode();
}
