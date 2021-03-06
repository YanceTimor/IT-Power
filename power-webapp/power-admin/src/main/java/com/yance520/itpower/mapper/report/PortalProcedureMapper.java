package com.yance520.itpower.mapper.report;

import com.yance520.itpower.mapper.base.BaseMapper;
import com.yance520.itpower.model.report.PortalProcedure;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 通用调度存储过程
 */
public interface PortalProcedureMapper extends BaseMapper<PortalProcedure> {

    /**
     * 批量删除对象
     *
     * @param procodes
     * @return
     */
    int deleteBatchByProcodes(String[] procodes);

    /**
     * 根据编码查询
     *
     * @param procode
     * @return
     */
    PortalProcedure queryObjectByProcode(@Param("procode") String procode);

    /**
     * 调用存储过程
     * parameter 如：aa,bb,cc
     *
     * @return
     */
    Map<String, Object> callProResultMapByParam(@Param("proname") String proname, @Param("parameter") String parameter);


    /**
     * 调用存储过程
     * parameter 如：aa,bb,cc
     *
     * @return
     */
    List<Map> callProListResultListMapByParam(@Param("proname") String proname, @Param("parameter") String parameter);

    /**
     * 产生新的编码
     *
     * @return
     */
    String getNewMaxCode();

}
