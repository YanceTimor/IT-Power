package com.yance520.itpower.controller.api;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.yance520.itpower.interceptor.AuthorizationInterceptor;
//import com.yance520.itpower.utils.redis.RedisBizUtilApi;
import com.yance520.itpower.utils.redis.ReportUtil;
import com.yance520.itpower.annotation.OpenAuth;
import com.yance520.itpower.model.report.PortalOpenapiReport;
import com.yance520.itpower.model.sys.SysOperationLog;
import com.yance520.itpower.service.sys.SysoperationLogService;
import com.yance520.itpower.utils.HttpContextUtils;
import com.yance520.itpower.utils.R;
import com.yance520.itpower.xss.SQLFilter;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 提供统一报表数据接口给客户端（走的是统一接口，只是增加了sign验证）
 * APP报表存错过程报表统一入口
 */
@RestController
@RequestMapping("/app/api")
public class AppApiController {

    Logger log = Logger.getLogger(this.getClass());
    @Reference
    private SysoperationLogService sysoperationLogService;
    @Autowired
    private ReportUtil reportUtil;
//    @Autowired
//    private RedisBizUtilApi redisBizUtilApi;


    /**
     * APP报表存储过程报表统一入口
     *
     * @param req
     * @param response
     * @param openApiCode 报表编码,字段名唯一，且不允许修改
     * @param sign        报表和token生成sign
     * @return
     */
    @OpenAuth
    @RequestMapping(value = "report", method = RequestMethod.GET)
    @ResponseBody
    public R portalCustom(HttpServletRequest req, HttpServletResponse response, String openApiCode,
                          String sign, String shopID, String barcode) {
        String parameter = null;
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        //记录日志
        SysOperationLog log = (SysOperationLog) req.getAttribute(AuthorizationInterceptor.LOGIN_USER_OPERATION_LOG);
        log.setReportcode(openApiCode);
        log.setRemark("APP报表存储过程报表统一入口");
        String errMsg = null;
        try {
            parameter = HttpContextUtils.getRequestParameter(req);
            String openApiJsonStr ="";// redisBizUtilApi.getPortalOpenApiReport(openApiCode);
            PortalOpenapiReport portalOpenapiReport = null;
            String reportCode = "";
            if (StringUtils.isBlank(openApiJsonStr)) {
                reportCode = openApiCode;
            } else {
                portalOpenapiReport = JSONObject.parseObject(openApiJsonStr, PortalOpenapiReport.class);
                if (portalOpenapiReport == null || portalOpenapiReport.getReportcode() == null) {
                    reportCode = openApiCode;
                } else {
                    reportCode = portalOpenapiReport.getReportcode();
                }
            }
            list = reportUtil.jdbcProListResultListMapByParam(SQLFilter.sqlInject(reportCode), SQLFilter.sqlInject(parameter));
        } catch (Exception e) {
            log.setStatus(1);
            log.setError(StringUtils.substring(e.toString(), 0, 2000));
            errMsg = "执行APP报表存储过程报表异常";
        } finally {
            log.setEndTime(new Date());
            sysoperationLogService.SaveLog(log);
        }
        return R.restul(errMsg, list);
    }
}
