package com.yestae.user.manage.modular.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.yestae.user.common.exception.BizExceptionEnum;
import com.yestae.user.common.exception.BussinessException;
import com.yestae.user.manage.core.mutidatasource.annotion.DataSource;
import com.yestae.user.manage.modular.system.persistence.dao.DeptMapper;
import com.yestae.user.manage.modular.system.persistence.model.Dept;
import com.yestae.user.manage.modular.system.service.IDeptService;

@Service
@Transactional
public class DeptServiceImpl implements IDeptService {

    @Resource
    DeptMapper deptMapper;

    @Override
    @DataSource(name="dataSourceUcMgr")
    public void deleteDept(String deptId) {

        Dept dept = deptMapper.selectById(deptId);
        
        //查询是否有关联的下级部门，如果存在则不能删除
  		EntityWrapper<Dept> wrapperDept = new EntityWrapper<>();
  		wrapperDept.eq("pid", deptId);
  		int num = deptMapper.selectCount(wrapperDept);
  		if(num > 0){
  			throw new BussinessException(BizExceptionEnum.DELETE_DEPT_ERROE);
  		}

        Wrapper<Dept> wrapper = new EntityWrapper<>();
        wrapper = wrapper.like("pids", "%[" + dept.getId() + "]%");
        List<Dept> subDepts = deptMapper.selectList(wrapper);
        for (Dept temp : subDepts) {
            temp.deleteById();
        }

        dept.deleteById();
    }
}
