package com.yance520.itpower.mapper.report;

import com.yance520.itpower.mapper.base.BaseMapper;
import com.yance520.itpower.model.report.ReportMeasureRelation;

import java.util.List;

/**
 */
public interface ReportMeasureRelationMapper extends BaseMapper<ReportMeasureRelation> {

    /**
     * 根据报表编码获取所有列
     * @param reportcode
     * @return
     */
    List<ReportMeasureRelation> queryListByReportCode(String reportcode);

}
