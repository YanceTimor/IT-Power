package com.yance520.itpower.mapper.shrio;

import com.yance520.itpower.mapper.base.BaseMapper;
import com.yance520.itpower.model.shrio.SysUserRole;

import java.util.List;

/**
 * 用户与角色对应关系
 * 
 */
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {
	
	/**
	 * 根据用户ID，获取角色ID列表
	 */
	List<Long> queryRoleIdList(Long userId);
}
