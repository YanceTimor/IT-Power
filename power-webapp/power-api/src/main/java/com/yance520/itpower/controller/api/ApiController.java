package com.yance520.itpower.controller.api;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.yance520.itpower.interceptor.AuthorizationInterceptor;
import com.yance520.itpower.utils.*;
import com.yance520.itpower.utils.redis.ReportUtil;
import com.yance520.itpower.model.report.PortalReport;
import com.yance520.itpower.model.sys.SysOperationLog;
import com.yance520.itpower.service.sys.SysoperationLogService;
import com.yance520.itpower.xss.SQLFilter;
import io.swagger.annotations.*;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 报表表统一入口
 */
@Api(value = "平台统一入口", description = "报表表统一入口")
@RestController
@RequestMapping("/api/portal")
public class ApiController {

    Logger log = Logger.getLogger(this.getClass());
    @Reference
    private SysoperationLogService sysoperationLogService;
    @Autowired
    private ReportUtil reportUtil;

    /**
     * 报表存储过程报表统一入口
     *
     * @param req
     * @param response
     * @param yanceReportCustomCode 报表编码,字段名唯一，且不允许修改
     * @return,
     */
    @RequestMapping(value = "custom")
    @ResponseBody
    @ApiOperation(value = "报表查询一入口", httpMethod = "GET", notes = "报表编码yanceReportCustomCode不能为空，其他参数参考接口文档", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "yanceReportCustomCode", value = "报表编码", paramType = "path", dataType = "string"),
            @ApiImplicitParam(name = "token", value = "令牌", paramType = "header", dataType = "string")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 0, message = "请求成功"),
            @ApiResponse(code = 1, message = "请求失败"),
            @ApiResponse(code = -98, message = "验签失败"),
            @ApiResponse(code = -99, message = "未登录")
    })
    public R portalCustom(HttpServletRequest req, HttpServletResponse response, String yanceReportCustomCode) {
        String parameter = null;
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        String result = null;
        SysOperationLog log = (SysOperationLog) req.getAttribute(AuthorizationInterceptor.LOGIN_USER_OPERATION_LOG);
        log.setReportcode(yanceReportCustomCode);
        // 1.根据报表唯一编码查询报表基本信息
        PortalReport report = reportUtil.getPortalReport(yanceReportCustomCode);
        JSONObject jsonObject = null;
        String errMsg = null;
        try {
            // 2.参数处理
            parameter = HttpContextUtils.getRequestParameter(req);

            // 3.是否从第三方系统数据结果
            if (report.getExecuteType() == ConstantsUtil.ExecuteType.FROMOTHER) {
                log.setRemark("统一报表接口-第三方接口查询数据");
                // 调用外部接口获取数据
                jsonObject = reportUtil.routeResultByParam(report.getExecuteCode(), SQLFilter.sqlInject(parameter));
            } else {
                log.setRemark("统一报表接口-平台数据");
                // 4.从平台数据结果
                list = reportUtil.jdbcProListResultListMapByParam(SQLFilter.sqlInject(yanceReportCustomCode), SQLFilter.sqlInject(parameter));
            }

        } catch (Exception e) {
            log.setStatus(1);
            log.setError(StringUtils.substring(e.toString(), 0, 2000));
        } finally {
            log.setEndTime(new Date());
            sysoperationLogService.SaveLog(log);
        }

        // 返回数据集，必须固定格式
        if (report.getExecuteType() == ConstantsUtil.ExecuteType.FROMOTHER) {
            return R.restul(errMsg, jsonObject.get("data")).put("routeMsg", jsonObject.get("routeMsg"));
        } else {
            return R.restul(errMsg, list);
        }
    }

    /**
     * 导出excel统一入口
     * 测试中,待完善
     *
     * @param request
     * @param response
     * @param yanceReportCustomCode
     */
    @ApiOperation(value = "报表Excel导出统一入口", httpMethod = "GET", notes = "报表编码yanceReportCustomCode不能为空，其他参数参考接口文档")
    @RequestMapping(value = "exportExcel")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "yanceReportCustomCode", value = "报表编码", paramType = "path", dataType = "string"),
            @ApiImplicitParam(name = "name", value = "导出文件名称", paramType = "path", dataType = "string"),
            @ApiImplicitParam(name = "token", value = "令牌", paramType = "header", dataType = "string")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 0, message = "请求成功"),
            @ApiResponse(code = 1, message = "请求失败"),
            @ApiResponse(code = -98, message = "验签失败"),
            @ApiResponse(code = -99, message = "未登录")
    })
    public void exportExcel(HttpServletRequest request, HttpServletResponse response, String yanceReportCustomCode, String name) {
        name = StringUtils.isEmpty(name) ? "数据化运营平台" : name;
        String parameter = null;
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        SysOperationLog log = (SysOperationLog) request.getAttribute(AuthorizationInterceptor.LOGIN_USER_OPERATION_LOG);
        log.setReportcode(yanceReportCustomCode);

        try {
            parameter = HttpContextUtils.getRequestParameter(request);

            // 根据报表唯一编码查询报表基本信息
            PortalReport report = reportUtil.getPortalReport(yanceReportCustomCode);
            report.setCellTitleName(GzipUtils.ungzip(report.getCellTitleName()));
            //String[] cellTitleName = {"useFlag1=状态", "hrScopename=门店", "empNo=工号", "empName1=姓名", "remark2=备注"};
            String[] cellTitleName = {};
            if (!StringUtils.isEmpty(report.getCellTitleName())) {
                cellTitleName = report.getCellTitleName().split("\\n");
            }

            // 数据内容
            list = reportUtil.jdbcProListResultListMapByParam(SQLFilter.sqlInject(yanceReportCustomCode), SQLFilter.sqlInject(parameter));

            ApiExportExport export = new ApiExportExport();
            HSSFWorkbook workbook = export.export(list, cellTitleName, name);

            String filename = java.net.URLEncoder.encode(name + ".xls", "UTF-8");
            response.setContentType("application/x-msdownload");
            response.setHeader("Content-disposition", "attachment;filename=" + filename);

            OutputStream ouputStream = response.getOutputStream();
            workbook.write(ouputStream);
            ouputStream.flush();
            ouputStream.close();

        } catch (Exception e) {
            log.setStatus(1);
            log.setError(StringUtils.substring(e.toString(), 0, 2000));
        } finally {
            log.setEndTime(new Date());
            sysoperationLogService.SaveLog(log);
        }
    }

    /**
     * 获取报表标题信息
     *
     * @param request
     * @param response
     * @param yanceReportCustomCode
     */
    @RequestMapping(value = "headers")
    @ApiOperation(value = "获取报表标题信息", httpMethod = "GET", notes = "报表编码yanceReportCustomCode不能为空，其他参数参考接口文档", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "yanceReportCustomCode", value = "报表编码", paramType = "path", dataType = "string"),
            @ApiImplicitParam(name = "token", value = "令牌", paramType = "header", dataType = "string")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 0, message = "请求成功"),
            @ApiResponse(code = 1, message = "请求失败"),
            @ApiResponse(code = -98, message = "验签失败"),
            @ApiResponse(code = -99, message = "未登录")
    })
    public R headers(HttpServletRequest request, HttpServletResponse response, String yanceReportCustomCode) {
        PortalReport report = reportUtil.getPortalReport(yanceReportCustomCode);

        report.setReportHotData(GzipUtils.ungzip(report.getReportHotData()));
        report.setReportHeadersFormatConsole(GzipUtils.ungzip(report.getReportHeadersFormatConsole()));
        report.setReportOuterHtml(GzipUtils.ungzip(report.getReportOuterHtml()));

        JSONObject jsonHeadersFormatConsol = JSONObject.parseObject(report.getReportHeadersFormatConsole());
        return R.success().put("countRows", report.getReportHeadersCountRows()).put("countCols", report.getReportHeadersCountCols()).put("yanceReportCustomCode", report.getCode()).put("headers", report.getReportHeadersConsole()).put("headersFormat", jsonHeadersFormatConsol).put("outerHtml", report.getReportOuterHtml());
    }

    /**
     * 获取报表模板
     *
     * @param request
     * @param response
     * @param code
     */
    @RequestMapping(value = "reportModule")
    @ApiOperation(value = "获取报表模板", httpMethod = "GET", notes = "报表编码code不能为空，其他参数参考接口文档", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "报表编码", paramType = "path", dataType = "string"),
            @ApiImplicitParam(name = "token", value = "令牌", paramType = "header", dataType = "string")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 0, message = "请求成功"),
            @ApiResponse(code = 1, message = "请求失败"),
            @ApiResponse(code = -98, message = "验签失败"),
            @ApiResponse(code = -99, message = "未登录")
    })
    public R reportModule(HttpServletRequest request, HttpServletResponse response, String code) {
        return R.success(JSONObject.parseObject(reportUtil.getPageModule(code).getOnlineStructrue()));
    }

}
