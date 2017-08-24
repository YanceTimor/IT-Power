package com.yance520.itpower.model.report;

import com.yance520.itpower.model.base.AuditAuto;

/**
 * Author : 杨杨
 * Date : 2017/08/20
 * Description : 存储过程
 */
public class PortalProcedure extends AuditAuto {

    // 存储过程标题
    private String title;
    // 存储过程唯一编码
    private String procode;
    private String procodeOld;
    // JDBC数据源编码
    private String dataSourceCode;
    // 存储过程名称
    private String proname;
    // 参数 @@分割 如 a@@b@@c
    private String parameter;
    // DruidDataSource数据源
    private String prodb;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProname() {
        return proname;
    }

    public void setProname(String proname) {
        this.proname = proname;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getProcode() {
        return procode;
    }

    public void setProcode(String procode) {
        this.procode = procode;
    }

    public String getProdb() {
        return prodb;
    }

    public void setProdb(String prodb) {
        this.prodb = prodb;
    }

    public String getDataSourceCode() {
        return dataSourceCode;
    }

    public void setDataSourceCode(String dataSourceCode) {
        this.dataSourceCode = dataSourceCode;
    }

    public String getProcodeOld() {
        return procodeOld;
    }

    public void setProcodeOld(String procodeOld) {
        this.procodeOld = procodeOld;
    }
}
