package com.yestae.user.manage.modular.system.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yestae.user.common.exception.BizExceptionEnum;
import com.yestae.user.common.exception.BussinessException;
import com.yestae.user.manage.core.mutidatasource.annotion.DataSource;
import com.yestae.user.manage.modular.system.dao.RoleDao;
import com.yestae.user.manage.modular.system.persistence.dao.RelationMapper;
import com.yestae.user.manage.modular.system.persistence.dao.RoleMapper;
import com.yestae.user.manage.modular.system.persistence.model.Relation;
import com.yestae.user.manage.modular.system.persistence.model.Role;
import com.yestae.user.manage.modular.system.service.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService {

    @Resource
    RoleMapper roleMapper;

    @Resource
    RoleDao roleDao;

    @Resource
    RelationMapper relationMapper;

    @Override
    @DataSource(name="dataSourceUcMgr")
    @Transactional(readOnly = false)
    public void setAuthority(String roleId, String ids) {

        // 删除该角色所有的权限
        this.roleDao.deleteRolesById(roleId);

        // 添加新的权限ids.split(",")
        for (String id : ids.split(",")) {
            Relation relation = new Relation();
            relation.setRoleid(roleId);
            relation.setMenuid(id);
            this.relationMapper.insert(relation);
        }
    }

    @Override
    @DataSource(name="dataSourceUcMgr")
    @Transactional(readOnly = false)
    public void delRoleById(String roleId) {
    	
    	//查询是否有关联的下级角色，如果存在则不能删除
  		EntityWrapper<Role> wrapperRole = new EntityWrapper<>();
  		wrapperRole.eq("pid", roleId);
  		int num = roleMapper.selectCount(wrapperRole);
  		if(num > 0){
  			throw new BussinessException(BizExceptionEnum.DELETE_ROLE_ERROE);
  		}
    	
        //删除角色
        this.roleMapper.deleteById(roleId);

        // 删除该角色所有的权限
        this.roleDao.deleteRolesById(roleId);
    }

}
