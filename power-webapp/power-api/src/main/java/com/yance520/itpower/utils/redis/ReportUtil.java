package com.yance520.itpower.utils.redis;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.yance520.itpower.model.report.*;
import com.yance520.itpower.service.ApiService;
import com.yance520.itpower.utils.*;
import com.yance520.itpower.utils.report.columns.HttpMethodUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 */
@Component("reportUtil")
public class ReportUtil {

    @Reference
    private ApiService apiService;
//    @Autowired
//    private RedisBizUtilApi redisBizUtilApi;

    /**
     * 获取报表数据
     *
     * @param yanceReportCustomCode
     * @param parameter
     * @return
     */
    public List<Map<String, Object>> jdbcProListResultListMapByParam(String yanceReportCustomCode, String parameter) {
        // 根据报表唯一编码查询报表基本信息
        PortalReport report = getPortalReport(yanceReportCustomCode);
        List<Map<String, Object>> dataList = null;
        PortalDataSource portalDataSource = null;
        if (report.getExecuteType() == ConstantsUtil.ExecuteType.PROCEDURE) {
            // 储存过程方式
            PortalProcedure portalPro = getPortalProcedure(report.getExecuteCode());
            portalDataSource = getPortalDataSource(portalPro.getDataSourceCode());
            dataList = apiService.getListResultListMapByPro(report, portalPro, portalDataSource, parameter);
        } else if (report.getExecuteType() == ConstantsUtil.ExecuteType.EXECUTESQL) {
            // sql语句方式
            PortalExecuteSql portalExecuteSql = getPortalExecuteSql(report.getExecuteCode());
            portalDataSource = getPortalDataSource(portalExecuteSql.getDataSourceCode());
            dataList = apiService.getListResultListMapBySql(report, portalExecuteSql, portalDataSource, parameter);
        }
        return dataList;
    }

    /**
     * 获取业务报表标题信息
     *
     * @param yanceReportCustomCode
     * @return
     */
    public JSONObject getReportColumns(String yanceReportCustomCode) {
        if (StringUtils.isEmpty(yanceReportCustomCode)) {
            throw new RRException("报表编码不能为空");
        }
        String columnsJson = "";//redisBizUtilApi.getReportColumns(yanceReportCustomCode);
        if (StringUtils.isEmpty(columnsJson)) {
            throw new RRException("未找到报表标题定义");
        }
        JSONObject jsonObject = null;
        try {
            jsonObject = JSONObject.parseObject(columnsJson);
            return jsonObject;
        } catch (Exception e) {
            throw new RRException("报表标题JSON转换异常");
        }
    }

    /**
     * 获取业务报表信息
     *
     * @param yanceReportCustomCode
     * @return
     */
    public PortalReport getPortalReport(String yanceReportCustomCode) {
        if (StringUtils.isEmpty(yanceReportCustomCode)) {
            throw new RRException("报表编码不能为空");
        }
        String reportJson ="";// redisBizUtilApi.getPortalReport(yanceReportCustomCode);
        if (StringUtils.isEmpty(reportJson)) {
            throw new RRException("报表编码 " + yanceReportCustomCode + "无效");
        }
        PortalReport report = JSONObject.parseObject(reportJson, PortalReport.class);
        if (report == null || StringUtils.isEmpty(report.getExecuteCode())) {
            throw new RRException("报表编码:" + yanceReportCustomCode + "无效，或未指定ExecuteCode");
        }
        return report;
    }

    /**
     * 获取业务报表模板
     *
     * @param code
     * @return
     */
    public ReportModulePage getPageModule(String code) {
        if (StringUtils.isEmpty(code)) {
            throw new RRException("模板编码不能为空");
        }
        String pageJson =""; //redisBizUtilApi.getPageModule(code);
        if (StringUtils.isEmpty(pageJson)) {
            throw new RRException("模板编码 " + code + "无效");
        }
        ReportModulePage reportModulePage = JSONObject.parseObject(pageJson, ReportModulePage.class);
        if (reportModulePage == null || StringUtils.isEmpty(reportModulePage.getCode())) {
            throw new RRException("模板编码:" + code + "无效，或未指定code");
        }
        reportModulePage.setDesignStructrue(GzipUtils.ungzip(reportModulePage.getDesignStructrue()));
        reportModulePage.setOnlineStructrue(GzipUtils.ungzip(reportModulePage.getOnlineStructrue()));
        return reportModulePage;
    }

