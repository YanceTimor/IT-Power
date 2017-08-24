package com.yance520.itpower.db;

/**
 * 多数据源类型（枚举）
 * 命名必须与DataSourceConstants相同
 * 命名规则：数据库类型+业务系统名称+数据库名称
 *
 * Author : 杨杨
 * Date : 2017/08/20
 * Description :
 */
public enum DataSourceEnum {

	// mysql
	MYSQL_PORTAL_MASTER("mysqlPortalMaster", false),
	MYSQL_PORTAL_SLAVE("mysqlPortalSlave", true),
    MYSQL_PORTAL_APP("mysqlPortalApp", false),
	// sqlServer
	SQLSERVER_PORTAL_MASTER("sqlserverPortalMaster", false),
	SQLSERVER_PORTAL_SLAVE("sqlserverPortalSlave", false),
	// oracle
	ORACLE_PORTAL_MASTER("oraclePortalMaster", false),
	ORACLE_PORTAL_SLAVE("oraclePortalSlave", false);

	// 数据源名称
	private String name;
	// 是否是默认数据源
	private boolean master;

	DataSourceEnum(String name, boolean master) {
		this.name = name;
		this.master = master;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isMaster() {
		return master;
	}

	public void setMaster(boolean master) {
		this.master = master;
	}

	public String getDefault() {
		String defaultDataSource = "";
		for (DataSourceEnum dataSourceEnum : DataSourceEnum.values()) {
			if (!"".equals(defaultDataSource)) {
				break;
			}
			if (dataSourceEnum.master) {
				defaultDataSource = dataSourceEnum.getName();
			}
		}
		return defaultDataSource;
	}

}
