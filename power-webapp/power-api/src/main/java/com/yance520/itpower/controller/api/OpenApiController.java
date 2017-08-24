package com.yance520.itpower.controller.api;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.yance520.itpower.interceptor.AuthorizationInterceptor;
import com.yance520.itpower.annotation.OpenAuth;
import com.yance520.itpower.model.report.PortalOpenapiReport;
import com.yance520.itpower.model.sys.SysOperationLog;
import com.yance520.itpower.service.sys.SysoperationLogService;
import com.yance520.itpower.utils.ConstantsUtil;
import com.yance520.itpower.utils.HttpContextUtils;
import com.yance520.itpower.utils.R;
import com.yance520.itpower.utils.redis.ReportUtil;
import com.yance520.itpower.xss.SQLFilter;
import io.swagger.annotations.*;
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
 * 对外部系统提供统一的数据接口（portal_openapi_report管理）
 */
@Api(value = "数据化运营平台开放统一接口", description = "开放统一接口")
@RestController
@RequestMapping("/openApi/portal")
public class OpenApiController {

    Logger log = Logger.getLogger(this.getClass());

    @Reference
    private SysoperationLogService sysoperationLogService;

    @Autowired
    private ReportUtil reportUtil;


    /**
     * 外部系统调用openApi统一入口
     */
    @OpenAuth
    @RequestMapping(value = "report", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "开放统一接口", httpMethod = "GET", notes = "其他参数参考接口文档", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openApiCode", value = "接口编码", paramType = "path", dataType = "string"),
            @ApiImplicitParam(name = "sign", value = "签名信息", paramType = "header", dataType = "string")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 0, message = "请求成功"),
            @ApiResponse(code = 1, message = "请求失败"),
            @ApiResponse(code = -98, message = "验签失败"),
            @ApiResponse(code = -99, message = "未登录")
    })
    public R portalCustom(HttpServletRequest request, HttpServletResponse response, String openApiCode) {
        String parameter = null;
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        String result = null;
        SysOperationLog log = (SysOperationLog) request.getAttribute(AuthorizationInterceptor.LOGIN_USER_OPERATION_LOG);
        log.setReportcode(openApiCode);
        PortalOpenapiReport report = null;
        JSONObject jsonObject = null;
        String errMsg = null;

        try {
            //参数处理
            parameter = HttpContextUtils.getRequestParameter(request);
            //根据code从redis查报表信息
            report = reportUtil.getPortalOpenApiReport(openApiCode);
            if (report.getReportcode().indexOf(ConstantsUtil.OpenExecuteType.PROVIDE) != -1) {
                log.setRemark("统一报表接口-第三方接口查询数据");
                jsonObject = reportUtil.routeResultByParam(report.getReportcode(), SQLFilter.sqlInject(parameter));
            } else if (report.getReportcode().indexOf(ConstantsUtil.OpenExecuteType.REP) != -1) {
                log.setRemark("统一报表接口-平台数据");
                list = reportUtil.jdbcProListResultListMapByParam(SQLFilter.sqlInject(report.getReportcode()), SQLFilter.sqlInject(parameter));
            }
        } catch (Exception e) {
            log.setError(StringUtils.substring(e.toString(), 0, 2000));
            errMsg = "执行openApi统一报表程序异常";
        } finally {
            log.setEndTime(new Date());
            sysoperationLogService.SaveLog(log);
        }

        // 返回数据集，必须固定格式
        if (report.getReportcode().indexOf(ConstantsUtil.OpenExecuteType.PROVIDE) != -1) {
            return R.restul(errMsg, jsonObject.get("data")).put("routeMsg", jsonObject.get("routeMsg"));
        } else if (report.getReportcode().indexOf(ConstantsUtil.OpenExecuteType.REP) != -1) {
            return R.restul(errMsg, list);
        } else {
            return R.error("请求无法处理！");
        }
    }


    /**
     * 获取报表模板
     *
     * @param request
     * @param response
     * @param code
     */
    @OpenAuth
    @RequestMapping(value = "reportModule", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "开放统一接口", httpMethod = "GET", notes = "其他参数参考接口文档", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openApiCode", value = "接口编码", paramType = "path", dataType = "string"),
            @ApiImplicitParam(name = "code", value = "模板编码", paramType = "path", dataType = "string"),
            @ApiImplicitParam(name = "sign", value = "签名信息", paramType = "header", dataType = "string")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 0, message = "请求成功"),
            @ApiResponse(code = 1, message = "请求失败"),
            @ApiResponse(code = -98, message = "验签失败"),
            @ApiResponse(code = -99, message = "未登录")
    })
    public R reportModule(HttpServletRequest request, HttpServletResponse response, String openApiCode, String code) {
        return R.success(JSONObject.parseObject(reportUtil.getPageModule(code).getOnlineStructrue()));
    }
}