    /**
     * 获取存储过程信息
     *
     * @return
     */
    private PortalProcedure getPortalProcedure(String executeCode) {
        String executeJson ="";// redisBizUtilApi.getPortalProcedure(executeCode);
        if (StringUtils.isEmpty(executeJson)) {
            throw new RRException("执行的存储过程不存在");
        }
        PortalProcedure portalPro = JSONObject.parseObject(executeJson, PortalProcedure.class);
        if (portalPro == null || StringUtils.isEmpty(portalPro.getDataSourceCode())) {
            throw new RRException("执行的存储过程不存在，或未指定DataSourceCode");
        }
        return portalPro;
    }

    /**
     * 获取执行sqld的信息
     *
     * @return
     */
    private PortalExecuteSql getPortalExecuteSql(String executeCode) {
        String executeJson = "";//redisBizUtilApi.getPortalExecuteSql(executeCode);
        if (StringUtils.isEmpty(executeJson)) {
            throw new RRException("执行的SQL不存在");
        }
        PortalExecuteSql portalExecuteSql = JSONObject.parseObject(executeJson, PortalExecuteSql.class);
        if (portalExecuteSql == null || StringUtils.isEmpty(portalExecuteSql.getDataSourceCode())) {
            throw new RRException("执行的SQL不存在，或未指定DataSourceCode");
        }
        return portalExecuteSql;
    }

    /**
     * 获取数据源
     *
     * @param dataSourceCode
     * @return
     */
    public PortalDataSource getPortalDataSource(String dataSourceCode) {
        if (StringUtils.isEmpty(dataSourceCode)) {
            throw new RRException("执行语句未指定数据源");
        }
        String portalDataSourceJson =""; //redisBizUtilApi.getPortalDataSource(dataSourceCode);
        if (StringUtils.isEmpty(portalDataSourceJson)) {
            throw new RRException("数据源" + dataSourceCode + "不存在");
        }
        PortalDataSource portalDataSource = JSONObject.parseObject(portalDataSourceJson, PortalDataSource.class);
        if (portalDataSource == null) {
            throw new RRException("数据源" + dataSourceCode + "不存在");
        }
        return portalDataSource;
    }

    /**
     * 获取路由业务报表信息
     *
     * @param yanceReportCustomCode
     * @return
     */
    public PortalRouteReport getPortalRouteReport(String yanceReportCustomCode) {
        if (StringUtils.isEmpty(yanceReportCustomCode)) {
            throw new RRException("报表编码不能为空");
        }
        String reportJson = "";//redisBizUtilApi.getPortalRouteReport(yanceReportCustomCode);
        if (StringUtils.isEmpty(reportJson)) {
            throw new RRException("报表编码 " + yanceReportCustomCode + "无效");
        }
        PortalRouteReport report = JSONObject.parseObject(reportJson, PortalRouteReport.class);
        if (report == null || StringUtils.isEmpty(report.getCode())) {
            throw new RRException("报表编码:" + yanceReportCustomCode + "无效，或未指定yanceReportCustomCode");
        }
        return report;
    }


