package com.yance520.itpower.model.sys;


import com.yance520.itpower.model.base.AuditAuto;

/**
 * Author : 杨杨
 * Date : 2017/08/20
 * Description : 系统启动日志记录
 */
public class SysStartLog extends AuditAuto {

	//IP地址
	private String ip;
	//用户名
	private String userName;
	//本机名称
	private String hostName;
	//计算机名称
	private String computerName;
	//计算机域名
	private String userDomain;

	/**
	 * 设置：IP地址
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	/**
	 * 获取：IP地址
	 */
	public String getIp() {
		return ip;
	}
	/**
	 * 设置：用户名
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 获取：用户名
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * 设置：本机名称
	 */
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	/**
	 * 获取：本机名称
	 */
	public String getHostName() {
		return hostName;
	}
	/**
	 * 设置：计算机名称
	 */
	public void setComputerName(String computerName) {
		this.computerName = computerName;
	}
	/**
	 * 获取：计算机名称
	 */
	public String getComputerName() {
		return computerName;
	}
	/**
	 * 设置：计算机域名
	 */
	public void setUserDomain(String userDomain) {
		this.userDomain = userDomain;
	}
	/**
	 * 获取：计算机域名
	 */
	public String getUserDomain() {
		return userDomain;
	}
}
