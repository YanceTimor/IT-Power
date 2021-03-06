package com.yance520.itpower.service.data;

import com.yance520.itpower.model.report.PortalDataSource;

import java.util.List;
import java.util.Map;

/**
 * 数据库基础操作
 * <p>
 */
public interface ApiDataBaseSqlService {

    /**
     * 通过存储过程查询数据
     *
     * @param sql
     * @param portalDataSource
     * @return
     */
    public List<Map<String, Object>> queryCallPro(String sql, PortalDataSource portalDataSource);


    /**
     * 通过sql查询
     *
     * @param sql
     * @param portalDataSource
     * @return
     */
    public List<Map<String, Object>> queryExecuteSql(String sql, PortalDataSource portalDataSource);

    /**
     * 插入数据
     *
     * @param sql
     * @param portalDataSource
     */
    public void insertTable(String sql, PortalDataSource portalDataSource);

    /**
     * 插入数据
     *
     * @param sql
     * @param portalDataSource
     */
    public void updateTable(String sql, PortalDataSource portalDataSource);

}
