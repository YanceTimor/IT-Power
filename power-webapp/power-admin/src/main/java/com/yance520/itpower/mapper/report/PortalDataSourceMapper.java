package com.yance520.itpower.mapper.report;

import com.yance520.itpower.mapper.base.BaseMapper;
import com.yance520.itpower.model.report.PortalDataSource;
import org.apache.ibatis.annotations.Param;

/**
 * jdbc数据源
 */
public interface PortalDataSourceMapper extends BaseMapper<PortalDataSource> {

    /**
     * 根据编码查询
     *
     * @param code
     * @return
     */
    PortalDataSource queryObjectByCode(@Param("code") String code);

    void deleteBatchByCodes(String[] code);

    /**
     * 产生新的编码
     *
     * @return
     */
    String getNewMaxCode();
}
