package com.yance520.itpower.model.report;

import com.yance520.itpower.model.base.AuditAuto;

/**
 * Author : 杨杨
 * Date : 2017/08/20
 * Description :
 */
public class PortalOpenapiReport extends AuditAuto {

    //报表唯一编码，api接口请求必须参数
    private String code;
    //对应的报表编码
    private String reportcode;

    //请求外部接口key值
    private String key;
    //请求外部系统url
    private String url;
    //执行参数，格式如aa@@bb@@cc
    private String parameter;
    //请求外部系统名称
    private String name;
    //标题
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 设置：报表唯一编码，api接口请求必须参数
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取：报表唯一编码，api接口请求必须参数
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置：请求外部接口key值
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * 获取：请求外部接口key值
     */
    public String getKey() {
        return key;
    }

    /**
     * 设置：请求外部系统url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取：请求外部系统url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置：执行参数，格式如aa@@bb@@cc
     */
    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    /**
     * 获取：执行参数，格式如aa@@bb@@cc
     */
    public String getParameter() {
        return parameter;
    }

    /**
     * 设置：请求外部系统名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：请求外部系统名称
     */
    public String getName() {
        return name;
    }

    public String codeOld;

    public String getCodeOld() {
        return codeOld;
    }

    public void setCodeOld(String codeOld) {
        this.codeOld = codeOld;
    }

    public String getReportcode() {
        return reportcode;
    }

    public void setReportcode(String reportcode) {
        this.reportcode = reportcode;
    }
}
