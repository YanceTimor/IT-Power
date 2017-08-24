package com.yance520.itpower.model.report;

import com.yance520.itpower.model.base.AuditAuto;

/**
 * Author : 杨杨
 * Date : 2017/08/20
 * Description : 执行的sql语句
 */
public class PortalExecuteSql extends AuditAuto {

    // 存储过程标题
    private String title;
    // sql语句唯一编码
    private String sqlcode;
    private String sqlcodeOld;
    // 执行语句
    private String executeSql;
    // JDBC数据源编码
    private String dataSourceCode;
    // 参数 @@分割 如 a@@b@@c
    private String parameter;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSqlcode() {
        return sqlcode;
    }

    public void setSqlcode(String sqlcode) {
        this.sqlcode = sqlcode;
    }

    public String getDataSourceCode() {
        return dataSourceCode;
    }

    public void setDataSourceCode(String dataSourceCode) {
        this.dataSourceCode = dataSourceCode;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getExecuteSql() {
        return executeSql;
    }

    public void setExecuteSql(String executeSql) {
        this.executeSql = executeSql;
    }

    public String getSqlcodeOld() {
        return sqlcodeOld;
    }

    public void setSqlcodeOld(String sqlcodeOld) {
        this.sqlcodeOld = sqlcodeOld;
    }
}
