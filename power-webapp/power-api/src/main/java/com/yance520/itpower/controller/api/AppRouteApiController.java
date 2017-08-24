package com.yance520.itpower.controller.api;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.yance520.itpower.annotation.OpenAuth;
import com.yance520.itpower.model.sys.SysOperationLog;
import com.yance520.itpower.service.sys.SysoperationLogService;
import com.yance520.itpower.utils.HttpContextUtils;
import com.yance520.itpower.utils.R;
import com.yance520.itpower.utils.redis.ReportUtil;
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

import static com.yance520.itpower.interceptor.AuthorizationInterceptor.LOGIN_USER_OPERATION_LOG;

/**
 * 调用外部系统，然后返回数据给客户端（portal_route_report管理）
 */
@RestController
@RequestMapping("/app/routeApi")
public class AppRouteApiController {

    Logger log = Logger.getLogger(this.getClass());

    @Reference
    private SysoperationLogService sysoperationLogService;

    @Autowired
    private ReportUtil reportUtil;

    /**
     * APP报表存储过程报表路由外部系统统一入口
     */
    @OpenAuth
    @RequestMapping(value = "report", method = RequestMethod.GET)
    @ResponseBody
    public R portalCustom(HttpServletRequest req, HttpServletResponse response, String provideCode,
                          String sign) {
        String parameter = null;
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        String result = null;
        JSONObject jsonObject = null;
        SysOperationLog log = (SysOperationLog) req.getAttribute(LOGIN_USER_OPERATION_LOG);
        log.setReportcode(provideCode);
        log.setRemark("第三方接口数据");
        String errMsg = null;
        try {
            parameter = HttpContextUtils.getParameterForLog(req);
            //调用外部接口获取数据
            jsonObject = reportUtil.routeResultByParam(provideCode, parameter);
        } catch (Exception e) {
            log.setStatus(1);
            log.setError(StringUtils.substring(e.toString(), 0, 2000));
            errMsg = "调用外部接口异常";
        } finally {
            log.setEndTime(new Date());
            sysoperationLogService.SaveLog(log);
        }
        return R.restul(errMsg, jsonObject.get("data")).put("routeMsg", jsonObject.get("routeMsg"));

    }
}
