package com.yance520.itpower.model.sys;

import com.yance520.itpower.model.base.AuditAuto;

/**
 * Author : 杨杨
 * Date : 2017/08/20
 * Description : 系统FTP配置信息表
 */
public class SysFtpConfig extends AuditAuto {

    //用户名
    private String username;
    //密码
    private String password;
    //地址
    private String host;
    //端口
    private Integer port;
    private Integer connecttime;

    private String rootpath;

    private String origin;

    public Integer getConnecttime() {
        return connecttime;
    }

    public void setConnecttime(Integer connecttime) {
        this.connecttime = connecttime;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getRootpath() {
        return rootpath;
    }

    public void setRootpath(String rootpath) {
        this.rootpath = rootpath;
    }

    /**
     * 设置：用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取：用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置：密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取：密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置：地址
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * 获取：地址
     */
    public String getHost() {
        return host;
    }

    /**
     * 设置：端口
     */
    public void setPort(Integer port) {
        this.port = port;
    }

    /**
     * 获取：端口
     */
    public Integer getPort() {
        return port;
    }
}
