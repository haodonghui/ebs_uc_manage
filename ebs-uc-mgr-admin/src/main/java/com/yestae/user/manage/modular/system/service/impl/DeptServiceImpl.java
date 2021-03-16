package com.yestae.user.manage.modular.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yestae.user.common.exception.BizExceptionEnum;
import com.yestae.user.common.exception.BussinessException;
import com.yestae.user.manage.core.mutidatasource.annotion.DataSource;
import com.yestae.user.manage.modular.system.persistence.dao.DeptMapper;
import com.yestae.user.manage.modular.system.persistence.model.Dept;
import com.yestae.user.manage.modular.system.service.IDeptService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

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
  		QueryWrapper<Dept> wrapperDept = new QueryWrapper<>();
  		wrapperDept.eq("pid", deptId);
  		int num = deptMapper.selectCount(wrapperDept);
  		if(num > 0){
  			throw new BussinessException(BizExceptionEnum.DELETE_DEPT_ERROE);
  		}

        QueryWrapper<Dept> wrapper = new QueryWrapper<>();
        wrapper = wrapper.like("pids", "%[" + dept.getId() + "]%");
        List<Dept> subDepts = deptMapper.selectList(wrapper);
        for (Dept temp : subDepts) {
            temp.deleteById();
        }

        dept.deleteById();
    }
}
