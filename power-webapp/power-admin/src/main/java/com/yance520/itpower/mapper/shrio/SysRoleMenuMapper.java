package com.yance520.itpower.mapper.shrio;

import com.yance520.itpower.mapper.base.BaseMapper;
import com.yance520.itpower.model.shrio.SysRoleMenu;

import java.util.List;

/**
 * 角色与菜单对应关系
 * 
 */
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {
	
	/**
	 * 根据角色ID，获取菜单ID列表
	 */
	List<Long> queryMenuIdList(Long roleId);
}