    /**
     * 获取路由报表数据
     *
     * @param provideCode
     * @param parameter
     * @return
     */
    public JSONObject routeResultByParam(String provideCode, String parameter) {
        String errMsg = null;
        if (StringUtils.isEmpty(provideCode)) {
            throw new RRException("路由编码不能为空");
        }
        // 根据报表唯一编码查询报表基本信息
        PortalRouteReport routeReport = getPortalRouteReport(provideCode);
        Map<String, Object> map = new HashMap<String, Object>();
        JSONObject jsonObject = null;
        String result = null;

        try {
            String routeParameter = StringUtils.getRouteParameter(parameter, routeReport.getParameter());
            StringBuffer sb = new StringBuffer();
            String[] arr = routeParameter.split("&");
            for (String p : arr) {
                if (p.split("=").length == 1) {
                    map.put(p.split("=")[0], "");
                    sb.append("");
                } else if (StringUtils.isEmpty(p.split("=")[1])) {
                    map.put(p.split("=")[0], "");
                    sb.append("");
                } else if (!StringUtils.isEmpty(p.split("=")[1])) {
                    map.put(p.split("=")[0], p.split("=")[1]);
                    sb.append(p.split("=")[1]);
                }
            }
            //请求参数加密（key + parameter + key）
            Md5Util util = new Md5Util();
            if (!StringUtils.isEmpty(routeReport.getKey())) {
                String sign = util.getMd5("MD5", 0, null, routeReport.getKey() + sb + routeReport.getKey());
                map.put("sign", sign);
            }
            //发送请求
            HttpMethodUtil httpUtil = new HttpMethodUtil();

            result = httpUtil.getGetResult(routeReport.getUrl(), map);
            jsonObject = JSONObject.parseObject(result);

            if (!StringUtils.isEmpty(result)) {
                jsonObject = JSONObject.parseObject(result);
                if (!jsonObject.containsKey("data")) {
                    errMsg = "统一报表接口-第三方返回结果没有data节点:" + result;
                    jsonObject.put("data", null);
                    jsonObject.put("routeMsg", "第三方返回结果没有data节点,返回结果" + result);
                }
            } else {
                errMsg = "统一报表接口-第三方返回结果为空";
                jsonObject.put("routeMsg", "第三方返回结果为空");
            }
        } catch (Exception e) {
            throw new RRException("调用外部系统出错：" + provideCode + " 错误内容：" + errMsg + " 异常信息为：" + e.getMessage());
        }

        return jsonObject;
    }

    /**
     * 根据url和参数直接调用外部接口（临时提供app扫码接口，接口标准未统一）
     *
     * @param parameter
     * @param url
     * @return
     */
    public String qRResultByParam(String parameter, String url) {
        if (StringUtils.isEmpty(parameter) || StringUtils.isEmpty(url)) {
            throw new RRException("报表参数或者请求url不能为空");
        }
        Map<String, Object> map = new HashMap<String, Object>();
        //处理参数
        String routeParameter = StringUtils.getRouteParameter(parameter, parameter);
        String[] arr = routeParameter.split("&");
        for (String p : arr) {
            if (p.split("=").length == 1) {
                map.put(p.split("=")[0], "");
            } else if (StringUtils.isEmpty(p.split("=")[1])) {
                map.put(p.split("=")[0], "");
            } else if (!StringUtils.isEmpty(p.split("=")[1])) {
                map.put(p.split("=")[0], p.split("=")[1]);
            }
        }
        url = url + "?shopId=" + map.get("shopID") + "&barcodeId=" + map.get("barcode");
        HttpMethodUtil httpUtil = new HttpMethodUtil();
        String result = httpUtil.getGetResult(url, new HashMap<String, Object>());
        return result;
    }

    /**
     * 获取openApi报表信息
     *
     * @param openApiCode
     * @return
     */
    public PortalOpenapiReport getPortalOpenApiReport(String openApiCode) {
        if (StringUtils.isEmpty(openApiCode)) {
            throw new RRException("报表编码不能为空");
        }
        String reportJson =""; //redisBizUtilApi.getPortalOpenApiReport(openApiCode);
        if (StringUtils.isEmpty(reportJson)) {
            throw new RRException("报表编码 " + openApiCode + "无效");
        }
        PortalOpenapiReport report = JSONObject.parseObject(reportJson, PortalOpenapiReport.class);
        if (report == null || StringUtils.isEmpty(report.getCode())) {
            throw new RRException("报表编码:" + openApiCode + "无效，或未指定openApiCode");
        }
        return report;
    }

}
