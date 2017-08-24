package com.yance520.itpower.mapper.report;

import com.yance520.itpower.mapper.base.BaseMapper;
import com.yance520.itpower.model.report.PortalExecuteSql;
import org.apache.ibatis.annotations.Param;

/**
 * 执行的sql语句
 */
public interface PortalExecuteSqlMapper extends BaseMapper<PortalExecuteSql> {

    /**
     * 根据编码查询
     *
     * @param sqlcode sql语句唯一编码
     * @return
     */
    PortalExecuteSql queryObjectBySqlcode(@Param("sqlcode") String sqlcode);

    int deleteBatchBySqlcodes(String[] sqlcods);

    /**
     * 产生新的编码
     *
     * @return
     */
    String getNewMaxCode();

}
