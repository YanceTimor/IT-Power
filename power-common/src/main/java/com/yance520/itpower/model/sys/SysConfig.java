package com.yance520.itpower.model.sys;


import org.hibernate.validator.constraints.NotBlank;

/**
 * Author : 杨杨
 * Date : 2017/08/20
 * Description : 系统配置信息
 */
public class SysConfig {
	private Long id;
	@NotBlank(message="参数名不能为空")
	private String key;
	private String keyOld;
	@NotBlank(message="参数值不能为空")
	private String value;
	private String remark;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}

	public String getKeyOld() {
		return keyOld;
	}

	public void setKeyOld(String keyOld) {
		this.keyOld = keyOld;
	}

	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
