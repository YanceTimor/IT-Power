package com.yance520.itpower.controller.api;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yance520.itpower.interceptor.AuthorizationInterceptor;
import com.yance520.itpower.utils.HttpContextUtils;
import com.yance520.itpower.utils.QrCodeUtil;
import com.yance520.itpower.utils.R;
import com.yance520.itpower.annotation.OpenAuth;
import com.yance520.itpower.model.sys.SysOperationLog;
import com.yance520.itpower.service.sys.SysoperationLogService;
import com.yance520.itpower.utils.redis.ReportUtil;
import org.apache.commons.lang.StringUtils;
import com.yance520.itpower.xss.SQLFilter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * APP扫码接口
 * 单独的业务逻辑 只针对app扫码
 */
@RestController
@RequestMapping("/app/api")
public class AppQrCodeApiController {

    Logger log = Logger.getLogger(this.getClass());

    @Reference
    private SysoperationLogService sysoperationLogService;

    @Autowired
    private ReportUtil reportUtil;

    public static final String URL = "http://10.0.12.15:32128/api/v1/businessman/search/scene2";

    /**
     * 查我们自己的dws库  非实时数据  门店实时销售、实时库存、销售相关的数据
     * 调用外部接口  实时数据
     *
     * @return
     */
    @OpenAuth
    @RequestMapping(value = "qrCode", method = RequestMethod.GET)
    @ResponseBody
    public R portalCustom(HttpServletRequest req, HttpServletResponse response, String shopID, String barcode) {
        String parameter = null;
        String result = null;
        JSONObject jsonObject = null;
        JSONArray jsonArrayResult = null;
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> hanaList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> shopIdList = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = null;
        List<Object> dateList = new ArrayList<Object>();
        QrCodeUtil util = new QrCodeUtil();
        SysOperationLog log = (SysOperationLog) req.getAttribute(AuthorizationInterceptor.LOGIN_USER_OPERATION_LOG);
        log.setReportcode("REP_000043");
        String errMsg = null;
        try {
            //调用自己库查数据
            parameter = HttpContextUtils.getRequestParameter(req);
            //首先根据parameter里面的shopId去查  该门店所在区域下下的所有门店
            shopIdList = reportUtil.jdbcProListResultListMapByParam(SQLFilter.sqlInject("REP_000045"), SQLFilter.sqlInject(parameter));
            //查hana数据库  查询商品信息(查询效率太低 暂时注释)
            //  hanaList = reportUtil.jdbcProListResultListMapByParam("REP_000058", SQLFilter.sqlInject(parameter));
            hanaList = new ArrayList<Map<String, Object>>();
            String saleDate = "";
            String saleAmount = "";
            String goodsName = "";
            for (Map<String, Object> item : hanaList) {
                saleDate = saleDate + item.get("SALEDATE").toString() + ",";
                saleAmount = saleAmount + item.get("AMOUNT").toString() + ",";
                goodsName = item.get("GOODSNAME").toString();
            }
            if (saleDate != null && !saleDate.equals("")) {
                saleDate.substring(0, saleDate.length() - 1);
            } else {
                saleDate = util.getDate();
            }
            if (saleAmount != null && !saleAmount.equals("")) {
                saleAmount.substring(0, saleAmount.length() - 1);
            }
            map = new HashMap<String, Object>();
            map.put("saleDate", saleDate);
            map.put("saleAmount", saleAmount);
            map.put("goodsName", goodsName);
            //将查出来的门店结果，拼接成参数
            StringBuffer newParam = new StringBuffer();
            for (Map<String, Object> item : shopIdList) {
                newParam.append("'" + item.get("shopId") + "',");
            }
            String newParamStr = "shopID=" + newParam.toString().substring(1, newParam.toString().length() - 2) + "@@barcode=" + barcode;
            //查非实时数据（我们自己的库），注意此处code  admin配置的是98  为了给app扫码统一code（REP_000043）
            list = reportUtil.jdbcProListResultListMapByParam(SQLFilter.sqlInject("REP_000043"), newParamStr);
            //调用外部接口获取数据
            result = reportUtil.qRResultByParam(SQLFilter.sqlInject(newParamStr), URL);
            if (!StringUtils.isEmpty(result)) {
                jsonObject = JSONObject.parseObject(result);
            } else {
                errMsg = errMsg + "调用外部接口获取数据异常。";
            }
            Map<String, Object> resMap = new HashMap<String, Object>();
            JSONArray jsonArray = (JSONArray) jsonObject.get("data");
            //第一个hana销售量；第二个实时数据；第三部分非实时数据
            dateList.add(0, map);
            dateList.add(1, jsonArray);
            dateList.add(2, list);
            if (dateList != null && dateList.size() > 0) {
                jsonArrayResult = util.createJsonTemplate(dateList, shopID);
            } else {
                return R.success(jsonArrayResult);
            }
        } catch (Exception e) {
            log.setStatus(1);
            log.setError(StringUtils.substring(e.toString(), 0, 2000));
            errMsg = errMsg + "执行APP报表存储过程报表异常。";
        } finally {
            log.setEndTime(new Date());
            log.setRemark("app@@qrCode");
            sysoperationLogService.SaveLog(log);
        }
        return R.restul(errMsg, jsonArrayResult);
    }
}
