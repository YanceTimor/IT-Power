//package com.yance520.itpower.utils.redis;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
///**
// * redis 业务数据处理
// *
// */
//@Component("redisBizUtilApi")
//public class RedisBizUtilApi {
//
//    @Autowired
//    private RedisUtil redisUtil;
//
//    //数据运营平台统一前缀
//    private static String KEY_PREFIX = "power";
//
//    //////////////////////////用户token基本信息设置////////////////////////////////
//
//    /**
//     * 设置用户登陆token信息，
//     *
//     * @param token token值
//     * @param value json字符串
//     */
//    public void setApiToken(String token, String value) {
//        redisUtil.put(String.format("%s:api:token", KEY_PREFIX), String.format("token:%s", token), value);
//    }
//
//    /**
//     * 根据token值获取
//     *
//     * @param token 用户token
//     * @return json字符串
//     */
//    public String getApiToken(String token) {
//        return redisUtil.get(String.format("%s:api:token", KEY_PREFIX), String.format("token:%s", token));
//    }
//
//    /**
//     * 根据token值删除
//     *
//     * @param token token值
//     */
//    public void removeApiToken(String token) {
//        redisUtil.remove(String.format("%s:api:token", KEY_PREFIX), String.format("token:%s", token));
//    }
//
//    /**
//     * 判断是否存在token
//     *
//     * @param token 需要判断的token值
//     * @return
//     */
//    public boolean hasApiToken(String token) {
//        return redisUtil.hasKey(String.format("%s:api:token", KEY_PREFIX), String.format("token:%s", token));
//    }
//
//
//    public String getHashByHashKey(String hashKey) {
//        return redisUtil.getHashByHashKey(String.format("%s:api:token", KEY_PREFIX), String.format("token:%s", hashKey));
//    }
//
//    //////////////////////////用户token基本信息设置////////////////////////////////
//
//    /**
//     * 设置用户登陆信息，应包括用户id,用户名，角色id等
//     *
//     * @param jobNumber token值
//     * @param value     json字符串，包含用户基本信息
//     */
//    public void setUserInfo(String jobNumber, String value) {
//        redisUtil.put(String.format("%s:api:userinfo", KEY_PREFIX), String.format("jobnumber:%s", jobNumber), value);
//    }
//
//    /**
//     * 根据jobNumber值获取用户基本信息
//     *
//     * @param jobNumber 工号
//     * @return json字符串
//     */
//    public String getUserInfo(String jobNumber) {
//        return redisUtil.get(String.format("%s:api:userinfo", KEY_PREFIX), String.format("jobnumber:%s", jobNumber));
//    }
//
//    /**
//     * 根据jobNumber值删除用户基本信息
//     *
//     * @param jobNumber 工号
//     */
//    public void removeUserInfo(String jobNumber) {
//        redisUtil.remove(String.format("%s:api:userinfo", KEY_PREFIX), String.format("jobnumber:%s", jobNumber));
//    }
//
//    /**
//     * 判断是否存在jobNumber
//     *
//     * @param jobNumber 工号
//     * @return
//     */
//    public boolean hasUserInfo(String jobNumber) {
//        return redisUtil.hasKey(String.format("%s:api:userinfo", KEY_PREFIX), String.format("jobnumber:%s", jobNumber));
//    }
//
//
//    //////////////////////////报表信息////////////////////////////////
//    public String getPortalProcedure(String procode) {
//        return redisUtil.get(String.format("%s:report:procedure", KEY_PREFIX), procode);
//    }
//
//    public String getPortalExecuteSql(String sqlcode) {
//        return redisUtil.get(String.format("%s:report:executesql", KEY_PREFIX), sqlcode);
//    }
//
//    public String getPortalReport(String code) {
//        return redisUtil.get(String.format("%s:report:info", KEY_PREFIX), code);
//    }
//
//    public String getPortalDataSource(String code) {
//        return redisUtil.get(String.format("%s:report:datasource", KEY_PREFIX), code);
//    }
//
//    public String getPortalRouteReport(String code) {
//        return redisUtil.get(String.format("%s:openapi:route", KEY_PREFIX), code);
//    }
//
//    public String getPortalOpenApiReport(String code) {
//        return redisUtil.get(String.format("%s:openapi:report", KEY_PREFIX), code);
//    }
//
//    /*****************报表模板配置信息*****************/
//    public void setPageModule(String code, String value) {
//        redisUtil.put(String.format("%s:report:page", KEY_PREFIX), code, value);
//    }
//
//    public String getPageModule(String code) {
//        return redisUtil.get(String.format("%s:report:page", KEY_PREFIX), code);
//    }
//
//    public void removePageModule(String code) {
//        redisUtil.remove(String.format("%s:report:page", KEY_PREFIX), code);
//    }
//
//    public boolean hasPageModule(String code) {
//        return redisUtil.hasKey(String.format("%s:report:page", KEY_PREFIX), code);
//    }
//
//    /**
//     * 获取报表标题信息
//     *
//     * @param code
//     * @return
//     */
//    public String getReportColumns(String code) {
//        return redisUtil.get(String.format("%s:report:columns", KEY_PREFIX), code);
//    }
//
//    //菜单信息
//    public String getRoleMenu(Integer roleId) {
//        return redisUtil.get(String.format("%s:role:menu", KEY_PREFIX), roleId.toString());
//    }
//
//    //获取ftp用户信息
//    public String getFtpInfo(Long id) {
//        return redisUtil.get(String.format("%s:sys:ftp", KEY_PREFIX), id.toString());
//    }
//
//    //获取系统全局配置信息
//    public String getSysConfig(String key) {
//        return redisUtil.get(String.format("%s:sys:config", KEY_PREFIX), key.toString());
//    }
//
//    /*****************日复盘基础数据 用户信息 权限-大区-门店-商行*****************/
//    public void setActionPlanUserInfoAndAreaStireShopInfo(String userId, String value) {
//        redisUtil.put(String.format("%s:store:replay:auth", KEY_PREFIX), userId, value);
//    }
//
//    public String getActionPlanUserInfoAndAreaStireShopInfo(String userId) {
//        return redisUtil.get(String.format("%s:store:replay:auth", KEY_PREFIX), userId);
//    }
//
//    public void removeActionPlanUserInfoAndAreaStireShopInfo(String userId) {
//        redisUtil.remove(String.format("%s:store:replay:auth", KEY_PREFIX), userId);
//    }
//
//    public boolean hasActionPlanUserInfoAndAreaStireShopInfo(String userId) {
//        return redisUtil.hasKey(String.format("%s:store:replay:auth", KEY_PREFIX), userId);
//    }
//
//
//    /*****************日复盘基础数据 区总团队 具体角色划分 45：品类教练   111：区长  9：策略组*****************/
//    public void setActionPlanSkuInfo(String userId, String value) {
//        redisUtil.put(String.format("%s:store:replay:sku", KEY_PREFIX), userId, value);
//    }
//
//    public String getActionPlanSkuInfo(String userId) {
//        return redisUtil.get(String.format("%s:store:replay:sku", KEY_PREFIX), userId);
//    }
//
//    public void removeActionPlanSkuInfo(String userId) {
//        redisUtil.remove(String.format("%s:store:replay:sku", KEY_PREFIX), userId);
//    }
//
//    public boolean hasActionPlanSkuInfo(String userId) {
//        return redisUtil.hasKey(String.format("%s:store:replay:sku", KEY_PREFIX), userId);
//    }
//
//}
