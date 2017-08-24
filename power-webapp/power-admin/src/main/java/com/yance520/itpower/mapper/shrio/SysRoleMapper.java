package com.yance520.itpower.mapper.shrio;

import com.yance520.itpower.mapper.base.BaseMapper;
import com.yance520.itpower.model.shrio.SysRole;

import java.util.List;

/**
 * 角色管理
 * 
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {
	
	/**
	 * 查询用户创建的角色ID列表
	 */
	List<Long> queryRoleIdList(Long createUserId);
}
