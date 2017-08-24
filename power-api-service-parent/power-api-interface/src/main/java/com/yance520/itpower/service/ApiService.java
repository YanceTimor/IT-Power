package com.yance520.itpower.service;

import com.yance520.itpower.model.report.PortalDataSource;
import com.yance520.itpower.model.report.PortalExecuteSql;
import com.yance520.itpower.model.report.PortalProcedure;
import com.yance520.itpower.model.report.PortalReport;

import java.util.List;
import java.util.Map;

/**
 * api系统总接口
 * <p>
 */
public interface ApiService {

    /**
     * 通过存储过程获取数据
     *
     * @param report
     * @param portalPro
     * @param portalDataSource
     * @param parameter
     * @return
     */
    List<Map<String, Object>> getListResultListMapByPro(PortalReport report, PortalProcedure portalPro, PortalDataSource portalDataSource, String parameter);

    /**
     * 通过sql获取数据
     *
     * @param report
     * @param portalExecuteSql
     * @param portalDataSource
     * @param parameter
     * @return
     */
    List<Map<String, Object>> getListResultListMapBySql(PortalReport report, PortalExecuteSql portalExecuteSql, PortalDataSource portalDataSource, String parameter);


}
