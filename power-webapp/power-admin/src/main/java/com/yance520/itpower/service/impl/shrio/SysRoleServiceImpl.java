package com.yance520.itpower.service.impl.shrio;

import com.yance520.itpower.mapper.shrio.SysRoleMapper;
import com.yance520.itpower.model.shrio.SysRole;
import com.yance520.itpower.service.shrio.SysRoleMenuService;
import com.yance520.itpower.service.shrio.SysRoleService;
import com.yance520.itpower.service.shrio.SysUserRoleService;
import com.yance520.itpower.service.shrio.SysUserService;
import com.yance520.itpower.utils.RRException;
import com.yance520.itpower.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 角色
 * 
 */
@Service("sysRoleService")
public class SysRoleServiceImpl implements SysRoleService {
	@Autowired
	private SysRoleMapper sysRoleMapper;
	@Autowired
	private SysRoleMenuService sysRoleMenuService;
	@Autowired
	private SysUserRoleService sysUserRoleService;
	@Autowired
	private SysUserService sysUserService;

	public SysRole queryObject(Long roleId) {
		return sysRoleMapper.queryObject(roleId);
	}

	public List<SysRole> queryList(Map<String, Object> map) {
		return sysRoleMapper.queryList(map);
	}

	public int queryTotal(Map<String, Object> map) {
		return sysRoleMapper.queryTotal(map);
	}

	@Transactional
	public void save(SysRole role) {
		role.setCreateTime(new Date());
		sysRoleMapper.save(role);
		
		//检查权限是否越权
		checkPrems(role);
		
		//保存角色与菜单关系
		sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
	}

	@Transactional
	public void update(SysRole role) {
		sysRoleMapper.update(role);
		
		//检查权限是否越权
		checkPrems(role);
		
		//更新角色与菜单关系
		sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
	}

	@Transactional
	public void deleteBatch(Long[] roleIds) {
		sysRoleMapper.deleteBatch(roleIds);
	}
	
	public List<Long> queryRoleIdList(Long createUserId) {
		return sysRoleMapper.queryRoleIdList(createUserId);
	}

	/**
	 * 检查权限是否越权
	 */
	private void checkPrems(SysRole role){
		//如果不是超级管理员，则需要判断角色的权限是否超过自己的权限
		if(role.getCreateUserId() == Constant.SUPER_ADMIN){
			return ;
		}
		
		//查询用户所拥有的菜单列表
		List<Long> menuIdList = sysUserService.queryAllMenuId(role.getCreateUserId());
		
		//判断是否越权
		if(!menuIdList.containsAll(role.getMenuIdList())){
			throw new RRException("新增角色的权限，已超出你的权限范围");
		}
	}
}
