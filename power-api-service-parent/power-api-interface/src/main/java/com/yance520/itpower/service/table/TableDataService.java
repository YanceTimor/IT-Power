package com.yance520.itpower.service.table;

import com.yance520.itpower.model.table.TableTitle;

import java.util.List;
import java.util.Map;

/**
 */
public interface TableDataService {
    List<Map<String,Object>> getTableTileByReportCode(String reportcode);

    List<TableTitle> getTableTitle(String reportcode);
}
