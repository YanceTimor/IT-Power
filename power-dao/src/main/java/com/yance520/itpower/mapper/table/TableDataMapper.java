package com.yance520.itpower.mapper.table;

import com.yance520.itpower.model.table.TableTitle;

import java.util.List;
import java.util.Map;

/**
 */
public interface TableDataMapper {
    //根据报表编码取出标题信息
    List<Map<String,Object>> getTableTileByReportCode(String reportcode);

    List<TableTitle> getTableTitle(String reportcode);
}
