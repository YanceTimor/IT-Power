package com.yance520.itpower.service.impl.table;

import com.yance520.itpower.mapper.table.TableDataMapper;
import com.yance520.itpower.model.table.TableTitle;
import com.yance520.itpower.service.table.TableDataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**前端获取报表service
 */
@Service("tableDataService")
public class TableDataServiceImpl implements TableDataService {
    @Resource
    private TableDataMapper tableDataMapper;
    @Override
    public List<Map<String, Object>> getTableTileByReportCode(String reportcode) {
        return tableDataMapper.getTableTileByReportCode(reportcode);
    }

    @Override
    public List<TableTitle> getTableTitle(String reportcode) {
        return tableDataMapper.getTableTitle(reportcode);
    }
}
